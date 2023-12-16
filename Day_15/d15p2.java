import java.util.*;

public class d15p2 {
    public static int getCode(String s){
        int res = 0;
        for (char c: s.toCharArray()){
            res += (int) c;
            res *= 17;
            res = res % 256;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String curLine = sc.nextLine();
        String[] strArr = curLine.split(",");
        ArrayList<ArrayList<String[]>> arr = new ArrayList<ArrayList<String[]>>();
        ArrayList<HashMap<String, String>> arrHM = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 256; i++){
            ArrayList<String[]> subTemp1 = new ArrayList<String[]>();
            HashMap<String, String> subTemp2 = new HashMap<String, String>();
            arr.add(subTemp1);
            arrHM.add(subTemp2);
        }
        int curCode;
        for (String i: strArr){
            if (i.endsWith("-")){
                String substr = i.substring(0, i.length() - 1);
                curCode = getCode(substr);
                System.out.println(i.substring(0, i.length() - 1));
                if (!arrHM.get(curCode).containsKey(substr)){
                    continue;
                } else {
                    for (int j = 0; j < arr.get(curCode).size(); j++){
                        if (arr.get(curCode).get(j)[0].equals(substr)){
                            arr.get(curCode).remove(j);
                        }
                    }
                    arrHM.get(curCode).remove(substr);
                }
            } else {
                String[] strSubArr = i.split("=");
                curCode = getCode(strSubArr[0]);
                if (arrHM.get(curCode).containsKey(strSubArr[0])){
                    for (int j = 0; j < arr.get(curCode).size(); j++){
                        if (arr.get(curCode).get(j)[0].equals(strSubArr[0])){
                            arr.get(curCode).get(j)[1] = strSubArr[1];                        }
                    }
                } else {
                    String[] newVal = new String[2];
                    newVal[0] = strSubArr[0];
                    newVal[1] = strSubArr[1];
                    arr.get(curCode).addLast(newVal);
                    arrHM.get(curCode).put(strSubArr[0], strSubArr[1]);
                }
            }
            
        }
        long res = 0;
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(i).size(); j++){
                int temp = (i + 1) * (j + 1) * Integer.valueOf(arr.get(i).get(j)[1]);
                res += temp;
            }
            
        }
        System.out.println(res);
        sc.close();
    }

}