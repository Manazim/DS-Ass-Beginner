/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package q1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author haika
 */
public class ReadMyLetter_U2002028 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Input second part of letter: ");
        Scanner in = new Scanner(System.in);
        String part2 = in.nextLine();
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File("HaikalHaziq_U2002028.txt"), true));
            pw.println();
            pw.println("Thursday, 18 June 2021");
            pw.println();
            pw.println(part2);
            pw.close();

            Scanner s = new Scanner(new FileInputStream(new File("HaikalHaziq_U2002028.txt")));
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("File output error.");
        }
    }
}
