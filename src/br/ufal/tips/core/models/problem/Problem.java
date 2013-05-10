package br.ufal.tips.core.models.problem;

import java.util.Set;

import br.ufal.tips.core.models.topic.TopicFactory;

public class Problem {
	
	public String id;
	
//	public String label;
	
//	public String parent;
	
//	public Set<String> next;
	
//	public Set<String> support;
	
//	public String content;

	protected Problem(String id, String label) {
		super();
		this.id = id;
		this.setLabel(label);
	}
	
	protected Problem(String id){
		
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return ProblemFactory.getInstance().getLabel(id);
	}

	public void setLabel(String label) {
		ProblemFactory.getInstance().setLabel(id, label);
	}
	
	
	public Set<String> getParents() {
		return ProblemFactory.getInstance().getParents(id);
	}

	public void setParents(Set<String> parents) {
		ProblemFactory.getInstance().setParents(id,parents);
	}
		
	public void addParents(String parentID){
		ProblemFactory.getInstance().addParents(id, parentID);		
	}
	
	public void removeParents(String parentID){
		ProblemFactory.getInstance().removeParents(id, parentID);
	}
	
	
////////////////////////////////
///////  NEXT          /////////
////////////////////////////////

	public String getNext() {
		return ProblemFactory.getInstance().getNext(id);
	}

	public void setNext(String next) {
		ProblemFactory.getInstance().setNext(id,next);
	}

////////////////////////////////
///////  SUPPORTS      /////////
////////////////////////////////

	public Set<String> getSupports() {
		return ProblemFactory.getInstance().getSupports(id);
	}
	
	public void setSupports(Set<String> supports) {
		ProblemFactory.getInstance().setSupports(id,supports);
	}
	
	public void addSupport(String supportID){
		ProblemFactory.getInstance().addSupport(id,supportID);
	}
	
	public void removeSupport(String supportID){
		ProblemFactory.getInstance().removeSupport(id,supportID);
	}

////////////////////////////////
///////  CONTENT       /////////
////////////////////////////////

	public Set<String> getContent() {
		return ProblemFactory.getInstance().getContent(id);
	}
	
	public void setContent(Set<String> content) {
		ProblemFactory.getInstance().setContent(id, content);
	}
	
	public void addContent(String contentID){
		ProblemFactory.getInstance().addContent(id, contentID);
	}
	
	public void removeContent(String contentID){
		ProblemFactory.getInstance().removeContent(id, contentID);
	}
	
	//DELETE
	
	public void delete(){
		ProblemFactory.getInstance().delete(id);
	}
	
////////////////////////////////
///////  TOSTRING      /////////
////////////////////////////////

	@Override
	public String toString() {
		String problem ="";
		problem += "Problem Id: "+id+"\n";
		problem+= "Label: "+this.getLabel()+"\n";
		problem+= "Contents: "+ this.getContent()+"\n";
		problem+= "Parents: "+this.getParents()+"\n";
		problem+= "Next Problem: "+this.getNext()+"\n";
		problem+= "Supports: "+this.getSupports()+"\n";
		return problem;
	}
}
