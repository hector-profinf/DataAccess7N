package com.xavi.dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 7JDAM
 */
public class DictionaryNoRepetitions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // TODO code application logic here
        HashMap<String, HashSet<String>> mapa = new HashMap<>();

        
        //Primero leer todo
        try {
            FileReader f = new FileReader("Imagine.txt");
            BufferedReader l = new BufferedReader(f);
            String line;
            //Mapa que contendrá para cada letra un Set de palabras que empiezan por esa letra

            //Recorrer líneas
            //while ((line = l.readLine()) != null) 
            line = l.readLine();
            while (line != null) {
                System.out.println(line);
                //Si la línea está vacía, leo nueva línea y vuelvo a iterar.
                if(line.isBlank()){
                    line = l.readLine();
                    continue;
                }
                //Divido la línea en palabras
                String[] palabras = line.split(" ");
                //para cada palabra
                for (int i = 0; i < palabras.length; i++) {
                    //Me quedo la inicial, la paso a mayúscula
                    String inicial = palabras[i].substring(0, 1).toUpperCase();
                    //Si no se ha añadido nunca esa letra, hay que crear el set
                    if (!mapa.containsKey(inicial)) {
                        mapa.put(inicial, new HashSet<String>());

                    }
                    mapa.get(inicial).add(palabras[i]);
                }
                line = l.readLine();
            }
            f.close();

        } catch (IOException ex) {
            Logger.getLogger(DictionaryNoRepetitions.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creo el directorio Dictionary si no existe
        File dir = new File("Dictionary");
        if (!dir.exists()) {
            dir.mkdir();
        }
        
        //Itero sobre caada entrada del mapa y guardo cada palabra en su archivo
        //Esta sintaxis itera sobre cada elemento de un mapa, sin índices,
        // cuando acaba una iteración el valor de entry se actualiza y pasa a 
        //ser el siguiente elemento
        for (Entry<String, HashSet<String>> entry : mapa.entrySet()) { 
            String inicial = entry.getKey();
            HashSet<String> palabras = entry.getValue();
            
            try {
                FileWriter fw = new FileWriter("Dictionary\\"+inicial+".txt");
                BufferedWriter bw = new BufferedWriter(fw);
                //Escribir cada palabra en una línea
                for(String p :palabras){
                    bw.write( p + "\n");
                }
                //Cerrar el BufferedWriter, que si no no hace flush
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(DictionaryNoRepetitions.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
            
        }
    }

}
