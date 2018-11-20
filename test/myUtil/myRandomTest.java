package myUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import root.myRandom;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Tag("all")
class myRandomTest {
    @BeforeEach
    void setUp(){
        myRandom.setRand(1000000009);
    }

    //FIXME: 冗長というか，少なくとももっといい書き方あるでしょ．
    @Test
    void setRandで正しくシードがセットできる() {
        int actual = new Random(1000000009).nextInt();
        myRandom.setRand(1000000009);
        int expected = myRandom.getRandomInt(0,5);

        assertThat( actual, is(expected) );
    }

    @Test
    void getRandomIntで同じ範囲で乱数を得た時違う値が得られる() {
        int actual1 = myRandom.getRandomInt(0,5);
        int actual2 = myRandom.getRandomInt(0,5);

        assertThat(actual1, is(not(actual2)));
    }

    @Test
    void getRandomIntで0から5の範囲内の乱数が得られる(){
        int maxTestNum = 100;
        double actual ;

        for( int i = 0; i < maxTestNum; i++ ){
            actual = myRandom.getRandomInt(0, 5);
            assertThat(actual, is( closeTo( 2.5, 2.5 )));
        }
    }

    @Test
    void getRandomIntで3から6の範囲内の乱数が得られる(){
        int maxTestNum = 100;
        double actual ;

        for( int i = 0; i < maxTestNum; i++ ){
            actual = myRandom.getRandomInt(3, 6);
            assertThat(actual, is( closeTo( 4.5, 1.5 )));
        }
    }


    @Test
    void getRandomDobleで得た乱数が0から1の範囲に収まっている(){
        int maxTestNum = 100;
        double actual ;

        for( int i = 0; i < maxTestNum; i++ ){
            actual = myRandom.getRandomDouble();
            assert 0 <= actual && actual <= 1 : "Illegal nextDouble";
        }
    }

    @Test
    void getRandomBooleanで得た乱数がtrueかfalseであり連続していない(){
        int maxTestNum = 100;
        boolean actual1 = false, actual2  ;
        int sequentialCount  = 0;

        for( int i = 0; i < maxTestNum; i++ ){
            actual2 = actual1;
            actual1 = myRandom.getRandomBoolean();
            if( actual1 == actual2 ) sequentialCount++;
            assertThat( actual1, is ( instanceOf( boolean.class ) ) );
        }
        assertThat(sequentialCount, is(not(maxTestNum)));
    }

}