package root;

import Strategies.Strategy;
import task.TaskManager;
import agent.AgentManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static root.EnvironmentalConstants.*;

public class GeneralManager {

    public static void main (String args[]){
        List<StrategyName> strategy = new ArrayList<>();

        strategy.add(StrategyName.StrategyX);

        try{
            String currentDirectory = System.getProperty("user.dir");

            int i = 0;
            BufferedReader br = readTextFile(currentDirectory + "/src/root/RandomSeed.txt");
            String line;

            // TODO: 一回で複数のStrategyや条件について実験できるようにする
            while( (line=br.readLine()) != null && i++ < EXPERIMENTAL_TIMES){
                // シードの設定
                myRandom.setRand(Long.parseLong(line));
                // 環境の初期化 = Strategyのセット．エージェントとタスクキューの生成
                AgentManager.getInstance().setStrategy(StrategyName.StrategyX);
                AgentManager.getInstance().vInitiateAgents(AGENT_NUM, INITIAL_LEADER_NUM);
                TaskManager.getInstance().vInitiateTaskQueue(INITIAL_TASK_NUM);

                // 実験の施行
                vDoExperiment();
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


    static int actual = 0;
    private static void vDoExperiment(){
        for(int i = 0; i < MAX_TURN; i++){
            AgentManager.getInstance().act();
        }
    }

    public static void reset(){
        AgentManager.getInstance().vReset();
        TaskManager.vReset();
    }
}
