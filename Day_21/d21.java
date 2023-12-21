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



public class d21 {

    public static boolean[] getPossibleMoves(NumberPair curAg, ArrayList<ArrayList<Integer>> arr){
        int maxr = arr.size() - 1; int maxc = arr.get(0).size() - 1;
        boolean[] res = new boolean[4];
        Arrays.fill(res, true);
        if (curAg.x == 0){res[0] = false;}
        else {if (arr.get(curAg.x - 1).get(curAg.y) == 1){res[0] = false;}}
        if (curAg.x == maxr){res[2] = false;}
        else {if (arr.get(curAg.x + 1).get(curAg.y) == 1){res[2] = false;}}
        if (curAg.y == 0){res[3] = false;}
        else {if (arr.get(curAg.x).get(curAg.y - 1) == 1){res[3] = false;}}
        if (curAg.y == maxc){res[1] = false;}
        else {if (arr.get(curAg.x).get(curAg.y + 1) == 1){res[1] = false;}}
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
        System.out.println(pats);
        ArrayDeque<NumberPair> pos = new ArrayDeque<>();
        NumberPair temp = new NumberPair(rowCounter, colCounter, 0); 
        pos.addLast(temp);
        HashSet<NumberPair> hs = new HashSet<>();
        hs.add(temp);
        runSSSP(pats, pos, hs, 64);
        System.out.println(pos);
        long res = pos.size();
        System.out.println(res);
        sc.close();
    }

}