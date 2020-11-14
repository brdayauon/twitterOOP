import java.util.ArrayList;

//system visitor
public class ButtonVisitorStats implements ButtonVisitor{
    private int totalUsers = 0;
    private int totalMessages = 0;
    private int totalGroups = 0;
    private double positivePercentage = 0;
    private int positiveWordCount = 0;

    public void setTotalUsers(int totalUsers){
        this.totalUsers = totalUsers;
    }

    public void setTotalMessages(User user){
        this.totalMessages = user.getMessages().size();
    }

    public void setTotalGroups(UserGroup group){
        this.totalGroups = group.getTotalGroups();
    }

    public double getPositivePercentage(){
        return (positiveWordCount / totalMessages)  * 100;
    }
    public void setPositivePercentage(double percentage){
        this.positivePercentage = 0.0;
    }
    @Override
    public void visitUserTotal(User user) {
        setTotalUsers(user.getTotalUsers());
    }

    @Override
    public void visitMessagesTotal(User user) {
        setTotalMessages(user);
    }


    @Override
    public void visitGroupTotal(UserGroup group) {
        setTotalGroups(group);
    }

    @Override
    public void visitPositivePercentage(User user) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String> tweets = user.getMessages();
    }
}
