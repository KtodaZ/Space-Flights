package com.mycompany.a2;

import java.util.ArrayList;
import java.util.ListIterator;

public class GameObjectCollection extends ArrayList<GameObject> implements ICollection<GameObject>{

    public IIterator<GameObject> getIterator() {
        return new Iterator(this);
    }
    
    @Override
    public boolean add(GameObject object) {
        return super.add(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public int size() {
        return super.size();
    }

    /**Remove all ArrayList Iterators in order to use custom iterator*/
    @Override
    public java.util.Iterator<GameObject> iterator() {
        return null;
    }

    @Override
    public ListIterator<GameObject> listIterator() {
        return null;
    }

    @Override
    public ListIterator<GameObject> listIterator(int location) {
        return null;
    }
    
    public GameObjectCollection clone() {
        GameObjectCollection newList = new GameObjectCollection();
        IIterator<GameObject> iterator = this.getIterator();
        while(iterator.hasNext()) {
            newList.add(iterator.next());
        }
        return newList;
    }
    
    public int getNonCapturedAstronautCount() {
        int astronautCount = 0;
        IIterator<GameObject> iterator = this.getIterator();
        while(iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (isValidAstronaut(gameObject)) {
                astronautCount++;
            }
        }
        return astronautCount;
    }
    
    public int getNonCapturedAlienCount() {
        int alienCount = 0;
        IIterator<GameObject> iterator = this.getIterator();
        while(iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (isValidAlien(gameObject)) {
                alienCount++;
            }
        }
        return alienCount;
    }
    
    // Private methods
    
    public Spaceship getSpaceship() {
        return (Spaceship) this.get(getSpaceshipIndex());
    }

    public void setSpaceship(Spaceship spaceship) {
        this.set(getSpaceshipIndex(), spaceship);
    }
    
    public int getSpaceshipIndex() {
        IIterator<GameObject> iterator = this.getIterator();
        int count = -1;
        while(iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            count++;
            if(isSpaceShip(gameObject)) break;
        }
        return count;
    }
    
    public Alien getRandomNonCapturedAlien() {
        if (getNonCapturedAlienCount() > 0) {
            while(true) {
                int randIndex = RandomUtils.getRandomInt(0, this.size() - 1);
                if(isValidAlien(this.get(randIndex))) 
                    return (Alien) this.get(randIndex);
            }
        }
        return null;
    }

    public Alien removeRandomNonCapturedAlien() {
        if (getNonCapturedAlienCount() > 0) {
            while(true) {
                int randIndex = RandomUtils.getRandomInt(0, this.size() - 1);
                if(isValidAlien(this.get(randIndex))) 
                    return (Alien) this.remove(randIndex);
            }
        }
        return null;
    }

    public Astronaut getNonCapturedRandomAstronaut() {
        if (getNonCapturedAstronautCount() > 0) {
            while(true) {
                int randIndex = RandomUtils.getRandomInt(0, this.size() - 1);
                if(isValidAstronaut(this.get(randIndex)))
                    return (Astronaut) this.get(randIndex);
            }
        }
        return null;
    }

    public Astronaut removeNonCapturedRandomAstronaut() {
        if (getNonCapturedAstronautCount() > 0) {
            while(true) {
                int randIndex = RandomUtils.getRandomInt(0, this.size() - 1);
                if(isValidAstronaut(this.get(randIndex)))
                    return (Astronaut) this.remove(randIndex);
            }
        }
        return null;
    }

    // The following *Valid methods return true if the opponent is not captured
    public static boolean isValidAlien(GameObject gameObject) {
        return isAlien(gameObject) && !((Opponent) gameObject).isCaptured();
    }

    public static boolean isValidAstronaut(GameObject gameObject) {
        return isAstronaut(gameObject) && !((Opponent) gameObject).isCaptured();
    }
    
    public static boolean isValidOpponent(GameObject gameObject) {
        return isOpponent(gameObject) && !((Opponent) gameObject).isCaptured();
    }

    public static boolean isOpponentCaptured(GameObject gameObject) {
        return isOpponent(gameObject) && ((Opponent) gameObject).isCaptured();
    }

    // InstanceOf Methods
    public static boolean isAlien(GameObject gameObject) {
        return gameObject instanceof Alien;
    }

    public static boolean isAstronaut(GameObject gameObject) {
        return gameObject instanceof Astronaut;
    }

    public static boolean isSpaceShip(GameObject gameObject) {
        return gameObject instanceof Spaceship;
    }

    public static boolean isOpponent(GameObject gameObject) {
        return gameObject instanceof Opponent;
    }
}

class Iterator implements IIterator<GameObject>{
    private ArrayList<GameObject> gameObjectList;
    private int curIndex;
    
    public Iterator(ArrayList<GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
        curIndex = 0;
    }
    
    public boolean hasNext() {
        return curIndex < gameObjectList.size();
    }

    public GameObject next() {
        GameObject returnObject = gameObjectList.get(curIndex); 
        curIndex ++;
        return returnObject;
    }

    public void remove() {
        gameObjectList.remove(curIndex);
    }
}
