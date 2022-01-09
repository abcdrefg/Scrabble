package aiPlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class AIPlayer {
    private ArrayList<String> avaibleLetters;
    private int roundsWithoutMove;
    private int points;
    private ArrayList<String> dictionary;
    private String[][] pointsTable;

    public AIPlayer(String[][] pointsTable) {
        BufferedReader fileReader = null;
        String filePath = "static\\SJP.txt";
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            dictionary = new ArrayList<>();
            avaibleLetters = new ArrayList<>();
            while ((line = fileReader.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        this.pointsTable = pointsTable;
        roundsWithoutMove = 0;
        points = 0;
    }

    public int getRoundsWithoutMove() {
        return roundsWithoutMove;
    }

    public TreeMap<Integer, String> MakeMove(String[][] ScrabbleMap) {
        TreeMap<Integer, String> answerMap = new TreeMap<>();
        String word;
        Boolean isWordAvaible;
        if (ScrabbleMap[7][7] == "o") {
            for (int i = 0; i < dictionary.size(); i++) {
                word = dictionary.get(i);
                ArrayList<String> avaibleLettersCopy = (ArrayList<String>) avaibleLetters.clone();
                isWordAvaible = checkWord(avaibleLettersCopy, word);
                if (isWordAvaible == true){
                    answerMap = countPointsAndPutIntoMap(answerMap, word);
                }
            }
        }
        return answerMap;
    }
    public boolean checkWord(ArrayList<String> avaibleLettersCopy, String Word){
        boolean isWordAvaible = true;
        for (int j = 0; j < Word.length(); j++) {
            if (avaibleLettersCopy.indexOf(String.valueOf(Word.charAt(j))) == -1) {
                isWordAvaible = false;
                break;
            } else {
                avaibleLettersCopy.remove(String.valueOf(Word.charAt(j)));
            }
        }
        return isWordAvaible;
    }
    public TreeMap<Integer, String> countPointsAndPutIntoMap(TreeMap<Integer, String> answerMap, String word){
        int pointSum = 0;
        for (int j = 0; j < pointsTable.length; j++) {
            if (word.indexOf(pointsTable[j][0]) != -1) {
                int letterCount = 0;
                for (int k = 0; k < word.length(); k++) {
                    if (word.charAt(k) == pointsTable[j][0].charAt(0)) {
                        letterCount = letterCount + 1;
                    }
                }
                pointSum = pointSum + letterCount * Integer.parseInt(pointsTable[j][1]);
            }
        }
        if (answerMap.containsKey(pointSum) == false) {
            answerMap.put(pointSum, word);
        }
        return answerMap;
    }

    public void SetLetters(String[] Letters) {
        for (int i = 0; i < Letters.length; i++) {
            avaibleLetters.add(Letters[i]);
        }
    }
}
