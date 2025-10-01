public class BankAccount {
    private String username;
    private int accountNumber;
    private double bankAccountAmount;

    public BankAccount(String username, int accountNumber) {
        this.username = username;
        this.accountNumber = accountNumber;
        this.bankAccountAmount = 0;
    }

    public void deposit(double value) {
        bankAccountAmount += value;
    }

    public void withdraw(double value) {
        if(value <= bankAccountAmount) {
            bankAccountAmount -= value;
        }
    }

    public double getValue() {
        return bankAccountAmount;
    }

}
