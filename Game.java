import java.util.*;

public class Game implements HasMenu{
    boolean keepGoing;
    double price;
    int size;
    List<Double> salesPrices = new ArrayList<>();
    List<Double> bidBuyPrices = new ArrayList<>();
    List<Double> bidSalePrices = new ArrayList<>();
    List<Agent> agents = new ArrayList<>();



    public Game(){
        keepGoing = true;
        price = 0;
        salesPrices.add(0.0);
        bidSalePrices.add(0.0);
        bidBuyPrices.add(0.0);
    }
    public Game(boolean keepGoing, double price, int size){
        this.keepGoing = keepGoing;
        this.price = price;
        this.size=size;
        agents = new ArrayList<>();
        salesPrices.add(0.0);
        bidBuyPrices.add(0.0);    
        bidSalePrices.add(0.0);
        int x=1;
        for (int i=0; i<size; i ++) { 
            double agentValuation = (x*(2.0/size)*price);
                if(i<(size/2)){
                Seller a = new Seller(agentValuation, price*2);
                agents.add(a);
                bidSalePrices.add(a.getBid());
                }else{
                Buyer a = new Buyer(agentValuation, 0);    
                agents.add(a);
                bidBuyPrices.add(a.getBid());
                
                }
            x++;
           
        }
    }
    public String checkRoundBidStats(){
        double totalBuyerProfit = 0;
        double totalSellerProfit = 0;
        for (Agent x : agents){
                    if (x instanceof Buyer){
                        totalBuyerProfit = totalBuyerProfit + x.getProfit(); 
                    } else {
                        totalSellerProfit = totalSellerProfit + x.getProfit();
                    }
        }
        String returnString = String.format("checking sale stats... %s %n ... %n And buyer bid stats ... %s %n And seller bid stats ... %s. %n Your total profit among sellers is %.2f, and among buyers it is %.2f. %n", salesPrices.toString(), bidBuyPrices.toString(), bidSalePrices.toString(), totalSellerProfit, totalBuyerProfit);
        return returnString; 
    }
    public String checkBidStats(){
        String returnString = String.format("checking sale stats... %s %n ... %n And buyer bid stats ... %s %n And seller bid stats ... %s %n", salesPrices.toString(), bidBuyPrices.toString(), bidSalePrices.toString());
        return returnString;
        
    }
    public void start(){
        keepGoing = true;
        String response;
        double salesCount = 0;
        double sale;
        while(keepGoing){
            response = roundMenu();
            if(response.equals("0")){
                salesCount=0;
                for (Agent a : agents) {
                    a.setKeepGoing(true);
                    a.setAdjusted(false);
                }
            }else if(response.equals("1")){
                System.out.printf("%s", checkRoundBidStats()); 
            }else if(response.equals("2")){
                keepGoing = false;
                System.out.println("It's so Joever.");
            }
            
            
            
            while(salesCount<(size/2.0)){ // Within the game loop, we want to loop through each agent's agent loops 
               
            response = menu();
            if(response.equals("0")){ 
                for (Agent x : agents){
                    if (x instanceof Buyer){
                        ((Buyer)x).takeTurn(bidBuyPrices.get(bidBuyPrices.size()-1),bidBuyPrices.get(bidBuyPrices.size()-2),bidBuyPrices.get(bidBuyPrices.size()-3),bidBuyPrices.get(bidBuyPrices.size()-4),bidBuyPrices.get(bidBuyPrices.size()-5));
                        bidBuyPrices.add(x.getBid()); 
                    } else {
                        ((Seller)x).takeTurn(bidSalePrices.get(bidSalePrices.size()-1),bidSalePrices.get(bidSalePrices.size()-2),bidSalePrices.get(bidSalePrices.size()-3),bidSalePrices.get(bidSalePrices.size()-4),bidSalePrices.get(bidSalePrices.size()-5));
                        bidSalePrices.add(x.getBid()); 
                    }
                for (Agent a : agents) {
                    if(a.getKeepGoing()){
                              
                        for (Agent b : agents) {
                            if(b.getKeepGoing()){
                                
                                if (a instanceof Buyer){
                                    if (b instanceof Seller){
                                        if ((a.getValuation()>b.getBid()) && (b.getValuation()<b.getBid()) && (a.getKeepGoing()) &&(b.getKeepGoing())){
                                            sale = b.getBid();
                                            salesPrices.add(sale);
                                            salesCount++;
                                            System.out.printf("Buy of %.2f achieved! %n", b.getBid());
                                            ((Buyer)a).Buy(a, b, sale);
                                            break;
                                        }

                                    } 
                                    
                            
                                } else {
                                    //Seller code here
                                    if(b instanceof Buyer)
                                        if((a.getValuation()<b.getBid()) && (b.getValuation()>b.getBid()) && (a.getKeepGoing()) && (b.getKeepGoing())){
                                            sale = b.getBid();
                                            System.out.printf("Sale of %.2f achieved! %n", b.getBid());
                                            ((Seller)a).Sell(a, b, sale);
                                            salesPrices.add(sale);
                                            salesCount++;
                                            break;


                                    }
                            
                                }
                            }
                        }
                          
                    }
                }


                
                
                
                }
            // we want to leave this empty because if we want the next agent then the 
            // program to just do nothing with the menu and continue the for loop
            }else if(response.equals("1")){
                System.out.printf("%s", checkBidStats()); 
            }else if(response.equals("2")){
                keepGoing = false;
                salesCount = size;
                System.out.println("It's so Joever.");
                break;
            }
            }
        }
    }
    public String menu(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Game Menu %n Choose a Menu Option: %n 0) Take Turn %n 1) Check Bid Stats %n 2) Quit Game %n");
        String value = input.next(); 
        return value;
    }
    public String roundMenu(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Between-Rounds Menu %n Choose a Menu Option: %n 0) See A Round of Bids %n 1) Check Bid Stats %n 2) Quit Game %n");
        String value = input.next(); 
        return value;
    }
    
}