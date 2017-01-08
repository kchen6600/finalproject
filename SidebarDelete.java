import javax.swing.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class SidebarDelete extends JFrame implements ActionListener, ItemListener{
    private Container sidebarDelete;
    private JLabel title;

    public SidebarDelete(){
	    
	sidebarDelete = this.getContentPane();
	sidebarDelete.setLayout(new BoxLayout(sidebarDelete, BoxLayout.Y_AXIS));

	this.setTitle("Your Glorified Post-it Notes-Deleting");
	this.setSize(500, 1000);
	this.setLocation(10, 10);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	JButton deleteSelected = new JButton("Delete Selected");
	deleteSelected.addActionListener(this);
	deleteSelected.setActionCommand("deleteSelected");

	JButton cancel = new JButton("Cancel");
	cancel.addActionListener(this);
	cancel.setActionCommand("cancel");

	//parse through file names and list all titles
	File dir = new File("postitnotes/");
	File[] directoryListing = dir.listFiles();
	String[] fileList = new String[directoryListing.length];
	if(directoryListing.length != 0){
	    int j = 0;
	    for(File child : directoryListing){
		System.out.println(child.getName());
		String name = child.getName();
		int i = name.indexOf(".txt");
		String fileName = name.substring(0,i);
		fileList[j] = fileName;
		j++;
	    }
	}
	else{
	    System.out.println("File don't work");
	}

	JList<String> list = new JList<>(fileList);
	
	sidebarDelete.add(list);
	sidebarDelete.add(deleteSelected);
	sidebarDelete.add(cancel);
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("deleteSelected")){
	    //PATH
	    //try{
	    // Files.delete(path);
	    //}catch(NoSuchFileException x){
	    System.out.println("No such file or directory");
	    //}catch(DirectoryNotEmptyException x){
	    System.out.println("Not empty directory");
	    //}catch(IOException x){
	    System.out.println("File permission problems.");
	    //	}
	}
	if(event.equals("cancel")){
	    Sidebar hi = new Sidebar();
	}	    
    }

    public void itemStateChanged(ItemEvent e){

    }

    public static void main(String[] args){
	SidebarDelete hi = new SidebarDelete();
	hi.setVisible(true);
    }
}

