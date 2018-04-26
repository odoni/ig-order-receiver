# ig-order-receiver
The goal of this service is to provide a simple interface that allows users to upload an order file in XML format. The interface also provides a visualizer to the queue status.

## Instructions to run
Create a runnable and deployable jar file
```txt
  mvn package
``` 

Run the service
```txt
java -jar /path/to/jar/ig-order-receiver-0.0.1-SNAPSHOT.jar
```

Access
```url
  http://localhost:8080/order
```

## Pre-requirements
- ActiveMQ `5.15.3`
- Java `8`
- Port `8080` available

## Sample Input

```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <Order>
     <accont>AX001</accont>
     <SubmittedAt>1507060723641</SubmittedAt>
     <ReceivedAt>1507060723642</ReceivedAt>
     <market>VOD.L</market>
     <action>BUY</action>
     <size>100</size>
  </Order>
  <Order>
     <accont>AX002</accont>
     <SubmittedAt>1507060723651</SubmittedAt>
     <ReceivedAt>1507060723652</ReceivedAt>
     <market>VOD.L</market>
     <action>BUY</action>
     <size>200</size>
  </Order>
```