/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ro.umsft.gabriel;

import ro.umsft.gabriel.databases.Database;
import ro.umsft.gabriel.rss.RSSSource;
import ro.umsft.gabriel.databases.DatabaseManager;
import ro.umsft.gabriel.news.News;
import ro.umsft.gabriel.rss.RSSReader;
import java.util.List;

/**
 *
 * @author ministar
 */
public class NewsReader {

    // Downloads every RSS source and saves the news into the database
    private static void downloadAllNews()
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
    private static List<News> loadNews()
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
    
    public static void main(String[] args) {
        new Database().createTables();
        List<News> newsList = loadNews();
        System.out.println("News loaded: " + newsList.size());
    }
}
