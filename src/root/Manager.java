package root;

import agent.Agent;
import agent.AgentManager;
import myUtil.myRandom;

import java.io.*;
import java.util.List;

import static constants.EnvironmentalConstants.AGENT_NUM;

public class Manager {
    private static List<Agent> agents;

    public static void main (String args[]){
        try{
            String currentDirectory = System.getProperty("user.dir");

            BufferedReader br = readTextFile(currentDirectory + "/src/root/RandomSeed.txt");
            String line;
            while( (line=br.readLine()) != null ){
                // シードの設定
                myRandom.setRand(Long.parseLong(line));
                //TODO: 環境の初期化 = エージェントとタスクキューの生成
                agents = AgentManager.initiateAgents(AGENT_NUM);

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
}
