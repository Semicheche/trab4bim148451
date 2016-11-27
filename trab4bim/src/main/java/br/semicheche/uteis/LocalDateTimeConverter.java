package br.semicheche.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;

/**
 * @author lucianosemicheche
 *
 *Clase utilizada para Formartar a hora do LocalDateTime
 *
 */
public class LocalDateTimeConverter extends DateTimeConverter {
	
	public static final String ID="br.semicheche.uteis.LocalDateTimeConverter"; 
	
	/**
	 * metodo sebrescrito da classe DateTimeConverter
	 * 
	 * @param facesContext, uiComponent, value
	 * 
	 * @return Object localDateTime
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
 
		Date date = null;
		LocalDateTime localDateTime = null;
 
		Object object = super.getAsObject(facesContext, uiComponent, value);
 
		if(object instanceof Date){
 
			date = (Date) object;
 
			Instant instant = Instant.ofEpochMilli(date.getTime());
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	  		return localDateTime;
 
		}
		else{
 
			throw new IllegalArgumentException(String.format("value=%s Não foi possível converter LocalDateTime, resultado super.getAsObject=%s",value,object));			
		}			 
 
 
	}
	
	/**
	 * methodo retorna a para superClasse um Date
	 * 
	 * @param facesContext, uiComponent, Object
	 * 
	 * @return facesContext, uiComponent, date
	 * 
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
 
		  if(value == null)
			  return super.getAsString(facesContext, uiComponent, value);
 
		  if(value instanceof LocalDateTime){
 
			  LocalDateTime localDateTime = (LocalDateTime) value;
 
			  Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
 
			  Date  date =  Date.from(instant);
 
			  return super.getAsString(facesContext, uiComponent, date);
		  }
		  else{
 
			  throw new IllegalArgumentException(String.format("value=%s não é um LocalDateTime",value));  
		  }
 
	  }

}
