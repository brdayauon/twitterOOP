import javafx.scene.control.TreeItem;

import java.util.ArrayList;

public class UserGroup implements userEntity {

    private String userGroupID;
    private int totalGroups = 0;
    private ArrayList<userEntity> userEntity;
    private static TreeItem<userEntity> root;

    private long creationTime;

    public UserGroup(){
        //root = new TreeItem<userEntity>("Root");
        this.totalGroups += 1;
    }

    public UserGroup(String userGroupID){
        this.totalGroups += 1;
        this.userGroupID = userGroupID;
        userEntity = new ArrayList<>();
        this.creationTime = System.currentTimeMillis();
    }

    public long getCreationTime(){
        return this.creationTime;
    }

    public boolean addUserToUserGroup(User user){
         if (!userEntity.contains(user)){
             userEntity.add(user);
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

    //don't need cause not using
//    public String getUGID(User userGroupID){
//        return userGroupID.getUID();
//    }
}
