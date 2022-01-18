import java.util.Date;
import java.util.Scanner;

public class Client extends User {
    private boolean vip;
    private int numberOfPurchases;
    private double allPurchasesPrice;
    private Date dateOfLastPurchase;
    final int PURCHASE = 1;


    public Client(Boolean vip, String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
        this.vip = vip;
    }

    public boolean getVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    public void setNumberOfPurchases() {
        this.numberOfPurchases += PURCHASE;
    }

    public double getAllPurchasesPrice() {
        return allPurchasesPrice;
    }

    public void setAllPurchasesPrice(double allPurchasesPrice) {
        this.allPurchasesPrice += allPurchasesPrice;
    }

    public Date getDateOfLastPurchase() {
        return dateOfLastPurchase;
    }

    public void setDateOfLastPurchase(Date dateOfLastPurchase) {
        this.dateOfLastPurchase = dateOfLastPurchase;
    }

    public String toString() {
        StringBuilder outcome = new StringBuilder();
        outcome.append("Full name: " + this.getFirstName() + " " + this.getLastName()
                + "\n").append("VIP: ");
        if (this.vip) {
            outcome.append("is club member.");
        } else {
            outcome.append("is not club member.");
        } outcome.append("\n");
        outcome.append("Number of purchases: ").append(getNumberOfPurchases() + "\n"
        ).append("Accumulated purchases price: ").append(getAllPurchasesPrice() + "\n"
        ).append("Date of last purchase: ").append(dateOfLastPurchase);
        return outcome.toString();
    }
}
