package by.epam.jwd.yakovlev.matrix.entity.state;

import by.epam.jwd.yakovlev.matrix.entity.Cell;
import by.epam.jwd.yakovlev.matrix.entity.TaskThread;

public class Unblocked implements CellCondition {

    private static final CellCondition INSTANCE = new Unblocked();

    private final String conditionName = "UNBLOCKED";

    private Unblocked() {
    }

    public static CellCondition getINSTANCE() {
        return INSTANCE;
    }

    public String getConditionName() {
        return conditionName;
    }

    @Override
    public void blockForWrite(CellCondition condition) {
        condition = WriteBlock.getINSTANCE();
    }

    @Override
    public void blockForRead(CellCondition condition) {
       condition = ReadBlock.getINSTANCE();
    }

    @Override
    public void unblock(CellCondition condition) {

    }
}
