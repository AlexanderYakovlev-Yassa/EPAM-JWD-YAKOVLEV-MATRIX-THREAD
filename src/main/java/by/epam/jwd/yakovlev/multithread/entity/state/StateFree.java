package by.epam.jwd.yakovlev.multithread.entity.state;

import by.epam.jwd.yakovlev.multithread.entity.Cell;

public class StateFree implements State {

    private static State instance = new StateFree();

    private final String name = "FREE";

    private StateFree() {
    }

    @Override
    public void markAsUsed(Cell cell) {

        if (cell == null){
            return;
        }

        cell.setState(StateUsed.getInstance());
    }

    @Override
    public void markAsFree(Cell cell) {
        return;
    }

    @Override
    public String getName() {
        return name;
    }

    public static State getInstance() {
        return instance;
    }
}
