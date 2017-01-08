import javax.swing.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SidebarDelete extends JFrame implements ActionListener, ItemListener{
    private Container sidebarDelete;
    private JLabel title;

    public SidebarDelete(){
	    
	sidebarDelete = this.getContentPane();
	sidebarDelete.setLayout(new BoxLayout(sidebarDelete, BoxLayout.Y_AXIS));

	this.setTitle("Your Glorified Post-it Notes");
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
	if(directoryListing.length != 0){
	    String[] listNames = new String[directoryListing.length];
	    int j = 0;
	    for(File child : directoryListing){
		System.out.println(child.getName());
		String name = child.getName();
		int i = name.indexOf(".txt");
		String fileName = name.substring(0,i);
		listNames[j] = fileName;
		j++;
	    }
	    JList list = new JList(createData(listNames));
	    list.setCellRenderer(new CheckListRenderer());
	    list.setBorder(new EmptyBorder(0,4,0,0));
	    list.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
			int index = list.locationToIndex(e.getPoint());
			CheckableItem item = (CheckableItem)list.getModel().getElementAt(index);
			item.setSelected(!item.isSelected());
			Rectangle rect = list.getCellBounds(index, index);
			list.repaint(rect);
		    }
		});
	    final JTextArea textArea = new JTextArea(3, 10);
	    JScrollPane textPanel = new JScrollPane(textArea);
	    JButton printButton = new JButton("print");
	    printButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			ListModel model = list.getModel();
			int n = model.getSize();
			for (int i = 0; i < n; i++) {
			    CheckableItem item = (CheckableItem) model.getElementAt(i);
			    if (item.isSelected()) {
				textArea.append(item.toString());
				textArea.append(System.getProperty("line.separator"));
			    }
			}
		    }
		});
	}
	else{
	    System.out.println("File don't work");
	}
	

	//how are timestamps organized in files??

	sidebarDelete.add(deleteSelected);
	sidebarDelete.add(cancel);
    }
	
    private CheckableItem[] createData(String[] strs) {
	int n = strs.length;
	CheckableItem[] items = new CheckableItem[n];
	for (int i = 0; i < n; i++) {
	    items[i] = new CheckableItem(strs[i]);
	}
	return items;
    }

    class CheckableItem {
	private String str;

	private boolean isSelected;

	public CheckableItem(String str) {
	    this.str = str;
	    isSelected = false;
	}

	public void setSelected(boolean b) {
	    isSelected = b;
	}

	public boolean isSelected() {
	    return isSelected;
	}

	public String toString() {
	    return str;
	}
    }

    class CheckListRenderer extends JCheckBox implements ListCellRenderer {

	public CheckListRenderer() {
	    setBackground(UIManager.getColor("List.textBackground"));
	    setForeground(UIManager.getColor("List.textForeground"));
	}

	public Component getListCellRendererComponent(JList list, Object value,
						      int index, boolean isSelected, boolean hasFocus) {
	    setEnabled(list.isEnabled());
	    setSelected(((CheckableItem) value).isSelected());
	    setFont(list.getFont());
	    setText(value.toString());
	    return this;
	}
    }


    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.substring(0, 3).equals("txt")){
	    //what to do?

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
    }

    public void itemStateChanged(ItemEvent e){
	//if(checkNote.isSelected()){
	//checkNote.setText("hello");
    }

    public static void main(String[] args){
	SidebarDelete hi = new SidebarDelete();
	hi.setVisible(true);
    }
}
