
import java.util.*;

class Payment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PriorityQueue<Transactions> queue_transaction = new PriorityQueue<>();
        long second_digit1;
        long second_digit2 = 0;
        boolean isClear = false;
        while(in.hasNextLine()){
                try{
                    String input_transaction = in.nextLine();
                    if(input_transaction.equals("EXIT") || input_transaction.equals("REBOOT")){
                        if(input_transaction.equals("EXIT")){
                            break;
                        }
                        if(input_transaction.equals("REBOOT")){
                            queue_transaction.clear();
                        }
                        
                    }else{
                        String[] transaction_details = input_transaction.split(" ");
                        long transaction_as_long = Long.valueOf(transaction_details[0]);
                        second_digit1 = transaction_as_long;
                        if(queue_transaction.isEmpty()){
                            second_digit2 = transaction_as_long;
                        }
                        
                        Transactions meowtransaction = new Transactions(transaction_as_long,transaction_details[1],transaction_details[2]);
                        switch(transaction_details[2]){
                            case "BRONZE":
                                queue_transaction.offer(meowtransaction);
                                break;
                            case "SILVER":
                                meowtransaction.setEpoch_time_ms(meowtransaction.getEpoch_time_ms()-1000);
                                queue_transaction.offer(meowtransaction);
                                break;
                            case "GOLD":
                                meowtransaction.setEpoch_time_ms(meowtransaction.getEpoch_time_ms()-2000);
                                queue_transaction.offer(meowtransaction);
                                break;
                            case "PLATINUM":
                                meowtransaction.setEpoch_time_ms(meowtransaction.getEpoch_time_ms()-3000);
                                queue_transaction.offer(meowtransaction);
                                break;
                            default:
                                break;
                        }
                        
                        if(second_digit1/1000 != second_digit2/1000 && second_digit1 > second_digit2/1000*1000+1000){//second_digit1 > second_digit2 //second_digit1 != second_digit2
//                            System.out.println(second_digit1);
//                            System.out.println(second_digit2);
//                            System.out.println((second_digit2/1000)*1000);
                            second_digit2 = transaction_as_long;
                            int i = 0;
                            String[] joined_id;
                            Long[] joined_epoch;
                            if(!queue_transaction.isEmpty()){
                                if(queue_transaction.size()>=100){
                                    joined_epoch = new Long[100];
                                    joined_id = new String[100];
                                    while(!queue_transaction.isEmpty()){
                                        joined_epoch[i] = queue_transaction.peek().getEpoch_time_ms( );
                                        joined_id[i] = queue_transaction.poll().getTxn_id();
                                        i++;
                                        if(i==100)break;
                                    }
                                }else{
                                    joined_epoch = new Long[queue_transaction.size()];
                                    joined_id = new String[queue_transaction.size()];
                                    while(!queue_transaction.isEmpty()){
                                        joined_epoch[i] = queue_transaction.peek().getEpoch_time_ms( );
                                        joined_id[i] = queue_transaction.poll().getTxn_id();
                                        i++;
                                        if(i==100)break;
                                    }
                                }
                                String hundredths_transaction = String.join(" ", joined_id);
                                System.out.println(hundredths_transaction);
//                            for(int j = 0; j<joined_id.length ; j++){
//                                System.out.println(j + " " + joined_epoch[j] + " " + joined_id[j]);
//                            }
                            }
                        }  
                    }
                    
                }catch(Exception e){
                    return;
                }
        }
    }
}

class Transactions<E> implements Comparable<Transactions>{
    private Long epoch_time_ms;
    private String txn_id;
    private String tier;
    private Integer tier_int;

    public Transactions(Long epoch_time_ms, String txn_id, String tier) {
        this.epoch_time_ms = epoch_time_ms;
        this.txn_id = txn_id;
        this.tier = tier;
        
        switch(this.tier){
                        case "BRONZE":
                            tier_int = 0;
                            break;
                        case "SILVER":
                            tier_int = 1;
                            break;
                        case "GOLD":
                            tier_int = 2;
                            break;
                        case "PLATINUM":
                            tier_int = 3;
                            break;
                        default:
                            break;
                    }
    }

    public Integer getTier_int() {
        return tier_int;
    }

    public void setTier_int(int tier_int) {
        this.tier_int = tier_int;
    }

    public Long getEpoch_time_ms() {
        return epoch_time_ms;
    }

    public void setEpoch_time_ms(Long epoch_time_ms) {
        this.epoch_time_ms = epoch_time_ms;
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
    
    
    @Override
    public int compareTo(Transactions t) {
        if(this.getEpoch_time_ms().compareTo(t.getEpoch_time_ms())==0){
            return this.getTier_int().compareTo(t.getTier_int());
        }
        return this.getEpoch_time_ms().compareTo(t.getEpoch_time_ms());
    }

    @Override
    public String toString() {
        return "time : " + epoch_time_ms + "ID :  "+ txn_id + "\n";
    }
    
    
}
