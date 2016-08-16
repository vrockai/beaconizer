# Beaconizer [![Build Status](https://travis-ci.org/mcupak/beaconizer.svg?branch=develop)](https://travis-ci.org/mcupak/beaconizer) [![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/mcupak/beaconizer/develop/LICENSE)


## Contents

* [What it is](#what-it-is)
* [System Requirements](#system-requirements)
* [Beacon Adapters](#beacon-adapters)
    * [Adding an adapter to a project](#adding-an-adapter-to-a-project)
    * [Configuring a beacon adapter](#configuring-a-beacon-adapter)
* [REST API](#rest-api)

## What it is
    
The Beaconizer is a tool which takes as its source any number of beacon adapters and makes them available through a common rest-api compliant with V0.3 of the beacon specification. The beaconizer is additionally responsible for the configuration of each adapter, as well as its life cycle. It is very similar in function to the Beacon-Java BDK, however there is no limit to the number of different data sources that can be listed. (Additionally it supports more complicated configuration)

## System requirements

All you need to build this project is Java 8.0 (Java SDK 1.8) or later, Maven 3.0 or later. Since the project is Java EE based, an application server with support for Java EE 7 is needed to deploy the application (e.g. JBoss EAP or WildFly). 

## Beacon Adapters

Beacon Adapters are data type specific implementations of the [BeaconAdapter Interface](https://github.com/mcupak/beacon-adapter-api), which enables the Beaconizer to access various different data sources with minimal configuration. Adapter implementations are responsible for handling all data access requests to their data source, and ensuring the data returned is in the proper format (V.03 of the Beacon specification). A single adapter can be reused to access multiple different data-sources (all of the same datatype) by simply providing it with another configuration.

### Adding an adapter to a project

Adding an adapter to a project is as easy as adding it as a dependency in the pom.xml file. This will make it available for use during the configuration steps. For example, the sample beacon adapter provided in the [Beacon-Java repository](https://github.com/mcupak/beacon-java/tree/develop/sample-beacon-adapter) would be added like this:

```xml
   <dependencies>
        <dependency>
            <groupId>com.dnastack</groupId>
            <artifactId>sample-beacon-adapter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
   </dependencies>
```

### Configuring a beacon adapter

Once a BeaconAdapter has been made available to the class path you then must also provide it with a configuration. By Default, the [BeconizerDaoImpl](src/main/java/com/dnastack/beacon/beaconizer/dao/impl/BeaconizerDaoImpl) reads its configuration from a json file which can be found [here](src/main/resources/beacons.java). The File is a Json Representation of the [AdapterConfig](https://github.com/mcupak/beacon-adapter-api/tree/develop/src/main/java/com/dnastack/beacon/utils/AdapterConfig.java) object provided through the beacon-adapter-api. If you wish to implement a different retrieval or storage method for you configurations, simply implement the BeaconizerDao interface in place of the current implementation.

The beacons.json file contains an array of AdapterConfig objects which will be used to configure the different adapters at runtime. You can use the same adapter multiple times with different configurations if you wish. Each entry in the beacons.json file has the following fields:

- **name**: The name to register the beacon under. This will be the name that is used when accessing the beacon from the rest api
- **adapterClass**: The fully qualified name of the adapter class to use. The class must be in the current class path. Ex "com.dnastack.beacon.core.adapter.impl.SampleBeaconAdapterImpl"
- **configValues**: An array of additional configuration values consisting of name / value pairs of strings
    - Each Configvalue has the form of:
        ```json
            { 
                "name": "String",
                "value": "String"
            }
        ```
        
    - Each beacon adapter will define which configValues are required and is responsible for ensuring those configValues are present. Please refer to the documentation for each adapter to determine which values are needed
    
```json

    {
        "name": "beacon_id",
        "adapterClass": "com.dnastack.beacon.core.adapter.impl.SampleBeaconAdapterImpl",
        "configValues": [
            {
                "name":"sampleName",
                "value":"33-03-03"
            }
        ]
    }
```

## REST API
The full rest-api (return types) is currently being defined elsewhere and a link soo be provided

### GET /beacons

Returns a Json Array of all of the registered Beacons

```
http http://localhost:8080/beacons
```

```
curl -X GET http://localhost:8080/beacons
```

### GET /beaons/:id

Returns the Specific Beacon

```
http http://localhost:8080/beacons/beacon_id
```

```
curl -X GET http://localhost:8080/beacons/beacon_id
```

### GET /beacons/:id/query

Look up a specific variant in the specified beacon
**Required Query Params**

* referenceName: [String] chromosome or contig
* start: [Long] start position to begin lookup
* referenceBases: [String] reference bases to match
* alternateBases: [String] alternate bases to match
* assemblyId: [String] Genome build
* datasetIds: [Array[String]] List of dataset ids to lookup within the beacon. At least one must be supplied
* includeDatasetResponses: [Boolean] Whether to include dataset Responses or not

```
http 'http://localhost:8080/beacons/beacon_id/query?referenceName=1&start=10000&referenceBases=A&alternateBases=C&assemblyId=grch37&datasetIds=sample-1&datasetIds=sample-2&includeDatasetResponses=true'
```

```
curl -X GET 'http://localhost:8080/beacons/beacon_id/query?referenceName=1&start=10000&referenceBases=A&alternateBases=C&assemblyId=grch37&datasetIds=sample-1&datasetIds=sample-2&includeDatasetResponses=true'
```

### POST /beacons/:id/query

Look up a specific variant in the specified beacon
**Required Body Json**

```json
{
    "referenceName": "String",
    "start": "Long",
    "referenceBases":"String",
    "alternateBases":"String",
    "assemblyId":"String",
    "datasetids":[
        "String"
    ],
    "includeDatasetResponses": "Boolean"
}
```


```
http POST http://localhost:8080/beacons/:id/query referenceName=2 start:=2 referenceBases=A alternateBases=G assemblyId=grch36 datasetIds:='["dataset_id"]' includeDatasetResponses:=true
```

```
curl -H "Content-Type: application/json" -X POST -d `{ "referenceName": "String", "start": Long, "referenceBases":"String", "alternateBases":"String", "assemblyId":"String", "datasetids":[ "String" ], "includeDatasetResponses":Boolean }` http://localhost:8080/beacons/:id/query
```
