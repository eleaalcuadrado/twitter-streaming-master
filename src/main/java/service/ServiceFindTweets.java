package service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date; 
import java.util.Random;
import javax.ws.rs.PathParam;
 
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Actor;
import model.Servicio;
import model.Datos;
import model.Coordenadas;

import model.DatosCoordenadas;

@Path("/tweet")
@ApplicationPath("/")
@WebService
public class ServiceFindTweets {
	MongoClient mongo = new MongoClient("localhost", 27017);
    DB db = mongo.getDB("twitterdb");
    DBCollection collection = db.getCollection("todo");
    @GET
    @Produces({"application/json"})
    @WebMethod
	public String obtenerDatos(){ 
		
		BasicDBObject fields = new BasicDBObject();
		DBCursor cursor = collection.find();
		List<DBObject> list = new ArrayList<DBObject>();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
        return list.toString();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/json"})
    public String buscarPorID(@PathParam("id") String id) {
       // MongoClient mongo = new MongoClient("localhost", 27017);
        //DB db = mongo.getDB("twitterdb");
        //DBCollection collection = db.getCollection("todo");
    	List<DBObject> list = new ArrayList<DBObject>();
        BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("id", id );
	
		BasicDBObject fields = new BasicDBObject();

		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
        return list.toString();
    }
	
	
	
	
	@GET
    @Path("fecha/{fecha}")
    @Produces({"application/json"})
    public String buscarPorFecha(@PathParam("fecha") String fecha) {
        //MongoClient mongo = new MongoClient("localhost", 27017);
        //DB db = mongo.getDB("twitterdb");
        //DBCollection collection = db.getCollection("todo");
    	List<DBObject> list = new ArrayList<DBObject>();
        BasicDBObject whereQuery = new BasicDBObject();

        String[] corte = fecha.split("-");
		String mes = corte[0];
		String dia = corte[1];
		String anho = corte[2];
		
		
		whereQuery.put("created_at", 
		new BasicDBObject("$regex", String.format(".*((?i)%s %s).*((?i)%s).*",mes,dia,anho)) );
	
		BasicDBObject fields = new BasicDBObject();

		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
        return list.toString();
    }
	@GET
    @Path("fecha/{fecha}/cantidad")
    @Produces({"application/json"})
    public String buscarPorFecha_cantidad(@PathParam("fecha") String fecha) {
        //MongoClient mongo = new MongoClient("localhost", 27017);
        //DB db = mongo.getDB("twitterdb");
        //DBCollection collection = db.getCollection("todo");
    	List<DBObject> list = new ArrayList<DBObject>();
        BasicDBObject whereQuery = new BasicDBObject();

        String[] corte = fecha.split("-");
		String mes = corte[0];
		String dia = corte[1];
		String anho = corte[2];
		
		
		whereQuery.put("created_at", 
		new BasicDBObject("$regex", String.format(".*((?i)%s %s).*((?i)%s).*",mes,dia,anho)) );
	
		BasicDBObject fields = new BasicDBObject();

		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
        return Integer.toString(list.size());
    }
	@GET
    @Path("compania/{compania}")
    @Produces({"application/json"})
    public String buscarPorCompania(@PathParam("compania") String compania) {
		if(compania.equals("entel")|| compania.equals("movistar")|| compania.equals("vtr")|| compania.equals("wom")|| compania.equals("virgin")|| compania.equals("claro")){
			//MongoClient mongo = new MongoClient("localhost", 27017);
			//DB db = mongo.getDB("twitterdb");
			//DBCollection collection = db.getCollection("todo");
			List<DBObject> list = new ArrayList<DBObject>();
			BasicDBObject whereQuery = new BasicDBObject();

			whereQuery.put("text", 
			new BasicDBObject("$regex", String.format(".*((?i)%s).*",compania)) );
		
			BasicDBObject fields = new BasicDBObject();

			DBCursor cursor = collection.find(whereQuery);
			while (cursor.hasNext()) {
				list.add(cursor.next());
			}
			return list.toString();
		}
		else{
			return compania;
		}
    }

@GET
@Path("algo")
@Produces({"application/xml","application/json"})
  public List<Actor> algo() {
  	Actor actor= new Actor();
  	actor.setFirstName("lalalaal");
  	List<Actor> lista= new ArrayList<Actor>();
  	lista.add(actor);
  	return lista;

  }


@GET
@Path("coordenada/{compania}")
@Produces({"application/xml","application/json"})
public List<DatosCoordenadas> obtenerCoordenadas( @PathParam("compania") String compania) {
				List<String> lista2 = new ArrayList<String>();
				List<String> listaAux = new ArrayList<String>();
				List<DBObject> list = new ArrayList<DBObject>();
				BasicDBObject whereQuery = new BasicDBObject();
				
				List<DatosCoordenadas> listaCoor= new ArrayList<DatosCoordenadas>();
				int i;
        		whereQuery.put("text", 
				new BasicDBObject("$regex", String.format(".*((?i)%s).*",compania)) );
			
				BasicDBObject fields = new BasicDBObject();
				i=0;
				DBCursor cursor = collection.find(whereQuery);
				while (cursor.hasNext()) {
					DatosCoordenadas datosC= new DatosCoordenadas();
					
					Coordenadas coor= new Coordenadas();
					BasicDBObject obj = (BasicDBObject) cursor.next();
	    			
	    			JSONObject obj2 = new JSONObject(obj);
					boolean result = obj2.has("place");
				    //String people = Integer.toString(result.getString("coordinates"));
					//lista2.add(obj2.toString());
					if (result==true){
						JSONObject lugar= obj2.getJSONObject("place");
						JSONObject dato= lugar.getJSONObject("bounding_box");
						JSONArray coordenadas= dato.getJSONArray("coordinates");
						
						JSONArray value1 = coordenadas.getJSONArray(0);
						JSONArray value = value1.getJSONArray(0);
						double a=value.getDouble(0);
						double b=value.getDouble(1);
						coor.setLatitude(a);
						coor.setLongitude(b);
						//val.add(coor);
						//datosC.setId(i);
						//datosC.setCoords(coor);
						datosC.setId(i);

						//lista2.add(Double.toString(a)+","+Double.toString(b));
					}
					else{
						//JSONObject lugar= obj2.getJSONObject("user");
						//String locacion= lugar.getString("location");
						Random r1 = new Random();
						double randomValue1 = 70.6 + (70.7 - 70.6) * r1.nextDouble();
						double rValA=randomValue1*(-1);
						Random r2 = new Random();
						double randomValue2 = 33.4 + (33.5 - 33.4) * r2.nextDouble();
						double rValB=randomValue2*(-1);
						coor.setLatitude(rValA);
						coor.setLongitude(rValB);
						//val.add(coor);
						datosC.setId(i);
						
						//lista2.add(Double.toString(rValB));
					}
					
					datosC.setCoords(coor);
	    			listaCoor.add(datosC);
	    			i=i+1;
				}
			
        return listaCoor;

}

    public List<String> obtenerTexto( String compania, String fecha) {
		List<String> lista2 = new ArrayList<String>();
		List<String> listaAux = new ArrayList<String>();
		List<DBObject> list = new ArrayList<DBObject>();
        BasicDBObject whereQuery = new BasicDBObject();
        

        	String[] corte = fecha.split("-");
			String mes = corte[0];
			String dia = corte[1];
			String anho = corte[2];
			
			
			whereQuery.put("created_at", 
				new BasicDBObject("$regex", String.format(".*((?i)%s %s).*((?i)%s).*",mes,dia,anho)) );
			whereQuery.put("text", 
				new BasicDBObject("$regex", String.format(".*((?i)%s).*",compania)) );
			
				BasicDBObject fields = new BasicDBObject("text",1);

				DBCursor cursor = collection.find(whereQuery,fields);
				while (cursor.hasNext()) {
					//list.add(cursor.next());
					BasicDBObject obj = (BasicDBObject) cursor.next();
	    			lista2.add(obj.getString("text"));

				}
	
			return lista2;
		
    }



	//@GET
    //@Path("sentimientos/{compania}")
    //@Produces({"text/plain"})
    public float obtenerSentimientos( String compania, String fecha) {
    		List<String> malo= new ArrayList<String>();
    		List<String> bueno= new ArrayList<String>();
    		List<String> res= new ArrayList<String>();
    		malo.add("problema");
    		malo.add("@VTRsoporte");
    		malo.add("@WOMteAyuda");
    		malo.add(" @AyudaMovistarCL");
    		malo.add("lento");
    		malo.add("@miclaro_cl");
    		bueno.add("bueno");
    		bueno.add("gracias");
    		bueno.add("buen");
    		bueno.add("felicidades");
    		bueno.add("rapido");
    		bueno.add("agradecer");
    		List<String> textos= obtenerTexto(compania,fecha);
    		int tamano= textos.size();
    		int i,j;
    		float porcentajeNeg=0,porcentajePos=0,porcentajeNeutro=0,resultado=0;
    		String dato;
    		int cantNeg=0, cantPos=0, cantNeutro=0,cantidadPalabras=0;
    		for (i=0;i<tamano;i++){
    			dato=textos.get(i);
    			dato=dato.replace(",","");
    			String[] palabras = dato.split(" ");
    			for (String palabra : palabras)
		        {

		        	for (j=0;j<malo.size();j++){
		        		String negativo= malo.get(j);
		        		String positivo= bueno.get(j);
		        		if (palabra.compareToIgnoreCase(negativo)==0){
		        		 	cantNeg++;
		        		 	res.add(palabra);
		        		 }
		        		 else if (palabra.compareToIgnoreCase(positivo)==0){
		        		 	cantPos++;
		        		 	res.add(palabra);
		        		 }
		        		 else if (palabra.compareToIgnoreCase("el")!=0 && palabra.compareToIgnoreCase("la")!=0 && palabra.compareToIgnoreCase("los")!=0 && palabra.compareToIgnoreCase("las")!=0  && palabra.compareToIgnoreCase("un")!=0 && palabra.compareToIgnoreCase("una")!=0 && palabra.compareToIgnoreCase("unos")!=0 && palabra.compareToIgnoreCase("unas")!=0  ){
		        		 	cantNeutro++;
		        		 }
		        	}
		        	cantidadPalabras++;
		           
		        }
		        porcentajeNeg=(float)cantNeg/cantidadPalabras;
		        porcentajePos=(float)cantPos/cantidadPalabras;
		        porcentajeNeutro=(float)cantNeutro/cantidadPalabras;
		        resultado= (float) porcentajeNeg/2 + porcentajeNeutro/4 + porcentajePos/8;

    		}


    		return resultado;

    }





    @GET
    @Path("sentimientos/fecha/{fecha}")
    @Produces({"text/plain"})
    public String analisisSentimentos(@PathParam("fecha") String fecha){
    		String companias[] = {"movistar", "vtr", "wom", "virgin", "entel","claro"};
    		String company="";
    		String datos="[";
    		int i;
    		for (i=0;i<6;i++){
    			
    			company = companias[i];
    			datos=datos+"{\n\"key\":\""+company+"\",\n\"values\":\n[\n";
    			float resultado= obtenerSentimientos(company,fecha);
    			datos=datos+"{\n\"x\":"+Integer.toString(i)+",\n\"y\":";
				datos=datos+""+Float.toString(resultado)+",\n}";
				datos=datos+"]";

    		}
    		datos=datos+"]";
    		return datos;
    		
    }


	@GET
    @Path("compania/{compania}/cantidad")
    @Produces({"application/json"})
    public String buscarPorCompania_Cantidad(@PathParam("compania") String compania) {
		if(compania.equals("entel")|| compania.equals("movistar")|| compania.equals("vtr")|| compania.equals("wom")|| compania.equals("virgin")|| compania.equals("claro")){
			//MongoClient mongo = new MongoClient("localhost", 27017);
			//DB db = mongo.getDB("twitterdb");
			//DBCollection collection = db.getCollection("todo");
			List<DBObject> list = new ArrayList<DBObject>();
			BasicDBObject whereQuery = new BasicDBObject();

			whereQuery.put("text", 
			new BasicDBObject("$regex", String.format(".*((?i)%s).*",compania)) );
		
			BasicDBObject fields = new BasicDBObject();

			DBCursor cursor = collection.find(whereQuery);
			while (cursor.hasNext()) {
				list.add(cursor.next());
			}
			return Integer.toString(list.size());
		}
		else{
			return "-1";
		}
    }
	@GET
    @Path("compania/{compania}/fecha/{fecha}")
    @Produces({"application/json"})
    public String buscarPorCompaniaFecha(@PathParam("compania") String compania,@PathParam("fecha") String fecha) {
        //MongoClient mongo = new MongoClient("localhost", 27017);
        //DB db = mongo.getDB("twitterdb");
        //DBCollection collection = db.getCollection("todo");
    	List<DBObject> list = new ArrayList<DBObject>();
        BasicDBObject whereQuery = new BasicDBObject();

        String[] corte = fecha.split("-");
		String mes = corte[0];
		String dia = corte[1];
		String anho = corte[2];
		
		
		whereQuery.put("created_at", 
			new BasicDBObject("$regex", String.format(".*((?i)%s %s).*((?i)%s).*",mes,dia,anho)) );
		whereQuery.put("text", 
			new BasicDBObject("$regex", String.format(".*((?i)%s).*",compania)) );
		BasicDBObject fields = new BasicDBObject();

		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
        return list.toString();
    }
	@GET
    @Path("compania/{compania}/fecha/{fecha}/cantidad")
    @Produces({"application/json"})
    public String buscarPorCompaniaFecha_cantidad(@PathParam("compania") String compania,@PathParam("fecha") String fecha) {
        //MongoClient mongo = new MongoClient("localhost", 27017);
        //DB db = mongo.getDB("twitterdb");
        //DBCollection collection = db.getCollection("todo");
    	List<DBObject> list = new ArrayList<DBObject>();
        BasicDBObject whereQuery = new BasicDBObject();

        String[] corte = fecha.split("-");
		String mes = corte[0];
		String dia = corte[1];
		String anho = corte[2];
		
		
		whereQuery.put("created_at", 
			new BasicDBObject("$regex", String.format(".*((?i)%s %s).*((?i)%s).*",mes,dia,anho)) );
		whereQuery.put("text", 
			new BasicDBObject("$regex", String.format(".*((?i)%s).*",compania)) );
		BasicDBObject fields = new BasicDBObject();

		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
        return Integer.toString(list.size());
    }

	

    public Date calcularFecha(Date fecha,int dias){

    	//java.util.Date fecha = new Date();
	
    	Calendar calendar = Calendar.getInstance();
	    calendar.setTime(fecha); // Configuramos la fecha que se recibe	
	    calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
	    return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos	
 	}


    public String validarNumero(int dia){
    		String diaOk;
    		if (dia>0 && dia<9){
    			diaOk="0"+Integer.toString(dia);

    		}
    		else{
    			diaOk=Integer.toString(dia);
    		}
    		return diaOk;

    }    


    public String obtenerMes(int mes){
    		String result;
    		switch(mes){
				  case 0:
				    {
				      result="jan";
				      break;
				    }
				  case 1:
				    {
				      result="feb";
				      break;
				    }
				  case 2:
				    {
				      result="mar";
				      break;
				    }
				  case 3:
				    {
				      result="apr";
				      break;
				    }
				  case 4:
				    {
				      result="may";
				      break;
				    }
				  case 5:
				    {
				      result="jun";
				      break;
				    }
				  case 6:
				    {
				      result="jul";
				      break;
				    }
				  case 7:
				    {
				      result="aug";
				      break;
				    }
				  case 8:
				    {
				      result="sep";
				      break;
				    }
				  case 9:
				    {
				      result="oct";
				      break;
				    }
				  case 10:
				    {
				      result="nov";
				      break;
				    }
				  case 11:
				    {
				      result="dec";
				      break;
				    }
				  default:
				    {
				      result="Error";
				      break;
				    }
				}

		
		return result;
					
}


	public Date setFecha(String fecha){
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("MM-dd-yyyy");
		
		Date fechaFinal = null;
		try {

		fechaFinal = formatoDelTexto.parse(fecha);

		} catch (ParseException ex) {

		ex.printStackTrace();

		}
		return fechaFinal;
	}

    
	@GET
    @Path("/grafico/fecha/{fecha}")
    @Produces({"application/xml","application/json"})
	public List<Servicio> crearGrafico(@PathParam("fecha") String fecha){
		String prueba;
		prueba="[{\"key\":\"movistar\",\"values\":[{\"x\":oct-25-2016,\"y\":22,},{\"x\":oct-19-2016,\"y\":46,}]}]";
		/*Servicio ser= new Servicio();
	  	ser.setKey("vtr");
	  	List<String> val= new ArrayList<String>();
	  	val.add("0");
	  	val.add("12");
	  	val.add("1");
	  	val.add("234");
	  	ser.setValues(val);
	  	List<Servicio> lista= new ArrayList<Servicio>();
	  	lista.add(ser);
	  	Servicio ser2= new Servicio();
	  	ser2.setKey("movistar");
	  	ser2.setValues(val);
	  	lista.add(ser2);*/
    	List<String> list = new ArrayList<String>();
    	List<Servicio> lista= new ArrayList<Servicio>();
        BasicDBObject whereQuery = new BasicDBObject();
        int i = 0, j = 0;
        int repetido;
        String companias[] = {"movistar", "vtr", "wom", "virgin", "entel","claro"};
        String company;
        String cantidad;
        Date fecha2,fecha1;
        int dia;
        int mes;
        String annio;
        String mesPal;
        String diaOk;
        String fechaFin;
        int dias;
        int cantidadRdn;
        String cRdn;
        Random rnd = new Random();
        fecha1= setFecha(fecha);
        //String[] corte = fecha.split("-");
		//String mes = corte[0];
		//String dia = corte[1];
		//String anho = corte[2];
	//int day = Integer.parseInt(dia);
	//CICLO FOR QUE PREGUNTE POR COMPAÑIA LOS TWEETS 
   	String datos="[\n";
    for(i=0;i<6;i++){
    company = companias[i];
    Servicio ser= new Servicio();
	ser.setKey(company);
	ser.setNonStackable(false);
	List<Datos> val= new ArrayList<Datos>();
	datos=datos+"{\n\"key\":\""+company+"\",\n\"values\":\n[\n";
	dias=0;	
	for (j=0;j<7;j++){
		Datos dat= new Datos();
		fecha2=calcularFecha(fecha1,dias);
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha2);
		dia = calendar.get(Calendar.DATE);
		mes = calendar.get(Calendar.MONTH);
		mesPal=obtenerMes(mes);
		diaOk=validarNumero(dia);
		annio = Integer.toString(calendar.get(Calendar.YEAR));
		fechaFin=mesPal+"-"+diaOk+"-"+annio;
		cantidad=buscarPorCompaniaFecha_cantidad(company,fechaFin);
		cantidadRdn= rnd.nextInt(101);
		cRdn=Integer.toString(cantidadRdn);
		dat.setX(j);
		dat.setY(cantidadRdn);
		datos=datos+"{\n\"x\":"+fechaFin+",\n\"y\":";
		datos=datos+cRdn+",\n}";
		val.add(dat);
		if (j!=6){
			datos=datos+",\n";
		}
		else{
			datos=datos+"\n";
		}
		dias=dias-1;

		}

		datos=datos+"]\n";
		if (i!=5){
			datos=datos+"},\n";
		}
		else{
			datos=datos+"}\n";
		}
		ser.setValues(val);
	  	lista.add(ser);
		}
	
	return lista;
     }


}