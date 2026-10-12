/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.news;

import java.sql.Timestamp;

/**
 *
 * @author ministar
 */
public abstract class ContentItem {
    
    //Declaring the class's fields
    private String title;
    private String description;
    private String source;
    private String author;
    private String publisher;
    private Timestamp publishDate;
    private String url;
    private Timestamp creationDate;
    private Timestamp lastModifiedDate;
    private String language;
    private String coverImage;
    
    public ContentItem(String title, String description,
            String source, String author, String publisher,
            Timestamp publishDate,
            String url, Timestamp creationDate,
            Timestamp lastModifiedDate, String language, 
            String coverImage)
    {
        // Validate the required fields before storing them
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title is required");
        }
        if(source == null || source.isBlank()){
            throw new IllegalArgumentException("Source is required");
        }
        if(url == null || url.isBlank()){
            throw new IllegalArgumentException("Url is required");
        }
        if(language == null || language.isBlank()){
            throw new IllegalArgumentException("Language is required");
        }
        this.title = title;
        this.description = description;
        this.source = source;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.url = url;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
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
    public Timestamp getLastModifiedDate(){return lastModifiedDate;}
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
    public void setLastModifiedDate(Timestamp lastModifiedDate){this.lastModifiedDate = lastModifiedDate;}
    
    @Override
    public String toString()
    {
        return title;
    }
}
