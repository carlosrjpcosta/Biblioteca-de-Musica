package mpeiproject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
public class MinHash {
	
	private int numHash;		// number os hashes that will be used
	private int hash[];			
	
	public MinHash(int numHash) {
		this.numHash = numHash;
		hash = new int[numHash];
		for(int i = 0; i < numHash; i++) {
			hash[i] = Math.abs((int)Math.random() * Integer.MAX_VALUE);
		}
	}
	
	//Calculats JaccardSimilarity from two sets of shingles
	public double jaccardSimilarity(Set<String> set1, Set<String> set2) {
		Set<Integer> hashset1 = new TreeSet<Integer>(intToArrayList(getStringHashSet(set1)));
		Set<Integer> hashset2 = new TreeSet<Integer>(intToArrayList(getStringHashSet(set2)));
		
		Set<Integer> intersept = new TreeSet<Integer>();
		intersept.addAll(hashset1);
		intersept.retainAll(hashset2);
		
		Set<Integer> union = new TreeSet<>();
		union.addAll(hashset1);
		union.addAll(hashset2);
		
		return (double) intersept.size() / (double) union.size();
	}
	
	
	public static List<Integer> intToArrayList (int[] intArray) {
		List<Integer> newList = new ArrayList<>();
		for(int n : intArray) {
			newList.add(n);
		}
		return newList;
	}
	
	public static String formatString(String str) {
		return str.toLowerCase().trim()
									.replaceAll(" +", "")
									.replaceAll("\t", "")
									.replaceAll("\n","")
									.replaceAll(" ", "");
	}
	
	public int[] getStringHashSet(Set<String> shingles) {
		
		int[] hashValues = new int[shingles.size()];
		Iterator<String> setIterator = shingles.iterator();
		
		for(int i = 0; i < hashValues.length; i++) {
			hashValues[i] = getMinHashValue(setIterator.next());
		}
		return hashValues;
	}
	
	private int getMinHashValue(String shingle) {
		int min = Integer.MAX_VALUE;
		for(int i=0; i < numHash; i++) {
			int hashCode = shingle.hashCode() ^ hash[i];
			min = Math.min(min, hashCode);
		}
		return min;
	}
	
}

