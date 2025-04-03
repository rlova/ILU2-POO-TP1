package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtal) {
		this.nom = nom;
		this.villageois = new Gaulois[nbVillageoisMaximum];
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

	public String afficherVillageois() throws VillageSansChefException {
		if (chef==null) {
			throw new VillageSansChefException("Le village n'a pas de chef");
		}
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
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int indiceEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
		if (indiceEtal!= -1) {
			chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
			marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'etal n° " + (indiceEtal+1) + "\n");	
		} else {
			chaine.append("Aucun étal disponible pour " +vendeur.getNom());
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit.length==0) {
			chaine.append("Il n'y a pas de vendeur qui propose " + produit + " au marché.\n");
		} else if (etalsProduit.length==1) {
			chaine.append("Seul le vendeur "+ etalsProduit[0].getVendeur().getNom() + " propose des " + produit + " au marché\n");
		} else {
			chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");
			for (int i=0; i<etalsProduit.length; i++) {
				chaine.append(" - " + etalsProduit[i].getVendeur().getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etal = rechercherEtal(vendeur);
		return etal.libererEtal();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village '"+getNom()+"' possède plusieurs étals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
	
	private static class Marche {
		private Etal[] etals;
		
		private Marche(int nbEtal) {
			this.etals = new Etal[nbEtal];
			for (int i=0; i<nbEtal; i++) {
				etals[i] = new Etal();
			}
		}		
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal>=0 && indiceEtal<etals.length) {
				etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);				
			} else {
				System.out.println(vendeur.getNom()+" n'a pas pu utiliser l'étal\n");
			}
		}
		
		public int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		//tableau avec tous les étals qui vendent le produit
		public Etal[] trouverEtals(String produit) {
			int nbr_Etal_occupe = 0;
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					nbr_Etal_occupe++;
				}
			}
			Etal[] etalProduit = new Etal[nbr_Etal_occupe];
			int index = 0;
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalProduit[index] = etals[i];
					index++;
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
			String libre = "L'étal est libre";
			for (int i=0; i<etals.length; i++) {
				if (libre.equals(etals[i].afficherEtal())) {
					nbEtalVide++;
				} else {
					chaine.append(etals[i].afficherEtal());
				}
			}
			if (nbEtalVide>0) {
				chaine.append("Il reste " + nbEtalVide + " etals non utilises dans le marche.\n");
			}
			return chaine.toString();
		}
	}
}