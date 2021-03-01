# Challenge

Proyecto back challenge Infosel

### Levantar el proyecto con mvn

Aplicar el siguiente comando

```
mvn spring-boot:run
```

### Compilación e imágen docker

Compilar la aplicación con el siguiente comando

```
mvn clean package docker:build -DskipTests
```
Tambien generará una imagen de docker de nombre y version

```
challenge:1.0.0
```

### Contenedor docker

Para generar el contenedor ir a la carpeta docker del proyecto


```
cd src/main/docker/challenge
```

y ejecutar el siguiente comando de docker compose

```
docker-compose up -d
```
