import java.math.BigInteger;
import java.util.*;
// Didn't have to use BigInteger, long is enough though

public class d5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.next();
        String nextTok = sc.next();
        ArrayList<BigInteger> seeds = new ArrayList<BigInteger>();
        while (sc.hasNext() && !nextTok.contains("-to-")){
            seeds.add(new BigInteger(nextTok));
            nextTok = sc.next();
        }
        while (sc.hasNext()){
            nextTok = sc.next();
            nextTok = sc.next();
            boolean[] checkarr =  new boolean[seeds.size()];
            Arrays.fill(checkarr, true);
            while (!nextTok.contains("-to-") && sc.hasNext()) {
                BigInteger dest = new BigInteger(nextTok);
                nextTok = sc.next();
                BigInteger src = new BigInteger(nextTok);
                nextTok = sc.next();
                BigInteger range = new BigInteger(nextTok);
                for (int i = 0; i < seeds.size(); i++) {
                    if ((seeds.get(i).compareTo(src) >= 0) && (seeds.get(i).compareTo(src.add(range)) < 0) && checkarr[i]){
                        seeds.set(i, dest.add(seeds.get(i)).subtract(src));
                        checkarr[i] = false;
                    }
                }
                if (sc.hasNext()){
                    nextTok = sc.next();
                }
            }
        }
        System.out.println(seeds);
        System.out.println(Collections.min(seeds));
        sc.close();
    }

}