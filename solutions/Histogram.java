import java.util.*;


class Histogram {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        int testCases = scan.nextInt();
        int bins = scan.nextInt();

        try{
            histogram(testCases,bins);
        }catch(Exception ignored){
        }
    }

    public static String histogram(int testCases, int bins){

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
            cutoffs = cutoffs+interval[i]+" ";
        }

        for (int i = 0; i < counts.length; i++) {
            count = count+counts[i]+" ";
        }
        return cutoffs+"\n"+count;
    }
}
