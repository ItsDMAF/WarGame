package WarGameGUI;

import Assets.Player;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
/*
Diego Andino - Alejandro Guerra - Keven Quevedo
Boxuan Chen - Luka Beridze - Hemant Kumar - Diego Acosta
*/
public class StartScreen extends JFrame {
    private JTextField play1NameFL;
    private JTextField play2NameFL;
    private JButton startBtn;
    private JButton exitBtn;
    private JButton audioBtn;
    private JRadioButton vsComputerBtn;
    private JRadioButton vsPlayerBtn;
    private ButtonGroup gameModes;
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

        vsComputerBtn = new JRadioButton("Play Against Computer");
        vsPlayerBtn = new JRadioButton("Play Against Another Player");
        gameModes = new ButtonGroup();
        gameModes.add(vsComputerBtn);
        gameModes.add(vsPlayerBtn);
        vsComputerBtn.setSelected(true);
        vsComputerBtn.setForeground(Color.black);
        vsPlayerBtn.setForeground(Color.black);
        vsComputerBtn.setBackground(new Color(0, 90, 0));
        vsPlayerBtn.setBackground(new Color(0, 90, 0));

        modePanel.add(vsComputerBtn);
        modePanel.add(vsPlayerBtn);

        // PLAYER NAMING
        // PANEL-----------------------------------------------------------
        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setBackground(new Color(0, 102, 0));
        playerNamePanel.setLayout(new GridLayout(2, 2, 5, 5));

        JLabel player1Label = new JLabel("Enter Name of Player 1:");
        player1Label.setForeground(Color.black);
        playerNamePanel.add(player1Label);

        play1NameFL = new JTextField(15);
        play1NameFL.setBackground(new Color(0, 102, 0));
        play1NameFL.setForeground(Color.white);
        playerNamePanel.add(play1NameFL);

        JLabel player2Label = new JLabel("Enter Name of Player 2:");
        player2Label.setForeground(Color.black);
        playerNamePanel.add(player2Label);

        play2NameFL = new JTextField(15);
        play2NameFL.setBackground(new Color(0, 102, 0));
        play2NameFL.setForeground(Color.white);
        play2NameFL.setEnabled(false);
        playerNamePanel.add(play2NameFL);

        // START
        // PANEL-------------------------------------------------------------------
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBackground(new Color(0, 90, 0));
        startBtn = new JButton("Start Game");
        startBtn.setFont(new Font("Arial", Font.BOLD, 16));
        startBtn.setForeground(Color.black);
        startBtn.setBackground(new Color(0, 77, 0));

        // Connect start() with the start button
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Set exit button
        JPanel exitButtonPanel = new JPanel();
        exitButtonPanel.setBackground(new Color(0, 90, 0));
        exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        exitBtn.setForeground(Color.black);
        exitBtn.setBackground(new Color(0, 77, 0));
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add new button for audio frame
        audioBtn = new JButton("???");
        audioBtn.setFont(new Font("Arial", Font.BOLD, 16));
        audioBtn.setForeground(Color.black);
        audioBtn.setBackground(new Color(0, 77, 0));
        audioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAudio();
            }
        });

        startButtonPanel.add(startBtn);
        startButtonPanel.add(audioBtn); // Add the new button to the panel
        startButtonPanel.add(exitBtn);

        // Add smaller panels to bigger
        mainPanel.add(modePanel);
        mainPanel.add(playerNamePanel);
        mainPanel.add(startButtonPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Connect button with function
        vsPlayerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play2NameFL.setEnabled(true);
            }
        });
        // Connect button with function
        vsComputerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play2NameFL.setEnabled(false);
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
        String playerName = play1NameFL.getText().trim();
        String player2Name = play2NameFL.getText().trim();

        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name.");
            return;
        }

        boolean vsComputer = vsComputerBtn.isSelected();
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
