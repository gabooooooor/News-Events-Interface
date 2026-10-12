/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ro.umsft.gabriel;

import ro.umsft.gabriel.databases.Database;
import ro.umsft.gabriel.service.NewsService;
import ro.umsft.gabriel.ui.MainWindow;
import javax.swing.*;

/**
 *
 * @author ministar
 */
public class NewsReader {

    public static void main(String[] args) {
        new Database().createTables();
        NewsService newsService = new NewsService();
        SwingUtilities.invokeLater(() -> new MainWindow(newsService).setVisible(true));
    }
}
