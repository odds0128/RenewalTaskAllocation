package task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static root.EnvironmentalConstants.INITIAL_TASK_NUM;
import static root.EnvironmentalConstants.MAX_TASKQUEUE_SIZE;

class TaskManagerTest {
    TaskManager sut = TaskManager.getInstance();
    int initial_task_num_   = INITIAL_TASK_NUM;
    int taskqueue_size      = MAX_TASKQUEUE_SIZE;
    Field tq;

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

            sut.vInitiateTaskQueue(test_task_num);
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
}