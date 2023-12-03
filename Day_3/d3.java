import java.util.*;

public class d3 {

    public static boolean checkpartnum(ArrayList<List<Character>> arr, List<Integer> pos, int rownum, HashSet<Character> hs){
        
        boolean ispart = false;
        for (int i: pos){
            if (rownum > 0){
                if (!hs.contains(arr.get(rownum - 1).get(i))){
                    ispart = true;
                    break;
                }
            }
            if (rownum < arr.size() - 1){
                if (!hs.contains(arr.get(rownum + 1).get(i))){
                    ispart = true;
                    break;
                }
            }
        }
        if (pos.get(0) > 0){
            if (!hs.contains(arr.get(rownum).get(pos.get(0) - 1))){
                ispart = true;
            }
            if (rownum > 0){
                if (!hs.contains(arr.get(rownum - 1).get(pos.get(0) - 1))){
                    ispart = true;
                }
            }
            if (rownum < arr.size() - 1){
                if (!hs.contains(arr.get(rownum + 1).get(pos.get(0) - 1))){
                    ispart = true;
                }
            }
        }
        if (pos.get(pos.size() - 1) < arr.get(0).size() - 1){
            if (!hs.contains(arr.get(rownum).get(pos.get(pos.size() - 1) + 1))){
                ispart = true;
            }
            if (rownum > 0){
                if (!hs.contains(arr.get(rownum - 1).get(pos.get(pos.size() - 1) + 1))){
                    ispart = true;
                }
            }
            if (rownum < arr.size() - 1){
                if (!hs.contains(arr.get(rownum + 1).get(pos.get(pos.size() - 1) + 1))){
                    ispart = true;
                }
            }
        }
        return ispart;
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
                    if (checkpartnum(finarr, pos, i, hs)){
                        StringBuilder builder = new StringBuilder(cur_temp.size());
                        for(Character ch: cur_temp){
                            builder.append(ch);
                        }
                        res += Integer.valueOf(builder.toString());
                    }
                }
                j++;
            }
        }
        System.out.println(res);
        sc.close();
    }

}