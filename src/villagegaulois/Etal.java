package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

//	public String libererEtal() {
//		etalOccupe = false;
//		StringBuilder chaine = new StringBuilder();
//		try {
//			chaine.append(
//					"Le vendeur " + vendeur.getNom() + " quitte son étal, ");
//			int produitVendu = quantiteDebutMarche - quantite;
//			if (produitVendu > 0) {
//				chaine.append(
//						"il a vendu " + produitVendu + " parmi " + produit + ".\n");
//			} else {
//				chaine.append("il n'a malheureusement rien vendu.\n");
//			}
//		    this.vendeur = null;
//		    this.produit = null;
//		    this.quantiteDebutMarche = 0;
//		    this.quantite = 0;
//		} catch (NullPointerException e) {
//			chaine.append("L'etal n'est pas occupe");
//		}
//		return chaine.toString();
//	}
	public String libererEtal() throws IllegalStateException {
//		creer un try catch pour l'exception
//		try catch : generer l'exception
	    if (!etalOccupe) {
	        throw new IllegalStateException("L'etal n'est pas occupe");
	    }
	    etalOccupe = false;
		StringBuilder chaine = new StringBuilder();
	    chaine.append(
				"Le vendeur " + vendeur.getNom() + " quitte son étal, ");
		int produitVendu = quantiteDebutMarche - quantite;
		if (produitVendu > 0) {
			chaine.append(
					"il a vendu " + produitVendu + " parmi " + produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) throws NullPointerException, IllegalArgumentException {
		if (acheteur==null) {
			throw new NullPointerException("L'acheteur ne doit pas être null");
		}
		if (quantiteAcheter<1) {
			throw new IllegalArgumentException("La quantité doit etre positive");
		}
		if (!etalOccupe) {
			throw new IllegalStateException("L'etal doit etre occupe");
		}	
//		if (etalOccupe) {
//		tout ca c'est un try catch
			StringBuilder chaine = new StringBuilder();
			chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
					+ " " + produit + " à " + vendeur.getNom());
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", "
						+ acheteur.getNom() + " vide l'étal de "
						+ vendeur.getNom() + ".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " + acheteur.getNom()
						+ ", est ravi de tout trouver sur l'étal de "
						+ vendeur.getNom() + "\n");
			}
			return chaine.toString();
//		}
	}

	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}

}
