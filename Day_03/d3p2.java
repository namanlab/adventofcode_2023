import java.util.*;

class NumberPair {
    private double number1;
    private double number2;
    private int hashCode;

    // Default constructor
    public NumberPair() {
        this.number1 = 0.0;
        this.number2 = 0.0;
        this.hashCode = Objects.hash(this.number1, this.number2);
    }

    // Parameterized constructor
    public NumberPair(double number1, double number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.hashCode = Objects.hash(this.number1, this.number2);
    }

    // Accessor for number1
    public double getNumber1() {
        return number1;
    }

    // Accessor for number2
    public double getNumber2() {
        return number2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        NumberPair otherPair = (NumberPair) obj;
        return (otherPair.number1 == number1) && (otherPair.number2 == number2);
    }

    @Override
    public String toString() {
        return "{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}


public class d3p2 {

    public static void checkpartnum(ArrayList<List<Character>> arr, List<Integer> pos, int rownum, int cur_num, HashMap< NumberPair, List<Integer>> hm){
        NumberPair curP;
        for (int i: pos){
            if (rownum > 0){
                if (arr.get(rownum - 1).get(i) == '*'){
                    curP = new NumberPair(rownum - 1, i);
                    if (!hm.containsKey(curP)){
                        hm.put(curP, new ArrayList<Integer>());
                    } 
                    hm.get(curP).add(cur_num);
                }
            }
            if (rownum < arr.size() - 1){
                if ((arr.get(rownum + 1).get(i)) == '*'){
                    curP = new NumberPair(rownum + 1, i);
                    if (!hm.containsKey(curP)){
                        hm.put(curP, new ArrayList<Integer>());
                    } 
                    hm.get(curP).add(cur_num);
                }
            }
        }
        if (pos.get(0) > 0){
            if ((arr.get(rownum).get(pos.get(0) - 1)) == '*'){
                curP = new NumberPair(rownum, pos.get(0) - 1);
                if (!hm.containsKey(curP)){
                    hm.put(curP, new ArrayList<Integer>());
                } 
                hm.get(curP).add(cur_num);
            }
            if (rownum > 0){
                if (arr.get(rownum - 1).get(pos.get(0) - 1) == '*'){
                    curP = new NumberPair(rownum - 1, pos.get(0) - 1);
                    if (!hm.containsKey(curP)){
                        hm.put(curP, new ArrayList<Integer>());
                    } 
                    hm.get(curP).add(cur_num);
                }
            }
            if (rownum < arr.size() - 1){
                if ((arr.get(rownum + 1).get(pos.get(0) - 1)) == '*'){
                    curP = new NumberPair(rownum + 1, pos.get(0) - 1);
                    if (!hm.containsKey(curP)){
                        hm.put(curP, new ArrayList<Integer>());
                    } 
                    hm.get(curP).add(cur_num);
                }
            }
        }
        if (pos.get(pos.size() - 1) < arr.get(0).size() - 1){
            if ((arr.get(rownum).get(pos.get(pos.size() - 1) + 1)) == '*'){
                curP = new NumberPair(rownum, pos.get(pos.size() - 1) + 1);
                if (!hm.containsKey(curP)){
                    hm.put(curP, new ArrayList<Integer>());
                } 
                hm.get(curP).add(cur_num);
            }
            if (rownum > 0){
                if ((arr.get(rownum - 1).get(pos.get(pos.size() - 1) + 1)) == '*'){
                    curP = new NumberPair(rownum - 1, pos.get(pos.size() - 1) + 1);
                    if (!hm.containsKey(curP)){
                        hm.put(curP, new ArrayList<Integer>());
                    } 
                    hm.get(curP).add(cur_num);
                }
            }
            if (rownum < arr.size() - 1){
                if ((arr.get(rownum + 1).get(pos.get(pos.size() - 1) + 1)) == '*'){
                    curP = new NumberPair(rownum + 1, pos.get(pos.size() - 1) + 1);
                    if (!hm.containsKey(curP)){
                        hm.put(curP, new ArrayList<Integer>());
                    } 
                    hm.get(curP).add(cur_num);
                }
            }
        }
    };

    public static void main(String[] args) {
        HashSet<Character> hs = new HashSet<Character>();
        hs.add('1');
        hs.add('2');
        hs.add('3');
        hs.add('4');
        hs.add('5');
        hs.add('6');
        hs.add('7');
        hs.add('8');
        hs.add('9');
        hs.add('0');
        hs.add('.');
        int res = 0;
        HashMap< NumberPair, List<Integer>> hm = new HashMap< NumberPair, List<Integer>>();
        ArrayList<List<Character>> finarr = new ArrayList<List<Character>>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String curLine = sc.nextLine();
            List<Character> temp = new ArrayList<Character>();
            for (char c : curLine.toCharArray()) {
                temp.add(c);
            }
            finarr.add(temp);
        }
        for (int i = 0; i < finarr.size(); i++){
            int j = 0;
            while (j < finarr.get(0).size()){
                char curChar = finarr.get(i).get(j);
                if (hs.contains(curChar) && curChar != '.'){
                    List<Character> cur_temp = new ArrayList<Character>();
                    List<Integer> pos = new ArrayList<Integer>();
                    while (hs.contains(curChar) && curChar != '.' && j < finarr.get(0).size()){
                        cur_temp.add(curChar);
                        pos.add(j);
                        j++;
                        if (j < finarr.get(0).size()){curChar = finarr.get(i).get(j);}
                    }
                    StringBuilder builder = new StringBuilder(cur_temp.size());
                    for(Character ch: cur_temp){
                        builder.append(ch);
                    }
                    checkpartnum(finarr, pos, i, Integer.valueOf(builder.toString()), hm);
                }
                j++;
            }
        }
        for (Map.Entry< NumberPair, List<Integer>> entry : hm.entrySet()) {
            List<Integer> cur_value = entry.getValue();
            if (cur_value.size() == 2){
                res += cur_value.get(0) * cur_value.get(1);
            }
        }
        System.out.println(res);
        sc.close();
    }

}