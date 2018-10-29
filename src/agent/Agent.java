package agent;

import static root.EnvironmentalConstants.*;
import static root.myRandom.getRandomInt;
import static agent.elements.LeaderElements.*;
import static agent.elements.MemberElements.*;


import agent.elements.*;
import communication.Message;

import java.util.List;

public class Agent implements Cloneable{
    private static int _agent_id_ = 0;

    int id;
    RoleName roleName;
    LeaderElements le;
    MemberElements me;
    int[] resources;
    List<Message> mailbox;


    public Agent() {
        this.id = _agent_id_++;
        this.resources = setResources();
        this.roleName  = RoleName.member;
    }

    public static void reset(){
        _agent_id_ = 0;
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
        sb.append("ag" + id);
        for(int i = 0; i < RESOURCE_TYPES; i++){
            sb.append(", " + "res" + i + ": " + resources[i]);
        }

        return sb.toString();
    }
}
