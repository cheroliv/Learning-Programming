package fr.chalodss.servlets;

import java.io.IOException;
import fr.chalodss.services.AgeService;
import fr.chalodss.utils.PropertiesReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Charles T.
 * 
 */
@WebServlet("/AgeServlet")
public final class AgeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("In doGet method.");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    var birthDate = request.getParameter("birthdate");
    var res       = PropertiesReader.getInstance().getProps().getProperty("age_page");
    var rd        = getServletContext().getRequestDispatcher(res);

    request.setAttribute("age", AgeService.computeAge(birthDate));

    try {
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
