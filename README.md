## Client-server sample application

________

Repository for QA course students for explanation client-server interconnection.

### Server side

Spring application, running on default port 8090. Contains H2 (in memory) database with several products, available on
`/api/v1/shop/items`

### Client side
Android app using retrofit as a rest client.

####IMPORTANT: Should set your own BASE_URL in build.gradle app module level

```groovy
        buildConfigField "String", "BASE_URL", '"http://192.168.0.100:8090"' // PASTE YOUR OWN IP:PORT PAIR HERE
```