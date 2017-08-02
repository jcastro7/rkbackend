package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonByteFlagSerializer extends JsonSerializer<byte[]> {

    @Override
    public void serialize (byte[] value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
    	
    	boolean data = false;
    	
    	
    	if(value != null && value.length>0){
    		if(value[0]== 1){
    			data = true;	
    		}
    		
    	}
    	
    	gen.writeBoolean(data);
    }
}