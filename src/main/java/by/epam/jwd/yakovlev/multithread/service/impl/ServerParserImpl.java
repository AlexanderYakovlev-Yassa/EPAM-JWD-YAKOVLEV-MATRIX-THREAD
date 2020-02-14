package by.epam.jwd.yakovlev.multithread.service.impl;

import by.epam.jwd.yakovlev.multithread.service.ServiceParser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerParserImpl implements ServiceParser {

    @Override
    public ArrayList<Integer> parseToIntegerList(String string) {

        final String PATTERN = "[\\[][\\s]*[\\d]+[\\]]";

        ArrayList<Integer> integerList = new ArrayList<>();
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(string);

        Integer tmp = null;
        while (m.find()){
            try {
                tmp = Integer.parseInt(m.group(1).trim());
            } catch(NumberFormatException e){
                return null;
            }
            integerList.add(tmp);
        }

        return integerList;
    }
}
