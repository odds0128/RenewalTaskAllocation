package agent;

import myUtil.myRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static constants.EnvironmentalConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class AgentManagerTest {

    private static int _agentNum_  = AGENT_NUM;
    private static int _leader_num = 0;
    private static List<Agent> _agents_;
    AgentManager am;

    @BeforeEach
    public void setUp() {
        myRandom.setRand(1000000009);
        _agents_ = AgentManager.lInitiateAgents(_agentNum_, _leader_num);
        am = new AgentManager();

    }

    @Test
    public void エージェントが500体生成されている() {
        assertThat(_agents_.size(), is(not(0)));
    }

    @Test
    public void エージェントが全員違うところに配置されている() {
        int agentSize = _agents_.size();
        boolean isDuplicated = false;
        Agent currentAgent, targetAgent;

        for (int iCurrentAgent = 0; iCurrentAgent < agentSize; iCurrentAgent++) {
            currentAgent = _agents_.get(iCurrentAgent);
            for (int iTargetAgent = iCurrentAgent + 1; iTargetAgent < agentSize; iTargetAgent++) {
                targetAgent = _agents_.get(iTargetAgent);

                if (currentAgent.position.x == targetAgent.position.x
                        && currentAgent.position.y == targetAgent.position.y) {
                    isDuplicated = true;
                }
            }
        }
        assertThat(isDuplicated, is(false));
    }

    @Test
    public void 全エージェント間の距離が1からMAX_DELAYに収まっている() {
        for (int i = 0; i < _agentNum_; i++) {
            for (int j = 0; j < _agentNum_; j++) {
                int actual = AgentManager._delays_[i][j];
                if (i != j) {
                    assertThat(actual, is(greaterThanOrEqualTo(1)));
                    assertThat(actual, is(lessThanOrEqualTo(MAX_DELAY)));
                }
            }
        }
    }

    @Test
    public void 自分との通信遅延は0() {
        for (int i = 0; i < _agentNum_; i++) {
            for (int j = 0; j < _agentNum_; j++) {
                int expected = AgentManager._delays_[i][j];

                if (i == j) {
                    assertThat(expected, is(0));
                }
            }
        }
    }

    @Test
    public void 各エージェントのリソースの総量は1十3xAGENT_MIN_RESOURCEから3xAGENT_MAX_RESOURCEの間である (){
        int actual;
        int _min = 1 + 3 * AGENT_MIN_RESOURCE;
        int _max = 3 * AGENT_MAX_RESOURCE;

        for( Agent ag: _agents_ ){
            actual = 0;
            for( int i = 0; i < RESOURCE_TYPES; i++) {
                actual += ag.resources[i];

            }
            assertThat(actual, is(greaterThanOrEqualTo(_min)));
            assertThat(actual, is(lessThanOrEqualTo(_max)));
        }
    }

    /**
     * ディープコピーであるので，中身が同じでアドレスが違うことを期待
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void エージェントのリストのディープコピーができる() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = AgentManager.class.getDeclaredMethod("lCloneAgents", List.class);
        method.setAccessible(true);

        List originals = _agents_;
        List clones    = (List) method.invoke(am, _agents_);

//        for( int i = 0; i < originals.size(); i++ ){
//            System.out.println("Original: " + originals.get(i) + ", " + "Clone: " + clones.get(i));
//            System.out.println("Original: " + Integer.toHexString(originals.get(i).hashCode()) + ", " + "Clone: " + Integer.toHexString(clones.get(i).hashCode()));
//        }

        assertThat(originals, is(not(equalTo(clones))));
    }

    @Test
    public void 役割の洗濯が期待した割合でできる(){
        
    }

    @AfterEach
    public void tearDown(){
        AgentManager.vReset();
        Agent.reset();
    }
}