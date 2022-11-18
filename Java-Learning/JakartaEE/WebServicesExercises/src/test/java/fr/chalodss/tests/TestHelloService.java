package fr.chalodss.tests;

import jakarta.ws.rs.client.ClientBuilder;

/**
 * @author Charles T.
 * 
 */
public final class TestHelloService {

  public static void main(String[] args) {
    var client = ClientBuilder.newClient();
    var uri_1  = "http://localhost:8080/TestsWebServices/rest/hello/helloText";
    var uri_2  = "http://localhost:8080/TestsWebServices/rest/hello/helloXML/Charles";
    var uri_3  = "http://localhost:8080/TestsWebServices/rest/hello/helloJSON/HAL";

    var s1     = client.target(uri_1).request().get(String.class);
    var s2     = client.target(uri_2).request().get(String.class);
    var s3     = client.target(uri_3).request().get(String.class);

    System.out.println(s1);
    System.out.println(s2);
    System.out.println(s3);
  }

}
