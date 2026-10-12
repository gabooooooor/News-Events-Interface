/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.news;

import java.sql.*;
/**
 *
 * @author ministar
 */
//Represents one news article
public class News extends ContentItem{
    
    public News(String title, String description,
            String source, String author, String publisher,
            Timestamp publishDate,
            String url, Timestamp creationDate,
            Timestamp lastModifiedDate, String language,
            String coverImage)
    {
        super(title, description, source, author, publisher,
                publishDate, url, creationDate, lastModifiedDate,
                language, coverImage);
    }
}

