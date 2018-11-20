package agent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;


// 座標が範囲外かどうかはCoordinateクラスは知らないのでチェックしなくていい．
// それはAgentManagerおよびAgentManagerTestの仕事
@Tag("all")
class CoordinateTest {
    @Nested class 座標52_3が取得できるか {
        Coordinate Coordinate;

        @BeforeEach
        void setup(){
            Coordinate = new Coordinate(52,3);
        }

        @Test
        void get_x() {
            int x_expected = 52;
            int x_actual = Coordinate.get_x();
            assertThat(x_expected, is(x_actual));
        }

        @Test
        void get_y(){
            int y_expected = 3;
            int y_actual   = Coordinate.get_y();
            assertThat(y_expected, is(y_actual));
        }
    }


    @Nested class 座標499_0が取得できるか{
        Coordinate Coordinate;

        @BeforeEach
        void setup(){
            Coordinate = new Coordinate(499,0);
        }

        @Test
        void get_x() {
            int x_expected = 499;
            int x_actual = Coordinate.get_x();
            assertThat(x_expected, is(x_actual));
        }

        @Test
        void get_y(){
            int y_expected = 0;
            int y_actual   = Coordinate.get_y();
            assertThat(y_expected, is(y_actual));
        }
    }
}