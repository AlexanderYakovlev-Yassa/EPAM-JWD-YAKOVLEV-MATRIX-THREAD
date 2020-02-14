package by.epam.jwd.yakovlev.multithread.entity.state;

import by.epam.jwd.yakovlev.multithread.entity.Cell;

public interface State {

    void markAsUsed(Cell cell);
    void markAsFree(Cell cell);
    String getName();
}
