package com.goodtime.user.test;

import com.github.api.entity.UserTask;
import com.github.api.service.UserTaskService;
import com.goodtime.user.controller.TaskController;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

/**
 * Task单元测试类
 * Created by zhongcy on 2016/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
public class TaskControllerTest {
    private Subject _mockSubject;

    private String taskUrl;

    private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    private TaskController taskController;

    @Autowired
    private UserTaskService userTaskService;

    @Before
    public void setUp() throws Exception {
        _mockSubject = Mockito.mock(Subject.class);
        ThreadState _threadState = new SubjectThreadState(_mockSubject);
        _threadState.bind();
        Mockito.when(_mockSubject.isAuthenticated()).thenReturn(true);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        taskUrl = "/goodtime/task";
    }

    @Test
    public void testGetTask() {
        Integer userId = 1;
        String beginTime = "2016-11-10";
        String endTime = "2016-11-23";

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", userId);

        Mockito.when(userTaskService.findTaskListByUserId(userId, beginTime, endTime)).thenReturn(new
                ArrayList<>());

        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.get(taskUrl).param("userId", "1").param("beginTime", beginTime).param
                            ("endTime", endTime).session(session))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(0))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
