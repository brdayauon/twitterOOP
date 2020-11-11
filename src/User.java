import java.util.ArrayList;
import java.util.List;

public class User {
    private final String uID;
    private final List<String> following;
    private final List<String> newsFeedList;
    private final List<User> followers;

    private int totalNumberOfUsers = 0;
    private int totalNumberOfGroups = 0;
    private int totalNumberOfTweets = 0;
    private int totalNumberOfPositiveCount = 0;
    private String[] positiveWords = { "good", "great", "excellent", };

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
        newsFeedList = new ArrayList<>();
        totalNumberOfUsers += 1;
    }

    /*Users can choose to follow other users (not user groups) by providing the target user ID.
    Unfollow is not required. Users can also post a short Tweet message (a String),
    so that all the followers can see this message in their news feed lists.
    Of course, the user can also see his or her own posted messages.
    */


    /*
    A few analysis features are needed in the admin control panel:
    1) output the total number of users; DONE
    2) output the total number of groups; DONE
    3) output the total number of Tweet messages in all the users’ news feed; DONE
    4) output the percentage of the positive Tweet messages in all the users’ news feed
    (the message containing positive words, such as good, great, excellent, etc.)
    Free free to decide the positive words.
     */
    public int getTotalNumberOfUsers(){
        return totalNumberOfUsers;
    }
    public int getTotalNumberOfGroups(){
        return totalNumberOfGroups;
    }
    public int getTotalNumberOfTweets(){
        return totalNumberOfTweets;
    }
    public int getTotalNumberOfPositiveCount() {return totalNumberOfPositiveCount;}

    public void followUser(String otherUser){
//        boolean followed = false;
//
//        for(String user : following){
//            if (user == otherUser){
//                followed = true;
//            }
//        }
//        if (followed)
//            return;
//        else
//            following.add(otherUser);
        following.add(otherUser);
    }

    @Override
    public String toString() {
        return this.uID;
    }

    public String getuID(){
        return this.uID;
    }

}
