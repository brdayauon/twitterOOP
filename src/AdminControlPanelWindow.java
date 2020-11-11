import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class AdminControlPanelWindow extends Application {
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //USER VIEW
//        Parent root = FXMLLoader.load(getClass().getResource("../userView.fxml"));
//        primaryStage.setTitle("User View");

        //CONTROL PANEL
        // Parent root = FXMLLoader.load(getClass().getResource("../adminControlPanel1.fxml"));

        //WORKING
        //  Parent root = FXMLLoader.load(getClass().getResource("../adminControlPanel.fxml"));
        primaryStage.setTitle("Admin Control Panel");
        button = new Button();
        button.setText("Click me");


        //  primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
