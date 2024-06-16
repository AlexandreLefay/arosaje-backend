package fr.epsi.mspr.arosaje.service.guardianship;

import fr.epsi.mspr.arosaje.entity.Guardianship;
import fr.epsi.mspr.arosaje.entity.Status;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.GuardianshipDTO;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.GuardianshipSaveRequest;
import fr.epsi.mspr.arosaje.entity.mapper.GuardianshipMapper;
import fr.epsi.mspr.arosaje.exception.guardianship.GuardianshipNotFoundException;
import fr.epsi.mspr.arosaje.repository.GuardianshipRepository;
import fr.epsi.mspr.arosaje.repository.StatusRepository;
import fr.epsi.mspr.arosaje.service.GuardianshipService;
import fr.epsi.mspr.arosaje.service.PlantService;
import fr.epsi.mspr.arosaje.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class GuardianshipServiceTest {

    @Mock
    private GuardianshipRepository guardianshipRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private GuardianshipMapper guardianshipMapper;

    @Mock
    private PlantService plantService;

    @Mock
    private UserService userService;

    @InjectMocks
    private GuardianshipService guardianshipService;

    private Guardianship guardianship;
    private GuardianshipDTO guardianshipDTO;
    private GuardianshipSaveRequest guardianshipSaveRequest;

    @BeforeEach
    void setUp() {
        guardianship = new Guardianship();
        guardianshipDTO = new GuardianshipDTO();
        guardianshipSaveRequest = new GuardianshipSaveRequest();
        // Configure here any default values for your entities if necessary
    }

    @Test
    void findAll_ShouldReturnListOfGuardianships() {
        given(guardianshipRepository.findAll()).willReturn(Collections.singletonList(guardianship));
        given(guardianshipMapper.guardianshipToGuardianshipDTO(any(Guardianship.class))).willReturn(guardianshipDTO);

        List<GuardianshipDTO> result = guardianshipService.findAll();

        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.get(0)).isEqualTo(guardianshipDTO);
    }

    @Test
    void findById_ExistingId_ShouldReturnGuardianship() {
        given(guardianshipRepository.findById(anyLong())).willReturn(Optional.of(guardianship));
        given(guardianshipMapper.guardianshipToGuardianshipDTO(any(Guardianship.class))).willReturn(guardianshipDTO);

        GuardianshipDTO result = guardianshipService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(guardianshipDTO);
    }

    @Test
    void findById_NonExistingId_ShouldThrowException() {
        given(guardianshipRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> guardianshipService.findById(1L))
                .isInstanceOf(GuardianshipNotFoundException.class);
    }

//    @Test
//    void create_ShouldCreateGuardianship() {
//        given(guardianshipMapper.guardianshipSaveRequestToGuardianship(any(GuardianshipSaveRequest.class))).willReturn(guardianship);
//        given(userService.getUserEntityById(anyLong())).willReturn(new User());
//        given(plantService.getPlantEntityById(anyLong())).willThrow(new PlantNotFoundException(1L));
//        given(guardianshipRepository.save(any(Guardianship.class))).willReturn(guardianship);
//        given(guardianshipMapper.guardianshipToGuardianshipDTO(any(Guardianship.class))).willReturn(guardianshipDTO);
//
//        GuardianshipDTO result = guardianshipService.create(guardianshipSaveRequest);
//
//        assertThat(result).isNotNull();
//        assertThat(result).isEqualTo(guardianshipDTO);
//    }

//    @Test
//    void update_ExistingId_ShouldUpdateGuardianship() {
//        given(guardianshipRepository.findById(anyLong())).willReturn(Optional.of(guardianship));
//        given(statusRepository.findById(anyLong())).willReturn(Optional.of(new Status()));
//        given(guardianshipRepository.save(any(Guardianship.class))).willReturn(guardianship);
//        given(guardianshipMapper.guardianshipToGuardianshipDTO(any(Guardianship.class))).willReturn(guardianshipDTO);
//
//        GuardianshipDTO result = guardianshipService.update(guardianshipSaveRequest);
//
//        assertThat(result).isNotNull();
//        assertThat(result).isEqualTo(guardianshipDTO);
//    }

    @Test
    void delete_ExistingId_ShouldDeleteGuardianship() {
        given(guardianshipRepository.findById(anyLong())).willReturn(Optional.of(guardianship));
        willDoNothing().given(guardianshipRepository).delete(any(Guardianship.class));

        guardianshipService.delete(1L);

        then(guardianshipRepository).should().delete(guardianship);
    }

    @Test
    void isPlantInUse_WithInUse_ShouldReturnTrue() {
        given(guardianshipRepository.existsByPlantId(anyLong())).willReturn(true);

        boolean inUse = guardianshipService.isPlantInUse(1L);

        assertThat(inUse).isTrue();
    }
}
