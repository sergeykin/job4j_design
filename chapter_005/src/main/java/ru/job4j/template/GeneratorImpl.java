package ru.job4j.template;

import java.util.Map;

public class GeneratorImpl implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) throws Exception {
        String produce = new String(template);
        for (Map.Entry<String, String> entry:args.entrySet()    ) {
            String shablon = "\\$\\{".concat(entry.getKey()).concat("\\}");
            if (!produce.equals(produce.replaceAll(shablon, entry.getValue()))) {
                produce = produce.replaceAll(shablon, entry.getValue());
            } else {
                throw new IllegalArgumentException();
            }

        }
        if (produce.contains("${")) {
            throw new IllegalArgumentException();
        }
        return produce;
    }
}
