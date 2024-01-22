package web;

public class BookMark {

	private String title;
	private String url;
	public BookMark(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	
	public String getUrl() {
		return url;
	}

	public String toString() {
        return title;
    }
}
