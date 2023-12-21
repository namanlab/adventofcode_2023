import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

class NumberPair {
    public int x;
    public int y;
    public int level;
    private int hashCode;

    // Default constructor
    public NumberPair() {
        this.x = 0;
        this.y = 0;
        this.level = 0;
        this.hashCode = Objects.hash(this.x, this.y, this.level);
    }

    // Parameterized constructor
    public NumberPair(int number1, int number2, int level) {
        this.x = number1;
        this.y = number2;
        this.level = level;
        this.hashCode = Objects.hash(this.x, this.y, this.level);
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
        return (otherPair.x == x) && (otherPair.y == y) && (otherPair.level == level);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.level + ")";
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}



public class d21p2 {

    public static boolean[] getPossibleMoves(NumberPair curAg, ArrayList<ArrayList<Integer>> arr){
        int maxr = arr.size(); int maxc = arr.get(0).size();
        boolean[] res = new boolean[4];
        Arrays.fill(res, true);
        int x, y;
        if (curAg.x - 1 < 0){x = (maxr + ((curAg.x - 1) % maxr)) % maxr;} else {x = (curAg.x - 1) % maxr;}
        if (curAg.y < 0){y = (maxc + ((curAg.y) % maxc)) % maxc;} else {y = curAg.y % maxc;}
        if (arr.get(x).get(y) == 1){res[0] = false;}
        if (curAg.x< 0){x = (maxr + ((curAg.x) % maxr)) % maxr;} else {x = (curAg.x) % maxr;}
        if (curAg.y + 1 < 0){y = (maxc + ((curAg.y + 1) % maxc)) % maxc;} else {y = (curAg.y + 1) % maxc;}
        if (arr.get(x).get(y) == 1){res[1] = false;}
        if (curAg.x + 1 < 0){x = (maxr + ((curAg.x + 1) % maxr)) % maxr;} else {x = (curAg.x + 1) % maxr;}
        if (curAg.y < 0){y = (maxc + ((curAg.y) % maxc)) % maxc;} else {y = (curAg.y) % maxc;}
        if (arr.get(x).get(y) == 1){res[2] = false;}
        if (curAg.x < 0){x = (maxr + ((curAg.x) % maxr)) % maxr;} else {x = (curAg.x) % maxr;}
        if (curAg.y - 1 < 0){y = (maxc + ((curAg.y - 1) % maxc)) % maxc;} else {y = (curAg.y - 1) % maxc;}
        if (arr.get(x).get(y) == 1){res[3] = false;}
        return res;
    }

    public static void runSSSP(ArrayList<ArrayList<Integer>> arr, ArrayDeque<NumberPair> pos, HashSet<NumberPair> hs, int steps){
        
        while (!pos.isEmpty()){
            NumberPair curEl = pos.removeFirst();
            if (curEl.level == steps){pos.addFirst(curEl); break;}
            boolean[] neighb = getPossibleMoves(curEl, arr);
            if (neighb[0]){NumberPair temp = new NumberPair(curEl.x - 1, curEl.y, curEl.level + 1); 
                if (!hs.contains(temp)){pos.addLast(temp); hs.add(temp);}}
            if (neighb[1]){NumberPair temp = new NumberPair(curEl.x, curEl.y + 1, curEl.level + 1);
                if (!hs.contains(temp)){pos.addLast(temp); hs.add(temp);}}
            if (neighb[2]){NumberPair temp = new NumberPair(curEl.x + 1, curEl.y, curEl.level + 1); 
                if (!hs.contains(temp)){pos.addLast(temp); hs.add(temp);}}
            if (neighb[3]){NumberPair temp = new NumberPair(curEl.x, curEl.y - 1, curEl.level + 1); 
                if (!hs.contains(temp)){pos.addLast(temp); hs.add(temp);}}
            }
    }

    public static long getPol(long x1, long x2, long x3, long y1, long y2, long y3, long x){
        long res = (((x-x2) * (x-x3)) / ((x1-x2) * (x1-x3))) * y1 + 
        (((x-x1) * (x-x3)) / ((x2-x1) * (x2-x3))) * y2 + 
        (((x-x1) * (x-x2)) / ((x3-x1) * (x3-x2))) * y3;
        return res;

    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> pats = new ArrayList<ArrayList<Integer>>();
        int rowCounter = 0; int colCounter = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('#', 1);
        hm.put('.', 0);
        hm.put('S', -1);
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            char[] curSt = curLine.toCharArray();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (char c: curSt){temp.add(hm.get(c));}
            pats.add(temp);
            int counter = 0;
            for (int i: temp){if (i == -1){rowCounter = pats.size() - 1; colCounter = counter;} counter++;}
        }
        // System.out.println(pats);
        int steps = 26501365;
        int griDSize = pats.size();
        ArrayDeque<NumberPair> pos = new ArrayDeque<>();
        NumberPair temp = new NumberPair(rowCounter, colCounter, 0); 
        HashSet<NumberPair> hs = new HashSet<>();
        ArrayList<Long> fin = new ArrayList<>();
        for (int j = 0; j < 4; j++){
            pos.clear(); hs.clear(); 
            pos.addLast(temp); hs.add(temp);
            runSSSP(pats, pos, hs, griDSize*j + griDSize/2);
            long res = pos.size();
            System.out.println(res);
            fin.addLast(res);
        }
        long checkDiff = getPol(65, 131 + 65, 2*131 + 65, fin.get(0), fin.get(1), fin.get(2), 3*131 + 65) - fin.get(3);
        System.out.println(checkDiff);
        long finRes = getPol(65, 131 + 65, 2*131 + 65, fin.get(0), fin.get(1), fin.get(2), steps) ;
        System.out.println(finRes);
        
        sc.close();
    }

}