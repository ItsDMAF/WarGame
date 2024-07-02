package WarGameGUI;

import javax.swing.JPanel;
import java.util.Scanner;

public class YourProject5 {

    public static void main(String[] args) {
        /*
        Project Description: 
        The  purpose  of  this  project  is  to  develop aCard  Game program  
        with  a Graphical  User  Interface  that  the  Player  can  play  with 
        the  Computer  or other player. The  program  will  be  written  in Java
        and should  have  a  user-friendly  graphical  user  interface  (GUI)  
        to  enhance  usability,in  addition  to save  and  Capability,  so  the  
        user  can  continue  the  game  from  where  he finished. 
        
        Game Rules:
        1. Introduction
        War (also known as Battle in the United Kingdom) 
        is a simple card game, typically  played  by  two  players  using  a  
        standard  playing  card  deck, and often played by children. 
        
        2. How to Play
        The objective of the game is to win all of the cards.
        The deck is divided evenlyand randomlyamong the players, giving each 
        a down stack. In unison, each player reveals the top card of their deck
        -this is a "battle"-and the player with the higher card takes both of 
        the cards played and moves them to their stack. Aces arehigh, and suits 
        are ignored.
        If the two cards played are of equal value, then there is a "war". 
        Both players place the next 2 cards from their pile face down and then
        another card face-up. The owner of the higher face-up card wins the war 
        and adds all thecards on the table to the bottom of their deck. If the 
        face-up cards are again equal then the battle repeats with another set 
        of face-down/up cards. This repeats until one player's face-up card is
        higher than their opponent's.
        If a player runs out of cards during a war, that player immediately 
        loses. In others, the player may play the last card in their deck as 
        their face-up card for the remainder of the war or replay the game from 
        the beginning.
        The game will continue until one player hascollected all ofthe cards.
        
        3. Solution Steps: 
        Please followthe following stepsduring the solution: 
        A.Create Packages, You need at least 2 main packages, 
        B.You are free to useyour solution, the solution mustbe OOP, and you 
        need to create at least:
        card class,user class (players, name and id),
        save class (save game progress),
        ..
        In each class you have to:
        Constructors.
        Getter and setters.
        Print info.
        Needed methods.
        D.You save the game progress
        
        4.The Solution condition
        The solution must has the following conditions 
        (Not following the following conditionsyou will have penalties of
        each points): 
        1-Basedon  OOP,  no  static  methods  are  allowed,  except  of Lambda 
        Function if needed. (20 points penalty)
        2-All variables must be private, (4 point penalty)
        3-Uses inheritance,   and abstractin   the  solution (4 points penalty)
        4-Uses Array or list to save the data (4points penalty).
        5-Uses file saving to save the game progress,(4 points penalty).
        6-Uses  the  random  number  to  shuffle  and  distribute  the  card 
        deck, (4 Points penalty).
        7-Use  an Array  orlist  to  save  the user'sinformation.
        (2  points penalty).
        8-Using  a  Graphical  User  interface  controlled  by  a  mouse  or 
        Keyboard, (20 Points penalties).
        9-The GUI muss had a menu bar which had at least the following (New)  
        for the new  Game,  (Save)  for  saving  the  Game, (Open) to open
        the saved game. (5 points penalties)
        10-Code must havecomments, (2 Points penalties),
        11-Code   must not be copied, or AI generated (50 Points penalties). 
        12-UTF 8 character coding (1 point penalty).
        13-Project  muss  show  your  names,  in  the  about  Menu  
        Bar,(2-pointpenalty).
        14-All used images muss be included in the Images folder, in your Project.
        15-You can add sound effects if you would like to game.
        16-You can download card images from the Interne to create your design.
        17-There is no pre-defined design of how the game will be at the end,
        the winning group game will be given 10 extra points,
        */
        int op = 0;
        do {
            System.out.println("WAR GAME\n"
                    + "1 <- PVE Game\n"
                    + "2 <- PVP Game\n"
                    + "3 <- Create Player\n"
                    + "4 <- Leaderboard\n"
                    + "5 <- EXIT");
            switch(op){
                case 1:
                    System.out.println("PVE");
                    break;
                case 2:
                    System.out.println("PVP");
                    break;
                case 3:
                    System.out.println("Create Player");
                    break;
                case 4:
                    System.out.println("Leaderboard");
                    break;
                case 5:
                    System.out.println("End");
                    break; 
                default:
                    System.out.println("Not a Valid Option");
                    break;
            }
            
        }while(op != 4);
 
    }
    
}
