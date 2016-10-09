package footballClub;

import footballClub.model.Player;
import footballClub.view.PlayerEditDialogController;
import footballClub.view.PlayerOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class
 */

public class MainApp extends Application {

    // fields
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Player> playerData = FXCollections.observableArrayList();


    // constructor
    public MainApp() {
        playerData.add(new Player("Banel", "Nicolita", 24, 1));
        playerData.add(new Player("Cristiano", "Ronaldo", 29, 1));
        playerData.add(new Player("Leo", "Messi", 27, 1));
        playerData.add(new Player("Cay", "Holmegaard", 22, 1));
        playerData.add(new Player("Claus", "Bowe", 24, 1));
    }


    // getters
    public ObservableList<Player> getPlayerData() {
        return playerData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Football Club");

        // set the app icon
        this.primaryStage.getIcons().add(new Image("file:resources/images/ball.png"));

        initRootLayout();

        showPlayerOverview();
    }


    // method to initialize the root layout
    public void initRootLayout() {
        try {
            // load root layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            // setScene, show
            Scene scene = new Scene(rootLayout, 600, 500);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // method to show the player overview inside the root layout
    public void showPlayerOverview() {
        try {
            // load player overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PlayerOverview.fxml"));
            AnchorPane personOverview = loader.load();

            // setting player overview into the center of root layout
            rootLayout.setCenter(personOverview);

            // method to give the controller access to the main app
            PlayerOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // method to show the edit dialog for a certain player
    public boolean showPlayerEditDialog(Player player) {
        try {
            // load the FXML file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PlayerEditDialog.fxml"));
            AnchorPane page = loader.load();

            // crate the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit player");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // set the player in the controller
            PlayerEditDialogController controller = loader.getController();
            controller.setEditDialogStage(dialogStage);
            controller.setPlayer(player);

            // show the dialog
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    // MAIN
    public static void main(String[] args) {
        launch(args);
    }

}