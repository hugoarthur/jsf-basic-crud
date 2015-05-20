package br.com.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.service.PersonService;

@Path("/people")
public class PersonRest {

	@EJB
	private PersonService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPeople() {
		return Response.ok(service.list()).build();
	}
}
