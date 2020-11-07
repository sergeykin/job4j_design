package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> listBotAnswers;
    private UsageEncoding encoding;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        encoding = new UsageEncoding();
        String s = encoding.readFile(botAnswers);
        listBotAnswers = new ArrayList<>(Arrays.asList(s.split(System.lineSeparator())));
    }

    public String getBotMessage() {
        return listBotAnswers.get(ThreadLocalRandom.current().nextInt(listBotAnswers.size() - 1));
    }

    public void run() {
        System.out.println(listBotAnswers);
        Scanner myObj = new Scanner(System.in);
        String userMessage;
        StringBuilder log = new StringBuilder();
        boolean isContinue = true;
        do {
            userMessage = myObj.nextLine();
            log.append(userMessage).append(System.lineSeparator());
            if (userMessage.equals(STOP)) {
                isContinue = false;
            }
            if (userMessage.equals(CONTINUE)) {
                isContinue = true;
            }
            if (isContinue) {
                String botMessage = getBotMessage();
                System.out.println(botMessage);
                log.append(botMessage).append(System.lineSeparator());
            }
        } while (!userMessage.equals(OUT));
        encoding.writeDataInFile(path, log.toString());
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            ConsoleChat cc = new ConsoleChat(args[0], args[1]);
            cc.run();
        } else {
            throw new IllegalArgumentException("Incorrect params");
        }
    }
}
