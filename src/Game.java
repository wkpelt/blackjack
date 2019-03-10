import java.util.Scanner;

import org.json.JSONObject;

public class Game {
    protected String playerName;
    protected int round;
    Player player;
    Player dealer;
    Deck deck;
    Scanner sc;

    public Game() {
        System.out.println("Blackjack");
        System.out.println("What is your name?");
        this.sc = new Scanner(System.in);
        this.playerName = sc.nextLine();
        this.round = 0;
        this.deck = new Deck();
        ifPlayerExists();
    }
    
    public void ifPlayerExists() {
        boolean playerExists = JSONUtils.objectExists(playerName);
        
        if(playerExists) {
        	System.out.println("Welcome back " + playerName + "!");
        	this.player = new Player(playerName);
        	player.setBalance(JSONUtils.balanceOfPlayer(playerName));
        	System.out.println("Your balance is: " + player.getBalance() + "�");
        }
        else {
        	System.out.println("Welcome, " + playerName + "!");
        	this.player = new Player(playerName);
        	player.setBalance(100);
        	System.out.println("Your balance is: " + player.getBalance() + "�");
        }
    }

	public String givePlayerName() {
        return playerName;
    }

    public int giveRound() {
        return round;
    }

    public void startGame() {
        System.out.println("Type 'play' to start the game");
        while (true) {
        	if(deck.deckSize() <= 104) {
        		deck.shuffle();
        		System.out.println("Shuffling the deck!");
        	}
        	String playerCommand = sc.nextLine();
        	if(playerCommand.equals("save")) {
        		System.out.println("moi");
        		JSONUtils.savePlayer(player.getPlayerName(),player.getBalance());
        	}
        	if(playerCommand.equals("stop")) {
        		// pys�yt� peli jos er� on loppu
        		System.out.println("Stopped");
        	}
        	if(playerCommand.equals("play")) {
        			this.dealer = new Player("Dealer");
        			System.out.println("The round is beginning, pleace place your bet");
        			System.out.println("Your balance: " + player.getBalance());
        			int playerBet = sc.nextInt();
        			if (playerBet > player.getBalance()) {
        				System.out.println("menisit t�ihin :D");
        			}
        			else {
        				System.out.println("Bet accepted");
        				
        				player.addCard(deck.deal());
        				System.out.println("You get");
        				player.printLast();
        				
        				
        				dealer.addCard(deck.deal());
        				System.out.println("Dealer gets");
        				dealer.printLast();
        				
        				player.addCard(deck.deal());
        				System.out.println("You get");
        				player.printLast();
        				
        				dealer.addCard(deck.deal());
        				//(piilossa oleva kortti)
        				
        				player.printHand();
        				
    					System.out.println("What would you like to do?");
    					System.out.println("Hit, Stay, Double or Split");
        					
        					
    					if(sc.nextLine().equals("hit")) {
    						player.addCard(deck.deal());
    						player.printLast();
    					}
        			}
        			
        	}
        }

    }
}