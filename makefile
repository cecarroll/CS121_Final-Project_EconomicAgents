HasMenu.class: HasMenu.java
	javac HasMenu.java
	
Agent.class: Agent.java HasMenu.class
	javac Agent.java

Seller.class Buyer.class: Agent.class Buyer.java Seller.java
	javac Buyer.java Seller.java

Game.class: Game.java Seller.class Buyer.class Agent.class HasMenu.class
	javac -g Game.java

Main.class: Game.class
	javac -g Main.java

run: Main.class
	java Main

debug: Game.class
	jdb Game

clean:
	rm -f *.class