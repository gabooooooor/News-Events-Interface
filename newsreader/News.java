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
//Represents one news article
public class News {
    
    //Declaring the class's fields
    private String title;
    private String description;
    private String source;
    private String author;
    private String publisher;
    private Timestamp publishDate;
    private String url;
    private Timestamp creationDate;
    private Timestamp lastEditedDate;
    private String language;
    private String coverImage;
    
    public News(String title, String description,
            String source, String author, String publisher,
            Timestamp publishDate,
            String url, Timestamp creationDate,
            Timestamp lastEditedDate, String language, 
            String coverImage)
    {
        this.title = title;
        this.description = description;
        this.source = source;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.url = url;
        this.creationDate = creationDate;
        this.lastEditedDate = lastEditedDate;
        this.language = language;
        this.coverImage = coverImage;
    }
    
    //getters
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public String getSource(){return source;}
    public String getAuthor(){return author;}
    public String getPublisher(){return publisher;}
    public Timestamp getPublishDate(){return publishDate;}
    public String getUrl(){return url;}
    public Timestamp getCreationDate(){return creationDate;}
    public Timestamp getLastEditedDate(){return lastEditedDate;}
    public String getLanguage(){return language;}
    public String getCoverImage(){return coverImage;}
    
    //setters
    public void setTitle(String title){this.title = title;}
    public void setDescription(String description){this.description = description;}
    public void setSource(String source){this.source = source;}
    public void setAuthor(String author){this.author = author;}
    public void setPublisher(String publisher){this.publisher = publisher;}
    public void setPublishDate(Timestamp publishDate){this.publishDate = publishDate;}
    public void setUrl(String url){this.url = url;}
    public void setLanguage(String language){this.language = language;}
    public void setCoverImage(String coverImage){this.coverImage = coverImage;}
    public void setLastEditedDate(Timestamp lastEditedDate){this.lastEditedDate = lastEditedDate;}
}

