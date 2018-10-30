package root;

import task.TaskManager;
import agent.AgentManager;

import java.io.*;

import static root.EnvironmentalConstants.*;

public class GeneralManager {

    public static void main (String args[]){
        try{
            String currentDirectory = System.getProperty("user.dir");

            BufferedReader br = readTextFile(currentDirectory + "/src/root/RandomSeed.txt");
            String line;
            while( (line=br.readLine()) != null ){
                // シードの設定
                myRandom.setRand(Long.parseLong(line));
                //TODO: 環境の初期化 = エージェントとタスクキューの生成
                AgentManager.getInstance().vInitiateAgents(AGENT_NUM, INITIAL_LEADER_NUM);
                TaskManager.getInstance().vInitiateTaskQueue(INITIAL_TASK_NUM);
            }


        }catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * readTextFileメソッド
     * ファイルのフルパスをStringで渡すとその内容をBufferdReader型で返す．
     * ファイルが見つからなければFileNotFoundExceptionを投げる
     * @param path
     * @return
     */
    private static BufferedReader readTextFile( String path ) throws FileNotFoundException{
        File        f = new File(path);
        FileReader fr = new FileReader(f);
        return new BufferedReader(fr);
    }

    public static void reset(){
        AgentManager.getInstance().vReset();
        TaskManager.vReset();
    }
}
