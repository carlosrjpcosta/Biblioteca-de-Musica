package mpei_project;
import java.util.HashMap;
public class ContadorEstocastico {
	static HashMap<String, Integer> mapProb = new HashMap<>();
	
	public static int contador(String str) {
		if(Math.random() < 0.5) {
			if(!mapProb.containsKey(str)) {
				mapProb.put(str, 1); 
			}
			else {
				int num = mapProb.get(str);
				mapProb.replace(str, num, num+1);
			}
			
			return (int) ((int)mapProb.get(str) * 2); // multiplies by 2
		}
		
		return 0;
	}

}
