package br.com.ggdio.templating;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import br.com.ggdio.templating.test.App;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

final class FTLTemplateEngine extends TemplateEngine {
	
	private final Configuration cfg;
	
	FTLTemplateEngine() { 
		String dir = getProperty("dir.template", "/templates/ftl");
		try {
			Configuration cfg = new Configuration(Configuration.getVersion());
			cfg.setDirectoryForTemplateLoading(new File(App.class.getResource(dir).toURI()));
			cfg.setDefaultEncoding(getProperty("encoding", "UTF-8"));
			cfg.setTemplateExceptionHandler(getProperty("handler.exception", TemplateExceptionHandler.RETHROW_HANDLER));
			cfg.setLogTemplateExceptions(getProperty("log.exceptions", false));
			
			this.cfg = cfg;
			
		} catch(Exception e) {
			throw new TemplateException(String.format("Template dir not found '%s'", dir));
			
		}
	}

	@Override
	public String render(String reference, Object model) {
		try(Writer out = new StringWriter();) {
			Template template = cfg.getTemplate(reference);
			template.process(model, out);
			
			return out.toString();
			
		} catch(Exception e) {
			throw new TemplateException(String.format("Failed to render '%s' due to '%s': '%s'", reference, e.getCause(), e.getMessage()));
		}
	}

}
