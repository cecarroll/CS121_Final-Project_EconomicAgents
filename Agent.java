import java.util.*;


public class Agent implements HasMenu{
    public double valuation;
    public double bid; 
    public boolean keepGoing = true;
    public double profit = 0.0; 
    public boolean adjusted; 

    public Agent(){
        this.valuation = 0;
        
    }
    public Agent(double valuation){
        this.valuation = valuation;

    }
    public double getBid(){
        return bid;
    }
    public double getValuation(){
        return valuation;
    }

    public void setValuation(double valuation){
        this.valuation = valuation;
    }
    public void setBid(double bid){
        this.bid = bid;
    }
    public double getProfit(){
        return profit;
    }
    public boolean getKeepGoing(){
        return keepGoing;
    }

    public void setProfit(double profit){
        this.profit = profit;
    }
    public void setKeepGoing(boolean keepGoing){
        this.keepGoing = keepGoing;
        
    }
    public void setAdjusted(boolean adjusted){
        this.adjusted = adjusted;
    }
    public double calcBid(){
        System.out.printf("I bid: %.2f%n", getBid());
        return bid;
    }
    public void takeTurn(double recentSales0,double recentSales1,double recentSales2,double recentSales3,double recentSales4){
       
    }
    public void start(){
        boolean keepGoing = true;
        String response;
        double value;
        while(keepGoing){
            response = menu();
            if(response.equals("0")){
                keepGoing = false;
                calcBid();
            }else if(response.equals("1")){
                System.out.printf("This Agent's valuation is: %.2f%n", getValuation()); 
            }else if(response.equals("2")){
            keepGoing = false;
            System.out.println("It's so Joever.");
            }
        }
    }
    public String menu(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Agent Menu %n Choose a Menu Option: %n 0) make bid %n 1) check my valuation %n 2) skip turn %n");
        String value = input.next(); 
        return value;
    }

    //public static void main(String[] args) {
     //   Agent a = new Agent(1, 2);
     //   System.out.printf("Your bid was: %.2f%n", a.getBid());
    ///    a.start();
    //}

}