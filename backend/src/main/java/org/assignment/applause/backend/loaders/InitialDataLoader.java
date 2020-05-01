package org.assignment.applause.backend.loaders;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitialDataLoader {

    private List<AbstractEntityLoader> entityLoaders;

    public InitialDataLoader(List<AbstractEntityLoader> entityLoaders) {
        this.entityLoaders = entityLoaders;
        this.loadEntities();
    }

    @PostConstruct
    public void init() {
        entityLoaders.sort(AnnotationAwareOrderComparator.INSTANCE);
    }

    private void loadEntities() {
        this.entityLoaders.forEach(AbstractEntityLoader::loadEntity);
    }
}
