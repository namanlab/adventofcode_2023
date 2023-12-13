import java.util.*;

public class d12 {

    public static boolean check(char[] pat, ArrayList<Integer> act){
        boolean res = true;
        int counter = 0;
        int i = 0;
        while (i < pat.length){
            if (pat[i] == '#'){
                int counter2 = 0;
                while (pat[i] == '#'){
                    i++;
                    counter2++;
                    if (i >= pat.length){break;}
                }
                if (counter >= act.size()){
                    return false;
                }
                if (counter2 != act.get(counter)){
                    return false;
                }
                counter++;
            }
            i++;
        }
        if (counter != act.size()){
            return false;
        }
        return res;
    }

    public static long checkPats(char[] pat, ArrayList<Integer> act, int diff){
        ArrayList<Integer> qn = new ArrayList<Integer>();
        for (int i = 0; i < pat.length; i++){
            if (pat[i] == '?'){qn.add(i);}
        }
        if (qn.size() == 0){
            if (check(pat, act)){return 1;} 
            else {return 0;}
        }
        char[] curiinch = pat.clone();
        char[] curininch = pat.clone();
        curiinch[qn.get(0)] = '#';
        curininch[qn.get(0)] = '.';
        if (diff == 0){
            return checkPats(curininch, act, diff);
        } else {
            return checkPats(curiinch, act, diff - 1) + checkPats(curininch, act, diff);
        }
    }

    public static long allPat(char[] pat, ArrayList<Integer> act){
        ArrayList<Integer> qn = new ArrayList<Integer>();
        int hn = 0;
        int ehn = 0;
        for (int i = 0; i < pat.length; i++){
            if (pat[i] == '?'){qn.add(i);}
            if (pat[i] == '#'){hn++;}
        }
        for (int i = 0; i < act.size(); i++){
            ehn += act.get(i);
        }
        return checkPats(pat, act, ehn - hn);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<char[]> allPat = new ArrayList<char[]>();
        ArrayList<ArrayList<Integer>> allAct = new ArrayList<ArrayList<Integer>>();
        while (sc.hasNext()) {
            String curPat = sc.next();
            String actRes = sc.next();
            allPat.add(curPat.toCharArray());
            List<String> curCont = Arrays.asList(actRes.split(","));
            ArrayList<Integer> curAct = new ArrayList<Integer>();
            for (int i = 0; i < curCont.size(); i++){
                curAct.add(Integer.parseInt(curCont.get(i)));
            }
            allAct.add(curAct);
        }
        long res = 0;
        for (int i = 0; i < allPat.size(); i++){
            long curVal = allPat(allPat.get(i), allAct.get(i));
            res += curVal;
        }
        System.out.println(res);
        sc.close();
    }

}