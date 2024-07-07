package WarGameGUI;

import Assets.Player;
import GameLogic.AudioPlayer;
import GameLogic.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarGameGUI extends JFrame {

    private GameController gameController;
    private JLabel playerCardLabel, computerCardLabel;
    private JLabel playerWar1Label, playerWar2Label, computerWar1Label, computerWar2Label;
    private JLabel playerDeckCountLabel, playerDiscardCountLabel, computerDeckCountLabel, computerDiscardCountLabel;
    private JTextArea gameArea;
    private JButton startButton, nextButton, resolveWarButton, mainMenuButton, newGameButton, saveButton, openButton;
    private final AudioPlayer audioPlayer;

    private final String cardBackImagePath = "src/images/rear.gif";
    ImageIcon img = new ImageIcon("src/images/dizhu.gif");

    private final Player player1;
    private final Player player2;
    private final boolean vsComputer;

    public WarGameGUI(Player player1, Player player2, boolean vsComputer) {
        this.player1 = player1;
        this.player2 = player2;
        this.vsComputer = vsComputer;
        setIconImage(img.getImage());
        setTitle("WAR GAME");
        setSize(850, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        audioPlayer = new AudioPlayer();
        setupGUI();
    }

    private void setupGUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

//TEXT PANEL--------------------------------------------------------------------
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(new Color(0, 112, 0));
        JLabel nameLabel = new JLabel("WAR GAME");
        nameLabel.setForeground(Color.black);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel nameLabel2 = new JLabel("Diego Andino - Alejandro Guerra - Keven Quevedo - Mark Boxuen - Luka Beridze - Hemant Kumar - Digo Acosta");
        nameLabel2.setForeground(Color.black);
        nameLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel2.setHorizontalAlignment(JLabel.CENTER);
        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(nameLabel2, BorderLayout.SOUTH);

//BUTTON PANEL------------------------------------------------------------------
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(0, 112, 0));
        startButton = new JButton(vsComputer ? "Start PvE" : "Start PvP");
        startButton.setBackground(new Color(0, 77, 0));
        nextButton = new JButton("Next");
        nextButton.setBackground(new Color(0, 77, 0));
        nextButton.setEnabled(false);
        resolveWarButton = new JButton("War");
        resolveWarButton.setBackground(new Color(0, 77, 0));
        resolveWarButton.setEnabled(false);
        mainMenuButton = new JButton("Quit");
        mainMenuButton.setBackground(new Color(0, 77, 0));

        //NOT WORKIN------------------------------------------------------------
        newGameButton = new JButton("New Game");
        newGameButton.setBackground(new Color(0, 77, 0));

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0, 77, 0));

        openButton = new JButton("Open");
        openButton.setBackground(new Color(0, 77, 0));
        //----------------------------------------------------------------------
        buttonPanel.add(startButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(resolveWarButton);
        buttonPanel.add(mainMenuButton);

        //NOT WORKING-----------------------------------------------------------
        buttonPanel.add(newGameButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);
        //----------------------------------------------------------------------
        
        setLocationRelativeTo(null);

        gameArea = new JTextArea(5, 30);
        gameArea.setBackground(new Color(0, 112, 0));
        gameArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameArea);
        scrollPane.getViewport().setBackground(new Color(0, 90, 0));

        topPanel.add(namePanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        topPanel.add(scrollPane, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

//MIDDLE PANEL: for cards and counters------------------------------------------
        JPanel middlePanel = new JPanel(new GridLayout(3, 2));
        middlePanel.setBackground(new Color(0, 90, 0));
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBackground(new Color(0, 102, 0));
        JPanel computerPanel = new JPanel(new BorderLayout());
        computerPanel.setBackground(new Color(0, 102, 0));

        playerCardLabel = new JLabel(new ImageIcon(cardBackImagePath));
        computerCardLabel = new JLabel(new ImageIcon(cardBackImagePath));
        playerWar1Label = new JLabel();
        playerWar2Label = new JLabel();
        computerWar1Label = new JLabel();
        computerWar2Label = new JLabel();

        playerDeckCountLabel = new JLabel("Deck: 0");
        playerDiscardCountLabel = new JLabel("Discard: 0");
        computerDeckCountLabel = new JLabel("Deck: 0");
        computerDiscardCountLabel = new JLabel("Discard: 0");

        playerPanel.add(playerCardLabel, BorderLayout.CENTER);
        playerPanel.add(playerDeckCountLabel, BorderLayout.NORTH);
        playerPanel.add(playerDiscardCountLabel, BorderLayout.SOUTH);

        computerPanel.add(computerCardLabel, BorderLayout.CENTER);
        computerPanel.add(computerDeckCountLabel, BorderLayout.NORTH);
        computerPanel.add(computerDiscardCountLabel, BorderLayout.SOUTH);

        middlePanel.add(playerPanel);
        middlePanel.add(computerPanel);

        middlePanel.add(playerWar1Label);
        middlePanel.add(computerWar1Label);
        middlePanel.add(playerWar2Label);
        middlePanel.add(computerWar2Label);

        add(middlePanel, BorderLayout.CENTER);

//ACTION LISTENERS--------------------------------------------------------------
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playRound();
            }
        });

        resolveWarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolveWar();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioPlayer.stopSound(); // Stop background music
                new StartScreen().setVisible(true);
                dispose();
            }
        });

        audioPlayer.loopSound("src/soundfx/backgroundmusic.wav");
    }

    private void startGame() {
        gameController = new GameController(player1, player2, vsComputer);
        gameController.startGame();
        updateCounts();
        gameArea.setText("Game started!\n");
        nextButton.setEnabled(true);
        resolveWarButton.setEnabled(false);
    }

    private void playRound() {
        String result = gameController.playRound();
        updateCounts();
        updateCardImages();
        gameArea.append(result + "\n");

        if (gameController.isWar()) {
            nextButton.setEnabled(false);
            resolveWarButton.setEnabled(true);
        }

        if (result.startsWith("Game Over!")) {
            audioPlayer.playSound("src/soundfx/win.wav");
            nextButton.setEnabled(false);
            resolveWarButton.setEnabled(false);
        }
    }

    private void resolveWar() {
        String result = gameController.resolveWar();
        updateCounts();
        updateCardImages();
        gameArea.append(result + "\n");

        nextButton.setEnabled(true);
        resolveWarButton.setEnabled(false);

        if (result.startsWith("Game Over!")) {
            audioPlayer.playSound("src/soundfx/win.wav");
            nextButton.setEnabled(false);
        }
    }

    private void updateCounts() {
        playerDeckCountLabel.setText("Deck: " + player1.getHand().size());
        playerDiscardCountLabel.setText("Discard: " + player1.getDiscardPile().size());
        computerDeckCountLabel.setText("Deck: " + player2.getHand().size());
        computerDiscardCountLabel.setText("Discard: " + player2.getDiscardPile().size());
    }

    private void updateCardImages() {
        playerCardLabel.setIcon(new ImageIcon(gameController.getPlayer1Card().getImagePath()));
        computerCardLabel.setIcon(new ImageIcon(gameController.getPlayer2Card().getImagePath()));

        if (gameController.isWar()) {
            playerWar1Label.setIcon(new ImageIcon(cardBackImagePath));
            playerWar2Label.setIcon(new ImageIcon(cardBackImagePath));
            computerWar1Label.setIcon(new ImageIcon(cardBackImagePath));
            computerWar2Label.setIcon(new ImageIcon(cardBackImagePath));
        } else {
            playerWar1Label.setIcon(null);
            playerWar2Label.setIcon(null);
            computerWar1Label.setIcon(null);
            computerWar2Label.setIcon(null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WarGameGUI(new Player("Player 1"), new Player("Player 2"), true).setVisible(true);
        });
    }
}
