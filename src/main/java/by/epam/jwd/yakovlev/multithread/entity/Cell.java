package by.epam.jwd.yakovlev.multithread.entity;

import by.epam.jwd.yakovlev.multithread.entity.state.State;
import by.epam.jwd.yakovlev.multithread.entity.state.StateFree;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class Cell {

    private Location location;
    private Integer value;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private State state = StateFree.getInstance();
    private final String STATE_FREE = "FREE";

    public Cell(Location location, int value) {
        this.location = location;
        this.value = value;
    }

    public Cell() {
        this.location = new Location(0, 0);
        this.value = 0;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getValue() {

        Integer res = null;

        try {
            lock.lock();
            condition.await(0, TimeUnit.MILLISECONDS);
            res = value;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return res;
    }

    public boolean setValueIfFree(int value) {

        boolean successFlag = false;

        try {
            lock.lock();
            condition.await(0, TimeUnit.MILLISECONDS);

            if (state.getName().equals(STATE_FREE)) {
                this.value = value;
                markAsUsed();
                successFlag = true;
            }
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return successFlag;
    }

    public boolean increaseValue(){

        return setValueIfFree(++value);
    }

    public void markAsFree(){
        state.markAsFree(this);
    }

    public void markAsUsed(){
        state.markAsUsed(this);
    }

    public String getState(){
        return state.getName();
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        return getLocation().equals(cell.getLocation());
    }

    public String getStringLocation() {
        return String.format("[%s, %s]", location.getX(), location.getY());
    }

    @Override
    public int hashCode() {

        int prime = 31;
        int res = 7;

        res = res * prime + location.hashCode();

        return res;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cell{");
        sb.append("location=").append(location);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}