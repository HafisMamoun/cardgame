package com.openclassrooms.cardgame.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.openclassrooms.cardgame.controller.GameController;

public class GameSwing implements GameViewable {
    GameController controller;
    
    JButton btnAddPlayer;
    JButton btnDealCards;
    JButton btnFindWinner;
    JTextArea textArea;
    char nextPlayerName = 'A';

    public void createAndShowGUI() {
    	// create the main display area, with enough initial space
        JFrame frame = new JFrame("MVC-SOLID-Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // all of the controls will appear vertically
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        addAddPlayerButton(contentPane);
        addDealCardsButton(contentPane);
        addFindWinnerButton(contentPane);
        
        addControllerCommandTracker(contentPane);

        frame.setVisible(true);
    }

    // when clicked, tell controller to add a player of the given name
    // this SHOULD bring up a dialog box to allow the user to enter a name
    // but that is beyond the scope of what we need to learn
    // for simplicity, we'll just tell it to add "Player A", "Player B", etc.
    private void addAddPlayerButton (Container contentPane) {
        btnAddPlayer = new JButton("Add A Player");
        addCenteredComponent (btnAddPlayer, contentPane);
        btnAddPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("Current players:\n");
                controller.addPlayer("Player " + nextPlayerName++);
            }
        });
    }
    
    // when clicked, tell controller to deal cards to players
    private void addDealCardsButton (Container contentPane) {
        btnDealCards = new JButton("Deal Cards");
        addCenteredComponent(btnDealCards, contentPane);
        btnDealCards.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("Cards dealt:\n");
                controller.startGame();
            }
        });
    }
    
    // when clicked, tell controller to flip cards and determine who won
    private void addFindWinnerButton(Container contentPane) {
	    btnFindWinner = new JButton("Find Winner");
	    addCenteredComponent(btnFindWinner, contentPane);
	    btnFindWinner.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            textArea.append("Show your cards:\n");
	            controller.flipCards();
	        }
	    });
    }
    
    // a simple place to display what the controller is telling us
    // very similar to our command line version    
    private void addControllerCommandTracker(Container contentPane) {
	    textArea = new JTextArea("Game Status\n", 100, 1);
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    addCenteredComponent(scrollPane, contentPane);
	    textArea.setSize(500, 500);
    }
	
	// make sure that every time something is added to the text area,
	// scroll down to the bottom so that it is visible
	// textArea control does not have an auto-scroll option
	// so we have to do it ourselves
	private void appendText (String text) {
		textArea.append(text + "\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
    
    // all controls are added so they are centered horizontally in the area
    private void addCenteredComponent(JComponent component, Container contentPane) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(component);
    }
	
    // GameViewable interface implementations
	public void setController(GameController controller) {
		this.controller = controller;
	}

	// same implementation as our CommandLineView
	// just show what the controller is telling us
    @Override
    public void showPlayerName(int playerIndex, String playerName) {
    	appendText("[" + playerIndex + "][" + playerName +"]");
    }

    @Override
    public void showCardForPlayer(int playerIndex, String playerName, String cardRank, String cardSuit) {
    	appendText("[" + playerName + "][" + cardRank + ":" + cardSuit + "]");
    }

    @Override
    public void showWinner(String winnerName) {
        appendText("Winner!\n" + winnerName);
    }

	@Override
	public void showFaceDownCardForPlayer(int playerIndex, String name) {
		appendText("[" + name + "][][]");
	}

	
	@Override
	public void promptForPlayerName() {
		System.out.println("Enter Player Name:");
		// it's ok to add names
	    btnAddPlayer.setEnabled(true);
	    
	    // it's ok to start, one a player has been added
	    btnDealCards.setEnabled(nextPlayerName > 'A');
	    
	    // but not to ask to find a winner
	    btnFindWinner.setEnabled(false);
	}

	@Override
	public void promptForFlip() {
		System.out.println("Press enter to reveal cards");
		
		// past the time to add names
		btnAddPlayer.setEnabled(false);
		
		// or deal cards
		btnDealCards.setEnabled(false);
		
		// but you can ask to find a winner
		btnFindWinner.setEnabled(true);
	}

	@Override
	public void promptForNewGame() {
		System.out.println("Press enter to deal again");
		
		// past the time to add names
		btnAddPlayer.setEnabled(false);
		
		// ok to deal cards for the next game
		btnDealCards.setEnabled(true);
		
		// can't find a winner yet
		btnFindWinner.setEnabled(false);
	}

	@Override
	public void add(GameViewable view) {
		// TODO Auto-generated method stub
		
	}
}
 