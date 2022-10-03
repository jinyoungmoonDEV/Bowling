package com.example.Bowling.service.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Bowling.domain.dto.UserDTO;
import com.example.Bowling.domain.entity.UserEntity;
import com.example.Bowling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenServiceImpl implements TokenService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Override
    public void loginMethod(UserDTO user, HttpServletResponse response) {

        String id = user.getId();

        UserEntity info = userRepository.findById(id).orElseThrow();

        if(info == null){
            throw new UsernameNotFoundException("User not found in the database");
        }

        else {
            String password = info.getPassword();
            boolean verify = passwordEncoder.matches(user.getPassword(), password);

            if(verify){
                createToken(info.toDTO(), response);
            }

            else {
                throw new RuntimeException("WrongPassword");
            }
        }
    }

    @Override
    public UserDTO decodeJWT(HttpServletRequest request) {

        String access_token = request.getHeader(AUTHORIZATION);

        if(access_token != null && access_token.startsWith("Bearer ")) {
            try {
                String token = access_token.substring("Bearer ".length());

                Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();

                DecodedJWT decodedJWT = verifier.verify(token);

                String id = decodedJWT.getId();
                String name = decodedJWT.getIssuer();
                String role = decodedJWT.getClaim("role").toString().replace("\"", "");

                UserDTO userDTO = UserDTO.builder()
                        .id(id)
                        .name(name)
                        .role(role)
                        .build();

                return userDTO;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new RuntimeException("No Token");
        }
    }

    @Override
    public void createToken(UserDTO user, HttpServletResponse response) {

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes()); //token 생성 알고리즘

        String access_token = JWT.create() //access token 생성
                .withSubject(user.getId())//이름을 유일한 유저 정보로 하여 토큰의 중복 방지
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *1000)) //기간 설정
                .withIssuer(user.getName())
                .withClaim("role", user.getRole())
                .sign(algorithm);

        String refresh_token = JWT.create() //refresh token 생성
                .withSubject(user.getId())//이름을 유일한 유저 정보로 하여 토큰의 중복 방지
                .withExpiresAt(new Date(System.currentTimeMillis() + 300 * 60 *1000))
                .withIssuer(user.getName())
                .withClaim("role", user.getRole())
                .sign(algorithm);

        response.setHeader("access_token", access_token);
        response.setHeader("refresh_token", refresh_token);
    }
}
