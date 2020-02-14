package by.epam.jwd.yakovlev.multithread.service;

import by.epam.jwd.yakovlev.multithread.entity.Cell;
import by.epam.jwd.yakovlev.multithread.entity.Matrix;
import by.epam.jwd.yakovlev.multithread.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public interface ServiceLogic {

int getCellRangeSum(ArrayList<Cell> range);
void executeTask() throws ServiceException;
void printResult();
void startGroupOfWorkThread();
void initialiseTask(int groupCount) throws ServiceException;
Matrix getMatrix();
StringBuffer getResult();
void clearResult();
Matrix buildMatrix() throws ServiceException;
void sendToResultStorage(String result) throws ServiceException;
}
