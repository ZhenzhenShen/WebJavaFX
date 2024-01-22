package web;


import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.stage.Stage;

public class WebMy extends Application  {

	private TextField textFieldUrl;
	private WebEngine engine = new WebEngine();
	private ProgressBar progress = new ProgressBar();
	private WebHistory history;
	private TabPane tabPane;
	public ObservableList<WebHistory.Entry> globalHistory = FXCollections.observableArrayList();
	public TableView<WebHistory.Entry> historyTableView = new TableView<>(globalHistory);
	public ComboBox<BookMark> bookMarksBox = new ComboBox<BookMark>();
	public ArrayList<BookMark> bookMarks = new ArrayList<BookMark>();
	public Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			
		primaryStage.setTitle("WebMy");
		
		// Back button
		Button back = new Button();
//		back.setStyle("-fx-background-color: lightblue");	
		ImageView backImage = new ImageView(new Image("back6.png"));
        backImage.setFitWidth(20);
        backImage.setFitHeight(17);
		back.setGraphic(backImage);
		back.setOnAction(e -> back());
 
		//forward button	
		Button forward = new Button();
		ImageView forwardImage = new ImageView(new Image("forward4.png"));
        forwardImage.setFitWidth(20);
        forwardImage.setFitHeight(17);
		forward.setGraphic(forwardImage);
		forward.setOnAction(e -> forward());
	
		//reload button		
		Button reLoad = new Button();
		ImageView reloadImage = new ImageView(new Image("refresh4.png"));
		reloadImage.setFitWidth(20);
		reloadImage.setFitHeight(17);
		reLoad.setGraphic(reloadImage);	
		reLoad.setOnAction(e -> reload());
		
		//This will be the URL text field, and you "enter" to load webpages
		textFieldUrl = new TextField();
		textFieldUrl.setPromptText("Enter URL");
		textFieldUrl.setOnAction(e -> loadURL());
		
		//Save the URL to bookmark
		Button saveToBookMark = new Button("Save");
		ImageView saveBookMarkImage = new ImageView(new Image("bookmark2.png"));
		saveBookMarkImage.setFitWidth(20);
		saveBookMarkImage.setFitHeight(17);
		saveToBookMark.setGraphic(saveBookMarkImage);
		saveToBookMark.setOnAction(e -> saveToBookMark());	
		
		//When you click "History" button, you get the history of URL.
		//If you click on a history Entry, it will load the history website.
		Button historyButton = new Button("History");
		historyButton.setOnAction(e -> getHistory());
		
		//Zoom out the page
		Button zoomOut = new Button();
		ImageView zoomOutImage = new ImageView(new Image("zoomout2.png"));
		zoomOutImage.setFitWidth(20);
		zoomOutImage.setFitHeight(17);
		zoomOut.setGraphic(zoomOutImage);
		zoomOut.setOnAction(e -> zoomOut());

		//Zoom in the page
		Button zoomIn = new Button();
		ImageView zoomInImage = new ImageView(new Image("zoomin2.png"));
		zoomInImage.setFitWidth(20);
		zoomInImage.setFitHeight(17);
		zoomIn.setGraphic(zoomInImage);
		zoomIn.setOnAction(e -> zoomIn());
		
		//Where you could open the bookmark, and if you click on the URL,it could load the URL
		bookMarksBox.setPromptText("Select a bookmark");
		bookMarksBox.setOnAction(e -> openBookMark());
		
		MenuBar menuBar = new MenuBar();
		
		//This is a menu that contains "Print" and "Check Html Source".
		Menu menu = new Menu("Tools");

		MenuItem printMenuItem = new MenuItem("Print");
		printMenuItem.setOnAction(e -> printContent());
		
		MenuItem htmlSourceItem = new MenuItem("Check Html Source");
		htmlSourceItem.setOnAction(e -> printHtmlSource());
		
		menu.getItems().add(printMenuItem);
		menu.getItems().add(htmlSourceItem);
		
		/*
		 * This is a menu that you could change the color theme.
		 * The "Settings" contains another menu "Color Theme".
		 * There are themes "Red","Yellow","Green", "Purple" that you could choose.
		 */		
		Menu settingsMenu = new Menu("Settings");	
		
		Menu colorMenu = new Menu("Color Theme");
		
