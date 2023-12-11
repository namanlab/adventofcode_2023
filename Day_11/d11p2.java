import java.util.*;

public class d11p2 {

    private static int parseAddLine(String line, ArrayList<ArrayList<Integer>> arr, int galaxyCount, ArrayList<Integer> rowArr) {
        ArrayList<Integer> res = new ArrayList<>();
        char[] curArr = line.toCharArray();
        boolean toDouble = true;
        for (char curChar : curArr) {
            if (curChar == '#'){
                res.add(galaxyCount);
                toDouble = false;
                galaxyCount++;
            } else {
                res.add(0);
            }
        }
        arr.add(res);
        if (toDouble){rowArr.add(1);}
        else {rowArr.add(0);}
        return galaxyCount;
    }

    public static void expandCols(ArrayList<ArrayList<Integer>> arr, ArrayList<Integer> colArr){
        boolean[] res = new boolean[arr.get(0).size()];
        Arrays.fill(res, true);
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(0).size(); j++){
                if (arr.get(i).get(j) != 0){
                    res[j] = false;
                }
            }
        }
        for (int i = 0; i < res.length; i++){
            if (res[i]){
                colArr.add(1);
            } else {
                colArr.add(0);
            }
            
        }
    }

    public static long dist(int[] curSt, int[] curEn, ArrayList<Integer> rowArr, ArrayList<Integer> colArr){
        int rs = Math.min(curSt[0], curEn[0]);
        int re = Math.max(curSt[0], curEn[0]);
        int cs = Math.min(curSt[1], curEn[1]);
        int ce = Math.max(curSt[1], curEn[1]);
        long res = 0;
        for (int i = rs; i < re; i++){
            if (rowArr.get(i) == 0){
                res += 1;
            } else {
                res += 1000000;
            }
        }
        for (int i = cs; i < ce; i++){
            if (colArr.get(i) == 0){
                res += 1;
            } else {
                res += 1000000;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int galaxyCount = 1;
        ArrayList<ArrayList<Integer>> finArr = new ArrayList<ArrayList<Integer>>();
        ArrayList<int[]> galaxyCoords = new ArrayList<int[]>();
        ArrayList<Integer> rowArr = new ArrayList<Integer>();
        ArrayList<Integer> colArr = new ArrayList<Integer>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            galaxyCount = parseAddLine(line, finArr, galaxyCount, rowArr);
        }
        expandCols(finArr, colArr);
        for (int i = 0; i < finArr.size(); i++){
            for (int j = 0; j < finArr.get(0).size(); j++){
                if (finArr.get(i).get(j) != 0){
                    int[] tempCoord = new int[2];
                    tempCoord[0] = i;
                    tempCoord[1] = j;
                    galaxyCoords.add(tempCoord);
                }
            }
        }
        long res = 0;
        for (int i = 0; i < galaxyCoords.size(); i++){
            for (int j = 0; j < i; j++){
                int[] curSt = galaxyCoords.get(i);
                int[] curEn = galaxyCoords.get(j);
                res += dist(curSt, curEn, rowArr, colArr);
            }
        }
        System.out.println(res);
        sc.close();
    }

}