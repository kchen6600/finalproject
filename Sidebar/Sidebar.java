import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sidebar extends JFrame implements ActionListener{
    private Container sidebar;
    private Jlabel title;

    public Sidebar(){
	this.setTitle("Your Glorified Post-it Notes");
	this.setSize(1000, 600);
	this.setLocation(0, 0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	sidebar = this.getContentPane();
	sidebar.getLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

	JButton newNote = new JButton("New Note");
	newNote.addActionListener(this);
	b.setActionCommand("newNote");

	//parse through file names and list all titles and time stamps

	//create directory for post it notes
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("newNote")){
	    //create new note method
	}
    }

    
}

