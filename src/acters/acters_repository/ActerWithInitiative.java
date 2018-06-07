package acters.acters_repository;

import acters.Acter;
import game.Game;
import game.IRandom;
import game.PajinAntiRandom;
import game.ThreadRandom;

import java.util.concurrent.ThreadLocalRandom;

public class ActerWithInitiative{

    private Acter acter;
    private int initiative;
    private IRandom random = new PajinAntiRandom();

    public ActerWithInitiative(Acter acter, IRandom random){
        this.acter = acter;
        this.initiative = acter.getInitiative() + getRandomInitiative();
        this.random = random;
    }

    private int getRandomInitiative(){
        return random.nextInt(1, 21);
    }

    public Acter getActer(){
        return acter;
    }

    public int getInitiative() {
        return initiative;
    }

}