package mpeiProject;

import java.util.Set;

public class MinHashTest {
	
	public static void main(String[] args) {
		
		System.out.println("Similarity algorithm Strings Test");
		int numHash = 50;
		int k = 2;
		MinHash minhash = new MinHash(numHash);
		
		String text1 = "Bob o Construtor";
		String text2 = "Bob o Trabalhador";
		String text3 = "Bob o Construtor";
		String text4 = "Algo nada a haver";
		
		Set<String> set1 = ShingleUtils.splitToShingles(text1, k);
		Set<String> set2 = ShingleUtils.splitToShingles(text2, k);
		Set<String> set3 = ShingleUtils.splitToShingles(text3, k);
		Set<String> set4 = ShingleUtils.splitToShingles(text4, k);
		
		System.out.printf("\nSimilarity 1: "+minhash.jaccardSimilarity(set1, set2));
		System.out.printf("\nSimilarity 2: "+minhash.jaccardSimilarity(set1, set3));
		System.out.printf("\nSimilarity 3: "+minhash.jaccardSimilarity(set1, set4));
		
	}
}