import java.util.*;

public class d2 {

    public static boolean checkcurgame(int r, int g, int b){
        return (r <= 12) && (g <= 13) && (b <= 14);
    };
    public static void main(String[] args) {
        int res = 0;
        String id = "";
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext() ){
            sc.next();
            boolean cur_game = true;
            id = sc.next();
            String nextTok = ",";
             while (((nextTok.charAt(nextTok.length() - 1) == ',') || (nextTok.charAt(nextTok.length() - 1) == ';')) && sc.hasNext()){
                int curr = 0, curg = 0, curb = 0; 
                boolean continuerun = true;
                while (((nextTok.charAt(nextTok.length() - 1) == ',') && sc.hasNext()) || continuerun){
                    continuerun = false;
                    int curnum = sc.nextInt();
                    nextTok = sc.next();
                    if (nextTok.equals("red") || nextTok.equals("red,") || nextTok.equals("red;")){
                        curr += curnum;
                    } else if (nextTok.equals("blue") || nextTok.equals("blue,") || nextTok.equals("blue;")){
                        curb += curnum;
                    } else {
                        curg += curnum;
                    }
                    if (!checkcurgame(curr, curg, curb)){
                        cur_game = false;
                        break;
                    }
                }
             }
             if (cur_game){
                System.out.println(Integer.valueOf(id.substring(0, id.length() - 1)));
                res += Integer.valueOf(id.substring(0, id.length() - 1));
             }
        }
        System.out.println(res);
        sc.close();
    }

}