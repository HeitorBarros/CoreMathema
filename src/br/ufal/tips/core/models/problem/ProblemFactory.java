package br.ufal.tips.core.models.problem;

import java.util.HashSet;
import java.util.Set;

import br.ufal.ic.tips.utils.Keys;
import br.ufal.tips.core.models.factory.Factory;

public class ProblemFactory extends Factory{

	public ProblemFactory() {
		super();
		// TODO Stub de construtor gerado automaticamente
	}
	
	public static ProblemFactory getInstance(){
		
		if (factory ==null) {
			factory = new ProblemFactory();
			return (ProblemFactory) factory;
		}else{
			return (ProblemFactory) factory;
		}
	}

	
	
	public String getLabel(String id) {
		return jedis.hget(id, Keys.LABEL);
	}

	public void setLabel(String id, String label) {
		jedis.hset(id, Keys.LABEL, label);
		
	}

	public Set<String> getParents(String id) {
		Set<String> parents = new HashSet<String>();
		
		String builder=jedis.hget(id, Keys.PARENTS);
		if (builder!=null) {
			for (String string : builder.split(Keys.DELIMITER)) {
				parents.add(string);
			}
		}		
		return parents;
	}

	public void setParents(String id, Set<String> parents) {
		String concatenatedParents ="";
		String delimiter = "";
		for (String aParent : parents) {
			concatenatedParents+=delimiter+aParent;
			delimiter = Keys.DELIMITER;
		}
		jedis.hset(id, Keys.PARENTS, concatenatedParents);	
		
	}

	public void addParents(String id, String parentID) {
		String topicParents = jedis.hget(id, Keys.PARENTS);
		
		if (topicParents=="") {
			topicParents= parentID;
		} else {
			topicParents+= Keys.DELIMITER + parentID;
		}
		
		jedis.hset(id, Keys.PARENTS, topicParents);
		
	}

	public void removeParents(String id, String parentID) {
		Set<String> topicParents = getParents(id);
		
		if (topicParents.remove(parentID)) {
			setParents(id, topicParents);
		}else{
			//Parent não conseguiu ser removido. O QUE FAZER? Talvez ele não exista. Mandar mensagem?
		}
		
	}
	
	public String getNext(String id) {
		return jedis.hget(id, Keys.NEXT);
	}

	public void setNext(String id, String next) {
		jedis.hset(id, Keys.NEXT, next);		
	}
			
	public Set<String> getSupports(String id) {
		Set<String> supports = new HashSet<String>();
		
		String builder=jedis.hget(id, Keys.SUPPORT);
		
		if (builder!=null) {
			for (String string : builder.split(Keys.DELIMITER)) {
				supports.add(string);
			}
		}		
		return supports;
	}

	public void setSupports(String id, Set<String> supports) {
		
		String concatenatedSupports ="";
		String delimiter = "";
		for (String aSupport : supports) {
			concatenatedSupports+=delimiter+aSupport;
			delimiter = Keys.DELIMITER;
		}
		jedis.hset(id, Keys.SUPPORT, concatenatedSupports);
	}

	public void addSupport(String id, String supportID) {
		String problemSupports = jedis.hget(id, Keys.SUPPORT);
		
		if (problemSupports=="") {
			problemSupports= supportID;
		} else {
			problemSupports+=Keys.DELIMITER+supportID;
		}
		
		jedis.hset(id, Keys.SUPPORT, problemSupports);
	}

	public void removeSupport(String id, String supportID) {
		Set<String> problemSupports = getSupports(id);
		
		if (problemSupports.remove(supportID)) {
			setSupports(id,problemSupports);
		}else {
			//Parent não conseguiu ser removido. O QUE FAZER? Talvez ele não exista. Mandar mensagem?
		}	
	}

	public Set<String> getContent(String id) {
		Set<String> contents = new HashSet<String>();
		
		String builder=jedis.hget(id, Keys.CONTENT);
		
		if (builder!=null) {
			for (String string : builder.split(Keys.DELIMITER)) {
				contents.add(string);
			}
		}		
		return contents;
	}

	public void setContent(String id, Set<String> contents) {
		String concatenatedContents ="";
		String delimiter = "";
		for (String aSupport : contents) {
			concatenatedContents+=delimiter+aSupport;
			delimiter = Keys.DELIMITER;
		}
		jedis.hset(id, Keys.CONTENT, concatenatedContents);
		
	}
	
	public void addContent(String id, String contentID) {
		String problemContents = jedis.hget(id, Keys.SUPPORT);
		
		if (problemContents=="") {
			problemContents= contentID;
		} else {
			problemContents+=Keys.DELIMITER+contentID;
		}
		
		jedis.hset(id, Keys.CONTENT, problemContents);
	}

	public void removeContent(String id, String contentID) {
		Set<String> problemContents = getSupports(id);
		
		if (problemContents.remove(contentID)) {
			setSupports(id,problemContents);
		}else {
			//Parent não conseguiu ser removido. O QUE FAZER? Talvez ele não exista. Mandar mensagem?
		}	
	}

	public void delete(String id) {
		jedis.hdel(id, Keys.TYPE, Keys.CONTENT, Keys.LABEL, Keys.PARENTS, Keys.NEXT, Keys.SUPPORT);
		
	}
	
	
	
	

}
