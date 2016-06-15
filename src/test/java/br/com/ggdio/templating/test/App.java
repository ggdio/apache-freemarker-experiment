package br.com.ggdio.templating.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ggdio.templating.TemplateEngine;
import br.com.ggdio.templating.test.model.User;
import freemarker.template.TemplateException;

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
		
		System.out.println("#users.ftl#");
		System.out.println(TemplateEngine.getInstance().render("users.ftl", map));
	}
	
}
