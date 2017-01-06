import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;

public class Postitnotes extends JFrame implements ActionListener {

    
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
	this.setSize(600,300);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        b = new JButton("save");
	b.addActionListener(this);
	b.setActionCommand("save");
	//b2.addActionListener(this);
	titlebar = new JTextField(10);
	titlelabel = new JLabel("TITLE: ");
	textBody = new JTextArea(10,60);
	textlabel = new JLabel("TEXT: ");
	
	textBody.setFont(new Font("Monospaced",Font.PLAIN,12));
	titlebar.setFont(new Font("Monospaced",Font.PLAIN,12));
	JScrollPane scroll2 = new JScrollPane(textBody,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	pane.add(titlelabel);
	pane.add(titlebar);
	pane.add(textlabel);
	pane.add(textBody);
	pane.add(scroll2,BorderLayout.CENTER);
	pane.add(b);
	
	b.setEnabled(ifChanged);
       	textBody.addKeyListener(k1);
	titlebar.addKeyListener(k2);
	setTitle(current);
	setVisible(true);
       
  }
    

     private KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			ifChanged = true;
				System.out.println("KEYPRESSED");
		       	b.setEnabled(ifChanged);
		}
	};

         private KeyListener k2 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			ifChanged = true;
			System.out.println("KEYPRESSED");
		       	b.setEnabled(ifChanged);
		}
	};

    
    //save file
    private void saveFile(String filename){
	try{
	    /**
	    BufferedWriter w = new BufferedWriter(new FileWriter("Z:\\finalproject\\postitnotes\\"+filename+".txt"));
	    //need to figue out how to save it in special "notes" folder
	    textBody.write(w);
	    w.close();
	    this.setTitle(filename);
	    ifSaved = true;
	    ifChanged = false;
	    //System.out.println("File saved!") This might have to do with GUI stuff
	    b.setEnabled(ifChanged);
	}
	catch (IOException e){
	    e.printStackTrace();
	    //System.out.println("File could not be saved, file is open elsewhere, etc.") This might have to do with GUI stuff
	}
	     **/
	    FileWriter writer = new FileWriter("Z:\\finalproject\\postitnotes\\"+filename+".txt");
	    textBody.write(writer);
	    writer.close();
	    current = filename;
	    setTitle(current);
	    ifChanged = false;
	    b.setEnabled(false);
	}
	catch(IOException e){
	    e.printStackTrace();
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
		setTitle(current);
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
