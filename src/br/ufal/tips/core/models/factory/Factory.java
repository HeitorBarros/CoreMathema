package br.ufal.tips.core.models.factory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public abstract class Factory {
	
	protected Jedis jedis;
	
	protected JedisPool pool;
	
	protected static Factory factory;
	
	protected Factory(){
		pool= new JedisPool(new JedisPoolConfig(), "localhost");
		jedis = pool.getResource();
	}

	public void close() {
		 pool.returnResource(jedis);
		 pool.destroy();
		 
		 factory = null;
	}
}
