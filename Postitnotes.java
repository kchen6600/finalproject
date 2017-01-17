//
//MUST COMPILE WITH .:jar/*
//

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentListener;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import com.sun.speech.freetts.*;
import javax.swing.text.html.*;
import javax.swing.filechooser.*;

public class Postitnotes extends JFrame implements ActionListener {

    private Container pane;
    private JLabel textlabel, titlelabel, timestamp;
    private JButton b, tts, bullets, picture;
    private JTextPane textBody;
    private JTextField titlebar;
    private JComboBox fontselection;
    private JComboBox fontsizeselection;
    private String fontchosen;
    private Integer fontsizechosen;
    private String lastmod;
    private boolean ifChanged = false;
    private boolean ifSaved, ifOpened;
    private static final String voicename = "kevin16";
    private Font gotofont;
    final static boolean shouldFill = true;
    final static boolean shouldWeighX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private String current = "Untitled";
    private Sidebar refreshedBar;
    private Point currentlocation;
    
    public Postitnotes() {
	refreshedBar = new Sidebar();
	this.setTitle("CREATE NEW NOTE");
	this.setSize(600,300);
	this.setLocation(100,100);
	pane = this.getContentPane();
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	if(shouldFill){
	    c.fill = GridBagConstraints.HORIZONTAL;
	}
	if(RIGHT_TO_LEFT){
	    pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}
	
	titlebar = new JTextField(55);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 1;
	c.gridy = 0;
	c.gridwidth = 4;
	pane.add(titlebar, c);
	c.gridwidth = 1;
	    
	titlelabel = new JLabel("TITLE: ");
	c.gridx = 0;
	c.gridy = 0;
	c.insets = new Insets(10, 0, 0, 0);
	pane.add(titlelabel, c);
	c.insets = new Insets(0, 0, 0, 0);
	
	textBody = new JTextPane();
	textBody.setSize(55,60);
	c.ipady = 60;
	c.gridx = 0;
	c.gridy = 2;
	c.gridwidth = 5;
	c.fill = GridBagConstraints.BOTH;

	textBody.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
	gotofont = new Font("Serif", Font.PLAIN, 12);
       	textBody.setFont(gotofont);
	titlebar.setFont(gotofont);
	fontchosen = "Serif";
	fontsizechosen = 12;
	
	JScrollPane scroll2 = new JScrollPane(textBody,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	pane.add(textBody, c);
	c.gridwidth = 1;
	
	textlabel = new JLabel("TEXT: ");
	c.ipady = 20;
	c.gridx = 0;
	c.gridy = 1;
	pane.add(textlabel, c);
	c.ipady = 0;
	
	lastmod = "Not saved";
	timestamp = new JLabel(lastmod);
	c.gridx = 2;
	c.gridy = 5;
	pane.add(timestamp, c);

	Insets pad = new Insets(0, 0, 10, 0);
	b = new JButton("save");
	b.addActionListener(this);
	b.setActionCommand("save");
	c.gridx = 2;
	c.gridy = 4;
	c.insets = pad;
	pane.add(b, c);
	c.gridwidth = 1;

	Insets pad1 = new Insets(10, 0, 10, 0);
	c.insets = pad1;
	fontselection = new JComboBox();
	fontselection.setEditable(false);
	fontselection.addItem("Serif");
	fontselection.addItem("SansSerif");
	fontselection.addItem("Monospaced");
	fontselection.addItem("Dialog");
	fontselection.addItem("DialogInput");
	fontselection.addActionListener(this);
	fontselection.setActionCommand("fontsel");
	c.gridx = 0;
	c.gridy = 3;
	pane.add(fontselection, c);
	
	fontsizeselection = new JComboBox();
	fontsizeselection.setEditable(false);
	fontsizeselection.addItem("12");
	fontsizeselection.addItem("14");
	fontsizeselection.addItem("16");
	fontsizeselection.addItem("18");
	fontsizeselection.addItem("20");
       	fontsizeselection.addItem("22");
	fontsizeselection.addItem("24");
	fontsizeselection.addItem("26");
	fontsizeselection.addItem("28");
	fontsizeselection.addItem("30");
	fontsizeselection.addActionListener(this);
	fontsizeselection.setActionCommand("fontsizesel");
	c.gridx = 1;
	c.gridy = 3;
	pane.add(fontsizeselection, c);

	//bullets feature is glitchy
	textBody.setEditorKit(new HTMLEditorKit());
	HTMLEditorKit.InsertHTMLTextAction bulletAction = new HTMLEditorKit.InsertHTMLTextAction("Bullets", "<li> </li>", HTML.Tag.BODY, HTML.Tag.UL);  
	bullets = new JButton(bulletAction);
	textBody.setText(textBody.getText());
	textBody.repaint();
	c.gridx = 3;
	c.gridy = 3;
	pane.add(bullets, c);
	
	//tts button
	tts = new JButton("Text-to-Speech");
	tts.addActionListener(this);
	tts.setActionCommand("tts");
	c.gridx = 2;
	c.gridy = 3;
	c.gridheight = 1;
	pane.add(tts, c);
	    
	//upload picture
	picture = new JButton("Upload picture");
	picture.addActionListener(this);
	picture.setActionCommand("picture");
	c.gridx = 4;
	c.gridy = 3;
	pane.add(picture, c);


	/**
	pane.add(titlelabel);
	pane.add(titlebar);
	pane.add(textlabel);
	pane.add(textBody);
	pane.add(scroll2,BorderLayout.CENTER);
	//pane.add(b);
	pane.add(fontselection);
	pane.add(fontsizeselection);

	
	pane.add(tts);
	pane.add(bullets);
	pane.add(picture);
	**/

	currentlocation = this.getLocation();
	pane.addComponentListener(c1);
	
	b.setEnabled(ifChanged);
       	textBody.addKeyListener(k1);
	titlebar.addKeyListener(k2);

	setTitle(current);
	setVisible(true);
       
  }
    
    
    public Postitnotes(String filename) {
	this.setSize(600,300);
	
	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        b = new JButton("save");
	b.addActionListener(this);
	b.setActionCommand("save");
	titlebar = new JTextField(10);
	titlelabel = new JLabel("TITLE: ");

	textBody = new JTextPane();
	textBody.setSize(10,60);
	textlabel = new JLabel("TEXT: ");

	
       	fontselection = new JComboBox();
	fontselection.setEditable(false);
	fontselection.addItem("Serif");
	fontselection.addItem("SansSerif");
	fontselection.addItem("Monospaced");
	fontselection.addItem("Dialog");
	fontselection.addItem("DialogInput");
	fontselection.addActionListener(this);
	fontselection.setActionCommand("fontsel");

	fontsizeselection = new JComboBox();
	fontsizeselection.setEditable(false);
	fontsizeselection.addItem("12");
	fontsizeselection.addItem("14");
	fontsizeselection.addItem("16");
	fontsizeselection.addItem("18");
	fontsizeselection.addItem("20");
       	fontsizeselection.addItem("22");
	fontsizeselection.addItem("24");
	fontsizeselection.addItem("26");
	fontsizeselection.addItem("28");
	fontsizeselection.addItem("30");
	fontsizeselection.addActionListener(this);
	fontsizeselection.setActionCommand("fontsizesel");
	
	JScrollPane scroll2 = new JScrollPane(textBody,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	//bullets feature is glitchy
	textBody.setEditorKit(new HTMLEditorKit());
	/**
	HTMLEditorKit.InsertHTMLTextAction bulletAction = new HTMLEditorKit.InsertHTMLTextAction("Bullets", "<li> </li>", HTML.Tag.BODY, HTML.Tag.UL);  
	bullets = new JButton(bulletAction);
	textBody.setText(textBody.getText());
	textBody.repaint();
	**/
	//tts button
	tts = new JButton("Text-to-Speech");
	tts.addActionListener(this);
	tts.setActionCommand("tts");
	//upload picture
	picture = new JButton("Upload picture");
	picture.addActionListener(this);
	picture.setActionCommand("picture");

	
	textBody.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
	pane.add(titlelabel);
	pane.add(titlebar);
	pane.add(textlabel);
	pane.add(textBody);
	pane.add(scroll2,BorderLayout.CENTER);
	pane.add(b);
	pane.add(fontselection);
       	pane.add(fontsizeselection);
	pane.add(tts);
	openFile(filename);
	this.setLocation(currentlocation);

	pane.addComponentListener(c1);
	timestamp = new JLabel(lastmod);



	//	pane.add(bullets);
	pane.add(picture);
	pane.add(timestamp);
        
	b.setEnabled(ifChanged);
       	textBody.addKeyListener(k1);
	titlebar.addKeyListener(k2);
	setTitle(current);
	setVisible(true);
       
  }

    private ComponentListener c1 = new ComponentAdapter(){
	    public void componentMoved(ComponentEvent e){
		ifChanged = true;
		b.setEnabled(ifChanged);
	    }
	};
    
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

	    textBody.write(writer);

	    writer.write("font="+fontchosen+"fontsizechosen="+fontsizechosen+this.getLocation());
	  
	    writer.close();
	   
	    
	    current = filename;
	    setTitle(current);

	    // currentlocation =this.getLocation();

	    File f = new File("postitnotes/"+current+".txt");
	    SimpleDateFormat formatting = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    String modified = formatting.format(f.lastModified());
	    lastmod = modified;

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

	    Scanner s = new Scanner (new File("postitnotes/"+filename+".txt"));
	    String choices = "";
	    while (s.hasNext()){
		choices = s.next();
	    }
	    fontchosen = choices.substring((choices.indexOf("font="))+5,(choices.indexOf("fontsizechosen")));
	    fontsizechosen = Integer.parseInt(choices.substring((choices.indexOf("fontsizechosen="))+15,(choices.indexOf("fontsizechosen="))+17));

	    Integer xval = Integer.parseInt(choices.substring((choices.indexOf("java.awt.Point[x="))+17,(choices.indexOf(",y="))));
	    Integer yval = Integer.parseInt(choices.substring((choices.indexOf(",y="))+3,(choices.indexOf("]"))));

	    currentlocation = new Point(xval, yval);
	    s.close();

	    gotofont = new Font(fontchosen, Font.PLAIN, fontsizechosen);
	    textBody.setFont(gotofont);
	    titlebar.setFont(gotofont);
	    
	    File f = new File("postitnotes/"+current+".txt");
	    SimpleDateFormat formatting = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    String modified = formatting.format(f.lastModified());
	    lastmod = modified;
	    ifOpened = true;
	    ifChanged = false;
	    //file opens
	}
	catch(IOException e){
	    e.printStackTrace();
	}
    }

    

