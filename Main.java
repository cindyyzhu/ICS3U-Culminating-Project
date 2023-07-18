//Cindy & Stella
//ICS3U Culminating Project
//June 12, 2023

//imports necessary libraries
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import Board.GameBoard; //imports the boardgame

public class Main {
  //creates necessary variables for the game to function
  private static JPanel contentPanel;
  private static CardLayout cardLayout;
  public static int numOfPlayers;
  private static String [] playerUsernames;

  public static void startScreen() {
    JFrame welcomeFrame = new JFrame("Culminating Game Project");
    welcomeFrame.setLayout(new BorderLayout());
    welcomeFrame.setSize(500, 360);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  // Create the content panel with CardLayout
  contentPanel = new JPanel();
  cardLayout = new CardLayout();
  contentPanel.setLayout(cardLayout);

  // First screen: title and start button
  JPanel titlePanel = createTitlePanel();
  contentPanel.add(titlePanel, "title");

  // Second screen: Set up Players
  JPanel playerPanel = createPlayerPanel();
  contentPanel.add(playerPanel, "player");

  // Third screen: Set up Usernames
  JPanel usernamePanel = new JPanel(); // Create an empty panel for now
  contentPanel.add(usernamePanel, "username");
        
  // Fourth screen: Instructions
  JPanel instructionsPanel = createInstructionsPanel();
        contentPanel.add(instructionsPanel, "instructions");

  welcomeFrame.add(contentPanel, BorderLayout.CENTER);
  welcomeFrame.setVisible(true);
    }
  
//Title Screen
private static JPanel createTitlePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    JLabel label = new JLabel("<html><div style='text-align:center;'>"
      + "<span style='font-size:20px;'>Dice Dash</span><br>"
      + "<span style='font-size:18px;'>Cindy &amp; Stella</span><br>"
      + "<span style='font-size:16px;'>June 12, 2023</span></div></html>");
// border preferences
  Border border = BorderFactory.createLineBorder(Color.BLACK);
    label.setBorder(border);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setPreferredSize(new Dimension(400, 200));
    panel.add(label, BorderLayout.CENTER);

  // Start button: title screen
  JButton startButton = new JButton("Start");
  startButton.addActionListener(new ActionListener() {
  @Override
  public void actionPerformed(ActionEvent e) {
    cardLayout.show(contentPanel, "player");// Transition to the "player" screen
    }
});
  panel.add(startButton, BorderLayout.SOUTH);

  return panel;
}

// Player Screen
public static JPanel createPlayerPanel() {
  JPanel panel = new JPanel();
  panel.setLayout(new BorderLayout());

  JLabel titleLabel = new JLabel("<html><div style='text-align:center;'>"
   + "<span style='font-size:20px;'>Choose Number of Players (2-4)</span></div></html>");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
 panel.add(titleLabel, BorderLayout.NORTH);

JPanel inputPanel = new JPanel(new FlowLayout());
JLabel inputLabel = new JLabel("Number of Players: ");
  JTextField inputField = new JTextField(5);
  inputPanel.add(inputLabel);
  inputPanel.add(inputField);
  panel.add(inputPanel, BorderLayout.CENTER);

  JButton nextButton = new JButton("Next");
  nextButton.addActionListener(new ActionListener() {
 @Override
public void actionPerformed(ActionEvent e) {
String inputText = inputField.getText().trim();
  try {
    int inputNum = Integer.parseInt(inputText);
    if (inputNum >= 2 && inputNum <= 4) {
        numOfPlayers = inputNum; // Store the valid number of players
        createUsernamePanel(); // Call the username panel creation method
      
  cardLayout.show(contentPanel, "username");
        } else {                  JOptionPane.showMessageDialog(panel,
"Invalid number of players. Please enter a number between 2 and 4.",
"Invalid Input", JOptionPane.ERROR_MESSAGE);
  }
} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(panel,
"Invalid input format. Please enter a number between 2 and 4.",
"Invalid Input", JOptionPane.ERROR_MESSAGE);
           }
        }
    });
        panel.add(nextButton, BorderLayout.SOUTH);

        return panel;
    }

// Username Screen
  public static void createUsernamePanel() {
    JPanel usernamePanel = new JPanel();
    usernamePanel.setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("<html><div style='text-align:center;'>"
    + "<span style='font-size:20px;'>Usernames</span></div></html>");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
usernamePanel.add(titleLabel, BorderLayout.NORTH);

  JPanel userPanelsContainer = new JPanel(); // Create a new container for user panels
  userPanelsContainer.setLayout(new BoxLayout(userPanelsContainer, BoxLayout.Y_AXIS)); // Use vertical BoxLayout

JTextField[] userFields = new JTextField[numOfPlayers]; // Array to store the text fields for usernames

  for (int i = 0; i < numOfPlayers; i++) {
    JPanel userPanel = new JPanel(new FlowLayout());
      JLabel userLabel = new JLabel("Enter Username for Player " + (i + 1));
      JTextField userField = new JTextField(7);
        userPanel.add(userLabel);
        userPanel.add(userField);
        userPanelsContainer.add(userPanel); // Add each user panel to the container

        userFields[i] = userField; // Store the text field in the array
        }

usernamePanel.add(userPanelsContainer, BorderLayout.CENTER); // Add the container to the main panel
// Next button
JButton nextButton = new JButton("Next");
  nextButton.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {
    playerUsernames = new String[numOfPlayers]; // Array to store the usernames

  for (int i = 0; i < numOfPlayers; i++) {
    playerUsernames[i] = userFields[i].getText().trim();
   }
  // Transition to the instructions screen
   cardLayout.show(contentPanel, "instructions");
     }
  });
  usernamePanel.add(nextButton, BorderLayout.SOUTH);

// Replace the empty "username" panel with the actual panel
  contentPanel.remove(2);
  contentPanel.add(usernamePanel, "username");
  contentPanel.revalidate();
  }

//Instructions Screen
private static JPanel createInstructionsPanel() {
  JPanel panel = new JPanel();
  panel.setLayout(new BorderLayout());

  JLabel label = new JLabel("<html><div style='text-align:center;'>"
  + "<span style='font-size:20px;'>Dice Dash Instructions</span><br>"
  + "<span style='font-size:13px;'>\n In this game each player rolls the dice and moves forward on the board the number that they roll.\n\n HOWEVER IF you land on a special square you get to play a mini game for FUN!\n\nThe player to reach the end of the board first is the winner!\n\nGood luck and have fun!!</span></div></html>");

// border preferences
Border border = BorderFactory.createLineBorder(Color.BLACK);
  label.setBorder(border);
  label.setHorizontalAlignment(JLabel.CENTER);
  label.setVerticalAlignment(JLabel.CENTER);
  label.setPreferredSize(new Dimension(400, 200));
    panel.add(label, BorderLayout.CENTER);
  
// Next Button
  JButton gameboardButton = new JButton("Next");
gameboardButton.addActionListener(new ActionListener() {
  
@Override
public void actionPerformed(ActionEvent e) {
     GameBoard game = new GameBoard(playerUsernames);
  }
 });
   panel.add(gameboardButton, BorderLayout.SOUTH);

   return panel;
    }

public static void main(String[] args) {
  SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        startScreen();
        }
     });
  }
}
