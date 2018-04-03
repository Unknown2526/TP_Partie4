package main;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CalculerFacture {

	private ArrayList<String> facture = new ArrayList<String>();
	private List<String> nom = new ArrayList<String>();
	private List<String> plat = new ArrayList<String>();
	private List<String> commande = new ArrayList<String>();
	private List<String> table = new ArrayList<String>();

	public CalculerFacture(List<String> nom2, List<String> plat2,
			List<String> commande2, List<String> table2) {

		this.nom = nom2;
		this.plat = plat2;
		this.commande = commande2;
		this.table = table2;

	}

	public ArrayList<String> calculer(ArrayList<String> factureFinal) {

		Verification verification = new Verification(this.nom, this.plat,
				this.commande);
		
		List<String> nomCommande = new ArrayList<>();
		boolean factureAdded = false;

		factureAdded = isPrixValid(verification, factureAdded);

		ajoutCalcul(factureAdded);

		for (int i = 0; i < commande.size(); i++) {

			nomCommande.add(commande.get(i).split(" ")[0]);
		}

		nom.removeAll(nomCommande);

		factureFinal.add("Bienvenue chez Barrette!\nFacture:");

		//refactoring
		AddFactFinal(factureFinal);
		//refactoring
		//AddNomFactFinal(factureFinal);
		
		return factureFinal;
	}

	public void ajoutCalcul(boolean factureAdded) {
		if (factureAdded) {

			DecimalFormat format = new DecimalFormat(".##");
			
			for (int i = 0; i < facture.size(); i++) {
				for (int j = i + 1; j < facture.size(); j++) {
					if (facture.get(i).split(" ")[0].equals(facture.get(j)
							.split(" ")[0])) {

						double calcul = Double.parseDouble(facture.get(i)
								.split(" ")[1])
								+ Double.parseDouble(facture.get(j).split(" ")[1]);
						facture.set(i, facture.get(i).split(" ")[0] + " "
								+ format.format(calcul));
						facture.remove(facture.get(j));
					}
				}
			}
		}
	}

	public boolean isPrixValid(Verification verification, boolean factureAdded) {
		double montant;
		//if (verification.verifiePrix()) {
			
			for (int i = 0; i < commande.size(); i++) {
				for (int j = 0; j < plat.size(); j++) {

					if (plat.get(j).split(" ")[0].equals(commande.get(i).split(
							" ")[1])) {

						montant = Double.parseDouble(plat.get(j).split(" ")[1])
								* Double.parseDouble(commande.get(i).split(" ")[2]);

						try {
							if (montant > 100) {
								montant *= 1.15;
							}
						} catch (Exception e) {
						}
						
						facture.add("Table#" + table.get(i) + "\n" + commande.get(i).split(" ")[0] + " "
								+ calculTaxe(montant));
						factureAdded = true;
					}
				}
			}
		//}
		return factureAdded;
	}

	public void AddNomFactFinal(ArrayList<String> factureFinal) {
		for (int i = 0; i < nom.size(); i++) {
			factureFinal.add(nom.get(i) + " 0.00$");
		}
	}

	public void AddFactFinal(ArrayList<String> factureFinal) {
		for (int i = 0; i < facture.size(); i++) {
			factureFinal.add(facture.get(i) + "$\n");
		}
	}
	
	public double calculTaxe(double montantAvantTaxe) {
		
		double avecTaxe = 0;
		
			avecTaxe = montantAvantTaxe * 1.15;
		
		return avecTaxe;
	}
}
