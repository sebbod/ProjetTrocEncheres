package fr.eni.bids.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bo.User;

public class AppUtils {
	private static int REDIRECT_ID = 0;
	private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
	private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();
	
	/**
	 * Store URL to redirect after login
	 * 
	 * @param	session		Concerned session
	 * @param	requestUri	Requested uri
	 * @return				Redirect id
	 */
	public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
		Integer id = uri_id_map.get(requestUri);
		if(id == null) {
			id = REDIRECT_ID++;
			uri_id_map.put(requestUri, id);
			id_uri_map.put(id, requestUri);
			return id;
		}
		return id;
	}
	
	/**
	 * Get URL to redirect to after login by redirect id
	 * 
	 * @param	session		Concerned session
	 * @param	redirectId	Redirect id
	 * @return				URL to redirect to after login
	 */
	public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
		String url = id_uri_map.get(redirectId);
		if(url != null) {
			return url;
		}
		return null;
	}
	
	/**
	 * Get connected user
	 * 
	 * @param	session	Concerned session
	 * @return			Connected user
	 */
	public static User getConnectedUser(HttpSession session) {
		UserManager uMngr;
		User loggedUser = null;
		try {
			uMngr = new UserManager();
			Integer connectedUserId = (Integer) session.getAttribute("connectedUserId");
			if(connectedUserId != null) {
				loggedUser = uMngr.getById(connectedUserId);
			}
		} catch (BidsException e) {
			e.printStackTrace();
		}
		return loggedUser;
	}
}
