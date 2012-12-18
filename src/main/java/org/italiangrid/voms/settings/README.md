# This is internal documentaion

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


Add the getter to the Settings interface

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

