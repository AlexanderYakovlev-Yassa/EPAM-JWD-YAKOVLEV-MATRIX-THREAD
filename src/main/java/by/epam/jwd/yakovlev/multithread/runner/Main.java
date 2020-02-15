package by.epam.jwd.yakovlev.multithread.runner;

import by.epam.jwd.yakovlev.multithread.service.exception.ServiceException;
import by.epam.jwd.yakovlev.multithread.service.ServiceFactory;
import by.epam.jwd.yakovlev.multithread.service.ServiceLogic;

import java.io.File;

public class Main {

    private static final ServiceLogic LOGIC = ServiceFactory.getINSTANCE().getServiceLogic();

    public static void main(String[] args) {

        final File RESULT_FILE = new File("src\\main\\resources\\files\\MatrixResult.txt");
        final File SOURCE_FILE = new File("src\\main\\resources\\files\\MatrixSource.txt");

        try {
            LOGIC.executeTask(5, SOURCE_FILE, RESULT_FILE);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
