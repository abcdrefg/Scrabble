package wordFinder;

import ScrabbleBoard.Field;

import java.util.HashMap;

public class PossibleWords {
    private Field startField;
    private Field endField;
    private String word;

    public PossibleWords(Field startField, Field endField, String word){
        this.startField = startField;
        this.endField = endField;
        this.word = word;
    }

    public Field getStartField() {
        return startField;
    }

    public void setStartField(Field startField) {
        this.startField = startField;
    }

    public Field getEndField() {
        return endField;
    }

    public void setEndField(Field endField) {
        this.endField = endField;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


}
