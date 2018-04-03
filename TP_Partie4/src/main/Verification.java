package main;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Verification {

	List<String> nom = new ArrayList<String>();
	List<String> plat = new ArrayList<String>();
	List<String> commande = new ArrayList<String>();

	public Verification(List<String> nom, List<String> plat,
			List<String> commande) {

		this.nom = nom;
		this.plat = plat;
		this.commande = commande;
	}

	public boolean verifieFichier() {

		boolean fichierRespect = false;
		String[] mot = null;

		for (int i = 0; i < commande.size(); i++) {

			mot = commande.get(i).split("\\s+");
		}
		if (mot.length == 3) {
			fichierRespect = true;
		}

		return fichierRespect;
	}
/**
 * Vérifie si le nom est dans la liste des commandes.
 * @return true si le client est dans la liste
 */
	public boolean verifieNomCommande() {

		List<String> nomCommande = new ArrayList<>();
		boolean verifie = true;

		for (int i = 0; i < commande.size(); i++) {

			nomCommande.add(commande.get(i).split(" ")[0]);
		}

		nomCommande.removeAll(nom);
		
		if (!nomCommande.isEmpty()) {
				verifie = false;
				PrintWriter writer;
				try {
					writer = new PrintWriter("Facture-du-" + LocalDate.now() + ".txt", "UTF-8");
					writer.println("Erreur des clients:");
					System.out.println("Erreur des clients:");
					for (int i = 0; i < nomCommande.size(); i++) {
						writer.println(nomCommande.get(i) + " n'est pas dans la liste des clients.");
						System.out.println(nomCommande.get(i) + " n'est pas dans la liste des clients.");
					}
					writer.println();
					System.out.println();
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		}

		return verifie;

	}

	public boolean verifiePlatCommande() {

		List<String> nomCommande2 = new ArrayList<>();
		List<String> platCommande = new ArrayList<String>();
		boolean verifie = true;

		for (int i = 0; i < commande.size(); i++) {

			nomCommande2.add(commande.get(i).split(" ")[1]);
		}

		for (int i = 0; i < plat.size(); i++) {

			platCommande.add(commande.get(i).split(" ")[1]);
		}

		nomCommande2.removeAll(platCommande);

		if (!nomCommande2.isEmpty()) {

			for (int i = 0; i < nomCommande2.size(); i++) {

				verifie = false;
			}
		}
		return verifie;

	}
	
	public boolean verifiePrix() {
		
		boolean verifie = true;
		
		for (int i = 0; i < commande.size(); i++) {
			
			if (Double.parseDouble(commande.get(i).split(" ")[2]) <= 0) {
				
				verifie = false;
			}
		}
		
		return verifie;
	}
}
