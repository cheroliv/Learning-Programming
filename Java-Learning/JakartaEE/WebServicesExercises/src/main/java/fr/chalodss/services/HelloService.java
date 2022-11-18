package fr.chalodss.services;

import fr.chalodss.beans.Player;
import fr.chalodss.data.Data;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Charles T.
 * 
 */
@Path("/hello")
public final class HelloService {

  @GET
  @Path("/helloText")
  @Produces(MediaType.TEXT_PLAIN)
  public String helloText() {
    return "Hello Jersey - Plain Text";
  }

  @GET
  @Path("/helloXML/{name}")
  @Produces(MediaType.APPLICATION_XML)
  public Response helloXML(@PathParam("name") String name) {
    var player = Data.getPlayers().stream().filter(p -> p.getName().equals(name)).findFirst();
    return Response.status(200).entity(player.isPresent() ? player.get() : new Player()).build();
  }

  @GET
  @Path("/helloJSON/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response helloJSON(@PathParam("name") String name) {
    var player = Data.getPlayers().stream().filter(p -> p.getName().equals(name)).findFirst();
    return Response.status(200).entity(player.isPresent() ? player.get() : new Player()).build();
  }

}
