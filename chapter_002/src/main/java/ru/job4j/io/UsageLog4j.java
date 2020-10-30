package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);

        String first = "Kinyapin";
        int second = 22;
        byte	third = 3;
        short	four = 450;
        long	five = 44444444443L;
        char	six = 'A';
        float	seven = 3434.43F;
        double	eight = 34342222.34342;
        boolean	nine = true;
        LOG.debug("first : {}, second : {}, third : {}, four : {}, five : {}, six : {}, seven : {}, eight : {}, nine : {}", first, second, third, four, five, six, seven, eight, nine);

        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
