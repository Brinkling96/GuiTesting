import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WithdrawMenu extends InputIntMenu {

    private CheckingAccount account;

    public WithdrawMenu(Frame owner, boolean modal, CheckingAccount account) {
        super(owner, modal, "Withdraw","Withdraw Amount");

        this.account = account;

        pack();
        setVisible(true);
    }

    @Override
    protected void submitButtonAction(ActionEvent e) {
        String warning = null;

        if(intField.getText().isEmpty()){
            warning = "Withdraw must be entered";
        }
        else{
            try{
                int amount = Integer.parseInt(intField.getText());
                if(amount> 0 && account.getBalance()> amount) {
                    int result = JOptionPane.showConfirmDialog(this, "Withdraw $" + Integer.toString(amount) + " ?");
                    if (result == JOptionPane.OK_OPTION) {
                        account.setBalance(account.getBalance() - amount);
                        this.dispose();
                    }
                }
                else{
                    warning = "Deposit amount is not valid, either less than 1 or greater than balance";
                }

            }catch (NumberFormatException err){
                warning = "Deposit a number";
            }
        }
        if(warning != null){
            JOptionPane.showMessageDialog(this,warning,"Input Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
