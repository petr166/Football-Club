package footballClub.view;

import footballClub.MainApp;
import footballClub.model.Player;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;
import java.util.Scanner;

/**
 * Player overview Controller class
 */

public class PlayerOverviewController {

    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> firstNameColumn;
    @FXML
    private TableColumn<Player, String> lastNameColumn;
    @FXML
    private TableColumn<Player, Integer> ageColumn;
    @FXML
    private TableColumn<Player, Integer> teamColumn;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button remove;
    @FXML
    private Button search;
    @FXML
    private TextField searchField;
    @FXML
    private Label message;


    // reference to the main application
    private MainApp mainApp;


    // constructor
    public  PlayerOverviewController() {}


    // initialize the controller class
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        teamColumn.setCellValueFactory(cellData -> cellData.getValue().teamProperty().asObject());
    }


    // method called by the main application to give a reference back to itself
    public  void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // method to add observable list data to the table
        playerTable.setItems(mainApp.getPlayerData());
    }


    // method to handle the remove button action
    @FXML
    private void handleRemovePlayer() {
        int selectedIndex = playerTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Remove player");
            String playerName = playerTable.getSelectionModel().getSelectedItem().getFirstName() +
                    " " + playerTable.getSelectionModel().getSelectedItem().getLastName();
            alert.setHeaderText(playerName);
            alert.setContentText("Are you sure you want to remove this player?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                playerTable.getItems().remove(selectedIndex);
            }
            else {
                alert.close();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Remove player");
            alert.setHeaderText(null);
            alert.setContentText("Please select a player to remove!");

            alert.showAndWait();
        }
    }


    // handle the add button action
    @FXML
    private void handleAddPlayer() {
        Player tempPlayer = new Player();
        boolean okClicked = mainApp.showPlayerEditDialog(tempPlayer);

        if (okClicked) {
            mainApp.getPlayerData().add(tempPlayer);
        }
    }


    // handle the edit button action
    @FXML
    private void handleEditPlayer() {
       Player selectedPlayer = playerTable.getSelectionModel().getSelectedItem();

        if (selectedPlayer != null) {
            mainApp.showPlayerEditDialog(selectedPlayer);
        }
        else {
            // if nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Edit player");
            alert.setHeaderText(null);
            alert.setContentText("Please select a player to edit!");

            alert.showAndWait();
        }
    }


    // handle the search button action
    @FXML
    private void handleSearch() {

        playerTable.getSelectionModel().clearSelection();
        message.setText(null);
        boolean found = false;

        if (searchField.getText() != null && !searchField.getText().isEmpty()) {
            String name = searchField.getText();
            ObservableList<Player> list = mainApp.getPlayerData();

            for (Player player : list) {
                if (player.getLastName().equalsIgnoreCase(name)) {
                    playerTable.getSelectionModel().select(player);
                    message.setText("Player found!");
                    found = true;
                }
            }

            if (!found) {
                message.setText("No such player!");
            }
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Search player");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the player's name!");
            alert.showAndWait();
        }

        add.setDefaultButton(true);
        search.setDefaultButton(false);
    }


    //searchField on mouse clicked
    @FXML
    private void searchFieldClicked() {
        add.setDefaultButton(false);
        search.setDefaultButton(true);
    }

}