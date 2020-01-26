package no.comp.finalproject.configuration;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebApplicationContext.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);
    filter.setForceRequestEncoding(true);
    filter.setForceResponseEncoding(true);
    return new Filter[] { filter };
  }

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		String TMP_FOLDER = "";
    int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;
    
    MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, 
        MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
       
    registration.setMultipartConfig(multipartConfigElement);
	}

}
