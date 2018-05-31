package net.afalko.zipkin.filter;

import zipkin.Annotation;
import zipkin.Span;
import zipkin.filter.FilterActivatedException;
import zipkin.filter.SpanFilter;
import zipkin.storage.Callback;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GenericEnrichmentFilter implements SpanFilter {

    public static final String ZIPKIN_V1_ANNOTATION = "ZIPKIN_V1_ANNOTATION";
    public static final String ZIPKIN_V2_TAG = "ZIPKIN_V2_TAG";

    public List<Span> processV1(List<Span> spans, Callback<Void> callback) {
        if (System.getenv(ZIPKIN_V1_ANNOTATION) == null) {
            return spans;
        }
        String v1Tag = System.getenv(ZIPKIN_V1_ANNOTATION);

        return spans.stream()
                .map(span -> span.toBuilder()
                        .addAnnotation(Annotation.builder()
                                .timestamp(Instant.now().truncatedTo(ChronoUnit.MICROS).toEpochMilli())
                                .value(v1Tag)
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

    public List<zipkin2.Span> processV2(List<zipkin2.Span> spans, Callback<Void> callback) {
        if (System.getenv(ZIPKIN_V2_TAG) == null) {
            return spans;
        }
        // TODO: Error handling
        String[] v2Tag = System.getenv(ZIPKIN_V2_TAG).split(":");
        String v2Key = v2Tag[0];
        String v2Value = v2Tag[1];

        return spans.stream()
                .map(span -> span.toBuilder()
                        .putTag(v2Key, v2Value)
                        .build())
                .collect(Collectors.toList());
    }
}
