package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class JsonByteFlagDeserializer extends JsonDeserializer<byte[]> {

    @Override
    public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    	
        
    	boolean blobAsString = p.getBooleanValue();
        try {
            if (blobAsString) {
                return  new byte[] {1};
            }
            
            return  new byte[] {0};
        }
        catch (Exception pe) {
            throw new RuntimeException(pe);
        }
    }

}