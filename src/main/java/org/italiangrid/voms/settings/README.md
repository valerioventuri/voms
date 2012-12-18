This is internal documentation, meant to be used by developers

# Configuration module for VOMS

This module loads the service configuration, and provides methods to other modules for accessing configuration
values.

The configuration module is based on the [Typesafe config library](https://github.com/typesafehub/config).

The format used for the configuration file is the HOCON format ( “Human-Optimized Config Object Notation”),
which might looks like properties but allow for using stanzas  and other amenities, see 
the [HOCON spec](https://github.com/typesafehub/config/blob/master/HOCON.md).

Default values goes into a reference.conf file in the classpath, now under src/main/resources. 
The reference.conf file is also used to validate the main configuration file.

An example configuration file is in src/main/resources. The configuration can either be on the classpath
or specified by the system property config.file.

The Settings interface provides getters to the configurations values. The SettingsImpl implememts the interface,
and is a Singleton implemented using an ENUM. See Joshua Bloks in Effective Java 'a single-element enum type is 
the best way to implement a singleton.'

While developing, just edit the application.conf file under src/main/resources and launch within eclipse. 
Or, to prevent accidentally pushing changes to it, copy it somehwere else and pass the location in the config.file
system property.

## Use the Settings object

Get the Settings singleton and call the getters

```java
Settings settings = SettingsImpl.INSTANCE;

String host = settings.getHost();
int port = settings.getPort();
```

## Add a setting

First add a default to the reference.conf resource

```bash
# The interval at which the service will refresh the trustore, loading 
# new certification authorities or revocations lists
truststore-refresh-interval = 60000
```

and the documentaion to the application.conf resource

```bash
# The interval at which the service will refresh the trustore, loading 
# new certification authorities or revocations lists. Defaults to 60000
# milliseconds
#truststore-refresh-interval = 60000
```

The add the getter to the Settings interface

```java
/**
 * Get the time after which the service will reload the contents of the 
 * truststore directory.
 */
long getTrustStoreRefreshInterval();
```

and the implementation to the SettingsImpl class

```java
/**
 * The interval for reloading trusted materials. 
 * 
 */
private long trustStoreRefreshInterval;

/**
 * Default constructor. Load the config and set settings field.
 * 
 */
private SettingsImpl() {

  ..
  trustStoreRefreshInterval = config.getLong("truststore-refresh-interval");
}

..

/**
 * @return the trustStoreRefreshInterval
 */
public long getTrustStoreRefreshInterval() {
  return trustStoreRefreshInterval;
}
```
