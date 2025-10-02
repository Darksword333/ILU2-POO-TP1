package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois gaugolois = new Gaulois("Gaugolois", 0);
		etal.libererEtal();
		System.out.println("Fin du test.");
		
		etal.acheterProduit(1, null);
		System.out.println("Fin du test. 1");
		
		try {	
			etal.occuperEtal(gaugolois, "Test", 0);
			etal.occuperEtal(gaugolois, "Test", 0);
			etal.acheterProduit(-1, gaugolois);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Fin du test. 2");
		}
		try {	
			etal.libererEtal();
			etal.acheterProduit(1, gaugolois);
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("Fin du test. 3");
		}
		
		try {
			Village village = new Village("le village des irréductibles", 10, 5);
			Druide druide = new Druide("Panoramix", 2, 5, 10);
			Gaulois obelix = new Gaulois("Obélix", 25);
			Gaulois asterix = new Gaulois("Astérix", 8);
			Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
			Gaulois bonemine = new Gaulois("Bonemine", 7);
		
			village.ajouterHabitant(bonemine);
			village.ajouterHabitant(assurancetourix);
			village.ajouterHabitant(asterix);
			village.ajouterHabitant(obelix);
			village.ajouterHabitant(druide);
			village.afficherVillageois();
		}
		catch (VillageSansChefException e) {
			e.printStackTrace();
			System.out.println("Fin du test. 4");
			
		}
	}
}