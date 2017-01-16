//
//MUST COMPILE WITH .:jar/*
//

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.io.*;
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
    private String fontchosen, fontc;
    private Integer fontsizechosen, fontsc;
    private String lastmod;
    private boolean ifChanged = false;
    private boolean ifSaved, ifOpened;
    private static final String voicename = "kevin16";

    private Font gotofont;

    private String current = "Untitled";
    private Sidebar refreshedBar;

    
    public Postitnotes() {
	refreshedBar = new Sidebar();
	this.setTitle("CREATE NEW NOTE");
	this.setSize(600,300);
	this.setLocation(100,100);
	
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
	lastmod = "Not saved";
	timestamp = new JLabel(lastmod);
	fontselection = new JComboBox();
	fontselection.setEditable(true);
	fontselection.addItem("Serif");
	fontselection.addItem("SansSerif");
	fontselection.addItem("Monospaced");
	fontselection.addItem("Dialog");
	fontselection.addItem("DialogInput");
	fontselection.addActionListener(this);
	fontselection.setActionCommand("fontsel");
	
	fontsizeselection = new JComboBox();
	fontsizeselection.setEditable(true);
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

	//bullets feature is glitchy
	textBody.setEditorKit(new HTMLEditorKit());
	HTMLEditorKit.InsertHTMLTextAction bulletAction = new HTMLEditorKit.InsertHTMLTextAction("Bullets", "<li> </li>", HTML.Tag.BODY, HTML.Tag.UL);  
	bullets = new JButton(bulletAction);
	textBody.setText(textBody.getText());
	textBody.repaint();
	//tts button
	tts = new JButton("Text-to-Speech");
	tts.addActionListener(this);
	tts.setActionCommand("tts");
	//upload picture
	picture = new JButton("Upload picture");
	picture.addActionListener(this);
	picture.setActionCommand("picture");
	textBody.setFont(new Font("Serif",Font.PLAIN,12));
	titlebar.setFont(new Font("Serif",Font.PLAIN,12));
	gotofont = new Font("Serif", Font.PLAIN, 12);
	fontchosen = "Serif";
	fontsizechosen = 12;
	JScrollPane scroll2 = new JScrollPane(textBody,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	pane.add(titlelabel);
	pane.add(titlebar);
	pane.add(textlabel);
	pane.add(textBody);
	pane.add(scroll2,BorderLayout.CENTER);
	pane.add(b);
	pane.add(fontselection);
	pane.add(fontsizeselection);

	
	pane.add(tts);
	pane.add(bullets);
	pane.add(picture);
	pane.add(timestamp);
	b.setEnabled(ifChanged);
       	textBody.addKeyListener(k1);
	titlebar.addKeyListener(k2);

	setTitle(current);
	setVisible(true);
       
  }
    
    
    public Postitnotes(String filename) {
	System.out.println(fontchosen + fontsizechosen);
	this.setSize(600,300);
	this.setLocation(100,100);
	
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
	fontselection.setEditable(true);
	fontselection.addItem("Serif");
	fontselection.addItem("SansSerif");
	fontselection.addItem("Monospaced");
	fontselection.addItem("Dialog");
	fontselection.addItem("DialogInput");
	fontselection.addActionListener(this);
	fontselection.setActionCommand("fontsel");

	fontsizeselection = new JComboBox();
	fontsizeselection.setEditable(true);
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
	
	
	if (fontchosen == null){
	    fontchosen = "Serif";
	}	    

	if (fontsizechosen == null){
	    fontsizechosen = 12;
	}	
        
	JScrollPane scroll2 = new JScrollPane(textBody,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	//bullets feature is glitchy
	textBody.setEditorKit(new HTMLEditorKit());
	HTMLEditorKit.InsertHTMLTextAction bulletAction = new HTMLEditorKit.InsertHTMLTextAction("Bullets", "<li> </li>", HTML.Tag.BODY, HTML.Tag.UL);  
	bullets = new JButton(bulletAction);
	textBody.setText(textBody.getText());
	textBody.repaint();
	//tts button
	tts = new JButton("Text-to-Speech");
	tts.addActionListener(this);
	tts.setActionCommand("tts");
	//upload picture
	picture = new JButton("Upload picture");
	picture.addActionListener(this);
	picture.setActionCommand("picture");
	
	openFile(filename);
	pane.add(titlelabel);
	pane.add(titlebar);
	pane.add(textlabel);
	pane.add(textBody);
	pane.add(scroll2,BorderLayout.CENTER);
	pane.add(b);
	pane.add(fontselection);
       	pane.add(fontsizeselection);
	pane.add(tts);


	timestamp = new JLabel(lastmod);

	pane.add(bullets);
	pane.add(picture);
	pane.add(timestamp);
	textBody.setFont(new Font(fontchosen, Font.PLAIN, fontsizechosen));


        
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

	    File f = new File("postitnotes/"+current+".txt");
	    SimpleDateFormat formatting = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    String modified = formatting.format(f.lastModified());
	    lastmod = modified;

	    ifChanged = false;
	    b.setEnabled(false);
	    System.out.println(fontchosen + fontsizechosen);
	    fontc = fontchosen;
	    fontsc = fontsizechosen;

	}
	catch(IOException e){
	    e.printStackTrace();
	}

    }

    //open file
    private void openFile(String filename){
	try{
	    
	    System.out.println(fontc +fontsc);
	    
	    textBody.setFont(new Font(fontchosen, Font.PLAIN, fontsizechosen));
	    titlebar.setFont(new Font(fontchosen, Font.PLAIN, fontsizechosen));
	    current = filename;
	    titlebar.setText(filename);
	    FileReader r = new FileReader("postitnotes/"+filename+".txt");
	    textBody.read(r, null);

	    
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
	    if(current.equals("Untitled")){
	        current = titlebar.getText();
		saveFile(current);
		setTitle(current);
		System.out.println(ifChanged);
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
	    System.out.println("Font selected");
	    JComboBox selection = (JComboBox) ev.getSource();
	    fontchosen = (String) selection.getSelectedItem();
	    gotofont = new Font(fontchosen, Font.PLAIN, fontsizechosen);
	    textBody.setFont(gotofont);
	    titlebar.setFont(gotofont);
	    System.out.println("Font set"+fontchosen+fontsizechosen);
	    ifChanged = true;
	    b.setEnabled(ifChanged);
	    System.out.println(ifChanged);
	    System.out.println(fontchosen);
	}

	
	if (event.equals("fontsizesel")){
	    System.out.println("Font size selected");
	    JComboBox sel = (JComboBox) ev.getSource();
	    fontsizechosen = Integer.parseInt((String)sel.getSelectedItem());

	    System.out.println(fontsizechosen);
	    textBody.setFont(new Font(fontchosen,Font.PLAIN,fontsizechosen));
	    titlebar.setFont(new Font(fontchosen,Font.PLAIN,fontsizechosen));
	    System.out.println("Font size set");

	    ifChanged = true;
	    b.setEnabled(ifChanged);
	    System.out.println(ifChanged);
	}
		
	if(event.equals("tts")){
	    Voice voice;
	    VoiceManager vm = VoiceManager.getInstance();
	    voice = vm.getVoice(voicename);
	    voice.allocate();
	    voice.speak(textBody.getText());	    
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
       	Postitnotes c = new Postitnotes("anotha");
    }
}
