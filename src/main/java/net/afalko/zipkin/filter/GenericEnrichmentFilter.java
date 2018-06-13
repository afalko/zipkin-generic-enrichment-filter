package net.afalko.zipkin.filter;

import zipkin2.Callback;
import zipkin2.Span;
import zipkin2.collector.CollectorMetrics;
import zipkin2.collector.filter.SpanFilter;

import java.util.List;
import java.util.stream.Collectors;

public class GenericEnrichmentFilter implements SpanFilter {

    public static final String ZIPKIN_TAG = "ZIPKIN_TAG";

    public List<Span> process(List<Span> spans, CollectorMetrics metrics, Callback<Void> callback) {
        if (System.getenv(ZIPKIN_TAG) == null) {
            return spans;
        }
        // TODO: Error handling
        String[] tag = System.getenv(ZIPKIN_TAG).split(":");
        String key = tag[0];
        String value = tag[1];

        return spans.stream()
                .map(span -> span.toBuilder()
                        .putTag(key, value)
                        .build())
                .collect(Collectors.toList());
    }
}
