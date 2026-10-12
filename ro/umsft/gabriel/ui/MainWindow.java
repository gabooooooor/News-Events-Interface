/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.ui;

import javax.swing.*;
import java.awt.*;
import ro.umsft.gabriel.news.CoverImageResolver;
import ro.umsft.gabriel.service.NewsService;
import ro.umsft.gabriel.news.News;
/**
 *
 * @author ministar
 */

// Main window: news list on the left, details on the right
public class MainWindow extends JFrame{
    
    private final NewsService newsService;
    private final DefaultListModel<News> listModel = new DefaultListModel<>();
    private final JList<News> newsList = new JList<>(listModel);
    private final JPanel detailPanel = new JPanel();
    
    private final JButton addButton = new JButton("Add");
    private final JButton editButton = new JButton("Edit");
    private final JButton deleteButton = new JButton("Delete");
    
    public MainWindow(NewsService newsService)
    {
        this.newsService = newsService;
        setTitle("News Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(addButton);
        toolbar.add(editButton);
        toolbar.add(deleteButton);
        add(toolbar, BorderLayout.NORTH);
        
        add(buildSplitPane(), BorderLayout.CENTER);
        newsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        newsList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                showDetails(newsList.getSelectedValue());
            }
        });
        
        addButton.addActionListener(e -> onAdd());
        editButton.addActionListener(e -> onEdit());
        deleteButton.addActionListener(e -> onDelete());
        reloadNews();
    }
    
    private JSplitPane buildSplitPane()
    {
        JScrollPane left = new JScrollPane(newsList);
        JPanel right = detailPanel;
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        splitPane.setDividerLocation(366);
        splitPane.setResizeWeight(0.33);
        return splitPane;
    }
    
    private void reloadNews()
    {
        listModel.clear();
        for(News news : newsService.loadNews())
        {
            listModel.addElement(news);
        }
    }
    
    private void showDetails(News news)
    {
        detailPanel.removeAll();
        detailPanel.setLayout(new BorderLayout());
        if(news == null)
        {
            detailPanel.add(new JLabel("Select a news item"));
            detailPanel.revalidate();
            detailPanel.repaint();
            return;
        }
        String imagePath = CoverImageResolver.resolve(news.getCoverImage());
        ImageIcon original = new ImageIcon(imagePath);
        JLabel imageLabel;
        if(original.getIconWidth() <= 0)
        {
            imageLabel = new JLabel("No cover image");
        }
        else
        {
            Image scaled = original.getImage().getScaledInstance(400, -1, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaled));
        }
        
        
        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        String description = news.getDescription() == null
                ? ""
                : news.getDescription().replaceAll("<[^>]+>", " ").trim();
        text.setText(
                news.getTitle() + "\n\n" 
                        + description + "\n\n" 
                                + "Author: " + news.getAuthor() + "\n\n" 
                                        + "Source: " + news.getSource() + "\n\n" 
                                                + "URL: " + news.getUrl()
        );
        
        detailPanel.add(imageLabel, BorderLayout.NORTH);
        detailPanel.add(new JScrollPane(text), BorderLayout.CENTER);
        detailPanel.revalidate();
        detailPanel.repaint();
    }
}
