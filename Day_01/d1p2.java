import java.util.*;

public class d1p2 {

    public static int checkword(char[] arr, int i, HashMap<String, Integer> hm){
        for (int j = i + 2; j < i +6; j++){
            char[] curArr = Arrays.copyOfRange(arr, i, j);
            String temp_string = new String(curArr);
            if (hm.containsKey(temp_string)){
                return hm.get(temp_string);
            }
        }
        return -1;
    }
    public static void main(String[] args) {

        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("zero", 0);
        hm.put("one", 1);
        hm.put("two", 2);
        hm.put("three", 3);
        hm.put("four", 4);
        hm.put("five", 5);
        hm.put("six", 6);
        hm.put("seven", 7);
        hm.put("eight", 8);
        hm.put("nine", 9);
        int res = 0, cur_num = 0, cur_num_2 = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String curString = sc.nextLine();
            char[] curarr = curString.toCharArray();
            for (int i = 0; i <  curarr.length; i++){
                if (Character.isDigit(curarr[i])){
                    cur_num = Character.getNumericValue(curarr[i]);
                    break;
                }
                if (checkword(curarr, i, hm) != -1){
                    cur_num = checkword(curarr, i, hm);
                    break;
                }
            }
            for (int i = curarr.length - 1; i >= 0; i--){
                if (Character.isDigit(curarr[i])){
                    cur_num_2 = Character.getNumericValue(curarr[i]);
                    break;
                }
                if (checkword(curarr, i, hm) != -1){
                    cur_num_2 = checkword(curarr, i, hm);
                    break;
                }
            }
            res = res + 10*cur_num + cur_num_2;
        }
        System.out.println(res);
        sc.close();
    }
}