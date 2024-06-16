package fr.epsi.mspr.arosaje.service.plant;

import fr.epsi.mspr.arosaje.entity.Plant;
import fr.epsi.mspr.arosaje.entity.User;
import fr.epsi.mspr.arosaje.entity.dto.plant.PlantDto;
import fr.epsi.mspr.arosaje.entity.dto.plant.PlantSaveRequest;
import fr.epsi.mspr.arosaje.entity.mapper.PlantMapper;
import fr.epsi.mspr.arosaje.exception.plant.PlantInUseException;
import fr.epsi.mspr.arosaje.exception.plant.PlantNotFoundException;
import fr.epsi.mspr.arosaje.repository.PlantRepository;
import fr.epsi.mspr.arosaje.service.GuardianshipService;
import fr.epsi.mspr.arosaje.service.PlantService;
import fr.epsi.mspr.arosaje.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @Mock
    private UserService userService;

    @Mock
    private GuardianshipService guardianshipService;

    @Mock
    private PlantMapper plantMapper;

    @InjectMocks
    private PlantService plantService;

    private Plant plant;
    private User user;
    private PlantDto plantDto;
    private PlantSaveRequest plantSaveRequest;

    @BeforeEach
    void setUp() {
        plant = new Plant();
        user = new User();
        plantDto = new PlantDto();
        plantSaveRequest = new PlantSaveRequest();

        plant.setId(1);
        user.setId(1);
        plant.setUser(user);
        plant.setCreatedAt(LocalDateTime.now());
        plant.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void findAll_ShouldReturnListOfPlants() {
        given(plantRepository.findAll()).willReturn(Collections.singletonList(plant));
        given(plantMapper.plantToPlantResponseDto(any(Plant.class))).willReturn(plantDto);

        List<PlantDto> result = plantService.findAll();

        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.get(0)).isSameAs(plantDto);
    }

    @Test
    void findById_ExistingId_ShouldReturnPlant() {
        given(plantRepository.findById(anyLong())).willReturn(Optional.of(plant));
        given(plantMapper.plantToPlantResponseDto(any(Plant.class))).willReturn(plantDto);

        PlantDto result = plantService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isSameAs(plantDto);
    }

    @Test
    void findById_NonExistingId_ShouldThrowException() {
        given(plantRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> plantService.findById(1L))
                .isInstanceOf(PlantNotFoundException.class);
    }

    @Test
    void delete_ExistingId_ShouldDeletePlant() {
        long plantId = 1L;
        given(plantRepository.existsById(plantId)).willReturn(true);
        willDoNothing().given(plantRepository).deleteById(plantId);
        given(guardianshipService.isPlantInUse(plantId)).willReturn(false);

        plantService.delete(plantId);

        then(plantRepository).should().existsById(plantId);
        then(plantRepository).should().deleteById(plantId);
        then(guardianshipService).should().isPlantInUse(plantId);
    }

    @Test
    void delete_WithPlantInUse_ShouldThrowException() {
        long plantId = 1L;
        given(plantRepository.existsById(plantId)).willReturn(true);
        given(guardianshipService.isPlantInUse(plantId)).willReturn(true);

        assertThatThrownBy(() -> plantService.delete(plantId))
                .isInstanceOf(PlantInUseException.class)
                .hasMessageContaining("Plant with id " + plantId + " is in use");

        then(plantRepository).should().existsById(plantId);
        then(guardianshipService).should().isPlantInUse(plantId);
    }
}
