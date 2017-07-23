package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonStringIntegerSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize (Integer value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
    	
    	String data = "No";
    	
    	
    	if(value != null){
    		if(value.intValue()== 1){
    			data = "Si";	
    		}
    		
    	}
    	
        gen.writeString(data);
    }
}