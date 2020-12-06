public class CheckingAccount {

    private static int numAccounts = 0;

    private Integer accountID;
    private Integer balance;


    public CheckingAccount( Integer balance) {
        this.accountID = numAccounts;
        numAccounts++;
        this.balance = balance;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
