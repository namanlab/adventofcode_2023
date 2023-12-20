import java.util.*;



public class d8 {

    public static String[] updatePos(String curPos, char[] pathInstr, HashMap<String, ArrayList<String>> hm){
        String[] res = new String[2];
        String newPos = curPos;
        int stepCount = pathInstr.length;
        int index = 0;
        for (int i = 0; i < pathInstr.length; i++){
            if (pathInstr[i] == 'R'){index = 1;}
            else {index = 0;}
            newPos = hm.get(newPos).get(index);
            if (newPos.equals("ZZZ")){
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        char[] pathInstr = path.toCharArray();
        String curLoc;
        String curPos = "AAA";
        HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
        while (sc.hasNext()){
            curLoc = sc.next();
            sc.next();
            ArrayList<String> mapArr = new ArrayList<String>();
            mapArr.add(sc.next().substring(1, 4));
            mapArr.add(sc.next().substring(0, 3));
            hm.put(curLoc, mapArr);
        }
        long stepCount = 0;
        while (!curPos.equals("ZZZ")){
            String[] getUpd = updatePos(curPos, pathInstr, hm);
            curPos = getUpd[0];
            stepCount += Long.valueOf(getUpd[1]);
        }
        System.out.println(stepCount);
        sc.close();
    }

}