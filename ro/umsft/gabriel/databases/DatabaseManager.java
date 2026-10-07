/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ro.umsft.gabriel.news.News;
/**
 *
 * @author ministar
 */
public class DatabaseManager {
    
    private static final Database database = new Database();
    
    // Inserts one news item, duplicates (same source and url) are ignored
    public static void insertNews(News news)
    {
        //Insetring the new data
        String sql = "INSERT OR IGNORE INTO news (title, description, source,"
                + "author, publisher, publish_date, url, creation_date,"
                + "last_modified_date, language, cover_image) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try(Connection conn = database.connect();
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getDescription());
            ps.setString(3, news.getSource());
            ps.setString(4, news.getAuthor());
            ps.setString(5, news.getPublisher());
            ps.setTimestamp(6, news.getPublishDate());
            ps.setString(7, news.getUrl());
            ps.setTimestamp(8, news.getCreationDate());
            ps.setTimestamp(9, news.getLastModifiedDate());
            ps.setString(10, news.getLanguage());
            ps.setString(11, news.getCoverImage());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Error while inserting news: " + e.getMessage());
        }
    }
}
