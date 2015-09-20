# Meghaduta

### Dependencies
- JDK7
- Redis

### Design
File Changes are monitored through a Storm topology that propogates the events. 
![Meghaduta Storm Topology](https://raw.githubusercontent.com/ashwanthkumar/meghaduta/master/docs/storm_topology.png)

The event gets propagated through each stage where the item is built incrementally and finally the notifier is notified (pluggable).

### Getting Started
```bash
$ mvn clean package

# To start the storm Topology
$ java -cp target/meghaduta-1.0.0-SNAPSHOT.jar meghaduta.storm.MeghaDutaTopology

# To start the HTTP Service
$java -cp target/meghaduta-1.0.0-SNAPSHOT.jar meghaduta.service.MeghaDutaService -server
```

<hr />

### Problem Description
The goal is to build a configurable notification system, which receives inputs in a CSV file and notifies its subscribers based on the condition set.

### System Requirements

- The system should consume CSV files which are dropped into a shared folder which are of the form item,attribute_name,attribute_value. The CSV files will be dropped into the shared folder at any point in time.
- The system should be able to notify the attribute changes for items to different subscribers based on different configurable conditions
- The notifications can be sent on any asynchronous messaging system
- The system should also be able to support queries to get all the attributes for a given item at any time
- The system should be scalable to
-- accept large number of/large sized CSV files
-- support large number of subscriber configurations
-- support high throughput and low latency on the get query

### Sample Files:

`file_1.csv`

```csv
13579,title,freakonomics
13579,authors,Levitt & Dubner
13579,release date,20-09-2011
13579,list price,7 USD
13579,publisher,William Morrow
1248,title,Effective Java
1248,authors,Joshua Bloch
1248,list price,10 USD
1248,publisher,Addison-Wesley
```

`file_2.csv`

```csv
13579, list price,11 USD
```

### Sample Subscriber Configurations

#### Sample conditions are: 
```
if release date >= 01-01-2000, notify to subscriber1 
if publisher is Addison-Wesley and list price is >= 10, notify to subscriber2
```

#### Sample Query
```
GET 13579
```

##### Result Set:
```json
title=freakonomics
authors=Levitt & Dubner
release date = 20-09-2011
list price=11 USD
publisher=William Morrow
````

### Technology

Programming Language: Java, but using C#/ C++ is acceptable 
Other Technologies: AWS