		MenuItem yellowItem = new MenuItem("Yellow");
		yellowItem.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				scene.getStylesheets().clear();
				scene.getStylesheets().add("web/yellowstylesheet.css");
			}
		});

		MenuItem greenItem = new MenuItem("Green");		
		greenItem.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				scene.getStylesheets().clear();
				scene.getStylesheets().add("web/greenstylesheet.css");
			}
		});
		
		MenuItem redItem = new MenuItem("Red");
		redItem.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				scene.getStylesheets().clear();
				scene.getStylesheets().add("web/redstylesheet.css");
			}
		});
	
		MenuItem purpleItem = new MenuItem("Purple");
		purpleItem.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				scene.getStylesheets().clear();
				scene.getStylesheets().add("web/purplestylesheet.css");
			}
		});
		
		colorMenu.getItems().add(yellowItem);
		colorMenu.getItems().add(greenItem);
		colorMenu.getItems().add(redItem);
		colorMenu.getItems().add(purpleItem);
		
		settingsMenu.getItems().add(colorMenu);
				
		menuBar.getMenus().add(menu);
		menuBar.getMenus().add(settingsMenu);
				
		HBox hBox = new HBox();
		hBox.getChildren().addAll(back, reLoad,forward,textFieldUrl,saveToBookMark,bookMarksBox,historyButton,zoomOut, zoomIn,menuBar);
		
		/*
		 * This is important part. 
		 * iniTab is the first Tab when you open the browser.
		 * newTab "+" is where when you click on it, it could create a new Tab.
		 * The new tab will be before the "+" and be added to the tabpane, and the new tab will be shown as selected page.
		 * 
		 */
		tabPane = new TabPane();
		
		Tab iniTab = new TabView(this);
		tabPane.getTabs().add(iniTab);
		
		//Create a button to add new tabs
		Tab newTab = new Tab("+");
		tabPane.getTabs().add(newTab);
		
		newTab.setOnSelectionChanged(e -> {
			Tab tab = new TabView(this);
			tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
			tabPane.getSelectionModel().select(tab);
			
			TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
			
			//when firstly open a new Tab, set the textfield.
			textFieldUrl.setText(selectedTab.getEngine().getLocation());
			
			//when open new websites, update the textfield.
		    selectedTab.getEngine().locationProperty().addListener((observable, oldValue, newValue) -> {
	            textFieldUrl.setText(newValue);
	        });
		});
			
		//Update the URL when you click another link.
		//like if you search "Briscoes" in google, and click Briscoes' website, 
		//the URL textfield will be updated.
		TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
	    selectedTab.getEngine().locationProperty().addListener((observable, oldValue, newValue) -> {
            textFieldUrl.setText(newValue);
        });
	    	
		VBox root = new VBox();
		root.getChildren().addAll(hBox,tabPane);
		
		VBox.setVgrow(tabPane, Priority.ALWAYS);
		HBox.setHgrow(textFieldUrl, Priority.ALWAYS);
		
		scene = new Scene(root);		
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
			
	}
	
   private void printHtmlSource() {
	   TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
	   String htmlSource = (String) selectedTab.getEngine().executeScript("document.documentElement.outerHTML");
       
	   Tab htmlShowTab = new TabView(this);
       Label label = new Label(htmlSource);
       htmlShowTab.setContent(label);
       htmlShowTab.setText("html source");
       
       int indexOfSelectedTab = tabPane.getTabs().indexOf(selectedTab);
       tabPane.getTabs().add(indexOfSelectedTab+1, htmlShowTab);
       tabPane.getSelectionModel().select(htmlShowTab);
	}

   private void printContent() {
	   
	   PrinterJob job = PrinterJob.createPrinterJob();
       if (job != null) {
           // Get content of web   
    	   TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
   		 if(selectedTab != null) {
   			 engine = selectedTab.getEngine();
   		 }		
     	   
         String pageTitle = (String) engine.executeScript("document.title");
              
         job.getJobSettings().setJobName(pageTitle);
           
           // Perform printing job
            if (job.showPrintDialog(selectedTab.getWebView().getScene().getWindow())) {
               boolean success = job.printPage(selectedTab.getWebView());
               if (success) {
                   job.endJob();
               }
            }
       }
   }
	

   public void openBookMark() {
     
           BookMark selectedBookmark = bookMarksBox.getValue();
           if (selectedBookmark != null) {
               
               String selectedUrl = selectedBookmark.getUrl();
               Tab tHtr = new TabView(this);
               tabPane.getTabs().add(tabPane.getTabs().size()-1,tHtr);
               tabPane.getSelectionModel().select(tHtr);                   
               
               TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
               
   			if(selectedTab != null) {
   				engine =selectedTab.getEngine();
   				engine.load(selectedUrl);
   				textFieldUrl.setText(engine.getLocation());
   			}
   			
           }
   
   }
	
	
	
	private void saveToBookMark() {
		// TODO Auto-generated method stub
		TabView selectedTab;
		String title = null;
		String urlToSave = null;
		//The first if statement: When you are on the History page, you can't save it to bookmark
		if(tabPane.getSelectionModel().getSelectedItem() instanceof TabView) {
			
		   selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
		   
		   if(selectedTab != null) {
			    title = selectedTab.getEngine().getTitle();
			    urlToSave = selectedTab.getEngine().getLocation();
					for(BookMark b : bookMarks) {
						if (urlToSave.equals(b.getUrl())) 
						 return;
					    }	
					}
			    BookMark bookMark = new BookMark(title, urlToSave);
			    bookMarks.add(bookMark);
			    bookMarksBox.getItems().add(bookMark);
		   }
		}


	@SuppressWarnings("deprecation")
	private void getHistory() {
		
		TableView<WebHistory.Entry> table = new TableView<WebHistory.Entry>();
		
		table.getItems().addAll(globalHistory);

		table.getColumns().add(TableViewHelper.getTitleColumn());
		table.getColumns().add(TableViewHelper.getURLColumn());
		table.getColumns().add(TableViewHelper.getDateColumn());
		  // Set the column resize policy to constrained resize policy
        table.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
            
        Tab t = new Tab();
        tabPane.getTabs().add(tabPane.getTabs().size()-1,t);
        t.setText("History");
        t.setContent(table);
        tabPane.getSelectionModel().select(t);
        
        table.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // Handle double-click (load URL)
                WebHistory.Entry selectedEntry = table.getSelectionModel().getSelectedItem();
                if (selectedEntry != null) {
                    String selectedUrl = selectedEntry.getUrl();
                    Tab tHtr = new TabView(this);
                    tabPane.getTabs().add(tabPane.getTabs().size()-1,tHtr);
                    tabPane.getSelectionModel().select(tHtr);                   
                    
                    TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
        			if(selectedTab != null) {
        				engine =selectedTab.getEngine();
        				engine.load(selectedUrl);
        				textFieldUrl.setText(engine.getLocation());
        			}
               
                }
            }
        });
	}

	private void loadURL() {
		
		String url = textFieldUrl.getText();
		
		if (!url.isEmpty()) {
			if (!url.startsWith("http://")&&!url.startsWith("https://")) {
				url = "http://" + url;
			}						
			TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
			if(selectedTab != null) {
				engine =selectedTab.getEngine();
				engine.load(url);
				textFieldUrl.setText(engine.getLocation());
			}
			
		}
		//Webview magages the visual representation of a webpage
		progress.progressProperty().bind(engine.getLoadWorker().progressProperty());		
		
	}
	public void forward() {
				
		TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
		if(selectedTab != null) {
			history = selectedTab.getEngine().getHistory();
			try {
				history.go(1);
			} catch (Exception e) {
				// TODO: handle exception
				return;
			}
			ObservableList<WebHistory.Entry> entries = history.getEntries();
			textFieldUrl.setText(entries.get(history.getCurrentIndex()).getUrl());
		}		
	}
	
	
	public void back() {
		
		TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
		if(selectedTab != null) {
			history = selectedTab.getEngine().getHistory();
			try {
				history.go(-1);
			} catch (Exception e) {
				// TODO: handle exception
				return;
			}
			ObservableList<WebHistory.Entry> entries = history.getEntries();
			textFieldUrl.setText(entries.get(history.getCurrentIndex()).getUrl());
		}		
		
	}
	
	public void reload() {
		
		TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
		if(selectedTab != null && history != null) {
		
			try {
				selectedTab.getEngine().reload();
			} catch (Exception e) {
				// TODO: handle exception
				return;
			}
//			ObservableList<WebHistory.Entry> entries = history.getEntries();			
			textFieldUrl.setText(selectedTab.getEngine().getLocation());
//			textFieldUrl.setText(entries.get(history.getCurrentIndex()).getUrl());
			selectedTab.setText(selectedTab.getEngine().getTitle());
			
		}		
	}
	
	private void zoomIn() {
	    TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());		
	    selectedTab.getWebView().setZoom(selectedTab.getWebView().getZoom() + 0.10);

	}
	
    private void zoomOut() {
    	TabView selectedTab =(TabView)(tabPane.getSelectionModel().getSelectedItem());
    	selectedTab.getWebView().setZoom(selectedTab.getWebView().getZoom() - 0.10);
	
	}
    	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Application.launch(args);
	}
	

}
