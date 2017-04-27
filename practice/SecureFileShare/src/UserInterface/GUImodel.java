package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dropbox.core.DbxException;

import DBfunctions.DBconn;
import DBfunctions.DBstore;
import DropboxConnection.DropboxConnection;
import DropboxFunctions.UploadFiles;
import SecureFeatures.EncryptFile;


import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class GUImodel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	String filepath,fileName,chosenFile, jusFileName ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUImodel frame = new GUImodel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUImodel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 876);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	//	contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 843, 787);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Upload", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFileChooser chooser = new JFileChooser();
	            chooser.setCurrentDirectory(new java.io.File("."));
	            chooser.setDialogTitle("Browse the folder to process");
	            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	            chooser.setAcceptAllFileFilterUsed(false);
	            //chooser.setPreferredSize(new Dimension(int width,int height));

	          
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	                System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
	                System.out.println("getSelectedFile() : "+ chooser.getSelectedFile());
	                chosenFile=chooser.getSelectedFile().toString();
	                String cut[] = chosenFile.split("\\\\");
	                fileName = chooser.getSelectedFile().getName();
	                int len= cut.length;
	                //jusFileName = cut[len-1];
	               jusFileName = fileName;
	                textField.setText(fileName);
	            } else {
	                System.out.println("No Selection ");
	            }
	          
	            filepath= chosenFile;
				
				
				
			}
		});
		
		btnNewButton.setBounds(37, 150, 155, 37);
		panel.add(btnNewButton);
		
		JLabel lblUploadFile = new JLabel("Upload File");
		lblUploadFile.setBounds(333, 24, 146, 60);
		panel.add(lblUploadFile);
		
		
		textField = new JTextField();
		textField.setBounds(219, 151, 585, 36);
		panel.add(textField);
		textField.setEnabled(false);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(37, 323, 779, 325);
		panel.add(textArea);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(778, 324, 38, 341);
		panel.add(scrollBar);
		
		JRadioButton rdbtnEnablePassword = new JRadioButton("Enable Password");
		rdbtnEnablePassword.setBounds(37, 221, 223, 37);
		panel.add(rdbtnEnablePassword);
		
		if(rdbtnEnablePassword.isSelected())
		{
			textField.setEnabled(true);
		}
		
		textField_1 = new JTextField();
		textField_1.setBounds(391, 222, 413, 35);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(filepath == null)
				{
					JOptionPane.showMessageDialog(panel, "Please select a file to upload using Browse");
				}
				else
				{
					
					 
					String password = null;
					password = textField.getText();
					boolean pwdexist = true;
					if(password == null)
					{
						pwdexist = false;
					}
					String em="ENC_"+fileName;
					DBstore obj2 =new DBstore();
					DBconn sa = new DBconn();
					obj2=sa.getData(em);
					
					System.out.println("FILE NAME : "+obj2.getFileName());
					String filematch = obj2.getFileName();

					if(filematch!=null)
							{
								System.out.println("This file is already encrypted. Please try another file");
								JOptionPane.showMessageDialog(panel, "The file "+jusFileName+" is already encrypted.Please try uploading another file");
								
							}
							else
							{
								
								//Encrypt the file
								String efile ;
								
								EncryptFile encFile=new EncryptFile(128,"AES","AES",fileName,password,pwdexist);
								//input file to be encrypted
								File inputFile = new File(filepath);
								//output location for the encrypted file
								File encryptedFile = new File("temp/ENC_"+ fileName);
								efile ="temp/ENC_"+fileName;
								System.out.println("Encrypted file path :"+efile);
								//Call the Encrypt function
								 try {
									encFile.Encrypt(inputFile, encryptedFile);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								
								boolean upload=UploadFiles.uploadToDropbox(DropboxConnection.client, efile,em);
								System.out.println("Boolean value upload: "+upload);
								if(upload == true){
									JOptionPane.showMessageDialog(panel, "Upload successful");
								
									
								//Delete the file from the temp folder
									File fileDel = new File(efile);      
									fileDel.delete();
							}
							}
					 
					
				}			
							
				
				
				
				
			}
		});
		btnUpload.setBounds(324, 683, 155, 37);
		panel.add(btnUpload);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Download", null, panel_1, null);
		panel_1.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(313, 139, 439, 35);
		panel_1.add(comboBox);
		
		JLabel lblChooseAFile = new JLabel("Choose a File");
		lblChooseAFile.setBounds(66, 142, 159, 29);
		panel_1.add(lblChooseAFile);
		
		JButton btnDownload = new JButton("DOWNLOAD");
		btnDownload.setBounds(322, 287, 180, 37);
		panel_1.add(btnDownload);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Share", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(38, 120, 155, 37);
		panel_2.add(btnBrowse);
		
		textField_2 = new JTextField();
		textField_2.setBounds(272, 121, 450, 35);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblToEmail = new JLabel("To Email*");
		lblToEmail.setBounds(54, 212, 104, 29);
		panel_2.add(lblToEmail);
		
		textField_3 = new JTextField();
		textField_3.setBounds(272, 209, 316, 35);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSetPassword = new JLabel("Set Password*");
		lblSetPassword.setBounds(38, 294, 192, 29);
		panel_2.add(lblSetPassword);
		
		textField_4 = new JTextField();
		textField_4.setBounds(272, 291, 206, 35);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(272, 375, 206, 35);
		panel_2.add(comboBox_1);
		
		JLabel lblAccessLevel = new JLabel("Access Level");
		lblAccessLevel.setBounds(38, 378, 166, 29);
		panel_2.add(lblAccessLevel);
		
		JButton btnShare = new JButton("SHARE");
		btnShare.setBounds(323, 537, 155, 37);
		panel_2.add(btnShare);
	}
}
