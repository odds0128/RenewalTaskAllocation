package agent;

// 座標が範囲外かどうかはCoordinationクラスは知らないのでチェックしなくていい．
// それはAgentManagerおよびAgentManagerTestの仕事
public class Coordination {
    private int x;
    private int y;

    Coordination(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getXcoordinate(){
        return this.x;
    }

    public int getYcoordinate(){
        return this.y;
    }
}
