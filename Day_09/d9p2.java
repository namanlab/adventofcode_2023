import java.util.*;



public class d9p2 {

    private static ArrayList<Integer> parseLine(String line) {
        ArrayList<Integer> integerList = new ArrayList<>();
        String[] numbers = line.split("\\s+");
        for (String number : numbers) {
            integerList.add(Integer.parseInt(number));
        }
        return integerList;
    }

    public static boolean checkZeros(int[] arr){
        boolean res = true;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != 0){
                return false;
            }
        }
        return res;
    }

    public static long getPrediction(ArrayList<Integer> arr){
        ArrayList<Integer> preds = new ArrayList<Integer>();
        int[] curArr = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++){
            curArr[i] = arr.get(i);
        }
        boolean allDiff = checkZeros(curArr);
        while (!allDiff){
            preds.add(curArr[0]);
            int[] diffArr = new int[curArr.length - 1];
            for (int i = 1; i < curArr.length; i++){
                diffArr[i - 1] = curArr[i] - curArr[i - 1];
            }
            curArr = diffArr;
            allDiff = checkZeros(curArr);
        }
        long pred = 0;
        for (int i = preds.size() - 1; i >= 0; i--){
            pred = preds.get(i) - pred;
        }
        return pred;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> finArr = new ArrayList<ArrayList<Integer>> ();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            ArrayList<Integer> integerList = parseLine(line);
                finArr.add(integerList);
        }
        ArrayList<Long> res = new ArrayList<Long>();
        for (int i = 0; i < finArr.size(); i++){
            res.add(getPrediction(finArr.get(i)));
        }
        long sumRes = 0;
        for (int i = 0; i < res.size(); i++){
            sumRes += res.get(i);
        }
        System.out.println(sumRes);
        sc.close();
    }

}