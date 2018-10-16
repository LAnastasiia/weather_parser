
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Coordinates {
    double longitute;
    double lattitude;


    @SneakyThrows
    public static Coordinates getCoordinates (String coord_url) {
        Document doc = Jsoup.connect(coord_url).get();
        Elements coord_set = doc.select("span.geo");

        try{
            String[] CoordinateStr = String.valueOf(coord_set.get(0).text()).split(";");
            Coordinates this_coords = new Coordinates();
            this_coords.longitute = Double.parseDouble(CoordinateStr[0]);
            this_coords.lattitude = Double.parseDouble(CoordinateStr[1]);
            return this_coords;
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }

    }
}
