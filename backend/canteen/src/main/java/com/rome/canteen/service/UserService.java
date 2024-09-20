package com.rome.canteen.service;

import com.rome.canteen.model.User;
import com.rome.canteen.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import org.springframework.context.annotation.Lazy;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user, ensuring that the email is unique.
    public void registerUser(User user) {
        // Check if the email is already in use
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Encode password and save the new user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), new ArrayList<>()
        );
    }
}
