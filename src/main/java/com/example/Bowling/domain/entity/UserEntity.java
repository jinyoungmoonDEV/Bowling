package com.example.Bowling.domain.entity;

import com.example.Bowling.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String password;

    @Column(unique = true)
    private String name;

    private String rank;

    private String avg;

    private String max;

    private Integer game_num;

    private Integer win_rate;

    private String role;

    public UserDTO toDTO(){

        UserDTO userDTO = UserDTO.builder()
                .id(id)
                .password(password)
                .name(name)
                .rank(rank)
                .avg(avg)
                .max(max)
                .game_num(game_num)
                .win_rate(win_rate)
                .role(role)
                .build();

        return userDTO;
    }

}
