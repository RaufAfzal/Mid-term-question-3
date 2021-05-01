/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rauf
 */
public class Notepad extends JFrame implements ActionListener  {
    
    JTextArea area;
    JScrollPane pane;
     String text = "";
    Notepad(){
        setBounds(0,0,1950,1050);
        
        JMenuBar menubar= new JMenuBar();
        
        JMenu file=new JMenu("File");
        
        JMenuItem  newdoc= new JMenuItem("New");
        newdoc.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);
        
        JMenuItem  Open= new JMenuItem("Open");
        Open.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        Open.addActionListener(this);
        
        JMenuItem  Save= new JMenuItem("Save");
        Save.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        Save.addActionListener(this);
        
        JMenuItem  Print= new JMenuItem("Print");
        Print.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        Print.addActionListener(this);
        
        JMenuItem  Exit= new JMenuItem("Exit");
        Exit.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_ESCAPE, 0));
        Exit.addActionListener(this);
        
       
        
       
        
        file.add(newdoc);
        file.add(Open);
        file.add(Save);
        file.add(Print);
        file.add(Exit);
        
        
        
        JMenu edit=new JMenu("Edit");
        
         JMenuItem  copy= new JMenuItem("Copy");
         
        copy.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        
         JMenuItem  paste= new JMenuItem("Paste");
        paste.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        
         JMenuItem  cut= new JMenuItem("Cut");
        cut.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        
         JMenuItem  select_all= new JMenuItem("Select All");
         select_all.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
         select_all.addActionListener(this);
         
         edit.add(copy);
         edit.add(paste);
         edit.add(cut);
         edit.add(select_all);
        
       
        JMenu help=new JMenu("Help");
        
        JMenuItem  about= new JMenuItem("About the Notepad");
        about.addActionListener(this);
        help.add(about);
        
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);
        
        setJMenuBar(menubar);
        
        
        area = new JTextArea();
        area.setFont(new Font("SAN-SERIF",Font.PLAIN,20)); 
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        pane = new JScrollPane(area);
        
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Notepad().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("New")) {
            area.setText("");
        
        } else if (ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false); 
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt"); 
            chooser.addChoosableFileFilter(restrict);
    	
            int result = chooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
				
                try{
                    System.out.println("HEki");
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    area.read( br, null );
                    br.close();
                    area.requestFocus();
                }catch(Exception e){
                    System.out.print(e);
                }
            }
        } else if(ae.getActionCommand().equals("Save")){
            final JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
            int actionDialog = SaveAs.showOpenDialog(this);
            if (actionDialog != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File fileName = new File(SaveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch(Exception e){}
        }else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        }else if (ae.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        }else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        }else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }else if (ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        }else if (ae.getActionCommand().equals("About Notepad")) {
            new about().setVisible(true);
            
        }
    }
    }

    
    

