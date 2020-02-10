package by.epam.jwd.yakovlev.matrix.entity.state;

import by.epam.jwd.yakovlev.matrix.entity.Cell;
import by.epam.jwd.yakovlev.matrix.entity.TaskThread;

public class ReadBlock implements CellCondition {

    private static final CellCondition INSTANCE = new ReadBlock();

    private final String conditionName = "READ_BLOCKED";

    private ReadBlock() {
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

    }

    @Override
    public void unblock(CellCondition condition) {
        condition = Unblocked.getINSTANCE();
    }
}
