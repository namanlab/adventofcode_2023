import java.util.*;

class Agent {
    public int x;
    public int y;
    public int dir;
    private int hashCode;
    public int steps = 0;

    // Default constructor
    public Agent() {
        this.x = 0;
        this.y = 0;
        this.dir = 1;
        this.steps = 0;
        this.hashCode = Objects.hash(this.x, this.y, this.dir, this.steps);
        
    }

    // Parameterized constructor
    public Agent(int x, int y, int dir, int steps) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.steps = steps;
        this.hashCode = Objects.hash(this.x, this.y, this.dir, this.steps);
    }

    public int getArrVal(ArrayList<ArrayList<Integer>> arr){
        return arr.get(this.x).get(this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Agent otherPair = (Agent) obj;
        return (otherPair.x == x) && (otherPair.y == y) && (otherPair.dir == dir)  && (otherPair.steps == steps);
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

class HeatDir {
    public int heatval;
    public int x;
    public int y;
    public int dir;
    public int steps;

    // Default constructor
    public HeatDir() {
        this.heatval = 0;
        this.x = 0;
        this.y = 0;
        this.dir = 0;
        this.steps = 0;
    }

    // Parameterized constructor
    public HeatDir(int v1, int v2, int v3, int v4, int v5) {
        this.heatval = v1;
        this.x = v2;
        this.y = v3;
        this.dir = v4;
        this.steps = v5;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HeatDir otherPair = (HeatDir) obj;
        return (otherPair.heatval == heatval) && (otherPair.x == x) && (otherPair.y == y) && (otherPair.dir == dir) && (otherPair.steps == steps);
    }

    @Override
    public String toString() {
        return "(" + this.heatval + ", " + this.x  + ", " + this.y + ", " + this.dir + ")";
    }
}

class HeatDirComp implements Comparator<HeatDir>{
    @Override
    public int compare(HeatDir h1, HeatDir h2){
        int val = Integer.compare(h1.heatval, h2.heatval);
        if (val == 0){val = Integer.compare(h1.x, h2.x);}
        if (val == 0){val = Integer.compare(h1.y, h2.y);}
        if (val == 0){val = Integer.compare(h1.dir, h2.dir);}
        if (val == 0){val = Integer.compare(h1.steps, h2.steps);}
        return val;
    }
}

class Cruc{
    public int forwardCount;
    public TreeMap<HeatDir, Agent> ts;
    public HashMap<Agent, HeatDir> hm;
    public ArrayList<ArrayList<Integer>> arr;
    public HashSet<Agent> visited;

    // Parameterized constructor
    public Cruc(int x, int y, ArrayList<ArrayList<Integer>> arr) {
        this.forwardCount = 0;
        HeatDirComp hc = new HeatDirComp();
        this.ts = new TreeMap<>(hc);
        this.hm = new HashMap<>();
        this.arr = arr;
        HeatDir hdNew;
        Agent npNew;
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(0).size(); j++){
                for (int k = 0; k < 4; k++){
                    for (int l = 0; l < 11; l++){
                        hdNew = new HeatDir(Integer.MAX_VALUE, i, j, k, l);
                        npNew = new Agent(i, j, k, l);
                        this.ts.put(hdNew, npNew);
                        this.hm.put(npNew, hdNew);
                    }
                    
                }
            }
        }
        hdNew = new HeatDir(0, x, y, 1, 0);
        npNew = new Agent(x, y, 1, 0);
        this.ts.put(hdNew, npNew);
        this.hm.put(npNew, hdNew);
        hdNew = new HeatDir(0, x, y, 2, 0);
        npNew = new Agent(x, y, 2, 0);
        this.ts.put(hdNew, npNew);
        this.hm.put(npNew, hdNew);
        this.visited = new HashSet<>();
    }

    public boolean[] getPossibleMoves(Agent curAg, int maxr, int maxc){
        boolean[] res = new boolean[4];
        if (curAg.steps < 4){
            Arrays.fill(res, false);
            res[curAg.dir] = true;
        } else {
            Arrays.fill(res, true);
            // No backward
            if (curAg.dir == 0){res[2] = false;}
            if (curAg.dir == 1){res[3] = false;}
            if (curAg.dir == 2){res[0] = false;}
            if (curAg.dir == 3){res[1] = false;}
            // No > 10 steps same dir
            if (curAg.steps == 10){res[curAg.dir] = false;}
        }
        // Boundaries
        if (curAg.x == 0){res[0] = false;}
        if (curAg.x == maxr){res[2] = false;}
        if (curAg.y == 0){res[3] = false;}
        if (curAg.y == maxc){res[1] = false;}
        return res;
    }

    public void move(){
        int maxr = arr.size();
        int maxc = arr.get(0).size();
        // [2, 0, 0, 2], [0, 0, 2]
        Map.Entry<HeatDir, Agent> curEn = this.ts.pollFirstEntry();
        Agent curAg = curEn.getValue();
        System.out.println(curEn.getKey());
        boolean[] possible = getPossibleMoves(curAg, maxr - 1, maxc - 1);
        visited.add(curAg);
        int curSteps;
        if (possible[0]){
            if (curAg.dir == 0){curSteps = curAg.steps + 1;}
            else {curSteps = 1;}
            Agent temp = new Agent(curAg.x - 1, curAg.y, 0, curSteps);
            if (!visited.contains(temp)){
                int oldDist = this.hm.get(temp).heatval;
                int newDist = this.hm.get(curAg).heatval + this.arr.get(curAg.x - 1).get(curAg.y);
                if (oldDist > newDist){
                    HeatDir curHD = hm.get(temp);
                    ts.remove(curHD);
                    HeatDir newHD = new HeatDir(newDist, curAg.x - 1, curAg.y, 0, curSteps);
                    hm.put(temp, newHD);
                    ts.put(newHD, temp);
                }
            }  
        }
        if (possible[1]){
            if (curAg.dir == 1){curSteps = curAg.steps + 1;}
            else {curSteps = 1;}
            Agent temp = new Agent(curAg.x, curAg.y + 1, 1, curSteps);
            if (!visited.contains(temp)){
                int oldDist = this.hm.get(temp).heatval;
                int newDist = this.hm.get(curAg).heatval + this.arr.get(curAg.x).get(curAg.y + 1);
                if (oldDist > newDist){
                    HeatDir curHD = hm.get(temp);
                    ts.remove(curHD);
                    HeatDir newHD = new HeatDir(newDist, curAg.x, curAg.y + 1, 1, curSteps);
                    hm.put(temp, newHD);
                    ts.put(newHD, temp);
                }
            }  
        }
        if (possible[2]){
            if (curAg.dir == 2){curSteps = curAg.steps + 1;}
            else {curSteps = 1;}
            Agent temp = new Agent(curAg.x + 1, curAg.y, 2, curSteps);
            if (!visited.contains(temp)){
                int oldDist = this.hm.get(temp).heatval;
                int newDist = this.hm.get(curAg).heatval + this.arr.get(curAg.x + 1).get(curAg.y);
                if (oldDist > newDist){
                    HeatDir curHD = hm.get(temp);
                    ts.remove(curHD);
                    HeatDir newHD = new HeatDir(newDist, curAg.x + 1, curAg.y, 2, curSteps);
                    hm.put(temp, newHD);
                    ts.put(newHD, temp);
                }
            }  
        }
        if (possible[3]){
            if (curAg.dir == 3){curSteps = curAg.steps + 1;}
            else {curSteps = 1;}
            Agent temp = new Agent(curAg.x, curAg.y - 1, 3, curSteps);
            if (!visited.contains(temp)){
                int oldDist = this.hm.get(temp).heatval;
                int newDist = this.hm.get(curAg).heatval + this.arr.get(curAg.x).get(curAg.y - 1);
                if (oldDist > newDist){
                    HeatDir curHD = hm.get(temp);
                    ts.remove(curHD);
                    HeatDir newHD = new HeatDir(newDist, curAg.x, curAg.y - 1, 3, curSteps);
                    hm.put(temp, newHD);
                    ts.put(newHD, temp);
                }
            }  
        }
    }

}


public class d17p2 {

    public static long runSSSP(ArrayList<ArrayList<Integer>> arr){
        Cruc mod = new Cruc(0, 0, arr);
        int maxr = arr.size();
        int maxc = arr.get(0).size();
        while (!mod.ts.isEmpty()){
            mod.move();
        }
        int heatVal = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 11; j++)
                heatVal = Math.min(heatVal, mod.hm.get(new Agent(maxr - 1, maxc - 1, i, j)).heatval);
        }
        ;
        //System.out.println(mod.par);
        //int stx = maxr - 1;
        //int sty = maxc - 1;
        //String curSt = "";
        //while (stx != 0 || sty != 0){
        //    curSt = mod.par.get(stx).get(sty);
        //    System.out.println(curSt);
        //    String[] curArr = curSt.split("-");
        //    stx = Integer.valueOf(curArr[0]);
        //    sty = Integer.valueOf(curArr[1]);
        //}
        //System.out.println(curSt);
        return heatVal;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> pats = new ArrayList<ArrayList<Integer>>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            char[] curSt = curLine.toCharArray();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (char c: curSt){temp.add(Character.getNumericValue(c));}
            pats.add(temp);
        }
        long res = runSSSP(pats);
        System.out.println(res);
        sc.close();
    }

}