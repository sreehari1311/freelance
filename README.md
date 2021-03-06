# Freelance Rest API Assignment- Testing

Spring Rest API for freelance project submission and bidding
```
  This API is designed to perform following operations
  1) CRUD operations on project
  2) CRUD operations on bid 
  3) Anouncing winner of the bid is a daimon process which check the announcement in specific interval
     Developed using quarts scheduler

```
### Notes about the assignment
```
*The time the exercise took (after dev environment is set up)
3 hours
*Exercise Difficulty: Easy, Moderate, Difficult, Very Difficult
Moderate
*How did you feel about the exercise itself? (1 lowest, 10 highest—awesome way to assess coding ability)
7
*How do you feel about coding an exercise as a step in the interview process?  (1 lowest, 10 highest—awesome way to assess coding ability)
Great way to assess 
*What would you change in the exercise and/or process?
- Add few data structure and algorithamic use cases along with the application
```
### Database
```
Database is H2 (java in-memory) database
schema can be find in resource folder
To access database
http://localhost:8080/h2
```
### Libraries And Framework
```
 * Spring Framework
 * Quartz scheduler
 * H2 Database
 * Apache common utils
 
```
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
####  Get one  Project
```
End point: /api/v1/projects/{projectId}
Method: DELETE
{
  "response": {
    "id": 97,
    "title": "testing",
    "description": "test description",
    "budget": 2000.5,
    "submissionDate": "2018-04-03 23:54:10.473",
    "updateDate": "2018-04-03 23:54:10.473",
    "minimumBid": {
      "id": 65,
      "projectId": 97,
      "bidAmount": 1000,
      "userId": "bidUser",
      "submissionDate": "2018-04-04 00:02:03.411",
      "updateDate": "2018-04-04 00:02:03.411"
    },
    "userId": "projectUser"
},
"status": "SUCCESS"
}
```
####  Delete Project
```
End point: /api/v1/projects/{projectId}
Method: DELETE

```
####  Create Bid
```
End point: /api/v1/bids
Method: POST
Request Body
{
	"projectId":97,
   "bidAmount":1000,
   "userId":"testing"
}

 
 
####  Delete Bid
```
End point: /api/v1/bids/{bidId}
Method: DELETE
 
```
