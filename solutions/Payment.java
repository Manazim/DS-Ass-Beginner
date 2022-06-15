import java.util.*;

public class Payment implements Comparable<Payment>{
    
    // variable block
    private Long oldEpoch;
    private Long newEpoch;
    private Long newEpochHolder;
    private String txnId;
    private String tier;
    
    final String PLATINUM = "PLATINUM";
    final String GOLD = "GOLD";
    final String SILVER = "SILVER";
    
    static PriorityQueue<Payment> meowsPQ = new PriorityQueue<>();
    private static Stack<Long> stack = new Stack<>();
    // end of variale block
    
    public Payment(Long oldEpoch, String txnId, String tier) {
        if (tier.equalsIgnoreCase(PLATINUM)) {
            this.newEpoch = oldEpoch - 3000;
        }
        else if (tier.equalsIgnoreCase(GOLD)) {
            this.newEpoch = oldEpoch - 2000;
        }
        else if (tier.equalsIgnoreCase(SILVER)) {
            this.newEpoch = oldEpoch - 1000;
        } 
        else {
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
        stack.push(this.newEpoch);

        this.oldEpoch = oldEpoch;
        this.txnId = txnId;
        this.tier = tier;
    }

    public Long getOldEpoch() {return oldEpoch;}
    
    public Long getNewEpoch() {return newEpoch;}

    public String getTxnId() {return txnId;}

    public String getTier() {return tier;}
    
    @Override 
    public int compareTo(Payment otherTransaction) {
        return this.getNewEpoch().compareTo(otherTransaction.newEpoch);
    }
    
    @Override
    public String toString() {
        return this.getTxnId();
    }
    
    public static String toStr(PriorityQueue<Payment> q) {
        if (stack.size() == 150) {

        }
        String result = "";
        int i = 0;
        while(!q.isEmpty() && i < 100){
            result += q.poll() + " ";
            i++;
        }
        return result.trim();
    }
    
    public static void main(String[] args) {
        // variables block
        final String EXIT = "EXIT";
        final String REBOOT = "REBOOT";
        
        Long epoch;
        String txnId, tier;
        
        int diff = 0;
        Long lastAddedEpoch = 0L;
        boolean pass = false;
        // end of variale block
                
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNextLine()) {
            try {
                String in = sc.nextLine();
                in = in.trim();
                
                if (in.equalsIgnoreCase(EXIT)) {
                    break;
                } else if (in.equalsIgnoreCase(REBOOT)) {
                    meowsPQ.clear();
                } else {
                    String [] transaction = in.split("\\s+", 3);
                    epoch = Long.valueOf(transaction[0]);
                    txnId = transaction[1];
                    tier = transaction[2];
                    
                    if (!meowsPQ.isEmpty()) {
                        lastAddedEpoch /= 1000;
                        
                        Long epochTemp = epoch;
                        epochTemp /= 1000;
                        
                        diff = epochTemp.intValue() - lastAddedEpoch.intValue();
                        lastAddedEpoch = epoch;
                        
                    } else {
                        lastAddedEpoch = epoch;
                    }
                    
                    Payment transactionObj = new Payment(epoch, txnId, tier);
                    meowsPQ.add(transactionObj);
                    
                    Long round = lastAddedEpoch % 1000;
                    if (diff == 1 || pass) { 
                        if (round.intValue() != 0) {
                            String ans = toStr(meowsPQ);
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
