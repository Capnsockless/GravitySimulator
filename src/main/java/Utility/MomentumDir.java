package Utility;

import lombok.Getter;

@Getter
public class MomentumDir {
    double dir, len;

    public MomentumDir() { }

    public MomentumDir(double dir, double len) {
        this.dir = dir;
        this.len = len;
    }

    public MomentumXY ToMomentumXY(){
        return new MomentumXY(Math.cos(dir)*len, Math.sin(dir)*len);
    }

    public void MultiplyDistance(double mult){ this.len *= mult;}

    @Override
    public String toString() {
        return "MomentumDir{" +
                "dir=" + dir +
                ", len=" + len +
                '}';
    }
}
