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

    public static void main(String[] args) {
        new Database().createTables();
        List<RSSSource> sources = RSSReader.loadSources();
        System.out.println("Loaded sources: " + sources.size());
        
        for(RSSSource source : sources)
        {
            List<News> fetched = RSSReader.fetchNews(source);
            System.out.println("Source name: " + source.getName() + "   " + fetched.size() + " news was fetched");
            for(News news : fetched)
            {
                DatabaseManager.insertNews(news);
            }
        }
        System.out.println("News in the database: " + DatabaseManager.getAllNews().size());
    }
}
