<h5>UIDAI-explore is a study of various data storage designs for the UIDAI dataset.</h5>

<h4>Getting started</h4>

 - Prerequisties
     1. JDK 6 or more
     2. Maven 3 or above
     3. Postgres 9 or above

 - Download the maven project

 - Run JUnit tests for data loading and reading (JDBC and ORM)

    1. Test cases are organized into dataloader and datareader Java packages
 	  2. Sample test data is located in \resources\test-data
   	3. Schema for JDBC 1NF and 2NF designs are located in \resources
    4. Hibernate config is located in \src\test\resources

 -  Sharding classes are in the package uidai.explore.shard

    1. WriteClient
    2. ReadClient
    3. Both of them require a runtime configuration file -> \resources\shard.conf

