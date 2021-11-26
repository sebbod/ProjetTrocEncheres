package fr.eni.bids.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Used to configure roles and which pages they're allowed to access
 */
public class SecurityConfig {

	// Connected user role
	public static final String ROLE_USER = "USER";

	// Connected admin role
	public static final String ROLE_ADMIN = "ADMIN";

	/*
	 *  String: Role
	 *  List<String>: urlPatterns
	 */
	private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

	static {
		init();
	}

	private static void init() {
		// Configuration for USER role
		List<String> userUrlPatterns = new ArrayList<String>();
		userUrlPatterns.add("/logout");
		userUrlPatterns.add("/user/profil");
		//userUrlPatterns.add("/user/register");
		mapConfig.put(ROLE_USER, userUrlPatterns);

		// Configuration for ADMIN role
		List<String> adminUrlPatterns = new ArrayList<String>();
		adminUrlPatterns.add("/logout");
		adminUrlPatterns.add("/user/profil");
		//adminUrlPatterns.add("/user/register");
		mapConfig.put(ROLE_ADMIN, adminUrlPatterns);
	}

	/**
	 * Get all roles
	 * 
	 * @return all roles
	 */
	public static Set<String> getAllRoles() {
		return mapConfig.keySet();
	}

	/**
	 * Get URL patterns for a role
	 * 
	 * @param role
	 *            concerned role
	 * @return all URL patterns for a role
	 */
	public static List<String> getUrlPatternsForRole(String role) {
		return mapConfig.get(role);
	}

}
