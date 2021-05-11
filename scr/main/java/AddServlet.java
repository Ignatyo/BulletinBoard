import java.io.*;
import java.util.Collections;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date date = new Date();
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        if( uri.equals("/AddServlet/like" ) ) {
            if(session != null){
                Cookie[] cookies = request.getCookies();
                for (Cookie i: cookies){
                    if(i.getName().equals("userId")){
                        if(i.getValue().equals((String)session.getAttribute("userId"))){
                            MainPageServlet.adv.get(request.getParameter("id")).setLike((String)session.getAttribute("userId"));
                        }
                    }
                }
            }
            response.sendRedirect("/MainPageServlet");
        }
        else if( uri.equals("/AddServlet/dislike" )) {
            if(session != null){
                Cookie[] cookies = request.getCookies();
                for (Cookie i: cookies){
                    if(i.getName().equals("userId")){
                        if(i.getValue().equals((String)session.getAttribute("userId"))){
                            MainPageServlet.adv.get(request.getParameter("id")).setDislike((String)session.getAttribute("userId"));
                        }
                    }
                }
            }
            response.sendRedirect("/MainPageServlet");
        }
        else if( uri.equals("/AddServlet" )){
            MainPageServlet.adv.put(request.getParameter("name") + " : " + date.toString(), new Advertisement(date.toString(), request.getParameter("name"), request.getParameter("desc")));
            response.sendRedirect("/MainPageServlet");
        }
    }
}