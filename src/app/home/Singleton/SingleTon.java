package app.home.Singleton;


import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import app.home.function.CosineDistance;
import app.home.model.ArctileFew;
import app.home.model.ArctileLittle;

public class SingleTon {
private static final ObjectMapper objectMapper=new ObjectMapper();
private static final CosineDistance cosineDistance=new CosineDistance();
private static final StringBuffer buffer=new StringBuffer();
private static final ArctileFew arctileFew=new ArctileFew();

public static ObjectMapper getObjectMapper(){
	return objectMapper;
}
public static CosineDistance getcosineDistance (){
	return cosineDistance;
}
public static StringBuffer getStringBuffer(){
	return buffer;
}
public static  ArctileFew getArctileFew(){
	return arctileFew;
}
}
