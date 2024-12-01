package myUtil;

import java.util.ArrayList;
import java.util.List;

public class MyData {

	String title;
	String names;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public MyData(String title, String names) {
		super();
		this.title = title;
		this.names = names;
	}

}
