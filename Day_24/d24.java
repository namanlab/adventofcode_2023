import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class d24 {

    public static double[] solver(BigInteger a1, BigInteger b1, BigInteger c1, BigInteger a2, BigInteger b2, BigInteger c2) {
        BigInteger determinant = a1.multiply(b2).subtract(a2.multiply(b1));
        double x = c2.multiply(b1).subtract(c1.multiply(b2)).doubleValue() / determinant.doubleValue();
        double y = a2.multiply(c1).subtract(a1.multiply(c2)).doubleValue() / determinant.doubleValue();
        return new double[]{x, y};
    }

    public static int getPoss(ArrayList<BigInteger> arr1, ArrayList<BigInteger> arr2, BigInteger ll, BigInteger ul) {
        // ax + by = c
        BigInteger a1 = arr1.get(3);
        BigInteger b1 = arr1.get(2).negate();
        BigInteger c1 = a1.multiply(arr1.get(0)).add(b1.multiply(arr1.get(1))).negate();

        BigInteger a2 = arr2.get(3);
        BigInteger b2 = arr2.get(2).negate();
        BigInteger c2 = a2.multiply(arr2.get(0)).add(b2.multiply(arr2.get(1))).negate();

        double d1 = a1.doubleValue() / a2.doubleValue();
        double d2 = b1.doubleValue() / b2.doubleValue();
        double d3 = c1.doubleValue() / c2.doubleValue();

        if (d1 == d2) {
            if (d3 == d1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            double[] curSol = solver(a1, b1, c1, a2, b2, c2);
            if ((curSol[0] < ll.doubleValue()) || (curSol[0] > ul.doubleValue())) {
                return 0;
            }
            if ((curSol[1] < ll.doubleValue()) || (curSol[1] > ul.doubleValue())) {
                return 0;
            }
            double t1 = (curSol[0] - arr1.get(0).doubleValue()) / arr1.get(2).doubleValue();
            double t2 = (curSol[0] - arr2.get(0).doubleValue()) / arr2.get(2).doubleValue();
            if (t1 < 0 || t2 < 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<BigInteger>> arr = new ArrayList<>();

        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            String[] pv = curLine.split(" @ ");
            String[] p = pv[0].split(", ");
            String[] v = pv[1].split(", ");
            ArrayList<BigInteger> temp = new ArrayList<>();
            temp.add(new BigInteger(p[0].replaceAll("\\s+", "")));
            temp.add(new BigInteger(p[1].replaceAll("\\s+", "")));
            temp.add(new BigInteger(v[0].replaceAll("\\s+", "")));
            temp.add(new BigInteger(v[1].replaceAll("\\s+", "")));
            arr.add(temp);
        }
        BigInteger ll = new BigInteger("200000000000000");
        BigInteger ul = new BigInteger("400000000000000");
        long res = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                long cur = getPoss(arr.get(i), arr.get(j), ll, ul);
                res += cur;
            }
        }
        System.out.println(res);
        sc.close();
    }
}
