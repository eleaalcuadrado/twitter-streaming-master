package service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
 
import javax.ws.rs.PathParam;
 
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
//import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
//import com.mongodb.client.MongoCollection;

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

import model.TweetExample;
import cl.citiaps.twitter.streaming.TwitterProcessor;

@Path("/ranking")
@ApplicationPath("/")
@WebService
public class ServiceTweetRanking{
	@GET
    @Path("compania/{compania}")
	@Produces({"application/xml", "application/json"})
    //@Produces({"application/json"})
    public List<TweetExample> buscarPorCompania(@PathParam("compania") String compania) {
		if(compania.equals("entel")|| compania.equals("movistar")|| compania.equals("vtr")|| compania.equals("wom")|| compania.equals("virgin")|| compania.equals("claro")){	
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase db = mongoClient.getDatabase("twitterdb");
			MongoCollection<Document> collection = db.getCollection("todo");
			
			Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "0995" ) );
			Session session = driver.session();
			
			
			
			BasicDBObject whereQuery = new BasicDBObject();
	
			whereQuery.put("text", 
			new BasicDBObject("$regex", String.format(".*((?i)%s).*",compania)) );
			
			TwitterProcessor twitterProcessor = new TwitterProcessor();
			float puntaje;
			String algo = "Empezamos:\n";
			List<TweetExample> list = new ArrayList<TweetExample>();
			TweetExample t_e;
			
			for(Document doc : collection.find(whereQuery)){
				String texto = doc.getString("text");
				Document user = (Document)doc.get("user");
				String id = doc.getString("id_str");
				String nombre = user.getString("screen_name");
				String id_user = user.getString("id_str");
				puntaje = twitterProcessor.SNP(session,id_user);
				t_e = new TweetExample(id, texto, user, puntaje);
				list.add(t_e);
				//algo = algo + t_e.id + " " + t_e.text + " " +t_e.puntaje +": "+ puntaje+"\n";
				
			}	
			Collections.sort(list);
			List<TweetExample> lista_retorno = new ArrayList<TweetExample>();
			lista_retorno.add(list.get(0));
			lista_retorno.add(list.get(1));
			lista_retorno.add(list.get(2));
			return lista_retorno; 	
			
			
		}
		else{
			List<TweetExample> asd= new ArrayList<TweetExample>();
			return asd;
		}
    }
}
