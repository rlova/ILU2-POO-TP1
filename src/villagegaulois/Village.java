package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	private int nbEtal;

	public Village(String nom, int nbVillageoisMaximum,int nbEtal) {
		this.nom = nom;
		this.villageois = new Gaulois[nbVillageoisMaximum];
		this.nbEtal = nbEtal;
		this.marche = new Marche(nbEtal);
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
	
	public String installerVendeurs(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur).append(" cherche un endroit pour vender").append(nbProduit).append(produit);
		int indiceEtal = 1;
		marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
		chaine.append("Le vendeur").append(vendeur).append(" vend des ").append("fleurs ").append("à l'étal n° ").append(indiceEtal);
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		Etal etal = new Etal();
		if (etalsProduit.length==0) {
			chaine.append("Il n'y a pas de vendeur qui propose").append(produit).append("au marché.");
		} else if (etalsProduit.length==1) {
			chaine.append("Seul le vendeur "+ etal.getVendeur().getNom() + " propose des ").append(produit).append("au marché");
		} else {
			chaine.append("Les vendeurs qui proposent des ").append(produit).append("sont :\n");
			for (int i=0; i<etalsProduit.length; i++) {
				chaine.append(" - " + etal.getVendeur().getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		Etal etal = marche.trouverVendeur(vendeur);
		if (etal!=null) {
			etal.libererEtal();
		}
		chaine.append("Le vendeur").append(vendeur).append(" quitte son étal, il a vendu " ).append(marche.etals.length).append("de... parmis les ").append(marche.etals.length).append(" qu'il voulait vendre\n");
		return chaine.toString();
	}
	
	public String afficherMarche() {
		return marche.afficherMarche();
	}
	
	public static class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtal) {
			this.etals = new Etal[nbEtal];
			for (int i=0; i<etals.length; i++) {
				etals[i] = new Etal();
			}
		}		
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (etals[indiceEtal].isEtalOccupe()) {
				indiceEtal++;
			}
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		public int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			int compteur = 0;
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					compteur++;
				}
			}
			Etal[] etalProduit = new Etal[compteur];
			for (int i=0; i<etals.length; i++) {
				int j=0;
				if (etals[j].isEtalOccupe() && etals[j].contientProduit(produit)) {
					etalProduit[i] = etals[j];
				} else {
					j++;
				}
			}
			return etalProduit;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = 0;
			for (int i=0; i<etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					nbEtalVide++;
				} else {
					chaine.append(etals[i].afficherEtal());
				}
			}
			if (nbEtalVide>0) {
				chaine.append("Il reste ").append(nbEtalVide).append(" etals non utilis�s dans le march�.\n");
			}
			return chaine.toString();
		}
	}
}