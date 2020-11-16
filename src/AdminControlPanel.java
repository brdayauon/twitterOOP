import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminControlPanel  {

    private ArrayList<User> users;
    private ArrayList<UserGroup> uniqueGroupIDs;

    //Singleton Pattern
    private static AdminControlPanel instance;

    //to return total tweets
    private ArrayList<String> tweets;

    private AdminControlPanel(){
        this.users = new ArrayList<>();
        this.uniqueGroupIDs = new ArrayList<>();
        this.tweets = new ArrayList<>();
    }

    public static AdminControlPanel getInstance(){
        if (instance == null)
            instance = new AdminControlPanel();

        return instance;
    }


    /*
    A few analysis features are needed in the admin control panel:
    1) output the total number of users; DONE
    2) output the total number of groups; DONE
    3) output the total number of Tweet messages in all the users’ news feed; DONE
    4) output the percentage of the positive Tweet messages in all the users’ news feed
    (the message containing positive words, such as good, great, excellent, etc.)
    Free free to decide the positive words.
     */


    //    1) output the total number of users; DONE
    public int getNumberOfUsers(){
        return users.toArray().length;
    }

    //2) output the total number of groups; DONE
    public int getNumberOfGroups(){
        return uniqueGroupIDs.toArray().length;
    }

    //FOR TREEVIEW
    public ArrayList<User> getUsers(){
        ArrayList<User> listOfUsers = new ArrayList<>();
        listOfUsers.addAll(this.users);

        return listOfUsers;
    }

    public boolean addUser(String uId){
        for(User user : users){
            if (user.getUID().equals(uId)){
                return false;
            }
        }
        this.users.add(new User(uId));
        System.out.println("WORKING");
        return true;
    }

    public boolean addUserGroup(String userGroupId){
        for(UserGroup userGroup : uniqueGroupIDs){
            if (userGroup.getUID().equals(userGroupId))
                return false;
        }
        this.uniqueGroupIDs.add(new UserGroup(userGroupId));
        System.out.println("WORKING");
        return true;

    }

    public User getUser(String uID){

        for (User user : this.users) {
            if (user.toString().equals(uID)) {
                return user;
            }
        }
        return null;
    }

    public UserViewWindow getUserViewWindow(String uID){
        if (uID != null)
            return this.getUserViewWindow(uID);

        return null;
    }

    public void addTweet(String tweet){
        this.tweets.add(tweet);
    }

    public ArrayList<String> getTweets() {
        return this.tweets;
    }


//    public void pressAddGroupButton(ActionEvent event) {
//            System.out.println("Adding group button");
//        }
//
//    public void pressAddUser(ActionEvent event) {
//        System.out.println("Adding sampleUser");
//    }
//
//    public void pressOpenUserView(ActionEvent event){
//        System.out.println("Pressing open UserView");
//    }
//    public void pressShowMessagesTotal(ActionEvent event){
//        System.out.println("Pressing show Messages Total");
//    }
//
//    public void pressShowUserTotal(ActionEvent event) {
//        System.out.println("Pressing show User Total");
//    }


}
