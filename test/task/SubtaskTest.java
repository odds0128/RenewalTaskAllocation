package task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static root.EnvironmentalConstants.*;

class SubtaskTest {
    private List<Subtask> sut;

    SubtaskTest() {
        this.sut = new ArrayList<>();
    }

    @Nested
    class インスタンス化のテスト {

        @BeforeEach
        void setup() {
            for( int i = 0; i < 1000; i++ ) sut.add(new Subtask());
//            for( int i = 0; i < 1000; i++ ) System.out.println(sut.get(i));

        }

        @Test
        void aSetResourcesで1種類が0ではない配列が生成できる() {
            int actual   = 0;
            int expected = 1;

            for(Subtask st: sut) {
                int[] resources = st.getRequiredResources();
                for (int i = 0; i < RESOURCE_TYPES; i++) {
                    if (resources[i] > 0) actual++;
                }
                assertThat(actual, is(expected));
                actual = 0;
            }
        }

        @Test
        void aSetResourcesで0ではないリソースの範囲がMINとMAXの間に収まっている() {
            int actual;

            for (Subtask st : sut) {
                actual = st.getRequiredResources()[st.getRequiredType()];
                assertThat(actual, is(greaterThanOrEqualTo(TASK_MIN_RESOURCE)));
                assertThat(actual, is(lessThanOrEqualTo(TASK_MAX_RESOURCE)));
            }
        }
        @AfterEach
        void teardown(){
            Subtask.vResetSubtask();
        }
    }

    @AfterEach
    void teardown(){
        Subtask.vResetSubtask();
    }

}