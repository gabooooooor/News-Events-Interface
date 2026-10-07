/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.rss;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author ministar
 */

// Downloads and parses RSS feeds
public class RSSReader {
    
    // Returns the HTTP status code of the url, or -1 if the connection fails
    public static int getStatusCode(String urlAddress)
    {
        try {

            URL url =
                    new URL(urlAddress);

            HttpURLConnection connection =
                    (HttpURLConnection)
                            url.openConnection();

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int statusCode =
                    connection.getResponseCode();

            connection.disconnect();

            return statusCode;

        } catch (Exception e) {

            System.out.println(
                    "Connection error: "
                    + e.getMessage()
            );

            return -1;
        }
    }
    
    // Reads the text of the first tag with the given name, or "" if it is missing
    public static String getText(Element element, String tagName)
    {
        NodeList nodes =
                element
                        .getElementsByTagName(
                                tagName
                        );

        if (nodes.getLength() > 0) {

            return nodes
                    .item(0)
                    .getTextContent();
        }

        return "";
    }
    
    // Converts an RSS date text to a Timestamp, current time if it fails
    public static Timestamp parseDate(String dateText)
    {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        try
        {
            Date date = format.parse(dateText);
            return new Timestamp(date.getTime());
        }
        catch(Exception e)
        {
            return new Timestamp(System.currentTimeMillis());
        }
    }
}
