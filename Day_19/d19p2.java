import java.text.DecimalFormat;
import java.util.*;


public class d18p2 {

    public static double shoelace(ArrayList<Long> x, ArrayList<Long> y){
        double area = 0.0;
        int n = x.size();
        int j = n - 1;
        for (int i = 0; i < n; i++){
            area += (x.get(j) + x.get(i)) * (y.get(j) - y.get(i));
            j = i; 
        }
        return Math.abs(area / 2.0);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<long[]> pats = new ArrayList<>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            String[] curArr = curLine.split(" ");
            String hexCode = curArr[2].substring(2, curArr[2].length() - 1);
            long[] newCurArr = new long[2];
            newCurArr[1] = Integer.parseInt(hexCode.substring(0, hexCode.length() - 1),16);  
            newCurArr[0]= Integer.valueOf(hexCode.substring(hexCode.length() - 1, hexCode.length()));
            pats.add(newCurArr);
        }
        long[] curSt = new long[2];
        curSt[0] = 0;
        curSt[1] = 0;
        ArrayList<Long> x = new ArrayList<>();
        ArrayList<Long> y = new ArrayList<>();
        double perim = 0.0;
        for (int i = 0; i < pats.size(); i++){
            x.add(curSt[0]);
            y.add(curSt[1]);
            long[] curStArr = pats.get(i);
            if (curStArr[0] == 0){
                curSt[0] = curSt[0] + curStArr[1];
                curSt[1] = curSt[1];
            }
            if (curStArr[0] == 2){
                curSt[0] = curSt[0] - curStArr[1];
                curSt[1] = curSt[1];
            }
            if (curStArr[0] == 3){
                curSt[0] = curSt[0];
                curSt[1] = curSt[1] + curStArr[1];
            }
            if (curStArr[0] == 1){
                curSt[0] = curSt[0];
                curSt[1] = curSt[1] - curStArr[1];
            }
            perim += curStArr[1];
            
        }
        x.add(curSt[0]);
        y.add(curSt[1]);
        double res = shoelace(x, y) + (perim / 2) + 1;
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        System.out.println(df.format(res));
        sc.close();
    }

}