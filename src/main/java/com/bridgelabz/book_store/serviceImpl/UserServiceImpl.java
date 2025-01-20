package com.bridgelabz.book_store.serviceImpl;

import com.bridgelabz.book_store.dto.LoginRequestDTO;
import com.bridgelabz.book_store.dto.UserRequestDTO;
import com.bridgelabz.book_store.dto.UserResponseDTO;
import com.bridgelabz.book_store.exception.UnauthorizedAccessException;
import com.bridgelabz.book_store.exception.UserNotFoundException;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.repository.UserRepository;
import com.bridgelabz.book_store.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO register) {
        Boolean isPresent = userRepository.existsByEmailId(register.getEmailId());
        if(isPresent) {
            throw new UnauthorizedAccessException("Email already exists");
        }
        User user=new User(register);
        return mapToDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user=userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
        return mapToDTO(user);
    }

    private UserResponseDTO mapToDTO(User user) {

        UserResponseDTO userResponseDTO=new UserResponseDTO();
        userResponseDTO.setUserId(user.getUserId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setDob(user.getDob());
        userResponseDTO.setRegisteredDate(user.getRegisteredDate());
        userResponseDTO.setUpdatedDate(user.getUpdatedDate());
        userResponseDTO.setEmailId(user.getEmailId());
        userResponseDTO.setPassword(user.getPassword());
        userResponseDTO.setRole(user.getRole());

        return userResponseDTO;
    }

    @Override
    public User userLogin(LoginRequestDTO loginRequest) {

        Optional<User> userLogin=userRepository.findByEmailIdAndPassword(loginRequest.getEmailId(), loginRequest.getPassword());
        if(userLogin.isPresent()){
            return userLogin.get();
        }
        else {
            return null;
        }

    }

    @Override
    public UserResponseDTO updateUser(Long userId,UserRequestDTO userRequestDTO) {

        User user= userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setDob(userRequestDTO.getDob());
        user.setPassword(userRequestDTO.getPassword());
        user.setUpdatedDate(LocalDate.now());
        userRepository.save(user);
        return mapToDTO(userRepository.save(user));
    }

    @Override
    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }

    @Override
    public String forgotPassword(String email) {
        User user = userRepository.findByEmailId(email).orElseThrow(() -> new IllegalArgumentException("Invalid email"));
//        String subject = "Forgot Password";
//        String body = "Your password is: "+generateRandomPassword();
//        String mail = user.getEmail();
//        System.out.println(mail);
//        emailService.sendEmail(mail,subject,body);
//        return "The password has been sent to your email";
        return generateRandomPassword();
    }

    private String generateRandomPassword() {
        return "User@123";
    }
}
