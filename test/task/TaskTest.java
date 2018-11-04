package task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static root.EnvironmentalConstants.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.*;

class TaskTest {
    private List<Task> sut;

    TaskTest() {
        this.sut = new ArrayList<>();
    }

    @Nested
    class インスタンス化のテスト {

        @BeforeEach
        void setup() {
            for( int i = 0; i < 1000; i++ ) sut.add(new Task());
        }

        @Test
        void サブタスクがMIN_SUBTASK_NUMからMAX_SUBTASK_NUMの間の分だけ含まれる()throws Exception{
            boolean areEqualAll  = true;         // 全て等しかったらtrue.falseが期待される
            double  actualMeanSubtasks   = 0;      // (MIN+MAX)/2が期待される
            double  expectedMeanSubtasks = (MIN_SUBTASKS + MAX_SUBTASKS)/2.0;

            Task priorOne = null;
            for(Task t: sut){
                if( !t.equals(priorOne) ) areEqualAll=false;
                priorOne =t;
                actualMeanSubtasks+=t.getSubtasks().size();
            }
            actualMeanSubtasks/=sut.size();
            assertThat(areEqualAll, is(false));
            assertThat(actualMeanSubtasks, is(closeTo(expectedMeanSubtasks, 0.1)));
        }

    }

    @AfterEach
    void teardown(){ Task.vResetTask();
    }

}