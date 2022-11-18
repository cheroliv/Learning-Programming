package fr.chalodss.tests;

import java.util.concurrent.ExecutionException;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Charles T.
 * 
 */
public final class TestAgeService {

  public static void main(String[] args) {
    var client     = ClientBuilder.newClient();
    var target     = client.target("http://localhost:8080/TestsWebServices/rest/AgeService");
    var form       = new Form("birthDate", "1982-09-07");
    var invocation = target.request(MediaType.TEXT_PLAIN).buildPost(Entity.form(form));
    var response   = invocation.submit(String.class);

    try {
      System.out.println(response.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

}
