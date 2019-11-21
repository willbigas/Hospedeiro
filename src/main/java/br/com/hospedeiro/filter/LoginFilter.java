package br.com.hospedeiro.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.xhtml"})
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String requestURI = req.getRequestURI();

        if (requestURI.contains("/login.xhtml") || !requestURI.contains("/paginas/") || (session != null && session.getAttribute("usuarioLogado") != null)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String novaURI = req.getContextPath() + "/login.xhtml";
            resp.sendRedirect(novaURI);
        }
    }


    @Override
    public void destroy() {

    }
}
