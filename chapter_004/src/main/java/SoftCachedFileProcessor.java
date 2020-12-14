import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoftCachedFileProcessor {
    private Map<String, List<String>> cache;
    private SoftReference<Map<String, List<String>>> softReference;

    public SoftCachedFileProcessor() {
        this.cache = new HashMap<>();
        this.softReference = new SoftReference<>(cache);
    }

    public List<String> getFile(String fileName) throws IOException {
        if (cache.containsKey(fileName) && softReference.get().get(fileName) != null) {
            return softReference.get().get(fileName);
        } else {
            List<String> list = Files.readAllLines(Paths.get(fileName));
            softReference.get().put(fileName, list);
            return list;
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("names.txt");
        File fileadr = new File("Address.txt");
        SoftCachedFileProcessor softCachedFileProcessor = new SoftCachedFileProcessor();
        System.out.println(softCachedFileProcessor.getFile(file.getPath()));
        System.out.println(softCachedFileProcessor.getFile(fileadr.getPath()));
        System.out.println(softCachedFileProcessor.getFile(file.getPath()));
    }
}
