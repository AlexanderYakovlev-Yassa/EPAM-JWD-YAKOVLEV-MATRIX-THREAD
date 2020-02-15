package by.epam.jwd.yakovlev.multithread.dao;

import by.epam.jwd.yakovlev.multithread.dao.exception.DAOLogicException;

import java.io.File;
import java.util.ArrayList;

public interface DAOLogic {

    ArrayList<String> readMatrixBlueprint(File blueprintFile) throws DAOLogicException;
    void saveResult(File resultFile, String result) throws DAOLogicException;
}
