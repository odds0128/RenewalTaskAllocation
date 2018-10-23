package agent;

import java.util.ArrayList;
import java.util.List;

import myUtil.myRandom;
import static constants.EnvironmentalConstants.*;

public class Agent implements Cloneable{
    private static int _agent_id_;

    int id;
    Position position;
    Roles role;
    int[] resources;


    public Agent() {
        this.id = _agent_id_++;
        this.resources = setResources();
    }


    public static void reset(){
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
        sb.append("id:" + this.id + ", x: " + this.position.x + ", y: " + this.position.y );
        return sb.toString();
    }
}
