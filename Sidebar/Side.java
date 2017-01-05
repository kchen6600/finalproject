import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Side extends JFrame implements ActionListener{
    private Container pane;
    private JLabel j;
    private JTextField t;

    public Side(){
	this.setTitle("My first GUI");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	JButton b = new JButton("Byte");
	b.addActionListener(this);
	b.setActionCommand("byte");
	t = new JTextField(10);

	pane.add(t);
	pane.add(b);
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
    }

    public static void main(String[] args){
	Side hi = new Side();
    }
}
