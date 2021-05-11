import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MainPageServlet extends HttpServlet {
    Names base = new Names();
//    static
    static Map<String, Advertisement> adv = new HashMap<>();
    @Override
    public void init() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\кудахтер\\Desktop\\l15\\project13\\src\\main\\java\\base.txt"))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] subStr = line.split(" ");
                base.add(subStr[0], subStr[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<link rel=\"stylesheet\" href=\"page.css\">\n");
        out.println("<body>");
        HttpSession session = request.getSession(false);
        if(session != null){
            int flag = 0;
            Cookie[] cookies = request.getCookies();
            for (Cookie i: cookies){
                if(i.getName().equals("userId")){
                    if(i.getValue().equals((String)session.getAttribute("userId"))){
                        String name = (String)session.getAttribute("userName");
                        out.print("<h1>Hello " + name + ", Welcome to AVITO!</h1>");
                        out.print("<h3>your cookie is: (Key): " + i.getName() + ";  (Value): " + i.getValue() + "</h3>");
                        flag = 1;
                        out.println(setAdd());
                    }
                }
            }
            if(flag == 0){
                out.print("Please login first");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        }
        else{

            out.print("Please login first");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        request.getRequestDispatcher("link.html").include(request, response);
        out.println(getMap());
        out.println("</html></body>");
        out.close();
    }

    public String setAdd() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>");
        sb.append("Add your advertisement");
        sb.append("</h2>");
        sb.append("<form method=\"GET\" action=\"/AddServlet\">\n");
        sb.append("Name: <input type=\"text\" name=\"name\">\n");
        sb.append("Description: <input type=\"text\" name=\"desc\">\n");
        sb.append("<input type=\"submit\" value=\"add\">\n");
        sb.append("</form>");
        return sb.toString();
    }

    public static String getMap(){
        StringBuilder sb = new StringBuilder();
        Iterator<Advertisement> iterator = adv.values().iterator();
        ArrayList<Advertisement> buf = new ArrayList<>();
        while(iterator.hasNext()){
            buf.add(iterator.next());
        }
        Collections.sort(buf);
        for (int i = buf.size()-1; i >= 0 ; --i) {
            sb.append(buf.get(i));
        }
        return sb.toString();
    }

}
