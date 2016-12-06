package com.goodtime.user.test;

import com.github.api.entity.SystemMessage;
import com.goodtime.user.controller.SystemMessageController;
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

import java.util.Date;

/**
 * Created by zhongcy on 16-12-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class SystemMessageControllerTest {

    private String url ;

    private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    private SystemMessageController systemMessageController;

    @Before
    public void setUp(){
        url = "/goodtime/sysMessage";

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(systemMessageController).build();
    }

    @Test
    public void testAddSysMessage(){
        SystemMessage systemMessage = new SystemMessage();

        systemMessage.setId(4);
        systemMessage.setTitle("test4");
        systemMessage.setContent("测试4");
        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(new Gson().toJson(systemMessage)))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(0))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
