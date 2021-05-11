import java.io.*;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setContentType("text/html");
        String login = request.getParameter("name");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("link.html").include(request, response);
        HashMap<String, String> base = Names.getBase();
        String pass = base.get(login);
        if(pass != null) {
            if(pass.equals(password) ){
                HttpSession session = request.getSession();
                session.setAttribute("userName", login);
                Date date = new Date();
                String cookieId = date.toString() + login;
                cookieId = cookieId.replaceAll("\\s+","");
                session.setAttribute("userId", cookieId);
                session.setAttribute("login", login);
                response.sendRedirect("/CookieServlet?userId=" + cookieId);
            }
        }
        else{
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"page.css\">\n");
            out.println("<body>");
            out.print("Sorry, username or password error!");
            request.getRequestDispatcher("login.html").include(request, response);
            out.println(MainPageServlet.getMap());
            out.println("</body></html>");
            out.close();
        }


    }
}

