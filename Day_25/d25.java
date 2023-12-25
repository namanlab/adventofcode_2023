import java.util.*;

class Graph {
    // eg. {(0, 0) -> [(0, 1, w1), (), ...]}
    public HashMap<String, HashMap<String, HashSet<Integer>>> arr;
    
    public Graph(){
        this.arr = new HashMap<>();
    }

    public Graph(HashMap<String,  HashMap<String, HashSet<Integer>>> og){
        this.arr = new HashMap<>();
        for (String i: og.keySet()){
            this.arr.put(i, new  HashMap<String, HashSet<Integer>>());
            for (String j: og.get(i).keySet()){
                HashSet<Integer> temp = new HashSet<>();
                temp.addAll(og.get(i).get(j));
                this.arr.get(i).put(j, temp);
            }
        }
    }

    public void addVertex(String ver){
        if (!this.arr.containsKey(ver)){
            this.arr.put(ver, new HashMap<String, HashSet<Integer>>());
        }
    }
    public void addEdge(String stVert, String enVert, int id){
        addVertex(stVert);
        addVertex(enVert);
        if (!this.arr.get(stVert).containsKey(enVert)) {this.arr.get(stVert).put(enVert, new HashSet<Integer>());}
        if (!this.arr.get(enVert).containsKey(stVert)) {this.arr.get(enVert).put(stVert, new HashSet<Integer>());}
        this.arr.get(stVert).get(enVert).add(id);
        this.arr.get(enVert).get(stVert).add(id);
    }

    public void removeEdge(int id){
        for (HashMap<String, HashSet<Integer>> hs: this.arr.values()){
            for (String s: hs.keySet()){
                 if (hs.get(s).contains(id)){hs.get(s).remove(id);}
            }

        }
    }

    public void mergeV(String v1, String v2){
        // System.out.println("Merging: " + v1 + ", " + v2);
        // System.out.println(this.arr);
        HashMap<String, HashSet<Integer>> curHM = this.arr.get(v2);
        for (String s: curHM.keySet()){
            if (!this.arr.get(v1).containsKey(s)) {this.arr.get(v1).put(s, new HashSet<Integer>());}
            this.arr.get(v1).get(s).addAll(curHM.get(s));
        }
        if (this.arr.get(v1).containsKey(v2)){this.arr.get(v1).remove(v2);}
        if (this.arr.get(v1).containsKey(v1)){this.arr.get(v1).remove(v1);}
        this.arr.remove(v2);
        for (HashMap<String, HashSet<Integer>> i: this.arr.values()){
            if (i.containsKey(v2)){
                if (!i.containsKey(v1)) {i.put(v1, new HashSet<Integer>());}
                i.get(v1).addAll(i.get(v2));
                i.remove(v2);
            }
        }
    }
    @Override
    public String toString() {
        return this.arr.toString() + " \nSize: " + this.arr.size();
    }

}





public class d25 {

    public static <K, V> K getRandomKey(Map<K, V> map) {
        Random random = new Random();
        int randomIndex = random.nextInt(map.size());
        Iterator<K> iterator = map.keySet().iterator();
        for (int i = 0; i < randomIndex; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    public static int getAns(Graph g){
        HashSet<String> hs = new HashSet<>();
        String s = getRandomKey(g.arr);
        ArrayDeque<String> que = new ArrayDeque<>();
        que.add(s);
        while (!que.isEmpty()){
            s = que.poll();
            hs.add(s);
            for (String s2: g.arr.get(s).keySet()){
                if (!hs.contains(s2) && !g.arr.get(s).get(s2).isEmpty()){
                    que.offer(s2);
                }
            }
        }
        return hs.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph g = new Graph();
        int eId = 0;
        while (sc.hasNextLine()) {
            String curLine = sc.nextLine();
            String[] curArr = curLine.split(": ");
            for (String i: curArr[1].split(" ")){
                g.addEdge(curArr[0], i, eId);
                eId++;
            }
        }
        System.out.println(g);
        long counter = 0;
        while (true){
            Graph curG = new Graph(g.arr);
            while (curG.arr.size() != 2){
                String v1 = getRandomKey(curG.arr);
                String v2 = getRandomKey(curG.arr);
                while (v1.equals(v2)) {
                    v2 = getRandomKey(curG.arr);
                }
                curG.mergeV(v1, v2);
            }
            String s2 = getRandomKey(curG.arr);
            String s3 = getRandomKey(curG.arr.get(s2));
            if (curG.arr.get(s2).get(s3).size() == 3){
                System.out.println(curG.arr.get(s2).get(s3));
                for (Integer en: curG.arr.get(s2).get(s3)){g.removeEdge(en);}
                break;
            }
            System.out.println(counter + ": " + curG.arr.get(s2).get(s3).size());
            counter++;
        }
        int res = getAns(new Graph(g.arr));
        System.out.println(res);
        System.out.println(res*(g.arr.size() - res));
        sc.close();
    }
    

}