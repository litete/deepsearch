package app.home.Singleton;


import app.home.function.CosineDistance;
import app.home.model.ArctileFew;
import org.codehaus.jackson.map.ObjectMapper;

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
public static  ArctileFew getArctileFew(){
	return arctileFew;
}
}
