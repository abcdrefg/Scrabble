package gui;

import javafx.scene.control.Alert;

public class AlertHandler {

    public final static String ERROR = "Error";
    public final static String WARNING = "Warning";
    public final static String DATABASE_ERROR = "Błąd bazy danych";
    public final static String LOGIN_WARNING = "Błąd logowania";
    public final static String IO_EXPECTATION = "IOExpectation";
    public final static String NO_LOGIN_OR_PASSWORD = "Nie podano hasła lub loginu!";
    public final static String USER_DOES_NOT_EXIST = "Taki użytkownik nie istnieje!";
    public final static String CONFIRMATION = "Confirmation";
    public final static String SUCCESSFUL_REGISTRATION = "Udana rejestracja użytkownika";
    public final static String REGISTRATION_ERROR = "Błąd rejestracji";
    public final static String NOT_IDENTICAL_PASSWORDS_OR_EMPTY_FIELDS = "Niektóre z podanych pól są puste lub podane hasła nie są identyczne!";
    public final static String NOT_ENOUGH_PLAYERS = "Mało graczy!";
    public final static String NOT_ENOUGH_PLAYERS_TO_START_GAME = "Za mało graczy, aby rozpocząć rozgrywkę!";
    public final static String PLAYER_TYPE_NOT_SELECTED = "Nie wybrano gracza!";
    public final static String CHOOSE_PLAYER_TYPE = "Wybierz rodzaj gracza, a następnie zaloguj się na konto lub wybierz poziom trudności!";
    public final static String PLAYER_IS_LOGGED = "Gracz jest zalogowany!";
    public final static String USER_IS_ALREADY_LOGGED = "Użytkownik o danym loginie jest już zalogowany!";
    public final static String DIFFICULTY_IS_NOT_SELECTED = "Nie wybrano poziomu trudności!";
    public final static String CHOOSE_COMPUTER_DIFFICULTY = "Wybierz poziom trudności komputera!";
    public final static String CHOOSE_LETTER_BEFORE_MOVE = "Najpierw wybierz literę którą chcesz postawić";
    public final static String WRONG_MOVE = "Niedozwolony ruch";
    public final static String CANT_SET_THIS_LETTER_HERE = "Nie możesz tu postawić litery";
    public final static String ALREADY_USED_LETTER = "Użyłeś już tę literę";
    public final static String INCORRECT_WORD = "Ułożone słowa nie są poprawne";
    public final static String EMPTY_LETTER = "Najpierw wybierz literę którą chcesz wymienić";
    public final static String END_OF_THE_GAME = "Koniec gry";
    public final static String END_OF_AI_MOVE = "Koniec tury gracza AI";
    public final static String DURING_CHANGING = "Jesteś podczas wymieniania liter, nie możesz postawić litery na mapie";

    public void display(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        try {
            alert.showAndWait();
        } catch (Exception e){
            alert.show();
        }
    }

}
