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
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeurs(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le vendeur a �t� installer");
		return produit;
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
				for (int j=0; i<etals.length; j++) {
					if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
						etalProduit[i] = etals[j];
					} else {
						j++;
					}
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
		
		private String afficherMarche() {
			int nbEtalVide = 0;
			for (int i=0; i<nbEtal; i++) {
				if (!etals[i].isEtalOccupe()) {
					nbEtalVide++;
				} else {
					System.out.println(etals[i].getVendeur()+" vend ");
				}
			}
			if (nbEtalVide>0) {
				System.out.println("Il reste "+nbEtalVide+" �tals non utilis�s dans le march�.\n");
			}
		}
	}
}