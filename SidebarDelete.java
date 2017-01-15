import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


public class SidebarDelete extends JFrame implements ActionListener{
    private Container sidebarDelete;
    private JLabel title;
    private ArrayList<String> filesDeleted = new ArrayList<String>();

    public SidebarDelete(){
	    
	sidebarDelete = this.getContentPane();
	sidebarDelete.setLayout(new BoxLayout(sidebarDelete, BoxLayout.Y_AXIS));

	this.setTitle("Your Glorified Post-it Notes-Deleting");
	this.setSize(300, 1000);
	this.setLocation(10, 10);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	JButton instruct = new JButton("To delete multiple files, hold shift.");
	instruct.setFont(new Font("Verdana", Font.PLAIN, 15));
	instruct.setFocusPainted(false);
	instruct.setMaximumSize(new Dimension(300, 50));
	instruct.setAlignmentX(Component.CENTER_ALIGNMENT);
	instruct.setBackground(Color.GRAY);
	instruct.setBorderPainted(false);
	    
	
	JButton deleteSelected = new JButton("Delete Selected");
	deleteSelected.addActionListener(this);
	deleteSelected.setActionCommand("deleteSelected");
	deleteSelected.setFont(new Font("Verdana", Font.PLAIN, 15));
	deleteSelected.setFocusPainted(false);
	deleteSelected.setMaximumSize(new Dimension(140, 35));
	deleteSelected.setAlignmentX(Component.CENTER_ALIGNMENT);

	JButton cancel = new JButton("Cancel");
	cancel.addActionListener(this);
	cancel.setActionCommand("cancel");
	cancel.setFont(new Font("Verdana", Font.PLAIN, 18));
	cancel.setFocusPainted(false);
	cancel.setMaximumSize(new Dimension(140, 35));
	cancel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
		if(i>0){
		    String fileName = name.substring(0,i);
		    fileList[j] = fileName;
		}
		j++;
	    }
	}
	else{
	    System.out.println("File don't work");
	}

	JList<String> list = new JList<>(fileList);
	list.setMinimumSize(new Dimension (300,800));
	list.setFixedCellWidth(250);
	list.setFixedCellHeight(20);
	DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();  
	renderer.setHorizontalAlignment(JLabel.CENTER); 
	list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	list.addListSelectionListener(new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent event){
		    filesDeleted.add(fileList[list.getSelectedIndex()]);
		}
	    });
	list.setFont(new Font("Verdana", Font.PLAIN, 15));

	sidebarDelete.add(Box.createRigidArea(new Dimension(20,20)));
	sidebarDelete.add(instruct);
	sidebarDelete.add(list);
	sidebarDelete.add(Box.createRigidArea(new Dimension(20,20)));
	sidebarDelete.add(deleteSelected);
	sidebarDelete.add(cancel);
	setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("deleteSelected")){
	    for(int i = 0; i<filesDeleted.size(); i++){
		String path = "postitnotes/"+filesDeleted.get(i)+".txt";
		File file = new File(path);
		file.delete();
		setVisible(false);
		Sidebar hello = new Sidebar();
		    
	    }
	}
	if(event.equals("cancel")){
	    setVisible(false);
	    Sidebar hi = new Sidebar();
	}	    
    }

    public static void main(String[] args){
	SidebarDelete test  = new SidebarDelete();
    }
}

