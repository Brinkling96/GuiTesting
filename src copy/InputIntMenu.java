import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class InputIntMenu extends JDialog {
    protected JLabel intLabel;
    protected JTextField intField;
    protected JButton submitButton;
    protected JButton cancelButton;


    public InputIntMenu(Frame owner, boolean modal, String title, String label) {
        super(owner, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(title);

        initComponents(label);
        setLocationRelativeTo(owner);
    }

    private void initComponents(String label) {
        this.intLabel = new JLabel(label);
        this.intField = new JTextField();
        this.submitButton = new JButton("Submit");
        this.cancelButton = new JButton("Cancel");


        getContentPane().setLayout(new GridLayout(2,2,5,5));

        getContentPane().add(intLabel);
        getContentPane().add(intField);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonAction(e);
            }
        });

        getContentPane().add(submitButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction(e);
            }
        });

        getContentPane().add(cancelButton);


    }

    protected void cancelButtonAction(ActionEvent e){
        this.dispose();
    }

    protected abstract void submitButtonAction(ActionEvent e);
}
