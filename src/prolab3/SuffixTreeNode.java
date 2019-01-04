package prolab3;
import java.util.HashMap;

public class SuffixTreeNode {
    SuffixTreeNode() {
        cocukDugum = new HashMap<Character, SuffixTreeEdge>();
        suffixLink = null;
    }

    // add a new edge (the edge has startIndex, endIndex and next node)
    // adding an initialized edge is equivalent to adding the transition g'(s, (k,x)) = r
    void cocukEkle (char childChar, SuffixTreeEdge edge) {
        cocukDugum.put(childChar, edge);
    }

    SuffixTreeEdge getChild (char childChar) {
        return cocukDugum.get(childChar);
    }

    // Set the suffix link ()
    void setSuffixLink (SuffixTreeNode suffixLink) {
        this.suffixLink = suffixLink;
    }

    SuffixTreeNode getSuffixLink () {
        return suffixLink;
    }

    // private
    private HashMap<Character, SuffixTreeEdge> cocukDugum;

    private SuffixTreeNode suffixLink;


}
