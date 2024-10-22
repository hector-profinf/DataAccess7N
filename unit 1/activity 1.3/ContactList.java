/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 7JDAM
 */
public class ContactList implements Serializable{
    private  ArrayList<Contact> list;

    public ContactList() {
        this.list = new ArrayList<>();
    }
    
    public void add(Contact c){
        this.list.add(c);
    }

    public ArrayList<Contact> getList() {
        return list;
    }
    
    

}
