# JSF-Basic-CRUD

This is just a simple JSF sample for check and study purposes. You can clone this code and use it for whatever you want, and you, also, can help improve it.

For this example I use the following tools:

  - MySQL;
  - JBoss AS 7.1.1 Final.

### Version
1.0.2

### Java Tools

* JavaEE 6
* CDI 1.2
* JSF 2.0
* Hibernate 4.3.8
* JPA 2.0

### Configuration on JBoss 7.1.1

 1. Create a folder named **mysql** inside *JBOSS_HOME/module/com*.
 2. Inside the **mysql** folder create another folder named **main**.
 3. Inside **main** put your driver connector .jar and create a file named module.xml with the content below.

**Module.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.0" name="com.mysql">
    <resources>
        <resource-root path="mysql-connector-java-5.1.34-bin.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
    </dependencies>
</module>
```

Now we are gonna to configure the **standalone.xml** to find this driver connector.

1. Open the file *JBOSS_HOME/standalone/configuration/standalone.xml*
2. Search for the *datasources* tag and add a new datasource inside of it.

**Datasource**
```xml
<datasource jta="true" jndi-name="java:/MyDS" pool-name="MyDS" enabled="true" use-java-context="true" use-ccm="true">
    <connection-url>jdbc:mysql://localhost:3306/mydb</connection-url>
    <driver>com.mysql</driver>
    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
    <pool>
        <min-pool-size>5</min-pool-size>
        <max-pool-size>30</max-pool-size>
        <prefill>true</prefill>
        <use-strict-min>false</use-strict-min>
        <flush-strategy>FailingConnectionOnly</flush-strategy>
    </pool>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
    <statement>
        <prepared-statement-cache-size>32</prepared-statement-cache-size>
    </statement>
</datasource>
```

Now inside the same file, *standalone.xml*, search for the *drivers* tag and add a new driver inside of it.

**Driver**

```xml
<driver name="com.mysql" module="com.mysql">
    <driver-class>com.mysql.jdbc.Driver</driver-class>
</driver>
```

That's pretty much it... Just run your application and good luck. :)

### Issues

 - All issues were fixed for now.

#### Contact
 * [Twitter]

Created on: [Dillinger]

[Twitter]:http://twitter.com/hugoarthur
[Dillinger]:http://dillinger.io/
