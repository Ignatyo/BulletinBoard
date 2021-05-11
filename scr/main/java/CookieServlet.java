import java.io.*;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
public class CookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String val = req.getParameter("userId");
        Cookie cookie = new Cookie("userId", val);
        resp.addCookie(cookie);
        resp.sendRedirect("/MainPageServlet");
    }
}
