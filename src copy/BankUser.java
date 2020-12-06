import java.util.ArrayList;

public class BankUser {
    private String fName;
    private String lName;
    private Integer userID;

    private ArrayList<CheckingAccount> accounts;

    public BankUser(String fName, String lName, Integer userID) {
        this.fName = fName;
        this.lName = lName;
        this.userID = userID;
        this.accounts = new ArrayList<>();
    }

    public ArrayList<CheckingAccount> getAccounts() {
        return accounts;
    }

    public void addCheckingAccount(CheckingAccount checkingAccount){
       this.accounts.add(checkingAccount);
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public CheckingAccount getAccount(int AccountID){
        for(CheckingAccount acct: accounts){
            if(acct.getAccountID() == AccountID){
                return acct;
            }
        }
        return null;
    }

}
