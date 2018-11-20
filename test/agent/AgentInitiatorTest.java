package agent;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import root.myRandom;

import static root.EnvironmentalConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Tag("all")
class AgentInitiatorTest {

    private static int _agentNum_ = AGENT_NUM;
    private static int _leader_num = INITIAL_LEADER_NUM;
    private AgentInitiator ai;

    @BeforeEach
    void setUp() {
        myRandom.setRand(1000000009);
        ai = AgentInitiator.getInstance();
    }

    @Nested
    class エージェント生成のテスト {
        List<Agent> agents;
        Field f, g;

        @BeforeEach
        void setUp() throws NoSuchFieldException {

            agents = ai.lGenerateAgents(_agentNum_, _leader_num);

            f = agents.get(0).getClass().getDeclaredField("resources");
            f.setAccessible(true);

            g = agents.get(0).getClass().getDeclaredField("roleName");
            g.setAccessible(true);
        }

        @Test
        void エージェントが500体生成されている() {
            assertThat(agents.size(), is(_agentNum_));
        }


        @Test
        void リーダーが期待した数存在する() throws IllegalAccessException {
            List<Agent> agents = ai.lGenerateAgents(_agentNum_, _leader_num);
            int expected = _leader_num;
            int actual = 0;

            RoleName temp;
            for (Agent ag : agents) {
                temp = (RoleName) g.get(ag);
                if (temp.equals(RoleName.leader)) {
                    actual++;
                }
            }
            assertThat(expected, is(actual));
        }
    }

    @Nested
    class エージェントの配置のテスト {
        List<Agent> agents;
        HashMap<Agent, Coordinate> coordinates;
        int[][] delays;

        @BeforeEach
        void setUp() {
            agents = ai.lGenerateAgents(_agentNum_, _leader_num);
            coordinates = ai.hSetPositions(agents);
            delays = ai.aSetDelays(coordinates, agents);
        }

        @Test
        void エージェントが全員違うところに配置されている() {
            int agentSize = agents.size();
            boolean isDuplicated = false;
            Agent currentAgent, targetAgent;

            for (int iCurrentAgent = 0; iCurrentAgent < agentSize; iCurrentAgent++) {
                currentAgent = agents.get(iCurrentAgent);
                for (int iTargetAgent = iCurrentAgent + 1; iTargetAgent < agentSize; iTargetAgent++) {
                    targetAgent = agents.get(iTargetAgent);

                    if (coordinates.get(currentAgent).get_x() == coordinates.get(targetAgent).get_x()
                            && coordinates.get(currentAgent).get_y() == coordinates.get(targetAgent).get_y()) {
                        isDuplicated = true;
                    }
                }
            }
            assertThat(isDuplicated, is(false));
        }

        @Test
        void 全エージェント間の距離が1からMAX_DELAYに収まっている() {
            for (int i = 0; i < _agentNum_; i++) {
                for (int j = 0; j < _agentNum_; j++) {
                    int actual = delays[i][j];
                    if (i != j) {
                        assertThat(actual, is(greaterThanOrEqualTo(1)));
                        assertThat(actual, is(lessThanOrEqualTo(MAX_DELAY)));
                    }
                }
            }
        }

        @Test
        void 自分との通信遅延は0() {
            for (int i = 0; i < _agentNum_; i++) {
                for (int j = 0; j < _agentNum_; j++) {
                    int expected = delays[i][j];

                    if (i == j) {
                        assertThat(expected, is(0));
                    }
                }
            }
        }
    }


    @AfterEach
    void tearDown() {
        ai.vReset();
        Agent.vResetAgent();
    }
}