package Utility;

import lombok.Getter;

@Getter
public class MomentumXY {
    double x, y;

    public MomentumXY() {}

    public MomentumXY(MomentumDir mDir){
        MomentumXY self = mDir.ToMomentumXY();
        this.x = self.getX();
        this.y = self.getY();
    }

    public MomentumXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Converts X and Y vector to a direction based vector
    public MomentumDir ToMomentumDir(){
        return new MomentumDir(Math.atan2(y, x), Math.sqrt(x*x+y*y));
    }

    public void SumMomentum(MomentumXY mXY){ this.x += mXY.getX(); this.y += mXY.getY();}

    @Override
    public String toString() {
        return "MomentumXY{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
