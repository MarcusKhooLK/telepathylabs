# Question 1
## Build and Run Jar
mvn package
java -jar question1-0.0.1.jar `<filepath>` <br>
Eg. java -jar question1-0.0.1.jar C:\Example1.txt

## Run
mvn spring-boot:run -Dspring-boot.run.arguments=`<filepath>` <br>
Eg. mvn spring-boot:run -Dspring-boot.run.arguments="C:\Example1.txt"

# Question 2
## Build and Run Jar
mvn package
java -jar question2-0.0.2.jar `<filepath>` `<feature1,feature2,...>` <br>
Eg. java -jar question2-0.0.2.jar C:\Example1.txt email,voice,admin

## Run
mvn spring-boot:run -Dspring-boot.run.arguments=`<filepath feature1,feature2,...>` <br>
Eg. mvn spring-boot:run -Dspring-boot.run.arguments="C:\Example1.txt email,voice,admin"

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
docker pull marcuskhoo/telepathy-q2:v0.0.1 <br>
docker run marcuskhoo/telepathy-q2:v0.0.1

Run with file and features as arguments <br>
docker run -v `<volumepath>` `<filepath>` `<feature1,feature2,...>`


