package it.discovery.metrics;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * @author isegodin
 */
@Component
public class MyIndicator implements HealthIndicator, InfoContributor {
    @Override
    public Health health() {
        return Health.unknown().withDetail("key", "Value").build();
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("myValue", "Hello");
    }
}
