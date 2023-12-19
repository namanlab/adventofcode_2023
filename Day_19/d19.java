import java.util.*;


public class d19 {

    // px{a<2006:qkq,m>2090:A,rfg}
    // HahMap(K: px, V: ArrayList<ArrayList<var, >/<, value, Next WF>, ArrayList<String: Next WF>>)

    public static String getNextWF(ArrayList<Integer> arr,  ArrayList<ArrayList<String>> curWF){
        if (curWF.size() == 1){
            return curWF.getLast().get(0);
        } else {
            for (int i = 0; i < curWF.size() - 1; i++){
                String val1 = curWF.get(i).get(0);
                String cond = curWF.get(i).get(1);
                String val2 = curWF.get(i).get(2);
                String val3 = curWF.get(i).get(3);
                if (val1.equals("x")){
                    if (cond.equals(">")){if (arr.get(0) > Integer.valueOf(val2)){return val3;}}
                    if (cond.equals("<")){if (arr.get(0) < Integer.valueOf(val2)){return val3;}}
                }
                if (val1.equals("m")){
                    if (cond.equals(">")){if (arr.get(1) > Integer.valueOf(val2)){return val3;}}
                    if (cond.equals("<")){if (arr.get(1) < Integer.valueOf(val2)){return val3;}}
                }
                if (val1.equals("a")){
                    if (cond.equals(">")){if (arr.get(2) > Integer.valueOf(val2)){return val3;}}
                    if (cond.equals("<")){if (arr.get(2) < Integer.valueOf(val2)){return val3;}}
                }
                if (val1.equals("s")){
                    if (cond.equals(">")){if (arr.get(3) > Integer.valueOf(val2)){return val3;}}
                    if (cond.equals("<")){if (arr.get(3) < Integer.valueOf(val2)){return val3;}}
                }
            }
            return curWF.get(curWF.size() - 1).get(0);
        }
    }

    public static long propWF(ArrayList<Integer> arr, HashMap<String, ArrayList<ArrayList<String>>> hm){
        long res = 0;
        String curSt = "in";
        ArrayList<ArrayList<String>> curWF = hm.get(curSt);
        curSt = getNextWF(arr, curWF);
        while ((!curSt.equals("R")) && (!curSt.equals("A"))){
            curWF = hm.get(curSt);
            curSt = getNextWF(arr, curWF);
        }
        if (curSt.equals("A")){
            res = arr.get(0) + arr.get(1) + arr.get(2) + arr.get(3);
        } else {
            res = 0;
        }
        return res;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, ArrayList<ArrayList<String>>> hm = new HashMap<>();
        String curLine = sc.nextLine();
        while (!curLine.equals("")) {
            String[] curArr = curLine.split("\\{");
            String curKey = curArr[0];
            String[] nextArr = curArr[1].substring(0, curArr[1].length() - 1).split(",");
            ArrayList<ArrayList<String>> temp = new ArrayList<>();
            for (int i = 0; i < nextArr.length -  1; i++){
                ArrayList<String> subTemp = new ArrayList<String>();
                String curSt = nextArr[i];
                String[] subNextArr = curSt.split(":");
                subTemp.add(subNextArr[0].substring(0, 1));
                subTemp.add(subNextArr[0].substring(1, 2));
                subTemp.add(subNextArr[0].substring(2));
                subTemp.add(subNextArr[1]);
                temp.add(subTemp);
            }
            ArrayList<String> lastTemp = new ArrayList<String>();
            lastTemp.add(nextArr[nextArr.length - 1]);
            temp.add(lastTemp);
            curLine = sc.nextLine();
            hm.put(curKey, temp);
        }
        long res = 0;

        while (sc.hasNextLine()) {
            String curLine2 = sc.nextLine();
            String[] curSubArr = curLine2.substring(1, curLine2.length() - 1).split(",");
            ArrayList<Integer> tempVal = new ArrayList<Integer>();
            for (String s: curSubArr){
                tempVal.add(Integer.valueOf(s.substring(2)));
            }
            res += propWF(tempVal, hm);
        }
        System.out.println(res);
        sc.close();
    }

}