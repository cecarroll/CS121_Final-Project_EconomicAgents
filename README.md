# Final-Project


I am creating economic agents that will accurately negotiate a price in a marketplace. 

I had the idea to do an interface for menu like we did before. 

Then we would have a game which actually ran the game and presented the frames of the game. 

My first stab at implementation will have buyers and sellers who are agents in a game. Each of these nouns are classes. 

I'm gonna take a stab at implementing them.

For later so that it's written here:
Bids can be accepted as soon as one is above the price that a prospective seller could sell at. There is only one commodity bought per round per buyer and sold per seller. Excess is a goal to maximize for each agent. This may eventually lead to strategy in time where someone waits for a better bid. But for right now, bids will be random and simply more than the minimums for sellers and less than the maximum for buyers.

When buyers are making bids, they will have to pass their bids to the market and the other agents will have to evaluate the bid and if both agree on the price, they will dip from the game

So, the agents finding the bid can just be the game class tracking the bids and the agents having another function that they do when their start is called that calls the game to ask it what the bids are looking like. That can be a list. There's no real reason it needs to be ordered. Though it's looking more and more like these bids need to be a data type. 

An agent whose turn it is in the queue is checking to see if there are any for sale signs for less than his maximum buy price. 
He indexes the list and finds the best option for him. He accepts it and pockets the change surplus joy coins.
The person selling then has to get contacted somehow about the commodity they just sold.

I figured out how to have it go both ways. Whenever agents place a bid, they make a bid object that has the value of it, and importantly, the agent gets a pointer to the bid. When the bid gets accepted, we can delete it and then at the start of the next turn, we can check whether it exists and just do the accounting and end the turn. 
