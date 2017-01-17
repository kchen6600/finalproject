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
	this.setSize(300, 1000);
	this.setLocation(10, 10);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);


	sidebar = this.getContentPane();
	sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

	JButton newNote = new JButton("NEW NOTE");
	newNote.addActionListener(this);
	newNote.setActionCommand("newNote");
	newNote.setFont(new Font("Verdana", Font.PLAIN, 20));
	newNote.setFocusPainted(false);
	newNote.setMaximumSize(new Dimension(130,35));
	newNote.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	
	JButton delete = new JButton("DELETE");
	delete.addActionListener(this);
	delete.setActionCommand("delete");
	delete.setFont(new Font("Verdana", Font.PLAIN, 20));
	delete.setFocusPainted(false);
	delete.setMaximumSize(new Dimension(130,35));
	delete.setAlignmentX(Component.CENTER_ALIGNMENT);

	sidebar.add(Box.createRigidArea(new Dimension(20, 20)));
	sidebar.add(newNote);
	sidebar.add(delete);
	sidebar.add(Box.createRigidArea(new Dimension(30, 30)));

       
	//parse through file names and list all titles
	File dir = new File("postitnotes/");
	File[] directoryListing = dir.listFiles();
	if(directoryListing.length != 0){
	    for(File child : directoryListing){
		String name = child.getName();
		int i = name.indexOf(".txt");
		if(i>0){
		    String fileName = name.substring(0,i);
		    JButton fileOnSidebar = new JButton(fileName);
		    fileOnSidebar.addActionListener(this);
		    String eventCommand = "txt" + fileName;
		    fileOnSidebar.setActionCommand(eventCommand);
		    fileOnSidebar.setFont(new Font("Verdana", Font.PLAIN, 15));
		    fileOnSidebar.setMaximumSize(new Dimension(250,20));
      		    fileOnSidebar.setFocusPainted(false);
		    fileOnSidebar.setAlignmentX(Component.CENTER_ALIGNMENT);
		    sidebar.add(fileOnSidebar);
		}
	    }
	}
	else{
	    System.out.println("File don't work");
	}

	setVisible(true);
	

	//how are timestamps organized in files??

    }


    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("newNote")){
	    setVisible(false);
	    Postitnotes postit = new Postitnotes();
       	}
	
	if(event.substring(0, 3).equals("txt")){
	    Postitnotes post = new Postitnotes(event.substring(3));
	}

	if(event.equals("delete")){
	    setVisible(false);
	    SidebarDelete deleteNotes = new SidebarDelete();
	}
    }
    
    public static void main(String[] args){
	Sidebar hi = new Sidebar();
    }

}
