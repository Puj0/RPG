package acters.acters_repository;

import acters.Acter;

import java.util.concurrent.ThreadLocalRandom;

public class ActerWithInitiative{

    private Acter acter;
    private int initiative;
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    public ActerWithInitiative(Acter acter){
        this.acter = acter;
        this.initiative = acter.getInitiative() + getRandomInitiative();
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