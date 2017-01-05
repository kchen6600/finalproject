import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;

public class Postitnotes extends JFrame implements ActionListener{

    
    private Container pane;
    private JLabel textlabel, titlelabel;
    private JButton b;
    private JTextArea textBody;
    private JTextField titlebar;
    private boolean ifChanged = false;
    private boolean ifSaved, ifOpened;
    private String current = "Untitled";



    public Postitnotes() {
	this.setTitle("CREATE NEW NOTE");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	JButton b = new JButton("save");
	b.addActionListener(this);
	b.setActionCommand("save");
	//JButton b2 = new JButton("No...");
	//b2.addActionListener(this);
	//b2.setActionCommand("NotByte");
	titlebar = new JTextField(10);
	//JCheckBox c = new JCheckBox("OverClock");
	titlelabel = new JLabel("TITLE: ");
	textBody = new JTextArea(10,60);
	textlabel = new JLabel("TEXT: ");
	
	textBody.setFont(new Font("Monospaced",Font.PLAIN,12));
	titlebar.setFont(new Font("Monospaced",Font.PLAIN,12));
	JScrollPane scroll2 = new JScrollPane(textBody,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	pack();
	
	pane.add(titlelabel);
	pane.add(titlebar);
	pane.add(textlabel);
	pane.add(textBody);
	pane.add(scroll2,BorderLayout.CENTER);
	pane.add(b);

       	textBody.addKeyListener(k1);
	setTitle(current);
	setVisible(true);
  }
    
     private KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			ifChanged = true;
		}
	};

    
    //save file
    private void saveFile(String filename){
	try{
	    BufferedWriter w = new BufferedWriter(new FileWriter("Z:\\finalproject\\postitnotes\\"+filename+".txt"));
	    //need to figue out how to save it in special "notes" folder
	    textBody.write(w);
	    w.close();
	    this.setTitle(filename);
	    ifSaved = true;
	    ifChanged = false;
	    //System.out.println("File saved!") This might have to do with GUI stuff
	}
	catch (IOException e){
	    e.printStackTrace();
	    //System.out.println("File could not be saved, file is open elsewhere, etc.") This might have to do with GUI stuff
	}
    }
    
    //save edits to file -- I don't think this works yet pls help
    private void saveEdits(String filename){
	try{
	    BufferedWriter w = new BufferedWriter(new FileWriter(filename));
	    w.write(textBody.getText());
	    w.close();
	    
	}
	catch (IOException e){
	    e.printStackTrace();
	    //System.out.println("File could not be saved, file is open elsewhere, etc.") This might have to do with GUI stuff
	}
    }
    //open file
    private void openFile(File filename){
	try{
	    current = filename.getName();
	    FileReader r = new FileReader(filename);
	    textBody.read(r, null);
	    this.setTitle(filename.getName());
	    ifOpened = true;
	    //file opens
	}
	catch(IOException e){
	    e.printStackTrace();
	    //System.out.println("Could not open, file does not exist, etc.") This might have to do with GUI stuff
	}
    }

    public void actionPerformed(ActionEvent ev){
	//click save button -> saves file
	String event = ev.getActionCommand();
	if (event.equals("save")){
	    if(!current.equals("Untitled")){
	        current = titlebar.getText();
       
		saveFile(current);
	    }
	    else{
		saveEdits(current);
	    }
	}
	    
    }

    //getters and setters will be here (if needed for sidebar or texteditor)
    public static void main (String[]args){
	Postitnotes b = new Postitnotes();
    }
}
