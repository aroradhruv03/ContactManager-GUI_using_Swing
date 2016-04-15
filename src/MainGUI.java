/**
 * @Author: Dhruv Arora
 * @Date : 09/14/2014
 *
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// Class for Jtextfield, to limit the no of characters entered
class JTextFieldLimit extends PlainDocument
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limit;
	
	public JTextFieldLimit(int limit)
	{
		super();
		this.limit = limit;
	}
	
	public JTextFieldLimit(int limit,boolean upper)
	{
		super();
		this.limit = limit;
	}
	
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	    if (str == null)
	      return;

	    if ((getLength() + str.length()) <= limit) {
	      super.insertString(offset, str, attr);
	    }
	  }
}

/*
 * Class which contains all the GUI Rendering components, their initialization and their function
 * Invoked from the Main Class
 */
public class MainGUI extends JFrame {
	
	/**
	 * The serialization runtime associates with each serializable class a version number, 
	 * called a serialVersionUID, which is used during deserialization to verify that the 
	 * sender and receiver of a serialized object have loaded classes for that object that 
	 * are compatible with respect to serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	// Declaring the content pane, all the UI Components are added to this pane
	private JPanel contentPane = new JPanel();
	
	// Labels for text fields
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblMiddleName;
	private JLabel lblGender;
	private JLabel lblEmail;
	private JLabel lblAddress1;
	private JLabel lblAddress2;
	private JLabel lblCity;
	private JLabel lblState;
	private JLabel lblPhoneNo;
	private JLabel lblZipCode;
	private JLabel lblCountry;
	
	//Declaring all Textfields
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtLastName;
    private JTextField txtEmail;
	private JTextField txtAddress1;
	private JTextField txtAddress2;
	private JTextField txtCity;
	private JTextField txtPhoneNo;
	private JTextField txtState;
	private JTextField txtZipCode;
	private JTextField txtCountry;
	private JTextField txtFields[] = new JTextField [11]; // Used for storing all Text Fields in a array for easy access
	
	// Declaring the boolean for textfields completion
	private boolean booFirstName=false;
	private boolean booMiddleName=false;
	private boolean booLastName=false;
	private boolean booEmail=false;
	private boolean booGender=false;
	private boolean booAddress1=false;
	private boolean booAddress2=false;
	private boolean booCity=false;
	private boolean booPhoneNo=false;
	private boolean booState=false;
	private boolean booZipCode=false;
	private boolean booCountry=false;
	private boolean booArray[] = {booFirstName, booMiddleName, booLastName, booEmail, 
			booGender, booAddress1, booAddress2, booCity, booPhoneNo, booState, booZipCode, booCountry}; // used for string all the boolean
	
	// Declaring the Radio Buttons & Group
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private ButtonGroup groupGender; // For grouping the radio buttons
	private String gender= null; // For storing the gender
	
	//Declaring the JTable & its data model
	private JTable table;
	// Table Model, used for displaying the Selectable Table in Form
	private DefaultTableModel tableModel;
	private JLabel lblStatusBar1;
	private JLabel lblStatusBar2;
	
	private JLabel lblErrFName;
	private JLabel lblErrMName;
	private JLabel lblErrLName;
	private JLabel lblErrGender;
	private JLabel lblErrEmail;
	private JLabel lblErrAdd1;
	private JLabel lblErrAdd2;
	private JLabel lblErrCity;
	private JLabel lblErrState;
	private JLabel lblErrZip;
	private JLabel lblErrPhNo;
	private JLabel lblErrCountry;
	private JLabel lblErrLabels[] = new JLabel[12] ; // Used for storing all Error Labels in a array for easy access
	
	
    // Buttons used -
    private JButton btnSave;
    private JButton btnClear;
    private JButton btnDelete;
    private JButton btnUpdate;
    
    // Labels on Buttons
    private JLabel lblSave;
    private JLabel lblClear;
    private JLabel lblDelete;
    private JLabel lblUpdate;
    
    // Progress bar -
    private JProgressBar progressBar;
    // Counter to show completion of Jbar, initialized as 0 to show that progress is 0 at the start
    private int counterProgress=0;
    
    private Border defaultBorder; // Will store a border for restoring to default
    private JTextField txtHiddenField;
    
    // Setting the Font all
    Font fontErrLabel = new Font("Segoe UI Symbol 8", Font.PLAIN, 11);
    Font fontTxtLabel = new Font("Segoe UI Symbol 8", Font.PLAIN, 12);
    Font fontTxtField = new Font("Segoe UI Symbol 8", Font.PLAIN, 12);
    Font fontProgressBar = new Font("Segoe UI Symbol 8", Font.BOLD, 12);
    Font fontButton = new Font("Segoe UI Symbol 8", Font.PLAIN, 12);
    
    Border errorBorder = BorderFactory.createLineBorder(Color.red);
    Border successBorder = BorderFactory.createLineBorder(Color.green);
    Border selectedBorder = BorderFactory.createLoweredSoftBevelBorder();
    
    private JLabel lblStatusBar;
    
    ImageIcon icon = new ImageIcon("./src/wall.png");
	
	/**
	 * Create the frame.
	 */
	public MainGUI() {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(20,20,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		setFont(new Font("Segoe UI Semilight", Font.BOLD, 12));
		setTitle("Contact Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = new Dimension ( 920,730 );

		setBounds ( ss.width / 2 - frameSize.width / 2, 
		                  ss.height / 2 - frameSize.height / 2,
		                  920, 730 ); // Gives position & size of our JFrame (X coordinate, Y Coordinate, width, length);
		
//		for ( GraphicsDevice screen : getGraphicsDevices () )
//	    {
//	        GraphicsConfiguration gc = screen.getDefaultConfiguration ();
//	        Rectangle sb = gc.getBounds ();
//
//
////	        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
//	        setBounds ( sb.x + sb.width / 2 - frameSize.width / 2,
//	                sb.y + sb.height / 2 - frameSize.height / 2, frameSize.width,
//	                frameSize.height );
//	        setVisible ( true );
//	    }
		
//		setSize(825,535);
//		setLocationRelativeTo(null);
		
		// If user clicks on the screen, it removes all the error messages
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Reset status bar
				statusBarDefault();
				// Clear all error label
				clearErrorLbls();
			}
		});
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{83, 20, 20, 9, 43, 35, 25, 40, 100, 18, 2, 32, 33, 60, 100, 142, 22, 0};
		gbl_contentPane.rowHeights = new int[]{22, 17, 2, 20, 20, 20, 20, 23, 1, 20, 20, 20, 20, 29, 20, 20, 20, 5, 30, 15, 0, 30, 17, 22, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		// Declaration - All the Main Headings
		JLabel lblHeading1 = new JLabel("Contact Information Entry Form");
		lblHeading1.setFont(new Font("Segoe UI Symbol 8", Font.PLAIN, 17));
		GridBagConstraints gbc_lblHeading1 = new GridBagConstraints();
		gbc_lblHeading1.anchor = GridBagConstraints.NORTH;
		gbc_lblHeading1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHeading1.insets = new Insets(10, 0, 5, 5);
		gbc_lblHeading1.gridwidth = 11;
		gbc_lblHeading1.gridx = 1;
		gbc_lblHeading1.gridy = 0;
		contentPane.add(lblHeading1, gbc_lblHeading1);

		JLabel lblHeading2 = new JLabel("Contact Information View");
		lblHeading2.setFont(new Font("Segoe UI Symbol 8", Font.PLAIN, 17));
		GridBagConstraints gbc_lblHeading2 = new GridBagConstraints();
		gbc_lblHeading2.anchor = GridBagConstraints.NORTH;
		gbc_lblHeading2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHeading2.insets = new Insets(10, 0, 5, 5);
		gbc_lblHeading2.gridwidth = 4;
		gbc_lblHeading2.gridx = 13;
		gbc_lblHeading2.gridy = 0;
		contentPane.add(lblHeading2, gbc_lblHeading2);
		// End of Heading declaration
		
		// Status Messages
		
		lblStatusBar1 = new JLabel("Note: * Denotes Mandatory Fields");
		lblStatusBar1.setFont(new Font("Segoe UI Symbol 8", Font.PLAIN, 13));
		lblStatusBar1.setForeground(Color.RED);
		GridBagConstraints gbc_lblStatusBar1 = new GridBagConstraints();
		gbc_lblStatusBar1.fill = GridBagConstraints.BOTH;
		gbc_lblStatusBar1.insets = new Insets(0, 8, 5, 5);
		gbc_lblStatusBar1.gridwidth = 9;
		gbc_lblStatusBar1.gridx = 0;
		gbc_lblStatusBar1.gridy = 1;
		contentPane.add(lblStatusBar1, gbc_lblStatusBar1);
		
		lblStatusBar2 = new JLabel("Note: Please select a row below to be viewed/deleted");
		lblStatusBar2.setForeground(Color.RED);
		lblStatusBar2.setFont(new Font("Segoe UI Symbol 8", Font.PLAIN, 13));
		GridBagConstraints gbc_lblStatusBar2 = new GridBagConstraints();
		gbc_lblStatusBar2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblStatusBar2.insets = new Insets(0, 10, 5, 5);
		gbc_lblStatusBar2.gridwidth = 6;
		gbc_lblStatusBar2.gridx = 11;
		gbc_lblStatusBar2.gridy = 1;
		contentPane.add(lblStatusBar2, gbc_lblStatusBar2);
		
		// The two sub headings
		JLabel lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setFont(new Font("Segoe UI Symbol 8", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPersonalInformation = new GridBagConstraints();
		gbc_lblPersonalInformation.anchor = GridBagConstraints.WEST;
		gbc_lblPersonalInformation.insets = new Insets(0, 7, 5, 5);
		gbc_lblPersonalInformation.gridwidth = 6;
		gbc_lblPersonalInformation.gridx = 0;
		gbc_lblPersonalInformation.gridy = 3;
		contentPane.add(lblPersonalInformation, gbc_lblPersonalInformation);
		
		JLabel lblContactInformation = new JLabel("Contact Information");
		lblContactInformation.setFont(new Font("Segoe UI Symbol 8", Font.PLAIN, 16));
		GridBagConstraints gbc_lblContactInformation = new GridBagConstraints();
		gbc_lblContactInformation.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblContactInformation.insets = new Insets(5, 7, 5, 5);
		gbc_lblContactInformation.gridwidth = 5;
		gbc_lblContactInformation.gridx = 0;
		gbc_lblContactInformation.gridy = 9;
		contentPane.add(lblContactInformation, gbc_lblContactInformation);
		
		// Declaration - All the separators, horizontal and vertical lines in GUI
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, -20, 5, 5);
		gbc_separator.gridheight = 2;
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 10;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		contentPane.add(separator, gbc_separator);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridheight = 2;
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.insets = new Insets(0, -20, 5, 5);
		gbc_separator_1.gridwidth = 10;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 8;
		contentPane.add(separator_1, gbc_separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.gridwidth = 2;
		gbc_separator_3.fill = GridBagConstraints.BOTH;
		gbc_separator_3.insets = new Insets(5, 0, 5, 5);
		gbc_separator_3.gridheight = 23;
		gbc_separator_3.gridx = 10;
		gbc_separator_3.gridy = 0;
		contentPane.add(separator_3, gbc_separator_3);
		
		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.fill = GridBagConstraints.BOTH;
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridwidth = 6;
		gbc_separator_4.gridx = 11;
		gbc_separator_4.gridy = 2;
		contentPane.add(separator_4, gbc_separator_4);
		btnSave = new JButton("Save");
		btnSave.setFont(fontButton);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.ipadx = 5;
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.insets = new Insets(0, 10, 5, 5);
		gbc_btnSave.gridwidth = 3;
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 19;
		contentPane.add(btnSave, gbc_btnSave);
		// End of Seperator Declaration
		
		txtHiddenField = new JTextField();
		GridBagConstraints gbc_txtHiddenField = new GridBagConstraints();
		gbc_txtHiddenField.fill = GridBagConstraints.BOTH;
		gbc_txtHiddenField.insets = new Insets(0, 0, 0, 5);
		gbc_txtHiddenField.gridx = 0;
		gbc_txtHiddenField.gridy = 24;
		contentPane.add(txtHiddenField, gbc_txtHiddenField);
		txtHiddenField.setColumns(10);
		txtHiddenField.setVisible(false); // Sets the Hidden text field to be hidden as its already created to take the default border
		defaultBorder = txtHiddenField.getBorder(); // Sets the default border
		
		// Calls the method to create the labels for Text Fields
		createTxtLabel();
		// Calls the method to create & instantiate the text fields
		createTxtFields();
		// Calls the method for all the Text Field Event Listeners
		txtFieldListener();
		
		
		// Create the radio buttons		
		createRadioBtn();
		// Action listeners on radio buttons
		rdbtnListeners();
		
		// Create the Action buttons
		createBtns();
		// Create the Labels for buttons
		createBtnLabels();
		// Create the event handlers for the buttons
		btnListeners();
		
		// Function to create & instantiate all the Error Labels
		createErrlabels();
				
		// Calls the function to create & display the Table
		createTable();
		
		// Listener for the table, If user clicks a record, display the details
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// function called to clear all the fields & labels
				clearErrorLbls();
				// Function to clear all text fields
				clearAllFields();
				// Defaults the status bar from any error messages
				statusBarDefault();
				// Set Default Border
				setDefaultBorder();
				// Function to display all the details
				showUserDetails();
			}
		});
		
