package bong.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import bong.utils.BongException;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testConstructor_emptyTaskList() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void testConstructor_existingTaskList() throws BongException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Read book", false));
        tasks.add(new Todo("Write report", false));

        TaskList taskListWithTasks = new TaskList(tasks);
        assertEquals(2, taskListWithTasks.size());
        assertEquals("Read book", taskListWithTasks.getTask(0).getDescription());
        assertEquals("Write report", taskListWithTasks.getTask(1).getDescription());
    }

    @Test
    public void testAddTask() {
        Todo todo = new Todo("Read book", false);
        taskList.addTask(todo);
        assertEquals(1, taskList.size());
        assertDoesNotThrow(() -> assertEquals("Read book", taskList.getTask(0).getDescription()));
    }

    @Test
    public void testGetTask_validIndex() throws BongException {
        Todo todo = new Todo("Read book", false);
        taskList.addTask(todo);
        Task task = taskList.getTask(0);
        assertEquals("Read book", task.getDescription());
    }

    @Test
    public void testGetTask_invalidIndex_throwsException() {
        BongException exception = assertThrows(BongException.class, () -> {
            taskList.getTask(1);
        });
        assertEquals("Invalid task number!", exception.getMessage());
    }

    @Test
    public void testRemoveTask_validIndex() throws BongException {
        Todo todo1 = new Todo("Read book", false);
        Todo todo2 = new Todo("Write report", false);
        taskList.addTask(todo1);
        taskList.addTask(todo2);

        Task removedTask = taskList.removeTask(0);
        assertEquals("Read book", removedTask.getDescription());
        assertEquals(1, taskList.size());
        assertEquals("Write report", taskList.getTask(0).getDescription());
    }

    @Test
    public void testRemoveTask_invalidIndex_throwsException() {
        BongException exception = assertThrows(BongException.class, () -> {
            taskList.removeTask(0);
        });
        assertEquals("Invalid task number!", exception.getMessage());
    }

    @Test
    public void testGetList() {
        Todo todo1 = new Todo("Read book", false);
        Todo todo2 = new Todo("Write report", false);
        taskList.addTask(todo1);
        taskList.addTask(todo2);

        ArrayList<Task> tasks = taskList.getList();
        assertEquals(2, tasks.size());
        assertEquals("Read book", tasks.get(0).getDescription());
        assertEquals("Write report", tasks.get(1).getDescription());
    }

    @Test
    public void testSize() {
        assertEquals(0, taskList.size());
        taskList.addTask(new Todo("Read book", false));
        assertEquals(1, taskList.size());
        taskList.addTask(new Todo("Write report", false));
        assertEquals(2, taskList.size());
    }
}