import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Sidebar extends JFrame implements ActionListener{
    private Container sidebar;
    private Jlabel title;

    public Sidebar(){
	this.setTitle("Your Glorified Post-it Notes");
	this.setSize(1000, 600);
	this.setLocation(0, 0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	sidebar = this.getContentPane();
	sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

	JButton newNote = new JButton("New Note");
	newNote.addActionListener(this);
	newNote.setActionCommand("newNote");
	sidebar.add(newNote);

	//how will delete button work? do more research one double click/one
	//click
	
	// JButton delete = new JButton("Delete Note");
	// delete.addActionListener(this);
	// b.setActionCommand("delete");
	// delete.add(delete);

	//create directory for post it notes
	
	File directoryName = File("postitnotes");
	if(!directoryName.exits()){
	    boolean success = testDirectoryName.mkdir();
	}

	//parse through file names and list all titles
	File dir = new File("postitnotes");
	File[] directoryListing = dir.listFiles();
	if(directoryListing != null){
	    for(File child : directoryListing){
		String name = child.getName();
		i = name.indexOf(".txt");
		fileName = name.substring(0,i);
		JButton fileOnSidebar = new JButton(fileName);
		fileOnSidebar.newActionListener(this);
		fileOnSidebar.setActionCommand("txt" + fileName);
		fileOnSidebar.add(filename);
	    }
	}

	//how are timestamps organized in files??

    }	    

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("newNote")){
	    Postitnotes postit = new Postitnotes();
       	}
	if(event.substring(0, 3).equals("txt")){
	    
	    //where will openNote method be located?
	    
	    postit.openNote(event.substring(3));
	}
    }
}
	
