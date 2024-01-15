import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class balanceEnquiry extends JFrame implements ActionListener {
    JButton back;
    String pinnumber;
    balanceEnquiry(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        conn c = new conn();
        int balance = 0;
        try {
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "' ");
            while (rs.next()) {
                if (rs.getString("type").equals("Credited")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

        JLabel text = new JLabel("Your Current Account Balance is Rs : ");
        JLabel text1 = new JLabel("" + balance);
        text1.setBounds(290, 380, 400, 30);
        text.setBounds(190, 330, 400, 30);
        text.setFont(new Font("Times New Roman", Font.BOLD, 16));
        text1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text.setForeground(Color.white);
        text1.setForeground(Color.white);
        image.add(text);
        image.add(text1);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new transactions(pinnumber).setVisible(true);

    }
    public static void main(String[] args) {
        new balanceEnquiry("");
    }
}
