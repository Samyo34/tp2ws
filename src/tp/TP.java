package tp;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("/tp")
public class TP {

	@GET
	@Path("/int")
	@Produces(MediaType.TEXT_PLAIN)
	public int testInt(){
		return 125;
	}
	
	@GET
	@Path("/text")
	@Produces(MediaType.TEXT_PLAIN)
	public String testText(){
		return "Hello World";
	}
	
	@GET
	@Path("/integer")
	@Produces(MediaType.TEXT_PLAIN)
	public Integer testInteger(){
		return new Integer(125);
	}
	
	@GET
	@Path("/null")
	@Produces(MediaType.TEXT_PLAIN)
	public Integer testNull(){
		return null;
	}
	
	@GET
	@Path("/date")
	@Produces(MediaType.TEXT_PLAIN)
	public String testDate(){
		return new Date().toString();
	}
	
	@GET
	@Path("/qp")
	@Produces(MediaType.TEXT_PLAIN)
	public String testQueryParam(@QueryParam("name") String name, @QueryParam("id") int id){
		return name +" = "+id;
	}
	
	@GET
	@Path("/formatDate/{years}/{months}/{days}")
	@Produces(MediaType.TEXT_PLAIN)
	public String testFormatDate(@PathParam("years") int years,@PathParam("months") int months,@PathParam("days") int days){
		//Date d = new Date(years,months,days);
		//DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate d = LocalDate.of(years, Month.of(months), days);
		return d.toString();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testSearch(@QueryParam("categ") String categ,@DefaultValue("date") @QueryParam("sort") String sort ){
		if(categ.isEmpty()){
			return Response.status(500).build();
		}
		if(categ.equals("private")){
			return Response.status(401).build();
		}
		
		if(categ.equals("xab")){
			return Response.status(404).build();
		}
		
		return Response.status(200).entity("trouvé : "+sort).build();
	}
	
	@POST
	@Path("/post")
	@Consumes("application/x-www-form-urlencoded")
	public Response testPost(@FormParam("nom") String nom){
		return Response.status(200).entity("la "+nom).build();
	}
	
	@GET
	@Path("/Book")
	@Produces(MediaType.APPLICATION_XML)
	public Book testBook(){
		Book b = new Book();
		b.setTitle("Le petit cheval de manège");
		b.setDescription("très mauvais !");
		b.setNbPages(200);
		b.add("diner");
		b.add("cons");
		return b;
	}
	
	@GET
	@Path("/Book2")
	@Produces(MediaType.APPLICATION_JSON)
	public String testBook2() throws JsonProcessingException{
		Book b = new Book();
		b.setTitle("Le petit cheval de manège");
		b.setDescription("très mauvais !");
		b.setNbPages(200);
		b.add("diner");
		b.add("cons");
		return new ObjectMapper().writeValueAsString(b);
	}
	
	@GET
	@Path("/Book3")
	@Produces(MediaType.APPLICATION_JSON)
	public String testBook3() throws JsonProcessingException{
		Book b = new Book();
		b.setTitle("Le petit cheval de manège");
		b.setDescription("très mauvais !");
		b.setNbPages(200);
		b.add("diner");
		b.add("cons");
		return b.toJsonString(new ObjectMapper()).toString();
	}
	
}
