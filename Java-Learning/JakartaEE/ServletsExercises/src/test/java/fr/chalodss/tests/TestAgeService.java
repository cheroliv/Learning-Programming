package fr.chalodss.tests;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author Charles T.
 * 
 */
public final class TestAgeService {

  private final static CloseableHttpClient httpClient = HttpClients.createDefault();

  private static void close() throws IOException {
    httpClient.close();
  }

  private static void sendPost() throws Exception {
    var url        = "http://localhost:8080/TestsServlets/AgeServlet";
    var post       = new HttpPost(url);
    var parameters = new ArrayList<NameValuePair>();

    parameters.add(new BasicNameValuePair("birthdate", "1982-09-07"));
    post.setEntity(new UrlEncodedFormEntity(parameters));

    try (var httpClient = HttpClients.createDefault(); var response = httpClient.execute(post)) {
      System.out.println(EntityUtils.toString(response.getEntity()));
    }
  }

  public static void main(String... args) throws Exception {
    try {
      sendPost();
    } finally {
      close();
    }
  }

}
