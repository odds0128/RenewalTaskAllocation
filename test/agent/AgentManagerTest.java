package agent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static root.EnvironmentalConstants.AGENT_NUM;
import static root.EnvironmentalConstants.INITIAL_LEADER_NUM;

class AgentManagerTest {
    private static int _agentNum_  = AGENT_NUM;
    private static int _leader_num = INITIAL_LEADER_NUM;
    private AgentManager am = AgentManager.getInstance();

    private Field f;

    @BeforeEach
    public void setUp(){
    }

    /**
     * ディープコピーであるので，中身が同じでアドレスが違うことを期待

     */
    @Test
    public void エージェントのリストのディープコピーができる() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        am.vInitiateAgents(_agentNum_, _leader_num);

        Method method = AgentManager.class.getDeclaredMethod("lCloneAgents", List.class);
        method.setAccessible(true);

        f = am.getClass().getDeclaredField("agents_");
        f.setAccessible(true);

        List originals = (List)f.get(am);
        List clones    = (List) method.invoke(am, originals);

//        for( int i = 0; i < originals.size(); i++ ){
//            System.out.println("Original: " + originals.get(i) + ", " + "Clone: " + clones.get(i));
//            System.out.println("Original: " + Integer.toHexString(originals.get(i).hashCode()) + ", " + "Clone: " + Integer.toHexString(clones.get(i).hashCode()));
//        }

        assertThat(originals, is(not(equalTo(clones))));
    }
}