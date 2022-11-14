package Utils;

import Classes.Exchange;
import Exceptions.ExchangeException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class JsoupUtil {

    public static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Metoda koja cita lisu kurseva sa sajta
     * i vraca ih kao lisu Exchange objekata
     * @return Exchange list
     */
    public static List<Exchange> readFromWebsite() {
        List<Exchange> exchanges = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://www.kursna-lista.com/kursna-lista-nbs").get();
            Elements table = document.select("table[class=tablesorter tableHighlight alignFirstCol] tbody");
            int z = 1;
            for (int i = 0; i < table.select("tr").size(); i++) {
                String formatingName = String.format("#curFullTable > tbody > tr:nth-child(%d) > td:nth-child(1)", z);
                String formatingCode = String.format("#curFullTable > tbody > tr:nth-child(%d) > td:nth-child(2)", z);
                String formatinRate = String.format("#curFullTable > tbody > tr:nth-child(%d) > td:nth-child(5)", z);
                Elements name = document.select(formatingName);
                Elements code = document.select(formatingCode);
                Elements rate = document.select(formatinRate);
                double notRounded = Double.parseDouble(rate.text());
                String stringRounded = df.format(notRounded);
                double yesRounded = Double.parseDouble(stringRounded);
                Exchange exchange = null;
                try {
                    exchange = new Exchange(name.text(), code.text(), yesRounded);
                } catch (ExchangeException e) {
                    throw new RuntimeException(e);
                }
                exchanges.add(exchange);
                z += 1;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exchanges;
    }
}
