package com.tradeshift;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;

public class TaskServiceTest {

    private TaskDAO taskDAO;
    private TaskService taskService;

    @Before
    public void tearUp() {
        this.taskDAO = Mockito.mock(TaskDAO.class);
        taskService = new TaskService(taskDAO);
    }

    @Test
    public void does_actually_say_it() {
        assertEquals("The Task Service!", taskService.sayIt());
    }
}
