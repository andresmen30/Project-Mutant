# Project-Mutant
Challenge project mutant

La implementación del proyecto se llevo acabo con Java 8, junto con el framework Sprint boot, también se realizo se utiliza la estructura de Base de datos H2, todo bajo la arquitectura hexagonal.

## Tecnologias

Java 8
Spring Boot
JUnit
Mockito
Tomcat
Base de datos H2


## Consumo Servicio /project-mutant

### url: http://64.227.3.187:8081/project-mutant/

## Endpoints

### POST /mutant

Este método verifica y guarda la secuencia de ADN de cada sujeto
El request esta compuesto solamente por el campo dna con la estructura de un Array, conteniendo así el valor de la secuencia de ADN.

El response contiene los campos code y message el cual retornan un codigo 200 si la secuencia es de un mutante de lo contrario retorna un 403 si es un humano.

En dado caso que la estructura del ADN este mal retornara un codigo 400.


#### Request Exitoso ADN humano
```
{
   "dna":[
      "ATGCGA",
      "CAGCGC",
      "CTATGT",
      "AGGTGG",
      "CAACCG",
      "TCACTG"
   ]
}
```

#### Response Exitoso ADN humano
```
{
   "code":403,
   "message":"Los humanos no son bienvenidos acá, sin embargo te tendremos en cuenta"
}
```


#### Request Exitoso ADN mutante
```
{
   "dna":[
      "ATGCGA",
      "CAGTGC",
      "TTATGT",
      "AGAAGG",
      "CCCCTA",
      "TCACTG"
   ]
}
```

#### Response Exitoso ADN mutante
```
{
    "code": 200,
    "message": "Eres uno de los nuestros, bienvenido al equipo mutante"
}
```

#### Request Error Formato invalido
```
{
"dna":["PTGCGA", "CAGTGC", "TTATGT","AGAAGG","CCCCTA","TCAVTG"]
}
```

#### Response Error Formato invalido
```
{
    "code": 500,
    "message": "com.mercadolibre.projectomutante.exceptions.FormatInvalidDNAException: Oops la secuencia del ADN del sujeto, no es acorde a la base nitrogenada establecida"
}
```

#### Request Error Tamaño de Array invalido
```
{
   "dna":["ATGCGA", "CAGTGC", "TTATGT","AGAAGG","A","TTG"]
}
```

#### Response Error Tamaño de Array invalido
```
{
    "code": 500,
    "message": "com.mercadolibre.projectomutante.exceptions.SizeInvalidDNAException: Oops el tamaño de la secuencia del ADN no es correcto"
}
```

### GET /stats

Este método retorna las estadisticas de las pruebas, de cuantos sujetos son mutantes y humanos, también se calcula el ratio, que es la relación entre mutante y humano.


#### Response Exitoso 
```
{
    "code": 200,
    "message": "Success",
    "resultStats": {
        "countMutantDna": 0,
        "countHumantDna": 0,
        "ratio": 0.0
    }
}
```

## Compilación del proyecto

##### Clonar del repositorio
```
git clone https://github.com/andresmen30/Project-Mutant.git
```


##### Compilar

Ubíquese en la ruta /ProjectoMutante
```
mvn clean install
```

##### Desplegar

Ubíquese en la ruta /ProjectoMutante/launcher/tarjet y ejecute:
```
java -jar launcher-0.0.1.jar
```

###### Documentación [https://documenter.getpostman.com/view/8454301/UV5aeF7C](https://documenter.getpostman.com/view/8454301/UV5aeF7C).












