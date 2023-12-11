import java.util.*;

public class d11 {

    private static int parseAddLine(String line, ArrayList<ArrayList<Integer>> arr, int galaxyCount) {
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
        if (toDouble){arr.add(res);}
        return galaxyCount;
    }

    public static void expandCols(ArrayList<ArrayList<Integer>> arr){
        boolean[] res = new boolean[arr.get(0).size()];
        Arrays.fill(res, true);
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(0).size(); j++){
                if (arr.get(i).get(j) != 0){
                    res[j] = false;
                }
            }
        }
        int counter = 0;
        for (int i = 0; i < res.length; i++){
            if (res[i]){
                for (int j = 0; j < arr.size(); j++){
                    arr.get(j).add(i + 1 + counter, 0);
                }
                counter++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int galaxyCount = 1;
        ArrayList<ArrayList<Integer>> finArr = new ArrayList<ArrayList<Integer>>();
        ArrayList<int[]> galaxyCoords = new ArrayList<int[]>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            galaxyCount = parseAddLine(line, finArr, galaxyCount);
        }
        expandCols(finArr);
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
                res += Math.abs(curSt[0] - curEn[0]) + Math.abs(curSt[1] - curEn[1]);
            }
        }
        System.out.println(res);
        sc.close();
    }

}