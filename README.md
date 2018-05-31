# zipkin-generic-enrichment-filter
This is a Zipkin span filter that allows you to inject a tag from your environment where you Zipkin server is running. 

To run it locally do the following: 

```
mvn install
cd <path-to-zipkin-project>
ZIPKIN_V2_TAG=datacenter:local ZIPKIN_V1_ANNOTATION=datacenter:local \
    java -Dloader.path=/home/andrey/.m2/repository/net/afalko/zipkin/filter/zipkin-generic-enrichment-filter/1.0-SNAPSHOT/zipkin-generic-enrichment-filter-1.0-SNAPSHOT.jar \
        -cp zipkin-server/target/zipkin-server-*exec.jar \
        org.springframework.boot.loader.PropertiesLauncher
```