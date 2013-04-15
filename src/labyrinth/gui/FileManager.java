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
	
	
	/**
	 * Grava o estado actual do jogo.
	 * @param lab labirinto a ser gravado
	 * @param path caminho para onde gravar o ficheiro
	 */
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
	
	/**
	 * Carrega o estado do jogo gravado num ficheiro.
	 * @param path caminho de onde carrega o ficheiro
	 * @return devolve o labirinto gravado no ficheiro
	 */
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