		// Will Disable the Row going down on Enter key press, then will associate Enter Key with giving the details
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		table.getActionMap().put("Enter", new AbstractAction() {
				private static final long serialVersionUID = 1L;
				@Override
		        public void actionPerformed(ActionEvent ae) {
		            //do something on JTable enter pressed
		        }
		    });
										
		 // Will listen to events, if ENTER or SPACE key is pressed,then activates event
		table.addKeyListener(new KeyAdapter() {
		   @Override
		   public void keyPressed(KeyEvent keyEvent) {
		    if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER || keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
		    {
		    	// function called to clear all the fields & labels
				clearErrorLbls();
				// Function to clear all text fields
				clearAllFields();
				// Defaults the status bar from any error messages
				statusBarDefault();
				// Function to display all the details
				showUserDetails();
				// Set Default Border
				setDefaultBorder();
				    }
				 }
			});	
		
		
		lblStatusBar = new JLabel("Your Data Entry Progress --->");
		lblStatusBar.setForeground(Color.BLACK);
		lblStatusBar.setFont(new Font("Segoe UI Symbol 8", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStatusBar = new GridBagConstraints();
		gbc_lblStatusBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStatusBar.anchor = GridBagConstraints.NORTH;
		gbc_lblStatusBar.insets = new Insets(10, 10, 10, 5);
		gbc_lblStatusBar.gridwidth = 9;
		gbc_lblStatusBar.gridx = 0;
		gbc_lblStatusBar.gridy = 21;
		contentPane.add(lblStatusBar, gbc_lblStatusBar);
		
		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Segoe UI Symbol 8", Font.BOLD, 14));
		progressBar.setMaximum(10);
		progressBar.setValue(0);				// initial value is 0
		progressBar.setStringPainted(true);				 // for the painted % inside the bar
		GridBagConstraints gbc_pbName = new GridBagConstraints();
		gbc_pbName.gridheight = 2;
		gbc_pbName.gridwidth = 10;
		gbc_pbName.fill = GridBagConstraints.BOTH;
		gbc_pbName.insets = new Insets(0, 10, 5, 5);
		gbc_pbName.gridx = 0;
		gbc_pbName.gridy = 22;
		contentPane.add(progressBar, gbc_pbName);
		
	} // end of constructor
	
	
//	private static class __Tmp {
//		private static void __tmp() {
//			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
//		}
//	}
	
	/*
	 * Function to create all the labels for all Text Fields & the radio buttons.
	 * Naming Convention - lblRespectiveTextField
	 */
	private void createTxtLabel()
	{		
		lblFirstName = new JLabel("*First Name");
		lblFirstName.setFont(fontTxtLabel);
		lblFirstName.setLabelFor(txtFirstName);
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.fill = GridBagConstraints.VERTICAL;
		gbc_lblFirstName.insets = new Insets(0, 10, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 4;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		lblMiddleName = new JLabel(" Middle Initial");
		lblMiddleName.setFont(fontTxtLabel);
		lblMiddleName.setLabelFor(txtMiddleName);
		GridBagConstraints gbc_lblMiddleName = new GridBagConstraints();
		gbc_lblMiddleName.anchor = GridBagConstraints.WEST;
		gbc_lblMiddleName.fill = GridBagConstraints.VERTICAL;
		gbc_lblMiddleName.insets = new Insets(0, 10, 5, 5);
		gbc_lblMiddleName.gridx = 0;
		gbc_lblMiddleName.gridy = 5;
		contentPane.add(lblMiddleName, gbc_lblMiddleName);
		
		lblLastName = new JLabel("*Last Name");
		lblLastName.setFont(fontTxtLabel);
		lblLastName.setLabelFor(txtLastName);
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.fill = GridBagConstraints.VERTICAL;
		gbc_lblLastName.insets = new Insets(0, 10, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 6;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		lblGender = new JLabel("*Gender");
		lblGender.setFont(fontTxtLabel);
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.WEST;
		gbc_lblGender.fill = GridBagConstraints.VERTICAL;
		gbc_lblGender.insets = new Insets(0, 10, 5, 5);
		gbc_lblGender.gridx = 0;
		gbc_lblGender.gridy = 7;
		contentPane.add(lblGender, gbc_lblGender);
		
		lblEmail = new JLabel("*E-Mail");
		lblEmail.setFont(fontTxtLabel);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.fill = GridBagConstraints.VERTICAL;
		gbc_lblEmail.insets = new Insets(0, 10, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 10;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		lblPhoneNo = new JLabel("*Phone No");
		lblPhoneNo.setFont(fontTxtLabel);
		lblPhoneNo.setLabelFor(txtPhoneNo);
		GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
		gbc_lblPhoneNumber.anchor = GridBagConstraints.WEST;
		gbc_lblPhoneNumber.fill = GridBagConstraints.VERTICAL;
		gbc_lblPhoneNumber.insets = new Insets(0, 10, 5, 5);
		gbc_lblPhoneNumber.gridx = 0;
		gbc_lblPhoneNumber.gridy = 11;
		contentPane.add(lblPhoneNo, gbc_lblPhoneNumber);
		
		lblAddress1 = new JLabel("*Address 1");
		lblAddress1.setFont(fontTxtLabel);
		lblAddress1.setLabelFor(txtAddress1);
		GridBagConstraints gbc_lblAddress1 = new GridBagConstraints();
		gbc_lblAddress1.anchor = GridBagConstraints.WEST;
		gbc_lblAddress1.fill = GridBagConstraints.VERTICAL;
		gbc_lblAddress1.insets = new Insets(0, 10, 5, 5);
		gbc_lblAddress1.gridx = 0;
		gbc_lblAddress1.gridy = 12;
		contentPane.add(lblAddress1, gbc_lblAddress1);
		
		lblAddress2 = new JLabel(" Address 2");
		lblAddress2.setFont(fontTxtLabel);
		lblAddress2.setLabelFor(txtAddress2);
		GridBagConstraints gbc_lblAddress2 = new GridBagConstraints();
		gbc_lblAddress2.fill = GridBagConstraints.VERTICAL;
		gbc_lblAddress2.anchor = GridBagConstraints.WEST;
		gbc_lblAddress2.insets = new Insets(0, 10, 5, 5);
		gbc_lblAddress2.gridx = 0;
		gbc_lblAddress2.gridy = 13;
		contentPane.add(lblAddress2, gbc_lblAddress2);
		
		lblCity = new JLabel("*City");
		lblCity.setFont(fontTxtLabel);
		lblCity.setLabelFor(txtCity);
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.WEST;
		gbc_lblCity.fill = GridBagConstraints.VERTICAL;
		gbc_lblCity.insets = new Insets(0, 10, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 14;
		contentPane.add(lblCity, gbc_lblCity);
		
		lblState = new JLabel("*State");
		lblState.setFont(fontTxtLabel);
		lblState.setLabelFor(txtState);
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.WEST;
		gbc_lblState.fill = GridBagConstraints.VERTICAL;
		gbc_lblState.insets = new Insets(0, 10, 5, 5);
		gbc_lblState.gridx = 0;
		gbc_lblState.gridy = 15;
		contentPane.add(lblState, gbc_lblState);
		
		lblZipCode = new JLabel("*Zip Code");
		lblZipCode.setFont(fontTxtLabel);
		lblZipCode.setLabelFor(txtZipCode);
		GridBagConstraints gbc_lblZipCode = new GridBagConstraints();
		gbc_lblZipCode.anchor = GridBagConstraints.WEST;
		gbc_lblZipCode.fill = GridBagConstraints.VERTICAL;
		gbc_lblZipCode.insets = new Insets(0, 10, 5, 5);
		gbc_lblZipCode.gridx = 0;
		gbc_lblZipCode.gridy = 16;
		contentPane.add(lblZipCode, gbc_lblZipCode);
		
		lblCountry = new JLabel("*Country");
		lblCountry.setFont(fontTxtLabel);
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.fill = GridBagConstraints.VERTICAL;
		gbc_lblCountry.insets = new Insets(0, 10, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 17;
		contentPane.add(lblCountry, gbc_lblCountry);
	}
	
	/*
	 * Function to create all the Text Fields & to add them to the Jpanel
	 * Naming Convention - txtFieldName
	 */
	private void createTxtFields()
	{
		txtFirstName = new JTextField();
		txtFirstName.setToolTipText("Example : Harry; If name is Harry James Potter");
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.fill = GridBagConstraints.BOTH;
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstName.gridwidth = 5;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 4;
		contentPane.add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(20);
		txtFirstName.setDocument(new JTextFieldLimit(20)); // Limits the characters entered
		
		txtMiddleName = new JTextField();
		txtMiddleName.setToolTipText("Example : J; If name is Harry James Potter");
		txtMiddleName.setColumns(5);
		GridBagConstraints gbc_txtMiddleName = new GridBagConstraints();
		gbc_txtMiddleName.fill = GridBagConstraints.BOTH;
		gbc_txtMiddleName.insets = new Insets(0, 0, 5, 5);
		gbc_txtMiddleName.gridwidth = 2;
		gbc_txtMiddleName.gridx = 1;
		gbc_txtMiddleName.gridy = 5;
		contentPane.add(txtMiddleName, gbc_txtMiddleName);
		txtMiddleName.setDocument(new JTextFieldLimit(1)); // Limits the characters entered
		
		txtLastName = new JTextField();
		txtLastName.setToolTipText("Example : Potter; If name is Harry James Potter");
		txtLastName.setColumns(20);
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.fill = GridBagConstraints.BOTH;
		gbc_txtLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtLastName.gridwidth = 5;
		gbc_txtLastName.gridx = 1;
		gbc_txtLastName.gridy = 6;
		contentPane.add(txtLastName, gbc_txtLastName);
		txtLastName.setDocument(new JTextFieldLimit(20)); // Limits the characters entered
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("Example: name@domain.com");
		txtEmail.setColumns(100);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.fill = GridBagConstraints.BOTH;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.gridwidth = 6;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 10;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setDocument(new JTextFieldLimit(100)); // Limits the characters entered
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.setToolTipText("Example: +12343323");
		txtPhoneNo.setColumns(21);
		GridBagConstraints gbc_txtPhoneNo = new GridBagConstraints();
		gbc_txtPhoneNo.fill = GridBagConstraints.BOTH;
		gbc_txtPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhoneNo.gridwidth = 5;
		gbc_txtPhoneNo.gridx = 1;
		gbc_txtPhoneNo.gridy = 11;
		contentPane.add(txtPhoneNo, gbc_txtPhoneNo);
		txtPhoneNo.setDocument(new JTextFieldLimit(21)); // Limits the characters entered
		
		txtAddress1 = new JTextField();
		txtAddress1.setToolTipText("Example: 1234 ABCD Boulevard");
		txtAddress1.setColumns(35);
		GridBagConstraints gbc_txtAddress1 = new GridBagConstraints();
		gbc_txtAddress1.fill = GridBagConstraints.BOTH;
		gbc_txtAddress1.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddress1.gridwidth = 6;
		gbc_txtAddress1.gridx = 1;
		gbc_txtAddress1.gridy = 12;
		contentPane.add(txtAddress1, gbc_txtAddress1);
		txtAddress1.setDocument(new JTextFieldLimit(35)); // Limits the characters entered
		
		txtAddress2 = new JTextField();
		txtAddress2.setToolTipText("Example: Near ABCD Landmark");
		txtAddress2.setColumns(35);
		GridBagConstraints gbc_txtAddress2 = new GridBagConstraints();
		gbc_txtAddress2.fill = GridBagConstraints.BOTH;
		gbc_txtAddress2.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddress2.gridwidth = 6;
		gbc_txtAddress2.gridx = 1;
		gbc_txtAddress2.gridy = 13;
		contentPane.add(txtAddress2, gbc_txtAddress2);
		txtAddress2.setDocument(new JTextFieldLimit(35)); // Limits the characters entered
		
		txtCity = new JTextField();
		txtCity.setToolTipText("Example : Dallas");
		txtCity.setColumns(25);
		GridBagConstraints gbc_txtCity = new GridBagConstraints();
		gbc_txtCity.fill = GridBagConstraints.BOTH;
		gbc_txtCity.insets = new Insets(0, 0, 5, 5);
		gbc_txtCity.gridwidth = 5;
		gbc_txtCity.gridx = 1;
		gbc_txtCity.gridy = 14;
		contentPane.add(txtCity, gbc_txtCity);
		txtCity.setDocument(new JTextFieldLimit(25)); // Limits the characters entered
		
		txtState = new JTextField();
		txtState.setToolTipText("Examlpe : TX");
		txtState.setColumns(5);
		GridBagConstraints gbc_txtState = new GridBagConstraints();
		gbc_txtState.fill = GridBagConstraints.BOTH;
		gbc_txtState.insets = new Insets(0, 0, 5, 5);
		gbc_txtState.gridwidth = 2;
		gbc_txtState.gridx = 1;
		gbc_txtState.gridy = 15;
		contentPane.add(txtState, gbc_txtState);
		txtState.setDocument(new JTextFieldLimit(2)); // Limits the characters entered
		
		txtZipCode = new JTextField();
		txtZipCode.setToolTipText("Example: 12345 or LN-1234");
		txtZipCode.setColumns(9);
		GridBagConstraints gbc_txtZipCode = new GridBagConstraints();
		gbc_txtZipCode.fill = GridBagConstraints.BOTH;
		gbc_txtZipCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtZipCode.gridwidth = 5;
		gbc_txtZipCode.gridx = 1;
		gbc_txtZipCode.gridy = 16;
		contentPane.add(txtZipCode, gbc_txtZipCode);
		txtZipCode.setDocument(new JTextFieldLimit(9)); // Limits the characters entered
		
		txtCountry = new JTextField();
		txtCountry.setToolTipText("Example : Dallas");
		txtCountry.setColumns(30);
		GridBagConstraints gbc_txtCountry = new GridBagConstraints();
		gbc_txtCountry.weighty = 1.0;
		gbc_txtCountry.fill = GridBagConstraints.BOTH;
		gbc_txtCountry.insets = new Insets(0, 0, 5, 5);
		gbc_txtCountry.gridwidth = 5;
		gbc_txtCountry.gridx = 1;
		gbc_txtCountry.gridy = 17;
		contentPane.add(txtCountry, gbc_txtCountry);
		txtCountry.setDocument(new JTextFieldLimit(30)); // Limits the characters entered
		
		// List of textfields
		txtFields[0]=txtFirstName;
		txtFields[1]=txtMiddleName;
		txtFields[2]=txtLastName;
		txtFields[3]=txtEmail;
		txtFields[4]=txtPhoneNo;
		txtFields[5]=txtAddress1;
		txtFields[6]=txtAddress2;
		txtFields[7]=txtCity;
		txtFields[8]=txtState;
		txtFields[9]=txtZipCode;
		txtFields[10]=txtCountry;
	}
	
	/*
	 * Function to create the listeners for the text fields
	 */
	private void txtFieldListener()
	{
		txtFirstName.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrFName.setText("->Text Limit is 20 Characters");
					txtFirstName.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent arg0) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkFName())
					{
						if(!booFirstName)
						{
							lblErrFName.setText("");
							booFirstName = true;
							progressBar.setValue(++counterProgress);
							if(icon==null)
								lblErrFName.setText("->Validated");
							else
								lblErrFName.setIcon(icon);
							txtFirstName.setBorder(successBorder);
						}
					}
					else
					{
						if(booFirstName)
							progressBar.setValue(--counterProgress);
						lblErrFName.setIcon(null);
						booFirstName=false;
					}
				}
			});
			
			txtMiddleName.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrMName.setText("->Text Limit is 1 Character");
					txtMiddleName.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkMName() && !checkIfEmpty(txtMiddleName))
					{
						if(!booMiddleName)
						{
							lblErrMName.setText("");
							booMiddleName = true;
							if(icon==null)
								lblErrMName.setText("->Validated");
							else
								lblErrMName.setIcon(icon);
							txtMiddleName.setBorder(successBorder);
						}
					}
					else
					{
						lblErrMName.setIcon(null);
						booMiddleName=false;
					}
				}
			});
			
			txtLastName.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrLName.setText("->Text Limit is 20 Characters");
					txtLastName.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkLName())
					{
						if(!booLastName)
						{
							lblErrLName.setText("");
							booLastName = true;
							progressBar.setValue(++counterProgress);
							if(icon==null)
								lblErrLName.setText("->Validated");
							else
								lblErrLName.setIcon(icon);
							txtLastName.setBorder(successBorder);
						}
					}
					else
					{
						if(booLastName)
							progressBar.setValue(--counterProgress);
						lblErrLName.setIcon(null);
						booLastName=false;
					}
				}
			});
			
			txtEmail.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrEmail.setText("->Text Limit is 100 Characters");
					txtEmail.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkEmail())
					{
						if(!booEmail)
						{
							lblErrEmail.setText("");
							booEmail = true;
							progressBar.setValue(++counterProgress);
							if(icon==null)
								lblErrEmail.setText("->Validated");
							else
								lblErrEmail.setIcon(icon);
							txtEmail.setBorder(successBorder);
						}
					}
					else
					{
						if(booEmail)
							progressBar.setValue(--counterProgress);
						lblErrEmail.setIcon(null);
						booEmail=false;
					}
				}
			});
			
			txtPhoneNo.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrPhNo.setText("->Text Limit is 21 Characters");
					txtPhoneNo.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkPhoneNo())
					{
						if(!booPhoneNo)
						{
							lblErrPhNo.setText("");
							booPhoneNo = true;
							progressBar.setValue(++counterProgress);
							if(icon==null)
								lblErrPhNo.setText("->Validated");
							else
								lblErrPhNo.setIcon(icon);
							txtPhoneNo.setBorder(successBorder);
						}
					}
					else
					{
						if(booPhoneNo)
							progressBar.setValue(--counterProgress);
						lblErrPhNo.setIcon(null);
						booPhoneNo=false;
					}
				}
			});
			
			txtAddress1.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							// Clears all the Labels displaying errors for text fields
							clearErrorLbls();
							// Defaults the status bar from any error messages
							statusBarDefault();
							lblErrAdd1.setText("->Text Limit 35 Characters");
							txtAddress1.setBorder(selectedBorder);
						}
						@Override
						public void focusLost(FocusEvent e) {
							txtAddress1.setBorder(defaultBorder);
							// if validation is proper and if its is not validated before set progress bar
							if(checkAddress1())
							{
								if(!booAddress1)
								{
									lblErrAdd1.setText("");
									booAddress1 = true;
									progressBar.setValue(++counterProgress);
									if(icon==null)
										lblErrAdd1.setText("->Validated");
									else
										lblErrAdd1.setIcon(icon);
									txtAddress1.setBorder(successBorder);
								}
							}
							else
							{
								if(booAddress1)
									progressBar.setValue(--counterProgress);
								lblErrAdd1.setIcon(null);
								booAddress1=false;
							}
						}
					});
			
			txtAddress2.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrAdd2.setText("->Text Limit 35 Characters");
					txtAddress2.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkAddress2() && !checkIfEmpty(txtAddress2))
					{
						if(!booAddress2)
						{
							lblErrAdd2.setText("");
							booAddress2 = true;
							if(icon==null)
								lblErrAdd2.setText("->Validated");
							else
								lblErrAdd2.setIcon(icon);
							txtAddress2.setBorder(successBorder);
						}
					}
					else
					{
						lblErrAdd2.setIcon(null);
						booAddress2=false;
					}
					
				}
			});
			
			txtCity.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrCity.setText("->Text Limit is 25 Characters");
					txtCity.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkCity())
					{
						if(!booCity)
						{
							lblErrCity.setText("");
							booCity= true;
							progressBar.setValue(++counterProgress);
							if(icon==null)
								lblErrCity.setText("->Validated");
							else
								lblErrCity.setIcon(icon);
							txtCity.setBorder(successBorder);
						}
					}
					else
					{
						if(booCity)
							progressBar.setValue(--counterProgress);
						lblErrCity.setIcon(null);
						booCity=false;
					}
				}
			});
			
			txtState.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrState.setText("->Text Limit is 2 Characters");
					txtState.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkState())
					{
						if(!booState)
						{
							lblErrState.setText("");
							booState = true;
							progressBar.setValue(++counterProgress);
							if(icon==null)
								lblErrState.setText("->Validated");
							else
								lblErrState.setIcon(icon);
							txtState.setBorder(successBorder);
						}
					}
					else
					{
						if(booState)
							progressBar.setValue(--counterProgress);
						lblErrState.setIcon(null);
						booState=false;
					}
				}
			});

			txtZipCode.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrZip.setText("->Text Limit is 9 Characters");
					txtZipCode.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkZipCode())
					{
						if(!booZipCode)
						{
							lblErrZip.setText("");
							booZipCode = true;
							progressBar.setValue(++counterProgress);
							if(icon==null)
								lblErrZip.setText("->Validated");
							else
								lblErrZip.setIcon(icon);
							txtZipCode.setBorder(successBorder);
						}
					}
					else
					{
						if(booZipCode)
							progressBar.setValue(--counterProgress);
						lblErrZip.setIcon(null);
						booZipCode=false;
					}
				}
			});
			
			txtCountry.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					lblErrCountry.setText("->Text Limit is 30 Characters");
					txtCountry.setBorder(selectedBorder);
				}
				@Override
				public void focusLost(FocusEvent e) {
					// if validation is proper and if its is not validated before set progress bar
					if(checkCountry())
					{
						if(!booCountry)
						{
							lblErrCountry.setText("");
							booCountry = true;
							progressBar.setValue(++counterProgress);
							if(icon==null)
								lblErrCountry.setText("->Validated");
							else
								lblErrCountry.setIcon(icon);
							txtCountry.setBorder(successBorder);
						}
					}
					else
					{
						if(booCountry)
							progressBar.setValue(--counterProgress);
						lblErrCountry.setIcon(null);
						booCountry=false;
					}
				}
			});
	}
	
	/*
	 * Function to create all the labels for all Clickable Button on the Screen
	 * Naming Convention - lblButtonName
	 */
	private void createBtnLabels()
	{
		lblUpdate = new JLabel();
		lblUpdate.setForeground(Color.BLACK);
		lblUpdate.setFont(fontButton);
		GridBagConstraints gbc_lblUpdate = new GridBagConstraints();
		gbc_lblUpdate.fill = GridBagConstraints.BOTH;
		gbc_lblUpdate.insets = new Insets(0, 10, 5, 5);
		gbc_lblUpdate.gridwidth = 4;
		gbc_lblUpdate.gridx = 0;
		gbc_lblUpdate.gridy = 18;
		contentPane.add(lblUpdate, gbc_lblUpdate);	
		
		lblSave = new JLabel();
		lblSave.setForeground(Color.BLACK);
		lblSave.setFont(fontButton);
		GridBagConstraints gbc_lblSave = new GridBagConstraints();
		gbc_lblSave.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSave.insets = new Insets(0, 10, 5, 5);
		gbc_lblSave.gridwidth = 3;
		gbc_lblSave.gridx = 0;
		gbc_lblSave.gridy = 18;
		contentPane.add(lblSave, gbc_lblSave);
		
		lblClear = new JLabel();
		lblClear.setForeground(Color.BLACK);
		lblClear.setFont(fontButton);
		GridBagConstraints gbc_lblClear = new GridBagConstraints();
		gbc_lblClear.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblClear.insets = new Insets(0, 0, 5, 5);
		gbc_lblClear.gridwidth = 3;
		gbc_lblClear.gridx = 3;
		gbc_lblClear.gridy = 18;
		contentPane.add(lblClear, gbc_lblClear);
		
		lblDelete = new JLabel();
		lblDelete.setForeground(Color.BLACK);
		lblDelete.setFont(fontButton);
		GridBagConstraints gbc_lblDelete = new GridBagConstraints();
		gbc_lblDelete.fill = GridBagConstraints.BOTH;
		gbc_lblDelete.insets = new Insets(0, 0, 5, 5);
		gbc_lblDelete.gridwidth = 3;
		gbc_lblDelete.gridx = 7;
		gbc_lblDelete.gridy = 18;
		contentPane.add(lblDelete, gbc_lblDelete);	
	}
	
	/*
	 * Function to create all the Radio Buttons & to add them to the Jpanel
	 * Naming Convention - rdbtnRadioButtonName
	 */
	private void createRadioBtn()
	{
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clears all the Labels displaying errors for text fields
				 clearErrorLbls();
				// Defaults the status bar from any error messages
				statusBarDefault();
				if(rdbtnMale.isSelected() || rdbtnFemale.isSelected())
				{
					if(!booGender)
					{
						lblErrGender.setText("");
						booGender = true;
						progressBar.setValue(++counterProgress);
						if(icon==null)
							lblErrGender.setText("->Validated");
						else
							lblErrGender.setIcon(icon);
					}
				}
				else
				{
					if(booGender)
						progressBar.setValue(--counterProgress);
					lblErrGender.setIcon(null);
					booGender=false;
				}
			}
		});
		rdbtnMale.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtnMale.setToolTipText("Please Select your Gender");
		GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
		gbc_rdbtnMale.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdbtnMale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMale.gridwidth = 3;
		gbc_rdbtnMale.gridx = 1;
		gbc_rdbtnMale.gridy = 7;
		contentPane.add(rdbtnMale, gbc_rdbtnMale);
		
		//Group the radio buttons.
	    groupGender = new ButtonGroup();
		groupGender.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clears all the Labels displaying errors for text fields
				 clearErrorLbls();
				// Defaults the status bar from any error messages
				statusBarDefault();
				if(rdbtnMale.isSelected() || rdbtnFemale.isSelected())
				{
					if(!booGender)
					{
						lblErrGender.setText("");
						booGender = true;
						progressBar.setValue(++counterProgress);
						if(icon==null)
							lblErrGender.setText("->Validated");
						else
							lblErrGender.setIcon(icon);
					}
				}
				else
				{
					if(booGender)
						progressBar.setValue(--counterProgress);
					lblErrGender.setIcon(null);
					booGender=false;
				}
			}
		});
		rdbtnFemale.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtnFemale.setToolTipText("Please Select your Gender");
		GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
		gbc_rdbtnFemale.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdbtnFemale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFemale.gridwidth = 2;
		gbc_rdbtnFemale.gridx = 4;
		gbc_rdbtnFemale.gridy = 7;
		contentPane.add(rdbtnFemale, gbc_rdbtnFemale);
		groupGender.add(rdbtnFemale);
	}
	
	/*
	 * Function to create the listeners for the Radio Buttons
	 */
	private void rdbtnListeners()
	{
		rdbtnMale.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// Clears all the Labels displaying errors for text fields
				clearErrorLbls();
				// Defaults the status bar from any error messages
				statusBarDefault();
				// if Gender not selected yet, then display a message
				if(!(rdbtnFemale.isSelected()) && !(rdbtnMale.isSelected()))
				{
					lblErrGender.setText("Please select your gender.");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				lblErrGender.setText("");
			}
		});
		rdbtnMale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// if Gender not selected yet, then display a message
				if(!(rdbtnFemale.isSelected()) && !(rdbtnMale.isSelected()))
				{
					lblErrGender.setText("Please select your gender.");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblErrGender.setText("");
			}
		});
		
		rdbtnFemale.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// Clears all the Labels displaying errors for text fields
				clearErrorLbls();
				// Defaults the status bar from any error messages
				statusBarDefault();
				// if Gender not selected yet, then display a message
				if(!(rdbtnFemale.isSelected()) && !(rdbtnMale.isSelected()))
				{
					lblErrGender.setText("Please select your gender.");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				lblErrGender.setText("");
			}
		});
		rdbtnFemale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// if Gender not selected yet, then display a message
				if(!(rdbtnFemale.isSelected()) && !(rdbtnMale.isSelected()))
				{
					lblErrGender.setText("Please select your gender.");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblErrGender.setText("");
			}
		});
	}		
	
	/*
	 * Function to create all the Buttons & to add them to the Jpanel
	 * Naming Convention - btnName
	 */
	private void createBtns()
	{
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(fontButton);
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.ipady = 5;
		gbc_btnUpdate.ipadx = 5;
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.insets = new Insets(0, 10, 5, 5);
		gbc_btnUpdate.gridwidth = 3;
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 19;
		contentPane.add(btnUpdate, gbc_btnUpdate);
		btnUpdate.setVisible(false); // initially the button is not visible
		
		btnClear = new JButton("Clear");
		btnClear.setFont(fontButton);
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.ipadx = 5;
		gbc_btnClear.fill = GridBagConstraints.BOTH;
		gbc_btnClear.insets = new Insets(0, 0, 5, 5);
		gbc_btnClear.gridwidth = 4;
		gbc_btnClear.gridx = 3;
		gbc_btnClear.gridy = 19;
		contentPane.add(btnClear, gbc_btnClear);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(fontButton);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.ipady = 5;
		gbc_btnDelete.ipadx = 5;
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridwidth = 3;
		gbc_btnDelete.gridx = 7;
		gbc_btnDelete.gridy = 19;
		contentPane.add(btnDelete, gbc_btnDelete);
	}
	
	/*
	 * Function to create the Action listeners for the Buttons
	 */
	private void btnListeners()
	{
		// Will listen to events, if the button is clicked, then activates event
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// When save is pressed, will call the foll method to save the changes to database
				boolean saved = saveState();
				if(saved)
				{
					clearAllFields();
				}
				
			}
		});
		// Will listen to events, if ENTER or SPACE key is pressed,then activates event
		btnSave.addKeyListener(new KeyAdapter() {
			    	@Override
			    	public void keyPressed(KeyEvent keyEvent) {
			    		if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER || keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
			    		{
			    			boolean saved = saveState();
					if(saved)
					{
						clearAllFields();
					}
			    		}
			    	}
			    });
		
		// Action Listeners for buttons to show information below the buttons		
		 btnSave.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseEntered(MouseEvent arg0) {
		    		lblSave.setText("Press Button to Save");
		    	}
		    	@Override
		    	public void mouseExited(MouseEvent e) {
		    		lblSave.setText("");
		    	}
		    });
	
	
			
			// Will listen to events, if the button is clicked, then activates event
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Call the update function
					boolean updateSuccess = updateUser();
					if(updateSuccess)
					{
						// Set button visibility
						btnSave.setVisible(true);
						btnUpdate.setVisible(false);
						clearAllFields();
						clearErrorLbls();
					}
				}
			});
			// Will listen to events, if ENTER or SPACE key is pressed,then activates event
			btnUpdate.addKeyListener(new KeyAdapter() {
			    	@Override
			    	public void keyPressed(KeyEvent keyEvent) {
			    		if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER || keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
			    		{
			    			// Call the update function
						boolean updateSuccess = updateUser();
						if(updateSuccess)
						{
							// Set button visibility
							btnSave.setVisible(true);
							btnUpdate.setVisible(false);
							clearAllFields();
							clearErrorLbls();
						}
			    		}
			    	}
			    });			
			
			btnUpdate.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent arg0) {
							lblUpdate.setText("Press to Update the record");
						}
						@Override
						public void mouseExited(MouseEvent e) {
							lblUpdate.setText("");
						}
					});
	
			// Will listen to events, if the button is clicked, then activates event
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Clears all the Labels displaying errors for text fields
					clearErrorLbls();
					// Defaults the status bar from any error messages
					statusBarDefault();
					// function called to clear all the fields
					clearAllFields();
					// Set button visibility
					btnSave.setVisible(true);
					btnUpdate.setVisible(false);
					// Set Default Border
					setDefaultBorder();
				}
			});
			// Will listen to events, if ENTER or SPACE key is pressed,then activates event
			btnClear.addKeyListener(new KeyAdapter() {
					    	@Override
					    	public void keyPressed(KeyEvent keyEvent) {
					    		if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER || keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
					    		{
					    			// Clears all the Labels displaying errors for text fields
						clearErrorLbls();
						// Defaults the status bar from any error messages
						statusBarDefault();
						// function called to clear all the fields
						clearAllFields();	
						// Set button visibility
						btnSave.setVisible(true);
						btnUpdate.setVisible(false);
						// Set Default Border
						setDefaultBorder();
					    		}
					    	}
					    });
			
			btnClear.addMouseListener(new MouseAdapter() {
			   	@Override
			   	public void mouseEntered(MouseEvent arg0) {
			   		lblClear.setText("Press clear all !");
			   	}
			   	@Override
			   	public void mouseExited(MouseEvent e) {
			   		lblClear.setText("");
			   	}
			   });
			
			
			
			// Will listen to events, if the button is clicked, then activates event
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Calls the delete user method which will try to delete the selected user & will update table after that
					deleteUser();
				}
			});
			// Will listen to events, if ENTER or SPACE key is pressed,then activates event
			btnDelete.addKeyListener(new KeyAdapter() {
					    	@Override
					    	public void keyPressed(KeyEvent keyEvent) {
					    		if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER || keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
					    		{
					    			// Calls the delete user method which will try to delete the selected user & will update table after that
						deleteUser();
					    		}
					    	}
					    });
			
			btnDelete.addMouseListener(new MouseAdapter() {
			   	@Override
			   	public void mouseEntered(MouseEvent arg0) {
			   		lblDelete.setText("Press to Delete the record");
			   	}
			   	@Override
			   	public void mouseExited(MouseEvent e) {
			   		lblDelete.setText("");
			   	}
			   });	
	}
	
	/*
	 * Function to create all the error labels.. 
	 * Error label are the ones which give information on how to give input to the field and the expected valid input of a field.
	 * Naming Convention - lblErrRespectiveTextFieldName or lblStatusInformationGiven
	 */
	private void createErrlabels()
	{
			lblErrFName = new JLabel("");
			lblErrFName.setFont(fontErrLabel);
			lblErrFName.setForeground(Color.RED);
			GridBagConstraints gbc_lblErrFName = new GridBagConstraints();
			gbc_lblErrFName.fill = GridBagConstraints.BOTH;
			gbc_lblErrFName.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrFName.gridwidth = 4;
			gbc_lblErrFName.gridx = 6;
			gbc_lblErrFName.gridy = 4;
			contentPane.add(lblErrFName, gbc_lblErrFName);
			
			lblErrMName = new JLabel("");
			lblErrMName.setForeground(Color.RED);
			lblErrMName.setFont(fontErrLabel);
			GridBagConstraints gbc_lblErrMName = new GridBagConstraints();
			gbc_lblErrMName.fill = GridBagConstraints.BOTH;
			gbc_lblErrMName.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrMName.gridwidth = 4;
			gbc_lblErrMName.gridx = 6;
			gbc_lblErrMName.gridy = 5;
			contentPane.add(lblErrMName, gbc_lblErrMName);
			
			lblErrLName = new JLabel("");
			lblErrLName.setForeground(Color.RED);
			lblErrLName.setFont(fontErrLabel);
			GridBagConstraints gbc_lblErrLName = new GridBagConstraints();
			gbc_lblErrLName.fill = GridBagConstraints.BOTH;
			gbc_lblErrLName.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrLName.gridwidth = 4;
			gbc_lblErrLName.gridx = 6;
			gbc_lblErrLName.gridy = 6;
			contentPane.add(lblErrLName, gbc_lblErrLName);
			
			lblErrGender = new JLabel("");
			lblErrGender.setForeground(Color.RED);
			lblErrGender.setFont(fontErrLabel);
			GridBagConstraints gbc_lblErrGender = new GridBagConstraints();
			gbc_lblErrGender.fill = GridBagConstraints.BOTH;
			gbc_lblErrGender.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrGender.gridwidth = 4;
			gbc_lblErrGender.gridx = 6;
			gbc_lblErrGender.gridy = 7;
			contentPane.add(lblErrGender, gbc_lblErrGender);
			
			lblErrEmail = new JLabel("");
			lblErrEmail.setForeground(Color.RED);
			lblErrEmail.setFont(fontErrLabel);
			GridBagConstraints gbc_lblErrEmail = new GridBagConstraints();
			gbc_lblErrEmail.fill = GridBagConstraints.BOTH;
			gbc_lblErrEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrEmail.gridwidth = 3;
			gbc_lblErrEmail.gridx = 7;
			gbc_lblErrEmail.gridy = 10;
			contentPane.add(lblErrEmail, gbc_lblErrEmail);
			
			lblErrPhNo = new JLabel("");
			lblErrPhNo.setFont(fontErrLabel);
			lblErrPhNo.setForeground(Color.RED);
			GridBagConstraints gbc_lblErrPhNo = new GridBagConstraints();
			gbc_lblErrPhNo.fill = GridBagConstraints.BOTH;
			gbc_lblErrPhNo.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrPhNo.gridwidth = 4;
			gbc_lblErrPhNo.gridx = 6;
			gbc_lblErrPhNo.gridy = 11;
			contentPane.add(lblErrPhNo, gbc_lblErrPhNo);
			
			lblErrAdd1 = new JLabel("");
			lblErrAdd1.setFont(fontErrLabel);
			lblErrAdd1.setForeground(Color.RED);
			GridBagConstraints gbc_lblErrAdd1 = new GridBagConstraints();
			gbc_lblErrAdd1.fill = GridBagConstraints.BOTH;
			gbc_lblErrAdd1.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrAdd1.gridwidth = 3;
			gbc_lblErrAdd1.gridx = 7;
			gbc_lblErrAdd1.gridy = 12;
			contentPane.add(lblErrAdd1, gbc_lblErrAdd1);
			
			lblErrAdd2 = new JLabel("");
			lblErrAdd2.setFont(fontErrLabel);
			lblErrAdd2.setForeground(Color.RED);
			GridBagConstraints gbc_lblErrAdd2 = new GridBagConstraints();
			gbc_lblErrAdd2.fill = GridBagConstraints.BOTH;
			gbc_lblErrAdd2.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrAdd2.gridwidth = 3;
			gbc_lblErrAdd2.gridx = 7;
			gbc_lblErrAdd2.gridy = 13;
			contentPane.add(lblErrAdd2, gbc_lblErrAdd2);
			lblErrLabels[5] = lblErrAdd2;		
			
			lblErrCity = new JLabel("");
			lblErrCity.setForeground(Color.RED);
			lblErrCity.setFont(fontErrLabel);
			GridBagConstraints gbc_lblErrCity = new GridBagConstraints();
			gbc_lblErrCity.fill = GridBagConstraints.BOTH;
			gbc_lblErrCity.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrCity.gridwidth = 4;
			gbc_lblErrCity.gridx = 6;
			gbc_lblErrCity.gridy = 14;
			contentPane.add(lblErrCity, gbc_lblErrCity);
			
			lblErrState = new JLabel("");
			lblErrState.setForeground(Color.RED);
			lblErrState.setFont(fontErrLabel);
			GridBagConstraints gbc_lblErrState = new GridBagConstraints();
			gbc_lblErrState.fill = GridBagConstraints.BOTH;
			gbc_lblErrState.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrState.gridwidth = 4;
			gbc_lblErrState.gridx = 6;
			gbc_lblErrState.gridy = 15;
			contentPane.add(lblErrState, gbc_lblErrState);
			
			lblErrZip = new JLabel("");
			lblErrZip.setFont(fontErrLabel);
			lblErrZip.setForeground(Color.RED);
			GridBagConstraints gbc_lblErrZip = new GridBagConstraints();
			gbc_lblErrZip.fill = GridBagConstraints.BOTH;
			gbc_lblErrZip.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrZip.gridwidth = 4;
			gbc_lblErrZip.gridx = 6;
			gbc_lblErrZip.gridy = 16;
			contentPane.add(lblErrZip, gbc_lblErrZip);
			
			lblErrCountry = new JLabel("");
			lblErrCountry.setForeground(Color.RED);
			lblErrCountry.setFont(fontErrLabel);
			GridBagConstraints gbc_lblErrCountry = new GridBagConstraints();
			gbc_lblErrCountry.fill = GridBagConstraints.BOTH;
			gbc_lblErrCountry.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrCountry.gridwidth = 4;
			gbc_lblErrCountry.gridx = 6;
			gbc_lblErrCountry.gridy = 17;
			contentPane.add(lblErrCountry, gbc_lblErrCountry);
			
			// List of Error Labels
			lblErrLabels[0] = lblErrFName;
			lblErrLabels[1] = lblErrMName;
			lblErrLabels[2] = lblErrLName;
			lblErrLabels[3] = lblErrGender;
			lblErrLabels[4] = lblErrEmail;
			lblErrLabels[5] = lblErrPhNo;
			lblErrLabels[6] = lblErrAdd1;
			lblErrLabels[7] = lblErrAdd2;
			lblErrLabels[8] = lblErrCity;
			lblErrLabels[9] = lblErrState;
			lblErrLabels[10] = lblErrZip;
			lblErrLabels[11] = lblErrCountry;
			
	}		
	
	/*
	 * Used to create a table which lists all the contacts currently stored in our database.
	 * This method lays down the layout of the table, and calls a function to populate it.
	 * Input - None
	 * Return Method - None
	 * Function Called by this function - populateTable();
	 */
	private void createTable()
	{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.insets = new Insets(0, 10, 5, 5);
			gbc_scrollPane.gridheight = 21;
			gbc_scrollPane.gridwidth = 6;
			gbc_scrollPane.gridx = 11;
			gbc_scrollPane.gridy = 3;
			contentPane.add(scrollPane, gbc_scrollPane);
			
			// Create object of table and table model
			table = new JTable();
			table.setShowGrid(false);
			scrollPane.setViewportView(table);
			table.setAutoCreateRowSorter(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			
			// Create a Table Model for the Table 
			tableModel = new DefaultTableModel(0, 0)
			  {
			    /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				/*
				 * Used for making the fields in the Jtable non-editable by doublick or by pressing enter or a single click
				 */
				public boolean isCellEditable(int row, int column)
			    {
			      return false; //This causes all cells to be not editable
			    }
			  };

			//	Add header of the table
			String header[] = new String[] { "First Name", "Middle Name", "Last Name", "Phone No"};

			//	Add header in table model
			tableModel.setColumnIdentifiers(header);
			table.setModel(tableModel);
			table.setAutoscrolls(true);
			
			// Will populate the table with the help of another function
			populateTable();
			
			
	}
		
	/*
	 * The method is used to populate the Table List created.
	 */
	private void populateTable()
	{
		try{
			Service service = new Service();
			List <Person> listAddr = service.getListAddrMain();
			System.out.println(listAddr);
	
			if (listAddr == null || listAddr.isEmpty())
			{
				lblStatusBar2.setText("Table is empty. Please enter data to see a list below..");
			}
			// Add row dynamically into the table   
			else
			{
				for(Person p: listAddr)
				{
					tableModel.addRow(new Object[] { toCamelCase(p.varFirstName), toCamelCase(p.varMiddleName), toCamelCase(p. varLastName), p.varPhoneNo});
				}
			}	
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	

	/*
	 * Clears all the Error Labels in the form.
	 * Clears the following labels - lblErrFName, lblErrMName, lblErrLName, lblErrGender, lblErrAdd1, lblErrAdd2, lblErrCity, lblErrState, lblErrZip, lblErrPhNo
	 */
	private void clearErrorLbls() {
		for(int index=0;index<lblErrLabels.length;index++)
		{
			lblErrLabels[index].setText("");
		}
	}

	/*
	 * Function used to set the status bar to its default values
	 * Sets the following - lblStatusBar1, lblAsterisk, lblStatusBar2
	 */
	private void statusBarDefault()
	{
		lblStatusBar1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblStatusBar1.setText("Note: * Denotes Mandatory Fields");
		lblStatusBar1.setHorizontalAlignment(SwingConstants.LEADING);
		lblStatusBar2.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblStatusBar2.setText("Note: Please select a row below to be viewed/deleted");
	}
	
	/*
	 * Function to clear all the text fields & the radio buttons
	 */
	private void clearAllFields(){
		// On Press of button Clear, set all text to empty, so that the user can enter the text again
		for(int index=0;index<txtFields.length;index++)
		{
			txtFields[index].setText("");
			txtFields[index].setBorder(defaultBorder);
		}
		for(int index=0;index<lblErrLabels.length;index++)
		{
			lblErrLabels[index].setIcon(null);
		}
		for(int index=0;index<booArray.length;index++)
		{
			booArray[index]=false;
		}
		booFirstName=false;
		 booMiddleName=false;
		 booLastName=false;
		 booEmail=false;
		 booGender=false;
		 booAddress1=false;
		 booAddress2=false;
		 booCity=false;
		 booPhoneNo=false;
		 booState=false;
		 booZipCode=false;
		 booCountry=false;
		groupGender.clearSelection();
		gender=null;
		progressBar.setValue(0);
		
	}
	
	/*
	 * Checks the textfield if no data has been entered in the selected field, then returns true. If data is present returns false.
	 * Else if data is entered but only whitespace is present in the selected field, then returns true. If data is present returns false.
	 * That is return True on invalid data, and false on valid data
	 */
	private boolean checkIfEmpty(JTextField field){
		if(field.getText().equals(null) || field.getText().trim().length() <=0)
			return true;
		else 
			return false;
	}
	
	/*
	 * Checks the length of the textfield, if length is greater than the designated limits, then returns false otherwise true.
	 * That is return True on invalid data, and false on valid data
	 */
	private boolean checkLength(JTextField field, int len)
	{
		if(field.getText().trim().length() > len)
			return true;
		else
			return false;
	}
	
	/* Does various validity checks before saving the Entered information
	 * Does these check before a save and a update
	 * If invalid , displays an appropriate message and gives a red outline to the text box
	 * Returns true if all validations are true, otherwise false
	 */
	private boolean checkValidity()
	{
		boolean isValid=true; // Variable to check if any errors were found. Sets the boolean to true, then if even one error is found it's set to false
		
		if(!checkFName() && isValid) {
			isValid = false;
			 }
			if(!checkMName() && isValid) { // checktxtMiddleName for validation {
			isValid = false;
			 }

			if(!checkLName() && isValid) {  // checktxtLastName for validation {
			isValid = false;
			 }

			if(!checkGender() && isValid) {  // checkgender for validation {
			isValid = false;
			 }

			if(!checkEmail() && isValid) { // checkemail field for validation {
			isValid = false;
			 }

			if(!checkPhoneNo() && isValid) {  // checktxtAddress1 for validation {
			isValid = false;
			 }

			if(!checkAddress1() && isValid) {  // checktxtAddress1 for validation {
			isValid = false;
			 }

			if(!checkAddress2() && isValid) {  // checktxtAddress2 for validation {
			isValid = false;
			 }

			if(!checkCity() && isValid) {  // checktxtCity for validation {
			isValid = false;
			 }

			if(!checkState() && isValid) {  // checktxtState for validation {
			isValid = false;
			 }

			if(!checkZipCode() && isValid) {  // checktxtZipCode for validation {
			isValid = false;
			 }

			if(!checkCountry() && isValid) {
			isValid = false;
		}
		
		return isValid;
	}
	
	/*
	 * Searches if the Name already exists in the database.
	 * Returns - true if name already exists. Otherwise returns false.
	 * Takes the parameters - First, Middle & Last Name
	 */
	private boolean searchDuplicate(String varFirstName, String varMiddleName, String varLastName)
	{
		Service service = new Service();
		List <Person> listAddr = new ArrayList<Person>();
		// Reads the current databse and stores it in a list
		listAddr = service.getListAddrMain();
		
		for(Person person : listAddr)
		{
			if(person.varFirstName.equalsIgnoreCase(varFirstName) 
					&& person.varMiddleName.equalsIgnoreCase(varMiddleName) 
					&& person.varLastName.equalsIgnoreCase(varLastName) )
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Searches if the New Name being Updated already exists in the the database. 
	 * Returns - true if name already exists. Otherwise returns false.
	 * Takes the parameters - First, Middle & Last Name from the user and First, Middle & Last Name from the table 
	 */
	private boolean searchDuplicate(String varFirstName, String varMiddleName, String varLastName,
			String firstName, String middleName, String lastName)
	{
		if(varFirstName.equalsIgnoreCase(firstName) 
					&& varMiddleName.equalsIgnoreCase(middleName) 
					&& varLastName.equalsIgnoreCase(lastName) )
			{
				return true;
			}
		
		return false;
	}

	/*
	 * Author : Dhruv Arora
	 * Function to turn text to Camel Case. Here the first letter of each word is capitalized. Even Removes multiple spaces from the sentence.
	 * Input - Takes a String as input
	 * Copyright - Self
	 */
	private String toCamelCase(String string) {
	      int strLength;
	      if (string == null || (strLength = string.length()) == 0) {
	          return string;
	      }
	      
	      String delimited[] = null;
	      string=string.trim();
			// Split each line into it's constituent properties, then the result is stored in a string array.
			delimited = string.split("\\ ");
			//This array will store the result
			String camelCase="";
			
			for(int index=0;index<delimited.length;index++)
			{
				if(delimited[index].length()>0)
				{
					String s1 = new StringBuffer(strLength)
			          .append(delimited[index].substring(0, 1).toUpperCase())
			          .append(delimited[index].substring(1).toLowerCase())
			          .toString();
					camelCase = camelCase+" "+s1;
				}
			}
	      return camelCase.trim();
	  }
	
	/*
	 * Sets the default border for all the Text Fields elements
	 */
	private void setDefaultBorder()
	{
		for(int index=0;index<txtFields.length;index++)
		{
			txtFields[index].setBorder(defaultBorder);
		}
	}
	
	/*
	 * Method used to validate the data entered, and if valid then save the entered data as a new record to the database
	 * On successful save return true, else false
	 */
	private boolean saveState()
	{
		
		boolean isSaved=false;
		Service service = new Service();
		// Get the gender
		getGender();

		// Check if the data entered is valid
		if(checkValidity())
		{
			if( !(searchDuplicate(txtFirstName.getText().trim().toUpperCase(), txtMiddleName.getText().trim().toUpperCase(), txtLastName.getText().trim().toUpperCase())) )
			{
					System.out.println("Validated & Duplicate.. checked");
					// Call the Service method for Saving the Contents
					boolean wasSaved = service.saveContents(txtFirstName.getText().trim(), txtMiddleName.getText().trim(),
							txtLastName.getText().trim(), gender, txtEmail.getText().trim(), txtPhoneNo.getText().trim(), txtAddress1.getText().trim(), txtAddress2.getText().trim(), 
							txtCity.getText().trim(), txtState.getText().trim(), txtZipCode.getText().trim(), txtCountry.getText().trim());
					if(wasSaved)
					{	
						// Set status message to let user know the save was successful
						lblStatusBar1.setFont( new Font("Segoe UI Symbol", Font.PLAIN, 13));
						lblStatusBar1.setText(" Information Saved Successfully! :-) ");
						lblStatusBar1.setHorizontalAlignment(SwingConstants.CENTER);
						// Add the newly added row to the top
						tableModel.addRow(new Object[] 
								{toCamelCase(txtFirstName.getText()), toCamelCase(txtMiddleName.getText()), 
								toCamelCase(txtLastName.getText()), txtPhoneNo.getText()});
						
						isSaved=true;
					}
			}
			else
			{
				// Set status message to let user know that duplicate exists in the database
				lblStatusBar1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
				lblStatusBar1.setText("Current Contact Details Already Exists in the Address Manager!!");
				lblStatusBar1.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		else
		{	
			System.out.println("Please Enter Information In The Required Fileds");
			lblStatusBar1.setText("Please Enter Information In The Required Fields !! ");
//			lblStatusBar1.setHorizontalAlignment(SwingConstants.CENTER);	
		}
		return isSaved;
	}
	
	/*
	 * Method to show Details of user .
	 */
	private void showUserDetails()
	{
		
		if(table.getSelectedRow()== (-1))
		{
			lblStatusBar2.setText("Please select a row first");
		}
		else
		{
			String firstName = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
			String middleName = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
			String lastName = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
			
			Service service = new Service();
			List <Person> listAddr = new ArrayList<Person>();
			
			listAddr = service.getListAddrMain();
			
			if ( !(listAddr.isEmpty()) )
			{
				for(Person person : listAddr)
				{
						// Since only one person with same Name can Exist, it checks if the name is equal to one in data base and then retrieves the values
						if(person.varFirstName.equalsIgnoreCase(firstName) && person.varMiddleName.equalsIgnoreCase(middleName) 
								&& person.varLastName.equalsIgnoreCase(lastName) )
						{
							System.out.println("Match found.. Displaying data");
							
							txtFirstName.setText(toCamelCase(person.varFirstName));
							txtMiddleName.setText(toCamelCase(person.varMiddleName));
							txtLastName.setText(toCamelCase(person.varLastName));
							gender=person.varGender;
							if(gender.equalsIgnoreCase("M"))
								rdbtnMale.setSelected(true);
							else
								rdbtnFemale.setSelected(true);
							txtEmail.setText(toCamelCase(person.varEmail));
							txtPhoneNo.setText(person.varPhoneNo);
							txtAddress1.setText(toCamelCase(person.varAddress1));
							txtAddress2.setText(toCamelCase(person.varAdress2));
							txtCity.setText(toCamelCase(person.varCity));
							txtState.setText(toCamelCase(person.varState));
							txtZipCode.setText(person.varZipCode);
							txtCountry.setText(person.varCountry);

							btnSave.setVisible(false);
							btnUpdate.setVisible(true);
						}
						}
					}
			}
	}
	
	/*
	 * Method used to delete a selected user from the database
	 */
	private void deleteUser(){
		Service service = new Service();
		if(table.getSelectedRow()== (-1))
		{
			lblStatusBar2.setText("Please select a row first");
		}
		else
		{
			String firstName = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
			String middleName = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
			String lastName = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
			
			boolean wasDel = service.deleteRecord(firstName, middleName, lastName);
			
			if(wasDel)
			{
				tableModel.removeRow(table.getSelectedRow());
				clearAllFields();
				lblStatusBar1.setText("Record was deleted successfully!");
			}
		}
	}

	/*
	 * Gets the gender selected by the user
	 */
	private void getGender()
	{
		if(rdbtnFemale.isSelected())
			gender="F";
		else if (rdbtnMale.isSelected())
			gender="M";
		else
			gender = null;
	}
	
	/*
	 * Function used when a user wants to update his record. All validity checks are performaed first, and then if valid, the record is inserted.
	 * Returns true if the operation was successful
	 */
	private boolean updateUser()
	{
		System.out.println("Start updating...");
		Service service = new Service();
		
		getGender();
		statusBarDefault();
		
		boolean updateSuccess = false;
		
		if(checkValidity())
		{
			// Get the original names
			String firstName = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
			String middleName = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
			String lastName = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
			
			/*
			 *  Check if the name entered already exists already in file or not.
			 */
			boolean newNameExists = searchDuplicate(txtFirstName.getText().trim().toUpperCase(), txtMiddleName.getText().trim().toUpperCase(), txtLastName.getText().trim().toUpperCase());
		
			// Checks if the new name entered by the user or not during updation
			boolean existsSameName = searchDuplicate
					(txtFirstName.getText().trim().toUpperCase(), txtMiddleName.getText().trim().toUpperCase(), txtLastName.getText().trim().toUpperCase(),
					firstName.trim().toUpperCase(), middleName.trim().toUpperCase(), lastName.trim().toUpperCase());

			// User deatils will be updated only if the new entered name doesn't already exist in the database
			if( (!newNameExists && existsSameName) || (newNameExists && existsSameName) || (!newNameExists && !existsSameName))
			{
				System.out.println("Validated & Duplicate.. checked");
				// If duplicate doesn't exist, delete the old record then add the new one
				
				if(service.deleteRecord( firstName.trim().toUpperCase(), middleName.trim().toUpperCase(), lastName.trim().toUpperCase() ))
				{
					Service service2 = new Service();
					// Call the Service method for Saving the Contents
					boolean wasSaved = service2.saveContents(txtFirstName.getText().trim(), txtMiddleName.getText().trim(),
							txtLastName.getText().trim(), gender, txtEmail.getText().trim(), txtPhoneNo.getText().trim(), txtAddress1.getText().trim(), txtAddress2.getText().trim(), 
							txtCity.getText().trim(), txtState.getText().trim(), txtZipCode.getText().trim(), txtCountry.getText().trim());
					if(wasSaved)
					{	
						lblStatusBar1.setText("Record Updated Successfully !! ");
						lblStatusBar1.setHorizontalAlignment(SwingConstants.CENTER);

						// Delete original table
						int rowCount = tableModel.getRowCount();
						//Remove rows one by one from the end of the table
						for (int index = rowCount - 1; index >= 0; index--) {
							tableModel.removeRow(index);
						}
						// Call to populate with new values
						populateTable();

						updateSuccess=true;

						}
						else
							System.out.println("could not save...");
					}
					else
						System.out.println("cound not delete");
				}
				else
				{
					System.out.println("duplicate detected");
					lblStatusBar1.setText("Current Contact Details Already Exists in the Address Manager!!");
					lblStatusBar1.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
			else
			{	
				System.out.println("Please Enter Information In The Required Fileds");
				lblStatusBar1.setText("Please Enter Information In The Required Fileds !! ");
				lblStatusBar1.setHorizontalAlignment(SwingConstants.CENTER);		
			}
		return updateSuccess;
	} // end of update method
	
	public static List<GraphicsDevice> getGraphicsDevices ()
	{
	    List<GraphicsDevice> devices = new ArrayList<GraphicsDevice> ();
	    for ( GraphicsDevice gd : GraphicsEnvironment.getLocalGraphicsEnvironment ()
	            .getScreenDevices () )
	    {
	        if ( gd.getType () == GraphicsDevice.TYPE_RASTER_SCREEN )
	        {
	            if ( gd == GraphicsEnvironment.getLocalGraphicsEnvironment ()
	                    .getDefaultScreenDevice () )
	            {
	                devices.add ( 0, gd );
	            }
	            else
	            {
	                devices.add ( gd );
	            }
	        }
	    }
	    return devices;
	}

	//Check txtFirstName for validation errors
	private boolean checkFName()
	{
		if(checkIfEmpty(txtFirstName))
		{
			lblErrFName.setText("->Field cannot be empty!");
			txtFirstName.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtFirstName, 20))
		{
			lblErrFName.setText("->Text Limit is 20 Characters!");
			txtFirstName.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}

	private boolean checkMName() { //Check txtMiddleName for validation errors
		if(checkLength(txtMiddleName, 1))
		{
			lblErrMName.setText("->Text Limit is 1 Character!");
			txtMiddleName.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}

	//Check txtLastName for validation errors
	private boolean checkLName () { 
		if(checkIfEmpty(txtLastName))
		{
			lblErrLName.setText("->Field cannot be empty!");
			txtLastName.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtLastName, 20))
		{
			lblErrLName.setText("->Text Limit is 20 Characters!");
			txtLastName.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}

	private boolean checkGender () { //Check gender for validation errors
		if(gender == null)
		{
			lblErrGender.setText("Please select a Gender!");
			return false;
		}
		return true;
	}

	private boolean checkEmail() { //Check email field for validation errors
		String regexEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regexEmail);
		Matcher matcher = pattern.matcher(txtEmail.getText().trim());
		
		if(checkIfEmpty(txtEmail))
		{
			lblErrEmail.setText("->Field cannot be empty!");
			txtEmail.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(!matcher.matches()) // used for matching a email pattern
		{
			lblErrEmail.setText("->Enter as user@domain.com");
			txtEmail.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtEmail, 100))
		{
			lblErrEmail.setText("->Text Limit is 100 Characters!");
			txtEmail.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		} 
		return true;
	}

	private boolean checkPhoneNo () { //Check txtAddress1 for validation errors
		
		String regexDigits = "\\d+"; // A regex will be used to compare if a string contains only digits. 'd' is for integers and + denotes one or more occurrences possible
			
		String phoneNo = txtPhoneNo.getText().trim().replaceFirst("\\+",""); // replaces the first "+" in the phone number, then checks if it contains only numbers
			
		if(checkIfEmpty(txtPhoneNo))
		{
			lblErrPhNo.setText("->Field cannot be empty!");
			txtPhoneNo.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if( !(phoneNo.trim().matches(regexDigits)) )
		{
			lblErrPhNo.setText("->Enter Numbers with no gap");
			txtPhoneNo.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtPhoneNo, 21))
		{
			lblErrPhNo.setText("->Text Limit is 21 Characters!");
			txtPhoneNo.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}

	private boolean checkAddress1 () { //Check txtAddress1 for validation errors
		if(checkIfEmpty(txtAddress1))
		{
			lblErrAdd1.setText("->Field cannot be empty!");
			txtAddress1.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtAddress1, 35))
		{
			lblErrAdd1.setText("->Text Limit is 35 Characters!");
			txtAddress1.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}

	private boolean checkAddress2 () { //Check txtAddress2 for validation errors
		if(checkLength(txtAddress2, 35))
		{
			lblErrAdd2.setText("->Text Limit is 35 Characters!");
			txtAddress2.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}
		
	private boolean checkCity () { //Check txtCity for validation errors
		if(checkIfEmpty(txtCity))
		{
			lblErrCity.setText("->Field cannot be empty!");
			txtCity.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtCity, 25))
		{
			lblErrCity.setText("->Text Limit is 25 Characters!");
			txtCity.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}

	private boolean checkState () { //Check txtState for validation errors
		if(checkIfEmpty(txtState))
		{
			lblErrState.setText("->Field cannot be empty!");
			txtState.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtState, 2))
		{
			lblErrState.setText("->Text Limit is 2 Characters!");
			txtState.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}

	private boolean checkZipCode () { //Check txtZipCode for validation errors
		if(checkIfEmpty(txtZipCode))
		{
			lblErrZip.setText("->Field cannot be empty!");
			txtZipCode.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtZipCode, 9))
		{
			lblErrZip.setText("->Text Limit is 9 Characters!");
			txtZipCode.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}

	private boolean checkCountry () { //Check email field for validation errors
		if(checkIfEmpty(txtCountry))
		{
			lblErrCountry.setText("->Field cannot be empty!");
			txtCountry.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		else if(checkLength(txtCountry, 30))
		{
			lblErrCountry.setText("->Text Limit is 30 Characters!");
			txtCountry.setBorder(BorderFactory.createLineBorder(Color.red));
			return false;
		}
		return true;
	}
	
}
// End of class