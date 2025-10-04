import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

abstract class Character {
    protected int power;
    protected int hp;

    public Character(int power, int hp){
        this.power = power;
        this.hp = hp;
    }

    public void kick(Character c){
        c.hp = c.hp - this.power;
    }

    public boolean isAlive(){
        if (this.hp > 0) {
            return true;
        }
        return false;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        if (power < 0) {
            this.power = 0;
        } else {
            this.power = power;
        }
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        String result = className + "{hp=" + hp + ", power=" + power + "}";
        return result;
    }
}


class Hobbit extends Character {
    public Hobbit(){
        super(0, 3);
    }

    @Override
    public void kick(Character c){
        System.out.println("Hobbit is crying");
        System.out.println("Hobbit cries because of " + c);
    }

    @Override
    public String toString() {
        String info = "Hobbit{hp=" + hp + ", power=" + power + "}";
        return info;
    }
}


class Elf extends Character {
    public Elf(){
        super(10, 10);
    }

    @Override
    public void kick(Character c){
        if (c.power < this.power) {
            c.hp = 0;
            System.out.println("Fatality! Elf kills " + c);
        } else {
            c.hp = c.hp - 1;
            System.out.println("Elf: 'You got skills, you got skills' - about " + c);
        }
    }

    @Override
    public String toString() {
        String info = "Elf{hp=" + hp + ", power=" + power + "}";
        return info;
    }
}


class King extends Character {
    public King(){
        super(ThreadLocalRandom.current().nextInt(5, 15),
             ThreadLocalRandom.current().nextInt(5, 15));
    }

    @Override
    public void kick(Character c){
        Random random = new Random();
        int ppower = random.nextInt(this.power - 5 + 1) + 5;
        c.hp = c.hp - ppower;
        System.out.println("King gives -" + ppower + " damage to " + c);
    }

    @Override
    public String toString() {
        String info = "King(" + power + ", " + hp + ")";
        return info;
    }
}

class Knight extends Character {
    public Knight(){
        super(ThreadLocalRandom.current().nextInt(2, 12),
             ThreadLocalRandom.current().nextInt(2, 12));
    }

    @Override
    public void kick(Character c){
        Random random = new Random();
        int ppower = random.nextInt(this.power - 2 + 1) + 2;
        c.hp = c.hp - ppower;
        System.out.println("Knight gives -" + ppower + " damage to " + c);
    }
    
    @Override
    public String toString() {
        String info = "Knight(" + power + ", " + hp + ")";
        return info;
    }
}

class CharacterFactory{
    public Character createCharacter(){
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        
        Character result = null;
        
        if (randomIndex == 0) {
            result = new Knight();
        } else if (randomIndex == 1) {
            result = new King();
        } else if (randomIndex == 2) {
            result = new Elf();
        } else if (randomIndex == 3) {
            result = new Hobbit();
        }
        
        return result;
    }
}

class GameManager{
    public void fight(Character c1, Character c2){
        System.out.println("Battle starts: " + c1 + " vs " + c2);
        
        while (true) {
            c1.kick(c2);
            
            if (c2.isAlive() == false) {
                System.out.println(c1 + " wins!");
                break;
            }
            
            c2.kick(c1);
            
            if (c1.isAlive() == false) {
                System.out.println(c2 + " wins!");
                break;
            }
        }
    }
}


public class tes {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();
        Character c1 = factory.createCharacter();
        Character c2 = factory.createCharacter();
        
        GameManager gm = new GameManager();
        gm.fight(c1, c2);
    }
}
