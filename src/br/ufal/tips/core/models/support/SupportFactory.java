package br.ufal.tips.core.models.support;

import br.ufal.ic.tips.utils.Keys;
import br.ufal.tips.core.exceptions.CannotCreateInstanceException;
import br.ufal.tips.core.exceptions.InstanceNotFoundException;
import br.ufal.tips.core.models.factory.Factory;
import br.ufal.tips.core.models.topic.TopicFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SupportFactory extends Factory{
	
	private SupportFactory(){
		 super();
	}
	
	public static SupportFactory getInstance(){
		
		if (factory ==null) {
			factory = new SupportFactory();
			return (SupportFactory) factory;
		}else{
			return (SupportFactory) factory;
		}
	}
	
	public Support create(String id, String label, String link) throws CannotCreateInstanceException{
		if (jedis.hgetAll(id).size()>0) {
			throw new CannotCreateInstanceException("This Id is already set in database.");
		}else{
			return new Support(id, label, link);
		}		
	}
	
	public Support retrieve(String id) throws InstanceNotFoundException{
		if (Keys.SUPPORT.equals(jedis.hget(id, Keys.TYPE))) {
			Support s = new Support(id);
			return s;		
		}		
		throw new InstanceNotFoundException();
	}
	
	public void setIdAsSupport(String id){
		jedis.hset(id, Keys.TYPE, Keys.SUPPORT);
	}
	
	public void setLabel(String id, String label) {
		jedis.hset(id, Keys.LABEL, label);		
	}
	public String getLabel(String id) {
		return jedis.hget(id, Keys.LABEL);
	}
	public void setLink(String id, String link) {
		jedis.hset(id, Keys.LINK, link);
		
	}
	public String getLink(String id) {
		return jedis.hget(id, Keys.LINK);
	}
	public void delete(String id) {
		System.out.println("Deleting Support. "+jedis.hdel(id, Keys.LABEL, Keys.LINK, Keys.TYPE)+ " fields deleted");		
	}
	
	
	
	
	
	
}
