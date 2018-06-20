package myUtil;

import java.util.Random;



public class myRandom {
    // myRandomを最初に呼び出さないと_randに何もセットされず乱数生成が
    // 失敗してしまうため，デフォルトで適当なものを入れておくことにする．
    private static Random _rand_ = new Random(1000000009);

    public static void setRand(long seed){
        _rand_ = new Random(seed);
    }

    public static int getRandomInt(int min, int max){
        return min + _rand_.nextInt(max - min + 1);
    }

    public static double getRandomDouble(){
        return _rand_.nextDouble();
    }

    public static boolean getRandomBoolean(){
        return _rand_.nextBoolean();
    }
}
