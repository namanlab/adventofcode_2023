import java.util.*;

public class d2p2 {
    public static void main(String[] args) {
        int res = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext() ){
            sc.next();
            sc.next();
            String nextTok = ",";
            int curr = 0, curg = 0, curb = 0; 
             while (((nextTok.charAt(nextTok.length() - 1) == ',') || (nextTok.charAt(nextTok.length() - 1) == ';')) && sc.hasNext()){
                boolean continuerun = true;
                while (((nextTok.charAt(nextTok.length() - 1) == ',') && sc.hasNext()) || continuerun){
                    continuerun = false;
                    int curnum = sc.nextInt();
                    nextTok = sc.next();
                    if (nextTok.equals("red") || nextTok.equals("red,") || nextTok.equals("red;")){
                        curr = Math.max(curnum, curr);
                    } else if (nextTok.equals("blue") || nextTok.equals("blue,") || nextTok.equals("blue;")){
                        curb = Math.max(curnum, curb);
                    } else {
                        curg = Math.max(curnum, curg);
                    }
                }
             }
             int game_num = curr * curg * curb;
             System.out.println(game_num);
             res += game_num;

             
        }
        System.out.println(res);
        sc.close();
    }

}