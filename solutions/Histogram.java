import java.util.*;


class Histogram {

    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

        int amount = sc.nextInt();

        for (int j = 0; j < amount; j++) {

            int testCases = sc.nextInt();
            int bins = sc.nextInt();

            int max = 0;
            int min = Integer.MAX_VALUE;

            Stack<Integer> dataPoints = new Stack<>();

            int[] counts = new int[bins];
            int[] interval = new int[bins + 1];

            for (int i = 0; i < testCases; i++) {
                int l = sc.nextInt();
                max = Math.max(l, max);
                min = Math.min(l, min);

                dataPoints.push(l);
            }

            int range = (max - min) / bins;

            for (int i = 0; i < bins + 1; i++) {
                interval[i] = (range * i + min);
            }

            interval[bins - 1]++;

            while (!dataPoints.isEmpty()) {
                for (int i = 0; i < bins; i++) {
                    if (dataPoints.peek() < interval[i] + range) {
                        counts[i]++;
                        dataPoints.pop();
                        break;
                    }
                }
            }

            interval[bins - 1]--;

            String cutoffs = "";
            String count = "";

            for (int i = 0; i < interval.length; i++) {
                cutoffs = cutoffs + interval[i] + " ";
            }

            for (int i = 0; i < counts.length; i++) {
                count = count + counts[i] + " ";
            }

            System.out.println(cutoffs);
            System.out.println(count);
        }
    }
}
