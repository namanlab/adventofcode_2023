import java.util.*;

public class d13p2 {

    public static int checkMatch(ArrayList<Character> list1, ArrayList<Character> list2){
        int res = 0;
        for (int i = 0; i < list1.size(); i++){
            if (!list1.get(i).equals(list2.get(i))){
                res++;
            }
        }
        return res;
    }

    public static int getPat(ArrayList<ArrayList<Character>> arr){
        int res = -1;
        int curDiff;
        ArrayList<Character> matchStr, curStr;
        // First from top,
        matchStr = arr.getFirst();
        for (int i = arr.size() - 1; i > 0; i--){
            curStr = arr.get(i);
            if (i % 2 == 0){
                continue;
            }
            curDiff = checkMatch(matchStr, curStr);
            if (curDiff == 0 || curDiff == 1){
                int checkOne = 0;
                if (curDiff == 1){checkOne++;}
                if (i == 1 && curDiff == 1){
                    res =  1;
                    return res;
                }
                boolean potent = true;
                for(int j = 1; j <= (i - 1) / 2; j++){
                    curDiff = checkMatch(arr.get(j), arr.get(i - j));
                    if (curDiff == 1){checkOne++;}
                    if (curDiff != 0 && curDiff != 1){
                        potent = false;
                        break;
                    }
                    if (checkOne > 1){
                        potent = false;
                        break;
                    }
                }
                if (potent && checkOne == 1){
                    res = (i + 1) / 2;
                    return res;
                }
            }
        }
        
        // Then from bottom,
        matchStr = arr.getLast();
        for (int i = 0; i < arr.size() - 1; i++){
            curStr = arr.get(i);
            if (i % 2 == 0){
                continue;
            }
            curDiff = checkMatch(matchStr, curStr);
            if (curDiff == 0 || curDiff == 1){
                int checkOne = 0;
                if (curDiff == 1){checkOne++;}
                boolean potent = true;
                int counterim = 0;
                if (i == arr.size() - 2 && curDiff == 1){
                    res =  arr.size() - 1;
                    return res;
                }
                for(int j = arr.size() - 2; j >= (i + arr.size()) / 2; j--){
                    counterim++;
                    curDiff = checkMatch(arr.get(j), arr.get(i + counterim));
                    if (curDiff == 1){checkOne++;}
                    if (curDiff != 0 && curDiff != 1){
                        potent = false;
                        break;
                    }
                    if (checkOne > 1){
                        potent = false;
                        break;
                    }
                }
                if (potent && checkOne == 1){
                    res = (i + arr.size()) / 2;
                    return res;
                }
            }
        }
        return res;
    }

    public static ArrayList<ArrayList<Character>>  transpose(ArrayList<ArrayList<Character>> arr){
        ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>> ();
        for (int i = 0; i < arr.get(0).size(); i++){
            ArrayList<Character> temp = new ArrayList<Character>();
            res.add(temp);
        }
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(0).size(); j++){
                res.get(j).add(arr.get(i).get(j));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<ArrayList<Character>>> pats = new ArrayList<ArrayList<ArrayList<Character>>>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            ArrayList<ArrayList<Character>> temp = new ArrayList<ArrayList<Character>>();
            while (!curLine.equals("")){
                char[] curSt = curLine.toCharArray();
                ArrayList<Character> subTemp = new ArrayList<Character>();
                for (char c: curSt){subTemp.add(c);}
                temp.add(subTemp);
                if (!sc.hasNextLine()){break;}
                curLine = sc.nextLine();
            }
            pats.add(temp);
        }
        long res = 0;
        for (int i = 0; i < pats.size(); i++){
            int curVal = getPat(pats.get(i));
            // Row
            if (curVal != -1){res += 100*curVal;}
            else {
                curVal = getPat(transpose(pats.get(i)));
                res += curVal;
            }
            System.out.println(curVal);
        }
        System.out.println(res);
        sc.close();
    }

}