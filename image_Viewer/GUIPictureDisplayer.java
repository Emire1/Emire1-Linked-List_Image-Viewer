package image_Viewer;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import linkedlist.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * 
 * @author Emmanuel Mireku
 * @version 04/29/2019
 *
 * This is a GUI program, that allows a user to
 * view pictures like a slide show. It uses the 
 * doubly-linked list data structure that is implemented by 
 * Dr.Rao 
 *
 */

public class GUIPictureDisplayer extends Application {

	// Declare all the components

	private Scene scene;
	private BorderPane pane;
	private ComboBox<String> bottomBox;
	private Label showCountLabel;
	private Label name;
	private ImageView imageView;
	private Button exit;
	private Button next;
	private Button previous;
	private Button last;
	private Button first;
	private HBox h;
	private VBox v;
	DoublyLinkedList<String> myList;
	private int currentIndex;

	//-------------------------------------

	public void createGUIComponents() {

		// Create components

		first = new Button("<<");
		last = new Button(">>");
		next = new Button(">");
		previous = new Button("<");
		exit = new Button("Exit");

		showCountLabel = new Label("0 of 0");
		name = new Label("None");

		imageView = new ImageView();

		bottomBox = new ComboBox<String>();

		h = new HBox(first, previous, showCountLabel, next, last, exit);
		v = new VBox(imageView,name);

		pane = new BorderPane();

		pane.setTop(h);
		pane.setCenter(v);
		pane.setBottom(bottomBox);


		imageView.setPreserveRatio(true);

		h.setSpacing(10);
		h.setAlignment(Pos.CENTER);
		v.setAlignment(Pos.CENTER);
		h.setSpacing(10);

		pane.setPadding(new Insets(10));

		bottomBox.setPromptText("Choose folder to load pictures from");
		bottomBox.getItems().
		addAll( "C:\\Users\\emman\\CSC205\\src\\individual3\\directory1",
				"C:\\Users\\emman\\CSC205\\src\\individual3\\directory2",
				"C:\\Users\\emman\\CSC205\\src\\individual3\\directory3");
	}

