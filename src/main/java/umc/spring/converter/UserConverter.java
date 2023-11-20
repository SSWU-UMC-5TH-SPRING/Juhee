package umc.spring.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.user.UserRequestDTO;
import umc.spring.web.dto.user.UserResponseDTO;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getUserIdx())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request) {
        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }
        return User.builder()
                .gender(gender)
                .name(request.getName())
                .birth(request.getBirth())
                .address(request.getAddress())
                .profileImg(request.getProfileImg())
                .userPreferList(new ArrayList<>())
                .build();
    }
}
