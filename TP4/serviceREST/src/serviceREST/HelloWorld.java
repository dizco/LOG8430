package serviceREST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/")
public class HelloWorld {

	@GET
	@Path("/{name}")
	public Response getMessage(@PathParam("name") String name)
	{
		String outMsg = "Hello " + name + "!";
		return Response.status(200).entity(outMsg).build();
	}
	
	/*
	public void printName(String name) {
		System.out.printf("Hello, " + name);
	}
	
	public void printWordsNumber(String txt) {
		String[] words = txt.split("//s+");
		System.out.printf("Words: " + words.length);
	}*/
}
