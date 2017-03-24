package com.goodtime.user.test;

import com.github.api.entity.User;
import com.github.api.service.UserInfoService;
import com.goodtime.user.controller.UserController;
import com.goodtime.user.model.LoginUser;
import com.google.gson.Gson;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 用户信息单元测试
 * Created by zhongcy on 2016/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
public class UserControllerTest {

    private Subject _mockSubject;

    private String loginUrl;

    private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    private UserController userController;

    @Autowired
    private UserInfoService userInfoService;

    @Before
    public void setUp() throws Exception {
        _mockSubject = Mockito.mock(Subject.class);
        ThreadState _threadState = new SubjectThreadState(_mockSubject);
        _threadState.bind();
        Mockito.when(_mockSubject.isAuthenticated()).thenReturn(true);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        loginUrl = "/goodtime/login";
    }

    @Test
    public void testLogin(){
        LoginUser user = new LoginUser();
        user.setUserId("1");
        user.setPassword("11");

        User authentication = new User();

        Mockito.when(userInfoService.loginIn(Integer.valueOf(user.getUserId()),user.getPassword())).thenReturn(authentication);

        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.post(loginUrl).contentType(MediaType.APPLICATION_JSON)
                            .content(new Gson().toJson(user)))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(0))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
