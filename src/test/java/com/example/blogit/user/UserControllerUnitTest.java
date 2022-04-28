package com.example.blogit.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetUserData() throws Exception {
        Users user = new Users("test@tester.com", "testerman", "test123", "profilePictureLink");
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        when(userService.getUserData("testerman")).thenReturn(userDto);

        var result = this.mockMvc
                .perform(get("/api/user/get-user-data/{slug}", "testerman")
                        .contentType(MediaType.APPLICATION_JSON)
                );

        String expectedUser = objectMapper.writeValueAsString(user);
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedUser))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

}
