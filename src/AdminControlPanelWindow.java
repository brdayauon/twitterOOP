import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;


public class AdminControlPanelWindow extends Application {

    private TreeItem<userEntity> treeItemList;
    private TreeItem<userEntity> clickedUser;
    private int totalUsers;
    private int totalUserGroups;
    private final ArrayList<User> users;
    private final ArrayList<String> userGroups;
    private final HashMap<String, UserViewWindow> userViewWindows = new HashMap<String, UserViewWindow>();
    private final AdminControlPanel adminControlPanelSingletonInstance = AdminControlPanel.getInstance();
   // private UserGroup userGroup;
    private ButtonVisitor visitor;
    //need to fix
    private int totalMessages;
    private double positivePercentage;
    private final String[] positiveWords = { "good", "great", "excellent", "awesome", "best", "cool", "nice", "clear" };


    public static void main(String[] args) {
        launch(args);
    }

    public AdminControlPanelWindow(){
        this.userGroups = new ArrayList<String>();
        //TEXT AREAS AND TREEVIEW
        this.userIdTA = new TextArea();
        this.userIdTA.setPromptText("USER ID");
        this.groupIDTA = new TextArea();
        this.groupIDTA.setPromptText("Group ID");
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
            if (this.clickedUser.getValue() instanceof UserGroup){
                String clickedON = this.clickedUser.getValue().getUID();
                for (String group : userGroups){
                    if (group.equals(clickedON)){
                        //check if userGroup exists
                        String message = ("ERROR: USERGROUP ALRDY EXIST" );
                        new popUpDialogDisplayWindow(message, "ERROR").showDialogWindow();
                    }
                }
            }
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
                userGroups.add(newGroup);
                this.groupIDTA.setText("");
                this.addUserGroupToTreeView(newGroup);
                this.totalUserGroups += 1;
            }
            else {
                System.out.println("ERROR ON ADDING GROUP");
                String message = ("ERROR: USERGROUP ALRDY EXIST" );
                new popUpDialogDisplayWindow(message, "ERROR").showDialogWindow();
            }

        });

        this.treeView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) ->{
            if (newSelection != null){
                if(newSelection.getValue() instanceof  UserGroup)
                    this.treeItemList = newSelection;
                else if (newSelection.getValue() instanceof User){
                    this.clickedUser = newSelection;
                }
            }
        });
        openUserViewBttn.setOnAction(e-> {
            if(this.clickedUser != null) {
                UserViewWindow window = new UserViewWindow((User) this.clickedUser.getValue());
                //userViewWindows.put(this.clickedUser.getValue().getUID(), window);
                this.adminControlPanelSingletonInstance.setUserViewWindow(this.clickedUser.getValue().getUID(), window);
                window.start();
            }
        });

          /*
    A few analysis features are needed in the admin control panel:
    1) output the total number of users; DONE
    2) output the total number of groups; DONE
    3) output the total number of Tweet messages in all the users’ news feed; DONE
    4) output the percentage of the positive Tweet messages in all the users’ news feed
    (the message containing positive words, such as good, great, excellent, etc.)
    Free free to decide the positive words.
     */


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
            countPositiveWordsInTweets();
            String message = ("Positive Percentage: " + this.positivePercentage + "%");
             new popUpDialogDisplayWindow(message, "Positive Percentage").showDialogWindow();

//            String message = ("Positive Percentage: " + visitor.visitPositivePercentage() + "%");
//            new popUpDialogDisplayWindow(message, "Positive Percentage").showDialogWindow();

        });

        showMessagesTotalBttn.setOnAction(e-> {
            getTotalMessageCount();
            String message = ("Message total: " + this.totalMessages);
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
    private final Button addUserBttn;
    private final Button addGroupBttn;
    private final Button openUserViewBttn;
    private final Button showGroupTotalBttn;
    private final Button showUserTotalBttn;
    private final Button showMessagesTotalBttn;
    private final Button showPositivePercentageBttn;
    private final TextArea userIdTA;
    private final TextArea groupIDTA;
    private final TreeView<userEntity> treeView;
    private final TreeItem<userEntity> root;

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

    public void getTotalMessageCount() {
//        int totalMessageCount = 0;
//        for (User user : users){
//            totalMessageCount += user.getMessageCount();
//        }
        ArrayList<String> listOfTweets = adminControlPanelSingletonInstance.getTweets();

        this.totalMessages = listOfTweets.size();
    }

    public void countPositiveWordsInTweets(){
        //need to get tweets in admin control pane;
        ArrayList<String> listOfTweets = adminControlPanelSingletonInstance.getTweets();
        double positivePercentage;
        double positiveWordCount = 0;

        //TERRIBLE n^3 solution. MUST OPTIMIZE LATER. was quick brute force
        for (String positiveWord : positiveWords){
            for(String messages : listOfTweets){
                String message = messages.toLowerCase();
                String[] word = message.split(" ");
                for(String i : word) {
                    if (positiveWord.equals(i)) {
                        positiveWordCount += 1;
                    }
                }
            }
        }

        positivePercentage =  (positiveWordCount/ (double)(listOfTweets.size()))*100;

        this.positivePercentage = positivePercentage;
    }

    public UserViewWindow getUserViewWindow(String uID){
        if (uID != null)
            return this.userViewWindows.get(uID);

        return null;
    }


}
