/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.umsft.gabriel.news;

/**
 *
 * @author ministar
 */

// The only place that knows the default cover image path
public final class CoverImageResolver {
    
    private static final String DEFAULT_IMAGE = "res/images/images (1).jpg";
    
    // Returns the default image path if the given path is missing
    public static String resolve(String imagePath)
    {
        if(imagePath != null && !imagePath.isBlank() && new java.io.File(imagePath).isFile())
        {
            return imagePath;
        }
        return DEFAULT_IMAGE;
    }
}
