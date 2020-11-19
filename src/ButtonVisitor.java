public interface ButtonVisitor {

    //returns the number of users visited
    public int visitUserTotal(User user);

    //returns the number of total messages
    public int visitMessagesTotal(User user);

    //returns the total number of groups
    public int visitGroupTotal(UserGroup group);

    //returns the percent of positive words out of all the tweets
    public double visitPositivePercentage(User user);
}
