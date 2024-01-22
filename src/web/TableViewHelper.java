package web;

import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebHistory;

public class TableViewHelper {
      
    // Returns Entry title TableColumn
    public static TableColumn<WebHistory.Entry, String> getTitleColumn() 
    {
    	
        TableColumn<WebHistory.Entry, String> titleCol = new TableColumn<>("Title");
        PropertyValueFactory<WebHistory.Entry, String> titleCellValueFactory =  new PropertyValueFactory<>("Title");
        titleCol.setCellValueFactory(titleCellValueFactory);
        return titleCol;
    }
     
    // Returns Entry URL TableColumn
    public static TableColumn<WebHistory.Entry, String> getURLColumn() 
    {
        TableColumn<WebHistory.Entry, String> urlCol = new TableColumn<>("URL"); // getUrl()
        PropertyValueFactory<WebHistory.Entry, String> urlCellValueFactory = new PropertyValueFactory<>("Url");
        urlCol.setCellValueFactory(urlCellValueFactory);
        urlCol.setMaxWidth(500);
        return urlCol;
    }
     
    // Returns Entry Date TableColumn
    public static TableColumn<WebHistory.Entry, Date> getDateColumn() 
    {
        TableColumn<WebHistory.Entry, Date> dateCol = new TableColumn<>("Date");
        PropertyValueFactory<WebHistory.Entry, Date> dateCellValueFactory = new PropertyValueFactory<>("LastVisitedDate");
        dateCol.setCellValueFactory(dateCellValueFactory);
        return dateCol;
    }
 
	

}
