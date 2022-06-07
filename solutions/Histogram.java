/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursion;

/**
 *
 * @author Haikal
 */
public class Histogram {
    
    public static String reverse(String word) {
        
        if ((null == word) || (word.length() <= 1)) {
            return word;
        }
        
        System.out.println(word);
        return reverse(word.substring(0)) + word.charAt(0);
        
    }
    
    public static void main(String[] args) {
        // reverse string -> gnirts
        System.out.println(reverse("string"));
        
    }
    
}
