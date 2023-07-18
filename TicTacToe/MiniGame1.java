//sets it within the TicTacToe package
package TicTacToe;

//imports all the necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MiniGame1 {
    private JButton[][] board;
    private boolean playerX;
    private int numMoves;

    //creates an array with the buttons to allow for the tictactoe to work
    public MiniGame1() {
        board = new JButton[3][3];
        playerX = true;
        numMoves = 0;

        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true); //always sets this on top of other screens

      //general layout of the game
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic-Tac-Toe");
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));

        initializeButtons(frame);

      //opens the frame
        frame.setVisible(true);
    }

  //shows each button
    private void initializeButtons(JFrame frame) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                button.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
JButton clickedButton = (JButton) e.getSource();

  //changes button accordingly to user input
if (clickedButton.getText().equals("")) {
  if (playerX) {
      clickedButton.setText("X");
      numMoves++;
      playerX = !playerX;

    //win message for user
  if (checkWin("X")) {
    JOptionPane.showMessageDialog(null, "You win!");
    frame.setVisible(false);

    return;
      }
  }

  //has the computer move
  if (numMoves < 9) {
      makeComputerMove();
      numMoves++;
      playerX = !playerX;

    //computer win message
      if (checkWin("O")) {
 
    JOptionPane.showMessageDialog(null, "Computer wins!");
     frame.setVisible(false);

  }
} else {
    JOptionPane.showMessageDialog(null, "It's a draw!"); //draw message
     frame.setVisible(false);

    }
}
}
});

board[row][col] = button;
frame.add(button);
  }
  }
  }

//randomly lets the computer make a move
private void makeComputerMove() {
  Random random = new Random();

  int row, col;
  do {
      row = random.nextInt(3);
      col = random.nextInt(3);
  } while (!board[row][col].getText().equals(""));

  board[row][col].setText("O");
}

private boolean checkWin(String player) {
  String[][] boardValues = new String[3][3];

  for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
          boardValues[row][col] = board[row][col].getText();
            }
        }

  // Check rows
  for (int row = 0; row < 3; row++) {
      if (!boardValues[row][0].equals("") && boardValues[row][0].equals(boardValues[row][1]) &&
                    boardValues[row][0].equals(boardValues[row][2]) && boardValues[row][0].equals(player)) {
  return true;
}
}

// Check columns
for (int col = 0; col < 3; col++) {
if (!boardValues[0][col].equals("") && boardValues[0][col].equals(boardValues[1][col]) &&
    boardValues[0][col].equals(boardValues[2][col]) && boardValues[0][col].equals(player)) {
      return true;
  }
}
  // Check diagonals
  if (!boardValues[0][0].equals("") && boardValues[0][0].equals(boardValues[1][1]) &&
    boardValues[0][0].equals(boardValues[2][2]) && boardValues[0][0].equals(player)) {
            return true;
        }
  
  if (!boardValues[0][2].equals("") && boardValues[0][2].equals(boardValues[1][1]) &&
                boardValues[0][2].equals(boardValues[2][0]) && boardValues[0][2].equals(player)) {
    return true;
}

return false;
}

private void resetGame(JFrame frame) {
for (int row = 0; row < 3; row++) {
    for (int col = 0; col < 3; col++) {
        board[row][col].setText("");
    }
}

playerX = true;
numMoves = 0;
}

public static void minigame1(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
    @Override
    public void run() {
        MiniGame1 game = new MiniGame1();
    }
});
}
}
