import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminControlPanel  {

    private final ArrayList<User> users;
    private final ArrayList<UserGroup> uniqueGroupIDs;
    //Singleton Pattern
    private static AdminControlPanel instance;
    //to return total tweets
    private final ArrayList<String> tweets;

    private final HashMap<String, UserViewWindow> userViewWindow;

    private AdminControlPanel(){
        this.users = new ArrayList<>();
        this.uniqueGroupIDs = new ArrayList<>();
        this.tweets = new ArrayList<>();
        this.userViewWindow = new HashMap<>();
    }

    public static AdminControlPanel getInstance(){
        if (instance == null)
            instance = new AdminControlPanel();

        return instance;
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

//    public UserViewWindow getUserViewWindow(String uID){
//        if (uID != null)
//            return this.getUserViewWindow.get(uID);
//
//        return null;
//    }

    public void addTweet(String tweet){
        this.tweets.add(tweet);
    }

    public ArrayList<String> getTweets() {
        return this.tweets;
    }

    //O(1)
    public void setUserViewWindow(String uID, UserViewWindow window){
        this.userViewWindow.put(uID, window);
    }
    //O(1)
    public UserViewWindow getUserViewWindow(String uID){
        return this.userViewWindow.get(uID);
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
