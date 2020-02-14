package by.epam.jwd.yakovlev.multithread.dao.impl;

import by.epam.jwd.yakovlev.multithread.dao.DAOLogic;
import by.epam.jwd.yakovlev.multithread.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.multithread.util.FileUtil;
import by.epam.jwd.yakovlev.multithread.util.exception.FileUtilException;

import java.io.File;
import java.util.ArrayList;

public class DAOLogicImpl implements DAOLogic {

    private final FileUtil FILE_UTIL = FileUtil.getInstance();
    private final File RESULT_FILE = new File("src\\main\\resources\\files\\MatrixResult.txt");
    private final File SOURCE_FILE = new File("src\\main\\resources\\files\\MatrixSource.txt");

    @Override
    public ArrayList<String> readMatrixBlueprint() throws DAOLogicException {

        ArrayList<String> matrixBlueprint = null;

        try {
            matrixBlueprint = FILE_UTIL.readFile(SOURCE_FILE);
        } catch (FileUtilException e) {
           throw new DAOLogicException("Can't get the blueprint fron source");
        }
        return matrixBlueprint;
    }

    @Override
    public void saveResult(String result) throws DAOLogicException {

        if (result == null){
            throw new DAOLogicException("Nothing to save");
        }

        try {
            FILE_UTIL.addRecordToFile(RESULT_FILE, result);
        } catch (FileUtilException e) {
            throw new DAOLogicException("Fail to save the result");
        }
    }
}
