package problem;
import java.lang.*;
import java.util.*;

class NodeComparator implements Comparator<Node> {
    public int compare(Node v1, Node v2) {
        if (v1.h > v2.h) return 1;
        
        if (v1.h < v2.h) return -1;
        
    	return 0;
    }
} 