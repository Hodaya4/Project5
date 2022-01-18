import java.util.Scanner;

public class Employee extends User {
    private String rank;

    public Employee(String username, String password, String firstName, String lastName, String rank) {
        super(username, password, firstName, lastName);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Rank: " + this.rank;
    }
}
