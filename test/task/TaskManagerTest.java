package task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static root.EnvironmentalConstants.MAX_TASKQUEUE_SIZE;

class TaskManagerTest {
    private TaskManager sut = TaskManager.getInstance();
    private int taskqueue_size      = MAX_TASKQUEUE_SIZE;
    private Field tq;

    @Nested
    class taskqueue初期化のテスト{
        @BeforeEach
        void setUp() throws NoSuchFieldException {
            tq = sut.getClass().getDeclaredField("taskQueue");
            tq.setAccessible(true);
        }

        @Test
        void vInitiateTaskQueueでtaskQueueにタスクが0個生成される() throws IllegalAccessException {
            int test_task_num = 0;
            int expected      = test_task_num;
            int actual;

            sut.vInitiateTaskQueue(test_task_num);
            actual = ((List)tq.get(sut)).size();

            assertThat(actual, is(expected));
        }

        @Test
        void vInitiateTaskQueueでtaskqueueにタスクが100個生成される() throws IllegalAccessException {
            int test_task_num = 100;
            int expected      = test_task_num;
            int actual;

            actual = ((List)tq.get(sut)).size();

            assertThat(actual, is(expected));
        }

        @Test
        void vInitiateTaskQueueでtaskqueueにタスクが上限を超えて生成されない() throws IllegalAccessException {
            int test_task_num = 1000;
            int expected      = taskqueue_size;
            int actual;

            sut.vInitiateTaskQueue(test_task_num);
            actual = ((List)tq.get(sut)).size();

            assertThat(actual, is(expected));
        }

        @AfterEach
        void tearDown() throws IllegalAccessException {
            ((List)tq.get(sut)).clear();
        }
    }

    @Nested
    class tPopTaskのテスト{
        @BeforeEach
        void setUp() throws NoSuchFieldException {
            tq = sut.getClass().getDeclaredField("taskQueue");
            tq.setAccessible(true);
        }

        @Test
        void taskqueueが空の時nullを返す(){
            int test_task_num = 0;

            Task expected = null;
            Task actual;

            sut.vInitiateTaskQueue(test_task_num);
            actual = sut.tPopTask();

            assertThat(actual, is(expected));
        }

        @Test
        void taskqueueから先頭のタスクを取り出せる() throws IllegalAccessException {
            int test_task_num = 100;

            sut.vInitiateTaskQueue(test_task_num);

            Task expected = (Task) ((List)tq.get(sut)).get(0);
            Task actual   = sut.tPopTask();

            assertThat(actual, is(expected));

            int expectedQueueSize = test_task_num -1;
            int actualQueueSize   = ((List)tq.get(sut)).size();

            assertThat(actualQueueSize, is(expectedQueueSize));
        }

        @AfterEach
        void tearDown() throws IllegalAccessException {
            ((List)tq.get(sut)).clear();
        }
    }
}