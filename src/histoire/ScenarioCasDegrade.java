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
			e.printStackTrace();
//			System.out.println("L'acheteur ne doit pas etre null");
		}
		try {
	        Etal etal = new Etal();
	        Gaulois obelix = new Gaulois("Obelix", 2);
	        etal.acheterProduit(-1, obelix);
	    } catch (IllegalArgumentException e) {
			e.printStackTrace();
//	        System.out.println("La quantite doit etre positive");
	    }
		try {
			Etal etal = new Etal();
			etal.libererEtal();
		} catch (IllegalStateException e) {
			e.printStackTrace();
//			System.out.println("L'etal doit etre occupe");
		}
		try {
			Village village = new Village("Les Villages", 4,3);
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			e.printStackTrace();
//			System.out.println("Il doit y avoir un chef dans le village");
		}
	}
}
