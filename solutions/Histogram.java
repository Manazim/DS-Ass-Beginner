package Histogram;

import java.util.Scanner;

public class Histogram {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Firstline");
        String firstLine = sc.nextLine();
        String[] info = firstLine.split(" ");
        int number_bins = Integer.parseInt(info[1]);
        int dataPoints = Integer.parseInt(info[0]);

        System.out.println("Enter Secondline");
        String secondLine = sc.nextLine();
        String[] dataPoints_String = secondLine.split(" ");
        int[] dataPoints_Number = new int[dataPoints_String.length];

        for (int i = 0; i < dataPoints_String.length; i++) {

            dataPoints_Number[i] = Integer.parseInt(dataPoints_String[i]);

        }

        int max = dataPoints_Number[0];
        int min = dataPoints_Number[0];

        for (int j = 0; j < dataPoints_Number.length; j++) {

            if (max < dataPoints_Number[j]) {
                max = dataPoints_Number[j];
            }
            if (min > dataPoints_Number[j]) {
                min = dataPoints_Number[j];
            }
        }

        int cutoffs_value = (max - min) / number_bins;
        int[] interval = new int[number_bins + 1];
        int[] count = new int[number_bins];
        int count_track = 0;
        int start = min;

        for (int k = 0; k < interval.length; k++) {

            if (k == 0) {
                System.out.println(min);
            } else {
                interval[k] = min + cutoffs_value;
                System.out.print(interval[k] + " "); //print the interval value
            }
        }

        for (int l = 0; l < dataPoints; l++) {

            for (int m = 0; m < interval.length; m++) {

                if (m == 0) {
                    if (dataPoints_Number[l] >= min && dataPoints_Number[l] <= interval[m + 1]) {
                        count[count_track]++;
                        count_track++;
                    }
                } else {
                    if (dataPoints_Number[l] >= interval[m] && dataPoints_Number[l] <= interval[m + 1]) {
                        count[count_track]++;
                        count_track++;
                    }

                }

            }
        }

        for (int n = 0; n < count.length; n++) {
            System.out.print(count[n] + " "); //print the result
        }

    }
}
