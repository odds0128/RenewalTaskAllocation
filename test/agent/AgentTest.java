package agent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class AgentTest {
    @BeforeEach
    public void setUp(){
    }

    @Test
    public void エージェントが座標89コンマ100とidを持つ(){
        int expected_x = 89, expected_y = 100;
        Agent agent = new Agent(expected_x,expected_y);

        int actual_x = agent.x, actual_y = agent.y, actual_id = agent.id;
        assertThat(actual_x ,is(expected_x) );
        assertThat(actual_y ,is(expected_y) );
        assertThat(actual_id, is(greaterThanOrEqualTo(0)));
    }


    //TODO: インスタンス化のテスト


}