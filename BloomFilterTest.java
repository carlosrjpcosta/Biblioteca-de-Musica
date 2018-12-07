package mpei_project;

public class BloomFilterTest {

	public static void main(String[] args) {
		int k = 6; // number of hashFunctios
		String[] movies = {"Ralph", "Creed II", "The Grinch", "Fantastic Beast", "Bohemia Rhapsody", "Robin Hood", "Widows"};
		String[] movies2 = {"Ralph", "Fantastic Beast", "Green Book", "Venom", "The Grinch", "Robin Hood", "The Favourite"};
		BloomFilter bf = new BloomFilter((int)1e6, k);
		
		System.out.println("Inserting the movies in BloomFilter");
		for(String movie : movies) {
			bf.insert(movie);
			System.out.println("Movie insert: "+movie);
		}
		
		System.out.println("\nCheking the elements of the set movies 2");
		for(String movie : movies2) {
			if(bf.isMember(movie)) {
				System.out.println("Movie "+movie+" may be in the set");
			}
			else System.out.println("Movie "+ movie+" is not in the set");
		}
		
	}
}

