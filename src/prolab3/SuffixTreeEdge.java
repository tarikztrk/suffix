package prolab3;

public class SuffixTreeEdge {
    SuffixTreeEdge (int baslangicİndexi) {
        this.baslangicİndexi = baslangicİndexi;
        this.bitisIndexi   = -1;
        this.cocukDugum  = null;
    }


    SuffixTreeEdge (int baslangicİndexi, int bitisIndexi, SuffixTreeNode cocukDugum) {
        this.baslangicİndexi = baslangicİndexi;
        this.bitisIndexi   = bitisIndexi;
        this.cocukDugum  = cocukDugum;
    }

    boolean isLeaf () {
        return (bitisIndexi < 0);
    }

    int getbaslangicİndexi() {
        return baslangicİndexi;
    }

    void setbaslangicİndexi( int baslangicİndexi) {
        this.baslangicİndexi = baslangicİndexi;
    }

    int getbitisIndexi() {
        return bitisIndexi;
    }

    void setbitisIndexi( int bitisIndexi) {
        this.bitisIndexi = bitisIndexi;
    }

    SuffixTreeNode getChild () {
        return cocukDugum;
    }
    // private
    private int baslangicİndexi;
    private int bitisIndexi;
    private SuffixTreeNode cocukDugum;

}
