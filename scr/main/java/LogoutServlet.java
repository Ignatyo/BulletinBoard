import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {
            response.setContentType("text/html");

            HttpSession session = request.getSession();
            session.invalidate();
            Cookie userNameCookieRemove = new Cookie("userId", "");
            userNameCookieRemove.setMaxAge(0);
            response.addCookie(userNameCookieRemove);

            PrintWriter out=response.getWriter();
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"page.css\">\n");
            out.println("<body>");

            request.getRequestDispatcher("link.html").include(request, response);
            out.println("You are successfully logged out!");

            out.println("</html></body>");

            out.close();

    }
}
