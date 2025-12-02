import java.util.*;

public class Game implements HasMenu{
    boolean keepGoing;
    double price;
    List<Agent> agents = new ArrayList<>();


    public Game(){
        keepGoing = true;
        price = 0;
    }
    public Game(boolean keepGoing, double price, int size){
        this.keepGoing = keepGoing;
        this.price = price;
        List<Agent> agents = new ArrayList<>();
        int x=1;
        for (int i=0; i<size; i ++) {
            
            double agentValuation = (x*(2/size)*price);
            Agent a = new Agent(agentValuation, 2);
            x++;
            agents.add(a);
        }
    }
    public String checkBidStats(){
        return "checking bid stats";
        
    }
    public void start(){
        keepGoing = true;
        String response;
        double value;
        while(keepGoing){ // Within the game loop, we want to loop through each agent's agent loops
            response = menu();
            System.out.println(agents.size());
            for (Agent a : agents) {
                if(response.equals("0")){
                
                
                }else if(response.equals("1")){
                    System.out.printf("%s", checkBidStats()); 
                }else if(response.equals("2")){
                    keepGoing = false;
                    System.out.println("It's so Joever.");
                }

            }
        }
    }
    public String menu(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Game Menu %n Choose a Menu Option: %n 0) Next Agent %n 1) Check Bid Stats %n");
        String value = input.next(); 
        return value;
    }
    public static void main(String[] args) {
        Game a = new Game(true, 10.00, 10);
        
        a.start();
    }

}