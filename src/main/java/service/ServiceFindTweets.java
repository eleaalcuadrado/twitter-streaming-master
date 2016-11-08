package service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
 
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

@Path("/tweet")
@ApplicationPath("/")
@WebService
public class ServiceFindTweets {
    @GET
    @Produces({"application/json"})
    @WebMethod
	public String obtenerDatos(){ 
		MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("twitterdb");
        DBCollection collection = db.getCollection("todo");
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
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("twitterdb");
        DBCollection collection = db.getCollection("todo");
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
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("twitterdb");
        DBCollection collection = db.getCollection("todo");
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
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("twitterdb");
        DBCollection collection = db.getCollection("todo");
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
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("twitterdb");
			DBCollection collection = db.getCollection("todo");
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
    @Path("compania/{compania}/cantidad")
    @Produces({"application/json"})
    public String buscarPorCompania_Cantidad(@PathParam("compania") String compania) {
		if(compania.equals("entel")|| compania.equals("movistar")|| compania.equals("vtr")|| compania.equals("wom")|| compania.equals("virgin")|| compania.equals("claro")){
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("twitterdb");
			DBCollection collection = db.getCollection("todo");
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
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("twitterdb");
        DBCollection collection = db.getCollection("todo");
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
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("twitterdb");
        DBCollection collection = db.getCollection("todo");
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
}
