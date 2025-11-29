package com.example.sns.service;

import com.example.sns.domain.User;
import com.example.sns.domain.UserEntity;
import com.example.sns.exception.ErrorCode;
import com.example.sns.exception.SnsApplicationException;
import com.example.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    // TODO : implement
    public User join(String userName, String password) {
        // 회원가입하려는 userName으로 회원가입된 user 있는지
        userEntityRepository.findByUsername(userName).ifPresent(it -> {
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userName));
        });

        // 회원가입 진행 = user 등록
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, password));
        return User.fromEntity(userEntity);
    }

    // TODO : implement
    public String login(String userName, String password) {
        // 회원 가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUsername(userName)
                .orElseThrow(()-> new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, ""));

        // 비밀번호 체크
        if(!userEntity.getPassword().equals(password)){
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, "");
        }

        // 토큰 생성
        return "";
    }
}
