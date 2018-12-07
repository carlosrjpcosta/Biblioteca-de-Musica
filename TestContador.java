package mpei_project;

public class TestContador {
	public static void main(String[] args) {
		
		String[] movies = {"Ralph", "Creed II", "The Grinch", "Fantastic Beast", "Bohemia Rhapsody", "Robin Hood", "Widows"};
		String[] movies2 = {"Ralph", "Fantastic Beast", "Green Book", "Venom", "The Grinch", "Robin Hood", "The Favourite"};
		
		for(String movie : movies) {
			System.out.println("Movie: " + movie + " repeats "+ ContadorEstocastico.contador(movie));
		}
		
	}
}
