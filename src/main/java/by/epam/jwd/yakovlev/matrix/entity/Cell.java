package by.epam.jwd.yakovlev.matrix.entity;

import by.epam.jwd.yakovlev.matrix.entity.state.CellCondition;
import by.epam.jwd.yakovlev.matrix.entity.state.ConditionNameEnum;
import by.epam.jwd.yakovlev.matrix.entity.state.Unblocked;
import by.epam.jwd.yakovlev.matrix.exception.CellAccessException;

import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class Cell {
    private Point location;
    private ConditionNameEnum condition;
    private TaskThread owner;
    private Integer value;

    public Point getLocation() {
        return location;
    }

    public Integer getValue(TaskThread thread) throws CellAccessException {

        if (condition == ConditionNameEnum.WRITE_BLOCKED && owner != thread ){
            return null;
        }
        return value;
    }

    public void setValue(TaskThread thread, int value) {

        if (owner == null || owner == thread){
            this.value = value;
        }

    }

    public ConditionNameEnum getCondition() {
        return condition;
    }

    public TaskThread getOwner() {
        return owner;
    }

    public boolean blockCellForWrite(TaskThread thread) {

        switch (this.condition) {
            case UNBLOCKED: {
                this.condition = ConditionNameEnum.WRITE_BLOCKED;
                this.owner = thread;
                return true;
            }
            case WRITE_BLOCKED: {
                if (this.owner == thread) {
                    return true;
                }
                return false;
            }
            case READ_BLOCKED:
                if (this.owner == thread) {
                    this.condition = ConditionNameEnum.READ_BLOCKED;
                    return true;
                }
                return false;
        }
        return false;
    }

    public boolean blockCellForRead(TaskThread thread) {

        switch (this.condition) {
            case UNBLOCKED: {
                this.condition = ConditionNameEnum.READ_BLOCKED;
                this.owner = thread;
                return true;
            }
            case WRITE_BLOCKED: {
                if (this.owner == thread) {
                    this.condition = ConditionNameEnum.READ_BLOCKED;
                    return true;
                }
                return false;
            }
            case READ_BLOCKED:
                if (this.owner == thread) {
                    return true;
                }
                return false;
        }
        return false;
    }

    public boolean unblock(TaskThread thread) {

        switch (this.condition) {
            case UNBLOCKED: {
                return true;
            }
            case WRITE_BLOCKED: {
                if (this.owner == thread) {
                    this.condition = ConditionNameEnum.UNBLOCKED;
                    this.owner = null;
                    return true;
                }
                return false;
            }
            case READ_BLOCKED:
                if (this.owner == thread) {
                    this.condition = ConditionNameEnum.UNBLOCKED;
                    this.owner = null;
                    return true;
                }
                return false;
        }
        return false;
    }
}
