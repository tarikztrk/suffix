package prolab3;
import java.util.*;
public class SuffixTree {
    public SuffixTree (String metin) {
        kok = new SuffixTreeNode();
        this.metin = metin;

        SuffixTreeBuilder treeBuilder = new SuffixTreeBuilder();
        treeBuilder.build();
    }

   public boolean search (String AramaMetni) {
        SuffixTreeNode aramaNode = kok;
        int kenarSayisi = 1;
        int length = AramaMetni.length();
        int aramaIndex = 1;
        SuffixTreeEdge kenar = null;
        boolean match = true;

        while (aramaIndex <= length) {
           if (aramaNode == null) {
                match = false;
                break;
            }

            if (kenar == null) {
                kenar = aramaNode.getChild(AramaMetni.charAt(aramaIndex-1));
                if (kenar == null) {
                    match = false;
                    break;
                }
                kenarSayisi = kenar.getbaslangicİndexi();
            }
            else {
                if (AramaMetni.charAt(aramaIndex - 1) != metin.charAt(kenarSayisi - 1)) {
                    match = false;
                    break;
                }
            }
           if (kenarSayisi == kenar.getbitisIndexi()) {
                aramaNode = kenar.getChild();
                kenar = null;
            }

           aramaIndex++;
            kenarSayisi++;
        }

        return match;

    }

     private SuffixTreeNode kok;
    private String         metin;

    private class SuffixTreeBuilder {

        public void build () {
            SKTuple sk = new SKTuple(kok, 1);
            int length = metin.length();

            for (int i = 1; i <= length; i++) {
                update(sk, i);
                canonize(sk, i);
            }

            ListIterator<SuffixTreeEdge> 	leafIterator = leafEdges.listIterator(0);
            while (leafIterator.hasNext()) {
                SuffixTreeEdge leafEdge = leafIterator.next();
                leafEdge.setbitisIndexi(length);
            }
        }

        public SuffixTreeBuilder () {
            leafEdges = new LinkedList<SuffixTreeEdge>();
        }

        private LinkedList <SuffixTreeEdge> leafEdges;

        private class SKTuple {
            int kIndex;
            SuffixTreeNode sState;

            SKTuple (SuffixTreeNode sState, int kIndex) {
                this.kIndex = kIndex;
                this.sState = sState;
            }
        }

        private class TestAndSplitReturn {
            boolean         endPointReached;
            SuffixTreeNode  rState;

            TestAndSplitReturn (boolean         endPointReached,
                                SuffixTreeNode  rState) {
                this.endPointReached = endPointReached;
                this.rState          = rState;
            }
        }



        private void update (SKTuple sk, int i) {
            SuffixTreeNode oldr = kok;
            TestAndSplitReturn testAndSplitReturn;

            testAndSplitReturn = testAndSplit(sk, i-1, metin.charAt(i-1));

            while (!testAndSplitReturn.endPointReached) {
                // create new transition from the state r
                SuffixTreeEdge newEdge = new SuffixTreeEdge(i);
                leafEdges.add(newEdge);
                testAndSplitReturn.rState.cocukEkle(metin.charAt(i-1), newEdge);

                // oldr is not kok add a suffixlink to the state r
                if (oldr != kok) {
                    oldr.setSuffixLink (testAndSplitReturn.rState);
                }

                oldr = testAndSplitReturn.rState;

               if (sk.sState == kok) {
                    if (sk.kIndex <= i-1) {
                        sk.kIndex++;
                    }
                   else {
                        sk.kIndex++;
                        break;
                    }
                }
                else {
                    sk.sState = sk.sState.getSuffixLink();
                    canonize(sk, i-1);
                }
                testAndSplitReturn = testAndSplit(sk, i-1, metin.charAt(i-1));
            }

            if (oldr != kok) {
                oldr.setSuffixLink (sk.sState);
            }

            return;
        }

 private TestAndSplitReturn testAndSplit (SKTuple sk, int pIndex, char tVal) {
            SuffixTreeEdge tEdge = sk.sState.getChild(metin.charAt(sk.kIndex-1));

            if (sk.kIndex <= pIndex) {
                if (tVal == metin.charAt(tEdge.getbaslangicİndexi() + pIndex - sk.kIndex)) {

                    return new TestAndSplitReturn(true, sk.sState);
                }
                else {
                    SuffixTreeNode rState = new SuffixTreeNode();


                    sk.sState.cocukEkle(metin.charAt(sk.kIndex-1), new SuffixTreeEdge(tEdge.getbaslangicİndexi(), tEdge.getbaslangicİndexi() + pIndex - sk.kIndex, rState));


                    tEdge.setbaslangicİndexi(tEdge.getbaslangicİndexi() + pIndex - sk.kIndex + 1);
                    rState.cocukEkle(metin.charAt(tEdge.getbaslangicİndexi() - 1), tEdge);

                    return new TestAndSplitReturn(false, rState);
                }
            }
            else {
                return new TestAndSplitReturn((tEdge != null) ? true : false, sk.sState);
            }

        }


        private void canonize (SKTuple sk, int pIndex) {
            if (pIndex < sk.kIndex) {
                return;
            }
            else {
                SuffixTreeEdge kenar = sk.sState.getChild(metin.charAt(sk.kIndex-1));
                while (!kenar.isLeaf() &&
                        ((kenar.getbitisIndexi() - kenar.getbaslangicİndexi()) <= (pIndex - sk.kIndex))) {
                    sk.kIndex = sk.kIndex + (kenar.getbitisIndexi() - kenar.getbaslangicİndexi()) + 1;
                    sk.sState = kenar.getChild();

                    if (pIndex < sk.kIndex) {
                        break;
                    }
                    kenar = sk.sState.getChild(metin.charAt(sk.kIndex-1));
                }
                return;
            }
        }

    }

}
