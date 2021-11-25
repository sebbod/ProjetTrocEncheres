package fr.eni.bids.bll.utils;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;

/*
 * Implements methods that help to works with URL patterns
 */
public class UrlPatternUtils {
	
	/*
	 * Check if any registered servlet (based on ServletContext)
	 * has the specified URL pattern
	 * 
	 * Return true if any registered servlet has the specified URL pattern
	 * Return false if neither registered servlet has the specified URL pattern
	 */
	private static boolean hasUrlPattern(ServletContext servletContext, String urlPattern) {
		Map<String, ? extends ServletRegistration> servletRegistrations = servletContext.getServletRegistrations();
		
		for(String servletName: servletRegistrations.keySet()) {
			ServletRegistration sr = servletRegistrations.get(servletName);
			Collection<String> mappings = sr.getMappings();
			
			if(mappings.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Get URL pattern based on request
	 * 
	 * Return URL pattern as String
	 */
	public static String getUrlPattern(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String urlPattern = null;
		
		if(pathInfo != null) {
			urlPattern = servletPath + "/*";
			return urlPattern;
		}
		
		urlPattern = servletPath;
		boolean has = hasUrlPattern(servletContext, urlPattern);
		
		if(has) {
			return urlPattern;
		}
		
		int i = servletPath.lastIndexOf('.');
		
		if(i != -1) {
			String ext = servletPath.substring(i + 1);
			urlPattern = "*." + ext;
			has = hasUrlPattern(servletContext, urlPattern);
			
			if(has) {
				return urlPattern;
			}
		}
		return "/";
	}
	
}
