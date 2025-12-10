# spring-cloud-stream

Standard config with a supplier and a consumer function and a single binder used by default

```yaml
spring:
  application:
    name: spring-cloud-stream
  cloud:
    function:
      definition: supplier;consumer
    stream:
      bindings:
        supplier-out-0:
          destination: some-topic
        consumer-in-0:
          destination: some-topic
          group: group1
          consumer:
            concurrency: 2
      kafka:
        binder:
          brokers: localhost:9092,localhost:9093
```

Binding alias
```yaml
spring:
  application:
    name: spring-cloud-stream
  cloud:
    function:
      definition: supplier;consumer
    stream:
      function:
        bindings:
            supplier-out-0: out-alias
            consumer-in-0: in-alias
      bindings:
        out-alias:
          destination: some-topic
        in-alias:
          destination: some-topic
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
    function:
      definition: supplier;consumer
    stream:
      bindings:
        supplier-out-0:
          destination: some-topic
          binder: kafka1
        consumer-in-0:
          destination: some-topic
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

Multiple binders, multiple alias
```yaml
spring:
  application:
    name: spring-cloud-stream
  cloud:
    function:
      definition: supplier;consumer
    stream:
      function:
        bindings:
            supplier-out-0: out-alias1
            supplier-out-1: out-alias2
            consumer-in-0: in-alias
      bindings:
        out-alias1:
          destination: some-topic1
          binder: kafka1
        out-alias2:
          destination: some-topic2
          binder: kafka2
        in-alias:
          destination: some-topic
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
