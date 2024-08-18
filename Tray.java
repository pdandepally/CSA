import java.util.Scanner;
import java.util.*;

public class Tray {
    
    //variables i will probably need
    int score = 0;
    int diceTotal = 0;
    int numDice = 0;
    int tileNum = 0;
    String tiles = "";
    int tileTotal = 0;
    boolean works = false;
    boolean scoreCalled = false;
    
    //Tiles and Dice setup
    Tile one = new Tile(1);
    Tile two = new Tile(2);
    Tile three = new Tile(3);
    Tile four = new Tile(4);
    Tile five = new Tile(5);
    Tile six = new Tile(6);
    Tile seven = new Tile(7);
    Tile eight = new Tile(8);
    Tile nine = new Tile(9);
    Die fird = new Die();
    Die secd = new Die(); 
    ArrayList<String> tilist = new ArrayList<String>();
    ArrayList<String> cltiles = new ArrayList<String>();
    
    //Create Scanner Input
    Scanner input = new Scanner(System.in);
    
    public Tray(){
        
        tilist.add("0");
        tilist.add("1"); tilist.add("2"); tilist.add("3");
        tilist.add("4"); tilist.add("5"); tilist.add("6");
        tilist.add("7"); tilist.add("8"); tilist.add("9");
        
        System.out.println("Thank you for playing Shut the Box! ");
        
        play();
        
    }
    
    public void play(){
        
        System.out.println();
        System.out.println("Input the number of dice you wish to roll! (1 or 2)");
        System.out.println();
        numDice = input.nextInt();
        rollDice(numDice);
        
        //conditional to find out if possible, tileSelect under else

        if (tilist.size() >= 4){
        for (int i = 0; i < tilist.size() && works == false; i++){
            for (int j = 0; j < tilist.size() && works == false; j++){
                for (int k = 1; k < tilist.size() && works == false; k++){
                    
                    if (j == i && i != 0 && i != (tilist.size() - 1)){
                        j++;
                    }
                    else if (j == i && i == (tilist.size() - 1)){
                        break;
                    }
                    if (k == j && j != 0 && j != (tilist.size() - 1)){
                        k++;
                    }
                    else if (k == j && j == (tilist.size() - 1)){
                        break;
                    }
                    if (k == i && i != 0 && i != (tilist.size() - 1)){
                        k++;
                    }
                    else if (k == i && i == (tilist.size() - 1)){
                        break;
                    }
                    
                    
                    int sum = Integer.parseInt(tilist.get(i)) + Integer.parseInt(tilist.get(j)) + Integer.parseInt(tilist.get(k));
                    //System.out.println(tilist.get(i) + ", " + tilist.get(j) + ", " + tilist.get(k) + ", " + sum);
                    if (sum == diceTotal){
                        works = true;
                        //System.out.println("It works! hooray");
                    }
                }
            }
        }
        }
        
        else if (tilist.size() == 3){
        for (int i = 0; i < tilist.size() && works == false; i++){
            for (int j = 0; j < tilist.size() && works == false; j++){
                    if (j == i && i != 0 && i != (tilist.size() - 1)){
                        j++;
                    }
                    else if (j == i && i == (tilist.size() - 1)){
                        break;
                    }
                    
                    int sum = Integer.parseInt(tilist.get(i)) + Integer.parseInt(tilist.get(j));
                    //System.out.println(tilist.get(i) + ", " + tilist.get(j) + ", " + sum);
                    if (sum == diceTotal){
                        works = true;
                        //System.out.println("It works! hooray");
                    }
            }
        }
        }
        
        else if (tilist.size() == 2){
        for (int i = 0; i < tilist.size() && works == false; i++){
                
                int sum = Integer.parseInt(tilist.get(i));
                //System.out.println(tilist.get(i) + ", " + sum);
                if (sum == diceTotal){
                    works = true;
                    //System.out.println("It works! hooray");
                }
            }
        }
        
        System.out.println();
        
        if (works == true){
            tileSelect();
        }
        else{
            score();
        }
    }
    
