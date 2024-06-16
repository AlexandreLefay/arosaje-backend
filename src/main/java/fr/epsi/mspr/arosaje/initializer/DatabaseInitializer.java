//package fr.epsi.mspr.arosaje.initializer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import fr.epsi.mspr.arosaje.entity.User;
//import fr.epsi.mspr.arosaje.repository.UserRepository;
//
//import java.util.List;
//
////Uncomment when you want to hash clear password in db, (caution cause this will hash your password at launch)
//@Component
//public class DatabaseInitializer implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        hashExistingPasswords();
//    }
//
//    private void hashExistingPasswords() {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            String hashedPassword = passwordEncoder.encode(user.getPassword());
//            user.setPassword(hashedPassword);
//            userRepository.save(user);
//        }
//    }
//}
