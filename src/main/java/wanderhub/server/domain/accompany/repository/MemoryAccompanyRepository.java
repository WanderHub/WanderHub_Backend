package wanderhub.server.domain.accompany.repository;

import wanderhub.server.domain.accompany.entity.Accompany;

import java.util.*;

public class MemoryAccompanyRepository implements AccompanyRepository2 {

    private static Map<Long, Accompany> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Accompany save(Accompany accompany) {
 //       accompany.setId(++sequence);
        store.put(accompany.getId(), accompany);
        return accompany;
    }

    @Override
    public Optional<Accompany> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Accompany> findByName(String name) {
        return store.values().stream()
                .filter(accompany->accompany.getWriterName().equals(name))
                .findAny();
    }

    @Override
    public List<Accompany> findAll() {
        return new ArrayList<>(store.values());
    }
}
