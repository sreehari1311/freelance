# Freelance Rest API

Spring Rest API for freelance project submission and bidding
### Prerequisites

```
java 1.8
apache maven
```

### Installing

```
mvn package
```


## Running the api

To run this jar file

```
java -jar target/freelance-0.0.1-SNAPSHOT.jar 
```

## API Documentation
####  Create Project
```
End point: /api/v1/projects
Method: POST
Request Body
{
  "title":"test",
  "description": "test description",
  "budget":2000.50,
  "lastBidSubmissionDate":"04/04/2018 10:15"
  "userId":"testUser"
}
```
####  Update Project
```
End point: /api/v1/projects/{projectId}
Method: PUT
Request Body
{
  "title":"test",
  "description": "test description",
  "budget":2000.50,
  "lastBidSubmissionDate":"04/04/2018 10:15"
  "userId":"testUser"
}
```
####  Delete Project
```
End point: /api/v1/projects/{projectId}
Method: DELETE

```


