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
	    w.write(textBody.getText());
	    w.close();
	    ifSaved = true;
	    window.setTitle(filename.getName());
	}
	catch (IOException error){
	    error.printStackTrace();
	}
    }
    //save edits to file
    private void saveEdits(File filename){
	try{
	    BufferedWriter w = new BufferedWriter(new FileWriter(filename));
	    w.write(textBody.getText());
	    w.close();
	}
	catch (IOException error){
	    error.printStackTrace();
	}
    }
    //open file

    
}
