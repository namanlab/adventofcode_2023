import java.util.*;
import java.nio.file.*;

public class d4p2 {

    public static void main(String[] args) {
        int res = 0;
        String id = "";
        long lines = 0;
        try {
            // make a connection to the file
            Path file = Paths.get("input.txt");
            // read all lines of the file
            lines = Files.lines(file).count();
        } catch (Exception e) {
            e.getStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 1; i <= lines; i++){
            hm.put(i, 1);
        }
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
            Integer id_num = Integer.valueOf(id.substring(0, id.length() - 1));
            for (int i = id_num + 1; i <= id_num + cur_score; i++){
                if (hm.containsKey(i)){
                    hm.put(i, hm.get(i) + hm.get(id_num));
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int cur_value = entry.getValue();
            res += cur_value;
        }
        System.out.println(res);
        sc.close();
    }

}