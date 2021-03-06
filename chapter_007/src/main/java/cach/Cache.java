package cach;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(),
                (key, value) -> {
                    if (model.getVersion() != value.getVersion()) {
                        throw new OptimisticException("Versions are not equal");
                    }
                    model.setVersion(model.getVersion() + 1);
                    return model;
                }) == null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

}