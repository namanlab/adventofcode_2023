import java.util.*;

class CardHand {
    public int[] cardArr = new int[6];
    public int score;

    public int getScore(){
        return this.score;
    }

    // Default constructor
    public CardHand() {
        Arrays.fill(cardArr, -1);
        score = 0;
    }

    // Parameterized constructor
    public CardHand(int[] intArr, int score) {
        this.score = score;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 1; i < 6; i++){
            cardArr[i] = intArr[i - 1];
            if (!hm.containsKey(intArr[i - 1])){
                hm.put(intArr[i - 1], 1);
            } else {
                hm.put(intArr[i - 1], hm.get(intArr[i - 1]) + 1);
            }
        }
        if (!hm.containsKey(1)) {hm.put(1, 0);}
        HashMap<Integer, Integer> copyHm = new HashMap<Integer,Integer>(hm);
        copyHm.remove(1);
        List<Integer> valueList = new ArrayList<>(copyHm.values());
        if (valueList.size() == 0){
            cardArr[0] = 7;
            return;
        }
        if (valueList.contains(5) || (Collections.max(valueList) + hm.get(1) == 5)){
            cardArr[0] = 7;
            return;
        }
        if (valueList.contains(4)  || (Collections.max(valueList) + hm.get(1) == 4)){
            cardArr[0] = 6;
            return;
        }
        if ((valueList.contains(3) && valueList.contains(2)) || (valueList.contains(3) && hm.get(1) == 1) ||
        (valueList.contains(2) && hm.get(1) == 1 && valueList.size() == 2) ){
            cardArr[0] = 5;
            return;
        }
        if ((valueList.contains(3)) || (hm.get(1) == 2) || (valueList.contains(2) && hm.get(1) == 1) ){
            cardArr[0] = 4;
            return;
        }
        if (valueList.contains(2) && valueList.size() == 3){
            cardArr[0] = 3;
            return;
        }
        if (valueList.contains(2) || (hm.get(1) == 1)){
            cardArr[0] = 2;
            return;
        }
        cardArr[0] = 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CardHand [");
        for (int i = 0; i < cardArr.length; i++) {
            sb.append(cardArr[i]);
            if (i < cardArr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        sb.append("Score: ");
        sb.append(this.score);
        sb.append(" ");
        return sb.toString();
    }

}

class CardHandComparator implements Comparator<CardHand> {
    @Override
    public int compare(CardHand hand1, CardHand hand2) {
        for (int i = 0; i < hand1.cardArr.length; i++) {
            if (hand1.cardArr[i] < hand2.cardArr[i]) {
                return -1;
            } else if (hand1.cardArr[i] > hand2.cardArr[i]) {
                return 1;
            }
        }
        return 0;
    }
}

public class d7p2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Character, Integer> hmCards = new HashMap<Character, Integer>();
        hmCards.put('J', 1);
        hmCards.put('2', 2);
        hmCards.put('3', 3);
        hmCards.put('4', 4);
        hmCards.put('5', 5);
        hmCards.put('6', 6);
        hmCards.put('7', 7);
        hmCards.put('8', 8);
        hmCards.put('9', 9);
        hmCards.put('T', 10);
        hmCards.put('Q', 11);
        hmCards.put('K', 12);
        hmCards.put('A', 13);
        ArrayList<CardHand> allCards = new ArrayList<CardHand>();
        while (sc.hasNext()){
            String curCard = sc.next();
            int cardVal = sc.nextInt();
            char[] cardArrChar = curCard.toCharArray();
            int[] cardArrInt = new int[5];
            for (int i = 0; i < 5; i++){
                cardArrInt[i] = hmCards.get(cardArrChar[i]);
            }
            CardHand curHand = new CardHand(cardArrInt, cardVal);
            allCards.add(curHand);
        }
        CardHandComparator comparator = new CardHandComparator();
        Collections.sort(allCards, comparator);
        long res = 0;
        for (int i = 1; i <= allCards.size(); i++){
            res += i*allCards.get(i - 1).getScore();
        }
        System.out.println(res);
        sc.close();
    }

}