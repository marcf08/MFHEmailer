package marcus.email.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import marcus.email.database.Patron;

/**
 * This simple bean contains an email object and methods for retrieving it.
 * @author Marcus
 */
public class EmailTemplate {
	//Each template has a name
	private String name;
	private String htmlContent;
	
	/**
	 * This is the simple null constructor
	 */
	public EmailTemplate() {
	}
	
	public EmailTemplate(String name, String htmlContent) {
		this.name = name;
		this.htmlContent = htmlContent;
	}
	
	/**
	 * This method returns a copy of the template with the patron
	 * information added (if desired by the user). It uses JSoup to
	 * parse the html content and modify existing tags.
	 * @param patron the patron whose information will be used
	 * @return the string with content added.
	 */
	public String addPatronInformation(Patron patron) {
		Document doc = Jsoup.parse(htmlContent);

		//Replace first name
		Elements varsFirst = doc.select("var#firstName");
		for (Element element : varsFirst) {
			element.text(patron.getFirstName());
		}
		
		//Replace last name
		Elements varsLast = doc.select("var#lastName");
		for (Element element : varsLast) {
			element.text(patron.getLastName());
		}
		
		//Replace the date since
		Elements varsDateAdded = doc.select("var#dateAdded");
		for (Element element : varsDateAdded) {
			element.text(patron.getPatronSince().toString());
		}
		
		//Replace the birthday
		Elements varsBirthday= doc.select("var#birthday");
		for (Element element : varsDateAdded) {
			element.text(patron.getPatronDOB().toString());
		}
		
		//Replace the anniversary
		Elements varsAnniv= doc.select("var#anniv");
		for (Element element : varsAnniv) {
			element.text(patron.getAnniv().toString());
		}
		
		return doc.html();
		
}
		
		
	

	//Getters and setters below
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	

}
