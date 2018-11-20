package agent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static root.EnvironmentalConstants.*;

@Tag("all")
class AgentManagerTest {
    private AgentManager am = AgentManager.getInstance();
    private List agents = new ArrayList<>();



    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field f;

        am.vInitiateAgents(AGENT_NUM, INITIAL_LEADER_NUM);

        f = am.getClass().getDeclaredField("agents_");
        f.setAccessible(true);

        agents = (List) f.get(am);
    }

    /**
     * ディープコピーであるので，中身が同じでアドレスが違うことを期待
     */
    @Test
    void エージェントのリストのディープコピーができる() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method methodClone;

        methodClone = AgentManager.class.getDeclaredMethod("lCloneAgents", List.class);
        methodClone.setAccessible(true);

        List originals = agents;
        List clones = (List) methodClone.invoke(am, originals);

//        for( int i = 0; i < originals.size(); i++ ){
//            System.out.println("Original: " + originals.get(i) + ", " + "Clone: " + clones.get(i));
//            System.out.println("Original: " + Integer.toHexString(originals.get(i).hashCode()) + ", " + "Clone: " + Integer.toHexString(clones.get(i).hashCode()));
//        }
        assertThat(originals, is(not(equalTo(clones))));
    }

    @Test
    void lRearrangeListでLeaderが先に来るように並び替えられる() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodRearrange;
        List<Agent> rearrangedAgents;

        methodRearrange = AgentManager.class.getDeclaredMethod("lRearrangeList", List.class);
        methodRearrange.setAccessible(true);
        rearrangedAgents = (List<Agent>) methodRearrange.invoke(am, agents);

        int actual = 0;
        int expected = am.getLeader_num_();
        int changes = 0;                            // Listを頭から見ていった時，leaderからmemberに切り替わるのは一度だけ
        RoleName former = RoleName.leader;

        for( Agent ag: rearrangedAgents ){
            if( ag. getRoleName() != former ){
                changes++;
            }
            if( ag.getRoleName() == RoleName.leader ){
                actual++;
            }
            former = ag.getRoleName();
        }
        assertThat(actual, is(expected));
        assertThat(changes, is(1));
    }
}