package villagegaulois;

public class VillageSansChefException extends Exception {
	// 4 constructeurs
	
	public VillageSansChefException() {
	}
	
	public VillageSansChefException(String message) {
		super(message);
	}
	
	public VillageSansChefException(Throwable cause) {
		super(cause);
	}
	
	public VillageSansChefException(String message, Throwable cause) {
		super(message,cause);
	}
}
