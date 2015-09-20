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

The HTTP service runs on port 8080 by default and you can query the data

| API | Operation |
| --- | --- |
| /{id} | id - itemId to query for |

Sample output JSON would be
```json
{
    "publisher": "William Morrow",
    "authors": "Levitt & Dubner",
    "title": "freakonomics",
    "list price": "7",
    "release date": "20-09-2011"
}
```

### Subscription Configuration
```hocon
# Add all your subscriptions in here. The format is as follows
#  {
#   notifier = "whom should I notify?"
#   filters = [{
#     name = "attribute name"
#     type = "type of the attribute" # accepted values - int, string, date
#     operator = "eq" # accepted values - eq, gt, lt, ge, le
#     value = "value of the attribute to check"
#   }]
#  }
#
# You can specify multiple filters and by default we'll always AND them.
#
# FIXME - Move this to a store so that we can in-corporate updates while the system is running

subscriptions = [{
  notifier = "subscriber-test"
  filters = [{
    name = "title"
    type = "string"
    operator = "eq"
    value = "freakonomics"
  }]
}, {
  notifier = "subscriber1"
  filters = [{
    name = "release date"
    type = "date"
    operator = "gt"
    value = "01-01-2000"
  }]
}, {
  notifier = "subscriber2"
  filters = [{
    name = "publisher"
    type = "string"
    operator = "eq"
    value = "Addison-Wesley"
  }, {
    name = "list price"
    type = "int"
    operator = "ge"
    value = "10"
  }]
}]
# You can specify multiple filters and by default we'll always AND them.
```

### Configuration
```
meghaduta {
  # Store implementation - redis, rocksdb (experimental)
  store-type = "redis"
  db-location = "localhost"

  # Relative to the project root - can also be an absolute path
  shared-folder = "shared-folder/IN"

  # Notifier implementation
  notifier-type = "file"
  notify-output-file = "notifications.log"
}
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
