# LegalFAB-intern-test
 Serve json files. Only tested on Windows.
 
# Getting the code
```
git clone git@github.com:gerhean/LegalFABInternTest.git
```


# Requirements to run locally:

Have [nodeJs](https://nodejs.org/en/) installed.

Have [Ionic](https://ionicframework.com/docs/intro/cli) installed.

Have [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or above installed.

# Running locally:

Remember to run server before the client or client may not work properly.

## Running the server:

Navigate to the backend folder:
```
cd legalfabbacktest
```

### Setup
Run `gradlew clean build`, and ensure that it finishes with a BUILD SUCCESSFUL message.

### Run
```
gradlew bootRun
```

## Running the frontend:

Navigate to the frontend folder:

### Setup
```
npm install
```

### Run
```
ionic serve
```

# Libraries used:

[Ionic](https://ionicframework.com/docs/intro/cli) for frontend.

[Spring](https://spring.io/why-spring) for REST service.

[Gson](https://github.com/google/gson) to read json.
