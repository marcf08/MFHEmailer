package marcus.email.util;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This email storages the email templates.
 * @author Marcus
 *
 */
public class EmailStorage {
	//This is the main data member that stores an array of templates
	public ArrayList<EmailTemplate> templates;

	/**
	 * The base constructor instantiates the array list.
	 */
	public EmailStorage() {
		templates = new ArrayList<EmailTemplate>();
	}
	
	/**
	 * This method adds the template to the list.
	 * @param html
	 */
	public void addTemplate(String templateName, String html, String subject) {
		EmailTemplate t = new EmailTemplate(templateName, html, subject);
		if (isUniqueTemplate(t)) {
			templates.add(t);
		} else {
			throw new IllegalArgumentException("Already exists");
		}
	}
	
	/**
	 * This method ensures there are no matching email templates.
	 * @param t the email template to check
	 * @return true if the template is unique and false otherwise
	 */
	public boolean isUniqueTemplate(EmailTemplate t) {
		if (templates.contains(t)) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method gets the existing array of templates.
	 * @return the list of template names
	 */
	public String[] getTemplateNames() {
		String[] names = new String[templates.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = templates.get(i).getName();
		}
		return names;
	}
	
	/**
	 * This method allows the client to modify a specific template from the list.
	 * @param name the name of the template to modify
	 * @param html the new html content
	 * @return true if the operation was successful and false otherwise
	 */
	public boolean modifyTemplateByName(String name, String html,  String subject) {
		for (int i = 0; i < templates.size(); i++) {
			if (templates.get(i).getName().equals(name)) {
				templates.get(i).setHtmlContent(html);
				templates.get(i).setSubject(subject);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns the html given a template name.
	 * @param name the name of the template
	 * @return the html content given a template name
	 */
	public String extractContent(String name) {
		for (int i = 0; i < templates.size(); i++) {
			if (templates.get(i).getName().equals(name)) {
				return templates.get(i).getHtmlContent();
			}
		}
		throw new NoSuchElementException();
	}
	
	/**
	 * This method deletes a template given it's name.
	 * @param name the name of the template to delete.
	 */
	public void deleteTemplateByName(String name) {
		for (int i = 0; i < templates.size(); i++) {
			if (templates.get(i).getName().equals(name)) {
				templates.remove(i);
			}
		}
	}
	
	/**
	 * Returns the list of all templates.
	 * @return an array of all templates
	 */
	public EmailTemplate[] getAll() {
		EmailTemplate [] et = new EmailTemplate[size()];
		for (int i = 0; i < size(); i++) {
			et[i] = get(i);
		}
		return et;
	}

	/**
	 * Returns the template at the given position in the array
	 * @param i the position of the template
	 * @return the given template at position i
	 */
	public EmailTemplate get(int i) {
		return templates.get(i);
	}
	
	
	/**
	 * Returns the template with the given name or null if it does not exist
	 * @param name the template name
	 * @return the template object of the parameterized name
	 */
	public EmailTemplate getTemplateByName(String name) {
		for (int i = 0; i < templates.size(); i++) {
			if (templates.get(i).getName().equals(name)) {
				return templates.get(i);
			}
		}
		return null;
	}
	
	/**
	 * This method retrieves the template with a given template
	 * @param templateName the name of the template
	 * @return the template matching the name or null if it was not found
	 */
	public EmailTemplate getTemplate(String templateName) {
		for (int i = 0; i <= templates.size(); i++) {
			if (templates.get(i).getName().equals(templateName)) {
				return templates.get(i);
			}
		}
		return null;
	}
	
	/**
	 * This method simply returns the size of the templates array.
	 */
	public int size() {
		return templates.size();
	}
}
