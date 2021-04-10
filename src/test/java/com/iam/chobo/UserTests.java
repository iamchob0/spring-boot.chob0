package com.iam.chobo;

import com.iam.chobo.controller.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController helloWorldController;

    //모의 http request, response 만들어 테스트 진행하도록 해줌
    private MockMvc mockMvc;
    
    // @Test
    // public void createUserTest() throws Exception {

    //     //MockMvc setting
    //     mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();

    //     //실제 동작하도록 요청
    //     mockMvc.perform(
    //             //get 메소드로 요청
    //             MockMvcRequestBuilders.get("/user")
    //     ).andDo(MockMvcResultHandlers.print())
    //      .andExpect(MockMvcResultMatchers.status().isOk())
    //      .andExpect(MockMvcResultMatchers.content().string("HelloWorld"));
    // }
}