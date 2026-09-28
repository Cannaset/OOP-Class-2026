package Exercise2;

public class Customer {
    private String firstName;
    private String lastName;
    private Bank account;

    public Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Bank getAccount() {
        return account;
    }

    public void setAccount(Bank account) {
        this.account = account;
    }
}