package WarGameGUI;

import Assets.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartScreen extends JFrame {

    private JTextField playerNameField;
    private JTextField player2NameField;
    private JButton startButton;
    private JButton exitButton;
    private JRadioButton vsComputerButton;
    private JRadioButton vsPlayerButton;
    private ButtonGroup modeGroup;
    private ArrayList<Player> players = new ArrayList<>();

    ImageIcon img = new ImageIcon("src/images/dizhu.gif");

    public StartScreen() {
        setTitle("Start Screen");
        setSize(500, 250);
        setIconImage(img.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setupGUI();
    }

    private void setupGUI() {
        setLayout(new BorderLayout());

        // TITLE PANEL -------------------------------------------------------------------
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 90, 0));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("WAR GAME");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Diego Andino - Alejandro Guerra - Keven Quevedo - Mark Boxuen");
        nameLabel.setForeground(Color.black);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel2 = new JLabel("Luka Beridze - Hemant Kumar - Digo Acosta");
        nameLabel2.setForeground(Color.black);
        nameLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel2.setHorizontalAlignment(JLabel.CENTER);
        nameLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(nameLabel);
        titlePanel.add(nameLabel2);
        add(titlePanel, BorderLayout.NORTH);

//MAIN PANEL--------------------------------------------------------------------
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 0, 10)); 
        mainPanel.setBackground(new Color(0, 102, 0)); 

//MODE SELECT PANEL-------------------------------------------------------------
        JPanel modePanel = new JPanel();
        modePanel.setBackground(new Color(0, 102, 0));
        modePanel.setLayout(new GridLayout(1, 2)); 

        vsComputerButton = new JRadioButton("Play Against Computer");
        vsPlayerButton = new JRadioButton("Play Against Another Player");
        modeGroup = new ButtonGroup();
        modeGroup.add(vsComputerButton);
        modeGroup.add(vsPlayerButton);
        vsComputerButton.setSelected(true);
        vsComputerButton.setForeground(Color.black);
        vsPlayerButton.setForeground(Color.black);
        vsComputerButton.setBackground(new Color(0, 90, 0)); 
        vsPlayerButton.setBackground(new Color(0, 90, 0));

        modePanel.add(vsComputerButton);
        modePanel.add(vsPlayerButton);

//PLAYER NAMING PANEL-----------------------------------------------------------
        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setBackground(new Color(0, 102, 0));
        playerNamePanel.setLayout(new GridLayout(2, 2, 5, 5)); 

        JLabel player1Label = new JLabel("Enter Name of Player 1:");
        player1Label.setForeground(Color.black);
        playerNamePanel.add(player1Label);

        playerNameField = new JTextField(15);
        playerNameField.setBackground(new Color(0, 102, 0));
        playerNameField.setForeground(Color.white);
        playerNamePanel.add(playerNameField);

        JLabel player2Label = new JLabel("Enter Name of Player 2:");
        player2Label.setForeground(Color.black);
        playerNamePanel.add(player2Label);

        player2NameField = new JTextField(15);
        player2NameField.setBackground(new Color(0, 102, 0)); 
        player2NameField.setForeground(Color.white);
        player2NameField.setEnabled(false);
        playerNamePanel.add(player2NameField);

//START PANEL-------------------------------------------------------------------
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBackground(new Color(0, 90, 0));
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setForeground(Color.black);
        startButton.setBackground(new Color(0, 77, 0));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        JPanel exitButtonPanel = new JPanel();
        exitButtonPanel.setBackground(new Color(0, 90, 0));
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setForeground(Color.black);
        exitButton.setBackground(new Color(0, 77, 0));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        startButtonPanel.add(startButton);
        startButtonPanel.add(exitButton);

        mainPanel.add(modePanel);
        mainPanel.add(playerNamePanel);
        mainPanel.add(startButtonPanel);

        add(mainPanel, BorderLayout.CENTER);

        vsPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2NameField.setEnabled(true);
            }
        });

        vsComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2NameField.setEnabled(false);
            }
        });
    }

    private void startGame() {
        String playerName = playerNameField.getText().trim();
        String player2Name = player2NameField.getText().trim();

        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name.");
            return;
        }

        boolean vsComputer = vsComputerButton.isSelected();
        if (!vsComputer && player2Name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the second player's name.");
            return;
        }

        Player player1 = new Player(playerName);
        Player player2 = vsComputer ? new Player("Computer") : new Player(player2Name);

        players.add(player1);
        players.add(player2);

        WarGameGUI gameGUI = new WarGameGUI(player1, player2, vsComputer);
        gameGUI.setVisible(true);
        dispose();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartScreen().setVisible(true);
            }
        });
    }
}