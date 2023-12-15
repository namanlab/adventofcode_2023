import java.util.*;

public class d14 {

    public static ArrayList<ArrayList<Character>> moveNorth(ArrayList<ArrayList<Character>> arr){
        ArrayList<ArrayList<Character>> cur = transpose(arr);
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < cur.get(i).size(); j++){
                if (cur.get(i).get(j).equals('O')){
                    if (j == 0){continue;}
                    int counterim = 1;
                    while (cur.get(i).get(j - counterim).equals('.')){
                        cur.get(i).set(j - counterim + 1, '.');
                        cur.get(i).set(j - counterim, 'O');
                        counterim++;
                        if (j - counterim < 0){break;}
                    }
                }
            }
        }
        ArrayList<ArrayList<Character>> res = transpose(cur);
        return res;
    }

    public static ArrayList<ArrayList<Character>> moveWest(ArrayList<ArrayList<Character>> arr){
        ArrayList<ArrayList<Character>> cur = deepCopy(arr);
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < cur.get(i).size(); j++){
                if (cur.get(i).get(j).equals('O')){
                    if (j == 0){continue;}
                    int counterim = 1;
                    while (cur.get(i).get(j - counterim).equals('.')){
                        cur.get(i).set(j - counterim + 1, '.');
                        cur.get(i).set(j - counterim, 'O');
                        counterim++;
                        if (j - counterim < 0){break;}
                    }
                }
            }
        }
        return cur;
    }

    public static ArrayList<ArrayList<Character>> cycle(ArrayList<ArrayList<Character>> arr){
        ArrayList<ArrayList<Character>> res = moveNorth(arr);
        res = moveWest(res);
        res = flipVert(res);
        res = moveNorth(res);
        res = flipVert(res);
        res = flipHoriz(res);
        res = moveWest(res);
        res = flipHoriz(res);
        return res;
    }

    public static ArrayList<ArrayList<Character>>  transpose(ArrayList<ArrayList<Character>> arr){
        ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>> ();
        for (int i = 0; i < arr.get(0).size(); i++){
            ArrayList<Character> temp = new ArrayList<Character>();
            res.add(temp);
        }
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(0).size(); j++){
                res.get(j).add(arr.get(i).get(j));
            }
        }
        return res;
    }

    public static ArrayList<ArrayList<Character>>  deepCopy(ArrayList<ArrayList<Character>> arr){
        ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>> ();
        for (int i = 0; i < arr.size(); i++){
            ArrayList<Character> temp = new ArrayList<Character>();
            res.add(temp);
        }
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(0).size(); j++){
                res.get(i).add(arr.get(i).get(j));
            }
        }
        return res;
    }

    public static ArrayList<ArrayList<Character>>  flipHoriz(ArrayList<ArrayList<Character>> arr){
        ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>> ();
        for (int i = 0; i < arr.size(); i++){
            ArrayList<Character> temp = new ArrayList<Character>();
            res.add(temp);
        }
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(0).size(); j++){
                res.get(i).add(arr.get(i).get(arr.get(0).size() - j - 1));
            }
        }
        return res;
    }

    public static ArrayList<ArrayList<Character>>  flipVert(ArrayList<ArrayList<Character>> arr){
        ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>> ();
        for (int i = 0; i < arr.size(); i++){
            ArrayList<Character> temp = new ArrayList<Character>();
            res.add(temp);
        }
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < arr.get(0).size(); j++){
                res.get(i).add(arr.get(arr.size() - i - 1).get(j));
            }
        }
        return res;
    }

    public static long getCL(ArrayList<ArrayList<Character>> arr){
        long res = 0;
        HashMap<ArrayList<ArrayList<Character>>, Long> hm = new HashMap<ArrayList<ArrayList<Character>>, Long>();
        hm.put(arr, res);
        ArrayList<ArrayList<Character>> npats = cycle(arr);
        res++;
        while (!hm.containsKey(npats)){
            hm.put(npats, res);
            res++;
            npats = cycle(npats);
        }
        long CL = res-hm.get(npats);
        long toStart = hm.get(npats);
        long indx = ((1000000000 - toStart) % CL) + toStart;
        ArrayList<ArrayList<Character>> sub = deepCopy(arr);
        for (int j = 0; j < indx; j++){
            sub = cycle(sub);
        }
        return getLoad(sub);
        
    }

    public static long getLoad(ArrayList<ArrayList<Character>> arr){
        long res = 0;
        for (int i = 0; i < arr.size(); i++){
            int mult = (arr.size() - i);
            int temp = 0;
            for (char c: arr.get(i)){
                if (c == 'O'){
                    temp++;
                }
            }
            res += mult*temp;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Character>> pats = new ArrayList<ArrayList<Character>>();
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            char[] curSt = curLine.toCharArray();
            ArrayList<Character> temp = new ArrayList<Character>();
            for (char c: curSt){temp.add(c);}
            pats.add(temp);
        }
        long res = getCL(pats);
        System.out.println(res);
        //long res = getLoad(npats);
        //System.out.println(res);
        sc.close();
    }

}