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
	this.setSize(500, 1000);
	this.setLocation(10, 10);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);


	sidebar = this.getContentPane();
	sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

	JButton newNote = new JButton("New Note");
	newNote.addActionListener(this);
	newNote.setActionCommand("newNote");
	

	
	JButton delete = new JButton("Delete Notes");
	delete.addActionListener(this);
	delete.setActionCommand("delete");

	sidebar.add(newNote);
	sidebar.add(delete);

       
	//parse through file names and list all titles
	File dir = new File("postitnotes/");
	File[] directoryListing = dir.listFiles();
	if(directoryListing.length != 0){
	    for(File child : directoryListing){
		System.out.println(child.getName());
		String name = child.getName();
		int i = name.indexOf(".txt");
		String fileName = name.substring(0,i);
		JButton fileOnSidebar = new JButton(fileName);
		fileOnSidebar.addActionListener(this);
		fileOnSidebar.setActionCommand("txt" + fileName);
		sidebar.add(fileOnSidebar);
	    }
	}
	else{
	    System.out.println("File don't work");
	}
	

	//how are timestamps organized in files??

    }


    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("newNote")){
	    Postitnotes postit = new Postitnotes();
       	}
	
	if(event.substring(0, 3).equals("txt")){
	    Postitnotes post = new Postitnotes(event.substring(3));
	}

	if(event.equals("delete")){
	    SidebarDelete deleteNotes = new SidebarDelete();
	}
    }
    
    public static void main(String[] args){
	Sidebar hi = new Sidebar();
	hi.setVisible(true);
    }

}
