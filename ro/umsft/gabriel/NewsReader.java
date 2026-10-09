/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ro.umsft.gabriel;

import ro.umsft.gabriel.databases.Database;
import ro.umsft.gabriel.rss.RSSSource;

/**
 *
 * @author ministar
 */
public class NewsReader {

    public static void main(String[] args) {
        new Database().createTable();
        RSSSource testSource = new RSSSource();
    }
}
