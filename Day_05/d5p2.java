import java.util.*;

class NumberPair {
    private long number1;
    private long number2;
    private int hashCode;

    // Default constructor
    public NumberPair() {
        this.number1 = 0;
        this.number2 = 0;
        this.hashCode = Objects.hash(this.number1, this.number2);
    }

    // Parameterized constructor
    public NumberPair(long number1, long number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.hashCode = Objects.hash(this.number1, this.number2);
    }

    // Accessor for number1
    public long st() {
        return number1;
    }

    // Accessor for number2
    public long en() {
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


public class d5p2 {

    public static ArrayList<NumberPair> getNewArr(ArrayList<NumberPair> curarr, ArrayList<ArrayList<Long>> curMainArr){
        ArrayList<NumberPair> temp_arr = new ArrayList<NumberPair>();
        for (int k = 0; k < curarr.size(); k++){
            ArrayList<NumberPair> affected_arr = new ArrayList<NumberPair>();
            NumberPair curPr = curarr.get(k);
            for (int j = 0; j < curMainArr.size(); j++){
                long dest = curMainArr.get(j).get(0);
                long src = curMainArr.get(j).get(1);
                long range = curMainArr.get(j).get(2);
                if ((curPr.en() < src) || (curPr.st() >= src + range)){
                    continue;
                } else {
                    NumberPair tempPr = new NumberPair(Math.max(curPr.st(), src) - src + dest, Math.min(curPr.en(), src + range)  - src + dest);
                    NumberPair covtempPr = new NumberPair(Math.max(curPr.st(), src), Math.min(curPr.en(), src + range));
                    affected_arr.add(covtempPr);
                    temp_arr.add(tempPr);
                }
            }
            Collections.sort(affected_arr, new Comparator<NumberPair>() {
                @Override
                public int compare(NumberPair o1, NumberPair o2) {
                    // Compare based on the second element (index 1) of the subarrays
                    if (o1.st() > o2.st()){return 1;}
                    if (o1.st() == o2.st()){return 0;}
                    return -1;
                }
            });
            long mainSt = curPr.st();
            long mainEn = curPr.en();
            long int_st, int_en = mainSt;
            for (int r = 0; r < affected_arr.size(); r++){
                int_st = affected_arr.get(r).st();
                int_en = affected_arr.get(r).en();
                if (int_st - mainSt >= 1){
                    temp_arr.add(new NumberPair(mainSt, int_st));
                }
                mainSt = int_en;
            }
            if (mainEn - int_en >= 1){
                temp_arr.add(new NumberPair(int_en, mainEn));
            }
        }
        return temp_arr; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.next();
        String nextTok = sc.next();
        ArrayList<ArrayList<NumberPair>> seeds = new ArrayList<ArrayList<NumberPair>>();
        int seed_counter = 0;
        while (sc.hasNext() && !nextTok.contains("-to-")){
            seeds.add(new ArrayList<NumberPair>());
            long st1 = Long.valueOf(nextTok);
            nextTok = sc.next();
            long en1 = Long.valueOf(nextTok);
            seeds.get(seed_counter).add(new NumberPair(st1, st1 + en1));
            nextTok = sc.next();
            seed_counter++;
        }
        while (sc.hasNext()){
            nextTok = sc.next();
            nextTok = sc.next();
            ArrayList<ArrayList<Long>> curMainArr = new ArrayList<ArrayList<Long>>();
            while (!nextTok.contains("-to-") && sc.hasNext()) {
                ArrayList<Long> cur_temp_arr = new ArrayList<Long>();
                cur_temp_arr.add(Long.valueOf(nextTok));
                nextTok = sc.next();
                cur_temp_arr.add(Long.valueOf(nextTok));
                nextTok = sc.next();
                cur_temp_arr.add(Long.valueOf(nextTok));
                curMainArr.add(cur_temp_arr);
                if (sc.hasNext()){
                    nextTok = sc.next();
                }
            }
            // System.out.println(String.format("nextTok %s curMainArr %s", nextTok, curMainArr));
            for (int i = 0; i < seeds.size(); i++) {
                ArrayList<NumberPair> curarr = seeds.get(i);
                // System.out.println(String.format("curarr %s", curarr));
                ArrayList<NumberPair> temp_arr = getNewArr(curarr, curMainArr);
                // System.out.println(String.format("temp_arr %s", temp_arr));
                seeds.set(i, temp_arr);
            }
        }
        // System.out.println(seeds);
        long res = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i++){
            for (int j = 0; j < seeds.get(i).size(); j++){
                if (seeds.get(i).get(j).st() < res){
                    res = seeds.get(i).get(j).st();
                }
            }
        }
        System.out.println(res);
        sc.close();
    }

}