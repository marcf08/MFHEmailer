package marcus.email.GUI;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import marcus.email.database.Patron;
import marcus.email.util.EmailTemplate;

public class Test {
	public static void main(String [] args) throws FileNotFoundException {
		Patron test = new Patron("bctest@email.com");
		test.setFirstName("Bill");
		test.setLastName("Clinton");

		File htmlFile = new File("C:/Users/Marcus/Desktop/MFHEmailer Docs and Packages/testHtml.txt");
		Scanner scan = new Scanner(htmlFile);
		
		String htmlContent = null;
		while (scan.hasNextLine()) {
			htmlContent += scan.nextLine();
		}
		
		EmailTemplate t = new EmailTemplate("test template", htmlContent);
		
		System.out.println(t.addPatronInformation(test));

	}
}