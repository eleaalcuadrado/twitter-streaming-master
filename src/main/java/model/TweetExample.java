package model;

import java.util.List;
import javax.persistence.EntityManager;
import org.bson.Document;

public class TweetExample implements Comparable<TweetExample>{
    public String id;
	public String text;
	public Document user;
	public float puntaje;
	public TweetExample(String id, String text, Document user, float puntaje){
		this.id = id;
		this.text = text;
		this.user = user;		
		this.puntaje = puntaje;
	}
	
	@Override
        public int compareTo(TweetExample o) {
            if (puntaje < o.puntaje) {
                return -1;
            }
            if (puntaje > o.puntaje) {
                return 1;
            }
            return 0;
        }
}
