# spring-cloud-stream

Standard config with a supplier and a consumer function and a single binder used by default

```yaml
spring:
  application:
    name: spring-cloud-stream
  cloud:
    stream:
      function:
        definition: supplier;consumer
      bindings:
        supplier-out-0:
          destination: multi-partitions
        consumer-in-0:
          destination: multi-partitions
          group: group1
          consumer:
            concurrency: 2
      kafka:
        binder:
          brokers: localhost:9092,localhost:9093
```

Function alias
```yaml
spring:
  application:
    name: spring-cloud-stream
  cloud:
    stream:
      function:
        definition: supplier;consumer
        bindings:
            supplier-out-0: out-alias
            consumer-in-0: in-alias
      bindings:
        out-alias:
          destination: multi-partitions
        in-alias:
          destination: multi-partitions
          group: group1
          consumer:
            concurrency: 2
      kafka:
        binder:
          brokers: localhost:9092,localhost:9093
```

Multiple binders
```yaml
spring:
  application:
    name: spring-cloud-stream
  cloud:
    stream:
      function:
        definition: supplier;consumer
      bindings:
        supplier-out-0:
          destination: multi-partitions
          binder: kafka1
        consumer-in-0:
          destination: multi-partitions
          binder: kafka2
          group: group1
          consumer:
            concurrency: 2
      binders:
        kafka1:
          type: kafka
          brokers: host1:9092
        kafka2:
          brokers: host2:9092
```