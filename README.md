# Question 1
## Build and Run Jar
```console
mvn package
```
```console
java -jar question1-0.0.1.jar <filepath>
```
### Example
```console
java -jar question1-0.0.1.jar C:\Example1.txt
```

## Run
```console
mvn spring-boot:run -Dspring-boot.run.arguments=<filepath>
```
### Example
```console
mvn spring-boot:run -Dspring-boot.run.arguments="C:\Example1.txt"
```

# Question 2
## Build and Run Jar
```console
mvn package
```
```console
java -jar question2-0.0.2.jar <filepath> <feature1,feature2,...>
```
### Example
```console
java -jar question2-0.0.2.jar C:\Example1.txt email,voice,admin
```

## Run
```console
mvn spring-boot:run -Dspring-boot.run.arguments=<filepath feature1,feature2,...>
```
#### Example
```console
mvn spring-boot:run -Dspring-boot.run.arguments="C:\Example1.txt email,voice,admin"
```

## Bonus for question 2
Run question 2 without arguments for the program to wait for requests

### REST API
https://app.swaggerhub.com/apis/MarcusKhooLK/findBestPlan/0.1

### Sample Input
```json
{
    "allPlans" : [
        {
            "name" : "PLAN1",
            "price" : 100,
            "features" : [
                "voice", "email"
            ]
        },
        {
            "name" : "PLAN2",
            "price" : 150,
            "features" : [
                "email", "database", "admin"
            ]
        },
        {
            "name" : "PLAN3",
            "price" : 125,
            "features" : [
                "voice", "admin"
            ]
        },
        {
            "name" : "PLAN4",
            "price" : 135,
            "features" : [
                "database", "admin"
            ]
        }
    ],
    "selectedFeatures" : ["email","voice","admin"]
}
```

### Sample Output
```json
{
  "price" : 225.0,
  "plans" : [
    "PLAN1", "PLAN3"
  ]
}
```

### Docker
#### Pull
```console
docker pull marcuskhoo/telepathy-q2:v0.0.1
```
#### Run
```console
docker run marcuskhoo/telepathy-q2:v0.0.1
```
#### Run with file and features as arguments
```console
docker run -v <volumepath> marcuskhoo/telepathy-q2:v0.0.1 <filepath> <feature1,feature2,...>
```
#### Example
```console
docker run -v C:/Users/USER:/tmp marcuskhoo/telepathy-q2:v0.0.1 /tmp/Example1.txt email,voice,admin
```

### Test REST API
For testing, I'm using JUnit, MockMvc and Json as input and output data. As seen in /question2/src/test 
```console
mvn test
```