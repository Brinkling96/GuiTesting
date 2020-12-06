import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAccountDialong extends JDialog {
    private BankUser user;
    private CheckingAccount account;

    private JLabel balanceLabel;
    private JTextField balanceField;
    private JLabel typeLabel;
    private JComboBox accountType;
    private JButton okButton;
    private JButton cancelButton;

    public AddAccountDialong(Frame owner, boolean modal, BankUser user) {
        super(owner, modal);
        this.user = user;
        this.account = null;
        setLocationRelativeTo(owner);
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents(){
        balanceLabel = new JLabel("Input Balance");
        balanceField = new JTextField();
        typeLabel = new JLabel("Input Account type");
        accountType = new JComboBox();
        okButton = new JButton("OK");
        cancelButton = new JButton("CANCEL");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add Account Menu");

        getContentPane().setLayout(new GridLayout(3,2,5,5));

        getContentPane().add(balanceLabel);
        getContentPane().add(balanceField);

        accountType.setModel(new DefaultComboBoxModel(new String[]{"Checking"}));

        getContentPane().add(typeLabel);
        getContentPane().add(accountType);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonAction(e);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction(e);
            }
        });


        getContentPane().add(okButton);
        getContentPane().add(cancelButton);


        pack();
        setVisible(true);
    }

    private void cancelButtonAction(ActionEvent e){
        this.dispose();
    }

    private void okButtonAction(ActionEvent e){
        String returnString  = null;
        String balanceString="";
        Integer balance = 0;

        if(balanceField.getText().isEmpty()) {
            returnString = "Input a Balance!";
        }
        else {
            try {
                balance = Integer.parseInt(balanceField.getText());
            }catch (NumberFormatException err){
                returnString = "Balance must be a number!";
            }
        }

        if(returnString != null){
            JOptionPane.showMessageDialog(this, returnString,"Input Warning",JOptionPane.WARNING_MESSAGE);
        }
        else{
            boolean result = false;
            if(accountType.getSelectedItem().toString().equals("Checking")){
                if(balance >100){
                    result = true;
                }
                else{
                    returnString = "Balance must be at least 100!";
                }

            }
            if(result){
                this.account = new CheckingAccount(balance);
                user.addCheckingAccount(account);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,returnString,"Input Warning",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public CheckingAccount getAccount() {
        return account;
    }
}
