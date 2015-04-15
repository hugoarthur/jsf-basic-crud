# JSF-Basic-CRUD

This is just a simple JSF sample for check and study purposes. You can clone this code and use it for whatever you want, and you, also, can help improve it.

For this example I use the following tools:

  - MySQL;
  - JBoss AS 7.1.1 Final.

### Version
1.0.0

### Java Tools

* JavaEE 6
* CDI 1.2
* JSF 2.0
* Hibernate 4.3.8
* JPA 2.0

### Configurantion on JBoss 7.1.1

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

 - I'm having a problem when I try to persist a new Person. For some reason JBOSS is not creating the Transaction with the EntityManager.
 - Here is the strack trace:

 ```java
WARNING [javax.enterprise.resource.webcontainer.jsf.lifecycle] (http-localhost-127.0.0.1-8080-2) #{personMB.create}: javax.persistence.TransactionRequiredException: JBAS011469: Transaction is required to perform this operation (either use a transaction or extended persistence context): javax.faces.FacesException: #{personMB.create}: javax.persistence.TransactionRequiredException: JBAS011469: Transaction is required to perform this operation (either use a transaction or extended persistence context)
	at com.sun.faces.application.ActionListenerImpl.processAction(ActionListenerImpl.java:118) [jsf-impl-2.1.7-jbossorg-2.jar:]
	at javax.faces.component.UICommand.broadcast(UICommand.java:315) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at javax.faces.component.UIViewRoot.broadcastEvents(UIViewRoot.java:794) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at javax.faces.component.UIViewRoot.processApplication(UIViewRoot.java:1259) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at com.sun.faces.lifecycle.InvokeApplicationPhase.execute(InvokeApplicationPhase.java:81) [jsf-impl-2.1.7-jbossorg-2.jar:]
	at com.sun.faces.lifecycle.Phase.doPhase(Phase.java:101) [jsf-impl-2.1.7-jbossorg-2.jar:]
	at com.sun.faces.lifecycle.LifecycleImpl.execute(LifecycleImpl.java:118) [jsf-impl-2.1.7-jbossorg-2.jar:]
	at javax.faces.webapp.FacesServlet.service(FacesServlet.java:593) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:329) [jbossweb-7.0.13.Final.jar:]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:248) [jbossweb-7.0.13.Final.jar:]
	at org.jboss.weld.servlet.ConversationPropagationFilter.doFilter(ConversationPropagationFilter.java:62) [weld-core-1.1.5.AS71.Final.jar:2012-02-10 15:31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:280) [jbossweb-7.0.13.Final.jar:]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:248) [jbossweb-7.0.13.Final.jar:]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:275) [jbossweb-7.0.13.Final.jar:]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:161) [jbossweb-7.0.13.Final.jar:]
	at org.jboss.as.jpa.interceptor.WebNonTxEmCloserValve.invoke(WebNonTxEmCloserValve.java:50) [jboss-as-jpa-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.as.web.security.SecurityContextAssociationValve.invoke(SecurityContextAssociationValve.java:153) [jboss-as-web-7.1.1.Final.jar:7.1.1.Final]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:155) [jbossweb-7.0.13.Final.jar:]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102) [jbossweb-7.0.13.Final.jar:]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109) [jbossweb-7.0.13.Final.jar:]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:368) [jbossweb-7.0.13.Final.jar:]
	at org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:877) [jbossweb-7.0.13.Final.jar:]
	at org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:671) [jbossweb-7.0.13.Final.jar:]
	at org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:930) [jbossweb-7.0.13.Final.jar:]
	at java.lang.Thread.run(Thread.java:722) [rt.jar:1.7.0_10]
Caused by: javax.faces.el.EvaluationException: javax.persistence.TransactionRequiredException: JBAS011469: Transaction is required to perform this operation (either use a transaction or extended persistence context)
	at javax.faces.component.MethodBindingMethodExpressionAdapter.invoke(MethodBindingMethodExpressionAdapter.java:102) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at com.sun.faces.application.ActionListenerImpl.processAction(ActionListenerImpl.java:102) [jsf-impl-2.1.7-jbossorg-2.jar:]
	... 24 more
Caused by: javax.persistence.TransactionRequiredException: JBAS011469: Transaction is required to perform this operation (either use a transaction or extended persistence context)
	at org.jboss.as.jpa.container.AbstractEntityManager.transactionIsRequired(AbstractEntityManager.java:692) [jboss-as-jpa-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.as.jpa.container.AbstractEntityManager.persist(AbstractEntityManager.java:562) [jboss-as-jpa-7.1.1.Final.jar:7.1.1.Final]
	at br.com.jsf.dao.AbstractDao.create(AbstractDao.java:23) [classes:]
	at br.com.jsf.mb.PersonMB.create(PersonMB.java:71) [classes:]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) [rt.jar:1.7.0_10]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) [rt.jar:1.7.0_10]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) [rt.jar:1.7.0_10]
	at java.lang.reflect.Method.invoke(Method.java:601) [rt.jar:1.7.0_10]
	at org.apache.el.parser.AstValue.invoke(AstValue.java:262) [jbossweb-7.0.13.Final.jar:]
	at org.apache.el.MethodExpressionImpl.invoke(MethodExpressionImpl.java:278) [jbossweb-7.0.13.Final.jar:]
	at org.jboss.weld.util.el.ForwardingMethodExpression.invoke(ForwardingMethodExpression.java:39) [weld-core-1.1.5.AS71.Final.jar:2012-02-10 15:31]
	at org.jboss.weld.el.WeldMethodExpression.invoke(WeldMethodExpression.java:50) [weld-core-1.1.5.AS71.Final.jar:2012-02-10 15:31]
	at com.sun.faces.facelets.el.TagMethodExpression.invoke(TagMethodExpression.java:105) [jsf-impl-2.1.7-jbossorg-2.jar:]
	at javax.faces.component.MethodBindingMethodExpressionAdapter.invoke(MethodBindingMethodExpressionAdapter.java:88) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
 ```

####Contact
 * [Twitter]

Created on: [Dillinger]

[Twitter]:http://twitter.com/hugoarthur
[Dillinger]:http://dillinger.io/
