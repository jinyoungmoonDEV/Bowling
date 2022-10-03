package com.example.Bowling.domain.dto;

import com.example.Bowling.domain.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;

    private String password;

    private String name;

    private String rank;

    private String avg;

    private String max;

    private Integer game_num;

    private Integer win_rate;

    private String role;

    public UserEntity toEntity() {

        UserEntity userEntity = UserEntity.builder()
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

        return userEntity;
    }

}
