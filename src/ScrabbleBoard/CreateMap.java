package ScrabbleBoard;

public class CreateMap {
    private String[][] Scrabblemap = new String[15][15];

    public CreateMap() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Scrabblemap[i][j] = "x";
                if ((i == 7) && (j == 7)) {
                    Scrabblemap[i][j] = "o";
                }
            }
        }
    }

    public void PrintMap() {
        String Row;
        for (int i = 0; i < 15; i++) {
            Row = "";
            for (int j = 0; j < 15; j++) {
                Row = Row + Scrabblemap[i][j];
            }
            System.out.println(Row);
        }
    }

    //Method that modifies ScrabbleMap with given value.
    //CoordinateAndValue is a [WordLenght][3] lenght array where [NumberOfLetter][0] is a X coordinate on the map, [NumberOfLetter][1] is a Y coordinate on map and [NumberOfLetter][2] is a value.
    public void ModifyMap(String[][] CoordinatesAndValue) {
        int TabLen = CoordinatesAndValue.length;
        for (int i = 0; i < TabLen; i++) {
            Scrabblemap[Integer.parseInt((CoordinatesAndValue[i][0]))][Integer.parseInt((CoordinatesAndValue[i][1]))] = CoordinatesAndValue[i][2];
        }
    }

    public String[][] GetMap() {
        return Scrabblemap;
    }
}
