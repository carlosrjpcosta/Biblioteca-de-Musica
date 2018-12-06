package mpeiProject;

public class BloomFilter {
	
	private int set[];
	private int numHashs;
	
	public BloomFilter(int N, int numHashs) {
		set = new int[N];
		this.numHashs = numHashs;
	}
	
	public void insert(String elem) {
		String str = elem;
		
		for(int i = 1; i <= numHashs; i++) {
			str += i;
			set[hash(str, numHashs)]++;
		}
	}
		
	public boolean isMember(String elem) {
		String str = elem;
		boolean member = true;
		for (int i=1; i <= numHashs; i++) {
			str += i;
			if(set[hash(str, numHashs)] == 0) {
				member = false;
				break;
			}
		}
		return member;
	}
	
	public int count(String elem) {
		int min = 10000;
		
		for (int i=1; i <= numHashs; i++) {
			elem += i;
			int h = hash(elem, numHashs);
			if (set[h] < min) {
				min = set[h];
			}
		}
		
		return min;
	}
	
	private int hash(String elem, int humHashs) {
		int hash = Math.abs(elem.hashCode());
		return Math.abs(hash % set.length);
	}
}
