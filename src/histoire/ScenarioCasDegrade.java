package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		try {
			Etal etal = new Etal();
			etal.acheterProduit(1, null);
		} catch (NullPointerException e) {
			System.out.println("L'acheteur ne doit pas etre null");
			e.printStackTrace();
		}
		try {
	        Etal etal = new Etal();
	        Gaulois acheteur = new Gaulois("Acheteur", 2);
	        etal.acheterProduit(-1, acheteur);
	    } catch (IllegalArgumentException e) {
	        System.out.println("L'achat n'a pas été effectué");
	    	e.printStackTrace();
	    }
		try {
			Etal etal = new Etal();
			etal.libererEtal();
		} catch (IllegalStateException e) {
			System.out.println("L'etal ne comporte pas de vendeur");
			e.printStackTrace();
		}
		try {
			Village village = new Village("Village", 4,3);
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			System.out.println("Il doit y avoir un chef dans le village");
			e.printStackTrace();
		}
	}
}
