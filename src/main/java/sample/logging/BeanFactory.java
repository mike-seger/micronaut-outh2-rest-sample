package sample.logging;

import io.micronaut.context.annotation.Factory;
import org.zalando.logbook.Logbook;
import javax.inject.Singleton;

@Factory
class BeanFactory {
    @Singleton
    public Logbook logbook() {
        return Logbook.create();
    }
}