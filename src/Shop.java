import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Shop {
    private Employee[] employees;
    private Client[] clients;
    private Product[] products;
    final int EMPLOYEE = 1;
    final int CLIENT = 2;
    final int PASSWORD = 6;
    final int CLUB_MEMBER = 0;
    final int NOT_A_CLUB_MEMBER = 1;
    final int MANAGER = 2;
    final int MANAGEMENT_MEMBER = 3;
    final int EMPTY = 0;
    final int FINISH_PURCHASE = -1;
    final int DOESNT_EXIST = -999;
    final int IN_STOCK = 0;
    final int NOT_IN_STOCK = 1;
    final double EMPLOYEE_SALE = 0.9;
    final double MANAGER_SALE = 0.8;
    final double MANAGEMENT_TEAM_SALE = 0.7;
    final String PRINT_CLIENTS = "1";
    final String PRINT_VIP_CLIENTS = "2";
    final String PRINT_CLIENTS_WHO_PURCHASED_AT_LEAST_ONCE = "3";
    final String PRINT_HIGHEST_PURCHASE_CLIENT = "4";
    final String ADD_PRODUCT = "5";
    final String CHANGE_PRODUCT_STATUS = "6";
    final String MAKE_PURCHASE = "7";
    final String EXIT = "8";


    public Shop() {
        this.employees = new Employee[0];
        this.clients = new Client[0];
        this.products = new Product[0];
    }

    private boolean noNumbers(String name) {
        for (int i = 0; i < name.length(); i++) {
            char currentChar = name.charAt(i);
            if (Character.isDigit(currentChar)) {
                return false;
            }
        } return true;
    }

    public boolean isUsernameExist(String username) {
        boolean exists = false;
        for (int i = 0; i < this.clients.length; i++) {
            String currentClient = this.clients[i].getUsername();
            if (currentClient.equals(username)) {
                exists = true;
                return exists;
            }
        } for (int i = 0; i < this.employees.length; i++) {
            String currentEmployee = this.employees[i].getUsername();
            if (currentEmployee.equals(username)) {
                exists = true;
                return exists;
            }
        } return exists;
    }

    public int isUserExist(String username, String password, String accountType) {
        int index = DOESNT_EXIST;
        if (accountType.equals("Client")) {
            for (int i = 0; i < this.clients.length; i++) {
                Client currentClient = this.clients[i];
                if (currentClient.getPassword().equals(password) && currentClient.getUsername().equals(username)) {
                    index = i;
                    return  index;
                }
            }
        } else {
            for (int i = 0; i < this.employees.length; i++) {
                Employee currentEmployee = this.employees[i];
                if (currentEmployee.getPassword().equals(password) && currentEmployee.getUsername().equals(username)) {
                    index = i;
                    return index;
                }
            }
        } return index;
    }

    public void createUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Employee(1) or normal client(2)? ");
        int answer = sc.nextInt();
        while (answer != EMPLOYEE && answer != CLIENT) {
            System.out.println("Input is not valid. Try again. ");
            answer = sc.nextInt();
        }
        boolean isEmployee;
        if (answer == EMPLOYEE) {
            isEmployee = true;
        } else {
            isEmployee = false;
        }
        boolean clubMember = false;
        String rank = "";
        if (!isEmployee) {
            System.out.println("Are you a club member? yes(0) no(1): ");
            answer = sc.nextInt();
            while (answer != CLUB_MEMBER && answer != NOT_A_CLUB_MEMBER) {
                System.out.println("Invalid input. Try again. ");
                answer = sc.nextInt();
            }
            if (answer == CLUB_MEMBER) {
                clubMember = true;
            }
        } else {
            System.out.println("What is your rank? " + "\n" +
                    "1) Employee. " + "\n" +
                    "2) Manager. " + "\n" +
                    "3) Shop management member. ");
            answer = sc.nextInt();
            while (answer != EMPLOYEE && answer != MANAGER && answer != MANAGEMENT_MEMBER) {
                System.out.println("Invalid input. Try again. ");
                answer = sc.nextInt();
            } if (answer == EMPLOYEE) {
                rank = "Employee";
            } else if (answer == MANAGER) {
                rank = "Manager";
            } else {
                rank = "Shop management member";
            }
        }
        System.out.println("What is your first name? ");
        String firstName = new Scanner(System.in).nextLine();
        boolean res = noNumbers(firstName);
        while (!res) {
            System.out.println("Invalid input. Try again. ");
            firstName = new Scanner(System.in).nextLine();
            res = noNumbers(firstName);
        }
        System.out.println("What is your last name? ");
        String lastName = new Scanner(System.in).nextLine();
        res = noNumbers(lastName);
        while (!res) {
            System.out.println("Invalid input. Try again.");
            lastName = new Scanner(System.in).nextLine();
            res = noNumbers(lastName);
        }
        System.out.println("Enter your username: ");
        String username = new Scanner(System.in).nextLine();
        res = isUsernameExist(username);
        while (res) {
            System.out.println("Username is taken. Try again. ");
            username = new Scanner(System.in).nextLine();
            res = isUsernameExist(username);
        }
        System.out.println("Enter your password: ");
        String password = new Scanner(System.in).nextLine();
        while (password.length() < PASSWORD) {
            System.out.println("Password is too short. Try again. ");
            password = new Scanner(System.in).nextLine();
        }

        if (!isEmployee) {
            Client client = new Client(clubMember, username, password, firstName, lastName);
            Client[] newArr = new Client[this.clients.length + 1];
            for (int i = 0; i < this.clients.length; i++) {
                Client currentClient = this.clients[i];
                newArr[i] = currentClient;
            } this.clients = newArr;
            this.clients[this.clients.length - 1] = client;
            //System.out.println(this.clients[0].toString());
        } else {
            Employee employee = new Employee(username, password, firstName, lastName, rank);
            Employee[] newArr2 = new Employee[this.employees.length + 1];
            for (int i = 0; i < this.employees.length; i++) {
                Employee currentEmployee = this.employees[i];
                newArr2[i] = currentEmployee;
            } this.employees = newArr2;
            this.employees[this.employees.length - 1] = employee;
            //System.out.println(this.employees[0].toString());

        }
        System.out.println("\n" + "Account has been created!" + "\n");
    }

    public double decideOnPurchase(Client client, Employee employee) {
        Scanner sc = new Scanner(System.in);
        int numOfProduct;
        int count = 0;
        double generalPrice = 0;
        Product[] productArrayCopy = {null};
        do {
            if (this.products.length == EMPTY) {
                System.out.println("There are no products in the shop currently.");
                return EMPTY;
            } else {
                for (int j = 0; j < products.length; j++) {
                    if (this.products[j].getIsStocked()) {
                        System.out.println((j + 1) + ")" + this.products[j].toString());
                    }
                }
            }
            System.out.println("Choose the number of product or enter -1 to finish purchase.");
            numOfProduct = sc.nextInt();
            while ((numOfProduct < EMPTY && numOfProduct != FINISH_PURCHASE) || numOfProduct > this.products.length) {
                System.out.println("Invalid input, try again. ");
                numOfProduct = sc.nextInt();
            }
            if (numOfProduct == FINISH_PURCHASE) {
                System.out.println("Purchase finished.");
                break;
            }
            System.out.println("How many of the product do you want to purchase?");
            int quantityOfProduct = sc.nextInt();
            while (quantityOfProduct < EMPTY) {
                System.out.println("Quantity of product cant be negative. try again");
                quantityOfProduct = sc.nextInt();
            }
            count++;
            double currentPrice = this.products[numOfProduct - 1].getPrice();
            if (employee == null && client.getVip()) {
                currentPrice = currentPrice * this.products[numOfProduct - 1].getDiscountPercentages();
            } else if (client == null) {
                switch (employee.getRank()) {
                    case "Employee":
                        currentPrice = currentPrice * EMPLOYEE_SALE;
                        break;
                    case "Manager":
                        currentPrice = currentPrice * MANAGER_SALE;
                        break;
                    case "Shop management member":
                        currentPrice = currentPrice * MANAGEMENT_TEAM_SALE;
                        break;
                }
            }
            generalPrice += currentPrice * quantityOfProduct;
            Product[] productArray = new Product[count];
            for (int i = 0; i < productArrayCopy.length; i++) {
                productArray[i] = productArrayCopy[i];
            }
            productArray[count - 1] = this.products[numOfProduct - 1];
            productArrayCopy = new Product[count];
            productArrayCopy = productArray;
            ShoppingCart shoppingCart = new ShoppingCart(productArray);
            shoppingCart.getProducts();
            System.out.println("The accumulated price is " + generalPrice);
        } while (numOfProduct != FINISH_PURCHASE);
        System.out.println("The overall price of your purchase is " + generalPrice);
        return generalPrice;
    }

    private void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the product? ");
        String productDescription = sc.nextLine();
        System.out.println("How much does the product cost? ");
        double price = sc.nextDouble();
        System.out.println("What are the discount percentages for VIP members? ");
        double vipDiscount = sc.nextDouble();
        Product product = new Product(true, productDescription, price, vipDiscount);
        Product[] newProductArray = new Product[this.products.length + 1];
        for (int i = 0; i < this.products.length; i++) {
            Product currentProduct = this.products[i];
            newProductArray[i] = currentProduct;
        } newProductArray[newProductArray.length - 1] = product;
        this.products = newProductArray;
    }

    public void logIn() {
        Scanner sc = new Scanner(System.in);
        String accountType = "";
        System.out.println("Log into Employee(1) account or Client(2) account? ");
        int answer = sc.nextInt();
        while (answer != EMPLOYEE && answer != CLIENT) {
            System.out.println("Invalid input. Try again. ");
            answer = sc.nextInt();
        }
        if (answer == EMPLOYEE) {
            accountType = "Employee";
        } else {
            accountType = "Client";
        }
        if ((accountType.equals("Employee") && this.employees.length == EMPTY) ||
                (accountType.equals("Client") && this.clients.length == EMPTY)) {
            System.out.println("There are no existing users currently.");
            return;
        }
        System.out.println("Enter your username: ");
        String username = sc.next();
        System.out.println("Enter your password: ");
        String password = sc.next();

        int res = isUserExist(username, password, accountType);
        if (res == DOESNT_EXIST) {
            System.out.println("User does not exist.");
            return;
        }

        if (accountType.equals("Client")) {
            Client currentClient = this.clients[res];
            if (currentClient.getVip()) {
                System.out.println("Hello " + currentClient.getFirstName() + " " + currentClient.getLastName() + " (VIP)!");
            } else {
                System.out.println("Hello " + currentClient.getFirstName() + " " + currentClient.getLastName() + "!");
            }
            double generalPrice = decideOnPurchase(currentClient, null);
            currentClient.setAllPurchasesPrice(generalPrice);
            Date date = new Date();
            currentClient.setDateOfLastPurchase(date);
            currentClient.setNumberOfPurchases();
            return;
        } else {
            Employee currentEmployee = this.employees[res];
            System.out.println("Hello " + currentEmployee.getFirstName() + " " +
                    currentEmployee.getLastName() + "(" + currentEmployee.getRank() + ")!");
            String choice;
            do {
                System.out.println("Choose from the following: " + "\n" +
                        "1) Print all clients. \n" +
                        "2) Print all VIP clients. \n" +
                        "3) Print all clients who've purchased at least once.\n" +
                        "4) Print highest accumulated price's client.\n" +
                        "5) Add new product.\n" +
                        "6) Change stock status of product.\n" +
                        "7) Make a purchase.\n" +
                        "8) Exit. \n");
                choice = sc.next();

                switch (choice) {
                    case PRINT_CLIENTS:
                        for (int i = 0; i < this.clients.length; i++) {
                            Client currentClient = this.clients[i];
                            System.out.println(currentClient);
                            System.out.println("-----");
                        } break;
                    case PRINT_VIP_CLIENTS:
                        for (int i = 0; i < this.clients.length; i++) {
                            Client currentClient = this.clients[i];
                            if (currentClient.getVip()) {
                                System.out.println(currentClient);
                                System.out.println("-----");
                            }
                        } break;
                    case PRINT_CLIENTS_WHO_PURCHASED_AT_LEAST_ONCE:
                        for (int i = 0; i < this.clients.length; i++) {
                            Client currentClient = this.clients[i];
                            if (currentClient.getNumberOfPurchases() > 0) {
                                System.out.println(currentClient);
                                System.out.println("-----");
                            }
                        } break;
                    case PRINT_HIGHEST_PURCHASE_CLIENT:
                        double highest = 0;
                        double tempHighest = 0;
                        int indexOfClient = 0;
                        for (int i = 0; i < this.clients.length; i++) {
                            Client currentClient = this.clients[i];
                            tempHighest = currentClient.getAllPurchasesPrice();
                            if (tempHighest > highest) {
                                highest = tempHighest;
                                indexOfClient = i;
                            }
                        }
                        System.out.println(this.clients[indexOfClient]);
                        break;
                    case ADD_PRODUCT:
                        addProduct();
                        break;
                    case CHANGE_PRODUCT_STATUS:
                        System.out.println("Choose from the following which product to change status for: ");
                        for (int j = 0; j < products.length; j++) {
                            System.out.println((j + 1) + ")" + this.products[j]);
                        } answer = sc.nextInt() - 1;
                        Product chosenProduct = this.products[answer];
                        System.out.println("Is the product in stock(0) or not(1)?");
                        int result = sc.nextInt();
                        while (result != IN_STOCK && result != NOT_IN_STOCK) {
                            System.out.println("Invalid input, try again. ");
                            result = sc.nextInt();
                        }
                        if (result == IN_STOCK) {
                            chosenProduct.setIsStocked(true);
                        } else {
                            chosenProduct.setIsStocked(false);
                        }
                        break;
                    case MAKE_PURCHASE:
                        decideOnPurchase(null, currentEmployee);
                        break;
                }
            } while (!(choice.equals(EXIT)));
        }
    }


    public String toString() {
        return "Shop{" +
                "Employees=" + Arrays.toString(employees) +
                ", clients=" + Arrays.toString(clients) +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}