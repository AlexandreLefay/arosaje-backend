//package fr.epsi.mspr.arosaje.service;
//
//import fr.epsi.mspr.arosaje.entity.User;
//import fr.epsi.mspr.arosaje.repository.UserRepository;
//import fr.epsi.mspr.arosaje.security.CustomUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
///**
// * Service class for user authentication that implements the UserDetailsService interface.
// */
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    /**
//     * Loads the user by username. Used by Spring Security.
//     *
//     * @param username The username of the user.
//     * @return UserDetails containing user's information and authorities.
//     * @throws UsernameNotFoundException if the user is not found.
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));
//
//        // Handle cases where password might be null (e.g., Google users)
//        String password = user.getPassword() != null ? user.getPassword() : "";
//
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//                user.getUsername(), password, getAuthorities(user));
//
//        return new CustomUserDetails(userDetails, user.getId(), user.getEmail(), user.isActive());
//    }
//
//
//    /**
//     * Converts the roles associated with a User entity to Spring Security GrantedAuthority objects.
//     *
//     * @param user The user whose roles are to be converted.
//     * @return A collection of GrantedAuthority objects.
//     */
//    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        return user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                .collect(Collectors.toList());
//    }
//}
