package by.epam.jwd.yakovlev.multithread.service;

import by.epam.jwd.yakovlev.multithread.entity.Cell;
import by.epam.jwd.yakovlev.multithread.entity.Matrix;
import by.epam.jwd.yakovlev.multithread.service.exception.ServiceException;

import java.io.File;
import java.util.ArrayList;

public interface ServiceLogic {

    void executeTask(int groupCount, File matrixBlueprint, File resultFile) throws ServiceException;

    int getCellRangeSum(ArrayList<Cell> range);

    void printResult();

    void startGroupOfWorkThread();

    Matrix getMatrix();

    StringBuffer getResult();

    void clearResult();

    Matrix buildMatrix(File file) throws ServiceException;

    void sendToResultStorage(File resultFile, String result) throws ServiceException;
}
