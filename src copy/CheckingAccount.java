public class CheckingAccount {
    private Integer accountID;
    private Integer balance;

    public CheckingAccount(Integer accountID, Integer balance) {
        this.accountID = accountID;
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
