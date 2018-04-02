package acters;

import java.util.concurrent.ThreadLocalRandom;

public class ActerWithInitiative{

    private Acter acter;
    private int initiative;

    public ActerWithInitiative(Acter acter){
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

}