package mpei_project;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class BibliotecaMusica {
	
	List<String> albuns;				// INTEGER VALUE = 1
	List<String> artist;				// INTEGER VALUE = 2
	List<String> genre;				// INTEGER VALUE = 3
	
	private static BloomFilter bf;
	private static MinHash minhash;
	private int lastIndex;
	
	static Set<String> types;
	
	static HashMap<String, List<String>> history;
	static HashMap<Integer, List<String>> map = new HashMap<>();
	
	
	public BibliotecaMusica(int bloomFilterSize, int bloomFilterNumHash, int numHashFunctions) {
		bf = new BloomFilter(bloomFilterSize,bloomFilterNumHash);
		minhash = new MinHash(numHashFunctions);
	}
	
	public void readFile(String file) throws IOException {
		List<String> infoFile = Files.readAllLines(Paths.get(file));
		
		Iterator<String> it = infoFile.iterator();
		while(it.hasNext()) {
			String[] strSpliter = it.next().split(",");
			albuns.add(strSpliter[1]);
			artist.add(strSpliter[4]);
			genre.add(strSpliter[5]);
		}
		
		//keps track of witch set to Use
		map.put(1, albuns);
		map.put(2, artist);
		map.put(3, genre);
 	}
	
	private static int size(int i) {
		return map.get(i).size();
	}
	
	//Returns true if there is a idMatcher
	public boolean idTracker(int i, String input) {
		assert map.containsKey(i) : "There is no such Key value";				//Must contain the key
		if(!map.get(i).contains(input)) return false;
		return true;
	}
	
	
	
	//Calculates Jaccard Similarities from what is played 
	public void playMusic(int i, String input) {
		types.add(input);
		guess(input, i);
		
		lastIndex = i;
	}
	
	private static boolean guess(String inputText, int i) {		
		boolean validContent = false;
		double thresholder = 0.24;
		
		if(types.contains(inputText) && bf.isMember(inputText)) return false;
		
		bf.insert(inputText);
		
		
		double jaccardSim = calculateJaccardSimilarity(inputText, map.get(i).toString());

		for(int j = 0; j < size(i); j++) {
			
			if(jaccardSim >= thresholder) validContent = true;
			if(!validContent) throw new NoSuchElementException();
		
			if(!history.containsKey(inputText)) history.put(inputText, new ArrayList<>());
			history.get(inputText).add(map.get(i).toString());
		}
		
		
		return validContent;
 	}
	
	@SuppressWarnings("unlikely-arg-type")
	public List<String> guessedMusicToArray() {
		return history.get(lastIndex);
	}
	

	private static double calculateJaccardSimilarity(String str1, String str2) {
		return minhash.jaccardSimilarity(
				ShingleUtils.splitToShingles(MinHash.formatString(str1), 10),
				ShingleUtils.splitToShingles(MinHash.formatString(str2), 10)
				);		
	}
	
}
