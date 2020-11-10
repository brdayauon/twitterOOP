package sample;

import javafx.event.ActionEvent;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class AdminControlPanel {

    private int totalUsers;
    private int totalGroups;
    public ArrayList<String> users;
    public ArrayList<String> uniqueGroupIDs;

    //Singleton Pattern
    private static AdminControlPanel instance;

    public static AdminControlPanel getInstance(){
        if (instance == null)
            instance = new AdminControlPanel();

        return instance;
    }

    private AdminControlPanel(){
        totalUsers = 0;
        totalGroups = 0;
        this.users = new ArrayList<>();
        this.uniqueGroupIDs = new ArrayList<>();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new User("Root"));

    }



    public void addUser(String user){

    }



    public void pressAddGroupButton(ActionEvent event) {
            System.out.println("Adding group button");
        }

    public void pressAddUser(ActionEvent event) {
        System.out.println("Adding sampleUser");
    }

    public void pressOpenUserView(ActionEvent event){
        System.out.println("Pressing open UserView");
    }
    public void pressShowMessagesTotal(ActionEvent event){
        System.out.println("Pressing show Messages Total");
    }

    public void pressShowUserTotal(ActionEvent event) {
        System.out.println("Pressing show User Total");
    }


}
