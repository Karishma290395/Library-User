package library;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class LibraryUser {
	ArrayList<String> books = new ArrayList<String>();
	HashMap<String, Date> issueBookDates = new HashMap<>();
	HashMap<String, Date> returnBookDates = new HashMap<>();
	private String name;
	private String address;
	private String collegeName;
	private int standard;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public int getstandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public String getpassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getbooks() {
		return books;
	}

	public void setbooks(ArrayList<String> books) {
		this.books = books;
	}

	public HashMap<String, Date> getIssueBooksDates() {
		return issueBookDates;
	}

	public void setIssueBooksDates(HashMap<String, Date> bookDates) {
		this.issueBookDates = bookDates;
	}

	public HashMap<String, Date> getReturnBookDates() {
		return returnBookDates;
	}

	public void setReturnBookDates(HashMap<String, Date> returnBookDates) {
		this.returnBookDates = returnBookDates;
	}

	LibraryUser() {

	}

	LibraryUser(String name, String address, String collegeName, int standard, String password, ArrayList<String> books,
			HashMap<String, Date> bookDates) {
		this.name = name;
		this.address = address;
		this.collegeName = collegeName;
		this.standard = standard;
		this.password = password;
		this.books = books;
		this.issueBookDates = bookDates;
	}

	@Override
	public String toString() {
		return "Name = " + name + "\nAddress = " + address + "\nCollege Name = " + collegeName + "\nStandard = "
				+ standard + "\nIssued Books = " + books;
	}
}
