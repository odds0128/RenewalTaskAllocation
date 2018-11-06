package agent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static root.EnvironmentalConstants.*;

class AgentTest {
    private int _agentNum_ = AGENT_NUM;
    private List<Agent> agents;

    private Field f;

    @BeforeEach
    void setUp() throws NoSuchFieldException {
        agents = new ArrayList<>();
        for( int i = 0; i < _agentNum_; i++ ){
            agents.add(new Agent());
        }
        f = agents.get(0).getClass().getDeclaredField("resources");
        f.setAccessible(true);
    }

    @Test
    void 各エージェントのリソースの総量は1十3xAGENT_MIN_RESOURCEから3xAGENT_MAX_RESOURCEの間である() throws IllegalAccessException {
        int actual;
        int _min = 1 + (3 * AGENT_MIN_RESOURCE);
        int _max = 3 * AGENT_MAX_RESOURCE;

        int[] temp;
        for (int i = 0; i < _agentNum_; i++) {
            actual = 0;
            temp = (int[])f.get(agents.get(i));
            for (int j = 0; j < RESOURCE_TYPES; j++) {
                actual += temp[j];
            }
            assertThat(actual, is(greaterThanOrEqualTo(_min)));
            assertThat(actual, is(lessThanOrEqualTo(_max)));
//            System.out.println(agents.get(i));
        }
    }

    @Test
    void 全エージェントにおいてe_leaderとe_memberが同じ時vSelectRoleでリーダーとメンバが概ね半々になる(){
        double actual = 0;                       // leaderの数を数える
        double expected = _agentNum_/2.0;
        double CI_range = 1.96*sqrt(expected/2); // 95%信頼区間の幅

        for(Agent ag:agents){
            ag.vSelectRole();
            if( ag.getRoleName().equals(RoleName.leader) ) actual++;
        }
        assertThat(actual, is(closeTo(expected, CI_range)));
    }

}