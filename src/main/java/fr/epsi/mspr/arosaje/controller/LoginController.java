//package fr.epsi.mspr.arosaje.controller;
//
//import fr.epsi.mspr.arosaje.entity.dto.login.LoginRequest;
//import fr.epsi.mspr.arosaje.entity.dto.login.LoginResponse;
//import fr.epsi.mspr.arosaje.entity.dto.user.UserSaveRequest;
//import fr.epsi.mspr.arosaje.security.CustomUserDetails;
//import fr.epsi.mspr.arosaje.security.JwtUtil;
//import fr.epsi.mspr.arosaje.service.MyUserDetailsService;
//import fr.epsi.mspr.arosaje.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/api")
//public class LoginController {
//
////    @Autowired
////    private AuthenticationManager authenticationManager;
////
////    @Autowired
////    private MyUserDetailsService userDetailsService;
////
////    @Autowired
////    private UserService userService;
////
////    @Autowired
////    private JwtUtil jwtTokenUtil;
////
////    @PostMapping("/login")
////    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
////
//////        Authentication authentication = authenticationManager.authenticate(
//////                new UsernamePasswordAuthenticationToken(
//////                        authenticationRequest.getUsername(),
//////                        authenticationRequest.getPassword()
//////                )
//////        );
////
////        // Charger les détails de l'utilisateur après authentification
////        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
////
////        // Générer le JWT à partir des CustomUserDetails
////        final String jwt = jwtTokenUtil.generateToken(userDetails);
////
////        // Récupérer les informations de l'utilisateur
////        String username = userDetails.getUsername();
////        int userId = userDetails.getId();
////        Set<String> roles = userDetails.getAuthorities().stream()
////                .map(GrantedAuthority::getAuthority)
////                .collect(Collectors.toSet());
////
////        // Ajouter le token dans l'en-tête X-Authorization de la réponse
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("X-Authorization", jwt);
////        headers.add("Access-Control-Expose-Headers", "X-Authorization"); // Expose le header X-Authorization
////
////        // Retourner la réponse avec les détails de l'utilisateur et le JWT dans l'en-tête
////        return ResponseEntity.ok().headers(headers).body(new LoginResponse(username, userId, roles.toString(), jwt));
////    }
////
////    @PostMapping("/login/google")
////    public ResponseEntity<?> createUserFromGoogle(@RequestBody UserSaveRequest userSaveRequest) {
////        LoginResponse response = userService.createUserFromGoogle(userSaveRequest);
////
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("X-Authorization", response.getJwt());
////        headers.add("Access-Control-Expose-Headers", "X-Authorization");
////
////        return ResponseEntity.ok().headers(headers).body(response);
////    }
//}
