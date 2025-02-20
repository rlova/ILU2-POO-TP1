package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	private static int nbEtal;

	public Village(String nom, int nbVillageoisMaximum,int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.nbEtal = nbEtal;
		Marche marche = new Marche(nbVillageoisMaximum);
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
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeurs(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le vendeur a été installer");
		return produit;+
	}
	
	public static class Marche {
		private static Etal[] etal;
		private int nbEtalVide = 0;

		public Marche(int nbEtal) {
			super();
			this.etal = new Etal[nbEtal];
			for (int i=0; i<nbEtal; i++) {
				etal[i] = new Etal();
			}
		}		
		
		public static void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etal[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		public static int trouverEtalLibre() {
			for (int i=0; i<nbEtal; i++) {
				if (!etal[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		public static Etal[] trouverEtals(String produit) {
			for (int i=0; i<nbEtal; i++) {
				if (etal[i].contientProduit(produit)) {
					return etal;
				}
			}
		}
		
		public static Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<nbEtal; i++) {
				if (etal[i].isEtalOccupe() && etal[i].getVendeur().equals(gaulois)) {
					return etal[i];
				}
				return null;
			}
			return null;
		}
		
		private String afficherMarche() {
			for (int i=0; i<nbEtal; i++) {
				if (!etal[i].isEtalOccupe()) {
					nbEtalVide++;
				} else {
					System.out.println(etal[i].getVendeur()+" vend ");
				}
			}
			if (nbEtalVide>0) {
				System.out.println("Il reste "+nbEtalVide+" étals non utilisés dans le marché.\n");
			}
		}
	}
}