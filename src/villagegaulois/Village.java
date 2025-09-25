package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nb_etal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nb_etal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche {
		private Etal[] etals;
		private int nb_etal = 0;
		
		private Marche(int nb_etal) {
			this.nb_etal = nb_etal;
			etals = new Etal[nb_etal];
			for (int i = 0; i < nb_etal; i++) {
	            etals[i] = new Etal();
	        }
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			for (int i = 1; i < this.nb_etal; i++) {
				if (!etals[i].isEtalOccupe())
					return i;
			}
			return -1;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < this.nb_etal; i++) {
				if (etals[i].getVendeur() == gaulois)
					return etals[i];
			}
			return null;
		}
		private String afficherMarche() {
			int nbEtalVide = 0;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < this.nb_etal; i++) {
				if (!etals[i].isEtalOccupe())
					nbEtalVide++;	
				else
					sb.append(etals[i].afficherEtal() + "\n");
			}
			sb.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
			return sb.toString();
		}
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder sb = new StringBuilder();
		int etalLibre = this.marche.trouverEtalLibre();
		if (etalLibre == -1) {
			return "Pas d'Etal libre " + vendeur.getNom() + " rentre bredouille";
		}
		sb.append(vendeur.getNom());
		sb.append(" cherche un endroit pour vendre ");
		sb.append(nbProduit);
		sb.append(" ");
		sb.append(produit);
		sb.append(".");
		this.marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
		sb.append("\nLe vendeur ");
		sb.append(vendeur.getNom());
		sb.append(" vend des ");
		sb.append(produit);
		sb.append(" à l'étal n°");
		sb.append(etalLibre);
		sb.append(".\n");
		return sb.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder sb = new StringBuilder();
		Gaulois[] tab = new Gaulois[this.marche.nb_etal];
		int nbVendeur = 0;
		for (int i = 0; i < this.marche.nb_etal; i++) {
			if (this.marche.etals[i].contientProduit(produit)) {
				tab[nbVendeur] = this.marche.etals[i].getVendeur();
				nbVendeur++;
			}
		}
		switch(nbVendeur) {
			case 0:
				sb.append("Il n'y a pas de vendeur qui propose des ");
				sb.append(produit);
				sb.append(" au marché.");
				break;
			case 1:
				sb.append("Seul le vendeur ");
				sb.append(tab[0].getNom());
				sb.append(" propose des fleurs au marché.");
				break;
			default:
				sb.append("Les vendeurs qui proposent des fleurs sont :");
				for (int i = 0; i < nbVendeur; i++) {
					sb.append("\n- ");
					sb.append(tab[i].getNom());
				}
				break;
		}
		sb.append("\n");
		return sb.toString();
	}
	
	 public Etal rechercherEtal(Gaulois vendeur) {
		 for (int i = 1; i < this.marche.nb_etal; i++) {
			 if (this.marche.etals[i].getVendeur() == vendeur)
				 return this.marche.etals[i];
		 }
		 return null;
	 }
	 
	 public String partirVendeur(Gaulois vendeur) {
		 String msg;
		 Etal etal = this.rechercherEtal(vendeur);
		 msg = etal.libererEtal();
		 return msg;
	 }
	 
	 public String afficherMarche() {
		 return this.marche.afficherMarche();
	 }
}