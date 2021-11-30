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

import fr.eni.bids.bll.utils.AppUtils;
import fr.eni.bids.bll.utils.SecurityUtils;
import fr.eni.bids.bll.utils.UserRoleRequestWrapper;
import fr.eni.bids.bo.User;

@WebFilter("/*")
public class SecurityFilter implements Filter {
	public SecurityFilter() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String servletPath = request.getServletPath();
		User connectedUser = AppUtils.getConnectedUser(request.getSession());
		
		// If logged in
		if(connectedUser != null) {
			//	If login/register page redirect to homepage
			if(servletPath.equals("/login") || servletPath.equals("/user/register")) {
				response.sendRedirect(request.getContextPath() + "/");
				
			// Else check other conditions
			} else {
				HttpServletRequest wrapRequest = request;

				// Wrap original request to a new one with added informations (username and roles)
				List<String> userRoles = SecurityUtils.getUserRoles(connectedUser);
				wrapRequest = new UserRoleRequestWrapper(connectedUser.getPseudo(), userRoles, request);

				// If request need authentication, check user permissions
				if (SecurityUtils.isSecurityPage(request)) {

					// Check user permissions
					if (!SecurityUtils.hasPermission(wrapRequest)) {
						// TODO: INSERT ERROR HERE
						request.setAttribute("lstErrorCode", "");

						RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/views/ErrorPage.jsp");
						rd.forward(wrapRequest, response);
					
					} else {
						// Invoke next filter in the chain
						chain.doFilter(wrapRequest, response);
					}
				} else {
					// Invoke next filter in the chain
					chain.doFilter(wrapRequest, response);
				}
			}
			
		// Else, if request need authentication redirect to login page
		} else if(SecurityUtils.isSecurityPage(request)) {
			String requestUri = request.getRequestURI();

			// Store page to redirect to after logged in
			int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

			response.sendRedirect(request.getContextPath() + "/login?redirectId=" + redirectId);
			
		// Else invoke next filter in the chain
		} else {
			// Invoke next filter in the chain
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
