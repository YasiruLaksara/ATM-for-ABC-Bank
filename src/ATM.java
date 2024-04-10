import java.util.ArrayList;
import java.util.Scanner;

class Client {
    private int id;
    private String name;
    private String nationality;
    private String occupation;
    private String address;
    private int age;
    private String gender;
    protected ArrayList<BankAccounts> accounts;


    public Client(int id, String name, String nationality, String occupation, String address, int age, String gender, ArrayList<BankAccounts> accounts) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.occupation = occupation;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.accounts = accounts;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<BankAccounts> getAccounts() {
        return accounts;
    }
}


class BankAccounts{
    private String AccountNumber;
    private String Currency;

    private String branch;
    private double balance;

    public BankAccounts(String accountNumber, String currency, String branch, double balance) {
        AccountNumber = accountNumber;
        Currency = currency;
        this.branch = branch;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void getloan(double amount, double interest, String duration, String payment_method){
        loan Loan=new loan(amount,interest,duration,payment_method);

        System.out.println("In here you can call to take a loan");

    }
    public void depositMoney(double deposit_amount){
        Deposit deposit_money=new Deposit("100",getAccountNumber(),"2023/03/01",true,deposit_amount);
        this.balance+=deposit_amount;

    }

    public void withdrawMoney(double withdraw_amount){
        if(this.getBalance()>withdraw_amount) {
            Withrawal withdraw_money = new Withrawal("101", getAccountNumber(), "2023/03/01", true, withdraw_amount);
            this.balance -= withdraw_amount;
        }
        else{
                Withrawal withdraw_money=new Withrawal("101",getAccountNumber(),"2023/03/01",false,withdraw_amount);
            System.out.println("Insufficient Money");

            }

    }

    public void BalanceInquery( BankAccounts account){
        Balance_Inqueries inquery=new Balance_Inqueries("102",getAccountNumber(),"2022/03/01",true,getBalance());

    }

    public String getCurrency() {
        return this.Currency;
    }
}
class SavingAccount extends BankAccounts{
    public SavingAccount(String accountNumber, String currency, String branch, double balance) {
        super(accountNumber, currency, branch, balance);
    }

    public void intereset(){
        System.out.println("IN HERE INTEREST IS PAYING");
    }
}

class CuurentAccount extends BankAccounts{
    public CuurentAccount(String accountNumber, String currency, String branch, double balance) {
        super(accountNumber, currency, branch, balance);
    }
}

class loan{
    private double amount;
    private double interest;
    private String duration;
    private String payment_method;

    public loan(double amount, double interest, String duration, String payment_method) {
        this.amount = amount;
        this.interest = interest;
        this.duration = duration;
        this.payment_method = payment_method;
    }

    public double getAmount() {
        return amount;
    }

    public double getInterest() {
        return interest;
    }

    public String getDuration() {
        return duration;
    }

    public String getPayment_method() {
        return payment_method;
    }
}

class Transaction{
    private String transaction_id;
    private String bank_account_id;
    private String date;
    private Boolean status;

    public Transaction(String transaction_id, String bank_account_id, String date, Boolean status) {
        this.transaction_id = transaction_id;
        this.bank_account_id = bank_account_id;
        this.date = date;
        this.status = status;
    }

}
class Deposit extends Transaction{
    private Double deposit_amount;

    public Deposit(String transaction_id, String bank_account_id, String date, Boolean status, Double deposit_amount) {
        super(transaction_id, bank_account_id, date, status);
        this.deposit_amount = deposit_amount;
    }
}
class Withrawal extends Transaction{
    private Double withrawal_amount;

    public Withrawal(String transaction_id, String bank_account_id, String date, Boolean status, Double withrawal_amount) {
        super(transaction_id, bank_account_id, date, status);
        this.withrawal_amount = withrawal_amount;
    }


}

class Balance_Inqueries extends Transaction{
    private double balance;

    public Balance_Inqueries(String transaction_id, String bank_account_id, String date, Boolean status, double balance) {
        super(transaction_id, bank_account_id, date, status);
        this.balance = balance;
    }
}

public class ATM {
    public static void main(String[] args) {
        // Create some sample bank accounts for a client
        BankAccounts account1 = new SavingAccount("BOC-1234", "USD", "Hungama", 20000);
        BankAccounts account2 = new CuurentAccount("BOC-5678", "Rupees", "Ambalantota", 10000);
        ArrayList<BankAccounts> accounts = new ArrayList<BankAccounts>();
        accounts.add(account1);
        accounts.add(account2);

        // Create a sample client
        Client client = new Client(1, "Siripala", "Sinhalese", "Engineer", "123 Main St, Colobo", 22, "Male", accounts);

        // Ask the client for their PIN
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.print("Please enter your PIN: ");
        String pin = scanner.nextLine();

        // Check if the entered PIN matches the client's ID
        if (pin.equals(Integer.toString(client.getId()))) {
            // If the PIN is correct, show the client's accounts
            System.out.println("\nHello " + client.getName() + ", which account would you like to access?");
            for (int i = 0; i < client.getAccounts().size(); i++) {
                System.out.println((i+1) + ". " + client.getAccounts().get(i).getAccountNumber() + " (" + client.getAccounts().get(i).getCurrency() + ")");
            }
            System.out.print("Enter account number: ");
            int accountIndex = scanner.nextInt() - 1;
            BankAccounts account = client.getAccounts().get(accountIndex);

            // Show the main menu
            System.out.println("\nWelcome to the ABC Bank ATM");
            System.out.println("----------------------------");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");

            // Process the user's choice
            System.out.print("\nEnter choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Current balance: " + account.getBalance() + " " + account.getCurrency());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdrawMoney(withdrawAmount);
                    System.out.println("New balance: " + account.getBalance() + " " + account.getCurrency());
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.depositMoney(depositAmount);
                    System.out.println("New balance: " + account.getBalance() + " " + account.getCurrency());
                    break;
                case 4:
                    System.out.println("Thank you for using the ABC Bank ATM");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Invalid PIN");
        }
    }
}
