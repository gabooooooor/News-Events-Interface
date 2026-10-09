/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.rss;

import java.util.*;
/**
 *
 * @author ministar
 */

// Mirrors the structure of rss.json, so Gson can fill it
public class RSSConfig {
    
    private List<RSSSource> rssSources = new ArrayList<>();
    public List<RSSSource> getRssSources(){return rssSources;}
}
