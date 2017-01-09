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
	//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
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
    
    
    public Postitnotes(String filename) {
	this.setSize(600,300);
	this.setLocation(100,100);
	//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
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

	openFile(filename);
	
	b.setEnabled(ifChanged);
       	textBody.addKeyListener(k1);
	titlebar.addKeyListener(k2);
	setTitle(current);
	setVisible(true);
       
  }

    
     private KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			ifChanged = true;
		       	b.setEnabled(ifChanged);
		}
	};

         private KeyListener k2 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			ifChanged = true;
		       	b.setEnabled(ifChanged);
		}
	};

    
    //save file
    private void saveFile(String filename){
	try{
	   
	    FileWriter writer = new FileWriter("postitnotes/"+filename+".txt");
	    System.out.println("Writer created!");
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

    //open file
    private void openFile(String filename){
	try{
	    current = filename;
	    titlebar.setText(filename);
	    FileReader r = new FileReader("postitnotes/"+filename+".txt");
	    textBody.read(r, null);
	    // this.setTitle(filename);
	    ifOpened = true;
	    ifChanged = false;
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
	    System.out.println("Clicked Save");
	    if(current.equals("Untitled")){
	        current = titlebar.getText();
		
		saveFile(current);
		setTitle(current);
		System.out.println(ifChanged);
	    }
	    else{
		saveFile(current);
	    }
	}
	    
    }

    //getters and setters will be here (if needed for sidebar or texteditor)
    public static void main (String[]args){
	Postitnotes b = new Postitnotes();
	Postitnotes c = new Postitnotes("hello");
    }
}
