package by.epam.jwd.yakovlev.multithread.dao.impl;

import by.epam.jwd.yakovlev.multithread.dao.DAOLogic;
import by.epam.jwd.yakovlev.multithread.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.multithread.util.FileUtil;
import by.epam.jwd.yakovlev.multithread.util.exception.FileUtilException;

import java.io.File;
import java.util.ArrayList;

public class DAOLogicImpl implements DAOLogic {

    private final FileUtil FILE_UTIL = FileUtil.getInstance();

    @Override
    public ArrayList<String> readMatrixBlueprint(File blueprintFile) throws DAOLogicException {

        ArrayList<String> matrixBlueprint = null;

        try {
            matrixBlueprint = FILE_UTIL.readFile(blueprintFile);
        } catch (FileUtilException e) {
           throw new DAOLogicException("Can't get the blueprint from source");
        }
        return matrixBlueprint;
    }

    @Override
    public void saveResult(File resultFile, String result) throws DAOLogicException {

        if (result == null){
            throw new DAOLogicException("Nothing to save");
        }

        try {
            FILE_UTIL.addRecordToFile(resultFile, result);
        } catch (FileUtilException e) {
            throw new DAOLogicException("Fail to save the result");
        }
    }
}
