package library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class LibraryOfKarishma {

	static HashMap<String, Integer> booksMap = new HashMap<>();
	static {
		booksMap.put("English", 20);
		booksMap.put("Marathi", 30);
		booksMap.put("Hindi", 30);
		booksMap.put("Financial Accounts", 30);
		booksMap.put("Organasational Commerce", 30);
		booksMap.put("EVS", 30);
		booksMap.put("Maths", 30);
	}
	
	static LibraryOfKarishma objLibraryOfKarishma = new LibraryOfKarishma();
	static LibraryUser objLibraryUser = new LibraryUser();

	public static void main(String args[]) throws ParseException {

		Scanner intSc = new Scanner(System.in);
		Scanner stringSc = new Scanner(System.in);
		boolean isLoggedIn = false;

		objLibraryUser = objLibraryOfKarishma.registerNewUser(intSc, stringSc);
		isLoggedIn = objLibraryOfKarishma.loginNewUser(objLibraryUser, stringSc);
		if (isLoggedIn) {
			objLibraryUser = objLibraryOfKarishma.issueBook(objLibraryUser, booksMap, intSc, stringSc);
		}
		intSc.close();
		stringSc.close();

	}

	public LibraryUser registerNewUser(Scanner intSc, Scanner stringSc) {
		System.out.println("Welcome to Karishma's library. Please Register first to issue book");

		System.out.print("Enter your Name :");
		String userName = stringSc.nextLine();
		objLibraryUser.setName(userName);

		System.out.print("Address : ");
		String userAddress = stringSc.nextLine();
		objLibraryUser.setAddress(userAddress);

		System.out.print("College Name : ");
		String userCollegeName = stringSc.nextLine();
		objLibraryUser.setCollegeName(userCollegeName);

		System.out.print("Standard : ");
		int userStandard = intSc.nextInt();
		objLibraryUser.setStandard(userStandard);

		System.out.print("Please create password : ");
		String userPassword = stringSc.nextLine();
		objLibraryUser.setPassword(userPassword);

		return objLibraryUser;
	}

	public boolean loginNewUser(LibraryUser objLibraryUser, Scanner stringSc) {
		boolean isLoggedIn = false;

		System.out.println("Login :\nPlease enter Name : ");
		String loginName = stringSc.nextLine();

		while (!loginName.equals(objLibraryUser.getName())) {
			System.out.println("you entered incorrect loginId. Please enter correct loginId ");
			loginName = stringSc.nextLine();
		}

		System.out.println("Please enter password");
		String loginpassword = stringSc.nextLine();

		while (!loginpassword.equals(objLibraryUser.getpassword())) {
			System.out.println("you enterd incorrect password.Please enter correct password");
			loginpassword = stringSc.nextLine();
		}

		System.out.println("you are successfully loged in");
		isLoggedIn = true;

		return isLoggedIn;
	}

	public LibraryUser issueBook(LibraryUser objLibraryUser, HashMap<String, Integer> booksMap, Scanner intSc,
			Scanner stringSc) throws ParseException {

		boolean continueLogin = true;
		int optionSelected = 0;
		ArrayList<String> bookList = objLibraryUser.getbooks();

		while (continueLogin) {
			System.out.println(
					"Please select an option\n1.Issue a book\n2.Return the book\n3.check issued books on your name\n4.You want to exit");
			optionSelected = objLibraryOfKarishma.inputInt(intSc, optionSelected);

			if (optionSelected == 1) {

				System.out.println("Please select book from below list : \n" + booksMap);
				System.out.println("Please Enter Book you want to issue :");
				String bookName = stringSc.nextLine();

				System.out.println("Please Enter Date : ");
				String strDate = stringSc.nextLine();

				Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
				System.out.println("Date is : " + date1);
				HashMap<String, Date> issueBooksDate = objLibraryUser.getIssueBooksDates();
				issueBooksDate.put(bookName, date1);
				objLibraryUser.setIssueBooksDates(issueBooksDate);

				if (booksMap.containsKey(bookName)) {
					int valueOfBook = booksMap.get(bookName);
					booksMap.put(bookName, --valueOfBook);
					System.out.println("Congratulations!!! The Book is succesfully issued !!!!");
					bookList.add(bookName);
					objLibraryUser.setbooks(bookList);

				}

			} else if (optionSelected == 2) {

				System.out.println("Please enter the book you want to return");
				System.out.println("There are folling books in your Name : \n"+objLibraryUser.getbooks());
				String returnBook = stringSc.nextLine();
				System.out.println("Please Enter Date : ");
				String bookReturnDate = stringSc.nextLine();
				Date booksReturnDate = new SimpleDateFormat("dd/MM/yyyy").parse(bookReturnDate);
				System.out.println(booksReturnDate);
				int availableBooks = booksMap.get(returnBook);
				booksMap.put(returnBook, ++availableBooks);
				bookList.remove(returnBook);
				objLibraryUser.setbooks(bookList);
				HashMap<String, Date> issueBooksDates = objLibraryUser.getIssueBooksDates();
				Date issueDateOfBook = issueBooksDates.get(returnBook);
				objLibraryOfKarishma.calculateFine(issueDateOfBook, booksReturnDate);
			}

			else if (optionSelected == 3) {
				System.out.println(objLibraryUser.getbooks());
			} else if (optionSelected == 4) {
				System.out.println("Thank you for using Karishma's Library");
				continueLogin = false;
			}

		}
		return objLibraryUser;
	}

	public int inputInt(Scanner sc, int number) {
		try {
			String choice = sc.next();
			number = Integer.parseInt(choice);
		} catch (NumberFormatException e) {
			System.out.println("Please enter valid option");
			number = inputInt(sc, number);
		}
		return number;
	}

	public void calculateFine(Date issueDate, Date returnDate) {

		long differenceInMiliseconds = returnDate.getTime() - issueDate.getTime();
		long differenceInDays = differenceInMiliseconds / (1000 * 60 * 60 * 24) % 365;
		System.out.println("Difference in Days is : " + differenceInDays);

		if (differenceInDays > 3 && differenceInDays <= 15) {
			System.out.println("You returned the book after 3 days.Please pay dateExceeding charges of Rs.10");
		} else if (differenceInDays > 15 && differenceInDays <= 30) {
			System.out.println("You returned the book after 15 days.Please pay dateExceeding charges of Rs.50");
		} else if (differenceInDays > 30) {
			System.out.println("You returned the book after 30 days.Please pay dateExceeds charges of Rs.100");
		} else {
			System.out.println("You returned the book on time. No extra charges");
		}
	}
}
