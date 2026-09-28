package Exercise2;

public class Bank {
    private int balance;
    private Customer[] customers;
    private int numberOfCustomers;

    public Bank() {
        customers = new Customer[10];
        numberOfCustomers = 0;
    }

    public Bank(int balance) {
        this();
        this.balance = balance;
    }

    public int deposit(int addedBalance) {
        this.balance += addedBalance;
        return this.balance;
    }

    public int withdraw(int withdrawBalance) {
        this.balance -= withdrawBalance;
        return this.balance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addCustomer(String f, String l) {
        customers[numberOfCustomers] = new Customer(f, l);
        numberOfCustomers++;
    }

    public int getNumOfCustomers() {
        return numberOfCustomers;
    }

    public Customer getCustomer(int index) {
        return customers[index];
    }
}