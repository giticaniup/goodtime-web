package com.goodtime.user.test;

import com.github.api.entity.UserTask;
import com.github.api.service.UserTaskService;
import com.goodtime.user.controller.TaskController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Controller单元测试
 * 单元测试示例：
 * 1.Runwith、ContextConfiguration
 * 2.Mock bean
 * 3.@Before中initMocks，初始化MockMvc
 * 4.mockMvc.perform发送请求
 * 如果是传json参数，那么contentType是json，参数以json形式传递
 * 如果是个别参数，使用param方法传递
 * 如果是对象，也可以使用param方法，将对象的属性一个个传递
 * Created by zhongcy on 2016/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
public class TaskControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    private TaskController taskController;

    @Autowired
    private UserTaskService userTaskService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void testTaskList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/task/todoList"));
    }

    @Test
    public void testAddTask() throws Exception {
        UserTask userTask = new UserTask();
        userTask.setUserId(1);
        userTask.setTaskScore(5);
        Mockito.when(userTaskService.insertUserTask(userTask)).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/task/addUserTask").param("taskId", "1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(1001))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/task/getTask").param("id", "1").param("beginTime", "2016-01-01 " +
                "10:10"))
                .andDo(MockMvcResultHandlers.print());
    }
}
