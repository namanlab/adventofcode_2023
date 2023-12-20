import java.util.*;

public class d4 {

    public static boolean checkcurgame(int r, int g, int b){
        return (r <= 12) && (g <= 13) && (b <= 14);
    };

    public static void main(String[] args) {
        int res = 0;
        String id = "";
        Scanner sc = new Scanner(System.in);
        sc.next();
        while (sc.hasNext() ){
            id = sc.next();
            String nextTok = sc.next();
            HashSet<Integer> hs = new HashSet<Integer>();
            while (!nextTok.equals("|") && sc.hasNext()){
                hs.add(Integer.valueOf(nextTok));
                nextTok = sc.next();
            }
            int cur_score = 0;
            nextTok = sc.next();
            while (!nextTok.equals("Card") && sc.hasNext()){
                if (hs.contains(Integer.valueOf(nextTok))){
                    cur_score++;
                }
                nextTok = sc.next();
            }
            if (cur_score != 0){
                res += Math.pow(2, cur_score - 1);
            }
        }
        System.out.println(res);
        sc.close();
    }

}