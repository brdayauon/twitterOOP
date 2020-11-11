import java.util.ArrayList;

public class AdminControlPanel  {

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
        this.users = new ArrayList<>();
        this.uniqueGroupIDs = new ArrayList<>();


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




    public void addUser(String user){

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
