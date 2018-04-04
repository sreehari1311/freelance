# freelance 
Spring Rest API for freelance project submission and bidding

#Create Project
To create a project
End point: /api/v1/projects
Method: POST
Request Body: {
	"title":"test",
  "description": "test description",
  "budget":2000.50,
  "lastBidSubmissionDate":"04/04/2018 10:15"
   "userId":"testUser"
}
