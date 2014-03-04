package com.smartmotion.client;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
 
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SmartMotionDB implements EntryPoint {
 
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable notesFlexTable = new FlexTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
//	private TextArea newSymbolTextBox = new TextArea();
	  
	private TextBox accountTextBox = new TextBox();
	private TextBox passTextBox = new TextBox();
	private TextBox gestureTextBox = new TextBox();
	
	private Button addAccountButton = new Button("Add");
	private Button removeAccountButton = new Button("Remove");
	private Button closeButton = new Button("Close");
 
//	private Button addNoteButton = new Button("Add");
	private Label lastUpdatedLabel = new Label();
	private ArrayList<String> NotesNames = new ArrayList<String>();
 
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Please sign in to your Google Account to access the NoteWatcher application.");
	
	private DialogBox dialogBox = new DialogBox();
	
	
	private static final Logger LOG = Logger.getLogger(SmartMotionDB.class.getName());
 
	// (1) Create the client proxy. Note that although you are creating the
	// service interface properly, you cast the result to the asynchronous
	// version of the interface. The cast is always safe because the
	// generated proxy implements the asynchronous interface automatically.
	private final GestureServiceAsync gestureService = GWT.create(GestureService.class);
 
	public void onModuleLoad() {
		
		accountTextBox.setText("User Account");
		passTextBox.setText("Password");
		gestureTextBox.setText("Gesture1;Gesture2");
		
		accountTextBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				accountTextBox.selectAll();
			}
		});
		
		passTextBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				passTextBox.selectAll();
			}
		});
		
		gestureTextBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				gestureTextBox.selectAll();
			}
		});
 
		// Create table for stock data.  
		notesFlexTable.setText(0, 2, "User*");  
		notesFlexTable.setText(0, 3, "Password");  
		notesFlexTable.setText(0, 4, "Gesture");  
		notesFlexTable.setText(0, 0, "");
		notesFlexTable.setText(0, 1, "");
//		notesFlexTable.setText(0, 4, "Edit");
				
		// Assemble Add Account panel.
	    addPanel.add(accountTextBox);
	    addPanel.add(passTextBox);
	    addPanel.add(gestureTextBox);
	    addPanel.add(addAccountButton);
//	    addPanel.add(removeAccountButton);
	    
	    dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
//		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogPanel");
//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//		dialogVPanel.add(improperInput);
////		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
	    
		// Create table for Note data.
//		notesFlexTable.setText(0, 0, "Note");
 
		// set button's style
//		addNoteButton.addStyleName("addButton");
 
		// Assemble Add Note panel.
//		addPanel.add(newSymbolTextBox);
//		addPanel.add(addNoteButton);
 
		// Assemble Main panel.
		mainPanel.add(notesFlexTable);
		mainPanel.add(addPanel);
		mainPanel.add(lastUpdatedLabel);
 
		// Associate the Main panel with the HTML host page.
		RootPanel.get().add(mainPanel);
 
		// Move cursor focus to the input box.
//		newSymbolTextBox.setWidth("300px");
//		newSymbolTextBox.setFocus(true);
 
		// Listen for mouse events on the Add button.
		addAccountButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addNote();
 
			}
		});
 
		// Listen for keyboard events in the input box.
		accountTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					addNote();
				}
			}
		});
		
		passTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					addNote();
				}
			}
		});
		
		gestureTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					addNote();
				}
			}
		});
 
		//load notes initially.
		loadNotes();
	}
 
	private void addNote() {
		final String account = accountTextBox.getText().trim();
		final String password = passTextBox.getText().trim();
		final String gesture = gestureTextBox.getText().trim();
		accountTextBox.setFocus(true);
		accountTextBox.setText("");
 
		// Don't add the Note if it's already in the table.
		if (NotesNames.contains(account)) {
			// Tell user of error
			dialogBox.setText("Account already exists, you fool!");
			dialogBox.center();
			closeButton.setFocus(true);
			return;
		}
 
		addNote(account, password, gesture);
	}
 
	private void addNote(final String account, final String password, final String gesture) {
		// (2) Create an asynchronous callback to handle the result.
		AsyncCallback callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
				// do something, when fail
				Window.alert("Server failed, you can probably blame Bruce.");
			}
 
			public void onSuccess(Void ignore) {
				// when successful, do something, about UI
				displayNote(account, password, gesture);
				accountTextBox.setText("User Account");
				passTextBox.setText("Password");
				gestureTextBox.setText("Gesture1;Gesture2");
			}
		};
 
		// (3) Make the call. Control flow will continue immediately and later
		// 'callback' will be invoked when the RPC completes.
		String[] accountArray = new String[]{account, password, gesture};
		gestureService.addNote(accountArray, callback);
 
	}
 
	private void displayNote(final String account, final String password, final String gesture) {

		LOG.log(Level.SEVERE, "Displaying account: " + account + ", " + password + ", " + gesture);
		
		// Add the Note to the table.
		int row = notesFlexTable.getRowCount();
		NotesNames.add(account);
		notesFlexTable.setText(row, 2, account);
		notesFlexTable.setText(row, 3, password);
		notesFlexTable.setText(row, 4, gesture);
 
		Button removeNoteButton = new Button("x");
		removeNoteButton.addStyleDependentName("remove");
		removeNoteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				removeNote(account);
			}
		});
		notesFlexTable.setWidget(row, 0, removeNoteButton);
		Button UpdateButton = new Button("Update");
		UpdateButton.addStyleDependentName("Edit");
		UpdateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				undisplayNote(account);
				editNote(account, password);
			}
		});
		notesFlexTable.setWidget(row, 1, UpdateButton);
 
	}
 
	private void removeNote(final String note) {
		gestureService.removeNote(note, new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
			}
 
			public void onSuccess(Void ignore) {
				undisplayNote(note);
			}
		});
	}
	
	private void editNote(final String note, final String password) {
		final String gesture = gestureTextBox.getText().trim();
		gestureService.updateGesture(note, gesture, new AsyncCallback<Void>() {
			public void onFailure(Throwable error) {
			}
 
			public void onSuccess(Void ignore) {
				displayNote(note, password, gesture);
			}
		});
	}
 
	private void undisplayNote(String note) {
		int removedIndex = NotesNames.indexOf(note);
		NotesNames.remove(removedIndex);
		notesFlexTable.removeRow(removedIndex + 1);
 
	}
 
	private void loadNotes() {
		gestureService.getNotes(new AsyncCallback<String[][]>() {
			public void onFailure(Throwable error) {
			}

			@Override
			public void onSuccess(String[][] result) {
				displayNotes(result);
			}
		});
 
	}
 
	private void displayNotes(String[][] accounts) {
		String[] account = accounts[0];
		String[] password = accounts[1];
		String[] gesture = accounts[2];
		
		for (int i=0; i<account.length; i++) {
			displayNote(account[i], password[i], gesture[i]);
		}
	}
}