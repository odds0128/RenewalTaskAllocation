package agent;

import Strategies.StrategyBase;
import Strategies.StrategyX.StrategyX_Leader;
import Strategies.StrategyX.StrategyX_Member;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
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
    private AgentManager sut = AgentManager.getInstance();
    private List agents = new ArrayList<>();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field f;

        sut.vInitiateAgents(AGENT_NUM, INITIAL_LEADER_NUM);

        f = sut.getClass().getDeclaredField("agents_");
        f.setAccessible(true);

        agents = (List) f.get(sut);
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
        List clones = (List) methodClone.invoke(sut, originals);

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
        rearrangedAgents = (List<Agent>) methodRearrange.invoke(sut, agents);

        int actual = 0;
        int expected = sut.getLeader_num_();
        int changes = 0;                            // Listを頭から見ていった時，leaderからmemberに切り替わるのは一度だけ
        RoleName former = RoleName.leader;

        for (Agent ag : rearrangedAgents) {
            if (ag.getRoleName() != former) {
                changes++;
            }
            if (ag.getRoleName() == RoleName.leader) {
                actual++;
            }
            former = ag.getRoleName();
        }
        assertThat(actual, is(expected));
        assertThat(changes, is(1));
    }

    @Test
    void vSetStrategyでLeaderとMemberのStrategyが設定される() throws NoSuchFieldException, IllegalAccessException {
        String testStrategyName = "StrategyX";

        StrategyBase expected_l = StrategyX_Leader.getInstance();
        StrategyBase expected_m = StrategyX_Member.getInstance();

        sut.vSetStrategy(testStrategyName);

        StrategyBase actual_l, actual_m;

        Field fl = sut.getClass().getDeclaredField("strategyLeader");
        fl.setAccessible(true);
        actual_l = (StrategyBase) fl.get(sut);

        Field fm = sut.getClass().getDeclaredField("strategyMember");
        fm.setAccessible(true);
        actual_m = (StrategyBase) fm.get(sut);

        assertThat(actual_l, is(expected_l));
        assertThat(actual_m, is(expected_m));
    }

}