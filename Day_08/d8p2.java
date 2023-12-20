import java.util.*;



public class d8p2 {

    public static String[] updatePos(String curPos, char[] pathInstr, HashMap<String, ArrayList<String>> hm){
        String[] res = new String[2];
        String newPos = curPos;
        int stepCount = pathInstr.length;
        int index = 0;
        for (int i = 0; i < pathInstr.length; i++){
            if (pathInstr[i] == 'R'){index = 1;}
            else {index = 0;}
            newPos = hm.get(newPos).get(index);
            if (newPos.endsWith("Z")){
                stepCount = i + 1;
                res[0] = newPos;
                res[1] = String.valueOf(stepCount);
                return res;
            }
        }
        res[0] = newPos;
        res[1] = String.valueOf(stepCount);
        return res;
    }

    public static long getStepCount(String curPos, char[] pathInstr, HashMap<String, ArrayList<String>> hm){
        long stepCount = 0;
        while (!curPos.endsWith("Z")){
            String[] getUpd = updatePos(curPos, pathInstr, hm);
            curPos = getUpd[0];
            stepCount += Long.valueOf(getUpd[1]);
        }
        return stepCount;
        
    }

    public static long gcd(long num1, long num2){
        if (num2 == 0){
            return num1;
        }
        return gcd(num2, num1 % num2);
    }
 
    public static long getLCM(ArrayList<Long> arr){
        long lcm = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            long num1 = lcm;
            long num2 = arr.get(i);
            long gcd_val = gcd(num1, num2);
            lcm = (lcm * arr.get(i)) / gcd_val;
        }
        return lcm;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        char[] pathInstr = path.toCharArray();
        String curLoc;
        ArrayList<String> startPos = new ArrayList<String>();
        HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
        while (sc.hasNext()){
            curLoc = sc.next();
            if (curLoc.endsWith("A")){
                startPos.add(curLoc);
            }
            sc.next();
            ArrayList<String> mapArr = new ArrayList<String>();
            mapArr.add(sc.next().substring(1, 4));
            mapArr.add(sc.next().substring(0, 3));
            hm.put(curLoc, mapArr);
        }
        String curPos;
        ArrayList<Long> resArr = new ArrayList<Long>();
        for (int i = 0; i < startPos.size(); i++){
            curPos = startPos.get(i);
            long res = getStepCount(curPos, pathInstr, hm);
            resArr.add(res);
        }
        System.out.println(getLCM(resArr));
        sc.close();
        
    }

}