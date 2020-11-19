import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserViewWindow {
    private User user;
    private Stage stage;
    private Scene scene;
    private AnchorPane layout;

    //reference to singleton
    private AdminControlPanel adminSingleton;

    public UserViewWindow(User user){
        this.userIdTA = new TextArea();
        this.tweetMessageTA = new TextArea();

        this.newsFeed = new ListView<>();
        this.currentFollowing = new ListView<>();

        this.followUserBttn = new Button("Follow User");
        this.postTweetBttn = new Button("Post Tweet");

        this.user = user;

        //creates new window
        this.stage = new Stage();
        this.layout = new AnchorPane();

        this.scene = new Scene(layout, 400, 400);
        this.stage.setScene(scene);
        this.stage.setTitle(user.getUID() + " View");

        this.adminSingleton = AdminControlPanel.getInstance();

    }

    public void addTweetToNewsFeed(String tweetToAdd) {
        this.newsFeed.getItems().add(tweetToAdd);
    }

    public void start(){
        this.setLayout();
        this.layout.getChildren().addAll(userIdTA,tweetMessageTA,currentFollowing,newsFeed,followUserBttn,postTweetBttn);


        this.followUserBttn.setOnAction(e-> {
            User followUser = adminSingleton.getUser(this.userIdTA.getText());

            if (followUser != null){
                boolean followed = this.user.follow(followUser);

                if (followed){
                    this.currentFollowing.getItems().add(followUser);

                    followUser.addOtherToFollower(this.user); //this current user is following that other user

                    followUser.addObserver(this.user);
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
            if (tweetMessageTA.getText().equals("")){
                System.out.println("null, TWEET CAN'T BE BLANK");
            }else{
                //user.tweet(tweetMessageTA.getText());
                //String messageToTweet = this.user.tweet(this.tweetMessageTA.getText());
                String messageToTweet = this.tweetMessageTA.getText();
                //this.user.addNewTweet(this.user.getUID() + " : " + messageToTweet);
                this.user.tweet(messageToTweet);
                this.newsFeed.getItems().add(this.user.getUID() + " : " + messageToTweet);

                this.adminSingleton.addTweet(messageToTweet);


                this.tweetMessageTA.setText("");


            }

        });


        this.stage.show();
    }

    public void addNews(String newsToAdd){
        this.newsFeed.getItems().add(newsToAdd);
    }


    //two text areas
    private TextArea userIdTA;
    private TextArea tweetMessageTA;

    //two list views
    private ListView currentFollowing;
    private ListView<String> newsFeed;

    //two buttons
    private Button followUserBttn;
    private Button postTweetBttn;

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
    }
}
