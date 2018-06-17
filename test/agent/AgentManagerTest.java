package agent;

import agent.Agent;
import com.sun.tools.corba.se.idl.constExpr.GreaterThan;
import com.sun.tools.corba.se.idl.constExpr.LessThan;
import myUtil.myRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static constants.EnvironmentalConstants.AGENT_NUM;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AgentManagerTest {

    private static int         agentNum = AGENT_NUM;
    private static List<Agent> agents;

    @BeforeEach
    public void setUp(){
        myRandom.setRand(1000000009);
        agents = AgentManager.initiateAgents(agentNum);
    }

    @Test
    public void エージェントが500体生成されている(){
        assertThat(agents.size(), is(not(0)));
    }

    @Test
    public void エージェントが全員違うところに配置されている(){
        int agentSize = agents.size();
        boolean isDuplicated = false;
        Agent currentAgent, targetAgent;

        for( int iCurrentAgent = 0; iCurrentAgent < agentSize; iCurrentAgent++ ){
            currentAgent = agents.get(iCurrentAgent);
            for( int iTargetAgent = iCurrentAgent + 1;  iTargetAgent < agentSize; iTargetAgent++ ){
                targetAgent = agents.get(iTargetAgent);

                if( currentAgent.getX() == targetAgent.getX() && currentAgent.getY() == targetAgent.getY() ){ ;
                    isDuplicated = true;
                }
            }
        }
        assertThat(isDuplicated, is(false));
    }

    @Test
    public void エージェント間の距離が1からMAX_DELAYに収まっている(){
        int expected =
        assertThat(expected, is(GreaterThan(0,));
    }

}