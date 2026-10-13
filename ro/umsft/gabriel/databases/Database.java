/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author ministar
 */
// Handles the SQLite connection and table setup
public class Database {
    
    private final String databaseUrl = "jdbc:sqlite:news.db";
    
    // Opens a connection to the SQLite database file
    public Connection connect()throws SQLException
    {
        return DriverManager.getConnection(databaseUrl);
    }
    
    // Creates the news and events tables if they do not exist yet
    public void createTables()
    {
        
        // The composite primary key (source, url) prevents duplicate news
        String newsSql = "CREATE TABLE IF NOT EXISTS news (" +
                "title TEXT NOT NULL," +
                "description TEXT," +
                "source TEXT NOT NULL," +
                "author TEXT," +
                "publisher TEXT," +
                "publish_date TIMESTAMP," +
                "url TEXT NOT NULL," +
                "creation_date TIMESTAMP NOT NULL," +
                "last_modified_date TIMESTAMP NOT NULL," +
                "language TEXT NOT NULL," +
                "cover_image TEXT," +
                "PRIMARY KEY (source, url)" +
                ")";
        
        // The composite primary key identifies an event by title, date and place
        String eventsSql = "CREATE TABLE IF NOT EXISTS events (" +
                "title TEXT NOT NULL," +
                "description TEXT," +
                "source TEXT NOT NULL," +
                "author TEXT," +
                "publisher TEXT," +
                "publish_date TIMESTAMP," +
                "url TEXT NOT NULL," +
                "creation_date TIMESTAMP NOT NULL," +
                "last_modified_date TIMESTAMP NOT NULL," +
                "language TEXT NOT NULL," +
                "cover_image TEXT," +
                "event_date_time TIMESTAMP NOT NULL," +
                "location TEXT NOT NULL," +
                "PRIMARY KEY (title, event_date_time, location)" +
                ")";
        
        try(Connection conn = DriverManager.getConnection(databaseUrl);
                Statement stmt =conn.createStatement())
        {
            stmt.execute(newsSql);
            stmt.execute(eventsSql);
        }
        catch(SQLException e)
        {
            System.out.println("Error while creating tables: " + e.getMessage());
        }
    }
}
