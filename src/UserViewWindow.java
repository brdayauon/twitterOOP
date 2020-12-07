import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserViewWindow {
    private final User user;
    private final Stage stage;
    private final AnchorPane layout;

    private final AdminControlPanel adminControlPanelSingletonInstance;

    public UserViewWindow(User user){
        this.userIdTA = new TextArea();
        this.userIdTA.setPromptText("Enter User ID");
        this.tweetMessageTA = new TextArea();
        this.tweetMessageTA.setPromptText("Type to tweet");
        this.newsFeed = new ListView<>();
        this.currentFollowing = new ListView<>();

        this.followUserBttn = new Button("Follow User");
        this.postTweetBttn = new Button("Post Tweet");

        this.user = user;

        this.lastUpdateTime = new Label("Last Updated Time: " + user.getLastUpdateTime());
        this.creationTime = new Label("Creation Time: " + user.getCreationTime());

        //creates new window
        this.stage = new Stage();
        this.layout = new AnchorPane();

        Scene scene = new Scene(layout, 400, 400);
        this.stage.setScene(scene);
        this.stage.setTitle(user.getUID() + " View");

        this.adminControlPanelSingletonInstance = AdminControlPanel.getInstance();


    }

    public void addTweetToNewsFeed(String tweetToAdd) {
        this.newsFeed.getItems().add(tweetToAdd);
    }

    public void start(){
        showTweets(this.user);
        this.followUserBttn.setOnAction(e-> {
            String inputUserToFollow = this.userIdTA.getText();
            User followUser = adminControlPanelSingletonInstance.getUser(inputUserToFollow);

            if (followUser != null){
                boolean followed = this.user.follow(followUser);

                if (followed){
                    this.currentFollowing.getItems().add(followUser);

                    followUser.addOtherToFollower(this.user); //this current user is following that other user

                    followUser.register(this.user);
                }
                else{
                    System.out.println("already following");
                    String message = "ALREADY FOLLOWING OR CAN'T FOLLOW SELF";
                    new popUpDialogDisplayWindow(message, "NOT A VALID USER").showDialogWindow();
                }
            }
            else{
                System.out.println("NOT A VALID USER");
                String message = "ERROR: ";
                new popUpDialogDisplayWindow(message, "NOT A VALID USER").showDialogWindow();
            }
            //reset text area
            this.userIdTA.setText("");

        });

        //post tweets
        this.postTweetBttn.setOnAction(e -> {
            showTweets(this.user);
            showNewsFeed(this.user);
            if (tweetMessageTA.getText().equals("")){
                System.out.println("null, TWEET CAN'T BE BLANK");
            }else{
                //user.tweet(tweetMessageTA.getText());
                //String messageToTweet = this.user.tweet(this.tweetMessageTA.getText());
                String messageToTweet = this.tweetMessageTA.getText();
                this.user.addTweet(this.user.getUID() + ": " + messageToTweet);
                this.adminControlPanelSingletonInstance.addTweet(messageToTweet);

                //this.user.tweet(messageToTweet);
                //this is what gets posted after every tweet
                this.newsFeed.getItems().add(this.user.getUID() + " : " + messageToTweet);
                this.newsFeed.getItems().addAll();
                this.tweetMessageTA.setText("");

                this.updateTime(System.currentTimeMillis());
                this.adminControlPanelSingletonInstance.setLastUpdatedUser(this.user);
            }

        });

        this.setLayout();
        this.layout.getChildren().addAll(userIdTA,tweetMessageTA,currentFollowing,newsFeed,followUserBttn,postTweetBttn, creationTime, lastUpdateTime);

        this.stage.show();
    }

    //iterate over the current user's following and get their tweets
    public void showTweets(User currentUser){
        ArrayList<User> usersFollowing = currentUser.getFollowing();
        for(User user : usersFollowing){
            //for posts in user's post history
            ArrayList<String> messages = user.getMessages();
            for(String posts : messages) {
                this.newsFeed.getItems().add(user.getUID() + " : " + posts);
                System.out.println("POSTS: "+ posts);
            }
        }
    }

    public void showNewsFeed(User currentUser){
        ArrayList<String> temp = currentUser.getNewsFeed();
        boolean test = true;
        for (String feed: temp){
            this.newsFeed.getItems().add(user.getUID() + " : " + feed);
            test = false;
        }
        if (test)
            System.out.println("THIS IS NOT WORKING");
    }

    public void addTweet(String addTweetToFeed){
        this.newsFeed.getItems().add(addTweetToFeed);
    }


    //two text areas
    private final TextArea userIdTA;
    private final TextArea tweetMessageTA;

    //two list views
    private final ListView currentFollowing;
    private final ListView<String> newsFeed;

    //two buttons
    private final Button followUserBttn;
    private final Button postTweetBttn;

    public void setLayout(){
        //two text areas
        userIdTA.setLayoutX(14);
        userIdTA.setLayoutY(14);
        userIdTA.setPrefHeight(11);
        userIdTA.setPrefWidth(303);

        //"14.0" layoutY="210.0" prefHeight="43.0" prefWidth="303.0"
        tweetMessageTA.setLayoutX(14);
        tweetMessageTA.setLayoutY(210);
        tweetMessageTA.setPrefHeight(43);
        tweetMessageTA.setPrefWidth(303);

        //two list views
        currentFollowing.setLayoutX(14);
        currentFollowing.setLayoutY(72);
        currentFollowing.setPrefHeight(128);
        currentFollowing.setPrefWidth(615);

        newsFeed.setLayoutX(13);
        newsFeed.setLayoutY(268);
        newsFeed.setPrefHeight(110);
        newsFeed.setPrefWidth(615);


        //two buttons
        followUserBttn.setLayoutX(320);
        followUserBttn.setLayoutY(14);
        followUserBttn.setPrefHeight(42);
        followUserBttn.setPrefWidth(309);

        postTweetBttn.setLayoutX(323);
        postTweetBttn.setLayoutY(211);
        postTweetBttn.setPrefHeight(42);
        postTweetBttn.setPrefWidth(303);

        creationTime.setLayoutX(15);
        creationTime.setLayoutY(253);
        creationTime.setPrefHeight(22);
        creationTime.setPrefWidth(223);

        lastUpdateTime.setLayoutX(323);
        lastUpdateTime.setLayoutY(256);
        lastUpdateTime.setPrefHeight(17);
        lastUpdateTime.setPrefWidth(274);
    }

    //
    private final Label creationTime;
    private final Label lastUpdateTime;

    public void updateTime(long currentTimeMillis) {
        this.lastUpdateTime.setText("Last Updated Time: " + currentTimeMillis);
    }
}
