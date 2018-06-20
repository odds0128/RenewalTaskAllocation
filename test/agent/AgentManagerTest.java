package agent;

import myUtil.myRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static constants.EnvironmentalConstants.AGENT_NUM;
import static constants.EnvironmentalConstants.MAX_DELAY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class AgentManagerTest {

    private static int _agentNum_ = AGENT_NUM;
    private static List<Agent> _agents_;

    @BeforeEach
    public void setUp() {
        myRandom.setRand(1000000009);
        _agents_ = AgentManager.initiateAgents(_agentNum_);
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
                int expected = AgentManager._delays_[i][j];

                if (i != j) {
                    assertThat(expected, is(greaterThanOrEqualTo(1)));
                    assertThat(expected, is(lessThanOrEqualTo(MAX_DELAY)));
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

}