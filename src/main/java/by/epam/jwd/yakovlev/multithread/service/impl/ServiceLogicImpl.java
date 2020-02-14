package by.epam.jwd.yakovlev.multithread.service.impl;


import by.epam.jwd.yakovlev.multithread.dao.DAOFactory;
import by.epam.jwd.yakovlev.multithread.dao.DAOLogic;
import by.epam.jwd.yakovlev.multithread.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.multithread.entity.*;
import by.epam.jwd.yakovlev.multithread.service.exception.ServiceException;
import by.epam.jwd.yakovlev.multithread.service.ServiceLogic;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceLogicImpl implements ServiceLogic {

    private static DAOLogic DAO_LOGIC = DAOFactory.getINSTANCE().getDAO_LOGIC();

    private Integer matrixDimension;
    private Matrix matrix;
    private Integer groupCount;
    private Integer groupCounter;
    private StringBuffer result;

    @Override
    public void initialiseTask(int groupCount) throws ServiceException {

        try {
            this.matrix = buildMatrix();
        } catch (ServiceException e) {
            throw e;
        }

        if (groupCount < 1 || groupCount > this.matrix.getDimension()) {
            throw new ServiceException("Wrong initialise data!!!");
        }

        this.matrixDimension = this.matrix.getDimension();

        this.groupCount = groupCount;

        result = new StringBuffer("The source matrix:\n");
        result.append(matrix.getStringVisualisedMatrix());
        result.append("\n\n");

        groupCounter = 0;
    }

    @Override
    public int getCellRangeSum(ArrayList<Cell> range) {

        int sum = 0;

        for (Cell c : range) {
            sum += c.getValue();
        }

        return sum;
    }

    @Override
    public void executeTask() throws ServiceException {

        if (!isTaskInitialised()) {
            throw new ServiceException("Task is not initialised!!!");
        }

        for (int i = 0; i < groupCount; i++) {

            result.append("\nThreads result:\n");
            startGroupOfWorkThread();
            result.append("\nResult matrix:\n");
            result.append(getMatrix().getStringVisualisedMatrix());
            result.append("\n\nResult state matrix:\n");
            result.append(getMatrix().getStringVisualisedMatrixState());
            result.append("\n");
            sendToResultStorage(result.toString());
            printResult();
            clearResult();
            matrix.resetCellsState();
        }
    }

    @Override
    public void startGroupOfWorkThread() {

        WorkThread[] workThreadArray = new WorkThread[matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {

            workThreadArray[i] = new WorkThread(groupCounter);
            workThreadArray[i].start();
            groupCounter++;
        }
        for (WorkThread w : workThreadArray) {
            try {
                w.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Matrix getMatrix() {
        return matrix;
    }

    @Override
    public StringBuffer getResult() {
        return result;
    }


    @Override
    public void clearResult() {
        result = new StringBuffer();
    }

    @Override
    public void printResult() {

        System.out.println(result.toString());
    }

    @Override
    public Matrix buildMatrix() throws ServiceException {

        ArrayList<String> matrixBlueprint = null;

        try {
            matrixBlueprint = DAO_LOGIC.readMatrixBlueprint();
        } catch (DAOLogicException e) {
            throw new ServiceException("Can't get the blueprint of the matrix", e);
        }

        if (matrixBlueprint == null) {
            throw new ServiceException("Can't get the blueprint of the matrix");
        }

        int matrixDimension = matrixBlueprint.size();

        Matrix matrix = new Matrix(matrixDimension);
        matrix.resetCellsState();

        ArrayList<Integer> tempRowOfInteger;

        for (int i = 0; i < matrixDimension; i++) {

            if ((tempRowOfInteger = parseToIntegerList(matrixBlueprint.get(i))) == null) {

                return null;
            }
            if (tempRowOfInteger.size() != matrixDimension) {

                return null;
            }

            for (int j = 0; j < matrixDimension; j++) {

                matrix.getCellByLocation(j, i).setValueIfFree(tempRowOfInteger.get(j));
            }
        }

        matrix.resetCellsState();

        return matrix;
    }

    @Override
    public void sendToResultStorage(String result) throws ServiceException {

        if (result == null) {
            throw new ServiceException("There is nothing to save !!!");
        }

        try {
            DAO_LOGIC.saveResult(result);
        } catch (DAOLogicException e) {
            throw new ServiceException("Fail to put to a storage !!!", e);
        }
    }

    private ArrayList<Integer> parseToIntegerList(String string) {

        final String PATTERN = "[\\[][\\s]*([\\d]+)[\\]]";

        ArrayList<Integer> integerList = new ArrayList<>();
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(string);

        Integer tmp = null;
        while (m.find()) {
            try {
                tmp = Integer.parseInt(m.group(1));
            } catch (NumberFormatException e) {
                return null;
            }
            integerList.add(tmp);
        }

        return integerList;
    }

    private boolean isTaskInitialised() {

        return matrixDimension != null &&
                matrix != null &&
                groupCount != null &&
                groupCounter != null &&
                result != null;
    }
}
