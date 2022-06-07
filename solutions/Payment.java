package Payment;

import java.io.BufferedInputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Payment {

    static Queue<String> Platinum = new Queue<>();
    static Queue<String> Gold = new Queue<>();
    static Queue<String> Silver = new Queue<>();
    static Queue<String> Bronze = new Queue<>();
    static ArrayList<Character> check = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int i = 0;

        while (true) {
            System.out.print("INPUT :");
            String input = sc.nextLine();

            if (input.equals("END")) {

                System.out.println("REBOOT");
                break;
            } else if (input.equals("CLEAR")) {
                print(i);
            } else {
                String[] words = input.split(" ");
                check.add(words[0].charAt(9));
                if (i == 0) {

                    if (words[2].equals("PLATINUM")) {
                        Platinum.enqueue(words[1]);
                    } else if (words[2].equals("GOLD")) {
                        Gold.enqueue(words[1]);
                    } else if (words[2].equals("SILVER")) {
                        Silver.enqueue(words[1]);
                    } else if (words[2].equals("BRONZE")) {
                        Bronze.enqueue(words[1]);
                    }

                } else {
                    if (check.get(i - 1).equals(check.get(i))) {

                        if (words[2].equals("PLATINUM")) {
                            Platinum.enqueue(words[1]);
                        } else if (words[2].equals("GOLD")) {
                            Gold.enqueue(words[1]);
                        } else if (words[2].equals("SILVER")) {
                            Silver.enqueue(words[1]);
                        } else if (words[2].equals("BRONZE")) {
                            Bronze.enqueue(words[1]);
                        }
                    } else {

                        if (words[2].equals("PLATINUM")) {
                            Platinum.enqueue(words[1]);
                        } else if (words[2].equals("GOLD")) {
                            Gold.enqueue(words[1]);
                        } else if (words[2].equals("SILVER")) {
                            Silver.enqueue(words[1]);
                        } else if (words[2].equals("BRONZE")) {
                            Bronze.enqueue(words[1]);
                        }
                        i++;
                        print(i);

                        System.exit(0);
                    }

                }
                i++;
            }
        }

    }

    public static void print(int i) {

        System.out.print("Output :");
        for (int count = 0; count < i; count++) {

            if (!Platinum.isEmpty()) {
                System.out.print(Platinum.dequeue() + " ");
            }
            if (!Gold.isEmpty()) {

                System.out.print(Gold.dequeue() + " ");
            }
            if (!Silver.isEmpty()) {

                System.out.print(Silver.dequeue() + " ");
            }
            if (!Bronze.isEmpty()) {

                System.out.print(Bronze.dequeue() + " ");
            }
        }
    }

    public static class Queue<E> {

        private java.util.LinkedList<E> list = new java.util.LinkedList<>();

        public Queue() {
        }

        public void enqueue(E e) {

            list.addLast(e);

        }

        public E dequeue() {

            return list.removeFirst();

        }
        public boolean isEmpty() {

            return list.isEmpty();

        }
    }

}
