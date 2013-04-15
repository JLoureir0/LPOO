package labyrinth.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import labyrinth.logic.Labyrinth;

/**
 * Classe que trata da gravação e do carregamento de um estado do jogo
 */

public class FileManager {

	
	public FileManager() {
	}
	

	public void saveFile(Labyrinth lab, String path) {
		   try{
				FileOutputStream fout = new FileOutputStream(path);
				ObjectOutputStream oos = new ObjectOutputStream(fout);   
				oos.writeObject(lab);
				oos.close();
			   }catch(Exception ex){
				   ex.printStackTrace();
			   }
	}
	
	public Labyrinth loadFile(String path) {
		   try{
			   
			   FileInputStream fin = new FileInputStream(path);
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   Labyrinth lab = (Labyrinth) ois.readObject();
			   ois.close();
	 
			   return lab;
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
			   return null;
		   } 
	}

}
