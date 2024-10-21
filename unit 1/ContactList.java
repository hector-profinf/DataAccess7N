/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 7JDAM
 */
public class ContactList implements Serializable{	//This allows the object ContactList to be converted into a byte stream for storage or transmission
    private  ArrayList<Contact> list;

    public ContactList() {
        this.list = new ArrayList<>();	// initialization of the list attribute to a new empty ArrayList<Contact>
    }									// when a ContactList object is created, this ensures that the list is ready to hold objects of Contact class
    
    public void add(Contact c){			// this allows adding a c object of class Contact to the list
        this.list.add(c);
    }

    public ArrayList<Contact> getList() {		// this returns the list of contacts (the ArrayList<Contact>)
        return list;
    }
    
    

}
