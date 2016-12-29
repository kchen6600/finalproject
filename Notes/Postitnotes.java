import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

public class Postitnotes extends JFrame{
    public Postitnotes(){
	//create the sidebar

	//create the place for text input
	textBody();
	//create the window
	editor();
    }

    //save file
    private void saveFile(File filename){
	try{
	    BufferedWriter w = new BufferedWriter(new FileWriter(filename));

	    //to be saved in special "notes" folder or something, needs to create the "notes" folder though
	    w.write(textBody.getText());
	    w.close();
	    window.setTitle(filename.getName());
	    ifSaved = true;
	    //System.out.println("File saved!") This might have to do with GUI stuff
	}
	catch (IOException e){
	    e.printStackTrace();
	    //System.out.println("File could not be saved, file is open elsewhere, etc.") This might have to do with GUI stuff
	}
    }
    //save edits to file
    private void saveEdits(File filename){
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
	    opened = filename;
	    FileReader r = new FileReader(filename);
	    textBody.read(r, null);
	    window.setTitle(filename.getName());
	    ifOpened = true;
	    //file opens
	}
	catch(IOException e){
	    e.printStackTrace();
	    //System.out.println("Could not open, file does not exist, etc.") This might have to do with GUI stuff
	}
    }

    private JFrame textBody(){
    }

    private JFrame editor(){
    }
}
