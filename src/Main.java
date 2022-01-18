import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String CREATE_ACC = "1";
        final String LOG_IN = "2";
        final String EXIT = "3";
        String choice;
        Shop shop = new Shop();

        do {
            System.out.println("Choose from the following: \n" +
                    "1) Create account. " + "\n" +
                    "2) Log in. " + "\n" +
                    "3) Exit. ");
            choice = sc.nextLine();
            switch (choice) {
                case CREATE_ACC:
                    shop.createUser();
                    break;
                case LOG_IN:
                    shop.logIn();
                    break;
                case EXIT:
                    break;
            }
        } while (!choice.equals(EXIT));
    }
}
