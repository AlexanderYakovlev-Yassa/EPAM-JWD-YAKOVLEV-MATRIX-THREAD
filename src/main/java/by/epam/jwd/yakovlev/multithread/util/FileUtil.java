package by.epam.jwd.yakovlev.multithread.util;

import by.epam.jwd.yakovlev.multithread.util.exception.FileUtilException;

import java.io.*;
import java.util.ArrayList;

public class  FileUtil {

    private static final FileUtil INSTANCE = new FileUtil();

    private FileUtil() {
    }

    public static FileUtil getInstance() {
        return INSTANCE;
    }

    public ArrayList<String> readFile(File file) throws FileUtilException {


        if (file == null) {
            throw new FileUtilException("File is null");
        }

        ArrayList<String> fileContentByRows = new ArrayList<String>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                    fileContentByRows.add(tmp);
            }
        } catch (IOException e) {
            throw new FileUtilException("File not found", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new FileUtilException("Can't close reader", e);
                }
            }
        }

        return fileContentByRows;
    }

    public boolean rewriteFile(File file, ArrayList<String> newContent) throws FileUtilException {

        if (newContent == null){
            throw new FileUtilException("New content is null");
        }
        if (file == null){
            throw new FileUtilException("File is null");
        }

        boolean res = false;

        FileWriter fw = null;
        try {
            fw = new FileWriter(file, false);
        } catch (IOException e) {
            throw new FileUtilException("File not found", e);
        }

        BufferedWriter bw = new BufferedWriter(fw);

        PrintWriter pw = new PrintWriter(bw);

        for (String s : newContent)
            pw.println(s);
        pw.close();

        return res;
    }

    public void addRecordToFile(File file, String newRecord) throws FileUtilException {

        if (newRecord == null){
            throw new FileUtilException("New record is null");
        }
        if (file == null){
            throw new FileUtilException("File is null");
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            throw new FileUtilException("File not found", e);
        }
        BufferedWriter bw = new BufferedWriter(fw);

        PrintWriter pw = new PrintWriter(bw);

        pw.print("\n");
        pw.print(newRecord);
        pw.close();
    }
}
