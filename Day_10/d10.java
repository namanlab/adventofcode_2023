import java.util.*;



public class d10 {

    private static ArrayList<Integer> parseLine(String line, HashMap<Character, Integer> hm) {
        ArrayList<Integer> res = new ArrayList<>();
        char[] curArr = line.toCharArray();
        for (char curChar : curArr) {
            res.add(hm.get(curChar));
        }
        return res;
    }

    // Cur pos is integer value (hm converetd) of cur pos, prev pos is the coord (1, 2, 3, 4) of previous position
    public static int getNext(int curPos, int[] adjPos, int prevPos){
        // If adjPos has value -2, that means, end
        // For start, no nee d[prevPos, can apss any dummy value]
        if (curPos == -1){
            if (adjPos[0] == 3 || adjPos[0] == 8 | adjPos[0] == 2){
                return 0;
            }
            if (adjPos[1] == 1 || adjPos[1] == 4 | adjPos[1] == 2){
                return 1;
            }
            if (adjPos[2] == 3 || adjPos[2] == 4 | adjPos[3] == 6){
                return 2;
            }
            if (adjPos[3] == 1 || adjPos[3] == 6 | adjPos[3] == 8){
                return 3;
            }
        }
        // Make previous position inaccessible
        if ((curPos == 6 || curPos == 3 || curPos == 4) && prevPos != 2){
            return 0;
        }
        if ((curPos == 6 || curPos == 1 || curPos == 8) && prevPos != 3){
            return 1;
        }
        if ((curPos == 8 || curPos == 3 || curPos == 2) && prevPos != 0){
            return 2;
        }
        if ((curPos == 4 || curPos == 1 || curPos == 2) && prevPos != 1){
            return 3;
        }

        return 0;
    }

    public static int[] getadjPos(ArrayList<ArrayList<Integer>> arr, int[] curCoord) {
        int nrow = arr.size();
        int ncol = arr.get(0).size();
        int[] res = new int[4];
        res[0] = (curCoord[0] - 1 >= 0) ? arr.get(curCoord[0] - 1).get(curCoord[1]) : -2;
        res[1] = (curCoord[1] + 1 < ncol) ? arr.get(curCoord[0]).get(curCoord[1] + 1) : -2;
        res[2] = (curCoord[0] + 1 < nrow) ? arr.get(curCoord[0] + 1).get(curCoord[1]) : -2;
        res[3] = (curCoord[1] - 1 >= 0) ? arr.get(curCoord[0]).get(curCoord[1] - 1) : -2;
        return res;
    }

    public static int[] getPosArr(int[] curCoord, int newPos) {
        int[] res = curCoord;
        if (newPos == 0){res[0]--;}
        else if (newPos == 1){res[1]++;}
        else if (newPos == 2){res[0]++;}
        else {res[1]--;}
        return res;
    }
    

    public static void main(String[] args) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        hm.put('S', -1);
        hm.put('.', 0);
        hm.put('-', 1);
        hm.put('7', 2);
        hm.put('|', 3);
        hm.put('J', 4);
        hm.put('L', 6);
        hm.put('F', 8);
        int[] st = new int[2];
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> finArr = new ArrayList<ArrayList<Integer>> ();
        int counter = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            ArrayList<Integer> integerList = parseLine(line, hm);
            finArr.add(integerList);
            for (int i = 0; i < integerList.size(); i++){
                if (integerList.get(i) == -1){
                    st[0] = counter;
                    st[1] = i;
                }
            }
            counter++;
        }
        counter = 1;
        int curPos = getNext(-1, getadjPos(finArr, st), -2);
        int[] nextPos = getPosArr(st, curPos);
        int presentVal = finArr.get(nextPos[0]).get(nextPos[1]);
        while (presentVal != -1){
            curPos = getNext(presentVal, getadjPos(finArr, nextPos), curPos);
            nextPos = getPosArr(nextPos, curPos);
            presentVal = finArr.get(nextPos[0]).get(nextPos[1]);
            counter++;
        }
        System.out.println(counter);
        System.out.println(counter / 2);
        sc.close();
    }

}