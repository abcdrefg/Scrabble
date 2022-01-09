package fieldFinder;

import ScrabbleBoard.Board;
import ScrabbleBoard.Field;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class VerticalFieldsFinder {
    private Field[][] scrabbleMap;
    private final List<Word> firstTypeOfVerticalPossibleWords = new ArrayList<>();
    private final List<Word> secondTypeOfVerticalPossibleWords = new ArrayList<>();
    private List<Word> thirdTypeOfVerticalPossibleWords = new ArrayList<>();

    public VerticalFieldsFinder(Board board){
        this.scrabbleMap = board.getBoard();
    }

    public List<Word> findAllVerticalWords() {
        List<Word> listOfWords = new ArrayList<>();
        int actualColumn;
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                if (!scrabbleMap[x][y].isEmpty()) {
                    actualColumn = y;
                    Word word = new Word();
                    while (actualColumn != 14 && !scrabbleMap[x][actualColumn].isEmpty()) {
                        word.getListOfFields().add(scrabbleMap[x][actualColumn]);
                        actualColumn++;
                    }
                    if (actualColumn == 14 && !scrabbleMap[x][actualColumn].isEmpty()) {
                        word.getListOfFields().add(scrabbleMap[x][actualColumn]);
                    }
                    listOfWords.add(word);
                    y = actualColumn;
                }
            }
        }
        return listOfWords;
    }

    public List<List<Word>> findAllVerticalPossibleWords() {
        List<Word> listOfWords = findAllVerticalWords();
        Word firstTypePossibleWord, secondTypePossibleWord, thirdTypePossibleWord;
        boolean condition;
        int fieldRow, fieldColumn, columnIterator, indexOfLastWord;
        for (Word word : listOfWords) {
            fieldRow = word.getListOfFields().get(0).getCordx();
            fieldColumn = word.getListOfFields().get(0).getCordy();
            indexOfLastWord = word.getListOfFields().size() - 1;
            columnIterator = fieldColumn - 1;
            if (fieldRow != 0 && fieldRow != 14 && !scrabbleMap[fieldRow][fieldColumn].isEmpty()) {
                firstTypePossibleWord = new Word();
                firstTypePossibleWord.setListOfFields(word.getListOfFields());
                if (columnIterator==0 && scrabbleMap[fieldRow-1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty()){
                    firstTypePossibleWord.getListOfFields().add(scrabbleMap[fieldRow][columnIterator]);
                }
                while (columnIterator >= 1 && scrabbleMap[fieldRow - 1][columnIterator].isEmpty() &&
                        scrabbleMap[fieldRow + 1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator - 1].isEmpty()) {
                    condition = columnIterator == 1 && scrabbleMap[fieldRow + 1][columnIterator - 1].isEmpty() && scrabbleMap[fieldRow - 1][columnIterator - 1].isEmpty();
                    addFieldToRow(condition, firstTypePossibleWord, fieldRow, columnIterator, "FIRST");
                    columnIterator--;
                }
                addPossibleWordToList(firstTypeOfVerticalPossibleWords, firstTypePossibleWord);
                fieldRow = word.getListOfFields().get(indexOfLastWord).getCordx();
                fieldColumn = word.getListOfFields().get(indexOfLastWord).getCordy();

                secondTypePossibleWord = new Word();
                columnIterator = fieldColumn + 1;
                secondTypePossibleWord.setListOfFields(word.getListOfFields());
                if (columnIterator==14 && scrabbleMap[fieldRow-1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty() && scrabbleMap[fieldRow+1][columnIterator].isEmpty()){
                    secondTypePossibleWord.getListOfFields().add(scrabbleMap[fieldRow][columnIterator]);
                }
                while (columnIterator <= 13 && scrabbleMap[fieldRow - 1][columnIterator].isEmpty() &&
                        scrabbleMap[fieldRow + 1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator + 1].isEmpty()) {
                    condition = columnIterator == 13 && scrabbleMap[fieldRow + 1][columnIterator + 1].isEmpty() && scrabbleMap[fieldRow - 1][columnIterator + 1].isEmpty();
                    addFieldToRow(condition, secondTypePossibleWord, fieldRow, columnIterator, "SECOND");

                    columnIterator++;
                }
                addPossibleWordToList(secondTypeOfVerticalPossibleWords, secondTypePossibleWord);
                thirdTypePossibleWord = generatingThirdTypeWord(firstTypePossibleWord, secondTypePossibleWord);
                thirdTypeOfVerticalPossibleWords.add(thirdTypePossibleWord);
            } else if (fieldRow == 0 && !scrabbleMap[fieldRow][fieldColumn].isEmpty()) {
                firstTypePossibleWord = new Word();
                firstTypePossibleWord.setListOfFields(word.getListOfFields());
                if (columnIterator==0 && scrabbleMap[fieldRow+1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty()){
                    firstTypePossibleWord.getListOfFields().add(scrabbleMap[fieldRow][columnIterator]);
                }
                while (columnIterator >= 1 &&
                        scrabbleMap[fieldRow + 1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator - 1].isEmpty()) {
                    condition = columnIterator == 1 && scrabbleMap[fieldRow + 1][columnIterator - 1].isEmpty();
                    addFieldToRow(condition, firstTypePossibleWord, fieldRow, columnIterator, "FIRST");
                    columnIterator--;
                }

                addPossibleWordToList(firstTypeOfVerticalPossibleWords, firstTypePossibleWord);
                fieldRow = word.getListOfFields().get(indexOfLastWord).getCordx();
                fieldColumn = word.getListOfFields().get(indexOfLastWord).getCordy();

                secondTypePossibleWord = new Word();
                columnIterator = fieldColumn + 1;
                secondTypePossibleWord.setListOfFields(word.getListOfFields());
                if (columnIterator==14 && scrabbleMap[fieldRow+1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty()){
                    secondTypePossibleWord.getListOfFields().add(scrabbleMap[fieldRow][columnIterator]);
                }
                while (columnIterator <= 13 && scrabbleMap[fieldRow + 1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty()) {
                    condition = columnIterator == 13 && scrabbleMap[fieldRow + 1][columnIterator + 1].isEmpty();
                    addFieldToRow(condition, secondTypePossibleWord, fieldRow, columnIterator, "SECOND");
                    columnIterator++;
                }
                addPossibleWordToList(secondTypeOfVerticalPossibleWords, secondTypePossibleWord);
                thirdTypePossibleWord = generatingThirdTypeWord(firstTypePossibleWord, secondTypePossibleWord);
                thirdTypeOfVerticalPossibleWords.add(thirdTypePossibleWord);
            } else if (!scrabbleMap[fieldRow][fieldColumn].isEmpty()) {
                firstTypePossibleWord = new Word();
                firstTypePossibleWord.setListOfFields(word.getListOfFields());
                if (columnIterator==0 && scrabbleMap[fieldRow-1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty()){
                    firstTypePossibleWord.getListOfFields().add(scrabbleMap[fieldRow][columnIterator]);
                }
                while (columnIterator >= 1 &&
                        scrabbleMap[fieldRow - 1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator - 1].isEmpty()) {
                    condition = columnIterator == 1 && scrabbleMap[fieldRow - 1][columnIterator - 1].isEmpty();
                    addFieldToRow(condition, firstTypePossibleWord, fieldRow, columnIterator, "FIRST");
                    columnIterator--;
                }
                addPossibleWordToList(firstTypeOfVerticalPossibleWords, firstTypePossibleWord);
                fieldRow = word.getListOfFields().get(indexOfLastWord).getCordx();
                fieldColumn = word.getListOfFields().get(indexOfLastWord).getCordy();

                secondTypePossibleWord = new Word();
                columnIterator = fieldColumn + 1;
                secondTypePossibleWord.setListOfFields(word.getListOfFields());
                if (columnIterator==14 && scrabbleMap[fieldRow-1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty()){
                    secondTypePossibleWord.getListOfFields().add(scrabbleMap[fieldRow][columnIterator]);
                }
                while (columnIterator <= 13 && scrabbleMap[fieldRow - 1][columnIterator].isEmpty() && scrabbleMap[fieldRow][columnIterator].isEmpty()) {
                    condition = columnIterator == 13 && scrabbleMap[fieldRow - 1][columnIterator + 1].isEmpty();
                    addFieldToRow(condition, secondTypePossibleWord, fieldRow, columnIterator, "SECOND");
                    columnIterator++;
                }
                addPossibleWordToList(secondTypeOfVerticalPossibleWords, secondTypePossibleWord);
                thirdTypePossibleWord = generatingThirdTypeWord(firstTypePossibleWord, secondTypePossibleWord);
                thirdTypeOfVerticalPossibleWords.add(thirdTypePossibleWord);
            }

        }
        //mergeThirdTypeFields();
        return List.of(filterOneFieldPossibleWords(firstTypeOfVerticalPossibleWords), filterOneFieldPossibleWords(secondTypeOfVerticalPossibleWords), (thirdTypeOfVerticalPossibleWords));

    }

    private void mergeThirdTypeFields() {
        mergeWordsWithTwoSpacesBetweenThem(0);
        thirdTypeOfVerticalPossibleWords = filterOneFieldPossibleWords(thirdTypeOfVerticalPossibleWords);
        sortListOfWordsByXAxis(thirdTypeOfVerticalPossibleWords);
        System.out.println("Merging ...");
        mergeWordsWithZeroOrOneSpacesBetweenThem();
    }

    private void mergeWordsWithTwoSpacesBetweenThem(int indexOfIteration) {
        int xCordOfFirstField, yCordOfFirstField, xCordOfLastField, yCordOfLastField, xTmp;
        for (int i = indexOfIteration; i < thirdTypeOfVerticalPossibleWords.size() - 1; i++) {
            xTmp = thirdTypeOfVerticalPossibleWords.get(i).getListOfFields().size() - 1;
            xCordOfLastField = thirdTypeOfVerticalPossibleWords.get(i).getListOfFields().get(xTmp).getCordx();
            yCordOfLastField = thirdTypeOfVerticalPossibleWords.get(i).getListOfFields().get(xTmp).getCordy();
            xCordOfFirstField = thirdTypeOfVerticalPossibleWords.get(i + 1).getListOfFields().get(0).getCordx();
            yCordOfFirstField = thirdTypeOfVerticalPossibleWords.get(i + 1).getListOfFields().get(0).getCordy();
            if (xCordOfFirstField == xCordOfLastField && yCordOfFirstField - 2 == yCordOfLastField && scrabbleMap[xCordOfFirstField][yCordOfLastField + 1].isEmpty()) {
                Word firstPartOfNewWord = new Word();
                firstPartOfNewWord.setListOfFields(thirdTypeOfVerticalPossibleWords.get(i).getListOfFields());
                firstPartOfNewWord.getListOfFields().add(scrabbleMap[xCordOfFirstField][yCordOfLastField + 1]);
                Word secondPartOfNewWord = new Word();
                secondPartOfNewWord.setListOfFields(thirdTypeOfVerticalPossibleWords.get(i + 1).getListOfFields());
                Word newWord = generatingThirdTypeWord(firstPartOfNewWord, secondPartOfNewWord);
                thirdTypeOfVerticalPossibleWords.add(newWord);
                mergeWordsWithTwoSpacesBetweenThem(i + 1);
            }
        }
    }

    private void mergeWordsWithZeroOrOneSpacesBetweenThem() {
        boolean isAllMerged = true;
        List<Word> mergedWords = new ArrayList<>();
        for (int i = 0; i < thirdTypeOfVerticalPossibleWords.size() - 2; i++) {
            if (thirdTypeOfVerticalPossibleWords.get(i).getLastFieldYCord() == thirdTypeOfVerticalPossibleWords.get(i + 1).getFirstFieldYCord()) {
                Word newWord;
                newWord = generatingThirdTypeWord(thirdTypeOfVerticalPossibleWords.get(i), thirdTypeOfVerticalPossibleWords.get(i + 1));
                mergedWords.add(newWord);
                isAllMerged = false;
            } else if (Math.abs(thirdTypeOfVerticalPossibleWords.get(i).getLastFieldYCord() - thirdTypeOfVerticalPossibleWords.get(i + 1).getFirstFieldYCord()) == 1) {
                Word newWord;
                newWord = generatingThirdTypeWord(thirdTypeOfVerticalPossibleWords.get(i), thirdTypeOfVerticalPossibleWords.get(i + 1));
                mergedWords.add(newWord);
                isAllMerged = false;
            }
        }
        thirdTypeOfVerticalPossibleWords.addAll(mergedWords);
        sortListOfWordsByXAxis(thirdTypeOfVerticalPossibleWords);
    }

    private void sortListOfWordsByXAxis(List<Word> listOfWords) {
        thirdTypeOfVerticalPossibleWords.sort(Comparator.comparing(word -> word.getListOfFields().get(0).getCordx()));
    }

    private void addFieldToRow(boolean condition, Word possibleWord, int rowIndex, int columnIndex, String type) {
        if (condition) {
            int y = type.equals("FIRST") ? columnIndex - 1 : columnIndex + 1;
            possibleWord.getListOfFields().add(scrabbleMap[rowIndex][y]);
        }
        possibleWord.getListOfFields().add(scrabbleMap[rowIndex][columnIndex]);
    }

    private void addPossibleWordToList(List<Word> listOfPossibleWords, Word possibleWord) {
        possibleWord.sortListByYAxis();
        listOfPossibleWords.add(possibleWord);
    }


    private Word generatingThirdTypeWord(Word firstTypeWord, Word secondTypeWord) {
        Set<Field> setOfFields = new LinkedHashSet<>(firstTypeWord.getListOfFields());
        setOfFields.addAll(secondTypeWord.getListOfFields());
        Word thirdTypeWord = new Word();
        thirdTypeWord.setListOfFields(new ArrayList<>(setOfFields));
        //thirdTypeWord.sortList();
        return thirdTypeWord;
    }

    private static List<Word> filterOneFieldPossibleWords(List<Word> possibleWords) {
        return possibleWords.stream().distinct().filter(word -> word.getListOfFields().size() > 1).collect(Collectors.toList());
    }

    public void createMap() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                scrabbleMap[i][j] = new Field(" ", 0, i, j, true);
                if ((i == 7) && (j == 7)) {
                    scrabbleMap[i][j] = new Field("0", 0, i, j, false);
                }
            }
        }
    }

    public void printMap() {
        StringBuilder Row;
        for (int i = 0; i < 15; i++) {
            Row = new StringBuilder();
            for (int j = 0; j < 15; j++) {
                Row.append('|').append(scrabbleMap[i][j].getLetter());
            }
            System.out.println(Row);
        }
    }

    public void setValues() {
        scrabbleMap[0][0].setLetter("B");
        scrabbleMap[2][3].setLetter("A");
        scrabbleMap[3][3].setLetter("S");
        scrabbleMap[4][3].setLetter("D");
        scrabbleMap[5][3].setLetter("F");

        scrabbleMap[3][4].setLetter("O");
        scrabbleMap[3][5].setLetter("P");
        scrabbleMap[3][6].setLetter("A");

        scrabbleMap[4][6].setLetter("A");
        scrabbleMap[5][6].setLetter("A");
        scrabbleMap[6][6].setLetter("A");

        scrabbleMap[6][7].setLetter("F");
        scrabbleMap[6][8].setLetter("F");
        scrabbleMap[6][9].setLetter("F");
        scrabbleMap[6][10].setLetter("F");

        scrabbleMap[1][10].setLetter("S");
        scrabbleMap[2][10].setLetter("S");
        scrabbleMap[3][10].setLetter("S");
        scrabbleMap[4][10].setLetter("S");
        scrabbleMap[5][10].setLetter("S");
        scrabbleMap[6][10].setLetter("S");

        scrabbleMap[1][12].setLetter("S");
        //scrabbleMap[1][13].setLetter("S");
        scrabbleMap[1][14].setLetter("S");
        scrabbleMap[14][0].setLetter("A");

    }

    public static void main(String[] args) {
        Board board = new Board();
        board.getBoard()[1][1].setLetter("a");
        board.getBoard()[2][1].setLetter("a");
        board.getBoard()[3][1].setLetter("a");
        board.getBoard()[4][1].setLetter("a");
        board.getBoard()[5][1].setLetter("a");
        board.getBoard()[6][1].setLetter("a");
        board.getBoard()[7][1].setLetter("a");
        board.getBoard()[8][1].setLetter("a");
        board.getBoard()[9][1].setLetter("a");

        board.getBoard()[5][2].setLetter("a");
        board.getBoard()[7][2].setLetter("a");

        board.getBoard()[3][3].setLetter("a");
        board.getBoard()[4][3].setLetter("a");
        board.getBoard()[5][3].setLetter("a");
        board.getBoard()[6][3].setLetter("a");
        board.getBoard()[7][3].setLetter("a");
        board.getBoard()[8][3].setLetter("a");
        board.getBoard()[9][3].setLetter("a");

        board.getBoard()[3][4].setLetter("a");
        board.getBoard()[9][4].setLetter("a");
        board.getBoard()[10][4].setLetter("a");
        board.getBoard()[11][4].setLetter("a");
        board.getBoard()[12][4].setLetter("a");
        board.getBoard()[13][4].setLetter("a");

        board.getBoard()[1][5].setLetter("a");
        board.getBoard()[2][5].setLetter("a");
        board.getBoard()[3][5].setLetter("a");
        board.getBoard()[7][5].setLetter("a");
        board.getBoard()[8][5].setLetter("a");
        board.getBoard()[9][5].setLetter("a");

        board.getBoard()[1][6].setLetter("a");
        board.getBoard()[3][6].setLetter("a");
        board.getBoard()[5][6].setLetter("a");
        board.getBoard()[12][6].setLetter("a");

        board.getBoard()[1][7].setLetter("a");
        board.getBoard()[3][7].setLetter("a");
        board.getBoard()[5][7].setLetter("a");
        board.getBoard()[7][7].setLetter("a");
        board.getBoard()[10][7].setLetter("a");
        board.getBoard()[12][7].setLetter("a");

        board.getBoard()[1][8].setLetter("a");
        board.getBoard()[3][8].setLetter("a");
        board.getBoard()[5][8].setLetter("a");
        board.getBoard()[7][8].setLetter("a");
        board.getBoard()[10][8].setLetter("a");
        board.getBoard()[12][8].setLetter("a");

        board.getBoard()[1][9].setLetter("a");
        board.getBoard()[3][9].setLetter("a");
        board.getBoard()[4][9].setLetter("a");
        board.getBoard()[5][9].setLetter("a");
        board.getBoard()[6][9].setLetter("a");
        board.getBoard()[7][9].setLetter("a");
        board.getBoard()[10][9].setLetter("a");
        board.getBoard()[12][9].setLetter("a");

        board.getBoard()[1][10].setLetter("a");
        board.getBoard()[4][10].setLetter("a");
        board.getBoard()[7][10].setLetter("a");
        board.getBoard()[8][10].setLetter("a");
        board.getBoard()[9][10].setLetter("a");
        board.getBoard()[10][10].setLetter("a");
        board.getBoard()[11][10].setLetter("a");
        board.getBoard()[12][10].setLetter("a");

        board.getBoard()[1][11].setLetter("a");
        board.getBoard()[4][11].setLetter("a");
        board.getBoard()[9][11].setLetter("a");

        board.getBoard()[4][12].setLetter("a");
        board.getBoard()[9][12].setLetter("a");
        board.getBoard()[10][12].setLetter("a");
        board.getBoard()[11][12].setLetter("a");
        board.getBoard()[12][12].setLetter("a");
        board.getBoard()[13][12].setLetter("a");
        //board.getBoard()[0][13].setLetter("a");


        board.show();
        VerticalFieldsFinder verticalFieldsFinder = new VerticalFieldsFinder(board);
        long start = System.currentTimeMillis();
        List<List<Word>> listOfPossibleWords = verticalFieldsFinder.findAllVerticalPossibleWords();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("First type");
        listOfPossibleWords.get(0).forEach(word -> {
                    System.out.println();
                    word.getListOfFields().forEach(field -> {
                                System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                            }
                    );
                }
        );
        System.out.println();

        System.out.println("Second type");
        listOfPossibleWords.get(1).forEach(word -> {
            System.out.println();
            word.getListOfFields().forEach(field -> {
                        System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                    }
            );
        });
        System.out.println();
        System.out.println("Third type");
        listOfPossibleWords.get(2).forEach(word -> {
            System.out.println();
            word.getListOfFields().forEach(field -> {
                        System.out.print(field.getCordx() + "," + field.getCordy() + " ");
                    }
            );
        });
        System.out.println();
        //WordFinder.findAllValidFields(test);
        listOfPossibleWords.get(2).get(0).getListOfFields().get(1).setLetter("XDDDDD");
        verticalFieldsFinder.printMap();

        BufferedReader fileReader = null;
        String filePath = "static\\SJP.txt";
        List<String> asd = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                asd.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        //long start = System.currentTimeMillis();
        System.out.println(asd.stream().filter(word -> word.equals("żak")).findAny());
        //long end = System.currentTimeMillis();
        //System.out.println(end - start);
        start = System.currentTimeMillis();
        for (String s : asd) {
            if (s.equals("żak"))
                System.out.println("żak");
        }
        //end = System.currentTimeMillis();
        //System.out.println(end - start);
    }

}