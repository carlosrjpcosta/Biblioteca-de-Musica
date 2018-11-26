package mpeiProject;
import java.util.HashMap;
public class BloomFilter {
	
	private boolean set[];
	private int numHashs;
	private HashMap<String, Integer> counter;
	
	public BloomFilter(int N, int numHashs) {
		set = new boolean[N];
		this.numHashs = numHashs;
		counter = new HashMap<>();
	}
	
	public void insert(String elem) {
		String str = elem;
		
		for(int i = 1; i <= numHashs; i++) {
			str += i;
			int hash = Math.abs(str.hashCode());
			hash = Math.abs(hash % set.length);
			set[hash] = true;
		}
		//count 
		if(!counter.containsKey(elem)) counter.put(elem, 1);
		else {
			int num = counter.get(elem);
			counter.replace(elem, num, num+1);
		}
	}
		
	public boolean isMember(String elem) {
		String str = elem;
		boolean member = true;
		
		for(int i=1; i <= numHashs; i++) {
			str+=i;
			int hash = Math.abs(str.hashCode());
			hash = Math.abs(hash % set.length);
			if(!set[hash]) {
				member = false;
				break;
			}
		}
		return member;
	}
	
	public int repeats(String elem) {
		if(!counter.containsKey(elem)) return 0;
		return counter.get(elem);
	}
	
}