    public void tileSelect(){
        
        works = false;
        tileTotal = 0;
        
        System.out.println("Which tile(s) would you like to close? If multiple, please enter separated by a space.");
        System.out.println();
        //System.out.println(tilist);
        //System.out.println();
        
        while (tiles.equals("")){
            tiles = input.nextLine();
        }
        
        //tiles = input.nextLine();
        //System.out.println(tiles);
            
        for (int i = 0; i < tiles.length(); i+=2){
            tileNum = Integer.parseInt(tiles.substring(i, i+1));
            
            if (tileNum == 0){
            System.out.println("Your tiles are invalid. Please try again.");
            System.out.println();
            tiles = "";
            tileSelect();
            }
            
            //System.out.println(tileNum);
            tileTotal += tileNum;
            //System.out.println(tileTotal);
        }
        
        if (tileTotal == diceTotal){
            for (int i = 0; i < tiles.length(); i+=2){
                tileNum = Integer.parseInt(tiles.substring(i, i+1));
                closeTile(tileNum);
            }
            System.out.println();
            System.out.println("Closed tiles: " + cltiles);

            tiles = "";
            if (cltiles.size() == 9){
                score();
            } 
            else{
                play();
            }
            
        }
        else if (tileTotal != diceTotal){
            System.out.println();
            System.out.println("Your tiles are invalid. Please try again.");
            System.out.println();
            //System.out.println(tilist);
            //System.out.println(tileTotal + ", " + diceTotal);
            tiles = "";
            tileSelect();
        }
        
    }
    
    public void rollDice(int c){
        
        diceTotal = 0;
        
        if (c == 1 && ((seven.getStatus()) || (eight.getStatus()) || (nine.getStatus()))){
            System.out.println();
            System.out.println("Sorry! You have 7, 8, or 9 still uncovered so you must use both dice.");
            //System.out.println();
            play();
        }
        else if (c == 1){
            diceTotal = Die.rollDie(fird);
        }
        else if (c == 2){
            diceTotal = Die.rollDie(fird);
            diceTotal += Die.rollDie(secd);
        }
        else{
            System.out.println();
            System.out.println("Sorry! This is an invalid number of dice. Please try again.");
            play();
        }
        if (scoreCalled == false){
            System.out.println();
            System.out.println("You rolled: " + diceTotal);
        }
        
    }
    
    //yes, i probably could've shortened this method, but it already works 
    //and now i don't feel like changing it
    
    public void closeTile(int f){
        
        if (f == 1){
            if (one.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
            }
            else{
                one.close();
                tilist.remove("1");
                cltiles.add("1");
                diceTotal = diceTotal - 1;
            }
        }
        else if (f == 2){
           if (two.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
           }
            else{
                two.close();
                tilist.remove("2");
                cltiles.add("2");
                diceTotal = diceTotal - 2;
            }
        }
        else if (f == 3){
           if (three.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
            }
            else{
                three.close();
                tilist.remove("3");
                cltiles.add("3");
                diceTotal = diceTotal - 3;
            }
        }
        else if (f == 4){
           if (four.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
            }
            else{
                four.close();
                tilist.remove("4");
                cltiles.add("4");
                diceTotal = diceTotal - 4;
            }
        }
        else if (f == 5){
           if (five.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
            }
            else{
                five.close();
                tilist.remove("5");
                cltiles.add("5");
                diceTotal = diceTotal - 5;
            }
        }
        else if (f == 6){
           if (six.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
            }
            else{
                six.close();
                tilist.remove("6");
                cltiles.add("6");
                diceTotal = diceTotal - 6;
            }
        }
        else if (f == 7){
           if (seven.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
            }
            else{
                seven.close();
                tilist.remove("7");
                cltiles.add("7");
                diceTotal = diceTotal - 7;
            }
        }
        else if (f == 8){
           if (eight.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
            }
            else{
                eight.close();
                tilist.remove("8");
                cltiles.add("8");
                diceTotal = diceTotal - 8;
            }
        }
        else if (f == 9){
           if (nine.getStatus() == false){
                System.out.println("You have already closed this tile. Please select another tile.");
                tileSelect();
            }
            else{
                nine.close();
                tilist.remove("9");
                cltiles.add("9");
                diceTotal = diceTotal - 9;
            }
        }
        
        Collections.sort(cltiles);
    }
    
    public void score(){
        if (scoreCalled == false){
        for (int i = 0; i < tilist.size(); i++){
                score += Integer.parseInt(tilist.get(i));
            }
            
            if (score == 0){
                System.out.println();
                System.out.println("Congratulations! You win with a score of " + score + "!");
                scoreCalled = true;
            }
            else{
                System.out.println("Game Over! Your score is: " + score);
                scoreCalled = true;
            }
        }
    }

}