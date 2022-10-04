package com.example.Bowling.service.user;

import com.example.Bowling.domain.dto.UserDTO;
import com.example.Bowling.domain.entity.UserEntity;
import com.example.Bowling.repository.UserRepository;
import com.example.Bowling.service.token.TokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final TokenServiceImpl tokenService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDTO user) {

        if (userRepository.findById(user.getId()) == null && userRepository.findByName(user.getName()) == null) {

            user.setPassword(passwordEncoder.encode(user.getPassword())); //password encode

            userRepository.save(user.toEntity());

        }
        else if (userRepository.findById(user.getId()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ID Already Exist");
        }
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name Already Exist");
        }

    }

    @Override
    public void signinMethod(UserDTO user, HttpServletResponse response) {

        String id = user.getId();

        UserEntity info = userRepository.findById(id).orElseThrow();

        if(info == null){

            throw new UsernameNotFoundException("User not found in the database");

        }

        else {

            String password = info.getPassword();
            boolean verify = passwordEncoder.matches(user.getPassword(), password);

            if(verify){
                tokenService.createToken(info.toDTO(), response);
            }

            else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong Password");
            }
        }

    }

    @Override
    public UserDTO getUser(HttpServletRequest request) {

        UserEntity result = userRepository.findById(getId(request)).orElseThrow();

        return result.toDTO();
    }

    @Override
    public UserDTO updateInfo(UserDTO user) {

        UserEntity info = userRepository.findById(user.getId()).orElseThrow();
        UserDTO result = info.toDTO();

        result.setName(user.getName());
        result.setRank(user.getRank());
        result.setAvg(user.getAvg());
        result.setMax(user.getMax());
        result.setGame_num(user.getGame_num());
        result.setWin_rate(user.getWin_rate());

        return result;
    }

    @Override
    public void deleteUser(HttpServletRequest request) {
        userRepository.deleteById(getId(request));
    }

    @Override
    public String getId(HttpServletRequest request) {
        return tokenService.decodeJWT(request).getId();
    }
}
