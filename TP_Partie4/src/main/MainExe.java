package main;

import java.util.ArrayList;
import java.util.List;

public class MainExe {
	
	public static void main(String[] args) {
		
		LireFichier lireFichier = new LireFichier();
		
		ArrayList<List<String>> fichier = new ArrayList<List<String>>(lireFichier.lire());
		
		lireFichier.verifie( fichier.get( 0 ), fichier.get( 1 ), fichier.get( 2 ) );
		
		lireFichier.faireFacture();
		
	}
}