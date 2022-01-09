package fieldFinder;

import ScrabbleBoard.Field;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Word implements Cloneable {
    private final List<Field> listOfFields = new ArrayList<>();

    public List<Field> getListOfFields() {
        return listOfFields;
    }

    public void setListOfFields(List<Field> listOfFields) {
        this.listOfFields.addAll(listOfFields);
    }

    public void sortListByYAxis() {
        listOfFields.sort(Comparator.comparing(Field::getCordy));
    }

    public void sortListByXAxis() {
        listOfFields.sort(Comparator.comparing(Field::getCordx));
    }

    public int getLastIndex() {
        return listOfFields.size() - 1;
    }

    public int getLastFieldXCord() {
        return listOfFields.get(getLastIndex()).getCordx();
    }

    public int getLastFieldYCord() {
        return listOfFields.get(getLastIndex()).getCordy();
    }

    public int getFirstFieldXCord() {
        return listOfFields.get(0).getCordx();
    }

    public int getFirstFieldYCord() {
        return listOfFields.get(0).getCordy();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Word)) {
            return false;
        }

        final Word other = (Word) obj;

        return getFirstFieldXCord() == other.getFirstFieldXCord() || getFirstFieldYCord() == other.getFirstFieldYCord()
                || getLastFieldXCord() == other.getLastFieldXCord() || getLastFieldYCord() == other.getLastFieldYCord();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result * getListOfFields().hashCode();
        return result;
    }


}