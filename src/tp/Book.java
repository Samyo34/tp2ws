package tp;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
@XmlRootElement

public class Book {
	int id;
	String title;
	@JsonProperty(value="pages")
	private int nbPages;
	String description;
	Set<String> keywords=new HashSet<String>();

	
	
	public Book(int id, String title, int nbPages) {
		super();
		this.id = id;
		this.title = title;
		this.nbPages = nbPages;
	}
	
	public Book() {
		super();
	}
	
	public void add(String... kws){
		for(String keyword:kws)keywords.add(keyword);
	}
	
	public boolean matches(String s){
		for(String kw: keywords)if(kw.toLowerCase().indexOf(s.trim())>=0) return true;
		return false;
	}
	
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", nbPages=" + nbPages + ", description=" + description+ ", keywords=" + keywords + "]";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getNbPages() {
		return nbPages;
	}
	
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<String> getKeywords() {
		return keywords;
	}
	
	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}
	
	public ObjectNode toJsonString(ObjectMapper mapper){
		ObjectNode obj = mapper.createObjectNode();
		obj.put("id", this.id);
		obj.put("title", this.title);
		obj.put("pages", this.nbPages);
		obj.put("description", this.description);
		ArrayNode keyw = obj.putArray("keywords");
		for (String key : this.keywords) {
			keyw.add(key);
		}
		
		return obj;
	}
	
	

}
