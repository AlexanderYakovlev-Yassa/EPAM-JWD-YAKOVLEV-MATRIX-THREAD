package by.epam.jwd.yakovlev.matrix.entity.state;

import by.epam.jwd.yakovlev.matrix.entity.Cell;
import by.epam.jwd.yakovlev.matrix.entity.TaskThread;

public class WriteBlock implements CellCondition {

    private static final CellCondition INSTANCE = new WriteBlock();

    private final String conditionName = "WRITE_BLOCKED";

    private WriteBlock() {
    }

    public static CellCondition getINSTANCE() {
        return INSTANCE;
    }

    public String getConditionName() {
        return conditionName;
    }

    @Override
    public void blockForWrite(CellCondition condition) {

    }

    @Override
    public void blockForRead(CellCondition condition) {
        condition = ReadBlock.getINSTANCE();
    }

    @Override
    public void unblock(CellCondition condition) {
        condition = Unblocked.getINSTANCE();
    }
}
