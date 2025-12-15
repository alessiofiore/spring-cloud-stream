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



```
server:
  port: 8080

spring:
  application: 
    name: messaging-service

  cloud:
    stream:
      # Configurazione dei Kafka Binders multipli
      binders:
        kafka-binder-1:
          type:  kafka
          environment:
            spring: 
              cloud:
                stream:
                  kafka:
                    binder:
                      brokers:  localhost:9092
                      auto-create-topics: true
                      configuration:
                        key. serializer: org.apache.kafka.common.serialization.StringSerializer
                        value.serializer: org.apache.kafka.common. serialization.StringSerializer
        
        kafka-binder-2:
          type: kafka
          environment:
            spring:
              cloud:
                stream:
                  kafka:
                    binder:
                      brokers: localhost:9093
                      auto-create-topics: true
                      configuration: 
                        key.serializer: org.apache.kafka.common.serialization.StringSerializer
                        value.serializer: org.apache. kafka.common.serialization.StringSerializer
        
        kafka-binder-3:
          type:  kafka
          environment:
            spring:
              cloud:
                stream:
                  kafka:
                    binder:
                      brokers: localhost:9094
                      auto-create-topics: true
                      configuration:
                        key.serializer: org.apache.kafka.common.serialization. StringSerializer
                        value.serializer: org.apache.kafka. common.serialization.StringSerializer

      # Configurazione dei Bindings
      bindings:
        # Binding 1 - usa kafka-binder-1
        orders-out-0:
          destination: orders. topic
          binder: kafka-binder-1
          content-type: application/json
          producer:
            partition-count: 3
        
        # Binding 2 - usa kafka-binder-1
        notifications-out-0:
          destination: notifications.topic
          binder: kafka-binder-1
          content-type: application/json
        
        # Binding 3 - usa kafka-binder-2
        payments-out-0:
          destination: payments.topic
          binder: kafka-binder-2
          content-type: application/json
          producer:
            partition-count:  5
        
        # Binding 4 - usa kafka-binder-2
        inventory-out-0:
          destination: inventory.topic
          binder: kafka-binder-2
          content-type: application/json
        
        # Binding 5 - usa kafka-binder-3
        analytics-out-0:
          destination: analytics.topic
          binder: kafka-binder-3
          content-type: application/json
        
        # Binding 6 - usa kafka-binder-3
        audit-out-0:
          destination: audit.topic
          binder: kafka-binder-3
          content-type: application/json

# Configurazione custom per gli alias dei binding
messaging:
  bindings:
    aliases:
      orders:  orders-out-0
      notifications:  notifications-out-0
      payments: payments-out-0
      inventory: inventory-out-0
      analytics: analytics-out-0
      audit: audit-out-0

# Actuator endpoints
management:
  endpoints:
    web:
      exposure:
        include: health,info,bindings,metrics
  endpoint: 
    health:
      show-details: always

logging:
  level:
    org.springframework.cloud.stream: DEBUG
    org.apache.kafka: INFO
```
