package br.ufal.tips.core.models.support;

public class Support {

	public String id;

	//Contrutores
	protected Support(String id) {
		super();
		this.id = id;
	}
	
	protected Support(String id, String label, String link){
		this.id = id;
		SupportFactory.getInstance().setIdAsSupport(id);
		
		this.setLabel(label);
		this.setLink(link);
		
	}

	public void setLabel(String label) {
		SupportFactory.getInstance().setLabel(id,label);
		
	}
	
	public String getLabel(){
		return SupportFactory.getInstance().getLabel(id);
	}
	
	public void setLink(String link) {
		SupportFactory.getInstance().setLink(id,link);
		
	}
	
	public String getLink(){
		return SupportFactory.getInstance().getLink(id);
	}
	
	public void delete(){
		SupportFactory.getInstance().delete(id);	
	}
	
	@Override
	public String toString() {
		String message ="";
		message+="Support id: "+this.id+"\n";
		message+="Label: "+this.getLabel()+"\n";
		message+="Link: "+this.getLink()+"\n";
		
		return message;
	}
	
}
