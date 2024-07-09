package WarGameGUI;

import Assets.Card;
import Assets.Player;
import GameLogic.AudioPlayer;
import GameLogic.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
/*
Diego Andino - Alejandro Guerra - Keven Quevedo
Boxuan Chen - Luka Beridze - Hemant Kumar - Diego Acosta
*/
public class WarGameGUI extends JFrame {
    
    // Declare game controller, audio player, and UI components
    private GameController gamePlay;
    private JLabel playerCardLB, computerCardLB;
    private JLabel playerWar1Label, playerWar2Label, computerWar1Label, computerWar2Label;
    private JLabel playDeckCountLB, playDiscCountLB, compDeckCountLB, compDiscCountLB;
    private JTextArea roundArea;
    private JButton startBtn, nextBtn, warBtn, menuBtn, newBtn, saveBtn, openBtn;
    private final AudioPlayer audioPlayer;

    // Paths for card back image and window icon
    private final String rearCardPath = "src/images/rear.gif";
    ImageIcon img = new ImageIcon("src/images/dizhu.gif");

    // Player instances and flag for playing against computer
    private final Player player1;
    private final Player player2;
    private final boolean vsType;

    // Constructor to initialize the GUI with players and game mode
    public WarGameGUI(Player player1, Player player2, boolean vsComputer) {
        this.player1 = player1;
        this.player2 = player2;
        this.vsType = vsComputer;
        setIconImage(img.getImage());
        setTitle("WAR GAME");
        setSize(850, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        audioPlayer = new AudioPlayer();
        setupGUI();
    }

    // Method to setup the GUI layout and components
    private void setupGUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        // Setup top text panel
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(new Color(0, 112, 0));
        JLabel nameLabel = new JLabel("WAR GAME");
        nameLabel.setForeground(Color.black);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel nameLabel2 = new JLabel(
                "Diego Andino - Alejandro Guerra - Keven Quevedo - Boxuan Chen - Luka Beridze - Hemant Kumar - Diego Acosta");
        nameLabel2.setForeground(Color.black);
        nameLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel2.setHorizontalAlignment(JLabel.CENTER);
        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(nameLabel2, BorderLayout.SOUTH);

        // Setup button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(0, 112, 0));
        startBtn = new JButton(vsType ? "Start PvE" : "Start PvP");
        startBtn.setBackground(new Color(0, 77, 0));
        nextBtn = new JButton("Next");
        nextBtn.setBackground(new Color(0, 77, 0));
        nextBtn.setEnabled(false);
        warBtn = new JButton("War");
        warBtn.setBackground(new Color(0, 77, 0));
        warBtn.setEnabled(false);
        menuBtn = new JButton("Quit");
        menuBtn.setBackground(new Color(0, 77, 0));
        newBtn = new JButton("New Game");
        newBtn.setBackground(new Color(0, 77, 0));
        saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(0, 77, 0));
        openBtn = new JButton("Open");
        openBtn.setBackground(new Color(0, 77, 0));

