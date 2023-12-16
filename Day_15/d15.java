import java.util.*;

public class d15 {
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
        long res = 0;
        for (String i: strArr){
            res += getCode(i);
            
        }
        System.out.println(res);
        sc.close();
    }

}