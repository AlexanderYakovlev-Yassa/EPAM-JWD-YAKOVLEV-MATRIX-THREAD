package by.epam.jwd.yakovlev.multithread.dao;

import by.epam.jwd.yakovlev.multithread.dao.impl.DAOLogicImpl;

public class DAOFactory {

    private static final DAOFactory INSTANCE = new DAOFactory();

    private final DAOLogic DAO_LOGIC = new DAOLogicImpl();

    private DAOFactory() {
    }

    public static DAOFactory getINSTANCE() {
        return INSTANCE;
    }

    public DAOLogic getDAO_LOGIC() {
        return DAO_LOGIC;
    }
}
