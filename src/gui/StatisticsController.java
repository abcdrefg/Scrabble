package gui;

import User.Statistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class StatisticsController {

    @FXML
    private TableView<Statistics> easyAITableView;

    @FXML
    private TableColumn<Statistics, String> easyAIPlayerColumn;

    @FXML
    private TableColumn<Statistics, Integer> easyAIWonGamesColumn;

    @FXML
    private TableColumn<Statistics, Integer> easyAIAllGamesColumn;

    @FXML
    private TableColumn<Statistics, Double> easyAIWinPercentColumn;

    @FXML
    private TableView<Statistics> mediumAITableView;

    @FXML
    private TableColumn<Statistics, String> mediumAIPlayerColumn;

    @FXML
    private TableColumn<Statistics, Integer> mediumAIWonGamesColumn;

    @FXML
    private TableColumn<Statistics, Integer> mediumAIAllGamesColumn;

    @FXML
    private TableColumn<Statistics, Double> mediumAIWinPercentColumn;

    @FXML
    private TableView<Statistics> hardAITableView;

    @FXML
    private TableColumn<Statistics, String> hardAIPlayerColumn;

    @FXML
    private TableColumn<Statistics, Integer> hardAIWonGamesColumn;

    @FXML
    private TableColumn<Statistics, Integer> hardAIAllGamesColumn;

    @FXML
    private TableColumn<Statistics, Double> hardAIWinPercentColumn;

    @FXML
    private TableView<Statistics> pointFactorTableView;

    @FXML
    private TableColumn<Statistics, String> pointFactorPlayerColumn;

    @FXML
    private TableColumn<Statistics, Double> pointFactorPFColumn;

    @FXML
    private Button returnButton;

    @FXML
    private TableView<Statistics> pvpTableView;

    @FXML
    private TableColumn<Statistics, String> pvpPlayerColumn;

    @FXML
    private TableColumn<Statistics, Integer> pvpWonGamesColumn;

    @FXML
    private TableColumn<Statistics, Integer> pvpAllGamesColumn;

    @FXML
    private TableColumn<Statistics, Double> pvpWinPercentColumn;



    private ObservableList<Statistics> statisticsObservableList;

    @FXML
    void returnToMenuPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Menu.fxml"));
        ControllersHelper.changeScene(returnButton, fxmlLoader);
    }

    public void initializeStatistics(List<Statistics> listOfStatistics) {
        statisticsObservableList = FXCollections.observableArrayList(listOfStatistics);
        initialize();
    }


    public void initialize() {
        easyAIPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("playerLogin"));
        easyAIWonGamesColumn.setCellValueFactory(new PropertyValueFactory<>("easyAIwins"));
        easyAIAllGamesColumn.setCellValueFactory(new PropertyValueFactory<>("easyAIgames"));
        easyAIWinPercentColumn.setCellValueFactory(new PropertyValueFactory<>("easyAIwinPercent"));
        easyAITableView.setItems(statisticsObservableList);

        mediumAIPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("playerLogin"));
        mediumAIWonGamesColumn.setCellValueFactory(new PropertyValueFactory<>("hardAIwins"));
        mediumAIAllGamesColumn.setCellValueFactory(new PropertyValueFactory<>("hardAIgames"));
        mediumAIWinPercentColumn.setCellValueFactory(new PropertyValueFactory<>("mediumAIwinPercent"));
        mediumAITableView.setItems(statisticsObservableList);

        hardAIPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("playerLogin"));
        hardAIWonGamesColumn.setCellValueFactory(new PropertyValueFactory<>("hardAIwins"));
        hardAIAllGamesColumn.setCellValueFactory(new PropertyValueFactory<>("hardAIgames"));
        hardAIWinPercentColumn.setCellValueFactory(new PropertyValueFactory<>("hardAIwinPercent"));
        hardAITableView.setItems(statisticsObservableList);

        pvpPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("playerLogin"));
        pvpWonGamesColumn.setCellValueFactory(new PropertyValueFactory<>("pvpWins"));
        pvpAllGamesColumn.setCellValueFactory(new PropertyValueFactory<>("pvpGames"));
        pvpWinPercentColumn.setCellValueFactory(new PropertyValueFactory<>("pvpWinPercent"));
        pvpTableView.setItems(statisticsObservableList);

        pointFactorPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("playerLogin"));
        pointFactorPFColumn.setCellValueFactory(new PropertyValueFactory<>("pointFactor"));
        pointFactorTableView.setItems(statisticsObservableList);
    }
}

