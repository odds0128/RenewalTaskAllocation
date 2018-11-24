package agent;

import agent.elements.LeaderElements;
import agent.elements.MemberElements;
import communication.Message;

import java.util.List;

import static root.EnvironmentalConstants.*;
import static root.myRandom.getRandomBoolean;
import static root.myRandom.getRandomInt;

public class Agent implements Cloneable{
    private static int _agent_id_ = 0;

    public int id;

    private RoleName roleName;
    private LeaderElements le;
    private MemberElements me;
    private int[] resources;
    private List<Message> mailbox;


    public Agent() {
        this.id = _agent_id_++;
        this.resources = setResources();
        this.roleName  = RoleName.member;
        this.le = new LeaderElements(AGENT_NUM);
        this.me = new MemberElements(AGENT_NUM);
    }

    /**
     * Agentのインスタンス生成時に呼び出される．
     * 全リソースが0にならないようにリソースを設定する
     */
    private int[] setResources(){
        int[] tempResources = new int[RESOURCE_TYPES];
        int resSize = 0;

        int temp ;
        while( true ){
            for( int i = 0; i < RESOURCE_TYPES; i++ ){
                temp = getRandomInt(AGENT_MIN_RESOURCE, AGENT_MAX_RESOURCE);
                resSize          += temp;
                tempResources[i] =  temp;
            }
            if( resSize != 0 ) return tempResources;
            resSize = 0;
        }
    }

    void vSelectRole(){
        if( this.me.getE_member() > this.le.getE_leader() ){
            this.roleName = RoleName.member;
        }else if( this.me.getE_member() < this.le.getE_leader() ){
            this.roleName = RoleName.leader;
        }else{
            this.roleName = getRandomBoolean() ? RoleName.leader : RoleName.member;
        }
    }

    public LeaderElements getLeaderElements(){
        return this.le;
    }

    public MemberElements getMemberElements(){
        return this.me;
    }

    public static void vResetAgent(){
        _agent_id_ = 0;
    }

    @Override
    public Agent clone() { //基本的にはpublic修飾子を付け、自分自身の型を返り値とする
        Agent b = null;

        try {
            b=(Agent)super.clone(); //親クラスのcloneメソッドを呼び出す(親クラスの型で返ってくるので、自分自身の型でのキャストを忘れないようにする)
        }catch (Exception e){
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ag").append(id);
        for(int i = 0; i < RESOURCE_TYPES; i++){
            sb.append(", " + "res").append(i).append(": ").append(resources[i]);
        }

        return sb.toString();
    }

    public RoleName getRoleName() {
        return roleName;
    }
    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}
