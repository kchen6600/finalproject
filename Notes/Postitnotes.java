import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;

public class Postitnotes extends JFrame implements ActionListener{

    private Container pane;
    private JLabel j;
    private JButton b;
    private JTextArea textBody;
    private JTextArea titlebar;
    private boolean ifChanged;
    private boolean ifSaved;
    private boolean ifOpened;
    private String current;
    
    public Postitnotes(){
	this.setTitle("Post it notes");
	this.setSize(600, 400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	JButton b = new JButton("save");
	b.addActionListener(this);
	b.setActionCommand("save");
	textBody = new JTextArea(400,400);
	titlebar = new JTextArea(400,100);
	ifChanged = false;
	current = "Untitled";
	
	pane.add(j);
	pane.add(titlebar);
	pane.add(textBody);
	pane.add(b);
    }

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

    //need to do more research on how to integrate this
    /**
    private KeyListener k;
    k = new KeyAdapter() {
	public void keyPressed(KeyEvent ev){
	    ifChanged = true;
	}
    };
    **/
    
   
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
