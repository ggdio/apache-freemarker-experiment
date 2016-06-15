package br.com.ggdio.templating;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>Template Engine</b>
 * <p> Abstraction for template generation engine 
 * @author Dio
 *
 */
public abstract class TemplateEngine {

	private static TemplateEngine instance;
	
	protected static Map<String, Object> properties = new HashMap<>();
	
	protected TemplateEngine() { }
	
	public static final TemplateEngine getInstance() {
		if(instance == null) {
			instance = getDefault();
		}
		
		return instance;
	}
	
	public static void setProperty(String name, Object value) {
		properties.put(name, value);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getProperty(String key, T nvl) {
		Object value = properties.get(key);
		
		return value != null ? (T) value : nvl;
	}
	
	public static void reload() {
		instance = getDefault();
	}
	
	private static final TemplateEngine getDefault() {
		return new FTLTemplateEngine();
	}
	
	/**
	 * 
	 * @param template - The template reference
	 * @param model - The data model 
	 * @return A rendered document
	 * @throws TemplateException - if something unusual happens while rendering the template
	 */
	public abstract String render(String template, Object model) throws TemplateException;
	
}
