# Car Inventory

A simple car sales inventory manager. 

## Build
You will need a `config.properties` based on `config.properties.template` in `src/main/resources`

To build in the CLI:
```sh
mvn -B package --file pom.xml
````

To run the db locally:
```sh
docker-compose --env-file ./src/main/resources/config.properties up
```

## Authors

* **Nick Glazer**
* **Rechee Jozil**
