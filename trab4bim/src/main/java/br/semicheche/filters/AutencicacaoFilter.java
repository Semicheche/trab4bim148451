package br.semicheche.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.semicheche.model.UsuarioModel;

/**
 * @author lucianosemicheche
 * 
 * Servlet Filter implementation class AutencicacaoFilter
 * 
 */
@WebFilter("/sistema/*")
public class AutencicacaoFilter implements Filter {


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 
	 * @param request recebe uma requisição
	 * @param response recebe um response
	 * @param chain recebe uma cadeia
	 *  
	 * esse metodo faz o filtro do que e recevido das view da aplicação
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession httpSession 				= ((HttpServletRequest) request).getSession(); 
 
		HttpServletRequest httpServletRequest   = (HttpServletRequest) request;
 
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
 
		if(httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1){
 
			UsuarioModel usuarioModel =(UsuarioModel) httpSession.getAttribute("usuarioAutenticado");
 
			if(usuarioModel == null){
 
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/index.xhtml");
 
			}
			else{
 
				chain.doFilter(request, response);
			}
		}		
		else{
 
			chain.doFilter(request, response);
		}

	}
	/**
	 * metodo add obrigatoriamente 
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