	public void attachHandlers()
	{
		//--------------------------------------------
		bottomBox.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				// Get the String from the drop-down list and initiate slide show
				String imgFileName = 
						(String)(bottomBox.getSelectionModel().getSelectedItem());
				addPictures(imgFileName);
			}

		}
				);

		first.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * When clicked, set current index to 1,
			 * display the image at the first position
			 * and set the current element to the 
			 * first element, and display image of 
			 * the first element.
			 */
			public void handle(ActionEvent e) {
				currentIndex = 1;
				displayImage(imageView, myList.getFirst().toString());
				name.setText(myList.getFirst().toString());
				showCountLabel.setText(currentIndex + " of " + myList.length());
				myList.resetCurrentElement();
			}
		});

		next.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * When clicked, check if the currentIndex
			 * is less than the length of the list and increment
			 * but if the currentIndex is the same size as the list
			 * then set the current index to size of the list.
			 * After that display image at the currentIndex
			 */
			public void handle(ActionEvent e) {
				if(currentIndex < myList.length())
					currentIndex++;
				else if (currentIndex == myList.length())
					currentIndex = myList.length();
				next(myList, imageView);
			}
		});

		previous.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * When clicked, check if the currentIndex
			 * is the same as the length of the list and decrement
			 * but if the currentIndex is 1then set the current index 1.
			 * if not then decrement currentIndex
			 * After that display image at the currentIndex
			 */
			public void handle(ActionEvent e) {
				if(currentIndex == myList.length())
					currentIndex--;
				else if (currentIndex == 1)
					currentIndex = 1;
				else currentIndex--;
				prev(myList, imageView);
			}
		});

		last.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * When Clicked, set the currentIndex to the size
			 * of the list. Display the last element in the list
			 * set the current element to the last element.
			 */
			public void handle(ActionEvent e) {
				currentIndex = myList.length();
				displayImage(imageView, myList.getLast().toString());
				showCountLabel.setText(currentIndex + " of " + myList.length());
				name.setText(myList.getLast().toString());
				myList.resetCurrentElementToLast();

			}
		});

		exit.setOnAction(new EventHandler<ActionEvent>() {
			//Exit the program.
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});
	}

	public void displayImage(ImageView img, String imgFileName)
	{
		// Create an Image object from the image in the imgFilename
		Image image;
		try {
			image = new Image(new FileInputStream(imgFileName));

			// Display the image
			img.setImage(image);
		} catch (FileNotFoundException excep) 
		{
			excep.printStackTrace();
		}
	}


	public void addPictures(String directoryName)
	{
		try
		{
			// Start with a new empty doubly-linked-list
			//----------------------------------------------------------
			myList = new DoublyLinkedList<String>();

			// Get the File object from directory name
			//----------------------------------------------------------
			File rootDirectory = new File((java.lang.String)directoryName);
			// If it is not a directory report error
			//---------------------------------------------------------
			if (rootDirectory.isDirectory() == false)
			{
				JOptionPane.showMessageDialog(null, 
						"ERROR: You must specify a directory from where to load pictures");
				System.out.println(
						"ERROR: You must specify a directory from where to load pictures");
			}
			// Otherwise look at the contents one by one
			//----------------------------------------------------------
			else
			{
				try{
					// Make an array of files in this directory
					// JPGFilter object is used select only files that
					// have a .jpg, .jpeg, .pnj, etc. extensions
					//---------------------------------------------
					String[] filesInDirectory = 
							(String [])rootDirectory.list(new MyFilter());

					// Go thru files one by one and add them to dll
					//---------------------------------------------
					for (int cnt = 0; cnt < filesInDirectory.length; cnt++)
					{
						String nextFile = filesInDirectory[cnt];
						String fullFileName = (String)(rootDirectory.getCanonicalPath() 
								+ File.separator + nextFile);
						myList.insertAtEnd(fullFileName);
					}
					// if there are any pictures
					//---------------------------------------------
					if (filesInDirectory.length > 0)
					{
						myList.resetCurrentElement();
						currentIndex = 1;
						showCountLabel.setText(currentIndex+" of " + myList.length());
						displayImage(imageView, myList.getCurrentElement());
						name.setText(myList.getCurrentElement());
					}
					else
						JOptionPane.showMessageDialog(null, 
								"No Pictures in that folder." + " Choose another folder");
				} catch (IOException ioe){
					System.out.println("ERROR: IOException");
				}

			}
		}
		catch (SecurityException ex)
		{
			System.out.println("ERROR: Security Exception: " + ex.toString());
		}
	}

	/**
	 * It takes in a directory and imageViewer and displays the next image.
	 * @param list
	 * @param img
	 */
	public void next(DoublyLinkedList<String> list, ImageView img) {
		try {
			// Display the the next element at the currentIndex
			displayImage(img, list.elementAt(currentIndex-1));
			showCountLabel.setText(currentIndex + " of " + myList.length());
			name.setText(list.elementAt(currentIndex-1));

		} catch (NullPointerException e) {
			// if null then display image of the last element
			//and reset the current element to last element
			displayImage(img, list.getLast().toString());
			showCountLabel.setText(currentIndex + " of " + myList.length());
			name.setText(myList.getLast().toString());
			list.resetCurrentElementToLast();
		}
	}


	/**
	 * It takes imageViewer and directory and displays the previous image
	 * @param list
	 * @param img
	 */
	public void prev(DoublyLinkedList<String> list, ImageView img) {
		try {

			//Display the element that is next or the same as the currentIndex
			displayImage(img, list.elementAt(currentIndex-1));
			name.setText(list.elementAt(currentIndex-1));
			showCountLabel.setText(currentIndex + " of " + list.length());
		}
		//If null then display the image of the previous image and reset the current element
		// to the first element
		catch (NullPointerException e) {
			displayImage(img, list.prevElement());
			name.setText(list.prevElement());
			showCountLabel.setText(currentIndex-1 + " of " + list.length());
			list.resetCurrentElement();

		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		createGUIComponents();
		attachHandlers();

		scene = new Scene(pane);
		primaryStage.setTitle("Slide Show");
		primaryStage.setScene(scene);
		primaryStage.setWidth(1000);
		primaryStage.setHeight(910);
		primaryStage.show();
	}

	public static void main (String[] args) {
		Application.launch(args);
	}

}
