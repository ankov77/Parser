import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Parser {

    public static void main(String[] args) throws IOException {
        List<Article> articleList = new ArrayList<>();


        Document doc = Jsoup.connect("http://www.linaris.ru/letnie-shiny").get();


        Elements cell = doc.getElementsByAttributeValue("class", "name name-product");
        Elements price = doc.getElementsByAttributeValue("class", "price");

        for (int i = 0; i < cell.size(); i++) {
            Element aElement = cell.get(i).child(0);
            String url = aElement.attr("href");
            String title = aElement.text();

            aElement = price.get(i).child(0);
            String pricenew = aElement.text();
            articleList.add(new Article(url, title, pricenew));
        }
        articleList.forEach(System.out::println);
    }
}

class Article {
    private String url;
    private String name;
    private String pricenew;

    public Article(String url, String name, String pricenew) {
        this.url = url;
        this.name = name;
        this.pricenew = pricenew;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPricenew() { return pricenew; }

    public void setPricenew(String pricenew) { this.pricenew = pricenew; }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", pricenew='" + pricenew + '\'' +
                '}';
    }
}
