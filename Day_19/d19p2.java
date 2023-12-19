import java.math.BigInteger;
import java.util.*;

class Bracket {
    public int[] st = new int[4];
    public int[] en = new int[4];
    public String id = "";
    public boolean active = true;

    public Bracket(){
        Arrays.fill(st, 1);
        Arrays.fill(en, 4000);
    }

    public Bracket split(int xmas, String cond, int val2, String val3){
        // System.out.println(xmas + " " + cond + " " + val2 + " " + val3);
        if (cond.equals(">")){
            Bracket nb = new Bracket();
            nb.st = this.st.clone();
            nb.en = this.en.clone();
            nb.st[xmas] = Math.max(0, val2 + 1);
            this.en[xmas] = Math.max(0, val2);
            nb.id = val3;
            if (this.en[xmas] < this.st[xmas]){this.active = false;}
            if (nb.en[xmas] < nb.st[xmas]){nb.active = false;}
            return nb;
        } else {
            Bracket nb = new Bracket();
            nb.st = this.st.clone();
            nb.en = this.en.clone();
            nb.en[xmas] = Math.min(4000, val2 - 1);
            this.st[xmas] = Math.min(4000, val2);
            nb.id = val3;
            if (this.en[xmas] < this.st[xmas]){this.active = false;}
            if (nb.en[xmas] < nb.st[xmas]){nb.active = false;}
            return nb;
        }
    }

    public BigInteger getCombs(){
        if (this.active){
            BigInteger res = BigInteger.valueOf((this.en[0] - this.st[0] + 1));
            res = res.multiply(BigInteger.valueOf((this.en[1] - this.st[1] + 1)));
            res = res.multiply(BigInteger.valueOf((this.en[2] - this.st[2] + 1)));
            res = res.multiply(BigInteger.valueOf((this.en[3] - this.st[3] + 1)));
            return res;
        }
        else {return new BigInteger("0");}

    }

    
    @Override
    public String toString() {
        return "[" +  "(" + this.st[0] + ", " + this.en[0] + ")" + " " + "(" + this.st[1] + ", " + this.en[1] + ")" + " " +
        "(" + this.st[2] + ", " + this.en[2] + ")" + " " + "(" + this.st[3] + ", " + this.en[3] + ")" + "]" + "-" + this.id;
    }
}



public class d19p2 {

    // px{a<2006:qkq,m>2090:A,rfg}
    // HahMap(K: px, V: ArrayList<ArrayList<var, >/<, value, Next WF>, ArrayList<String: Next WF>>)

    public static void runMod(ArrayList<Bracket> arr,ArrayList<Bracket> pos, HashMap<String, ArrayList<ArrayList<String>>> hm){
        while (!arr.isEmpty()){
            Bracket curB = arr.removeLast();
            ArrayList<ArrayList<String>> curWF = hm.get(curB.id);
            if (curWF.size() == 1){
                String valOut = curWF.getLast().get(0);
                if (valOut.equals("A")){pos.add(curB);}
                if (!valOut.equals("A") && !valOut.equals("R")){curB.id = valOut; arr.add(curB);}
            } else {
                for (int i = 0; i < curWF.size() - 1; i++){
                    String val1 = curWF.get(i).get(0);
                    String cond = curWF.get(i).get(1);
                    String val2 = curWF.get(i).get(2);
                    String val3 = curWF.get(i).get(3);
                    Bracket newB;
                    if (val1.equals("x")){newB = curB.split(0, cond, Integer.valueOf(val2), val3);}
                    else if (val1.equals("m")){newB = curB.split(1, cond, Integer.valueOf(val2), val3);}
                    else if (val1.equals("a")){newB = curB.split(2, cond, Integer.valueOf(val2), val3);}
                    else {newB = curB.split(3, cond, Integer.valueOf(val2), val3);}
                    if (newB.id.equals("A") && newB.active){pos.add(newB);}
                    if (!newB.id.equals("A") && !newB.id.equals("R") && newB.active){arr.add(newB);}
                    if (!curB.active){break;}
                }
                String valOut = curWF.getLast().get(0);
                if (valOut.equals("A") && curB.active){pos.add(curB);}
                if (!valOut.equals("A") && !valOut.equals("R") && curB.active){curB.id = valOut; arr.add(curB);}
            }
        }
        
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, ArrayList<ArrayList<String>>> hm = new HashMap<>();
        String curLine = sc.nextLine();
        while (!curLine.equals("")) {
            String[] curArr = curLine.split("\\{");
            String curKey = curArr[0];
            String[] nextArr = curArr[1].substring(0, curArr[1].length() - 1).split(",");
            ArrayList<ArrayList<String>> temp = new ArrayList<>();
            for (int i = 0; i < nextArr.length -  1; i++){
                ArrayList<String> subTemp = new ArrayList<String>();
                String curSt = nextArr[i];
                String[] subNextArr = curSt.split(":");
                subTemp.add(subNextArr[0].substring(0, 1));
                subTemp.add(subNextArr[0].substring(1, 2));
                subTemp.add(subNextArr[0].substring(2));
                subTemp.add(subNextArr[1]);
                temp.add(subTemp);
            }
            ArrayList<String> lastTemp = new ArrayList<String>();
            lastTemp.add(nextArr[nextArr.length - 1]);
            temp.add(lastTemp);
            curLine = sc.nextLine();
            hm.put(curKey, temp);
        }
        BigInteger res = new BigInteger("0");
        ArrayList<Bracket> arr = new ArrayList<>();
        ArrayList<Bracket> pos = new ArrayList<>();
        Bracket br = new Bracket();
        br.id = "in";
        arr.add(br);
        runMod(arr, pos, hm);
        for (int i = 0; i < pos.size(); i++){
            res = res.add(pos.get(i).getCombs());
        }
        System.out.println(res);
        sc.close();
    }

}