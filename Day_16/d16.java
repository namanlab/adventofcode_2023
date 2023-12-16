import java.util.*;

class Light{
    public int x;
    public int y;
    public int dir; // 0, 1, 2, 3
    public int hashCode;

    // Default constructor
    public Light() {
        this.x = 0;
        this.y = 0;
        this.dir = 1;
        this.hashCode = Objects.hash(this.x, this.y, this.dir);
    }

    // Parameterized constructor
    public Light(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.hashCode = Objects.hash(this.x, this.y, this.dir);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Light otherPair = (Light) obj;
        return (otherPair.x == x) && (otherPair.y == y) && (otherPair.dir == dir);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.dir + ")";
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}


public class d16 {
    public static void moveLight(ArrayList<ArrayList<Character>> arr, ArrayList<ArrayList<Integer>> visited, ArrayList<Light> src, HashSet<Light> hs){        
        while (!src.isEmpty()){
            Light temp = src.removeLast();
            if (hs.contains(temp)){
                continue;
            } else {
                hs.add(temp);
            }
            Light curSrc = new Light(temp.x, temp.y, temp.dir);
            visited.get(curSrc.x).set(curSrc.y, 1);
            if (arr.get(curSrc.x).get(curSrc.y) == '\\'){
                if (curSrc.dir == 0){curSrc.dir = 3;}
                else if (curSrc.dir == 1){curSrc.dir = 2;}
                else if (curSrc.dir == 2){curSrc.dir = 1;}
                else {curSrc.dir = 0;}
            }
            if (arr.get(curSrc.x).get(curSrc.y) == '/'){
                if (curSrc.dir == 0){curSrc.dir = 1;}
                else if (curSrc.dir == 1){curSrc.dir = 0;}
                else if (curSrc.dir == 2){curSrc.dir = 3;}
                else {curSrc.dir = 2;}
            }
            if (arr.get(curSrc.x).get(curSrc.y) == '-'){
                if (curSrc.dir == 0 || curSrc.dir == 2){
                    curSrc.dir = 3;
                    Light newSrc = new Light(curSrc.x, curSrc.y, 1);
                    src.add(newSrc);
                }
            }
            if (arr.get(curSrc.x).get(curSrc.y) == '|'){
                if (curSrc.dir == 1 || curSrc.dir == 3){
                    curSrc.dir = 0;
                    Light newSrc = new Light(curSrc.x, curSrc.y, 2);
                    src.add(newSrc);
                }
            }
            if (curSrc.dir == 0){
                if (curSrc.x == 0){continue;}
                else {
                    curSrc.x = curSrc.x - 1;
                    src.add(curSrc);
                    continue;
                }
            }
            if (curSrc.dir == 2){
                if (curSrc.x == arr.size() - 1){continue;}
                else {
                    curSrc.x = curSrc.x + 1;
                    src.add(curSrc);
                    continue;
                }
            }
            if (curSrc.dir == 1){
                if (curSrc.y == arr.get(0).size() - 1){continue;}
                else {
                    curSrc.y = curSrc.y + 1;
                    src.add(curSrc);
                    continue;
                }
            }
            if (curSrc.dir == 3){
                if (curSrc.y == 0){continue;}
                else {
                    curSrc.y = curSrc.y - 1;
                    src.add(curSrc);
                    continue;
                }
            }

        }
    }

    public static long getCount(ArrayList<ArrayList<Integer>> visited){
        long res = 0;
        for (int i = 0; i < visited.size(); i++){
            for (int j = 0; j < visited.get(0).size(); j++){
                if (visited.get(i).get(j) == 1){
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Character>> pats = new ArrayList<ArrayList<Character>>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            char[] curSt = curLine.toCharArray();
            ArrayList<Character> temp = new ArrayList<Character>();
            for (char c: curSt){temp.add(c);}
            pats.add(temp);
        }
        ArrayList<ArrayList<Integer>> visited = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < pats.size(); i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            visited.add(temp);
        }
        for (int i = 0; i < pats.size(); i++){
            for (int j = 0; j < pats.get(0).size(); j++){
                visited.get(i).add(0);
            }
        }
        ArrayList<Light> sources = new ArrayList<Light>();
        Light st = new Light();
        sources.add(st);
        HashSet<Light> hs = new HashSet<>();
        moveLight(pats, visited, sources, hs);
        System.out.println(getCount(visited));
        sc.close();
    }

}