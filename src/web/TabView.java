package web;


import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class TabView extends Tab{

	private WebView webView;
	private WebMy webMy;

	
	public TabView(WebMy webMy) {
	
		this.webMy = webMy;
		this.webView = new WebView();
		
		webView.getEngine().load("https://www.google.co.nz/");
		webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if(newValue == Worker.State.SUCCEEDED) {
				this.setText(this.webView.getEngine().getTitle()); 			
			}else if(newValue == Worker.State.FAILED) {
				this.setText("Loading failed");
			}			
		});
			
		this.setContent(webView);
		
        WebHistory history = webView.getEngine().getHistory();
        history.getEntries().addListener((ListChangeListener<WebHistory.Entry>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
        
                  webMy.globalHistory.addAll(change.getAddedSubList());
                }
            }
        });
		
				
	}
	
	public WebEngine getEngine() {
		return webView.getEngine();
	}
	
	public WebView getWebView() {
		return webView;
	}
	
}
