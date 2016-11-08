package cl.citiaps.twitter.streaming;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
//import twitter4j.json.JSONObject;
import twitter4j.json.DataObjectFactory;

import java.lang.*;


/*
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
*/
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;


import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.index.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.IndexInput;
import org.apache.lucene.store.IndexOutput;
import org.apache.lucene.store.Lock;
public class Main {
	
    public final static String OAUTH_CONSUMER_KEY = "VlP9Cbu5Nu5LOn3UGNxwkLuR9";
    public final static String OAUTH_CONSUMER_SECRET = "f5Q3uxdasV6wxlsuV5vZzVZl57K3JwvhYMGukGZk9KYcaQZadJ";
    public final static String OAUTH_ACCESS_TOKEN = "785167171752255488-Ha4gmjbUteLQ7t3gMOgTeR0yF9ao4U8";
    public final static String OAUTH_ACCESS_TOKEN_SECRET = "GdS5RHugLgTkvVVwlZNzEXjme40WuN3IzDjUkqVtKtfus";
	
    
    
    public static void main(String[] args) {
        /*
        DB database = cliente.getDB("test");

        DBCollection coleccion = database.getCollection("things");
        BasicDBObject doc = new BasicDBObject();
        DBCollection collection = database.getCollection("coleccion");
        
        //collection.drop();
         
        //DBObject documento = new BasicDBObject().append("probando", "probando");
         
        //collection.insert(documento );
        */
        


    	new Main().doMain(args);      
    }
    
    public void doMain(String[] args){
    	
    	MongoClient cliente = new MongoClient("localhost", 27017);
    	DB db = cliente.getDB("twitterdb");
        final DBCollection coll = db.getCollection("todo");
        
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true);
    	cb.setOAuthConsumerKey(OAUTH_CONSUMER_KEY);
    	cb.setOAuthConsumerSecret(OAUTH_CONSUMER_SECRET);
    	cb.setOAuthAccessToken(OAUTH_ACCESS_TOKEN);
    	cb.setOAuthAccessTokenSecret(OAUTH_ACCESS_TOKEN_SECRET);
    	
    	cb.setJSONStoreEnabled(true);


    	TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
    	StatusListener listener = new StatusListener() {  		

	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	        }

	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	        }

	        public void onScrubGeo(long userId, long upToStatusId) {
	            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	        }

	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }

			@Override
			public void onStallWarning(StallWarning arg0) {
				
			}

			@Override
			public void onStatus(Status status) {
				System.out.println(status.getText());
				
				String tweet = DataObjectFactory.getRawJSON(status);
                //System.out.println(tweet);
				//String jsonUser = TwitterObjectFactory.getRawJSON(status.getUser());
                DBObject doc = (DBObject)JSON.parse(tweet);
                coll.insert(doc);
				
			}
	    };

	    FilterQuery fq = new FilterQuery();
	    String keywords[] = {"Trump"};

	    fq.track(keywords);

	    twitterStream.addListener(listener);
	    twitterStream.filter(fq);  
	    MongoDBSelectExample mse = new MongoDBSelectExample();
		mse.ejecutar();
	    
	    
    }



}