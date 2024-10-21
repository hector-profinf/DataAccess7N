/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.Serializable;

/**
 *
 * @author 7JDAM
 */
public class Contact implements Serializable{	//This allows the object Contact to be converted into a byte stream for storage or transmission
    String name, surname, telf;

    public Contact(String name, String surname, String telf) {
        this.name = name;
        this.surname = surname;
        this.telf = telf;
    }

    @Override
    public String toString() {										// -> see line 170 in Agenda.java
        return this.name + " " + this.surname +"; " + this.telf ;
    }

    
}
