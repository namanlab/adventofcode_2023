import java.math.BigInteger;
import java.util.*;

abstract class Gate {
    String name;
    ArrayList<String> dests;
    // type = 0: broad, type = 1: FF, type = 2: Conj
    int type;

    public Gate(String name, ArrayList<String> dests) {
        this.name = name;
        this.dests = dests;
    }

    // Abstract method to be implemented by subclasses
    public abstract ArrayList<ArrayList<String>> pass(String src, int signal);

    public abstract void addInput(String s);

    
}

class FF extends Gate {
    int status = 0;

    public FF(String name, ArrayList<String> dests) {
        super(name, dests);
        this.type = 1;
    }

    public void addInput(String s){
    }

    @Override
    public ArrayList<ArrayList<String>> pass(String src, int signal) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if (signal == 1){return res;}
        else {
            if (this.status == 0){
                for (String s: this.dests){
                    ArrayList<String> temp = new ArrayList<String>();
                    temp.add(this.name);
                    temp.add(1 + "");
                    temp.add(s);
                    res.add(temp);
                }
            } else {
                for (String s: this.dests){
                    ArrayList<String> temp = new ArrayList<String>();
                    temp.add(this.name);
                    temp.add(0 + "");
                    temp.add(s);
                    res.add(temp);
                }
            }
            this.status = 1 - this.status;
            return res;
        }
    }

    @Override
    public String toString() {
        return "[" + this.name + ", " + this.status + ", " + this.type + "]";
    }
}

class Conj extends Gate {
    HashMap<String, Integer> status;

    public Conj(String name, ArrayList<String> dests) {
        super(name, dests);
        this.type = 2;
        this.status = new HashMap<>();
    }
    
    public void addInput(String s){
        status.put(s, 0);
    }

    @Override
    public ArrayList<ArrayList<String>> pass(String src, int signal) {
        status.put(src, signal);
        int signalVal;
        if (status.containsValue(0)){signalVal = 1;}
        else {signalVal = 0;}
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        for (String s: this.dests){
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(this.name);
            temp.add(signalVal + "");
            temp.add(s);
            res.add(temp);
        }
        return res;
    }

    @Override
    public String toString() {
        return "[" + this.name + ", " + this.status + ", " + this.type + "]";
    }
}

class Broad extends Gate {
    public Broad(String name, ArrayList<String> dests) {
        super(name, dests);
        this.type = 0;
    }

    public void addInput(String s){
    }

    @Override
    public ArrayList<ArrayList<String>> pass(String src, int signal) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        for (String s: this.dests){
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(this.name);
            temp.add(0 + "");
            temp.add(s);
            res.add(temp);
        }
        return res;
    }

    @Override
    public String toString() {
        return "[" + this.name + ", " + -1 + ", " + this.type + "]";
    }
}



public class d20 {

    // &inv -> a, b
    // HashMap(String: name, Gate: gate vector)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Gate> hm = new HashMap<>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            String[] curArr = curLine.split(" -> ");
            String curName = curArr[0].substring(1);
            String[] destArr = curArr[1].split(", ");
            ArrayList<String> temp = new ArrayList<String>();
            for (String s: destArr){temp.add(s);}
            if (curArr[0].substring(0, 1).equals("%")){
                FF curGate = new FF(curName, temp);
                hm.put(curName, curGate);
            } else if (curArr[0].substring(0, 1).equals("&")){
                Conj curGate = new Conj(curName, temp);
                hm.put(curName, curGate);
            } else {
                Broad curGate = new Broad("b" + curName, temp);
                hm.put("b" + curName, curGate);
            }
        }
        // Update all inputs:
        for (String sg: hm.keySet()){
            Gate g = hm.get(sg);
            for (String subsg: g.dests){
                if (hm.containsKey(subsg)){Gate curB = hm.get(subsg); if (curB.type == 2){curB.addInput(sg);}}
            }
        }
        long lowCount = 0; long highCount = 0;
        for (int j = 0; j < 1000; j++){
            ArrayDeque<ArrayList<String>> que = new ArrayDeque<ArrayList<String>>();
            ArrayList<String> temp = new ArrayList<String>();
            temp.add("button"); temp.add("0"); temp.add("broadcaster");
            que.add(temp);
            while (!que.isEmpty()){
                // System.out.println(que);
                ArrayList<String> curEl = que.removeFirst();
                String parDest = curEl.get(0);
                int signalVal = Integer.valueOf(curEl.get(1));
                String nextDest = curEl.get(2);
                if (signalVal == 0){lowCount++;} else {highCount++;}
                if (hm.containsKey(nextDest)){
                    Gate nextDestKey = hm.get(nextDest);
                    ArrayList<ArrayList<String>> tempRes = nextDestKey.pass(parDest, signalVal);
                    if (!tempRes.isEmpty()){for (ArrayList<String> i: tempRes){que.addLast(i);}}
                }
            }
        }
        BigInteger res = BigInteger.valueOf(lowCount);
        res = res.multiply(BigInteger.valueOf(highCount));
        System.out.println(res);
        
        sc.close();
    }

}