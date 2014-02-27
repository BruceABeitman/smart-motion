package com.cse.smartmotioncloud.client;

import java.util.ArrayList;

import com.cse.smartmotioncloud.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	private VerticalPanel mainPanel = new VerticalPanel();  
	private FlexTable stocksFlexTable = new FlexTable();  
	private HorizontalPanel addPanel = new HorizontalPanel();  
	private TextBox newSymbolTextBox = new TextBox();  
	private Button addStockButton = new Button("Add");
	private Button removeStockButton = new Button("Remove");
	private Label lastUpdatedLabel = new Label();
	
	private ArrayList<String> Accounts = new ArrayList<String>();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// Create table for stock data.  
		stocksFlexTable.setText(0, 0, "User");  
		stocksFlexTable.setText(0, 1, "Password");  
		stocksFlexTable.setText(0, 2, "Gesture");  
		stocksFlexTable.setText(0, 3, "Remove");
		
		 // Assemble Add Stock panel.
	    addPanel.add(newSymbolTextBox);
	    addPanel.add(addStockButton);
	    addPanel.add(removeStockButton);

	    // Assemble Main panel.
	    mainPanel.add(stocksFlexTable);
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
		RootPanel.get("userNameFieldContainer").add(nameField);
		RootPanel.get("userPassFieldContainer").add(passField);
		RootPanel.get("userGDataFieldContainer").add(gdataField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {//, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendUserInfoToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
//			public void onKeyUp(KeyUpEvent event) {
//				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					sendNameToServer();
//				}
//			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendUserInfoToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String userName = nameField.getText();
				String userPass = passField.getText();
				if (!FieldVerifier.isValidName(userName)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(userName);
				serverResponseLabel.setText("");
				greetingService.greetServer(userName, userPass,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
//		nameField.addKeyUpHandler(handler);
	}
	
	private void addAccount(String account, String password) {
		if (this.Accounts.contains(account)){
			return;
		}
		
		int row = stocksFlexTable.getRowCount();
		this.Accounts.add(account);
		stocksFlexTable.setText(row, 0, account);
	}
}
