import java.util.*;

public class d22 {

    public static void seiveDown(int[][][] arr, int maxX, int maxY, int maxZ){
        for (int k = 1; k <= maxZ; k++){
            // Create hashmap of brick id vs list of  coordinates
            HashMap<Integer, ArrayList<ArrayList<Integer>>> hm = new HashMap<>();
            for (int i = 0; i <= maxX; i++){
                for (int j = 0; j <= maxY; j++){
                    int curId = arr[k][i][j];
                    if (curId != 0){
                        if (!hm.containsKey(curId)){hm.put(curId, new ArrayList<ArrayList<Integer>>());}
                        ArrayList<Integer> tempArr = new ArrayList<Integer>();
                        tempArr.add(i); tempArr.add(j);
                        hm.get(curId).add(tempArr);
                    }
                }
            }
            for (int valP: hm.keySet()){
                ArrayList<ArrayList<Integer>> curArr = hm.get(valP);
                for (int l = k - 1; l >= 0; l--){
                    boolean checkCur = true;
                    for (ArrayList<Integer> subArr: curArr){
                        if (arr[l][subArr.get(0)][subArr.get(1)] != 0){checkCur = false; break;}
                    }
                    if (checkCur){
                        for (ArrayList<Integer> subArr: curArr){
                            arr[l][subArr.get(0)][subArr.get(1)] = arr[l + 1][subArr.get(0)][subArr.get(1)];
                            arr[l + 1][subArr.get(0)][subArr.get(1)] = 0;
                        }
                    } else {break;}
                }
            }
        }
    }
    public static void getCount(int[][][] arr, int maxX, int maxY, int maxZ, boolean[] newArr){
        for (int k = 1; k <= maxZ; k++){
            // Create hashmap of brick id vs list of  coordinates
            HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
            for (int i = 0; i <= maxX; i++){
                for (int j = 0; j <= maxY; j++){
                    int curId = arr[k][i][j];
                    if (curId != 0){
                        if (!hm.containsKey(curId)){hm.put(curId, new HashSet<Integer>());}
                        if (arr[k - 1][i][j] != 0){hm.get(curId).add(arr[k - 1][i][j]);}
                    }
                }
            }
            for (int valP: hm.keySet()){
                if (hm.get(valP).size() == 1){
                    Iterator<Integer> iterator = hm.get(valP).iterator();
                    int remVal = iterator.next();
                    if (valP != remVal) {newArr[remVal - 1] = false;}
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> pats = new ArrayList<ArrayList<Integer>>();
        int maxX = 0; int maxY = 0; int maxZ = 0;
        int counter = 1;
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            String[] splitLine = curLine.split("~");
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (String s: splitLine[0].split(",")){temp.add(Integer.valueOf(s));}
            for (String s: splitLine[1].split(",")){temp.add(Integer.valueOf(s));}
            maxX = Math.max(maxX, temp.get(0)); maxX = Math.max(maxX, temp.get(3));
            maxY = Math.max(maxY, temp.get(1)); maxY = Math.max(maxY, temp.get(4));
            maxZ = Math.max(maxZ, temp.get(2)); maxZ = Math.max(maxZ, temp.get(5));
            temp.add(counter);
            pats.add(temp);
            counter++;
        } 
        int[][][] arr = new int[maxZ + 1][maxX + 1][maxY + 1];
        for (int[][] innerRow: arr){for (int[] innerInnerRow: innerRow){Arrays.fill(innerInnerRow, 0);}}
        int xst, xen, yst, yen, zst, zen;
        for (ArrayList<Integer> tarr: pats){
            xst = tarr.get(0); xen = tarr.get(3);
            yst = tarr.get(1); yen = tarr.get(4);
            zst = tarr.get(2); zen = tarr.get(5);
            for (int i = xst; i <= xen; i++){
                for (int j = yst; j <= yen; j++){
                    for (int k = zst; k <= zen; k++){
                        arr[k][i][j] = tarr.get(6);
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(arr));
        seiveDown(arr, maxX, maxY, maxZ);
        System.out.println(Arrays.deepToString(arr));
        boolean[] newArr = new boolean[pats.size()];
        Arrays.fill(newArr, true);
        getCount(arr, maxX, maxY, maxZ, newArr);
        int res = 0;
        for (int i = 0; i < newArr.length; i++){if (newArr[i]){res++;}}
        System.out.println(res);
        sc.close();
    }

}