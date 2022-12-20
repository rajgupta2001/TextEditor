import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

/*
Project - TextEditor(NotePad)
Created using Java Swing.
Does Operations like Cut, Copy, Paste, Open file, Save file and more.
Date of Project = 10 December 2022
*/

public class TextEditor implements ActionListener {

    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu file, edit, close;
    JMenuItem open, save, print, newFile;
    JMenuItem copy, cut, paste;
    JMenuItem closeEditor;

    TextEditor(){
        //Creating Frame(Window)
        frame=new JFrame("Text Editor");
        frame.setBounds(50, 50, 450, 500);

        //Creating Textarea
        jTextArea=new JTextArea("Welcome to Text-Editor");

        //Adding TextArea in Frame
        frame.add(jTextArea);

        //Creating Menubar
        jMenuBar=new JMenuBar();

        //Adding MenuBar in Frame
        frame.setJMenuBar(jMenuBar);

        //Creating different Menu
        file=new JMenu("File");
        edit=new JMenu("Edit");
        close=new JMenu("Close");

        //Creating Menu Items for File
        open=new JMenuItem("Open");
        open.addActionListener(this);

        save=new JMenuItem("Save");
        save.addActionListener(this);

        print=new JMenuItem("Print");
        print.addActionListener(this);

        newFile=new JMenuItem("New");
        newFile.addActionListener(this);

        //Adding Menu Items into File
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(newFile);

        //Creating Menu Items for Edit
        copy=new JMenuItem("Copy");
        copy.addActionListener(this);

        cut=new JMenuItem("Cut");
        cut.addActionListener(this);

        paste=new JMenuItem("Paste");
        paste.addActionListener(this);

        //Adding Menu Items into Edit
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);

        //Creating and Adding Menu Items for Close
        closeEditor=new JMenuItem("Close");
        closeEditor.addActionListener(this);
        close.add(closeEditor);

        //Adding menu into MenuBar
        jMenuBar.add(file);
        jMenuBar.add(edit);
        jMenuBar.add(close);

        //Close our Console as we Close Notepad
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //To make our frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TextEditor notepad=new TextEditor();
    }

    //To give actions/commands to components
    @Override
    public void actionPerformed(ActionEvent e) {

        //For checking which button has been pressed
        String s=e.getActionCommand();

        //Checking for each button created
        //For edit
        if(s.equals("Copy")) jTextArea.copy();
        else if(s.equals("Cut")) jTextArea.cut();
        else if(s.equals("Paste")) jTextArea.paste();

        //For file
        else if(s.equals("Print")) {
            try {
                jTextArea.print();
            }
            catch(PrinterException ex){
                throw new RuntimeException(ex);
            }
        }

        else if(s.equals("New")) jTextArea.setText("");

        else if(s.equals("Open")){
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="", s2="";
                try{
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null) s2+=s1+"\n";
                    jTextArea.setText(s2);
                } catch(FileNotFoundException ex){
                    throw new RuntimeException(ex);
                } catch(IOException ex){
                    throw new RuntimeException(ex);
                }
            }

        } else if(s.equals("Save")){
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer;
                try {
                    writer=new BufferedWriter(new FileWriter(file, false));
                    writer.write(jTextArea.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        //For close
        //else if(s.equals("Close")) frame.setVisible(false);
        //For closing execution below line for not closing above line
        else if(s.equals("Close")) System.exit(1);
    }
}
