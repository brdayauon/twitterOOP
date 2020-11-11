import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class popUpDialogDisplayWindow {

    private Scene scene;
    private Stage stage;
    private Pane layout;
    private String message;
    public popUpDialogDisplayWindow(String message, String windowTitle){
        stage = new Stage();
        this.layout = new Pane();
        this.label = new Label();
        this.scene = new Scene(layout, 300, 300);
        this.stage.setScene(scene);
        this.message = message;
        this.stage.setTitle(windowTitle);
    }
    public void showDialogWindow(){
        this.setLayout();
        this.layout.getChildren().add(label);
        this.label.setText(message);
        this.stage.show();
    }
    private Label label;
    public void setLayout(){
        label.setLayoutX(89);
        label.setLayoutY(41);
        label.setPrefHeight(112);
        label.setPrefWidth(280);
    }
}
