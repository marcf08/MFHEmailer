package marcus.email.database;

import java.io.File;

public class Test {
	
	public static void main(String [] args) {
		File file = new File("C:/Users/Marcus/Desktop/patronTest.txt");
		PatronDB db = new PatronDB();
		db.importFromFile(file);
		System.out.println(db.toString());
		
	}
	
	
}
