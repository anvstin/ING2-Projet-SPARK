FROM cnfldemos/cp-server-connect-datagen:0.5.3-7.1.0

RUN confluent-hub install --no-prompt confluentinc/kafka-connect-hdfs:latest