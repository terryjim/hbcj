package cn.com.topit.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

//处理null值为""
public class MyJsonSerializer  extends JsonSerializer<Object> {  
  
    @Override  
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)  
            throws IOException, JsonProcessingException {  
    	
//        jgen.writeString("");  
        jgen.writeString("");  
    }  
  
}  


