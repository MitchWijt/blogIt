package com.example.blogit.blog;

import com.example.blogit.user.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //Ik ben hier niet geintereseerd in de werking van de blogService.
    //Normaal gesproken hoeven service lagen niet gemocked te worden (mits ze extern requests uitvoeren)
    //Mock zo weinig mogelijk, maar krijg vooral vertrouwen in je code.
    // (e.g. als je ook maar iets kleins veranderd moet dan zichtbaar zijn in falende tests)
    @MockBean
    private BlogService blogService;

    //Dit is de standaard serialization engine van Spring, ik zag je eerder gson gebruiken.
    //Jackson is meer de standaard, ik autowire hem hier omdat spring deze voor ons regelt.
    @Autowired
    ObjectMapper om;

    //Elke test moet de @Test hebben
    @Test
    public void shouldGetABlog() throws Exception {
        //given
        var author = new Users("test@tester.com", "mitchelwijt", "test123", "profilePic");
        var blog = new Blog(author,1L, "Drunk", "Drunk on a Journey", "someBannerImg", 1L, LocalDateTime.now());
        var blogListDTO = new BlogListDto(Collections.singletonList(blog), false, 1, 0);

        when(blogService.getBlogs(1)).thenReturn(blogListDTO);

        //when
        var result = this.mockMvc
                .perform(get("/api/blog/get-blogs").param("page", "1"))
                .andDo(print());

        //then
        var expectedBlog = om.writeValueAsString(blogListDTO);
        result
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedBlog))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}