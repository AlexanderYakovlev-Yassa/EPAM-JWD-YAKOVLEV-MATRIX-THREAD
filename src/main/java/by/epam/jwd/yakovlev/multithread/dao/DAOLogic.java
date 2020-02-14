package by.epam.jwd.yakovlev.multithread.dao;

import by.epam.jwd.yakovlev.multithread.dao.exception.DAOLogicException;

import java.util.ArrayList;

public interface DAOLogic {

    ArrayList<String> readMatrixBlueprint() throws DAOLogicException, DAOLogicException;
    void saveResult(String string) throws DAOLogicException;
}
