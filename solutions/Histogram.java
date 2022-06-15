/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Ikhmal Fakhri <u2000600@siswa.um.edu.my>
 */
import java.util.Stack;
import java.util.Scanner;

class Histogram {

    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        int testCases = scan.nextInt();
        int bins = scan.nextInt();
        int max = 0;
        int min = Integer.MAX_VALUE;
        
        histo(testCases,bins,max,min);
        
    }
    
    public static String histo(int testCases, int bins, int max, int min){
        Stack<Integer> dataPoints = new Stack<>();
        int[] counts = new int[bins];
        int[] interval = new int[bins + 1];

        for (int i = 0; i < testCases; i++) {
            int l = scan.nextInt();
            max = Math.max(l, max);
            min = Math.min(l, min);
           
            dataPoints.push(l);
        }
        
        scan.close();
        
        int range = (max - min) / bins;
        int mid = max-min/2;
        
        for (int i = 0; i < bins + 1; i++) {
            interval[i] = (range * i + min);
        }
        
        interval[bins - 1]++;
        
        while (!dataPoints.isEmpty()) {
            if(dataPoints.peek() < interval[interval.length/2]){
                for (int i = 0; i < bins; i++) {
                    if (dataPoints.peek() < interval[i] + range) {
                        counts[i]++;
                        dataPoints.pop();
                        break;
                    }
                }
            } else {
                for (int i = 0; i < bins; i++) {
                    if (dataPoints.peek() < interval[interval.length/2+i] + range) {
                        counts[interval.length/2+i]++;
                        dataPoints.pop();
                        break;
                    }
                }
            }
        }
        
        String intervals = "";
        
        for (int i = 0; i < interval.length; i++) {
             intervals = intervals+interval[i]+" ";
        }
        System.out.println("");
        String counts1 = "";
        for (int i = 0; i < counts.length; i++) {
             counts1 = counts1+counts[i]+" ";
        }
        return intervals+"\n"+counts1;
    }
}
