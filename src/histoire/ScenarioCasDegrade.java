package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
//		Etal etal1 = new Etal();
//		etal1.libererEtal();
		System.out.println("Fin du test 1");
//		Village village = new Village("Villageois",10,5);
//		Gaulois humain1 = new Gaulois("Humain",2);
//		etal1.acheterProduit(-1, humain1);
		System.out.println("Fin du test 2");
//		try {
//			Etal etalProduit = village.rechercherEtal(humain);
//			etalProduit.acheterProduit(1, humain);
//			System.out.println("Fin du test 3");
//		} catch (IllegalStateException e) {
//			System.out.println("L'etal doit etre occupe");
//		}
		try {
	        Etal etal2 = new Etal();
	        Gaulois humain2 = new Gaulois("Humain", 2);
	        etal2.acheterProduit(1, humain2);
	        System.out.println("Fin du test 3");
	    } catch (IllegalStateException e) {
	        System.out.println("L'etal doit etre occupe");
	    }
	}
}
