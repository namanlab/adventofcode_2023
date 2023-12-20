import java.util.*;

public class d1 {
    public static void main(String[] args) {
        int res = 0, cur_num = 0, cur_num_2 = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String curString = sc.nextLine();
            char[] curarr = curString.toCharArray();
            for (int i = 0; i <  curarr.length; i++){
                if (Character.isDigit(curarr[i])){
                    cur_num = Character.getNumericValue(curarr[i]);
                    break;
                }
            }
            for (int i = curarr.length - 1; i >= 0; i--){
                if (Character.isDigit(curarr[i])){
                    cur_num_2 = Character.getNumericValue(curarr[i]);
                    break;
                }
            }
            res = res + 10*cur_num + cur_num_2;
        }
        System.out.println(res);
        sc.close();
    }
}