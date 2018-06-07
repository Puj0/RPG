package game;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadRandom implements IRandom {


    private ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    @Override
    public Integer nextInt(int origin, int bound) {
        return threadLocalRandom.nextInt(origin,bound);
    }
}
