package test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import main.CalculerFacture;
import main.LireFichier;
import main.Verification;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFacture {
	
	private List<String> nom = new ArrayList<String>();
	private List<String> plat = new ArrayList<String>();
	private List<String> commande = new ArrayList<String>();
	private List<String> table = new ArrayList<String>();
	private ArrayList<String> factureFinal = new ArrayList<String>();
	private boolean verifier;
	private LireFichier lireFichier = new LireFichier();
	private Verification verification;
	private CalculerFacture calculerFacture;
	
	@Before
	public void setUp() {
		nom.add("Bob");
		plat.add("Poutine");
		commande.add("Roger Poutine1 -10");
		verification = new Verification(nom, plat, commande);
		calculerFacture = new CalculerFacture(nom, plat, commande, table);
	}
	
	@After
	public void tearDown(){
		
		nom = null;
		plat = null;
		commande = null;
		table = null;
	}
	
	
	@Test
	public void testerNomClient() {
		
		verifier = verification.verifieNomCommande();
		assertFalse(verifier);
	}
	
	@Test
	public void testerPlat() {		
		verifier = verification.verifiePlatCommande();
		assertTrue(verifier);
	}
	
	@Test
	public void testerFormat() {		
		verifier = verification.verifieFichier();		
		assertTrue(verifier);
	}

	@Test
	public void testerPrix() {		
		verifier = verification.verifiePrix();		
		assertFalse(verifier);
	}
	
	@Test
	public void testerCalculer() {
		
		calculerFacture.calculer(factureFinal);
		assertNotNull(factureFinal);	
	}
	
	@Test
	public void testerLireFichier() {	
		ArrayList<List<String>> lect = new ArrayList<List<String>>();
		lect = lireFichier.lire() ;
		assertNotNull(lect );		
	}
	
	@Test
	public void testerCalculTaxe() {
		CalculerFacture tax = new CalculerFacture(nom, plat, commande, table);
		double taxAttendu =1.15;
		tax.calculTaxe(1.00);
		assertNotEquals(taxAttendu,tax);		
	}
	
	@Test
	public void testerIsFacture() {
		verifier = lireFichier.isFacture();
		assertTrue(verifier);		
	}
	
	@Test
	public void testerAddNomFactFinal() {
		ArrayList<String> addNomFinalFact = new ArrayList<String>();
		calculerFacture.AddFactFinal(addNomFinalFact); ;
		assertNotNull(addNomFinalFact);
	}
	
	@Test
	public void testerAddFactFinal() {
		ArrayList<String> addFinalFact = new ArrayList<String>();
		calculerFacture.AddFactFinal(addFinalFact); ;
		assertNotNull(addFinalFact);
	}
	@Test
	public void testerIsFacturAddedValid() {
		ArrayList<String> factAdded = new ArrayList<String>();
		calculerFacture.AddFactFinal(factAdded); ;
		assertNotNull(factAdded);
	}
	
	@Test
	public void testerIsPrixValid() {
		ArrayList<String> pxValid = new ArrayList<String>();
		calculerFacture.AddFactFinal(pxValid); ;
		assertNotNull(pxValid);
	}
	
}
