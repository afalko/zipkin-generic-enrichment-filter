package net.afalko.zipkin.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.collector.filter.SpanFilter;

@Configuration
public class GenericEnrichmentFilterAutoConfiguration {
    @Bean
    public SpanFilter genericEnrichmentFilterSpan() {
        return new GenericEnrichmentFilter();
    }
}
