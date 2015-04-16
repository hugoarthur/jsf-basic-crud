# JSF-Basic-CRUD

This is just a simple JSF sample for check and study purposes. You can clone this code and use it for whatever you want, and you, also, can help improve it.

For this example I use the following tools:

  - MySQL;
  - JBoss AS 7.1.1 Final.

### Version
1.0.1

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

 - I'm having a problem when I try to get a deletedPerson on the delete method of the PersonMB class.
 - Here is the strack trace:

 ```java
SEVERE [javax.enterprise.resource.webcontainer.jsf.context] (http-localhost-127.0.0.1-8080-1) javax.ejb.EJBException: java.lang.IllegalArgumentException: attempt to create delete event with null entity: javax.faces.event.AbortProcessingException: javax.ejb.EJBException: java.lang.IllegalArgumentException: attempt to create delete event with null entity
	at javax.faces.event.MethodExpressionActionListener.processAction(MethodExpressionActionListener.java:182) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at javax.faces.event.ActionEvent.processListener(ActionEvent.java:88) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at javax.faces.component.UIComponentBase.broadcast(UIComponentBase.java:769) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at javax.faces.component.UICommand.broadcast(UICommand.java:300) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	at javax.faces.component.UIData.broadcast(UIData.java:1093) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
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
	at java.lang.Thread.run(Thread.java:745) [rt.jar:1.7.0_75]
Caused by: javax.ejb.EJBException: java.lang.IllegalArgumentException: attempt to create delete event with null entity
	at org.jboss.as.ejb3.tx.CMTTxInterceptor.handleExceptionInOurTx(CMTTxInterceptor.java:166) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.as.ejb3.tx.CMTTxInterceptor.invokeInOurTx(CMTTxInterceptor.java:230) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.as.ejb3.tx.CMTTxInterceptor.required(CMTTxInterceptor.java:304) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.as.ejb3.tx.CMTTxInterceptor.processInvocation(CMTTxInterceptor.java:190) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ejb3.component.interceptors.CurrentInvocationContextInterceptor.processInvocation(CurrentInvocationContextInterceptor.java:41) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ejb3.component.interceptors.LoggingInterceptor.processInvocation(LoggingInterceptor.java:59) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ee.component.NamespaceContextInterceptor.processInvocation(NamespaceContextInterceptor.java:50) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ejb3.component.interceptors.AdditionalSetupInterceptor.processInvocation(AdditionalSetupInterceptor.java:32) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ee.component.TCCLInterceptor.processInvocation(TCCLInterceptor.java:45) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.invocation.ChainedInterceptor.processInvocation(ChainedInterceptor.java:61) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ee.component.ViewService$View.invoke(ViewService.java:165) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.as.ee.component.ViewDescription$1.processInvocation(ViewDescription.java:173) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.invocation.ChainedInterceptor.processInvocation(ChainedInterceptor.java:61) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ee.component.ProxyInvocationHandler.invoke(ProxyInvocationHandler.java:72) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at br.com.service.PersonService$$$view1.delete(Unknown Source) [classes:]
	at br.com.jsf.mb.PersonMB.delete(PersonMB.java:81) [classes:]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) [rt.jar:1.7.0_75]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) [rt.jar:1.7.0_75]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) [rt.jar:1.7.0_75]
	at java.lang.reflect.Method.invoke(Method.java:606) [rt.jar:1.7.0_75]
	at org.apache.el.parser.AstValue.invoke(AstValue.java:262) [jbossweb-7.0.13.Final.jar:]
	at org.apache.el.MethodExpressionImpl.invoke(MethodExpressionImpl.java:278) [jbossweb-7.0.13.Final.jar:]
	at org.jboss.weld.util.el.ForwardingMethodExpression.invoke(ForwardingMethodExpression.java:39) [weld-core-1.1.5.AS71.Final.jar:2012-02-10 15:31]
	at org.jboss.weld.el.WeldMethodExpression.invoke(WeldMethodExpression.java:50) [weld-core-1.1.5.AS71.Final.jar:2012-02-10 15:31]
	at javax.faces.event.MethodExpressionActionListener.processAction(MethodExpressionActionListener.java:153) [jboss-jsf-api_2.1_spec-2.0.1.Final.jar:2.0.1.Final]
	... 27 more
Caused by: java.lang.IllegalArgumentException: attempt to create delete event with null entity
	at org.hibernate.event.spi.DeleteEvent.<init>(DeleteEvent.java:45) [hibernate-core-4.0.1.Final.jar:4.0.1.Final]
	at org.hibernate.internal.SessionImpl.delete(SessionImpl.java:801) [hibernate-core-4.0.1.Final.jar:4.0.1.Final]
	at org.hibernate.ejb.AbstractEntityManagerImpl.remove(AbstractEntityManagerImpl.java:883) [hibernate-entitymanager-4.0.1.Final.jar:4.0.1.Final]
	at org.jboss.as.jpa.container.AbstractEntityManager.remove(AbstractEntityManager.java:638) [jboss-as-jpa-7.1.1.Final.jar:7.1.1.Final]
	at br.com.jsf.dao.AbstractDao.delete(AbstractDao.java:33) [classes:]
	at br.com.service.PersonService.delete(PersonService.java:28) [classes:]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) [rt.jar:1.7.0_75]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) [rt.jar:1.7.0_75]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) [rt.jar:1.7.0_75]
	at java.lang.reflect.Method.invoke(Method.java:606) [rt.jar:1.7.0_75]
	at org.jboss.as.ee.component.ManagedReferenceMethodInterceptorFactory$ManagedReferenceMethodInterceptor.processInvocation(ManagedReferenceMethodInterceptorFactory.java:72) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.invocation.InterceptorContext$Invocation.proceed(InterceptorContext.java:374) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.weld.ejb.Jsr299BindingsInterceptor.doMethodInterception(Jsr299BindingsInterceptor.java:127) [jboss-as-weld-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.as.weld.ejb.Jsr299BindingsInterceptor.processInvocation(Jsr299BindingsInterceptor.java:135) [jboss-as-weld-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.as.ee.component.interceptors.UserInterceptorFactory$1.processInvocation(UserInterceptorFactory.java:36) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.invocation.WeavedInterceptor.processInvocation(WeavedInterceptor.java:53) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ee.component.interceptors.UserInterceptorFactory$1.processInvocation(UserInterceptorFactory.java:36) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.jpa.interceptor.SBInvocationInterceptor.processInvocation(SBInvocationInterceptor.java:47) [jboss-as-jpa-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.weld.ejb.EjbRequestScopeActivationInterceptor.processInvocation(EjbRequestScopeActivationInterceptor.java:82) [jboss-as-weld-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.invocation.InitialInterceptor.processInvocation(InitialInterceptor.java:21) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.invocation.ChainedInterceptor.processInvocation(ChainedInterceptor.java:61) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ee.component.interceptors.ComponentDispatcherInterceptor.processInvocation(ComponentDispatcherInterceptor.java:53) [jboss-as-ee-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ejb3.component.pool.PooledInstanceInterceptor.processInvocation(PooledInstanceInterceptor.java:51) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288) [jboss-invocation-1.1.1.Final.jar:1.1.1.Final]
	at org.jboss.as.ejb3.tx.CMTTxInterceptor.invokeInOurTx(CMTTxInterceptor.java:228) [jboss-as-ejb3-7.1.1.Final.jar:7.1.1.Final]
	... 57 more
 ```

####Contact
 * [Twitter]

Created on: [Dillinger]

[Twitter]:http://twitter.com/hugoarthur
[Dillinger]:http://dillinger.io/
