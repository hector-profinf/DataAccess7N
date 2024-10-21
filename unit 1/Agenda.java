/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 7JDAM
 */
public class Agenda {

	enum Mode {
		BINARY, XML
	};				//Defines the storing modes of the agenda

	static Mode mode;	//Stores the mode selected by the user (binary or XML)
	static Scanner sc = new Scanner(System.in);

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		boolean salir = false;

		ContactList contacts = new ContactList();
		String fileName;
		mode = selectMode();		//Asks the user to select the mode (binary or XML) -> line 86

		fileName = getAgendaFileName();	//Name of the file where the agenda will be stored or loaded -> returns agenda.xml o agenda.bin -> line 116

		File f = new File(fileName);

		// If the agenda file exists
		if (f.exists()) {
			contacts = readAgenda(f);  			//...reads the content of the file -> line 129
		
		}

		System.out.println();
		// Menu
		do {
			printMenu();
			String opt = sc.nextLine();
			switch (opt) {
			case "1": // 1. Show all contacts
				showContacts(contacts.getList());	// -> line 164
				break;

			case "2": // 2. Add new contact
				String name, surname, telf;
				System.out.print("Name: ");
				name = sc.nextLine();
				System.out.print("Surname: ");
				surname = sc.nextLine();
				System.out.print("Telf: ");
				telf = sc.nextLine();
				contacts.add(new Contact(name, surname, telf));
				break;

			case "3": // 3. Save and exit
				saveAgendaFile(contacts, f);
				salir = true;
				break;
			default:
				System.out.println("Incorrect option");
			}

		} while (!salir);
		System.out.println("Bye, bye!");
	}

	private static Mode selectMode() {	//This allows to choose between storing the contacts in binary or XML format
		while (true) {
			System.out.println("Select Mode:\n  1-> Binary\n  2-> XML");
			String opt = sc.nextLine();
			switch (opt) {
			case "1":
				return Mode.BINARY;
			case "2":
				return Mode.XML;
			default:
				System.out.println("Incorrect option");
			}
		}
	}

	private static void saveAgendaFile(ContactList contacts, File f) throws FileNotFoundException, IOException {
		switch (mode) {
		case XML:													// Use of the library XStream to serialize the contacts in XML format
			XStream flujox = getConfiguredXStream();
			flujox.toXML(contacts, new FileOutputStream(f));
			break;
		default:													// Serialization of contacts in binary format
			FileOutputStream fout = new FileOutputStream(f);
			ObjectOutputStream objOut = new ObjectOutputStream(fout);
			objOut.writeObject(contacts);
			fout.close();
			objOut.close();
		}
	}

	private static String getAgendaFileName() {
		String fileName;
		switch (mode) {
		case XML:
			fileName = "agenda.xml";
			break;
		default:
			fileName = "agenda.bin";
			break;
		}
		return fileName;
	}

	private static ContactList readAgenda(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		ContactList contacts;
		switch (mode) {
		case XML:
			XStream flujox = getConfiguredXStream();
			contacts = (ContactList) flujox.fromXML(f);
			break;
		default:
			// read file
			FileInputStream fin = new FileInputStream(f);
			ObjectInputStream objIn = new ObjectInputStream(fin);
			contacts = (ContactList) objIn.readObject();
			fin.close();
			objIn.close();

			break;
		}
		return contacts;
	}

	private static XStream getConfiguredXStream() {
//		XStream xstream = new XStream(new StaxDriver());
//		xstream.alias("contact", Contact.class);
//		xstream.alias("contactList", ContactList.class);
//		xstream.addImplicitCollection(ContactList.class, "list");
		XStream xstream = new XStream();
		xstream.allowTypes(new Class[] { Contact.class, ContactList.class });
		return xstream;
	}

	/**
	 * Show contacts
	 * 
	 * @param contacts
	 */
	private static void showContacts(ArrayList<Contact> contacts) {

		System.out.println("");
		System.out.println("There are " + contacts.size() + " contacts");
		int i = 1;
		for (Contact contact : contacts) {
			System.out.println(i + ".- " + contact.toString()); // -> see Contact.java
			i++;
		}
	}

	/**
	 * Print the menu
	 */
	public static void printMenu() {
		System.out.println("");
		System.out.println("1. Show all contacts");
		System.out.println("2. Add new contact");
		System.out.println("3. Save and exit");
		System.out.print("Please, choose an option: ");
	}

}
