package fieldFinder;

import ScrabbleBoard.Board;
import ScrabbleBoard.Field;

import java.util.*;
import java.util.stream.Collectors;

public class HorizontalFieldsFinder {
    private Field[][] scrabbleMap;
    private final List<Word> firstTypeOfHorizontalPossibleWords = new ArrayList<>();
    private final List<Word> secondTypeOfHorizontalPossibleWords = new ArrayList<>();
    private static final String FIRST_TYPE = "FIRST";
    private static final String SECOND_TYPE = "SECOND";
    private List<Word> thirdTypeOfHorizontalPossibleWords = new ArrayList<>();

    public HorizontalFieldsFinder(Board board) {
        scrabbleMap = board.getBoard();
    }

    public List<Word> findAllHorizontalWords() {
        List<Word> listOfWords = new ArrayList<>();
        int actualRow;
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                if (!scrabbleMap[x][y].isEmpty()) {
                    actualRow = x;
                    Word word = new Word();
                    while (actualRow != 14 && !scrabbleMap[actualRow][y].isEmpty()) {
                        word.getListOfFields().add(scrabbleMap[actualRow][y]);
                        actualRow++;
                    }
                    if (actualRow == 14 && !scrabbleMap[actualRow][y].isEmpty()) {
                        word.getListOfFields().add(scrabbleMap[actualRow][y]);
                    }
                    listOfWords.add(word);
                    x = actualRow;
                }
            }
        }
        return listOfWords;
    }

    public List<List<Word>> findAllHorizontalPossibleWords() {
        List<Word> listOfWords = findAllHorizontalWords();
        Word firstTypePossibleWord, secondTypePossibleWord, thirdTypePossibleWord;
        boolean condition;
        int fieldRow, fieldColumn, rowIterator, indexOfLastWord;
        for (Word word : listOfWords) {
            fieldRow = word.getListOfFields().get(0).getCordx();
            fieldColumn = word.getListOfFields().get(0).getCordy();
            indexOfLastWord = word.getListOfFields().size() - 1;
            rowIterator = fieldRow - 1;
            if (fieldColumn != 0 && fieldColumn != 14 && !scrabbleMap[fieldRow][fieldColumn].isEmpty()) {
                firstTypePossibleWord = new Word();
                firstTypePossibleWord.setListOfFields(word.getListOfFields());
                if (rowIterator==0 && scrabbleMap[rowIterator][fieldColumn -1].isEmpty() && scrabbleMap[rowIterator][fieldColumn + 1].isEmpty()){
                    firstTypePossibleWord.getListOfFields().add(scrabbleMap[rowIterator][fieldColumn]);
                }
                while (rowIterator >= 1 && scrabbleMap[rowIterator][fieldColumn - 1].isEmpty() &&
                        scrabbleMap[rowIterator][fieldColumn + 1].isEmpty() && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator - 1][fieldColumn].isEmpty()) {
                    condition = rowIterator == 1 && scrabbleMap[rowIterator - 1][fieldColumn + 1].isEmpty() && scrabbleMap[rowIterator - 1][fieldColumn - 1].isEmpty();
                    addFieldToRow(condition, firstTypePossibleWord, rowIterator, fieldColumn, FIRST_TYPE, scrabbleMap);
                    rowIterator--;
                }
                addPossibleWordToList(firstTypeOfHorizontalPossibleWords, firstTypePossibleWord);
                fieldRow = word.getListOfFields().get(indexOfLastWord).getCordx();
                fieldColumn = word.getListOfFields().get(indexOfLastWord).getCordy();

                secondTypePossibleWord = new Word();
                rowIterator = fieldRow + 1;
                secondTypePossibleWord.setListOfFields(word.getListOfFields());
                if (rowIterator==14 && scrabbleMap[rowIterator][fieldColumn -1].isEmpty() && scrabbleMap[rowIterator][fieldColumn + 1].isEmpty()){
                    secondTypePossibleWord.getListOfFields().add(scrabbleMap[rowIterator][fieldColumn]);
                }
                while (rowIterator <= 13 && scrabbleMap[rowIterator][fieldColumn - 1].isEmpty() &&
                        scrabbleMap[rowIterator][fieldColumn + 1].isEmpty() && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator + 1][fieldColumn].isEmpty()) {
                    condition = rowIterator == 13 && scrabbleMap[rowIterator + 1][fieldColumn + 1].isEmpty() && scrabbleMap[rowIterator + 1][fieldColumn - 1].isEmpty();
                    addFieldToRow(condition, secondTypePossibleWord, rowIterator, fieldColumn, SECOND_TYPE, scrabbleMap);

                    rowIterator++;
                }
                addPossibleWordToList(secondTypeOfHorizontalPossibleWords, secondTypePossibleWord);
                thirdTypePossibleWord = generatingThirdTypeWord(firstTypePossibleWord, secondTypePossibleWord);
                thirdTypeOfHorizontalPossibleWords.add(thirdTypePossibleWord);
            } else if (fieldColumn == 0 && !scrabbleMap[fieldRow][fieldColumn].isEmpty()) {
                firstTypePossibleWord = new Word();
                firstTypePossibleWord.setListOfFields(word.getListOfFields());
                if (rowIterator==0 && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator][fieldColumn + 1].isEmpty()){
                    firstTypePossibleWord.getListOfFields().add(scrabbleMap[rowIterator][fieldColumn]);
                }
                while (rowIterator >= 1 &&
                        scrabbleMap[rowIterator][fieldColumn + 1].isEmpty() && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator - 1][fieldColumn].isEmpty()) {
                    condition = rowIterator == 1 && scrabbleMap[rowIterator - 1][fieldColumn + 1].isEmpty();
                    addFieldToRow(condition, firstTypePossibleWord, rowIterator, fieldColumn, FIRST_TYPE, scrabbleMap);
                    rowIterator--;
                }

                addPossibleWordToList(firstTypeOfHorizontalPossibleWords, firstTypePossibleWord);
                fieldRow = word.getListOfFields().get(indexOfLastWord).getCordx();
                fieldColumn = word.getListOfFields().get(indexOfLastWord).getCordy();

                secondTypePossibleWord = new Word();
                rowIterator = fieldRow + 1;
                secondTypePossibleWord.setListOfFields(word.getListOfFields());
                if (rowIterator==14 && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator][fieldColumn + 1].isEmpty()){
                    secondTypePossibleWord.getListOfFields().add(scrabbleMap[rowIterator][fieldColumn]);
                }
                while (rowIterator <= 13 && scrabbleMap[rowIterator][fieldColumn + 1].isEmpty() && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator + 1][fieldColumn].isEmpty()) {
                    condition = rowIterator == 13 && scrabbleMap[rowIterator + 1][fieldColumn + 1].isEmpty();
                    addFieldToRow(condition, secondTypePossibleWord, rowIterator, fieldColumn, SECOND_TYPE, scrabbleMap);
                    rowIterator++;
                }
                addPossibleWordToList(secondTypeOfHorizontalPossibleWords, secondTypePossibleWord);
                thirdTypePossibleWord = generatingThirdTypeWord(firstTypePossibleWord, secondTypePossibleWord);
                thirdTypeOfHorizontalPossibleWords.add(thirdTypePossibleWord);
            } else if (!scrabbleMap[fieldRow][fieldColumn].isEmpty()) {
                firstTypePossibleWord = new Word();
                firstTypePossibleWord.setListOfFields(word.getListOfFields());
                if (rowIterator==0 && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator][fieldColumn - 1].isEmpty()){
                    firstTypePossibleWord.getListOfFields().add(scrabbleMap[rowIterator][fieldColumn]);
                }
                while (rowIterator >= 1 &&
                        scrabbleMap[rowIterator][fieldColumn - 1].isEmpty() && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator - 1][fieldColumn].isEmpty()) {
                    condition = rowIterator == 1 && scrabbleMap[rowIterator - 1][fieldColumn - 1].isEmpty();
                    addFieldToRow(condition, firstTypePossibleWord, rowIterator, fieldColumn, FIRST_TYPE, scrabbleMap);
                    rowIterator--;
                }
                addPossibleWordToList(firstTypeOfHorizontalPossibleWords, firstTypePossibleWord);
                fieldRow = word.getListOfFields().get(indexOfLastWord).getCordx();
                fieldColumn = word.getListOfFields().get(indexOfLastWord).getCordy();

                secondTypePossibleWord = new Word();
                rowIterator = fieldRow + 1;
                secondTypePossibleWord.setListOfFields(word.getListOfFields());
                if (rowIterator==14 && scrabbleMap[rowIterator][fieldColumn].isEmpty() && scrabbleMap[rowIterator][fieldColumn - 1].isEmpty()){
                    secondTypePossibleWord.getListOfFields().add(scrabbleMap[rowIterator][fieldColumn]);
                }
                while (rowIterator <= 13 && scrabbleMap[rowIterator][fieldColumn - 1].isEmpty() && scrabbleMap[rowIterator][fieldColumn].isEmpty()) {
                    condition = rowIterator == 13 && scrabbleMap[rowIterator + 1][fieldColumn - 1].isEmpty();
                    addFieldToRow(condition, secondTypePossibleWord, rowIterator, fieldColumn, SECOND_TYPE, scrabbleMap);
                    rowIterator++;
                }
                addPossibleWordToList(secondTypeOfHorizontalPossibleWords, secondTypePossibleWord);
                thirdTypePossibleWord = generatingThirdTypeWord(firstTypePossibleWord, secondTypePossibleWord);
                thirdTypeOfHorizontalPossibleWords.add(thirdTypePossibleWord);
            }

        }
        //mergeThirdTypeFields(scrabbleMap);
        return List.of(filterOneFieldPossibleWords(firstTypeOfHorizontalPossibleWords), filterOneFieldPossibleWords(secondTypeOfHorizontalPossibleWords), (thirdTypeOfHorizontalPossibleWords));

    }

    private void mergeThirdTypeFields(Field[][] scrabbleMap) {
        mergeWordsWithTwoSpacesBetweenThem(0, scrabbleMap);
        thirdTypeOfHorizontalPossibleWords = filterOneFieldPossibleWords(thirdTypeOfHorizontalPossibleWords);
        sortListOfWordsByYAxis(thirdTypeOfHorizontalPossibleWords);
        System.out.println("Merging ...");
        mergeWordsWithZeroOrOneSpacesBetweenThem();
    }

    private void mergeWordsWithTwoSpacesBetweenThem(int indexOfIteration, Field[][] scrabbleMap) {
        int xCordOfFirstField, yCordOfFirstField, xCordOfLastField, yCordOfLastField, xTmp;
        for (int i = indexOfIteration; i < thirdTypeOfHorizontalPossibleWords.size() - 1; i++) {
            xTmp = thirdTypeOfHorizontalPossibleWords.get(i).getListOfFields().size() - 1;
            xCordOfLastField = thirdTypeOfHorizontalPossibleWords.get(i).getListOfFields().get(xTmp).getCordx();
            yCordOfLastField = thirdTypeOfHorizontalPossibleWords.get(i).getListOfFields().get(xTmp).getCordy();
            xCordOfFirstField = thirdTypeOfHorizontalPossibleWords.get(i + 1).getListOfFields().get(0).getCordx();
            yCordOfFirstField = thirdTypeOfHorizontalPossibleWords.get(i + 1).getListOfFields().get(0).getCordy();
            if (yCordOfFirstField == yCordOfLastField && xCordOfFirstField - 2 == xCordOfLastField && scrabbleMap[xCordOfLastField + 1][yCordOfLastField].isEmpty()) {
                Word firstPartOfNewWord = new Word();
                firstPartOfNewWord.setListOfFields(thirdTypeOfHorizontalPossibleWords.get(i).getListOfFields());
                firstPartOfNewWord.getListOfFields().add(scrabbleMap[xCordOfLastField + 1][yCordOfLastField]);
                Word secondPartOfNewWord = new Word();
                secondPartOfNewWord.setListOfFields(thirdTypeOfHorizontalPossibleWords.get(i + 1).getListOfFields());
                Word newWord = generatingThirdTypeWord(firstPartOfNewWord, secondPartOfNewWord);
                thirdTypeOfHorizontalPossibleWords.add(newWord);
                mergeWordsWithTwoSpacesBetweenThem(i + 1, scrabbleMap);
            }
        }
    }

    private void mergeWordsWithZeroOrOneSpacesBetweenThem() {
        List<Word> mergedWords = new ArrayList<>();
        for (int i = 0; i < thirdTypeOfHorizontalPossibleWords.size() - 2; i++) {
            if (thirdTypeOfHorizontalPossibleWords.get(i).getLastFieldXCord() == thirdTypeOfHorizontalPossibleWords.get(i + 1).getFirstFieldXCord()) {
                Word newWord;
                newWord = generatingThirdTypeWord(thirdTypeOfHorizontalPossibleWords.get(i), thirdTypeOfHorizontalPossibleWords.get(i + 1));
                mergedWords.add(newWord);
            } else if (Math.abs(thirdTypeOfHorizontalPossibleWords.get(i).getLastFieldXCord() - thirdTypeOfHorizontalPossibleWords.get(i + 1).getFirstFieldXCord()) == 1) {
                Word newWord;
                newWord = generatingThirdTypeWord(thirdTypeOfHorizontalPossibleWords.get(i), thirdTypeOfHorizontalPossibleWords.get(i + 1));
                mergedWords.add(newWord);
            }
        }
        thirdTypeOfHorizontalPossibleWords.addAll(mergedWords);
        sortListOfWordsByYAxis(thirdTypeOfHorizontalPossibleWords);
    }

    private void sortListOfWordsByYAxis(List<Word> listOfWords) {
        listOfWords.sort(Comparator.comparing(word -> word.getListOfFields().get(0).getCordy()));
    }

    private void addFieldToRow(boolean condition, Word possibleWord, int rowIndex, int columnIndex, String type, Field[][] scrabbleMap) {
        if (condition) {
            int y = type.equals("FIRST") ? rowIndex - 1 : rowIndex + 1;
            possibleWord.getListOfFields().add(scrabbleMap[y][columnIndex]);
        }
        possibleWord.getListOfFields().add(scrabbleMap[rowIndex][columnIndex]);
    }

    private void addPossibleWordToList(List<Word> listOfPossibleWords, Word possibleWord) {
        possibleWord.sortListByXAxis();
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

    private List<Word> filterOneFieldPossibleWords(List<Word> possibleWords) {
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
        scrabbleMap[7][7].setLetter("S");
        scrabbleMap[7][8].setLetter("A");
        scrabbleMap[7][9].setLetter("S");
        scrabbleMap[6][9].setLetter("D");
        scrabbleMap[5][9].setLetter("F");

        scrabbleMap[4][9].setLetter("O");
        scrabbleMap[5][7].setLetter("P");
        scrabbleMap[5][8].setLetter("A");

        scrabbleMap[5][10].setLetter("A");
        /*scrabbleMap[5][6].setLetter("A");
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
        scrabbleMap[12][0].setLetter("A");
        scrabbleMap[10][0].setLetter("A");*/

    }

    public static void main(String[] args) {
        Board board = new Board();
        /*board.getBoard()[1][1].setLetter("a");
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
        board.getBoard()[13][12].setLetter("a");*/
        board.getBoard()[1][1].setLetter("a");
        board.show();
        HorizontalFieldsFinder horizontalFieldsFinder = new HorizontalFieldsFinder(board);
        List<List<Word>> listOfPossibleWords = horizontalFieldsFinder.findAllHorizontalPossibleWords();
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
        //HorizontalFieldsFinder.printMap();
        /*
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
        long start = System.currentTimeMillis();
        System.out.println(asd.stream().filter(word -> word.equals("??ak")).findAny());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        for (String s : asd) {
            if (s.equals("??ak"))
                System.out.println("??ak");
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

         */
    }
}
