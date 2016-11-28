package br.semicheche.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author lucianosemicheche
 * 
 * Servlet Filter implementation class JPAFilter
 * 
 * JPAFilter sera ultilizado para verificar qualquer requisição para o faces servlets.	
 * 
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;
	
	private String persistence_unit_name = "unit_app";


	/**
	 * @see Filter#destroy()
	 * esse metodo finaliza o entityManagerFactory
	 */
	public void destroy() {
		this.entityManagerFactory.close();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * @param request recebeu uma requisição 
	 * @param response recebeu um response
	 * @param chain recebe uma cadeia
	 * 
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		
	    request.setAttribute("entityManager", entityManager);
	    
		entityManager.getTransaction().begin();
		
		chain.doFilter(request, response);
	
		try{
		
			entityManager.getTransaction().commit();
			
		} catch (Exception e){
		
			entityManager.getTransaction().rollback();
		}
		finally {
			
			entityManager.close();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 * 
	 * Add ao entityMangerFactory uma string que e um nome {@link #persistence_unit_name}
	 * 
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
		
	}

}
