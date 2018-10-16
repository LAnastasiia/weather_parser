/**
 * Created by Victor on 03.10.2018.
 */
import lombok.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


@Getter
@Setter
@ToString
public class City {
    private String name;
    private String url;
    private String administrativeArea;
    private int numberOfCitizens;
    private String yearOfFound;
    private Coordinates coordinates;
    private double area;

    private static final int INFO_SIZE = 6;




    public static City parse(Element city) throws IOException {
        Elements info = city.select("td");
        if (info.size() == INFO_SIZE) {

            City myCity = new City();
            Element anchor = info.get(1).select("a").get(0);

            myCity.setName(anchor.attr("title"));
            String thisUrl = String.format("https://uk.wikipedia.org%s", anchor.attr("href"));
            myCity.setUrl(thisUrl);

            anchor = info.get(2).select("a").get(0);
            myCity.setAdministrativeArea(anchor.attr("title"));

            String cit_anchor = info.get(3).text();
            String NumberOfCitizens = cit_anchor.replaceAll("[^\\d.]", "");
            int len = NumberOfCitizens.length();
            if (NumberOfCitizens.substring(0, len / 2).equals(NumberOfCitizens.substring((len / 2))))
                {NumberOfCitizens = NumberOfCitizens.substring(0, (len / 2) - 1);}
            myCity.setNumberOfCitizens(Integer.parseInt(NumberOfCitizens));

            myCity.setYearOfFound(info.get(4).text());

            double Area = Double.valueOf(info.get(5).text());
            myCity.setArea(Area);


            Coordinates myCoordinates = Coordinates.getCoordinates(thisUrl);
            myCity.setCoordinates(myCoordinates);


            return myCity;
        }
        return null;
    }

}