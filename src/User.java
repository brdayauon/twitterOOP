import java.util.ArrayList;
import java.util.List;

public class User implements userEntity{
    private final String uID;
    private final ArrayList<User> following;
    private final ArrayList<String> newsFeedList;
    private final ArrayList<User> followers;

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
    }

    /*Users can choose to follow other users (not user groups) by providing the target user ID.
    Unfollow is not required. Users can also post a short Tweet message (a String),
    so that all the followers can see this message in their news feed lists.
    Of course, the user can also see his or her own posted messages.
    */


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

    public ArrayList<User> getFollows(){
        return following;
    }

    @Override
    public String toString() {
        return this.uID;
    }

//    public String getuID(){
//        return this.uID;
//    }

    @Override
    public String getUID() {
        return this.uID;
    }
}
