package com.example.sns.controller;

import com.example.sns.domain.User;
import com.example.sns.dto.UserJoinRequest;
import com.example.sns.exception.SnsApplicationException;
import com.example.sns.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Test
    public void 회원가입() throws Exception{
        String userName = "userName";
        String password = "password";

        when(userService.join()).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        // TODO : add request body
                        .content(objectMapper.writeValueAsString(new UserJoinRequest(userName, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입시_이미_회원가입된_userName으로_회원가입을_하는경우_에러반환() throws Exception{
        String userName = "userName";
        String password = "password";

        when(userService.join()).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        // TODO : add request body
                        .content(objectMapper.writeValueAsString(new UserJoinRequest(userName, password)))
                ).andDo(print())
                .andExpect(status().isConflict());
    }
}
