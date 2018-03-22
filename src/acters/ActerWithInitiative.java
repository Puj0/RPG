package acters;

import java.util.concurrent.ThreadLocalRandom;

public class ActerWithInitiative implements Comparable<ActerWithInitiative>{

    private Acter acter;
    private int initiative;

    ActerWithInitiative(Acter acter){
        this.acter = acter;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        this.initiative = acter.getInitiative() + random.nextInt(1, 21);
    }

    public Acter getActer(){
        return acter;
    }

    public int getInitiative() {
        return initiative;
    }

    @Override
    public int compareTo(ActerWithInitiative acterWithInitiative) {
        return acterWithInitiative.getInitiative() - this.getInitiative();
    }
}