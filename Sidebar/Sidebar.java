import javax.swing.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Sidebar extends JFrame implements ActionListener{
    private Container sidebar;
    private JLabel title;

    public Sidebar(){
	this.setTitle("Your Glorified Post-it Notes");
	this.setSize(100, 100);
	this.setLocation(100, 100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);


	sidebar = this.getContentPane();
	sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

	JButton newNote = new JButton("New Note");
	newNote.addActionListener(this);
	newNote.setActionCommand("newNote");

	JButton hello = new JButton("hello");
	hello.addActionListener(this);
	hello.setActionCommand("hi");
	

	//how will delete button work? do more research one double click/one
	//click
	
	// JButton delete = new JButton("Delete Note");
	// delete.addActionListener(this);
	// b.setActionCommand("delete");
	// delete.add(delete);

	//create directory for post it notes
	File directoryName = new File("notes");
	if(!directoryName.exists()){
 	    boolean success = directoryName.mkdir();

	}

	//parse through file names and list all titles
	File dir = new File("postitnotes");
	File[] directoryListing = dir.listFiles();
	if(directoryListing != null){
	    for(File child : directoryListing){
		String name = child.getName();
		int i = name.indexOf(".txt");
		String fileName = name.substring(0,i);
		JButton fileOnSidebar = new JButton(fileName);
		fileOnSidebar.addActionListener(this);
		fileOnSidebar.setActionCommand("txt" + fileName);
		sidebar.add(fileOnSidebar);
	    }
	}

	//how are timestamps organized in files??
	sidebar.add(newNote);
	sidebar.add(hello);



    }	    

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("newNote")){
	    //Postitnotes postit = new Postitnotes();

       	}
	if(event.substring(0, 3).equals("txt")){
	    
	    //where will openNote method be located?
	    
	    //postit.openNote(event.substring(3));
	}
    }

    public static void main(String[] args){
	Sidebar hi = new Sidebar();
    }
}
