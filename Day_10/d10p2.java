import java.util.*;



public class d10p2 {

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

    public static void fillOutside(ArrayList<ArrayList<Integer>> arr, int sti, int stj){
        int nrow = arr.size();
        int ncol = arr.get(0).size();
        arr.get(sti).set(stj, 9);
        int curval;
        if (sti - 1 >= 0){
            curval = arr.get(sti - 1).get(stj);
            if (curval != 5 && curval != 9){fillOutside(arr, sti - 1, stj);}
            if (stj - 1 >= 0){
                curval = arr.get(sti - 1).get(stj - 1);
                if (curval != 5 && curval != 9){fillOutside(arr, sti - 1, stj - 1);}
            }
            if (stj + 1 < ncol){
                curval = arr.get(sti - 1).get(stj + 1);
                if (curval != 5 && curval != 9){fillOutside(arr, sti - 1, stj + 1);}
            }
        }
        if (sti + 1 < nrow){
            curval = arr.get(sti + 1).get(stj);
            if (curval != 5 && curval != 9){fillOutside(arr, sti + 1, stj);}
            if (stj - 1 >= 0){
                curval = arr.get(sti + 1).get(stj - 1);
                if (curval != 5 && curval != 9){fillOutside(arr, sti + 1, stj - 1);}
            }
            if (stj + 1 < ncol){
                curval = arr.get(sti + 1).get(stj + 1);
                if (curval != 5 && curval != 9){fillOutside(arr, sti + 1, stj + 1);}
            }
        }
        if (stj - 1 >= 0){
            curval = arr.get(sti).get(stj - 1);
            if (curval != 5 && curval != 9){fillOutside(arr, sti, stj - 1);}
        }
        if (stj + 1 < ncol){
            curval = arr.get(sti).get(stj + 1);
            if (curval != 5 && curval != 9){fillOutside(arr, sti, stj + 1);}
        }
    }

    public static int getArea(ArrayList<ArrayList<Integer>> arr){
        int nrow = arr.size();
        int ncol = arr.get(0).size();
        int res = 0;
        // Just fill all area outside graph with a value say -20;
        fillOutside(arr, 0, 0);
        arr.stream()
            .map(String::valueOf) 
            .forEach(System.out::println); 
        for (int i = 0; i < nrow; i++){
            for (int j = 0; j < ncol; j++){
                if ((arr.get(i).get(j) != 5) && (arr.get(i).get(j) != 9)){
                    res++;
                }
            }
        }
        return res;
    }
    
    // i = 0, look at row, i = 1, look at col
    public static void figureS(ArrayList<ArrayList<Integer>> arr, int[] st, int ival, int jval){
        System.out.println(ival);
        System.out.println(jval);
        if (ival == 0 && jval == 0){arr.get(st[0]).set(st[1], 3);}
        else if (ival == 0 && jval == 1){arr.get(st[0]).set(st[1], 4);}
        else if (ival == 0 && jval == 3){arr.get(st[0]).set(st[1], 6);}
        else if (ival == 1 && jval == 1){arr.get(st[0]).set(st[1], 1);}
        else if (ival == 1 && jval == 2){arr.get(st[0]).set(st[1], 6);}
        else if (ival == 1 && jval == 0){arr.get(st[0]).set(st[1], 8);}
        else if (ival == 2 && jval == 1){arr.get(st[0]).set(st[1], 2);}
        else if (ival == 2 && jval == 2){arr.get(st[0]).set(st[1], 3);}
        else if (ival == 2 && jval == 3){arr.get(st[0]).set(st[1], 8);}
        else if (ival == 3 && jval == 0){arr.get(st[0]).set(st[1], 2);}
        else if (ival == 3 && jval == 2){arr.get(st[0]).set(st[1], 4);}
        else if (ival == 3 && jval == 3){arr.get(st[0]).set(st[1], 1);}
        System.out.println(arr.get(st[0]).get(st[1]));
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
        ArrayList<ArrayList<Integer>> finArrCopy = new ArrayList<ArrayList<Integer>> ();
        int counter = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            ArrayList<Integer> integerList = parseLine(line, hm);
            ArrayList<Integer> integerListCopy = parseLine(line, hm);
            finArr.add(integerList);
            finArrCopy.add(integerListCopy);
            for (int i = 0; i < integerList.size(); i++){
                if (integerList.get(i) == -1){
                    st[0] = counter;
                    st[1] = i;
                }
            }
            counter++;
        }
        counter = 1;
        finArrCopy.get(st[0]).set(st[1], 5);
        int curPos = getNext(-1, getadjPos(finArr, st), -2);
        int ival, jval;
        ival = curPos;
        int[] nextPos = getPosArr(st, curPos);
        int presentVal = finArr.get(nextPos[0]).get(nextPos[1]);
        while (presentVal != -1){
            finArrCopy.get(nextPos[0]).set(nextPos[1], 5);
            curPos = getNext(presentVal, getadjPos(finArr, nextPos), curPos);
            nextPos = getPosArr(nextPos, curPos);
            presentVal = finArr.get(nextPos[0]).get(nextPos[1]);
            counter++;
        }
        jval = curPos;
        int res = 0;
        // Figure Out s:
        figureS(finArr, st, ival, jval);
        finArrCopy.stream()
            .map(String::valueOf) 
            .forEach(System.out::println); 
        int nrow = finArr.size();
        int ncol = finArr.get(0).size();
        for (int i = 0; i < nrow; i++){
            boolean parity = false;
            for (int j = 0; j < ncol; j++){
                int curval = finArr.get(i).get(j);
                if (finArrCopy.get(i).get(j) != 5){
                    if (parity){
                        String curstr = String.format("index is %s, %s", i, j);
                        System.out.println(curstr);
                        res++;
                    }
                }
                if ((finArrCopy.get(i).get(j) == 5) && (curval == 3 || curval == 4 || curval == 6)){
                    parity = !parity;
                }
            }
            System.out.println(res);
        }
        // Augment finArrCopy:
        //for (int i = 0; i < finArrCopy.size(); i++){
        //    finArrCopy.get(i).addFirst(0);
        //    finArrCopy.get(i).addLast(0);
        //}
        //ArrayList<Integer> temp = new ArrayList<Integer>(Collections.nCopies(finArrCopy.get(0).size(), 0));
        //finArrCopy.addFirst(temp);
        //finArrCopy.addLast(temp);
        //int res = getArea(finArrCopy);
        System.out.println(res);
        sc.close();
    }

}