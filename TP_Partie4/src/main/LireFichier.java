package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LireFichier {

	ArrayList<String> texte = new ArrayList<String>();
	List<String> nom = new ArrayList<String>();
	List<String> plat = new ArrayList<String>();
	List<String> commande = new ArrayList<String>();
	List<String> table = new ArrayList<String>();
	ArrayList<String> factureFinal = new ArrayList<String>();
	ArrayList<List<String>> tab = new ArrayList<List<String>>();
	int index1, index2, index3, index4;
	boolean fic = true, nomCommande = true, platCommande = true;
	
	public ArrayList<List<String>> lire() {
		
		//refactoring
		Buffering();
		
		//refactoring
		RepereTitres();
		
		return tab;
	}

	public void Buffering() {
		String fileName = "src/Facture.txt";

		String ligne = null;

		try {

			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((ligne = bufferedReader.readLine()) != null) {

				texte.add(ligne);
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Le fichier n'existe pas.");
		} catch (IOException ex) {
			System.out.println("Pas capable de lire le fichier: '" + fileName + "'");
		}
	}

	public void RepereTitres() {
		index1 = texte.indexOf("Plats :");
		index2 = texte.indexOf("Commandes :");
		index3 = texte.indexOf("Table :");
		index4 = texte.indexOf("Fin");

		nom = texte.subList(1, index1);
		plat = texte.subList(index1 + 1, index2);
		commande = texte.subList(index2 + 1, index3);
		table = texte.subList(index3 + 1, index4);
		
		tab.add(nom);
		tab.add(plat);
		tab.add(commande);
		tab.add(table);
	}
	
	public void verifie(List<String> nom, List<String> plat, List<String> commande) {
		
		Verification verifie = new Verification(nom, plat, commande);
		
		//refactoring
		ErrorsMsge(verifie);
	}

	public void ErrorsMsge(Verification verifie) {
		if (!verifie.verifieFichier()) {

			System.out.println("Le fichier ne respecte pas le format demandé!");
			fic = false;
		} else {
			if (!verifie.verifieNomCommande()) {
				//System.out.println("Il y a un/des client(s) dans la commande qui n'est pas dans la liste des clients.");
				nomCommande = false;
			}
			if (!verifie.verifiePlatCommande()) {
				//System.out.println("Il y a un/des plats dans la commande qui n'est pas dans la liste des plats.");
				platCommande = false;
			}
		}
	}
	
	public void faireFacture(){
		
		CalculerFacture facture = new CalculerFacture(nom, plat, commande, table);

		//if (isFacture()) {

			factureFinal = new ArrayList<String>(facture.calculer(factureFinal));
			
			//refactoring
			FactureFinale();
		//}
	}

	public void FactureFinale() {
		for (int i = 0; i < factureFinal.size(); i++) {
			System.out.println(factureFinal.get(i));
		}
	}

	public boolean isFacture() {
		return fic && nomCommande && platCommande;
	}
}
