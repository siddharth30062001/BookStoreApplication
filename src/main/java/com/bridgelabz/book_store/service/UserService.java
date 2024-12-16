package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.LoginRequestDTO;
import com.bridgelabz.book_store.dto.UserRequestDTO;
import com.bridgelabz.book_store.dto.UserResponseDTO;
import com.bridgelabz.book_store.exception.UserNotFoundException;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String registerUser(UserRequestDTO register) {
        User user=new User(register);
        userRepository.save(user);
        return "User registered successfully";
    }

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

    public User userLogin(LoginRequestDTO loginRequest) {

        Optional<User> userLogin=userRepository.findByEmailIdAndPassword(loginRequest.getEmailId(), loginRequest.getPassword());
        if(userLogin.isPresent()){
            return userLogin.get();
        }
        else {
            return null;
        }

    }

    public String updateUser(Long userId,UserRequestDTO userRequestDTO) {

        User user= userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setDob(userRequestDTO.getDob());
        user.setPassword(userRequestDTO.getPassword());
        user.setUpdatedDate(LocalDate.now());
        userRepository.save(user);
        return "User updated successfully";
    }

    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }
}
