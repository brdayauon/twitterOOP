import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class AdminControlPanelWindow extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        //USER VIEW
//        Parent root = FXMLLoader.load(getClass().getResource("../userView.fxml"));
//        primaryStage.setTitle("User View");
//        //CONTROL PANEL
//        // Parent root = FXMLLoader.load(getClass().getResource("../adminControlPanel1.fxml"));
//        //WORKING
//        //  Parent root = FXMLLoader.load(getClass().getResource("../adminControlPanel.fxml"));


        primaryStage.setTitle("Admin Control Panel");

        //TEXT AREAS AND TREEVIEW
        userIdTA = new TextArea("User ID");
        groupIDTA = new TextArea("Group ID");
        treeView = new TreeView();


        //BUTTONS
        addUserBttn = new Button("Add User");
        addGroupBttn = new Button("Add Group");
        openUserViewBttn = new Button("Open User View");
        showGroupTotalBttn = new Button("Show Group Total");
        showUserTotalBttn = new Button("Show User Total");
        showMessagesTotalBttn = new Button("Show Messages Total");
        showPositivePercentageBttn = new Button("Show Positive Percentage");




        //BUTTON ACTIONS
//        button.setOnAction(e -> {
//            System.out.println("hello world");
//        });


        //  primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(addGroupBttn, addUserBttn, openUserViewBttn, showGroupTotalBttn,
                showUserTotalBttn,showMessagesTotalBttn,showPositivePercentageBttn, userIdTA,groupIDTA,treeView);
        setLayOuts();

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*BUTTONS/TextArea/TreeView TO PUT  */
    private Button addUserBttn;
    private Button addGroupBttn;
    private Button openUserViewBttn;
    private Button showGroupTotalBttn;
    private Button showUserTotalBttn;
    private Button showMessagesTotalBttn;
    private Button showPositivePercentageBttn;
    private TextArea userIdTA;
    private TextArea groupIDTA;
    private TreeView treeView;

    private void setLayOuts(){

        //TextArea Fields AND TREEVIEW
        userIdTA.setLayoutX(248);
        userIdTA.setLayoutY(14);
        userIdTA.setPrefHeight(43);
        userIdTA.setPrefWidth(192);

        groupIDTA.setLayoutX(248);
        groupIDTA.setLayoutY(64);
        groupIDTA.setPrefHeight(43);
        groupIDTA.setPrefWidth(192);

        treeView.setLayoutX(14);
        treeView.setLayoutY(14);
        treeView.setPrefHeight(345);
        treeView.setPrefWidth(212);

        //ALL BUTTONS
        addUserBttn.setLayoutX(450);
        addUserBttn.setLayoutY(14);
        addUserBttn.setPrefHeight(43);
        addUserBttn.setPrefWidth(176);

        addGroupBttn.setLayoutX(450);
        addGroupBttn.setLayoutY(64);
        addGroupBttn.setPrefWidth(176);
        addGroupBttn.setPrefHeight(43);

        openUserViewBttn.setLayoutX(251);
        openUserViewBttn.setLayoutY(120);
        openUserViewBttn.setPrefWidth(378);
        openUserViewBttn.setPrefHeight(43);

        showUserTotalBttn.setLayoutX(258);
        showUserTotalBttn.setLayoutY(236);
        showUserTotalBttn.setPrefWidth(182);
        showUserTotalBttn.setPrefHeight(61);

        showPositivePercentageBttn.setLayoutX(450);
        showPositivePercentageBttn.setLayoutY(303);
        showPositivePercentageBttn.setPrefHeight(55);
        showPositivePercentageBttn.setPrefWidth(176);

        showMessagesTotalBttn.setLayoutX(258);
        showMessagesTotalBttn.setLayoutY(304);
        showMessagesTotalBttn.setPrefHeight(55);
        showMessagesTotalBttn.setPrefWidth(182);

        showGroupTotalBttn.setLayoutX(450);
        showGroupTotalBttn.setLayoutY(234);
        showGroupTotalBttn.setPrefWidth(176);
        showGroupTotalBttn.setPrefHeight(61);
    }
}
