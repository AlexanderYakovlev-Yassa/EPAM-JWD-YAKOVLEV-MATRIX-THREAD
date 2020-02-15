package by.epam.jwd.yakovlev.multithread.runner;

import by.epam.jwd.yakovlev.multithread.service.exception.ServiceException;
import by.epam.jwd.yakovlev.multithread.service.ServiceFactory;
import by.epam.jwd.yakovlev.multithread.service.ServiceLogic;

public class Main {

    private static final ServiceLogic LOGIC = ServiceFactory.getINSTANCE().getServiceLogic();

    public static void main(String[] args) {

        try {
            LOGIC.executeTask(5);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