        // Add buttons to the button panel
        buttonPanel.add(startBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(warBtn);
        buttonPanel.add(menuBtn);
        buttonPanel.add(newBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(openBtn);

        setLocationRelativeTo(null);

        // Setup game text area
        roundArea = new JTextArea(5, 30);
        roundArea.setBackground(new Color(0, 112, 0));
        roundArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(roundArea);
        scrollPane.getViewport().setBackground(new Color(0, 90, 0));

        topPanel.add(namePanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        topPanel.add(scrollPane, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Setup middle panel for card displays and counts
        JPanel middlePanel = new JPanel(new GridLayout(3, 2));
        middlePanel.setBackground(new Color(0, 90, 0));
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBackground(new Color(0, 102, 0));
        JPanel computerPanel = new JPanel(new BorderLayout());
        computerPanel.setBackground(new Color(0, 102, 0));

        // Initialize card and count labels
        playerCardLB = new JLabel(new ImageIcon(rearCardPath));
        computerCardLB = new JLabel(new ImageIcon(rearCardPath));
        playerWar1Label = new JLabel();
        playerWar2Label = new JLabel();
        computerWar1Label = new JLabel();
        computerWar2Label = new JLabel();

        playDeckCountLB = new JLabel("Deck: 0");
        playDiscCountLB = new JLabel("Discard: 0");
        compDeckCountLB = new JLabel("Deck: 0");
        compDiscCountLB = new JLabel("Discard: 0");

        // Add components to player and computer panels
        playerPanel.add(playerCardLB, BorderLayout.CENTER);
        playerPanel.add(playDeckCountLB, BorderLayout.NORTH);
        playerPanel.add(playDiscCountLB, BorderLayout.SOUTH);

        computerPanel.add(computerCardLB, BorderLayout.CENTER);
        computerPanel.add(compDeckCountLB, BorderLayout.NORTH);
        computerPanel.add(compDiscCountLB, BorderLayout.SOUTH);

        // Add panels to middle panel
        middlePanel.add(playerPanel);
        middlePanel.add(computerPanel);
        middlePanel.add(playerWar1Label);
        middlePanel.add(computerWar1Label);
        middlePanel.add(playerWar2Label);
        middlePanel.add(computerWar2Label);

        add(middlePanel, BorderLayout.CENTER);

        // Setup action listeners for buttons
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playRound();
                //isDummy();
                audioPlayer.playSound("src/soundfx/Drawcard.wav");
            }
        });

        warBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolveWar();
                audioPlayer.playSound("src/soundfx/Drawcard.wav");
            }
        });

        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioPlayer.stopSound(); // Stop background music
                new StartScreen().setVisible(true);
                dispose();
            }
        });

        newBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGame();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProgress();
            }
        });

        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProgress();
            }
        });

        // Start background music
        audioPlayer.loopSound("src/soundfx/backgroundmusic.wav");
    }

    // Method to start a new game
    private void startGame() {
        gamePlay = new GameController(player1, player2, vsType);
        gamePlay.startGame();
        updateCounts();
        roundArea.setText("Game started!\n");
        nextBtn.setEnabled(true);
        warBtn.setEnabled(false);
    }

    // Method to play a round of the game
    private void playRound() {
        String result = gamePlay.playRound();
        updateCounts();
        updateCardImages();
        roundArea.append(result + "\n");

        if (gamePlay.isWar()) {
            nextBtn.setEnabled(false);
            warBtn.setEnabled(true);
        }

        if (result.startsWith("Game Over!")) {
            audioPlayer.playSound("src/soundfx/win.wav");
            nextBtn.setEnabled(false);
            warBtn.setEnabled(false);
        }
    }

    // Method to resolve a war situation
    private void resolveWar() {
        String result = gamePlay.resolveWar();
        updateCounts();
        updateCardImages();
        roundArea.append(result + "\n");

        nextBtn.setEnabled(true);
        warBtn.setEnabled(false);

        if (result.startsWith("Game Over!")) {
            audioPlayer.playSound("src/soundfx/win.wav");
            nextBtn.setEnabled(false);
        }
    }

    // Update the numbers of cards and piles
    private void updateCounts() {
        playDeckCountLB.setText("Deck: " + player1.getHand().size());
        playDiscCountLB.setText("Discard: " + player1.getDiscardPile().size());
        compDeckCountLB.setText("Deck: " + player2.getHand().size());
        compDiscCountLB.setText("Discard: " + player2.getDiscardPile().size());
    }

    // Update the image of the cards
    private void updateCardImages() {
        playerCardLB.setIcon(new ImageIcon(gamePlay.getPlayer1Card().getImagePath()));
        computerCardLB.setIcon(new ImageIcon(gamePlay.getPlayer2Card().getImagePath()));

        if (gamePlay.isWar()) {
            playerWar1Label.setIcon(new ImageIcon(rearCardPath));
            playerWar2Label.setIcon(new ImageIcon(rearCardPath));
            computerWar1Label.setIcon(new ImageIcon(rearCardPath));
            computerWar2Label.setIcon(new ImageIcon(rearCardPath));
        } else {
            playerWar1Label.setIcon(null);
            playerWar2Label.setIcon(null);
            computerWar1Label.setIcon(null);
            computerWar2Label.setIcon(null);
        }
    }

    // Play specific sound if card is a dummy card
/*
    public void isDummy() {
        if (gameController.getPlayer1Card().getImagePath() == "src/images/6-1.gif"
                || gameController.getPlayer1Card().getImagePath() == "src/images/6-2.gif") {
            audioPlayer.playSound("src/soundfx/Rickroll.wav");
        }
    }
     */
    // Reset card images to the back of card image
    private void resetCardImage() {
        playerCardLB.setIcon(new ImageIcon(rearCardPath));
        computerCardLB.setIcon(new ImageIcon(rearCardPath));
    }

    // Reset the count labels
    private void resetCount() {
        playDeckCountLB.setText("Deck: " + 0);
        playDiscCountLB.setText("Discard: " + 0);
        compDeckCountLB.setText("Deck: " + 0);
        compDiscCountLB.setText("Discard: " + 0);
    }

    // Reset the game area for a new game
    private void NewGame() {
        roundArea.setText("");
        nextBtn.setEnabled(false);
        resetCount();
        resetCardImage();
    }

    // Save the current game progress
    private void saveProgress() {
        try (FileOutputStream fileOut = new FileOutputStream("src/GameLogic/savegame.ser"); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(gamePlay);
            objectOut.writeObject(player1);
            objectOut.writeObject(player2);
            roundArea.setText("Game saved successfully!\n");
        } catch (IOException e) {
            e.printStackTrace();
            roundArea.setText("Failed to save game!\n");
        }
    }

    // Load a previously saved game progress
    private void openProgress() {
        try (FileInputStream fileIn = new FileInputStream("src/GameLogic/savegame.ser"); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            GameController loadedGameController = (GameController) objectIn.readObject();
            Player loadedPlayer1 = (Player) objectIn.readObject();
            Player loadedPlayer2 = (Player) objectIn.readObject();

            // Debugging statements
            System.out.println("Loaded GameController: " + loadedGameController);
            System.out.println("Loaded Player 1: " + loadedPlayer1);
            System.out.println("Loaded Player 2: " + loadedPlayer2);

            if (loadedGameController == null || loadedPlayer1 == null || loadedPlayer2 == null) {
                throw new NullPointerException("One or more deserialized objects are null.");
            }

            // Ensure gameController is initialized
            if (gamePlay == null) {
                gamePlay = new GameController(player1, player2, vsType);
            }

            // Update the existing gameController's state
            gamePlay.updateState(loadedGameController);
            gamePlay.getPlayer1().updateState(loadedPlayer1);
            gamePlay.getPlayer2().updateState(loadedPlayer2);

            // Update UI components
            updateCounts();
            updateCardImages();

            roundArea.setText("Game loaded successfully!\n");
            nextBtn.setEnabled(true);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            roundArea.setText("Failed to load game!\n");
        } catch (NullPointerException e) {
            e.printStackTrace();
            roundArea.setText("Failed to load game due to null objects!\n");
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WarGameGUI(new Player("Player 1"), new Player("Player 2"), true).setVisible(true);
        });
    }
}
