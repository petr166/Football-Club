package footballClub.view;

import footballClub.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Player edit dialog controller class
 */

public class PlayerEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField teamField;


    private Stage editDialogStage;
    private Player player;
    private boolean okClicked = false;


    // initialize the controller class
    @FXML
    private void initialize() {}


    // set the stage of the dialog
    public void setEditDialogStage(Stage editDialogStage) {
        this.editDialogStage = editDialogStage;
    }


    // set the player to be edited in the dialog
    public void setPlayer(Player player) {
        this.player = player;


        firstNameField.setText(player.getFirstName());
        lastNameField.setText(player.getLastName());
        ageField.setText(Integer.toString(player.getAge()));
        teamField.setText(Integer.toString(player.getTeam()));
        teamField.setPromptText("1 or 2");


    }


    // returns true if user clicked OK, false otherwise
    public boolean isOkClicked() {
        return okClicked;
    }


    // handle OK click
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            player.setFirstName(firstNameField.getText());
            player.setLastName(lastNameField.getText());
            player.setAge(Integer.parseInt(ageField.getText()));
            player.setTeam(Integer.parseInt(teamField.getText()));

            okClicked = true;
            editDialogStage.close();
        }
    }


    // handle Cancel click
    @FXML
    private void handleCancel() {
        editDialogStage.close();
    }


    // validate the user input in the text fields
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }

        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }

        if (ageField.getText() == null || ageField.getText().length() == 0) {
            errorMessage += "No valid age!\n";
        }
        else {
            try {
                Integer.parseInt(ageField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid age!\n";
            }
        }

        if (teamField.getText() == null || teamField.getText().length() == 0) {
            errorMessage += "No valid team selection\n";
        }
        else {
            try {
                Integer.parseInt(teamField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid team selection!\n";
            }
        }


        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editDialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please correct the invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}