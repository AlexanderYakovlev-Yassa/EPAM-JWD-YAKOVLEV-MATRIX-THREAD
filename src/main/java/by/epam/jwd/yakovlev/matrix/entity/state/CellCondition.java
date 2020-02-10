package by.epam.jwd.yakovlev.matrix.entity.state;

import by.epam.jwd.yakovlev.matrix.entity.Cell;
import by.epam.jwd.yakovlev.matrix.entity.TaskThread;

public interface CellCondition {

    String getConditionName();
    void blockForWrite(CellCondition condition);
    void blockForRead(CellCondition condition);
    void unblock(CellCondition condition);
}
