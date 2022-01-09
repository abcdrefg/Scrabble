package wordFinder;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LettersInBox {
    private  HashMap<String,Integer> letterWithQty = new HashMap<String ,Integer>();
    private ArrayList<String> letters = new ArrayList<>();

    public LettersInBox() {
        letterWithQty.put("a", 9);
        letterWithQty.put("e", 7);
        letterWithQty.put("i", 8);
        letterWithQty.put("n", 5);
        letterWithQty.put("o", 6);
        letterWithQty.put("r", 4);
        letterWithQty.put("s", 4);
        letterWithQty.put("w", 4);
        letterWithQty.put("z", 5);
        letterWithQty.put("c", 3);
        letterWithQty.put("d", 3);
        letterWithQty.put("k", 3);
        letterWithQty.put("l", 3);
        letterWithQty.put("m", 3);
        letterWithQty.put("p", 3);
        letterWithQty.put("t", 3);
        letterWithQty.put("y", 4);
        letterWithQty.put("b", 2);
        letterWithQty.put("g", 2);
        letterWithQty.put("h", 2);
        letterWithQty.put("j", 2);
        letterWithQty.put("ł", 2);
        letterWithQty.put("u", 2);
        letterWithQty.put("ą", 1);
        letterWithQty.put("ę", 1);
        letterWithQty.put("f", 1);
        letterWithQty.put("ó", 1);
        letterWithQty.put("ś", 1);
        letterWithQty.put("ż", 1);
        letterWithQty.put("ć", 1);
        letterWithQty.put("ń", 1);
        letterWithQty.put("ź", 1);

        for(int i = 0; i < 9; i++) {
            letters.add("a");
        }
        for(int i = 0; i < 2; i++) {
            letters.add("b");
        }
        for(int i = 0; i < 3; i++) {
            letters.add("c");
        }
        for(int i = 0; i < 3; i++) {
            letters.add("d");
        }
        for(int i = 0; i < 7; i++) {
            letters.add("e");
        }
        letters.add("f");
        for(int i = 0; i < 2; i++) {
            letters.add("g");
        }
        for(int i = 0; i < 2; i++) {
            letters.add("h");
        }
        for(int i = 0; i < 8; i++) {
            letters.add("i");
        }
        for(int i = 0; i < 2; i++) {
            letters.add("j");
        }
        for(int i = 0; i < 3; i++) {
            letters.add("k");
        }
        for(int i = 0; i < 3; i++) {
            letters.add("l");
        }
        for(int i = 0; i < 3; i++) {
            letters.add("m");
        }
        for(int i = 0; i < 5; i++) {
            letters.add("n");
        }
        for(int i = 0; i < 6; i++) {
            letters.add("o");
        }
        for(int i = 0; i < 3; i++) {
            letters.add("p");
        }
        for(int i = 0; i < 4; i++) {
            letters.add("r");
        }
        for(int i = 0; i < 4; i++) {
            letters.add("s");
        }
        for(int i = 0; i < 3; i++) {
            letters.add("t");
        }
        for(int i = 0; i < 2; i++) {
            letters.add("u");
        }
        for(int i = 0; i < 4; i++) {
            letters.add("w");
        }
        for(int i = 0; i < 5; i++) {
            letters.add("z");
        }
        letters.add("ą");
        letters.add("ć");
        letters.add("ę");
        letters.add("ń");
        letters.add("ź");
        letters.add("ż");
        letters.add("ś");
        letters.add("ó");
        for(int i = 0; i < 2; i++) {
            letters.add("ł");
        }
        for(int i = 0; i < 4; i++) {
            letters.add("y");
        }




    }

    public String getRandomLetterFromBox(){
        Random rand = new Random();
        String b = "";
        while (true) {
            int a = rand.nextInt(letters.size());
            b = letters.get(a);
            if (letterWithQty.get(b) > 0){
                letterWithQty.replace(b, letterWithQty.get(b) - 1);
                break;
            }
        }
        //System.out.println(b);
        return b;
    }

    public int howManyLeft(){
        int max = 0;
        String b = "";
        for(int i = 0; i <  letters.size(); i++ ){
            b = letters.get(i);
            max = max + letterWithQty.get(b);
        }
        return max;
    }

    public void putLetterInBox(String letter){
        letterWithQty.replace(letter, letterWithQty.get(letter) + 1);
    }

    public HashMap<String, Integer> getLetterWithQty() {
        return letterWithQty;
    }

    public static void main(String[] args) {
        LettersInBox lettersInBox = new LettersInBox();

        for (int i = 0; i < 98; i++) {
            String s = lettersInBox.getRandomLetterFromBox();
        }
    }
}
