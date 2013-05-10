package br.ufal.tips.core.models.topic;

import java.util.HashSet;
import java.util.Set;

import br.ufal.ic.tips.utils.Keys;
import br.ufal.tips.core.exceptions.CannotCreateInstanceException;
import br.ufal.tips.core.exceptions.InstanceNotFoundException;
import br.ufal.tips.core.models.factory.Factory;

public class TopicFactory extends Factory{
	
	private TopicFactory(){
		 super();
	}
	
	public static TopicFactory getInstance(){
		
		if (factory ==null) {
			factory = new TopicFactory();
			return (TopicFactory) factory;
		}else{
			return (TopicFactory) factory;
		}
	}
	
	
	public Topic create(String id, String label) throws CannotCreateInstanceException{
		if (jedis.hgetAll(id).size()>0) {
			throw new CannotCreateInstanceException("This Id is already set in database.");
		}else{
			return new Topic(id, label);
		}		
	}
	
	public Topic retrieve(String id) throws InstanceNotFoundException{
		if (Keys.TOPIC.equals(jedis.hget(id, Keys.TYPE))) {
			Topic t = new Topic(id);
			return t;		
		}		
		throw new InstanceNotFoundException();
	}

	public void setIdAsTopic(String id) {
		jedis.hset(id, Keys.TYPE, Keys.TOPIC);
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

	public Set<String> getProblems(String id) {
		Set<String> problems = new HashSet<String>();
		
		String builder=jedis.hget(id, Keys.PROBLEM);
		if (builder!=null) {
			for (String string : builder.split(Keys.DELIMITER)) {
				problems.add(string);
			}	
		}
		return problems;
	}

	public void setProblems(String id, Set<String> problems) {
		String concatenatedProblems ="";
		String delimiter = "";
		for (String aProblem : problems) {
			concatenatedProblems+=delimiter+aProblem;
			delimiter = Keys.DELIMITER;
		}
		jedis.hset(id, Keys.PROBLEM, concatenatedProblems);
	}

	public void addProblem(String id, String problemID) {
		String topicProblems = jedis.hget(id, Keys.PROBLEM);
		
		if (topicProblems=="") {
			topicProblems= problemID;
		} else {
			topicProblems+= Keys.DELIMITER + problemID;
		}
		
		jedis.hset(id, Keys.PROBLEM, topicProblems);
		
	}

	public void removeProblem(String id, String problemID) {
		Set<String> topicProblems = getProblems(id);
		
		if (topicProblems.remove(problemID)) {
			setProblems(id,topicProblems);
		}else {
			//Problem não conseguiu ser removido. O QUE FAZER? Talvez ele não exista. Mandar mensagem?
		}		
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
		String topicSupports = jedis.hget(id, Keys.SUPPORT);
		
		if (topicSupports=="") {
			topicSupports= supportID;
		} else {
			topicSupports+=Keys.DELIMITER+supportID;
		}
		
		jedis.hset(id, Keys.SUPPORT, topicSupports);
	}

	public void removeSupport(String id, String supportID) {
		Set<String> topicSupports = getSupports(id);
		
		if (topicSupports.remove(supportID)) {
			setSupports(id,topicSupports);
		}else {
			//Parent não conseguiu ser removido. O QUE FAZER? Talvez ele não exista. Mandar mensagem?
		}	
	}

	public void delete(String id) {
		System.out.println("Deleting Topic. "+jedis.hdel(id, Keys.LABEL, Keys.NEXT, Keys.PARENTS, Keys.PROBLEM, Keys.SUPPORT, Keys.TYPE)+ " fields deleted");
	}	
	

}
