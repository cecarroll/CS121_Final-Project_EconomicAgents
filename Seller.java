

public class Seller extends Agent {

	public Seller(){
        this.valuation = 0;
        this.adjusted = false;
    }
    public Seller(double valuation, double bid){
        this.valuation = valuation;
		this.bid = bid;
        this.adjusted = false;
    }
	public void Sell(Agent sellingAgent, Agent buyingAgent, double saleprice){
		sellingAgent.setKeepGoing(false);
		
		buyingAgent.setKeepGoing(false);
		
		sellingAgent.setProfit(sellingAgent.getProfit() + saleprice - sellingAgent.getValuation());
		buyingAgent.setProfit(buyingAgent.getProfit() + buyingAgent.getValuation() - saleprice);
		System.out.printf("The new profit of the seller is %.2f. %nThe new profit of the buyer is %.2f.%n", sellingAgent.getProfit(), buyingAgent.getProfit());
	}

	@Override
	public void takeTurn(double recentSales0,double recentSales1,double recentSales2,double recentSales3,double recentSales4){

		double avg = (recentSales0 + recentSales1 + recentSales2 + recentSales3 + recentSales4)/5;
		double avgWithValuation = (recentSales0 + recentSales1 + recentSales2 + recentSales3 + recentSales4 + this.valuation)/6;
		if(this.bid > this.valuation){
			this.bid = (recentSales0 + recentSales1 + recentSales2 + recentSales3 + recentSales4 + this.valuation - 20)/6;
			if (this.bid > 20){
				this.bid = (this.valuation*.2 + avg) / 1.2;
			}
		}

		if((keepGoing == false) && (adjusted == false)){
			this.bid = avg*1.05;
			this.adjusted = true;
		}
		if(this.bid < this.valuation){
			this.bid = this.valuation + 10;
		}
		
	}
}




