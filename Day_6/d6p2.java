import java.util.*;

public class d6p2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.next();
        String nextTok = sc.next();
        ArrayList<Double> time = new ArrayList<Double>();
        ArrayList<Double> dist = new ArrayList<Double>();
        ArrayList<Double> res = new ArrayList<Double>();
        StringBuilder sb_dist = new StringBuilder();
        StringBuilder sb_time = new StringBuilder();
        while (sc.hasNext() && !nextTok.contains("Distance")){
            sb_time.append(nextTok);
            nextTok = sc.next();
        }
        time.add(Double.valueOf(sb_time.toString()));
        while (sc.hasNext()){
            nextTok = sc.next();
            sb_dist.append(nextTok);
        }
        dist.add(Double.valueOf(sb_dist.toString()));
        for (int i = 0; i < time.size(); i++){
            double curTime = time.get(i);
            double curDist = dist.get(i);
            if (curDist >= Math.pow(curTime, 2)/4){
                res.add(0.0);
            } else {
                double term1 = (curTime - Math.sqrt(Math.pow(curTime, 2) - 4*curDist))/2;
                double term2 = (curTime + Math.sqrt(Math.pow(curTime, 2) - 4*curDist))/2;
                term1 = Math.ceil(term1);
                term2 = Math.floor(term2);
                if (curTime*term1 - Math.pow(term1, 2) == curDist){
                    term1 = term1 + 1;
                } 
                if (curTime*term2 - Math.pow(term2, 2) == curDist){
                    term2 = term2 - 1;
                }
                res.add(Math.max(term2 - term1 + 1, 0));
            }
        }
        double fin = 1.0;
        for (int i = 0; i < res.size(); i++){
            fin = fin*res.get(i);
        }
        System.out.printf("%.9f", fin);
        System.out.println();
        sc.close();
    }

}