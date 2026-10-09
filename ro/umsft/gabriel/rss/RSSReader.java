/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.rss;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ro.umsft.gabriel.news.News;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.InputStream;

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
    
    // Downloads one RSS source and converts every <item> to a News object
    public static List<News> fetchNews(RSSSource rssSource)
    {
        List<News> newsList = new ArrayList<>();
        int statusCode = getStatusCode(rssSource.getUrl());
        if( statusCode != 200 )
        {
            System.out.println(rssSource.getName() + "      " + statusCode + " error code");
            return newsList;
        }
        try
        {
            URL url = new URL(rssSource.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            try(InputStream input = connection.getInputStream())
            {
               DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
               Document document = builder.parse(input); 
               NodeList items = document.getElementsByTagName("item");
               for(int i=0; i<items.getLength(); i++)
               {
                   Element item = (Element) items.item(i);
                   // Read the values from the RSS tags
                   String title = getText(item, "title");
                   String description = getText(item, "description");
                   String link = getText(item, "link");
                   String author = getText(item, "author");
                   Timestamp publishDate = parseDate(getText(item, "pubDate"));
                   Timestamp now = new Timestamp(System.currentTimeMillis());
                   
                   if (title.isEmpty()) {title = "No title";}
                   if (author.isEmpty()) {author  = getText(item, "do:creator");}
                   if (author.isEmpty()) {author = "Unknonw";}
                   
                   // Build the News object, the order matches the News constructor
                   News news = new News(
                   title,
                   description,
                   rssSource.getName(),//source
                   author,
                   rssSource.getName(),//publisher
                   publishDate,
                   link,
                   now,//creationDate
                   now,//lastModifiedDate
                   rssSource.getLanguage(),
                   "res/images/default.jpg"
                   );
                   newsList.add(news);
               }
            }
            catch(Exception e)
            {
                System.out.println("Error: " + e.getMessage());
            } 
        }
        catch(Exception e)
        {
            System.out.println(rssSource.getName() + e.getMessage());
        }
        return newsList;
    }
}
