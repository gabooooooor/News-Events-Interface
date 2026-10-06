/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsreader;
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
            Timestamp lastEditedDate, String language, 
            String coverImage,
            Timestamp eventDateTime, String location)
    {
        super(title, description, source, author,
                publisher,publishDate, url, creationDate,
                lastEditedDate, language, coverImage);
        this.eventDateTime = eventDateTime;
        this.location = location;
    }
    
    public Timestamp getEventDateTime(){return eventDateTime;}
    public String getLocation(){return location;}
}
