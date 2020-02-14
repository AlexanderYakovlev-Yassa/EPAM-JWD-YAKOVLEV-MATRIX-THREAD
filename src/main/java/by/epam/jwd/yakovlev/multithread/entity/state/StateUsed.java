package by.epam.jwd.yakovlev.multithread.entity.state;

import by.epam.jwd.yakovlev.multithread.entity.Cell;

public class StateUsed implements State {

    private static State instance = new StateUsed();

    private final String name = "USED";

    private StateUsed() {
    }

    @Override
    public void markAsUsed(Cell cell) {
        return;
    }

    @Override
    public void markAsFree(Cell cell) {

        if (cell == null){
            return;
        }

        cell.setState(StateFree.getInstance());
    }

    @Override
    public String getName() {
        return name;
    }

    public static State getInstance() {
        return instance;
    }
}
