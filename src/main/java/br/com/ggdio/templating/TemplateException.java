package br.com.ggdio.templating;

public class TemplateException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TemplateException(String message) {
		super(message);
	}
	
	public TemplateException(String message, Throwable cause) {
		super(message, cause);
	}

}