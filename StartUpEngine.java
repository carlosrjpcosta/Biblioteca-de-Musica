package mpei_project;
import java.util.List;
import java.util.Scanner;
public class StartUpEngine {
	
	static Scanner sc = new Scanner(System.in);
	static BibliotecaMusica bm = new BibliotecaMusica((int)1e6, 6, 70);
	
	public static void main(String[] args) {
		
		
		int op = -1;
		while(op != 0) {
			System.out.println("-------------------------------\n"
					+ "1 - Find Music\n"
					+ "2 - Discover Music\n"
					+ "0 - Log Out\n"
					+ "-------------------------------\n");
			
			try {
				System.out.print("Option: ");
				op = Integer.parseInt(sc.nextLine());
			} catch(NumberFormatException e) {
				System.exit(0);
			}
			
			switch(op) {
				case 1:	shearchMusic();
				case 2: findMusic();
				case 0: 
					System.out.println("Thank you for your time!");
					break;
			}
		}

	}


	public static void shearchMusic() {
		int op1 = -1;
		do {
			System.out.println("--------Select-----------------\n"
					+ "1 - Album ID\n"
					+ "2 - Artist ID\n"
					+ "3 - Genre\n"
					+ "0 - End shearching\n"
					+ "-------------------------------\n");
			
			
			System.out.print("Option: ");
			op1 = Integer.parseInt(sc.nextLine());
			if(op1 == 0) break;		//breakes the cycle even before it begins

			
			System.out.print("What to find: ");	
			String info = sc.nextLine();
					
			//else (adicionar musicas caso haja tempo depois);
			
			String op2 = "";
			switch(op1) {
				case 1 : 
					System.out.print("Play album? (Y/N)");
					op2 = sc.nextLine();
						
					if(op2.equals("Y")) {
						bm.playMusic(op1, info);
						System.out.println("Music Played!");
					}
					else continue;			
				case 2:	
					System.out.println("Play Artist music? (Y/N)");
					op2 = sc.nextLine();
					
					if(op2.equals("Y")) {
						bm.playMusic(op1, info);
						System.out.println("Music Played!");
					}
					else continue;		
				case 3:
					System.out.println("Play Genre music? (Y/N)");
					op2 = sc.nextLine();
					
					if(op2.equals("Y")) {
						bm.playMusic(op1, info);
						System.out.println("Music Played!");
					}
					else continue;	
				case 0: break;
			}
		} while(op1 != 0);
	}

	private static void findMusic() {
		
		List<String> list = bm.guessedMusicToArray();
		
		//print list
		list.forEach(s -> System.out.println());
	}

}
