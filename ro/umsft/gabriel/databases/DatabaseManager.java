
package ro.umsft.gabriel.databases;

import java.util.*;
import java.sql.*;
import ro.umsft.gabriel.news.News;
import ro.umsft.gabriel.NewsReader;

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
    
    // Updates a news item, the key (source, url) identifies which row to change
    public static void updateNews(News news)
    {
        news.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
        String sql = "UPDATE news SET title = ?, description = ?, author = ?,"
                + " publisher = ?, publish_date = ?, last_modified_date = ?,"
                + " language = ?, cover_image = ? WHERE source = ? AND url = ?";
        try(Connection conn = database.connect();
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getDescription());
            ps.setString(3, news.getAuthor());
            ps.setString(4, news.getPublisher());
            ps.setTimestamp(5, news.getPublishDate());
            ps.setTimestamp(6, news.getLastModifiedDate());
            ps.setString(7, news.getLanguage());
            ps.setString(8, news.getCoverImage());
            ps.setString(9, news.getSource());
            ps.setString(10, news.getUrl());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Error while updating news: " + e.getMessage());
        }
    }
    
    // Deletes the news item identified by source and url
    public static void deleteNews(String source, String url)
    {
        String sql = "DELETE FROM news WHERE source = ?"
                + " AND url = ?";
        try(Connection conn = database.connect();
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, source);
            ps.setString(2, url);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Error while deleting news: " + e.getMessage());
        }
    }
    // Reads every news item from the database, newest first
    public static List<News> getAllNews()
    {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT * FROM news ORDER BY publish_date DESC";
        try(Connection conn = database.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                News news = new News(
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("source"),
                rs.getString("author"),
                rs.getString("publisher"),
                rs.getTimestamp("publish_date"),
                rs.getString("url"),
                rs.getTimestamp("creation_date"),
                rs.getTimestamp("last_modified_date"),
                rs.getString("language"),
                rs.getString("cover_image"));
                newsList.add(news);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error while getting the news: " + e.getMessage());
        }
        return newsList;
    }
    
    // Inserts one event, duplicates (same title, date time and location) are ignored
    public static void isertEvent(Event event)
    {
        
    }
}
