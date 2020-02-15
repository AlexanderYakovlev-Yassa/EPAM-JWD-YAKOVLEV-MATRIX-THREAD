package by.epam.jwd.yakovlev.multithread.service.impl;

import by.epam.jwd.yakovlev.multithread.entity.Matrix;
import by.epam.jwd.yakovlev.multithread.service.ServiceFactory;
import by.epam.jwd.yakovlev.multithread.service.ServiceLogic;
import by.epam.jwd.yakovlev.multithread.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;


public class ServiceLogicImplTest {

    private static final ServiceLogic LOGIC = ServiceFactory.getINSTANCE().getServiceLogic();
    private static final File SOURCE_FILE = new File("src\\test\\resources\\files\\MatrixSource.txt");
    private static final File BROKEN_SOURCE_FILE = new File("src\\test\\resources\\files\\BrokenMatrixSource.txt");

    @Test
    public void buildMatrixTest() {

        Matrix matrix = null;

        try {
            matrix = LOGIC.buildMatrix(SOURCE_FILE);
        } catch (ServiceException e) {
            Assert.fail();
        }

        Assert.assertNotNull(matrix);
        Assert.assertEquals(4, matrix.getDimension());
        Assert.assertEquals(new Integer(1), matrix.getCellByLocation(0, 0).getValue());
        Assert.assertEquals(new Integer(4), matrix.getCellByLocation(3, 3).getValue());
    }

    @Test(expected = ServiceException.class)
    public void buildMatrixExceptionTest() throws ServiceException {

        Matrix matrix = null;

        matrix = LOGIC.buildMatrix(BROKEN_SOURCE_FILE);
    }
}