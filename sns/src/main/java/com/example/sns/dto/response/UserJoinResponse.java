package com.example.sns.dto.response;

import com.example.sns.domain.User;
import com.example.sns.domain.UserRole;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserJoinResponse {

    private Integer id;
    private String userName;
    private UserRole userRole;

    public static UserJoinResponse fromUser(User user){
        return new UserJoinResponse(
                user.getId(),
                user.getUserName(),
                user.getRole()
        );
    }
}
