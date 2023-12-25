import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

class NumberPair {
    public int x;
    public int y;
    public int weight = 0;
    private int hashCode;

    // Default constructor
    public NumberPair() {
        this.x = 0;
        this.y = 0;
        this.hashCode = Objects.hash(this.x, this.y);
    }

    // Parameterized constructor
    public NumberPair(int number1, int number2) {
        this.x = number1;
        this.y = number2;
        this.hashCode = Objects.hash(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        NumberPair otherPair = (NumberPair) obj;
        return (otherPair.x == x) && (otherPair.y == y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.weight + ")";
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}


class Graph {
    // eg. {(0, 0) -> [(0, 1, w1), (), ...]}
    public HashMap<NumberPair, HashSet<NumberPair>> arr = new HashMap<>();

    public void addVertex(NumberPair ver){
        if (!this.arr.containsKey(ver)){
            this.arr.put(ver, new HashSet<NumberPair>());
        }
    }
    public void addEdge(NumberPair stVert, NumberPair enVert, int weight){
        System.out.println("Adding edge from " + stVert + " to " + enVert + " of weight " + weight);
        addVertex(stVert);
        addVertex(enVert);
        NumberPair newEn = new NumberPair(enVert.x, enVert.y);
        NumberPair newSt = new NumberPair(stVert.x, stVert.y);
        newEn.weight = weight; newSt.weight = weight;
        this.arr.get(stVert).add(newEn);
        this.arr.get(enVert).add(newSt);
    }

    @Override
    public String toString() {
        return this.arr.toString() + " \nSize: " + this.arr.size() + "\n--" + this.arr.keySet();
    }

}





public class d23p2 {


    public static long runDFS(Graph arr, HashSet<NumberPair> vis, NumberPair st, NumberPair en){
        // ystem.out.println("HMM " + vis);
        vis.add(st);
        if (st.equals(en)){ // System.out.println(st); 
            return st.weight;}
        else {
            ArrayList<NumberPair> curLs = new ArrayList<>();
            for (NumberPair nps : arr.arr.get(st)){
                if (!vis.contains(nps)){curLs.add(nps);}
            }
            if (curLs.size() == 0){
                // System.out.println("hmm" + st); 
                return Integer.MIN_VALUE;
            }
            else {
                ArrayList<Long> np = new ArrayList<>();
                for (int i = 0; i < curLs.size(); i++) {
                    HashSet<NumberPair> tempVis = new HashSet<>(); 
                    tempVis.addAll(vis);
                    np.add(runDFS(arr, tempVis, curLs.get(i), en));
                }
                return st.weight + Collections.max(np);
            }
        }
    }

    public static NumberPair getPoss(int x, int y, HashSet<NumberPair> arr, HashSet<NumberPair> vis){
        int newX = x; int newY = y;
        NumberPair temp = new NumberPair(newX, newY);
        if ((arr.contains(temp)) && !vis.contains(temp)){
            return temp;
        } else {
            return null;
        } 
    }

    public static void compBFS(Graph g, HashSet<NumberPair> arr, HashSet<NumberPair> vis, NumberPair cur, HashSet<NumberPair> jn){
        ArrayDeque<NumberPair> que = new ArrayDeque<>();
        que.offer(cur);
        while (!que.isEmpty()) {
            NumberPair st = que.poll();
            vis.add(st);
            NumberPair temp;
            HashSet<NumberPair> hs = new HashSet<>();
            temp = getPoss(st.x - 1, st.y, arr, vis); if (temp != null){hs.add(temp);
            temp.weight = st.weight + 1;
            if (jn.contains(temp)){
                NumberPair v1 = new NumberPair(cur.x, cur.y);
                NumberPair v2 = new NumberPair(temp.x, temp.y);
                g.addEdge(v1, v2, temp.weight);
            } else {que.offer(temp);}}
            temp = getPoss(st.x + 1, st.y, arr, vis); if (temp != null){hs.add(temp);
            temp.weight = st.weight + 1;
            if (jn.contains(temp)){
                NumberPair v1 = new NumberPair(cur.x, cur.y);
                NumberPair v2 = new NumberPair(temp.x, temp.y);
                g.addEdge(v1, v2, temp.weight);
            } else {que.offer(temp);}}
            temp = getPoss(st.x, st.y - 1, arr, vis); if (temp != null){hs.add(temp);
            temp.weight = st.weight + 1;
            if (jn.contains(temp)){
                NumberPair v1 = new NumberPair(cur.x, cur.y);
                NumberPair v2 = new NumberPair(temp.x, temp.y);
                g.addEdge(v1, v2, temp.weight);
            } else {que.offer(temp);}}
            temp = getPoss(st.x, st.y + 1, arr, vis); if (temp != null){hs.add(temp);
            temp.weight = st.weight + 1;
            if (jn.contains(temp)){
                NumberPair v1 = new NumberPair(cur.x, cur.y);
                NumberPair v2 = new NumberPair(temp.x, temp.y);
                g.addEdge(v1, v2, temp.weight);
            } else {que.offer(temp);}}
        }
    }
    
    public static void getJunctions(HashSet<NumberPair> pats, HashSet<NumberPair> jn, ArrayList<char[]> fullArr){
        for (int i = 0; i < fullArr.size(); i++){
            for (int j = 0; j < fullArr.get(i).length; j++){
                NumberPair st = new NumberPair(i, j);
                if (pats.contains(st)){
                    HashSet<NumberPair> hs = new HashSet<>();
                    HashSet<NumberPair> vis = new HashSet<>();
                    NumberPair temp;
                    temp = getPoss(st.x - 1, st.y, pats, vis); if (temp != null){hs.add(temp);}
                    temp = getPoss(st.x + 1, st.y, pats, vis); if (temp != null){hs.add(temp);}
                    temp = getPoss(st.x, st.y - 1, pats, vis); if (temp != null){hs.add(temp);}
                    temp = getPoss(st.x, st.y + 1, pats, vis); if (temp != null){hs.add(temp);}
                    if (hs.size() > 2){jn.add(st);}
                }
            }
        }
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rowCounter = 0;
        int colCounter = 0;
        NumberPair st = new NumberPair(rowCounter, colCounter);
        Graph arr = new Graph();
        int endCC = 0;
        ArrayList<char[]> fullArr = new ArrayList<>();
        HashSet<NumberPair> pats = new HashSet<>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            char[] curSt = curLine.toCharArray();
            fullArr.add(curSt);
            colCounter = 0;
            while (colCounter < curSt.length){
                if (curSt[colCounter] != '#'){endCC = colCounter;
                    if (rowCounter == 0){st = new NumberPair(rowCounter, colCounter);}
                    pats.add(new NumberPair(rowCounter, colCounter));
                }
                colCounter++;
            }
            rowCounter++;
        }
        NumberPair en = new NumberPair(rowCounter - 1, endCC);
        System.out.println(pats.size());
        HashSet<NumberPair> jn = new HashSet<>();
        jn.add(st); jn.add(en);
        getJunctions(pats, jn, fullArr);
        System.out.println(jn.size());
        for (NumberPair npJn : jn){
            HashSet<NumberPair> alrvisited = new HashSet<>();
            compBFS(arr, pats, alrvisited, npJn, jn);
        }
        System.out.println(arr);
        System.out.println(st + " " + en);
        HashSet<NumberPair> visited = new HashSet<>();
        long res = runDFS(arr, visited, st, en);
        System.out.println(res);
        sc.close();
    }

}