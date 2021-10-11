package com.delivair.service.impl;

import com.delivair.model.User;
import com.delivair.repository.UserRepository;
import com.delivair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

    @Autowired
    UserRepository userRepository;





    public String forgotPassword(String username) throws IOException {

//		Optional<User> userOptional = Optional
//				.ofNullable(myBatisService.findByEmail(email));
        User user = userRepository.findByUsername(username);
        System.out.println(user.getUsername());
//		System.out.println(userOptional.get().getEmail());
//		System.out.println(userOptional.get().getPassword());
//		System.out.println(userOptional.get().getUsername());
//		System.out.println(userOptional.get().getId());

//		if (!userOptional.isPresent()) {
//			return "Invalid email id.";
//		}
        if (user.getUsername().equals(null)) {
            return "Invalid email id.";
        }

//		User user = userOptional.get();
//        user.setToken(generateToken());
//        user.setTokenCreationDate(LocalDateTime.now().toString());

        userRepository.save(user);

        return user.getUsername();
    }

//    public String resetPassword(String token, String password) throws IOException {
//
//        Optional<User> userOptional = Optional
//                .ofNullable(myBatisService.findByToken(token));
//
//        if (!userOptional.isPresent()) {
//            return "Invalid token.";
//        }
//
//        User user = userOptional.get();
//
//        user.setPassword(password);
//        user.setToken(null);
//        user.setTokenCreationDate(null);
//
//        myBatisService.updatePassword(user);
//
//        return "Your password successfully updated.";
//    }

    /**
     * Generate unique token. You may add multiple parameters to create a strong
     * token.
     *
     * @return unique token
     */
    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    /**
     * Check whether the created token expired or not.
     *
     * @param tokenCreationDate
     * @return true or false
     */
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }
}
