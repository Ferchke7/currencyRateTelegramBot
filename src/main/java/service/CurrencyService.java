package service;

import model.CurrencyModel;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CurrencyService {
    public static String getCurrencyRate(String message, CurrencyModel model) throws IOException, ParseException {
        URL url = new URL("https://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.KRW" + message);
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setCode(object.getString("code"));
        model.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(object.getString("date")));
        model.setCur_Abbreviation(object.getString("currencyCode"));
        model.setProvider(object.getString("provider"));
        model.setCur_name(object.getString("name"));
        model.setBuying_price(object.getDouble("ttBuyingPrice"));
        model.setSelling_price(object.getDouble("ttSellingPrice"));

        return "Official rate of KRW to " +
                model.getCur_Abbreviation()
                +"\non the date: " + getFormatDate(model)
                +"\nSelling price is: " + model.getSelling_price()
                +"\nBuying price is: " + model.getBuying_price()
                +"\nThe provider is: " + model.getProvider();
    }

    public static String getFormatDate(CurrencyModel model) {
        return new SimpleDateFormat("dd MMM yyyy").format(model
                .getDate());
    }

    //TODO make it two languages parsed and make it in the interface
//    public String infoInKorean(String info){
//
//    }
//
//    public String infoInEnglish(String info) {
//
//    }
}
