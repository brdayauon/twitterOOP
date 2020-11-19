import javafx.scene.control.TreeItem;

import java.util.ArrayList;

public class UserGroup implements userEntity {

    private String userGroupID;
    private int totalGroups = 0;
    private ArrayList<User> users;
    private static TreeItem<userEntity> root;

    public UserGroup(){
        //root = new TreeItem<userEntity>("Root");
        this.totalGroups += 1;
    }

    public UserGroup(String userGroupID){
        this.totalGroups += 1;
        this.userGroupID = userGroupID;
        users = new ArrayList<>();
    }

    public boolean addUserToUserGroup(User user){
         if (!users.contains(user)){
             users.add(user);
             return true;
         }
         return false;
    }


    public int getTotalGroups(){
        return totalGroups;
    }

    @Override
    public String toString(){
        return userGroupID;
    }

    @Override
    public String getUID() {
        return this.userGroupID;
    }

    public String getUGID(User userGroupID){
        return userGroupID.getUID();
    }
}
