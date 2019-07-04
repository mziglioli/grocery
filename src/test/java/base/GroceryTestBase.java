package base;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public abstract class GroceryTestBase {

    protected String getFileAsString(String fileName) throws IOException {
        return IOUtils.toString(
            this.getClass().getResourceAsStream(fileName),
            "UTF-8"
        );
    }

    protected Document buildDocument() throws IOException {
        return buildDocument("/strawberries.html");
    }

    protected Document buildDocument404() throws IOException {
        return buildDocument("/404.html");
    }

    protected Document buildDocument(String fileName) throws IOException {
        String html = getFileAsString(fileName);
        return Jsoup.parse(html);
    }
}
