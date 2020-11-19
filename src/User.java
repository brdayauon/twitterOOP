import java.util.ArrayList;

public class User implements userEntity, Observer, Subject {

    private AdminControlPanel adminControlPanel;

    private final String uID;
    private final ArrayList<User> following;
    private final ArrayList<String> newsFeed;
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
        newsFeed = new ArrayList<>();
        this.totalUsers += 1;
        this.observers = new ArrayList<>();
        this.tweets = "";
    }

    /*Users can choose to follow other users (not user groups) by providing the target user ID.
    Unfollow is not required. Users can also post a short Tweet message (a String),
    so that all the followers can see this message in their news feed lists.
    Of course, the user can also see his or her own posted messages.
    */

    public boolean addOtherToFollower(User otherUser) {
        if (!this.followers.contains(otherUser) && validUser(otherUser) && !(otherUser.getUID().equals(this.getUID()))){
            this.followers.add(otherUser);
            return true;
    }
        return false;
    }

    public boolean follow(User otherUser){
        if (!this.following.contains(otherUser) && validUser(otherUser) && !(otherUser.getUID().equals(this.getUID()))) {
            following.add(otherUser);
            return true;
        }
        return false;
    }

    public void tweet (String tweets){
        this.tweets = tweets;
        messages.add(tweets);

        newsFeed.add(0, "- " + uID + ": " + tweets);

        for (String words : positiveWords){
            if (tweets.toLowerCase().contains(words)){
                positiveWordCount += 1;
            }
        }
        messageCount += 1;
        this.notifyObserver();

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

    public void addTweet(String tweets){
        this.tweets = tweets;
        this.notifyObserver();
    }

    @Override
    public String toString() {
        return this.uID;
    }


    @Override
    public String getUID() {
        return this.uID;
    }

    @Override
    public void update(String newTweet) {
        this.newsFeed.add(newTweet);

        UserViewWindow userViewWindow = this.adminControlPanel.getUserViewWindow(this.uID);
        //userViewWindow.addTweetToNewsFeed(newTweet);

        if (userViewWindow != null) {
            userViewWindow.addTweet(newTweet);
        }

    }

    @Override
    public void register(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers)
            observer.update(this.tweets);
    }


    public ArrayList<User> getFollowed(){
        return this.followers;
    }

    public ArrayList<User> getFollowing(){

        return this.following;
    }

    public ArrayList<String> getNewsFeed(){
        return this.newsFeed;
    }

}
