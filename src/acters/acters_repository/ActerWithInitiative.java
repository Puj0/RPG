package acters.acters_repository;

import acters.Acter;
import game.IRandom;
import game.Derandomizer;

public class ActerWithInitiative{

    private Acter acter;
    private int initiative;
    private IRandom random = new Derandomizer();

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