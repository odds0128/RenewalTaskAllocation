package agent;

// 座標が範囲外かどうかはCoordinateクラスは知らないのでチェックしなくていい．
// それはAgentManagerおよびAgentManagerTestの仕事
public class Coordinate {
    private int x;
    private int y;

    Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int get_x(){
        return this.x;
    }

    public int get_y(){
        return this.y;
    }
}
