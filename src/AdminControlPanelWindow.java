import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.util.ArrayList;


public class AdminControlPanelWindow extends Application {

    private AdminControlPanel adminControlPanelSingletonInstance = AdminControlPanel.getInstance();
    private TreeItem<userEntity> treeItemList;
    private TreeItem<userEntity> clickedUser;
    private int totalUsers;
    private int totalUserGroups;
    private ArrayList<User> users;

    private ButtonVisitor visitor;
    //need to fix
    private int totalMessages;
    private double positivePercentage;

    public static void main(String[] args) {
        launch(args);
    }

    public AdminControlPanelWindow(){
        //TEXT AREAS AND TREEVIEW
        this.userIdTA = new TextArea("User ID");
        this.groupIDTA = new TextArea("Group ID");
        String rootName = "Root";
        this.root = new TreeItem<userEntity>(new UserGroup(rootName));
        this.root.setExpanded(true);
        this.treeView = new TreeView(root);
        treeView.setShowRoot(true);
        this.totalUserGroups = 0;
        this.totalUsers = 0;
        this.totalMessages = 0;
        this.positivePercentage = 0;
        this.clickedUser = null;
        this.treeItemList = this.root;
        this.users = new ArrayList<>();
        //BUTTONS
        this.addUserBttn = new Button("Add User");
        this.addGroupBttn = new Button("Add Group");
        this.openUserViewBttn = new Button("Open User View");
        this.showGroupTotalBttn = new Button("Show Group Total");
        this.showUserTotalBttn = new Button("Show User Total");
        this.showMessagesTotalBttn = new Button("Show Messages Total");
        this.showPositivePercentageBttn = new Button("Show Positive Percentage");

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

        //add user to treeview
        addUserBttn.setOnAction(e->{
            System.out.println("Pressing Add User Button");
            //String newUser = this.userIdTA.getText();
            User newUser = new User(this.userIdTA.getText());
            boolean success = this.adminControlPanelSingletonInstance.addUser(newUser.toString());
            if (success) {
                System.out.println("Successfully Added: " + newUser);
                this.userIdTA.setText("");
                this.addUserToTreeView(newUser.toString());
                this.totalUsers += 1;
                this.users.add(newUser);
            }
            else {
                System.out.println("ERROR");
            }
        });

        //adds group to tree view
        addGroupBttn.setOnAction(e -> {
            String newGroup = this.groupIDTA.getText();
            boolean success = this.adminControlPanelSingletonInstance.addUserGroup(newGroup);
            TreeItem<userEntity> groupNode = new TreeItem<userEntity>(new UserGroup(newGroup));
            root.nextSibling(groupNode);
            if (success){
                System.out.println("Successfully added group: " + newGroup);
                this.groupIDTA.setText("");
                this.addUserGroupToTreeView(newGroup);
                this.totalUserGroups += 1;
            }
            else {
                System.out.println("ERROR ON ADDING GROUP");
            }

        });

        this.treeView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) ->{
            if (newSelection != null){
                if(newSelection.getValue() instanceof  UserGroup)
                    this.treeItemList = newSelection;
                else{
                    this.clickedUser = newSelection;
                }
            }
        });
        openUserViewBttn.setOnAction(e-> {
            if(this.clickedUser != null)
                new UserViewWindow((User)this.clickedUser.getValue()).start();
        });

        showUserTotalBttn.setOnAction(e-> {
            String message = ("Total user amount: " + this.totalUsers);
            new popUpDialogDisplayWindow(message, "User Total").showDialogWindow();
        });

        showGroupTotalBttn.setOnAction(e->{
            String message = ("Total Group Amount: " + this.totalUserGroups);
            new popUpDialogDisplayWindow(message, "Total User Groups").showDialogWindow();
        });

        //need to fix these 2 buttons
        showPositivePercentageBttn.setOnAction(e -> {
            String message = ("Positive Percentage: " + this.positivePercentage + "%");
             new popUpDialogDisplayWindow(message, "Positive Percentage").showDialogWindow();
        });

        showMessagesTotalBttn.setOnAction(e-> {

            String message = ("Message total: " + getTotalMessageCount() + this.totalMessages);
            System.out.println(getTotalMessageCount());
            new popUpDialogDisplayWindow(message, "Total Messages").showDialogWindow();
        });
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
    private TreeView<userEntity> treeView;
    private TreeItem<userEntity> root;

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

    //Add users to the tree view

    private void addUserToTreeView(String uID){
        TreeItem<userEntity> newUser = new TreeItem<userEntity>(new User(uID));
        newUser.setExpanded(true);
        this.treeItemList.getChildren().add(newUser);
    }

    //Add user group to the tree View.
    private void addUserGroupToTreeView(String userGroupID){
        TreeItem<userEntity> newUserGroup = new TreeItem<>(new UserGroup(userGroupID));
        newUserGroup.setExpanded(true);
        this.treeItemList.getChildren().add(newUserGroup);
    }

    public int getTotalMessageCount() {
        int totalMessageCount = 0;
        for (User user : users){
            totalMessageCount += user.getMessageCount();
        }
        return totalMessageCount;
    }


}
