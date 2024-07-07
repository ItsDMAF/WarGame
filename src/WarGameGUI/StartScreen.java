package WarGameGUI;

import Assets.Player;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class StartScreen extends JFrame {
    private JTextField playerNameField;
    private JTextField player2NameField;
    private JButton startButton;
    private JButton exitButton;
    private JButton audioButton; // New button for audio frame
    private JRadioButton vsComputerButton;
    private JRadioButton vsPlayerButton;
    private ButtonGroup modeGroup;
    private ArrayList<Player> players = new ArrayList<>();

    ImageIcon img = new ImageIcon("src/images/dizhu.gif");

    public StartScreen() {
        setTitle("Start Screen");
        setSize(500, 380);
        setIconImage(img.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setupGUI();
    }

    // Set up the GUI
    private void setupGUI() {
        setLayout(new BorderLayout());

        // TITLE PANEL
        // -------------------------------------------------------------------
        JPanel titlePanel = new JPanel();
        // Panels for titles
        titlePanel.setBackground(new Color(0, 90, 0));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("WAR GAME");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Labels for names
        JLabel nameLabel = new JLabel("Diego Andino - Alejandro Guerra - Keven Quevedo - Boxuan Chen");
        nameLabel.setForeground(Color.black);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel2 = new JLabel("Luka Beridze - Hemant Kumar - Diego Acosta");
        nameLabel2.setForeground(Color.black);
        nameLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel2.setHorizontalAlignment(JLabel.CENTER);
        nameLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(nameLabel);
        titlePanel.add(nameLabel2);
        add(titlePanel, BorderLayout.NORTH);

        // MAIN
        // PANEL--------------------------------------------------------------------
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 0, 10));
        mainPanel.setBackground(new Color(0, 102, 0));

        // MODE SELECT
        // PANEL-------------------------------------------------------------
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

        // PLAYER NAMING
        // PANEL-----------------------------------------------------------
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

        // START
        // PANEL-------------------------------------------------------------------
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBackground(new Color(0, 90, 0));
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setForeground(Color.black);
        startButton.setBackground(new Color(0, 77, 0));

        // Connect start() with the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Set exit button
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

        // Add new button for audio frame
        audioButton = new JButton("???");
        audioButton.setFont(new Font("Arial", Font.BOLD, 16));
        audioButton.setForeground(Color.black);
        audioButton.setBackground(new Color(0, 77, 0));
        audioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAudio();
            }
        });

        startButtonPanel.add(startButton);
        startButtonPanel.add(audioButton); // Add the new button to the panel
        startButtonPanel.add(exitButton);

        // Add smaller panels to bigger
        mainPanel.add(modePanel);
        mainPanel.add(playerNamePanel);
        mainPanel.add(startButtonPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Connect button with function
        vsPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2NameField.setEnabled(true);
            }
        });
        // Connect button with function
        vsComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2NameField.setEnabled(false);
            }
        });
    }

    // Method to play audio and display image
    
    private void playAudio() {
        // Create a new dialog
        JDialog audioDialog = new JDialog(this, "Audio Dialog", true);
        audioDialog.setSize(70, 100);
        audioDialog.setLayout(new BorderLayout());
        audioDialog.setLocationRelativeTo(this);
        audioDialog.setUndecorated(true);

        // Add image
        JLabel imageLabel = new JLabel(new ImageIcon("src/images/6-1.gif"));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        audioDialog.add(imageLabel, BorderLayout.CENTER);

        // Set background color to black
        audioDialog.getContentPane().setBackground(Color.BLACK);

        // Load and play audio
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/soundfx/Rickroll.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Add listener to close the dialog when audio finishes
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                    audioDialog.dispose();
                }
            });

            // Start playing audio
            clip.start();
            audioDialog.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error playing audio: " + ex.getMessage());
        }
    }

    // Determine if PVP or PVE
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

    // Start the main function
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartScreen().setVisible(true);
            }
        });
    }
}
