import java.util.*;


public class d18 {

    public static double shoelace(ArrayList<Integer> x, ArrayList<Integer> y){
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
        ArrayList<String[]> pats = new ArrayList<>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            String[] curArr = curLine.split(" ");
            pats.add(curArr);
        }
        int[] curSt = new int[2];
        curSt[0] = 0;
        curSt[1] = 0;
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        double perim = 0.0;
        for (int i = 0; i < pats.size(); i++){
            x.add(curSt[0]);
            y.add(curSt[1]);
            String[] curStArr = pats.get(i);
            if (curStArr[0].equals("R")){
                curSt[0] = curSt[0] + Integer.valueOf(curStArr[1]);
                curSt[1] = curSt[1];
            }
            if (curStArr[0].equals("L")){
                curSt[0] = curSt[0] - Integer.valueOf(curStArr[1]);
                curSt[1] = curSt[1];
            }
            if (curStArr[0].equals("U")){
                curSt[0] = curSt[0];
                curSt[1] = curSt[1] + Integer.valueOf(curStArr[1]);
            }
            if (curStArr[0].equals("D")){
                curSt[0] = curSt[0];
                curSt[1] = curSt[1] - Integer.valueOf(curStArr[1]);
            }
            perim += Integer.valueOf(curStArr[1]);
            
        }
        x.add(curSt[0]);
        y.add(curSt[1]);
        System.out.println(x);
        System.out.println(y);
        double res = shoelace(x, y) + (perim / 2) + 1;
        System.out.println(res);
        sc.close();
    }

}