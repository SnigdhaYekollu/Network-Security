package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class GUImodel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
				
				//TODO Browse 
				
				
				
				
				
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
		
		textField_1 = new JTextField();
		textField_1.setBounds(391, 222, 413, 35);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnUpload = new JButton("UPLOAD");
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
