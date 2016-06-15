Template Engine
======
A simple facade for multiple template engines(eg.: freemarker, mustache, jtwig, etc...)

## Usage(freemarker)
 * FTL('classpath:/templates/ftl/person-list.ftl'):
   ```
   [#ftl]
   {
  	"name": "${(person.name)!name}",
  	"birthday": "${(person.birthday?date)!birthday?date}",
  	"location": "${(person.location)!location}"
  }
  ```

 * Java:
  ```
  // Create person model
  Person person = new Person("Guilherme Dio", LocalDate.of(1992, 11, 27), "São Paulo");
  
  // Render and write to sysout
  System.out.println(TemplateEngine.getInstance().render("person-list.ftl", person));
  ```

 * sysout:
  ```
  {
    "name": "Guilherme Dio",
    "birthday": "11/27/1992",
    "location": "São Paulo"
  }
  ```

## Contributors

### Contributors on GitHub
* [Contributors](https://github.com/ggdio/template-engine/graphs/contributors)

### Third party libraries
* Apache Freemarker
* Mustache
* JTwig

## Version 
* Version 1.0

## Contact
#### Developer/Company
* Homepage: [guilhermedio.com](http://guilhermedio.com)
* e-mail: ggrdio@gmail.com
