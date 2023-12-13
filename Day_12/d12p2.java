import java.util.*;

public class d12p2 {

    // Memoization cache
    private static HashMap<String, Long> memo = new HashMap<>();

    public static long allPat2(char[] lava, ArrayList<Integer> springs) {
        String key = Arrays.toString(lava) + springs.toString();
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (springs.isEmpty()) {
            for (char c : lava) {
                if (c == '#') {
                    return 0;
                }
            }
            return 1;
        }
        int current = springs.remove(0);
        long result = 0;
        for (int i = 0; i < lava.length - sum(springs) - springs.size() - current + 1; i++) {
            if (containsBrokenSpring(lava, i)) {
                break;
            }
            int nxt = i + current;
            if (nxt < lava.length){
                if (!containsDot(lava, i, nxt) && lava[nxt] != '#') {
                    result += allPat2(subArray(lava, nxt + 1, lava.length), new ArrayList<>(springs));
                }
            }
            if (nxt == lava.length && !containsDot(lava, i, nxt)) {
                    result += allPat2(new char[0], new ArrayList<>(springs));
                }
        }
        memo.put(key, result);
        return result;
    }

    private static boolean containsBrokenSpring(char[] lava, int position) {
        for (int j = 0; j < position; j++) {
            if (lava[j] == '#') {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDot(char[] lava, int start, int end) {
        for (int j = start; j < end; j++) {
            if (lava[j] == '.') {
                return true;
            }
        }
        return false;
    }

    private static char[] subArray(char[] array, int start, int end) {
        char[] result = new char[end - start];
        System.arraycopy(array, start, result, 0, end - start);
        return result;
    }

    private static int sum(ArrayList<Integer> list) {
        int sum = 0;
        for (int value : list) {
            sum += value;
        }
        return sum;
    }

    public static char[] multArr(char[] arr){
        char[] res = new char[arr.length*5 + 4];
        for (int i = 0; i < arr.length + 1 ; i++){
            for (int j = 0; j < 5; j++){
                if (i == arr.length && j != 4){res[i + (arr.length + 1)*j] = '?';}
                else if (i == arr.length && j == 4){}
                else {res[i + (arr.length + 1)*j] = arr[i];}
            }
        }
        return res;
    }
    public static ArrayList<Integer> multArrList(ArrayList<Integer> arr){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int j = 0; j < 5; j++){
            for (int i = 0; i < arr.size(); i++){
                res.add(arr.get(i));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<char[]> allPat = new ArrayList<char[]>();
        ArrayList<ArrayList<Integer>> allAct = new ArrayList<ArrayList<Integer>>();
        while (sc.hasNext()) {
            String curPat = sc.next();
            String actRes = sc.next();
            char[] charCurPat = curPat.toCharArray();
            charCurPat = multArr(charCurPat);
            List<String> curCont = Arrays.asList(actRes.split(","));
            ArrayList<Integer> curAct = new ArrayList<Integer>();
            for (int i = 0; i < curCont.size(); i++){
                curAct.add(Integer.parseInt(curCont.get(i)));
            }
            curAct = multArrList(curAct);
            allPat.add(charCurPat);
            allAct.add(curAct);
        }
        long res = 0;
        for (int i = 0; i < allPat.size(); i++){
            long curVal = allPat2(allPat.get(i), allAct.get(i));
            //System.out.println(memo.size());
            memo.clear();
            System.out.println(curVal);
            res += curVal;
        }
        System.out.println(res);
        sc.close();
    }

}