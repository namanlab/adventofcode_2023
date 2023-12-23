import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

class NumberPair {
    public int x;
    public int y;
    public int type = -1; // for . or 0, 1, 2, 3 for slope
    private int hashCode;

    // Default constructor
    public NumberPair() {
        this.x = 0;
        this.y = 0;
        this.type = -1;
        this.hashCode = Objects.hash(this.x, this.y);
    }

    // Parameterized constructor
    public NumberPair(int number1, int number2, int type) {
        this.x = number1;
        this.y = number2;
        this.type = type;
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
        return "(" + this.x + ", " + this.y + ", " + this.type + ")";
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}



public class d23 {

    public static NumberPair getPoss(int x, int y, HashSet<NumberPair> arr, ArrayList<char[]> typeArr, HashSet<NumberPair> vis){
        int newX = x; int newY = y;
        NumberPair temp = new NumberPair(newX, newY, -1);
        if ((arr.contains(temp)) && !vis.contains(temp)){
            char curT = typeArr.get(newX)[newY];
            if (curT == '.'){temp.type = -1;} else if (curT == '^'){temp.type = 0;} else if (curT == '>'){temp.type = 1;} 
            else if (curT == 'v'){temp.type = 2;} else {temp.type = 3;}
            return temp;
        } else {
            return null;
        } 
    }


    public static long runDFS(HashSet<NumberPair> arr, HashSet<NumberPair> vis, NumberPair st, NumberPair en, ArrayList<char[]> typeArr){
        vis.add(st);
        if (st.equals(en)){return 0;}
        else {
            HashMap<Integer, NumberPair> hm = new HashMap<>();
            hm.put(0, getPoss(st.x - 1, st.y, arr, typeArr, vis));
            hm.put(1, getPoss(st.x, st.y + 1, arr, typeArr, vis));
            hm.put(2, getPoss(st.x + 1, st.y, arr, typeArr, vis));
            hm.put(3, getPoss(st.x, st.y - 1, arr, typeArr, vis));
            if ( (hm.get(0) == null) &&  (hm.get(1) == null) &&  (hm.get(2) == null) &&  (hm.get(3) == null)){
                return Integer.MIN_VALUE;
            }
            if (st.type != -1){
                if (hm.get(st.type) != null){
                    HashSet<NumberPair> tempVis = new HashSet<>(); 
                    tempVis.addAll(vis);
                    return 1 + runDFS(arr, tempVis, hm.get(st.type), en, typeArr);
                } else {
                    return Integer.MIN_VALUE;
                }  
            }
            else {
                ArrayList<Long> np = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    if (hm.get(i) != null){np.add(runDFS(arr, vis, hm.get(i), en, typeArr));}
                }
                return 1 + Collections.max(np);
            }
        }
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<NumberPair> pats = new HashSet<>();
        int rowCounter = 0;
        int colCounter = 0;
        NumberPair st = new NumberPair(rowCounter, colCounter, -1);
        int endCC = 0;
        ArrayList<char[]> typeArr = new ArrayList<>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            char[] curSt = curLine.toCharArray();
            typeArr.add(curSt);
            colCounter = 0;
            while (colCounter < curSt.length){
                if (curSt[colCounter] == '.'){pats.add(new NumberPair(rowCounter, colCounter, -1));
                    endCC = colCounter;
                    if (rowCounter == 0){st = new NumberPair(rowCounter, colCounter, -1);}}
                if (curSt[colCounter] == '^'){pats.add(new NumberPair(rowCounter, colCounter, 0));}
                if (curSt[colCounter] == '>'){pats.add(new NumberPair(rowCounter, colCounter, 1));}
                if (curSt[colCounter] == 'v'){pats.add(new NumberPair(rowCounter, colCounter, 2));}
                if (curSt[colCounter] == '<'){pats.add(new NumberPair(rowCounter, colCounter, 3));}
                colCounter++;
            }
            rowCounter++;
        }
        NumberPair en = new NumberPair(rowCounter - 1, endCC, -1);
        HashSet<NumberPair> visited = new HashSet<>();
        long res = runDFS(pats, visited, st, en, typeArr);
        System.out.println(res);
        sc.close();
    }

}