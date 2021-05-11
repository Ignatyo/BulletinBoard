import java.util.ArrayList;
import java.util.Objects;

public class Advertisement implements Comparable <Advertisement>{
    String holder;
    String description;
    int rating;
    String id;
    ArrayList<String> likers = new ArrayList<>();

    public Advertisement(String date, String holder, String description) {
        this.holder = holder;
        this.description = description;
        this.rating = 0;
        this.id = holder + " : " + date;
    }

    public synchronized String getHolder() {
        return holder;
    }

    public synchronized String getDescription() {
        return description;
    }

    public synchronized int getRating() {
        return rating;
    }

    public synchronized void setLike(String username){
        if(likers.contains(username)){
            return;
        }else {
            likers.add(username);
            rating += 1;
        }
    }

    public synchronized void setDislike(String username){
        if(likers.contains(username)){
            return;
        }else {
            likers.add(username);
            rating -= 1;
        }
    }

    public String getId() {
        return id;
    }


    @Override
    public synchronized boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return rating == that.rating;
    }

    @Override
    public synchronized int hashCode() {
        return Objects.hash(rating);
    }

    @Override
    public synchronized int compareTo(Advertisement o) {
        return this.rating - o.rating;
    }

    @Override
    public synchronized String toString() {
        StringBuilder adds = new StringBuilder();
        adds.append("<p> </p>" + "<p> </p>");
        adds.append("<h2 class=\"name\" >" + holder  + "</h2>" + "<h3 class=\"desc\">" + description + "</h3>");
        adds.append("<form method=\"GET\" action=\"/AddServlet/like\">\n");
        adds.append("LIKE: <input type=\"submit\" value=\"+1\"> " + rating + "\n");
        adds.append("<input type=\"hidden\" name=\"id\" value=\"" + id + "\">");
        adds.append("</form>");
        adds.append("<form method=\"GET\" action=\"/AddServlet/dislike\">\n");
        adds.append("DISLIKE: <input type=\"submit\" value=\"-1\"> " + rating + "\n");
        adds.append("<input type=\"hidden\" name=\"id\" value=\"" + id + "\">");
        adds.append("</form>");
        adds.append("<p> </p>" + "<p> </p>");
        return adds.toString();
    }
}