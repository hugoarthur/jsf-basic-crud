package br.com.rest;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.service.PersonServiceLocal;

@Path("/people")
public class PersonRest {

	@EJB
	private PersonServiceLocal service;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPeople(@Context HttpServletRequest request) {
		return Response.ok(service.list()).build();
	}
}
