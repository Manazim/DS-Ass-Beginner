/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package histogram;

/**
 *
 * @author Ikhmal Fakhri <u2000600@siswa.um.edu.my>
 */
import java.util.Stack;
import java.util.Scanner;

class Histogram {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCases = scan.nextInt();
        int bins = scan.nextInt();
        int max = 0;
        int min = Integer.MAX_VALUE;

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
        for (int i = 0; i < interval.length; i++) {
            System.out.print(interval[i] + " ");
        }
        System.out.println("");
        for (int i = 0; i < counts.length; i++) {
            System.out.print(counts[i] + " ");
        }

        
        
    }
}
