//puts it within the Board package
package Board;

//imports the necessary libraries for the code to run
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

//imports the minigames into this gameboard
import TicTacToe.MiniGame1;
import NumberGuessing.MiniGame2;

public class GameBoard extends JFrame implements ActionListener {
    private static final int BOARD_SIZE = 5; //column + row size
    private int NUM_PLAYERS; //number of players dependant on what user enters in the Main class

    //arrays for game elements
    private int currentPlayer;
    private int[] playerPositions;
    private JButton diceButton;
    private JLabel[] counters;
    private JPanel[] squares;
    private String[] playerNames;
    private static final Color highlightColor = new Color(0, 0, 0); //higlight color
    private static final Font boldFont = new Font("Arial", Font.BOLD, 12);
    private static final Font normalFont = new Font("Arial", Font.ITALIC, 12);

    public GameBoard(String[] playerUsernames) {
        //new variable to allow for the player's own input usernames to be displayed
        playerNames = playerUsernames;
        NUM_PLAYERS = playerNames.length;

        setAlwaysOnTop( true ); //makes the frame on top of the previous one
      
        setTitle("Game Board");
        setMinimumSize(new Dimension (500, 350)); //sets minimum size of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //sets the positions of all of the players off the screen for now
        currentPlayer = 0;
        playerPositions = new int[NUM_PLAYERS];
      for (int i = 0; i < playerPositions.length; i++) {
        playerPositions[i] = -1;
      }

        //sets what would represent each of the players
        counters = new JLabel[NUM_PLAYERS];
        squares = new JPanel[BOARD_SIZE * BOARD_SIZE];

        // Creating the game board panel
        JPanel gamePanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
          gamePanel.setBackground(Color.BLACK);

        //creates squares for the entire boardgame
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            squares[i] = new JPanel(new BorderLayout());
                    squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        squares[i].setBackground(Color.WHITE);// makes the original background color white

          //numbers each of the squares on the boardgame
            JLabel numberLabel = new JLabel(Integer.toString(i + 1), SwingConstants.CENTER);
            squares[i].add(numberLabel, BorderLayout.CENTER);
            gamePanel.add(squares[i]);
        }

        // Player usernames and their scores
        JPanel counterPanel = new JPanel(new GridLayout(1, NUM_PLAYERS));
        counterPanel.setBackground(Color.WHITE); //sets original background to white
        for (int i = 0; i < NUM_PLAYERS; i++) {
            counters[i] = new JLabel(playerNames[i], SwingConstants.CENTER);
          
          //changes the colour and font of the players
            counters[i].setForeground(getPlayerColor(i));
            counters[i].setFont(normalFont);
            counterPanel.add(counters[i]);
        }

        // Creating the dice roll button
        diceButton = new JButton("Roll Dice");
        diceButton.addActionListener(this);

        // Creating the main panel and adding subpanels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(counterPanel, BorderLayout.SOUTH);
        mainPanel.add(diceButton, BorderLayout.NORTH);

        // Setting the main panel as the content panel
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == diceButton) {
            // Roll the dice
            int diceRoll = rollDice();
            // Move the counter for the current player
            moveCounter(currentPlayer, diceRoll);

            //sets to default color and font
            counters[currentPlayer].setForeground(getPlayerColor(currentPlayer));
            counters[currentPlayer].setFont(normalFont);

            // Switch to the next player
            currentPlayer = (currentPlayer + 1) % NUM_PLAYERS;

            //higlights current player by changing player name to black color & bolds the text
            counters[currentPlayer].setForeground(highlightColor);
            counters[currentPlayer].setFont(boldFont);

        }
    }

  
    // Roll the dice and return the result (random number between 1 and 6)
    private int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    // Move the counter for the given player by the specified number of steps
    private void moveCounter(int player, int steps) {
        //changes the player's position
        int currentPosition = playerPositions[player];
        int newPosition = steps;
    
        if (currentPosition > 0) {
          newPosition = currentPosition + steps;
        }
      
        // Check if the player reached the last square
        if (newPosition > 25) {
            newPosition = 25;
        }
        playerPositions[player] = newPosition;

      //changes the color of that square of that user's position
      setPositionColor(newPosition);
      setPositionColor(currentPosition);

      //directs the user to play some minigames if they land on squares 10 or 20
        if (newPosition == 10) {
            MiniGame1 tictactoe = new MiniGame1();
        }
        if (newPosition == 20) {
            MiniGame2 numberguessing = new MiniGame2();
        }
      
      // Update the text of the counter label with the number rolled on the dice
      counters[player].setText((playerNames[player]) + ": " + (newPosition) + " (Dice #: " + steps + ")");      

        // Check if the player reached the last square and end the game
        if ((newPosition) >= 25) {
            JOptionPane.showMessageDialog(this, "Player " + (playerNames[player]) + " wins!");
            System.exit(0);
        }
    }

  //This is in case the player rolls a dice number that would make them out of range
  private void setPositionColor(int pos) {
    if ((pos < 0) || (pos > 25)) {
      return;
    }
        ArrayList<Integer> playersOnSquare = new ArrayList<>();
        for (int i = 0; i < NUM_PLAYERS; i++) {
            if (playerPositions[i] == pos) {
                playersOnSquare.add(i);
            }
        }
    squares[pos-1].removeAll();
    JLabel numberLabel = new JLabel(Integer.toString(pos), SwingConstants.CENTER);
    
      squares[pos-1].add(numberLabel, BorderLayout.CENTER);
    
    // Update the colors of the squares
        if (playersOnSquare.size() >= 1) {
            // If multiple players are on the same square, split the square into multiple colors 
            squares[pos-1].setLayout(new GridLayout(2, 2));
            
            for (int i = 0; i < playersOnSquare.size(); i++) {
                JPanel playerPanel = new JPanel();
                playerPanel.setBackground(getPlayerColor(playersOnSquare.get(i)));
                squares[pos-1].add(playerPanel);
            }
        }
    else {
      //if there is no player on that square, set it back to white
      JPanel playerPanel = new JPanel();
      playerPanel.setBackground(Color.WHITE);
      squares[pos-1].add(playerPanel);
      }
    
  }
    // Return the color associated with the given player
    private Color getPlayerColor(int player) {
        switch (player) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            default:
                return Color.BLACK;
        }
    }
}
