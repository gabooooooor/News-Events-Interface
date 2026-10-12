/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import ro.umsft.gabriel.databases.Database;
import ro.umsft.gabriel.databases.DatabaseManager;
import ro.umsft.gabriel.news.News;
import ro.umsft.gabriel.rss.RSSReader;
import ro.umsft.gabriel.rss.RSSSource;

/**
 *
 * @author ministar
 */

// Coordinates RSS downloading and database access for the UI
public class NewsService {
    
    private static final Database database = new Database();
    // Downloads every RSS source and saves the news into the database
    private void downloadAllNews()
    {
        List<RSSSource> sources = RSSReader.loadSources();
        for(RSSSource source : sources)
        {
            List<News> fetched = RSSReader.fetchNews(source);
            System.out.println("Source name: " + source.getName() + "   " + fetched.size() + " news was fetched");
            for(News news : fetched)
            {
                DatabaseManager.insertNews(news);
            }
        }
    }
    
    // Loads the news from SQLite, downloads from RSS only if the database is empty
    public List<News> loadNews()
    {
        List<News> newsList = DatabaseManager.getAllNews();
        if(newsList.isEmpty())
        {
            System.out.println("First start, downloading the news ...");
            downloadAllNews();
            newsList = DatabaseManager.getAllNews();
        }
        return newsList;
    }
    
    public List<News> refresh()
    {
        downloadAllNews();
        return DatabaseManager.getAllNews();
    }
    
    public List<News> search(String keyword)
    {
        if(keyword == null || keyword.isBlank())
        {
            return DatabaseManager.getAllNews();
        }
        else
        {
            return DatabaseManager.searchNews(keyword.trim());
        }
    }
}
