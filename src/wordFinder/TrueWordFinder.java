package wordFinder;

import ScrabbleBoard.Board;
import ScrabbleBoard.Field;
import fieldFinder.HorizontalFieldsFinder;
import fieldFinder.VerticalFieldsFinder;
import fieldFinder.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TrueWordFinder {
    private List<List<Word>> listWithAllPossibleFields;
    private HashMap<Integer, PossibleWords> listWithAllWordsWithPoints = new HashMap<Integer, PossibleWords>();

    public TrueWordFinder(List<List<Word>> listWithAllPossibleFields){
        this.listWithAllPossibleFields = listWithAllPossibleFields;

    }

    public void FindAllWorlds(ArrayList<String> AvaibleLetters, Board board){
        FindAllWorldsFirstType(AvaibleLetters,board);
        FindAllWorldsSecondType(AvaibleLetters, board);
    }

    public void FindAllWorldsFirstType(ArrayList<String> AvaibleLetters, Board board){
        //word to aaraylista fieldow
        List<Word> listWithCord = new ArrayList<>();
        listWithCord = listWithAllPossibleFields.get(0);
        LetterPoints letterPoints = new LetterPoints();
        HashMap<String,Integer> letterWithPoints = letterPoints.getLetterWithPoints();
        ArrayList<String> Dictionary = letterPoints.getDictionary();
        for(int i=0; i < listWithCord.size(); i++) {
            //biore pierwsza pole i ostatnia
            int finded = 0;
            for (int j = 0; j < listWithCord.get(i).getListOfFields().size(); j++){
                if(!listWithCord.get(i).getListOfFields().get(j).isEmpty()){
                    finded = finded + 1;
                }
            }
            if (finded > 1){
                continue;
            }
            Field First = listWithCord.get(i).getListOfFields().get(0);
            Field Last = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1);
            //int xLast = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1).getCordx();
            //int yLast = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1).getCordy();

            //poziomo
            if(First.getCordx() == Last.getCordx()){
                int maxLength = Last.getCordy() - First.getCordy() + 1;
                AvaibleLetters.add(Last.getLetter().toLowerCase(Locale.ROOT));
                //szukamy w slowniku
                for(int j=0; j < Dictionary.size(); j++) {
                    //bierzemy slowo ze slownika
                    String wordFromDictionary = Dictionary.get(j);
                    if (wordFromDictionary.length() > maxLength){
                        continue;
                    }
                    //sprawdzamy czy mozemy je ulozyc
                    ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
                    boolean isWordAvaible = true;
                    for(int k = 0; k < wordFromDictionary.length(); k++) {
                        if (!Character.toString(wordFromDictionary.charAt(wordFromDictionary.length() - 1)).equals(AvaibleLetters.get(AvaibleLetters.size() - 1))) {
                            isWordAvaible = false;
                            break;
                        }
                        if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                            isWordAvaible = false;
                            break;
                        } else {
                            AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                        }
                    }
                    //liczymy punkty
                    int points = 0;
                    int bonus = 1;
                    int n = wordFromDictionary.length() - 1;
                    if (isWordAvaible){
                        for(int m = Last.getCordy(); m >= First.getCordy(); m--) {
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 0) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 1) {
                                points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 2) {
                                points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 3) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 2;
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 4) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 3;
                            }
                            n = n - 1;
                            if (n < 0) {
                                break;
                            }
                        }

                        points = points * bonus;
                        if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible){
                            listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[Last.getCordx()][Last.getCordy() - wordFromDictionary.length() + 1], board.getBoard()[Last.getCordx()][Last.getCordy()], wordFromDictionary));
                            //System.out.println(AvaibleLetters.get(AvaibleLetters.size() - 1));
                            //System.out.println(wordFromDictionary);
                        }
                    }

                }
            }

            //pionowo
            if(First.getCordy() == Last.getCordy()){
                int maxLength = Last.getCordx() - First.getCordx() + 1;
                AvaibleLetters.add(Last.getLetter().toLowerCase(Locale.ROOT));
                //szukamy w slowniku
                for(int j=0; j < Dictionary.size(); j++) {
                    //bierzemy slowo ze slownika
                    String wordFromDictionary = Dictionary.get(j);
                    if (wordFromDictionary.length() > maxLength){
                        continue;
                    }
                    //sprawdzamy czy mozemy je ulozyc
                    ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
                    boolean isWordAvaible = true;
                    for(int k = 0; k < wordFromDictionary.length(); k++) {
                        if (!Character.toString(wordFromDictionary.charAt(wordFromDictionary.length() - 1)).equals(AvaibleLetters.get(AvaibleLetters.size() - 1))) {
                            isWordAvaible = false;
                            break;
                        }
                        if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                            isWordAvaible = false;
                            break;
                        } else {
                            AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                        }
                    }
                    //liczymy punkty
                    int points = 0;
                    int bonus = 1;
                    int n = wordFromDictionary.length() - 1;
                    if (isWordAvaible){
                        for(int m = Last.getCordx(); m >= First.getCordx(); m--) {
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 0) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 1) {
                                points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 2) {
                                points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 3) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 2;
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 4) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 3;
                            }
                            n = n - 1;
                            if (n < 0) {
                                break;
                            }
                        }

                        points = points * bonus;
                        if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible){
                            listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[Last.getCordx() - wordFromDictionary.length() + 1][First.getCordy()], board.getBoard()[Last.getCordx()][Last.getCordy()], wordFromDictionary));
                            //System.out.println(AvaibleLetters.get(AvaibleLetters.size() - 1));
                            //System.out.println(wordFromDictionary);
                        }
                    }

                }
            }
            AvaibleLetters.remove(AvaibleLetters.size()-1);
        }
    }

    public void FindAllWorldsSecondType(ArrayList<String> AvaibleLetters, Board board){
        //word to aaraylista fieldow
        List<Word> listWithCord = new ArrayList<>();
        listWithCord = listWithAllPossibleFields.get(1);
        LetterPoints letterPoints = new LetterPoints();
        HashMap<String,Integer> letterWithPoints = letterPoints.getLetterWithPoints();
        ArrayList<String> Dictionary = letterPoints.getDictionary();
        for(int i=0; i < listWithCord.size(); i++) {
            //biore pierwsza pole i ostatnia
            int finded = 0;
            for (int j = 0; j < listWithCord.get(i).getListOfFields().size(); j++){
                if(!listWithCord.get(i).getListOfFields().get(j).isEmpty()){
                    finded = finded + 1;
                }
            }
            if (finded > 1){
                continue;
            }
            Field First = listWithCord.get(i).getListOfFields().get(0);
            Field Last = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1);
            //int xLast = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1).getCordx();
            //int yLast = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1).getCordy();

            //poziomo
            if(First.getCordx() == Last.getCordx()){
                int maxLength = Last.getCordy() - First.getCordy() + 1;
                AvaibleLetters.add(First.getLetter().toLowerCase(Locale.ROOT));
                //szukamy w slowniku
                for(int j=0; j < Dictionary.size(); j++) {
                    //bierzemy slowo ze slownika
                    String wordFromDictionary = Dictionary.get(j);
                    if (wordFromDictionary.length() > maxLength){
                        continue;
                    }
                    //sprawdzamy czy mozemy je ulozyc
                    ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
                    boolean isWordAvaible = true;
                    for(int k = 0; k < wordFromDictionary.length(); k++) {
                        if (!Character.toString(wordFromDictionary.charAt(0)).equals(AvaibleLetters.get(AvaibleLetters.size() - 1))) {
                            isWordAvaible = false;
                            break;
                        }
                        if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                            isWordAvaible = false;
                            break;
                        } else {
                            AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                        }
                    }
                    //liczymy punkty
                    int points = 0;
                    int bonus = 1;
                    int n = 0;
                    if (isWordAvaible){
                        for(int m = First.getCordy(); m <= Last.getCordy(); m++) {
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 0) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 1) {
                                points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 2) {
                                points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 3) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 2;
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 4) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 3;
                            }
                            n = n + 1;
                            if (n > wordFromDictionary.length() - 1) {
                                break;
                            }
                        }

                        points = points * bonus;
                        if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible){
                            listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[First.getCordx()][First.getCordy()], board.getBoard()[First.getCordx()][First.getCordy() + wordFromDictionary.length() -1], wordFromDictionary));
                            //System.out.println(AvaibleLetters.get(AvaibleLetters.size() - 1));
                            //System.out.println(wordFromDictionary);
                        }
                    }

                }
            }

            //pionowo
            if(First.getCordy() == Last.getCordy()){
                int maxLength = Last.getCordx() - First.getCordx() + 1;
                AvaibleLetters.add(First.getLetter().toLowerCase(Locale.ROOT));
                //szukamy w slowniku
                for(int j=0; j < Dictionary.size(); j++) {
                    //bierzemy slowo ze slownika
                    String wordFromDictionary = Dictionary.get(j);
                    if (wordFromDictionary.length() > maxLength){
                        continue;
                    }
                    //sprawdzamy czy mozemy je ulozyc
                    ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
                    boolean isWordAvaible = true;
                    for(int k = 0; k < wordFromDictionary.length(); k++) {
                        if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                            isWordAvaible = false;
                            break;
                        } else {
                            AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                        }
                        if (!Character.toString(wordFromDictionary.charAt(0)).equals(AvaibleLetters.get(AvaibleLetters.size() - 1))) {
                            isWordAvaible = false;
                            break;
                        }
                    }
                    //liczymy punkty
                    int points = 0;
                    int bonus = 1;
                    int n = 0;
                    if (isWordAvaible){
                        for(int m = First.getCordx(); m <= Last.getCordx(); m++) {
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 0) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 1) {
                                points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 2) {
                                points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 3) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 2;
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 4) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 3;
                            }
                            n = n + 1;
                            if (n > wordFromDictionary.length() - 1) {
                                break;
                            }
                        }

                        points = points * bonus;
                        if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible){
                            listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[First.getCordx()][First.getCordy()], board.getBoard()[First.getCordx() + wordFromDictionary.length() - 1][Last.getCordy()], wordFromDictionary));
                            //System.out.println(AvaibleLetters.get(AvaibleLetters.size() - 1));
                            //System.out.println(wordFromDictionary);
                        }
                    }

                }
            }
            AvaibleLetters.remove(AvaibleLetters.size()-1);
        }

    }

    public void FindAllWorldsThirdType(ArrayList<String> AvaibleLetters, Board board){
        //word to aaraylista fieldow
        List<Word> listWithCord = new ArrayList<>();
        listWithCord = listWithAllPossibleFields.get(1);
        LetterPoints letterPoints = new LetterPoints();
        HashMap<String,Integer> letterWithPoints = letterPoints.getLetterWithPoints();
        ArrayList<String> Dictionary = letterPoints.getDictionary();
        for(int i=0; i < listWithCord.size(); i++) {
            //biore pierwsza pole i ostatnia
            int finded = 0;
            for (int j = 0; j < listWithCord.get(i).getListOfFields().size(); j++){
                if(!listWithCord.get(i).getListOfFields().get(j).isEmpty()){
                    finded = finded + 1;
                }
            }
            if (finded > 1){
                continue;
            }
            Field First = listWithCord.get(i).getListOfFields().get(0);
            Field Last = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1);
            //int xLast = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1).getCordx();
            //int yLast = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1).getCordy();

            //poziomo
            if(First.getCordx() == Last.getCordx()){
                int maxLength = Last.getCordy() - First.getCordy();
                AvaibleLetters.add(First.getLetter().toLowerCase(Locale.ROOT));
                //szukamy w slowniku
                for(int j=0; j < Dictionary.size(); j++) {
                    //bierzemy slowo ze slownika
                    String wordFromDictionary = Dictionary.get(j);
                    if (wordFromDictionary.length() > maxLength){
                        continue;
                    }
                    //sprawdzamy czy mozemy je ulozyc
                    ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
                    boolean isWordAvaible = true;
                    for(int k = 0; k < wordFromDictionary.length(); k++) {
                        if (!Character.toString(wordFromDictionary.charAt(0)).equals(AvaibleLetters.get(AvaibleLetters.size() - 1))) {
                            isWordAvaible = false;
                            break;
                        }
                        if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                            isWordAvaible = false;
                            break;
                        } else {
                            AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                        }
                    }
                    //liczymy punkty
                    int points = 0;
                    int bonus = 1;
                    int n = 0;
                    if (isWordAvaible){
                        for(int m = First.getCordy(); m <= Last.getCordy(); m++) {
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 0) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 1) {
                                points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 2) {
                                points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 3) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 2;
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 4) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 3;
                            }
                            n = n + 1;
                            if (n > wordFromDictionary.length() - 1) {
                                break;
                            }
                        }

                        points = points * bonus;
                        if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible){
                            listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[First.getCordx()][First.getCordy()], board.getBoard()[First.getCordx()][First.getCordy() + wordFromDictionary.length() -1], wordFromDictionary));
                            //System.out.println(AvaibleLetters.get(AvaibleLetters.size() - 1));
                            //System.out.println(wordFromDictionary);
                        }
                    }

                }
            }

            //pionowo
            if(First.getCordy() == Last.getCordy()){
                int maxLength = Last.getCordx() - First.getCordx();
                AvaibleLetters.add(First.getLetter().toLowerCase(Locale.ROOT));
                //szukamy w slowniku
                for(int j=0; j < Dictionary.size(); j++) {
                    //bierzemy slowo ze slownika
                    String wordFromDictionary = Dictionary.get(j);
                    if (wordFromDictionary.length() > maxLength){
                        continue;
                    }
                    //sprawdzamy czy mozemy je ulozyc
                    ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
                    boolean isWordAvaible = true;
                    for(int k = 0; k < wordFromDictionary.length(); k++) {
                        if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                            isWordAvaible = false;
                            break;
                        } else {
                            AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                        }
                        if (!Character.toString(wordFromDictionary.charAt(0)).equals(AvaibleLetters.get(AvaibleLetters.size() - 1))) {
                            isWordAvaible = false;
                            break;
                        }
                    }
                    //liczymy punkty
                    int points = 0;
                    int bonus = 1;
                    int n = 0;
                    if (isWordAvaible){
                        for(int m = First.getCordx(); m <= Last.getCordx(); m++) {
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 0) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 1) {
                                points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 2) {
                                points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 3) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 2;
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 4) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                                bonus = bonus * 3;
                            }
                            n = n + 1;
                            if (n > wordFromDictionary.length() - 1) {
                                break;
                            }
                        }

                        points = points * bonus;
                        if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible){
                            listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[First.getCordx()][First.getCordy()], board.getBoard()[First.getCordx() + wordFromDictionary.length() - 1][Last.getCordy()], wordFromDictionary));
                            //System.out.println(AvaibleLetters.get(AvaibleLetters.size() - 1));
                            //System.out.println(wordFromDictionary);
                        }
                    }

                }
            }
            AvaibleLetters.remove(AvaibleLetters.size()-1);
        }
    }

    /*public void FindAllWorldsThirdType(ArrayList<String> AvaibleLetters, Board board){
        //word to aaraylista fieldow
        List<Word> listWithCord = new ArrayList<>();
        listWithCord = listWithAllPossibleFields.get(2);
        LetterPoints letterPoints = new LetterPoints();
        HashMap<String,Integer> letterWithPoints = letterPoints.getLetterWithPoints();
        ArrayList<String> Dictionary = letterPoints.getDictionary();
        for(int i=0; i < listWithCord.size(); i++) {
            //biore pierwsza pole i ostatnia
            String additionalLetters = "";
            Field First = listWithCord.get(i).getListOfFields().get(0);
            Field Last = listWithCord.get(i).getListOfFields().get(listWithCord.get(i).getListOfFields().size() - 1);
            int firstLength = -1;
            int lastLength = -1;

            boolean findFirst = false;
            //boolean findLast = false;
            //no chuj znajdujemyjakos dlugosci pojebana sprawa
            for (int j = 0; j < listWithCord.get(i).getListOfFields().size(); j++){
                if(!listWithCord.get(i).getListOfFields().get(j).isEmpty()){
                    additionalLetters = additionalLetters + listWithCord.get(i).getListOfFields().get(0).getLetter().toLowerCase(Locale.ROOT);
                    findFirst = true;
                }
                if (j != 0){
                    if (!listWithCord.get(i).getListOfFields().get(j - 1).isEmpty() && listWithCord.get(i).getListOfFields().get(j).isEmpty()){
                        if(First.getCordx() == Last.getCordx()) {
                            lastLength = listWithCord.get(i).getListOfFields().get(j).getCordy();
                        }
                        if(First.getCordy() == Last.getCordy()){
                            lastLength = listWithCord.get(i).getListOfFields().get(j).getCordx();
                        }
                    }
                }
                if (findFirst){
                    if(First.getCordx() == Last.getCordx()) {
                        firstLength = listWithCord.get(i).getListOfFields().get(j).getCordy();
                    }
                    if(First.getCordy() == Last.getCordy()){
                        firstLength = listWithCord.get(i).getListOfFields().get(j).getCordx();
                    }
                }
            }
            if (firstLength == -1){
                if(First.getCordx() == Last.getCordx()) {
                    firstLength = First.getCordy();
                }
                if(First.getCordy() == Last.getCordy()){
                    firstLength = First.getCordx();
                }
            }
            if (lastLength == -1){
                if(First.getCordx() == Last.getCordx()) {
                    lastLength = Last.getCordy();
                }
                if(First.getCordy() == Last.getCordy()){
                    lastLength = Last.getCordx();
                }
            }
            boolean addedLAtter = false;

            //poziomo
            if(First.getCordx() == Last.getCordx()){
                int maxLength = Last.getCordy() - First.getCordy();
                if (firstLength == lastLength) {
                    AvaibleLetters.add(First.getLetter().toLowerCase(Locale.ROOT));
                    addedLAtter = true;
                }
                //szukamy w slowniku
                for(int j=0; j < Dictionary.size(); j++) {
                    //bierzemy slowo ze slownika
                    String wordFromDictionary = Dictionary.get(j);
                    if (wordFromDictionary.length() < additionalLetters.length()){
                        continue;
                    }
                    if (wordFromDictionary.length() > maxLength){
                        continue;
                    }
                    //sprawdzamy czy mozemy je ulozyc
                    ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
                    boolean isWordAvaible = true;
                    if (!wordFromDictionary.contains(additionalLetters)) {
                        continue;
                    }
                    //sprawdzamy dlugosci
                    if(wordFromDictionary.indexOf(additionalLetters) > firstLength - First.getCordy()){
                        continue;
                    }
                    if(wordFromDictionary.length() - (wordFromDictionary.indexOf(additionalLetters) + additionalLetters.length() - 1) > Last.getCordy() - lastLength){
                        continue;
                    }
                    String wordFromDictionaryCopy = wordFromDictionary.substring(0, wordFromDictionary.indexOf(additionalLetters)) + wordFromDictionary.substring((wordFromDictionary.indexOf(additionalLetters) + additionalLetters.length() - 1));
                    if (firstLength == lastLength) {
                        for(int k = 0; k < wordFromDictionary.length(); k++) {
                            if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                                isWordAvaible = false;
                                break;
                            } else {
                                AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                            }
                        }
                    }else{
                        for(int k = 0; k < wordFromDictionaryCopy.length(); k++) {
                            if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionaryCopy.charAt(k)))) {
                                isWordAvaible = false;
                                break;
                            } else {
                                AvaibleLettersCopy.remove(String.valueOf(wordFromDictionaryCopy.charAt(k)));
                            }
                        }
                    }
                    //liczymy punkty
                    int points = 0;
                    int bonus = 1;
                    int n = 0;
                    if (isWordAvaible){
                        for(int m = firstLength - wordFromDictionary.indexOf(additionalLetters); m <= Last.getCordy(); m++) {
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 0) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 1) {
                                points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 2) {
                                points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 3) {
                                bonus = bonus * 2;
                            }
                            if (board.getBoard()[First.getCordx()][m].getBonus() == 4) {
                                bonus = bonus * 3;
                            }
                            n = n + 1;
                            if (n > wordFromDictionary.length() - 1) {
                                break;
                            }
                        }

                        points = points * bonus;
                        if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible){
                            listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[First.getCordx()][firstLength - wordFromDictionary.indexOf(additionalLetters)], board.getBoard()[First.getCordx()][firstLength - wordFromDictionary.indexOf(additionalLetters) + wordFromDictionary.length() -1], wordFromDictionary));
                            //System.out.println(additionalLetters);
                            //System.out.println(wordFromDictionary);
                        }
                    }

                }
            }

            //pionowo
            if(First.getCordy() == Last.getCordy()){
                int maxLength = Last.getCordx() - First.getCordx();
                AvaibleLetters.add(First.getLetter().toLowerCase(Locale.ROOT));
                //szukamy w slowniku
                for(int j=0; j < Dictionary.size(); j++) {
                    //bierzemy slowo ze slownika
                    String wordFromDictionary = Dictionary.get(j);
                    if (wordFromDictionary.length() > maxLength){
                        continue;
                    }
                    //sprawdzamy czy mozemy je ulozyc
                    ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
                    boolean isWordAvaible = true;
                    for(int k = 0; k < wordFromDictionary.length(); k++) {
                        if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                            isWordAvaible = false;
                            break;
                        } else {
                            AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                        }
                        if (!Character.toString(wordFromDictionary.charAt(0)).equals(AvaibleLetters.get(AvaibleLetters.size() - 1))) {
                            isWordAvaible = false;
                            break;
                        }
                    }
                    //liczymy punkty
                    int points = 0;
                    int bonus = 1;
                    int n = 0;
                    if (isWordAvaible){
                        for(int m = First.getCordx(); m <= Last.getCordx(); m++) {
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 0) {
                                points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 1) {
                                points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 2) {
                                points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 3) {
                                bonus = bonus * 2;
                            }
                            if (board.getBoard()[m][First.getCordy()].getBonus() == 4) {
                                bonus = bonus * 3;
                            }
                            n = n + 1;
                            if (n > wordFromDictionary.length() - 1) {
                                break;
                            }
                        }

                        points = points * bonus;
                        if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible){
                            listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[First.getCordx()][First.getCordy()], board.getBoard()[First.getCordx() + wordFromDictionary.length() - 1][Last.getCordy()], wordFromDictionary));
                            //System.out.println(AvaibleLetters.get(AvaibleLetters.size() - 1));
                            //System.out.println(wordFromDictionary);
                        }
                    }

                }
            }
            if (addedLAtter) {
                AvaibleLetters.remove(AvaibleLetters.size() - 1);
            }
        }

    }*/

    public void FindWordForEmptyBoard(ArrayList<String> AvaibleLetters, Board board){
        LetterPoints letterPoints = new LetterPoints();
        HashMap<String,Integer> letterWithPoints = letterPoints.getLetterWithPoints();
        ArrayList<String> Dictionary = letterPoints.getDictionary();
        int maxLength = 7;
        //szukamy w slowniku
        for (int j = 0; j < Dictionary.size(); j++) {
            //bierzemy slowo ze slownika
            String wordFromDictionary = Dictionary.get(j);
            if (wordFromDictionary.length() > maxLength) {
                continue;
            }
            //sprawdzamy czy mozemy je ulozyc
            ArrayList<String> AvaibleLettersCopy = (ArrayList<String>) AvaibleLetters.clone();
            boolean isWordAvaible = true;
            for (int k = 0; k < wordFromDictionary.length(); k++) {
                if (!AvaibleLettersCopy.contains(String.valueOf(wordFromDictionary.charAt(k)))) {
                    isWordAvaible = false;
                    break;
                } else {
                    AvaibleLettersCopy.remove(String.valueOf(wordFromDictionary.charAt(k)));
                }
            }
            //liczymy punkty
            int points = 0;
            int bonus = 1;
            int n = wordFromDictionary.length() - 1;
            if (isWordAvaible) {
                for (int m = 7; m <= 14; m++) {
                    if (board.getBoard()[7][m].getBonus() == 0) {
                        points = points + letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                    }
                    if (board.getBoard()[7][m].getBonus() == 1) {
                        points = points + 2 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                    }
                    if (board.getBoard()[7][m].getBonus() == 2) {
                        points = points + 3 * letterWithPoints.get(Character.toString(wordFromDictionary.charAt(n)));
                    }
                    if (board.getBoard()[7][m].getBonus() == 3) {
                        bonus = bonus * 2;
                    }
                    if (board.getBoard()[7][m].getBonus() == 4) {
                        bonus = bonus * 3;
                    }
                    n = n - 1;
                    if (n < 0) {
                        break;
                    }
                }

                points = points * bonus;
                if (!listWithAllWordsWithPoints.containsKey(points) && isWordAvaible) {
                    listWithAllWordsWithPoints.put(points, new PossibleWords(board.getBoard()[7][7], board.getBoard()[7][7 + wordFromDictionary.length() - 1], wordFromDictionary));
                    //System.out.println(AvaibleLetters.get(AvaibleLetters.size() - 1));
                    //System.out.println(wordFromDictionary);
                }
            }
        }
    }

    public HashMap<Integer, PossibleWords> getListWithAllWordsWithPoints() {
        return listWithAllWordsWithPoints;
    }

    public List<List<Word>> getListWithAllPossibleFields() {
        return listWithAllPossibleFields;
    }

    public void setListWithAllPossibleFields(List<List<Word>> listWithAllPossibleFields) {
        this.listWithAllPossibleFields = listWithAllPossibleFields;
    }

    public static void main(String[] args) {
        Board board1 = new Board();
        VerticalFieldsFinder verticalFieldsFinder = new VerticalFieldsFinder(board1);
        List<List<Word>> listOfPossibleWords1 = verticalFieldsFinder.findAllVerticalPossibleWords();
        System.out.println("First type");
        listOfPossibleWords1.get(0).forEach(word -> {
                    System.out.println();
                    word.getListOfFields().forEach(field -> {
                                System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                            }
                    );
                }
        );
        System.out.println();

        System.out.println("Second type");
        listOfPossibleWords1.get(1).forEach(word -> {
            System.out.println();
            word.getListOfFields().forEach(field -> {
                        System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                    }
            );
        });
        System.out.println();
        System.out.println("Third type");
        listOfPossibleWords1.get(2).forEach(word -> {
            System.out.println();
            word.getListOfFields().forEach(field -> {
                        System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                    }
            );
        });
        System.out.println();

        ArrayList<String> AvaibleLetters1 = new ArrayList<>();
        AvaibleLetters1.add("a");
        AvaibleLetters1.add("b");
        AvaibleLetters1.add("k");
        AvaibleLetters1.add("m");
        AvaibleLetters1.add("r");
        AvaibleLetters1.add("i");
        AvaibleLetters1.add("d");

        TrueWordFinder trueWordFinder100 = new TrueWordFinder(listOfPossibleWords1);
        trueWordFinder100.FindWordForEmptyBoard(AvaibleLetters1, board1);
        HashMap<Integer, PossibleWords> listWithAllWordsWithPoints100 = trueWordFinder100.getListWithAllWordsWithPoints();
        System.out.print(listWithAllWordsWithPoints100);
        for (int i = 0 ; i < 100; i++){
            if (listWithAllWordsWithPoints100.containsKey(i)){
                System.out.print(listWithAllWordsWithPoints100.get(i).getStartField().getCordx());
                System.out.print(listWithAllWordsWithPoints100.get(i).getStartField().getCordy());
                System.out.print(" ");
                System.out.print(listWithAllWordsWithPoints100.get(i).getEndField().getCordx());
                System.out.print(listWithAllWordsWithPoints100.get(i).getEndField().getCordy());
                System.out.print(" ");
                System.out.println(listWithAllWordsWithPoints100.get(i).getWord());
            }
        }


        TrueWordFinder trueWordFinder = new TrueWordFinder(listOfPossibleWords1);
        trueWordFinder.FindAllWorldsFirstType(AvaibleLetters1, board1);
        HashMap<Integer, PossibleWords> listWithAllWordsWithPoints1 = trueWordFinder.getListWithAllWordsWithPoints();
        System.out.print(listWithAllWordsWithPoints1);
        for (int i = 0 ; i < 100; i++){
            if (listWithAllWordsWithPoints1.containsKey(i)){
                System.out.print(listWithAllWordsWithPoints1.get(i).getStartField().getCordx());
                System.out.print(listWithAllWordsWithPoints1.get(i).getStartField().getCordy());
                System.out.print(" ");
                System.out.print(listWithAllWordsWithPoints1.get(i).getEndField().getCordx());
                System.out.print(listWithAllWordsWithPoints1.get(i).getEndField().getCordy());
                System.out.print(" ");
                System.out.println(listWithAllWordsWithPoints1.get(i).getWord());
            }
        }
        trueWordFinder.FindAllWorldsSecondType(AvaibleLetters1, board1);
        HashMap<Integer, PossibleWords> listWithAllWordsWithPoints2 = trueWordFinder.getListWithAllWordsWithPoints();
        System.out.print(listWithAllWordsWithPoints2);
        for (int i = 0 ; i < 100; i++){
            if (listWithAllWordsWithPoints2.containsKey(i)){
                System.out.print(listWithAllWordsWithPoints2.get(i).getStartField().getCordx());
                System.out.print(listWithAllWordsWithPoints2.get(i).getStartField().getCordy());
                System.out.print(" ");
                System.out.print(listWithAllWordsWithPoints2.get(i).getEndField().getCordx());
                System.out.print(listWithAllWordsWithPoints2.get(i).getEndField().getCordy());
                System.out.print(" ");
                System.out.println(listWithAllWordsWithPoints2.get(i).getWord());
            }
        }


        System.out.println("Przerqa");

        Field[][] scrabbleMap = new Field[15][15];
        Board board = new Board();
        HorizontalFieldsFinder horizontalFieldsFinder = new HorizontalFieldsFinder(board);
        List<List<Word>> listOfPossibleWords11 = horizontalFieldsFinder.findAllHorizontalPossibleWords();
        System.out.println("First type");
        listOfPossibleWords11.get(0).forEach(word -> {
                    System.out.println();
                    word.getListOfFields().forEach(field -> {
                                System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                            }
                    );
                }
        );
        System.out.println();

        System.out.println("Second type");
        listOfPossibleWords11.get(1).forEach(word -> {
            System.out.println();
            word.getListOfFields().forEach(field -> {
                        System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                    }
            );
        });
        System.out.println();
        System.out.println("Third type");
        listOfPossibleWords11.get(2).forEach(word -> {
            System.out.println();
            word.getListOfFields().forEach(field -> {
                        System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                    }
            );
        });
        System.out.println();

        ArrayList<String> AvaibleLetters11 = new ArrayList<>();
        AvaibleLetters11.add("a");
        AvaibleLetters11.add("b");
        AvaibleLetters11.add("h");
        AvaibleLetters11.add("m");
        AvaibleLetters11.add("r");
        AvaibleLetters11.add("i");
        AvaibleLetters11.add("d");
        Board board11 = new Board();

        TrueWordFinder trueWordFinder2 = new TrueWordFinder(listOfPossibleWords11);
        trueWordFinder2.FindAllWorldsFirstType(AvaibleLetters11, board11);
        HashMap<Integer, PossibleWords> listWithAllWordsWithPoints11 = trueWordFinder2.getListWithAllWordsWithPoints();
        System.out.print(listWithAllWordsWithPoints11);
        for (int i = 0 ; i < 100; i++){
            if (listWithAllWordsWithPoints11.containsKey(i)){
                System.out.print(listWithAllWordsWithPoints11.get(i).getStartField().getCordx());
                System.out.print(listWithAllWordsWithPoints11.get(i).getStartField().getCordy());
                System.out.print(" ");
                System.out.print(listWithAllWordsWithPoints11.get(i).getEndField().getCordx());
                System.out.print(listWithAllWordsWithPoints11.get(i).getEndField().getCordy());
                System.out.print(" ");
                System.out.println(listWithAllWordsWithPoints11.get(i).getWord());
            }
        }
        trueWordFinder2.FindAllWorldsSecondType(AvaibleLetters11, board11);
        HashMap<Integer, PossibleWords> listWithAllWordsWithPoints22 = trueWordFinder2.getListWithAllWordsWithPoints();
        System.out.print(listWithAllWordsWithPoints22);
        for (int i = 0 ; i < 100; i++){
            if (listWithAllWordsWithPoints22.containsKey(i)){
                System.out.print(listWithAllWordsWithPoints22.get(i).getStartField().getCordx());
                System.out.print(listWithAllWordsWithPoints22.get(i).getStartField().getCordy());
                System.out.print(" ");
                System.out.print(listWithAllWordsWithPoints22.get(i).getEndField().getCordx());
                System.out.print(listWithAllWordsWithPoints22.get(i).getEndField().getCordy());
                System.out.print(" ");
                System.out.println(listWithAllWordsWithPoints22.get(i).getWord());
            }
        }
    }
}
