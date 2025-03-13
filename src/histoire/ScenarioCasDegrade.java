package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		try {
			Etal etal = new Etal();
			etal.acheterProduit(1, null);
		} catch (NullPointerException e) {
			System.out.println("L'acheteur ne doit pas etre null");
		}
		try {
	        Etal etal = new Etal();
	        Gaulois obelix = new Gaulois("Obelix", 2);
	        etal.acheterProduit(-1, obelix);
	    } catch (IllegalArgumentException e) {
	        System.out.println("La quantite doit etre positive");
	    }
		try {
			Etal etal = new Etal();
			etal.libererEtal();
		} catch (IllegalStateException e) {
			System.out.println("L'etal doit etre occupe");
		}
	}
}
