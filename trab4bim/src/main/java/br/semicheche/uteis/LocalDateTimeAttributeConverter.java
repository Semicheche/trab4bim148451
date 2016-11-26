package br.semicheche.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 
 * @author lucianosemicheche
 * 
 * Classe que converte a hora do banco para a hora da JPA {@link LocalDateTime} e vice-versa
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp>{
	
	/**
	 * Metodo de conversão do tempo
	 * 
	 *recebe um @param LocalDateTime e
	 * 
	 * @return TimeStamp
	 */
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);
 
    	return null;
 
	}
	
	/**
	 * Metodo de conversão do tempo
	 * 
	 * recebe um @param TimeStamp e
	 * 
	 * @return localDateTime
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		
		if(timestamp != null)
    		return timestamp.toLocalDateTime();
		
		return null;
	}

}
