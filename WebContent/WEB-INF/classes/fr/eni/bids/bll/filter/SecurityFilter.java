package fr.eni.bids.bll.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bids.bo.User;
import fr.eni.bids.bll.utils.AppUtils;
import fr.eni.bids.bll.utils.SecurityUtils;
import fr.eni.bids.bll.utils.UserRoleRequestWrapper;

@WebFilter("/*")
public class SecurityFilter implements Filter {
	public SecurityFilter() {
		
	}
	
	@Override
	public void destroy() {	
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String servletPath = request.getServletPath();
		User connectedUser = AppUtils.getConnectedUser(request.getSession());
		
		// If login page, then invoke next filter in the chain
		if(servletPath.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest wrapRequest = request;
		
		// Wrap original request to a new one with added informations (username and roles)
		if(connectedUser != null) {
			List<String> userRoles = SecurityUtils.getUserRoles(connectedUser);
			wrapRequest = new UserRoleRequestWrapper(connectedUser.getPseudo(), userRoles, request);
		}
		
		// If request need authentication, check user permissions
		if(SecurityUtils.isSecurityPage(request)) {
			
			// If not logged in, redirect to login page
			if(connectedUser == null) {
				String requestUri = request.getRequestURI();

				// Store page to redirect to after logged in
				int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

				response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
				return;
			}
			
			// Check user permissions
			if(!SecurityUtils.hasPermission(wrapRequest)) {
				// INSERT ERROR HERE
				request.setAttribute("lstErrorCode", "");
				
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/views/ErrorPage.jsp");
				rd.forward(wrapRequest, response);
				return;
			}
		}
		
		// Invoke next filter in the chain
		chain.doFilter(wrapRequest, response);
		
	}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}
	
	
}
