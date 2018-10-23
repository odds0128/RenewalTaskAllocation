package agent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 座標が範囲外かどうかはCoordinationクラスは知らないのでチェックしなくていい．
// それはAgentManagerおよびAgentManagerTestの仕事
class CoordinationTest {
    @Nested class 座標52_3が取得できるか {
        Coordination coordination;

        @BeforeEach
        void setup(){
            coordination = new Coordination(52,3);
        }

        @Test
        void getXcoordinate() {
            int x_expected = 52;
            int x_actual = coordination.getXcoordinate();
            assertThat(x_expected, is(x_actual));
        }
    }

    @Nested class 座標499_0が取得できるか{
        @BeforeEach
        void setup(){
            Coordination coordination = new Coordination();
        }

        @Test
        void getYcoordinate() {
        }
    }
}