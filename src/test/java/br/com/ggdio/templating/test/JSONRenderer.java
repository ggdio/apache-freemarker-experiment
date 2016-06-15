package br.com.ggdio.templating.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ggdio.templating.TemplateEngine;
import br.com.ggdio.templating.test.model.User;
import junit.framework.TestCase;

public class JSONRenderer extends TestCase {
	
	public void testListOfUsersWithBinding() {
		List<User> users = new ArrayList<>();
		users.add(getUser("Martin Fowler", 52, "North Dakota", "Jamestown"));
		users.add(getUser("Mikey Mouse", 100, "United States", "Disney"));
		users.add(getUser("Darh Vader", 70, "Death Star", "Sector 29"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("users", users);
		String actual = TemplateEngine.getInstance().render("users.ftl", map).replaceAll("\\s", "").toLowerCase();
		String expected = "[{name:\"martinfowler\",age:52,state:\"northdakota\",city:\"jamestown\"},{name:\"mikeymouse\",age:100,state:\"unitedstates\",city:\"disney\"},{name:\"darhvader\",age:70,state:\"deathstar\",city:\"sector29\"}]";
		assertEquals(expected, actual);
	}

	public void testSingleUserWithoutBinding() {
		User user = getUser("Martin Fowler", 52, "North Dakota", "Jamestown");
		
		String expected = "{name:\"martinfowler\",age:52,state:\"northdakota\",city:\"jamestown\"}";
		String actual = TemplateEngine.getInstance().render("user.ftl", user).replaceAll("\\s", "").toLowerCase();
		assertEquals(expected, actual);
	}
	
	public void testSingleUserWithBinding() {
		User user = getUser("Martin Fowler", 52, "North Dakota", "Jamestown");
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("user", user);
		
		String expected = "{name:\"martinfowler\",age:52,state:\"northdakota\",city:\"jamestown\"}";
		String actual = TemplateEngine.getInstance().render("user.ftl", bindings).replaceAll("\\s", "").toLowerCase();
		assertEquals(expected, actual);
	}
	
	private static final User getUser(String name, int age, String state, String city) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setState(state);
		user.setCity(city);
		
		return user;
	}
	
}
