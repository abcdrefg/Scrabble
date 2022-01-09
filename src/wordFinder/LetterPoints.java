package wordFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LetterPoints {
    private final HashMap<String,Integer> letterWithPoints = new HashMap<String ,Integer>();
    private ArrayList<String> Dictionary;

    public LetterPoints(){
        letterWithPoints.put("a", 1);
        letterWithPoints.put("e", 1);
        letterWithPoints.put("i", 1);
        letterWithPoints.put("n", 1);
        letterWithPoints.put("o", 1);
        letterWithPoints.put("r", 1);
        letterWithPoints.put("s", 1);
        letterWithPoints.put("w", 1);
        letterWithPoints.put("z", 1);
        letterWithPoints.put("c", 2);
        letterWithPoints.put("d", 2);
        letterWithPoints.put("k", 2);
        letterWithPoints.put("l", 2);
        letterWithPoints.put("m", 2);
        letterWithPoints.put("p", 2);
        letterWithPoints.put("t", 2);
        letterWithPoints.put("y", 2);
        letterWithPoints.put("b", 3);
        letterWithPoints.put("g", 3);
        letterWithPoints.put("h", 3);
        letterWithPoints.put("j", 3);
        letterWithPoints.put("ł", 3);
        letterWithPoints.put("u", 3);
        letterWithPoints.put("ą", 5);
        letterWithPoints.put("ę", 5);
        letterWithPoints.put("f", 5);
        letterWithPoints.put("ó", 5);
        letterWithPoints.put("ś", 5);
        letterWithPoints.put("ż", 5);
        letterWithPoints.put("ć", 6);
        letterWithPoints.put("ń", 7);
        letterWithPoints.put("ź", 9);
        letterWithPoints.put(" ", 0);

        letterWithPoints.put("x", 1);
        letterWithPoints.put("v", 1);

        BufferedReader fileReader = null;
        String filePath = "static\\SJP.txt";
        try{
            fileReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            Dictionary = new ArrayList<>();
            while ((line = fileReader.readLine()) != null) {
                Dictionary.add(line);
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public HashMap<String, Integer> getLetterWithPoints() {
        return letterWithPoints;
    }

    public ArrayList<String> getDictionary() {
        return Dictionary;
    }

    public static void main(String[] args) {
        LetterPoints letterPoints = new LetterPoints();
        System.out.print(letterPoints.getDictionary());
        System.out.print(letterPoints.getLetterWithPoints());
    }
}

