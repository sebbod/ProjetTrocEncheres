package fr.eni.bids.bll.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import fr.eni.bids.bo.User;
import fr.eni.bids.config.SecurityConfig;

/*
 * Implements methods that help to check whether a request
 * is required to login or not and if this request is
 * appropriate for the role of the user (logged in or not)
 */
public class SecurityUtils {
	
	/*
	 * Check if request require authentication or not
	 * 
	 * Return true if it require authentication
	 * Return false if it don't require authentication
	 */
	public static boolean isSecurityPage(HttpServletRequest request) {
		String urlPattern = UrlPatternUtils.getUrlPattern(request);
		Set<String> roles = SecurityConfig.getAllRoles();

		for(String role: roles) {
			List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
			// Check if requested URL is in urlPatterns that require authentication
			if(urlPatterns != null && urlPatterns.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Check if request should be allowed or not
	 * 
	 * Return true if it's allowed
	 * Return false if it's not allowed
	 */
	public static boolean hasPermission(HttpServletRequest request) {
		String urlPattern = UrlPatternUtils.getUrlPattern(request);
		Set<String> roles = SecurityConfig.getAllRoles();
		
		for(String role: roles) {
			// If user doesn't have this role then skip that role
			if(!request.isUserInRole(role)) {
				continue;
			}
			
			// If user have this role then check if he's allowed based on that role
			List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
			if(urlPatterns != null && urlPatterns.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get roles of user
	 * 
	 * @param	user	concerned user
	 * @return			all user roles (contain null if neither role)
	 */
	public static List<String> getUserRoles(User user) {
		List<String> userRoles = new ArrayList<String>();
		if(user != null) {
			if(user.getIsAdmin() == 1) {
				userRoles.add(SecurityConfig.ROLE_USER);
				userRoles.add(SecurityConfig.ROLE_ADMIN);
			} else {
				userRoles.add(SecurityConfig.ROLE_USER);
			}
		} else {
			userRoles.add(null);
		}
		return userRoles;
	}
}
