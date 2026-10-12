/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ro.umsft.gabriel;

import ro.umsft.gabriel.databases.Database;
import ro.umsft.gabriel.rss.RSSSource;
import ro.umsft.gabriel.databases.DatabaseManager;
import ro.umsft.gabriel.news.News;
import ro.umsft.gabriel.service.NewsService;
import java.util.List;

/**
 *
 * @author ministar
 */
public class NewsReader {

    public static void main(String[] args) {
        new Database().createTables();
        NewsService newsService = new NewsService();
        List<News> newsList = newsService.loadNews();
        System.out.println("News loaded: " + newsList.size());
    }
}
