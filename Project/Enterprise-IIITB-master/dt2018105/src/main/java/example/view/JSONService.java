package example.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import example.control.FindQuadraticRoots;
import example.model.QuadraticEquation;


@Path("/json")
public class JSONService
{

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getSampleQEInJSON()
	{

		QuadraticEquation qe = new QuadraticEquation();
		qe.setA(6);
		qe.setB(4);
		qe.setC(2);

		return qe.toString() + "<a href='http://localhost:8080/dt2018105/'> return</a>";

	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createQE(QuadraticEquation qe)
	{
		String result = qe.toString();
		return Response.status(201).entity(result).build();
	}

	@POST
	@Path("/find_roots")
	@Consumes(MediaType.APPLICATION_JSON)
	public FindQuadraticRoots findRootsQE(QuadraticEquation qe)
	{
		FindQuadraticRoots roots = new FindQuadraticRoots(qe.getA(), qe.getB(), qe.getC());
		return roots;
	}

}