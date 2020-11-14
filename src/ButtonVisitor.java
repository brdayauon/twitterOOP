public interface ButtonVisitor {

    //returns the number of users visited
    public void visitUserTotal(User user);

    //returns the number of total messages
    public void visitMessagesTotal(User user);

    //returns the total number of groups
    public void visitGroupTotal(UserGroup group);

    //returns the percent of positive words out of all the tweets
    public void visitPositivePercentage(User user);
}
