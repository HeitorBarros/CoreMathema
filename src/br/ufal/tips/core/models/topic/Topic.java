package br.ufal.tips.core.models.topic;

import java.util.Set;


public class Topic {
	// Atributos
	private String id;
	
	// Construtores
	
	protected Topic(String id){
		this.id=id;
	}
	
	protected Topic(String id, String label){
		this.id=id;
		TopicFactory.getInstance().setIdAsTopic(id);
		this.setLabel(label);
	}
	
	//Getters and Setters	
	////////////////////////////////
	///////  ID            /////////
	////////////////////////////////

	public String getId() {
		return id;
	}

////////////////////////////////
///////  LABEL         /////////
////////////////////////////////
	
	public String getLabel() {
		return TopicFactory.getInstance().getLabel(id);
	}

	public void setLabel(String label) {
		TopicFactory.getInstance().setLabel(id, label);
	}

////////////////////////////////
///////  PARENTS       /////////
////////////////////////////////	
	
	public Set<String> getParents() {
		return TopicFactory.getInstance().getParents(id);
	}

	public void setParents(Set<String> parents) {
		TopicFactory.getInstance().setParents(id,parents);
	}
		
	public void addParents(String parentID){
		TopicFactory.getInstance().addParents(id, parentID);		
	}
	
	public void removeParents(String parentID){
		TopicFactory.getInstance().removeParents(id, parentID);
	}

////////////////////////////////
///////  NEXT          /////////
////////////////////////////////	
	
	public String getNext() {
		return TopicFactory.getInstance().getNext(id);
	}

	public void setNext(String next) {
		TopicFactory.getInstance().setNext(id,next);
	}
	
////////////////////////////////
///////  PROBLEMS      /////////
////////////////////////////////

	public Set<String> getProblems() {
		return TopicFactory.getInstance().getProblems(id);
	}

	public void setProblems(Set<String> problems) {
		TopicFactory.getInstance().setProblems(id,problems);
	}
	
	public void addProblem(String problemID){
		TopicFactory.getInstance().addProblem(id, problemID);		
	}
	
	public void removeProblem(String problemID){
		TopicFactory.getInstance().removeProblem(id,problemID);
	}
	
////////////////////////////////
///////  SUPPORTS      /////////
////////////////////////////////

	public Set<String> getSupports() {
		return TopicFactory.getInstance().getSupports(id);
	}

	public void setSupports(Set<String> supports) {
		TopicFactory.getInstance().setSupports(id,supports);
	}
	
	public void addSupport(String supportID){
		TopicFactory.getInstance().addSupport(id,supportID);
	}
	
	public void removeSupport(String supportID){
		TopicFactory.getInstance().removeSupport(id,supportID);
	}

////////////////////////////////
///////  DELETE        /////////
////////////////////////////////
	
	public void delete(){
		TopicFactory.getInstance().delete(id);
	}
	
////////////////////////////////
///////  TOSTRING      /////////
////////////////////////////////

	@Override
	public String toString() {
		String topic ="";
		topic += "Topic Id: "+id+"\n";
		topic+= "Label: "+this.getLabel()+"\n";
		topic+= "Parents: "+this.getParents()+"\n";
		topic+= "Next Topic: "+this.getNext()+"\n";
		topic+= "Problems: "+this.getProblems()+"\n";
		topic+= "Supports: "+this.getSupports()+"\n";
		return topic;
	}
	
}
