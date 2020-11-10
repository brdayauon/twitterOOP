package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
            //USER VIEW
        Parent root = FXMLLoader.load(getClass().getResource("../userView.fxml"));
        primaryStage.setTitle("User View");

        //CONTROL PANEL
       // Parent root = FXMLLoader.load(getClass().getResource("../adminControlPanel1.fxml"));

        //WORKING
//        Parent root = FXMLLoader.load(getClass().getResource("../adminControlPanel.fxml"));
//        primaryStage.setTitle("Admin Control Panel");



        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
