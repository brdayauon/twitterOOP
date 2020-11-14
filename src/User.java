import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class User implements userEntity, Observer {

    private AdminControlPanel adminControlPanel;

    private final String uID;
    private final ArrayList<User> following;
    private final ArrayList<String> newsFeedList;
    private final ArrayList<User> followers;
    private final ArrayList<String> messages;
    private int positiveWordCount = 0;
    private int messageCount = 0;
    private int totalUsers = 0;
    private final ArrayList<Observer> observers;
    public String tweets;
    private String[] positiveWords = { "good", "great", "excellent", "awesome" };

    /*
        A user has 1) an unique ID; 
        2) a list of user IDs that are following this user (followers); 
        3) a list of user IDs being followed by this user (followings); 
        4) a news feed list containing a list of Twitter messages.
    */

    public User (String uID){
        this.uID = uID;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        messages = new ArrayList<>();
        newsFeedList = new ArrayList<>();
        this.totalUsers += 1;
        this.observers = new ArrayList<>();
        this.tweets = "";
    }

    /*Users can choose to follow other users (not user groups) by providing the target user ID.
    Unfollow is not required. Users can also post a short Tweet message (a String),
    so that all the followers can see this message in their news feed lists.
    Of course, the user can also see his or her own posted messages.
    */


    public void tweet (String message){
        messages.add(message);

        newsFeedList.add(0, "- " + uID + ": " + message);

        for (String words : positiveWords){
            if (message.toLowerCase().contains(words)){
                positiveWordCount += 1;
            }
        }
        messageCount += 1;

    }


    public void followUser(User otherUser){
        if (!this.following.contains(otherUser) && validUser(otherUser))
            following.add(otherUser);
    }

    private boolean validUser(User otherUser) {
        for (User user : AdminControlPanel.getInstance().getUsers()){
            if (user.equals(otherUser))
                return true;
        }
        return  false;
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public int getTotalUsers(){
        return this.totalUsers;
    }

    public ArrayList<User> getFollows(){
        return following;
    }

    @Override
    public String toString() {
        return this.uID;
    }

    public ArrayList<String> getNewsFeedList(){
        return  newsFeedList;
    }

    @Override
    public String getUID() {
        return this.uID;
    }

    @Override
    public void update(String newTweet) {
        this.newsFeedList.add(newTweet);
        UserViewWindow userViewWindow = this.adminControlPanel.getUserViewWindow(this.uID);

        if (userViewWindow != null)
            userViewWindow.addTweetToNewsFeed(newTweet);

    }

    public void addObserver(Observer newObserver){
        this.observers.add(newObserver);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(this.tweets);
        }
    }



}
