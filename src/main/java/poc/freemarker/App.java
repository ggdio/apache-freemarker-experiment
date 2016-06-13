package poc.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import poc.freemarker.model.User;

/**
 * Hello world!
 *
 */
public class App {
	
	static List<User> users = new ArrayList<>();;
	static {
		users.add(getUser("Martin Fowler", 52, "North Dakota", "Jamestown"));
		users.add(getUser("Mikey Mouse", 100, "United States", "Disney"));
		users.add(getUser("Darh Vader", 70, "Death Star", "Sector 29"));
	}
	
	public static final User getUser(String name, int age, String state, String city) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setState(state);
		user.setCity(city);
		
		return user;
	}
	
	public static void main(String[] args) throws IOException, TemplateException, URISyntaxException {
		Map<String, Object> map = new HashMap<>();
		map.put("users", users);
		
		String generate = generate(map);
		
		System.out.println("#users.json#");
		System.out.println(generate);
	}
	
	public static String generate(Object model) throws IOException, TemplateException, URISyntaxException {
		Writer out = null;
		try {
			Configuration cfg = new Configuration(Configuration.getVersion());
			cfg.setDirectoryForTemplateLoading(new File(App.class.getResource("/templates/json/").toURI()));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			
			out = new StringWriter();
			Template template = cfg.getTemplate("users.json");
			template.process(model, out);
			
			return out.toString();
		} finally {
			
		}
	}
	
	
}
