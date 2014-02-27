package com.cse.smartmotioncloud.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cse.smartmotioncloud.server.Gesture;
import com.cse.smartmotioncloud.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SmartMotionCloud implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Gesture service.
	 */
	private final GestureServiceAsync gestureService = GWT
			.create(GestureService.class);
	
	private VerticalPanel mainPanel = new VerticalPanel();  
	private FlexTable SMFlexTable = new FlexTable();  
	private HorizontalPanel addPanel = new HorizontalPanel();  
	private TextBox accountTextBox = new TextBox();
	private TextBox passTextBox = new TextBox();
	private TextBox gestureTextBox = new TextBox();
	private Button addAccountButton = new Button("Add");
	private Button removeAccountButton = new Button("Remove");
	private Label lastUpdatedLabel = new Label();
	
	private Button closeButton = new Button("Close");
	
	private DialogBox dialogBox = new DialogBox();;
	
	private ArrayList<String> GestureNames = new ArrayList<String>();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// Create table for stock data.  
		SMFlexTable.setText(0, 0, "User*");  
		SMFlexTable.setText(0, 1, "Password");  
		SMFlexTable.setText(0, 2, "Gesture");  
		SMFlexTable.setText(0, 3, "Remove");
		SMFlexTable.setText(0, 4, "Edit");
		
		 // Assemble Add Stock panel.
	    addPanel.add(accountTextBox);
	    addPanel.add(passTextBox);
	    addPanel.add(gestureTextBox);
	    addPanel.add(addAccountButton);
//	    addPanel.add(removeAccountButton);

	    // Assemble Main panel.
	    mainPanel.add(SMFlexTable);
	    mainPanel.add(addPanel);
	    mainPanel.add(lastUpdatedLabel);
	    
	    // Associate the Main panel with the HTML host page.
	    RootPanel.get("accountList").add(mainPanel);
		
		final Button sendButton = new Button("Create New");
		final TextBox nameField = new TextBox();
		final TextBox passField = new TextBox();
		final TextBox gdataField = new TextBox();
		nameField.setText("Enter user name");
		passField.setText("Enter user password");
		gdataField.setText("Enter any gesture data");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
//		RootPanel.get("userNameFieldContainer").add(nameField);
//		RootPanel.get("userPassFieldContainer").add(passField);
//		RootPanel.get("userGDataFieldContainer").add(gdataField);
//		RootPanel.get("sendButtonContainer").add(sendButton);
//		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

//		// Create the popup dialog box
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
//		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label improperInput = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogPanel");
//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(improperInput);
//		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
//				sendButton.setEnabled(true);
//				sendButton.setFocus(true);
			}
		});
		
		gestureService.getAccounts(new AsyncCallback<List<Gesture>>() {
			public void onFailure(Throwable error) {
			}
			@Override
			public void onSuccess(List<Gesture> result) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Add a handler to add account to account list
		addAccountButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addAccount();
				
			}
		});
		
		// Listen for keyboard events in the input box.
		accountTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					addAccount();
				}
			}
		});
		
		loadAccounts();
	}
	
	private void addAccount() {
		final String account = accountTextBox.getText();
		final String password = passTextBox.getText();
		final String gestures = gestureTextBox.getText();
//		final List<String> gestureList = Arrays.asList(gestures.split(";"));
		
		String AccountError = verifyAccount(account);
		if (AccountError != null){
			// Tell user of error
			dialogBox.setText(AccountError);
			dialogBox.center();
			closeButton.setFocus(true);
			return;
		}
		
		int row = SMFlexTable.getRowCount();
		GestureNames.add(account);
		
		// (2) Create an asynchronous callback to handle the result.
		AsyncCallback callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
				// do something, when fail
				Window.alert("failed");
			}
 
			public void onSuccess(Void ignore) {
				// when successful, do something, about UI
				displayGesture(account, password, gestures);
			}
		};
 
		// (3) Make the call. Control flow will continue immediately and later
		// 'callback' will be invoked when the RPC completes.
		gestureService.addAccount(account, password, gestures, callback);
		
		SMFlexTable.setText(row, 0, account);
		SMFlexTable.setText(row, 1, password);
		SMFlexTable.setText(row, 2, gestures);
		
		// Add a button to remove this account from the table.
	    Button removeAccountButton = new Button("X");
	    removeAccountButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        int removedIndex = GestureNames.indexOf(account);
	        GestureNames.remove(removedIndex);
	        SMFlexTable.removeRow(removedIndex + 1);
	      }
	    });
	    SMFlexTable.setWidget(row, 3, removeAccountButton);
	    
	    // Add a button to edit this account in the table.
	    Button editAccountButton = new Button("Edit");
	    editAccountButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        int editIndex = GestureNames.indexOf(account);
//	        Accounts.remove(removedIndex);
//	        SMFlexTable.removeRow(removedIndex + 1);
	      }
	    });
	    SMFlexTable.setWidget(row, 4, editAccountButton);
	}
	
	private String verifyAccount(String Name) {
		
		if (this.GestureNames.contains(Name)) {
			return "Account already exists, you fool!";
		}
		
		if (Name.length() == 0) {
			return "Account name required.";
		}
		
		return null;
	}
	
	private void displayGesture(final String account, String password, String gestures) {
		// Add the Note to the table.
		int row = SMFlexTable.getRowCount();
		GestureNames.add(account);
		
		SMFlexTable.setText(row, 0, account);
		SMFlexTable.setText(row, 1, password);
		SMFlexTable.setText(row, 2, gestures);
 
		Button removeNoteButton = new Button("X");
		removeNoteButton.addStyleDependentName("remove");
		removeNoteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				removeAccount(account);
			}
		});
		SMFlexTable.setWidget(row, 2, removeNoteButton);
		
	}
	
	private void removeAccount(final String account) {
		gestureService.removeAccount(account, new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
			}
 
			public void onSuccess(Void ignore) {
				undisplayAccount(account);
			}
		});
	}
	
	private void undisplayAccount(String account) {
		int removedIndex = GestureNames.indexOf(account);
		GestureNames.remove(removedIndex);
		SMFlexTable.removeRow(removedIndex + 1);
 
	}
	
	private void loadAccounts() {
		gestureService.getAccounts(new AsyncCallback<List<Gesture>>() {
			public void onFailure(Throwable error) {
			}
 
			public void onSuccess(List<Gesture> gestures) {
				displayGestures(gestures);
			}
		});
	}
	
	private void displayGestures(List<Gesture> gestures) {
		for (Gesture gesture : gestures) {
			displayGesture(gesture.getAccount(), gesture.getPassword(), gesture.getGestures());
		}
	}
}