    public void actionPerformed(ActionEvent ev){
	//click save button -> saves file
	String event = ev.getActionCommand();
	if (event.equals("save")){
	    System.out.println("Clicked Save");
	    if (titlebar.getText().equals("")){
		JOptionPane.showMessageDialog(pane, "You didn't write a title!");

	    }
	    else if(current.equals("Untitled")){
	        current = titlebar.getText();
		saveFile(current);
		setTitle(current);
	    }
	    else if (new File("postitnotes/"+current+".txt").exists()){
		int confirm = JOptionPane.showConfirmDialog(pane, "Would you like to override the existing note?");
		if(confirm == JOptionPane.YES_OPTION){
		    saveFile(current);
		}
	    }
	    else{
		saveFile(current);
	    }
	    timestamp.setText(lastmod);
	    refreshedBar.setVisible(false);
	    Sidebar newRefresh = new Sidebar();
	}

	if (event.equals("fontsel")){
	    JComboBox selection = (JComboBox) ev.getSource();
	    fontchosen = (String) selection.getSelectedItem();
	    gotofont = new Font(fontchosen, Font.PLAIN, fontsizechosen);
	    textBody.setFont(gotofont);
	    titlebar.setFont(gotofont);
	    ifChanged = true;
	    b.setEnabled(ifChanged);
	}

	
	if (event.equals("fontsizesel")){
	    JComboBox sel = (JComboBox) ev.getSource();
	    fontsizechosen = Integer.parseInt((String)sel.getSelectedItem());

	    textBody.setFont(new Font(fontchosen,Font.PLAIN,fontsizechosen));
	    titlebar.setFont(new Font(fontchosen,Font.PLAIN,fontsizechosen));

	    ifChanged = true;
	    b.setEnabled(ifChanged);
	}
		
	if(event.equals("tts")){
	    Voice voice;
	    VoiceManager vm = VoiceManager.getInstance();
	    voice = vm.getVoice(voicename);
	    voice.allocate();
	    
	    String words = textBody.getText();
	    String wordstosay = words.substring((words.indexOf("<p style=\"margin-top: 0\">"))+25,(words.indexOf("</p>")));
	    //voice.speak(textBody.getText());
	    voice.speak(wordstosay);
	}

	if(event.equals("picture")){
	    JFileChooser file = new JFileChooser();
	    file.setDialogTitle("Choose Picture");
	    file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    file.setCurrentDirectory(new File(System.getProperty("user.home")));
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image", "jpg", "gif", "png");
	    file.addChoosableFileFilter(filter);
	    file.setAcceptAllFileFilterUsed(false);
	    int result = file.showSaveDialog(null);
	    if(result == JFileChooser.APPROVE_OPTION){
		File selectedFile = file.getSelectedFile();
		String path = selectedFile.getAbsolutePath();
		ImageIcon myPic = new ImageIcon(path);
		Image img = myPic.getImage();
		Image newimg = img.getScaledInstance(textBody.getWidth(), textBody.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newimg);
		textBody.insertIcon(image);
	    }
	    else if(result == JFileChooser.CANCEL_OPTION){
		System.out.println("No File Select");
	    }
	}
	    
    }


    //getters and setters will be here (if needed for sidebar or texteditor)
    public void setFontSize(int i){
	fontsizechosen = i;
    }
    public int fontSize(){
	return fontsizechosen;
    }
    public void setFont(String f){
	fontchosen = f;
    }
    public String font(){
	return fontchosen;
    }

    
    public static void main (String[]args){
	Postitnotes b = new Postitnotes();
       	Postitnotes c = new Postitnotes("nihao");
    }
}
