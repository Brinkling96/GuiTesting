import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class test extends JFrame {
    private JButton addAccountButton;
    private JButton removeAccountButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JPanel WholeThing;
    private JPanel ButtonPanel;
    private JScrollPane scrollPane;
    private JTable table1;

    private BankUser user;

    private DefaultTableModel model;


    public static void main(String[] args) {
        new test();

    }

    public test() throws HeadlessException {
        super("tst");

        this.user = new BankUser("Sean", "Brady", 232);

        for(int i =0; i<100; i++) {
            int balance = new Random().nextInt();
            if(balance<0){
                balance= balance *-1;

            }
            balance=balance%5000;
            user.addCheckingAccount(new CheckingAccount(balance));
        }

        ArrayList<ArrayList<Object>> temp = new ArrayList<ArrayList<Object>>();
        {
            int i = 0;
            for (CheckingAccount acct : user.getAccounts()) {
                ArrayList<Object> tempActlist = new ArrayList<>();
                tempActlist.add(acct.getAccountID());
                tempActlist.add(acct.getBalance());
                temp.add(i++,tempActlist);
            }
        }

        Object[][] tableData = new Object[temp.size()][];
        {
            int i = 0;
            for (ArrayList<Object> tempTableRow : temp) {
                Object[] tableRow = tempTableRow.toArray();
                tableData[i++] = tableRow;
            }
        }




        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(600,400));

        this.setLayout(new GridLayout(1,1));

        this.WholeThing = new JPanel(new GridLayout(2,1,0,0));
        this.ButtonPanel = new JPanel();
        this.addAccountButton = new JButton("Add Account");
        this.removeAccountButton = new JButton("Remove Account");
        this.depositButton = new JButton("Deposit");
        this.withdrawButton = new JButton("Withdraw");
        this.scrollPane = new JScrollPane();
        this.table1 = new JTable();



        this.addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAccountButtonAction(e);
            }
        });

        this.removeAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAccountButtonActionPerformed(e);
            }
        });

        this.depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        this.withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });


        ButtonPanel.add(addAccountButton);
        ButtonPanel.add(removeAccountButton);
        ButtonPanel.add(depositButton);
        ButtonPanel.add(withdrawButton);



        table1.setModel(new DefaultTableModel(
                tableData,
                new String[]{
                 "ID","Balance"
                }
        ) {
            Class[] types = new Class[]{
                    Integer.class, Integer.class
            };
            boolean[] canEdit = new boolean[]{
                    false,false,false,false
            };
            public Class getColumnClass(int column_index){
                return types[column_index];
            }

            public boolean isCellEditalbe(int row_index, int column_index){
                return canEdit [column_index];
            }
        });

        table1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table1.getTableHeader().setReorderingAllowed(false);


        model = (DefaultTableModel) this.table1.getModel();
        scrollPane.add(table1);

        scrollPane.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            for(int i = table1.getColumnModel().getColumnCount(); i== -1; i--){
                table1.getColumnModel().getColumn(i).setMinWidth(25);
                table1.getColumnModel().getColumn(i).setResizable(false);
            }
        }
        GroupLayout contentPanelLayout = new GroupLayout(WholeThing);
        WholeThing.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                .addComponent(addAccountButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(removeAccountButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(depositButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(withdrawButton)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addAccountButton)
                                        .addComponent(removeAccountButton)
                                        .addComponent(depositButton)
                                        .addComponent(withdrawButton))
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addContainerGap())
        );



        WholeThing.add(ButtonPanel);
        WholeThing.add(scrollPane);

        add(WholeThing);

      GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(WholeThing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(WholeThing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setResizable(true);
        setVisible(true);
    }

    private void addAccountButtonAction(ActionEvent e){
        AddAccountDialong d= new AddAccountDialong(this, true, user);
        if(d.getAccount() != null){
            addAccountToTable(d.getAccount());
        }
    }


    public void addAccountToTable(CheckingAccount account){
        model.addRow(new Object[]{});
        reloadAccountRowData(model.getRowCount()-1, account);
    }

    public void reloadAccountRowData(int selected_row, CheckingAccount account){
        model.setValueAt(account.getAccountID(), selected_row,0);
        model.setValueAt(account.getBalance(),selected_row,1);
    }

    public void removeAccountButtonActionPerformed(ActionEvent e){
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Account Deletion", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.OK_OPTION){
            int selectedRow = table1.getSelectedRow();
            if(selectedRow >= 0){
                CheckingAccount account = getSelectedAccount(selectedRow);
                if(account != null){
                    user.getAccounts().remove(account);
                    removeAccountFromTable(selectedRow);
                }
            }
        }
    }
    public CheckingAccount getSelectedAccount(int selectedRow){
        CheckingAccount act = null;
        if (selectedRow >= 0) {
            int actnum = (int) table1.getValueAt(selectedRow, 0);
            act = user.getAccount(actnum);
        }
        return act;
    }
    public void removeAccountFromTable(int row){
        model.removeRow(row);
    }

    public void deposit(){
        int selectedRow = table1.getSelectedRow();
        CheckingAccount account = getSelectedAccount(selectedRow);


        if(account != null){
            JDialog dWindow = new DepositMenu(this,true,account);
        }
        reloadAccountRowData(selectedRow,account);

    }

    public void withdraw(){
        int selectedRow = table1.getSelectedRow();
        CheckingAccount account = getSelectedAccount(selectedRow);
        if(account != null){
            JDialog wWindow = new WithdrawMenu(this,true,account);

        }
        reloadAccountRowData(selectedRow,account);
    }






}


