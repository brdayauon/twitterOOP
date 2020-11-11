import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserViewWindow {
    private User user;
    private Stage stage;
    private Scene scene;
    private AnchorPane layout;
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


    }

    public void start(){
        this.setLayout();
        this.layout.getChildren().addAll(userIdTA,tweetMessageTA,currentFollowing,newsFeed,followUserBttn,postTweetBttn);

        this.stage.show();
    }


    //two text areas
    private TextArea userIdTA;
    private TextArea tweetMessageTA;

    //two list views
    private ListView currentFollowing;
    private ListView newsFeed;

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
