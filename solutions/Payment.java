

import java.util.*;

public class Payment implements Comparable<Payment> {

    private Long oldEpoch;
    private Long newEpoch;
    private Long newEpochHolder;
    private String txnld, tier;
    final String PLATINUM = "PLATINUM";
    final String GOLD = "GOLD";
    final String SILVER = "SILVER";

    static PriorityQueue<Payment> meows = new PriorityQueue<>();
    private static Stack<Long> stack = new Stack<>();

    public Payment(Long oldEpoch, String txnld, String tier) {
        if (tier.equalsIgnoreCase(PLATINUM)) {
            this.newEpoch = oldEpoch - 3000;
        } else if (tier.equalsIgnoreCase(GOLD)) {
            this.newEpoch = oldEpoch - 2000;
        } else if (tier.equalsIgnoreCase(SILVER)) {
            this.newEpoch = oldEpoch - 1000;
        } else {
            this.newEpoch = oldEpoch - 0;
        }

        newEpochHolder = this.newEpoch;

        if (!stack.isEmpty()) {
            if (stack.contains(newEpochHolder)) {
                newEpochHolder += 1L;
            }
        }

        if (stack.size() == 1800) {
            stack.clear();
        }

        this.newEpoch = newEpochHolder;
        this.txnld = txnld;
        this.tier = tier;

    }

    public Long getOldEpoch() {
        return oldEpoch;
    }

    public Long getNewEpoch() {
        return newEpoch;
    }

    public String getTxnld() {
        return txnld;
    }

    public String getTier() {
        return tier;
    }

    public int compareTo(Payment otherTransaction) {
        return this.getNewEpoch().compareTo(otherTransaction.newEpoch);
    }

    public String toString() {
        return this.getTxnld();
    }

    public static String toStr(PriorityQueue<Payment> q) {
        if (stack.size() == 150) {

        }
        String result = "";
        int i = 0;
        while (!q.isEmpty() && i < 100) {
            result += q.poll() + " ";
            i++;
        }
        return result.trim();
    }

    public static void main(String[] args) {
        final String EXIT = "EXIT";
        final String REBOOT = "REBOOT";

        Long epoch;
        String txnld, tier;

        int diff = 0;
        Long lastAddedEpoch = 0L;
        boolean pass = false;

        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine()) {
            try {
                String in = scan.nextLine();
                in = in.trim();

                if (in.equalsIgnoreCase(EXIT)) {
                    break;
                } else if (in.equalsIgnoreCase(REBOOT)) {
                    meows.clear();
                } else {
                    String[] transaction = in.split("\\s+", 3);
                    epoch = Long.valueOf(transaction[0]);
                    txnld = transaction[1];
                    tier = transaction[2];

                    if (!meows.isEmpty()) {
                        lastAddedEpoch /= 1000;

                        Long epochTemp = epoch;
                        epochTemp /= 1000;

                        diff = epochTemp.intValue() - lastAddedEpoch.intValue();
                        lastAddedEpoch = epoch;
                    } else {
                        lastAddedEpoch = epoch;
                    }

                    Payment transactionObj = new Payment(epoch, txnld, tier);
                    meows.add(transactionObj);

                    Long round = lastAddedEpoch % 1000;
                    if (diff == 1 || pass) {
                        if (round.intValue() != 0) {
                            String ans = toStr(meows);
                            System.out.println(ans);
                            diff = 0;
                            pass = false;
                        } else {
                            pass = true;
                        }
                    }
                }
            } catch (InputMismatchException e) {
                return;
            } catch (NumberFormatException e) {
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                return;
            }
        }
    }
}
