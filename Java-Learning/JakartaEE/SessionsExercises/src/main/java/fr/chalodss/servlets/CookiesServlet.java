package fr.chalodss.servlets;

import java.io.IOException;
import java.util.stream.Stream;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Charles T.
 * 
 */
@WebServlet("/Cookies")
public final class CookiesServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CookiesServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher rd       = null;
    Cookie            cookie   = null;
    var               login    = request.getParameter("login");
    var               password = request.getParameter("password");
    var               signed   = request.getParameter("signed");

    if (signed != null) {
      cookie = new Cookie("cookie_auth", login + "_" + password);
      cookie.setMaxAge(3600);
      response.addCookie(cookie);
      //System.out.println("Create cookie.");
    } else {
      Stream.of(request.getCookies()).filter(c -> c.getName().equals("cookie_auth")).forEach(c -> {
        c.setMaxAge(0);
        response.addCookie(c);
        //System.out.println("Delete cookie.");
      });
    }
    request.setAttribute("connected", password.equals("hal") ? "OK" : "Failed");
    rd = getServletContext().getRequestDispatcher("/index.jsp");
    rd.forward(request, response);
  }

}
