/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.news;
import ro.umsft.gabriel.news.News;
import java.sql.Timestamp;
/**
 *
 * @author ministar
 */
public class Event extends News{
    
    private Timestamp eventDateTime;
    private String location;
    
    public Event(String title, String description,
            String source, String author, String publisher,
            Timestamp publishDate,
            String url, Timestamp creationDate,
            Timestamp lastModifiedDate, String language, 
            String coverImage,
            Timestamp eventDateTime, String location)
    {
        super(title, description, source, author,
                publisher,publishDate, url, creationDate,
                lastModifiedDate, language, coverImage);
        if(eventDateTime == null){
            throw new IllegalArgumentException("EventDateTime is required");
        }
        if(location.isBlank()){
            throw new IllegalArgumentException("Location is required");
        }
        this.eventDateTime = eventDateTime;
        this.location = location;
    }
    
    public Timestamp getEventDateTime(){return eventDateTime;}
    public String getLocation(){return location;}
}
