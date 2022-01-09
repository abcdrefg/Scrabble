package replay;

import ScrabbleBoard.Field;
import gui.GuiMove;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Replay {
    private static Field[][]scrabbleMap = new Field[15][15];
    public static void start(List<PlayerTurn> listOfTurns){
        Scanner scanner = new Scanner(System.in);
        listOfTurns.forEach(playerTurn -> {
            System.out.println("Gracz nr. " + playerTurn.getPlayerName() + " Tura nr. " + playerTurn.getTurnNumber() + " Zdobyte pkt. " + playerTurn.getPoints());
            playerTurn.getListOfMoves().forEach(move -> {
                int x = move.getxCord();
                int y = move.getyCord();
                String letter = move.getLetter();
                scrabbleMap[x][y].setLetter(letter);
                printMap();
                System.out.println("--- \n");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scanner.nextLine();
            });
        });
    }

    public static void createMap() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                scrabbleMap[i][j] = new Field(" ", 0, i, j, true);
                if ((i == 7) && (j == 7)) {
                    scrabbleMap[i][j] = new Field("0", 0, i, j, false);
                }
            }
        }
    }

    public static void printMap() {
        StringBuilder Row;
        for (int i = 0; i < 15; i++) {
            Row = new StringBuilder();
            for (int j = 0; j < 15; j++) {
                Row.append('|').append(scrabbleMap[i][j].getLetter());
            }
            System.out.println(Row);
        }
    }

    public static void main(String[] args) {
        Replay.createMap();
        Random random = new Random();
        List<PlayerTurn> listOfTurns = new ArrayList<>();
        PlayerTurn playerTurn;
        for (int k =0; k<4; k++) {
            List<GuiMove> listOfMoves = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                listOfMoves.add(new GuiMove(random.nextInt(14), random.nextInt(14), "S"));
            }
            //Collections.reverse(listOfMoves);
            playerTurn = new PlayerTurn(String.valueOf(k), false);
            playerTurn.setPoints(random.nextInt(10));
            playerTurn.setListOfMoves(listOfMoves);
            listOfTurns.add(playerTurn);
        }
        //Collections.reverse(listOfTurns);
        Replay.start(listOfTurns);
    }

}
