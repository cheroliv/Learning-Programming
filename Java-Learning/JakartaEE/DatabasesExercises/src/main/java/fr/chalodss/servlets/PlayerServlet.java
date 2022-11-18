package fr.chalodss.servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import fr.chalodss.services.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Charles T.
 * 
 */
@WebServlet("/PlayerServlet")
public final class PlayerServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public PlayerServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    var sc   = getServletContext();
    var res  = "/jsp/player.jsp";
    var url  = sc.getInitParameter("db.url");
    var user = sc.getInitParameter("db.user");
    var pass = sc.getInitParameter("db.password");

    try {
      var rd   = sc.getRequestDispatcher(res);
      var con  = DriverManager.getConnection(url, user, pass);
      var list = PlayerService.getPlayers(con);

      request.setAttribute("players", list);
      rd.forward(request, response);
    } catch (ServletException | SQLException e) {
      e.printStackTrace();
    }
  }

}
