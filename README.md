## API Documentation

# Pet Me Now

## 4th October 2022

## APPLICATION HEALTH CHECK API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/actuator/health]()

**METHOD** : GET \
**REQUEST** :EMPTY \
**RESPONSE** :

```JSON
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "MySQL",
        "validationQuery": "isValid()"
      }
    },
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 8577331200,
        "free": 5939519488,
        "threshold": 10485760,
        "exists": true
      }
    },
    "ping": {
      "status": "UP"
    }
  }
}
```

### ##########################################################

### #######

### ######################### USER APIs

### ###############################

### ##########################################################

### #######

## USER SIGN-UP API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/user/sign-up]() \
**METHOD** : POST \
**REQUEST** :

```JSON
{
  "userName": "testUser",
  "email": "test@gmail.com",
  "password": "password",
  "address": "123, Lonsdale"
}
```
#### RESPONSE : (SUCCESS)

```JSON
{
  "status": {
    "statusCode": 2000,
    "message": "User created successfully",
    "localizedMessage": "User created successfully"
  },
  "responseData": {
    "id": 1,
    "firstName": "Abhijeet",
    "lastName": "S",
    "userName": "abhijeet1",
    "password": "password",
    "email": "abhijeet1@student.unimelb.edu.au",
    "phoneNumber": 474517087,
    "address": null,
    "image": null,
    "dateOfBirth": null,
    "createdTimestamp": "2022-10-04T03:39:52.501+00:00",
    "updatedTimestamp": "2022-10-04T03:39:52.501+00:00",
    "ownerPetList": null
  }
}
```
#### RESPONSE: (USER EMAIL ALREADY EXISTS - ERROR)

```JSON
{
  "status": {
    "statusCode": -2000,
    "message": "Oops! Looks like the user already exists",
    "localizedMessage": "Oops! Looks like the user already exists"
  },
  "responseData": {}
}
```
#### RESPONSE: (EMAIL ADDRESS NOT WELL-FORMED - ERROR)

```JSON
{
  "status": {
    "statusCode": -1001,
    "message": "email",
    "localizedMessage": "must be a well-formed email address"
  },
  "responseData": {}
}
```
## USER SIGN-IN API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/user/sign-in]() \

**METHOD** : POST \
**REQUEST** : 

```
{
"userName" : "abhijeet1",
"password" : "password"
}
```
#### RESPONSE :

```
{
"status": {
"statusCode": 2002 ,
"message": "User signed in successfully",
"localizedMessage": "User signed in successfully"
},
"responseData": {
"id": 1 ,
"firstName": "Abhijeet",
"lastName": "S",
"userName": "abhijeet1",
"password": "password",
"email": "abhijeet1@student.unimelb.edu.au",
"phoneNumber": 474517087 ,
"address": null,
"image": null,
"dateOfBirth": null,
"createdTimestamp": 1664894393000 ,
"updatedTimestamp": 1664894393000 ,
"ownerPetList": []
}
}
```
## FETCH USER DETAILS API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/user/details]() \
**METHOD** : GET \
**PATH PARAMETERS** : 1 // {{ userId }} \
**EXAMPLE:**
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/user/details/1]() \
**RESPONSE** :

```
{
"status": {
"statusCode": 2001 ,
"message": "User details fetched successfully",
"localizedMessage": "User details fetched successfully"
},
"responseData": {
"id": 1 ,
"firstName": "abhijeet",
"lastName": "S",
"userName": "abhijeet1",
"password": "password",
"email": "abhijeet1@student.unimelb.edu.au",
"phoneNumber": 474517087 ,
"address": "39 Lonsdale Street",
"image": null,
"dateOfBirth": 835797600000 ,
"createdTimestamp": 1664854793000 ,
"updatedTimestamp": 1664818245000 ,
"ownerPetList": [
{
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dog with great loyalty towards
owner",
"createdTimestamp": 1665638746000 ,
"updatedTimestamp": 1665638746000
}
]
}
}
```

## UPDATE USER DETAILS API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/user/update-details]() \
**METHOD** : PUT \
**REQUEST** :

```
{
"id" : 1 ,
"firstName" : "abhijeet",
"lastName" : "S",
"userName" : "abhijeet1",
"email" : "abhijeet1@student.unimelb.edu.au",
"phoneNumber" : 474517087 ,
"address" : "39 Lonsdale Street",
"dateOfBirth" : 835884000000
}
```
#### RESPONSE :

```
{
"status": {
"statusCode": 2003 ,
"message": "User details updated successfully",
"localizedMessage": "User details updated successfully"
},
"responseData": {
"id": 1 ,
"firstName": "abhijeet",
"lastName": "S",
"userName": "abhijeet1",
"password": "password",
"email": "abhijeet1@student.unimelb.edu.au",
"phoneNumber": 474517087 ,
"address": "39 Lonsdale Street",
"image": null,
"dateOfBirth": 835884000000 ,
"createdTimestamp": 1664894393000 ,
"updatedTimestamp": 1664857844783 ,
"ownerPetList": []
}
}
```
## UPLOAD USER IMAGE API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/user/upload-image]() \
**METHOD** : POST \
**PATH PARAMETERS** : 1 // {{ userId }} \
**FORM-DATA:** file : {{ file-to-be-uploaded }} \
**EXAMPLE:**
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/user/upload-image/1]() \
**CURL REQUEST:**

```
curl --location --request POST
'http://localhost:8080/pet-me-now/user/upload-image/ 1 ' \
--form 'file=@"/Users/abhijeet/Desktop/Screen Shot2022-10-14 at 12.20.
am.png"'
```
#### RESPONSE :

```
{
"status": {
"statusCode": 2010 ,
"message": "User image uploaded successfully",
"localizedMessage": "User image uploaded successfully"
},
"responseData": {
"id": 1 ,
"firstName": "abhijeet",
"lastName": "S",
"userName": "abhijeet1",
"password": "password",
"email": "abhijeet1@student.unimelb.edu.au",
"phoneNumber": 474517087 ,
"address": "39 Lonsdale Street",
"image":
"https://petmenow-images.s3.us-west-2.amazonaws.com/ScreenShot2022-10-14at2.20.49am.png",
"dateOfBirth": 835797600000 ,
"createdTimestamp": 1664854793000 ,
"updatedTimestamp": 1664818245000 ,
"ownerPetList": [
{
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dog with great loyalty towards
owner",
"createdTimestamp": 1665639681000 ,
"updatedTimestamp": 1665639681000
}
]
}
}
```
### ##########################################################

### #######

### ######################### PET APIs

### ###############################

### ##########################################################

### #######

## FETCH PET TYPES API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/types]() \
**METHOD** : GET \
**RESPONSE** :

```
{
"status": {
"statusCode": 2008 ,
"message": "Pet types fetched successfully",
"localizedMessage": "Pet types fetched successfully"
},
"responseData": [
{
"id": 1 ,
"petType": "Dog"
},
{
"id": 2 ,
"petType": "Cat"
},
{
"id": 3 ,
"petType": "Rabbit"
},
{
"id": 4 ,
"petType": "Bird"
}
]
}
```
## SEARCH PET BREEDS API

#### URL :
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/search-breed]() \
**METHOD** : GET \
**PATH VARIABLE:**  1 // {{pet type id }}
**REQUEST PARAM:** ?search=la // {{ search string}} \
**EXAMPLE:**
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/search-breed/1?search=la]() \

**RESPONSE:**

```
{
"status": {
"statusCode": 2009 ,
"message": "Pet breeds searched successfully",
"localizedMessage": "Pet breeds searched successfully"
},
"responseData": [
{
"id": 1 ,
"petBreed": "Retrievers (Labrador)"
},
{
"id": 27 ,
"petBreed": "Shetland Sheepdogs"
},
{
"id": 35 ,
"petBreed": "Vizslas"
},
{
"id": 41 ,
"petBreed": "Newfoundlands"
},
{
"id": 44 ,
"petBreed": "West Highland White Terriers"
}
]
}
```
## REGISTER PET API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/register]() \
**METHOD** : POST \
**REQUEST** :

```
{
"name" : "Casper",
"petType" : "Dog",
"petBreed" : "Lhasa Apsos",
"age" : 2 ,
"vaccinated" : true,
"ownerId" : 1 ,
"description" : "Fun-loving dog with great loyalty towards owner"
}
```
#### RESPONSE:

```
{
"status": {
"statusCode": 2004 ,
"message": "Pet registered successfully",
"localizedMessage": "Pet registered successfully"
},
"responseData": {
"id": 2 ,
"name": "Unknown",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dog with great loyalty towards owner",
"createdTimestamp": 1665639688662 ,
"updatedTimestamp": 1665639688662
}
}
```
## FETCH PET DETAILS API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/fetch]() \
**METHOD** : GET \
**PATH PARAMETERS** : 1 // {{ petId }} \
#### EXAMPLE:

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/fetch/1]() \
**RESPONSE** :

```
{
"status": {
"statusCode": 2007 ,
"message": "Pet information fetched successfully",
"localizedMessage": "Pet information fetched successfully"
},
"responseData": {
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dog with great loyalty towards owner",
"createdTimestamp": 1665639681000 ,
"updatedTimestamp": 1665639681000
}
}
```
## UPDATE PET DETAILS API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/update]() \
**METHOD** : POST \
**REQUEST** :

```
{
"id" : 1 ,
"name" : "Casper",
"petType" : "Dog",
"petBreed" : "Lhasa Apsos",
"age" : 2 ,
"vaccinated" : true,
"description" : "Fun-loving dog with great loyalty towards owner and
friends"
}
```
#### RESPONSE:

```
{
"status": {
"statusCode": 2006 ,
"message": "Pet information updated successfully",
"localizedMessage": "Pet information updated successfully"
},
"responseData": {
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dog with great loyalty towards owner and
friends",
"createdTimestamp": 1665638746000 ,
"updatedTimestamp": 1665639069650
}
}
```
## DELETE PET API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/delete]() \
**METHOD** : DELETE \
**PATH PARAMETERS** : 1 // {{ petId }} \
**EXAMPLE:**
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/delete/1]() \
**RESPONSE** :

```
{
"status": {
"statusCode": 2005 ,
"message": "Pet information deleted successfully",
"localizedMessage": "Pet information deleted successfully"
},
"responseData": {}
}
```
## UPLOAD PET IMAGE API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/upload-image]() \
**METHOD** : POST \
**PATH PARAMETERS** : /1 // {{ petId }} \
**FORM-DATA:** file : {{ file-to-be-uploaded }} \
**EXAMPLE:**
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/pet/upload-image/1]() \
**CURL REQUEST:**

```
curl --location --request POST
'http://localhost: 8080 /pet-me-now/pet/upload-image/ 1 ' \
--form
'file=@"/Users/abhijeet/Downloads/fef4d60a-1884-45ed-a864-595a26b35714.JPG"'
```
#### RESPONSE:

```
{
"status": {
"statusCode": 2011 ,
"message": "Pet image uploaded successfully",
"localizedMessage": "Pet image uploaded successfully"
},
"responseData": {
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image":
"https://petmenow-images.s3.us-west-2.amazonaws.com/fef4d60a-1884-45ed-a
-595a26b35714.JPG",
"ownerId": 1 ,
"description": "Fun-loving dog with great loyalty towards owner",
"createdTimestamp": 1665639681000 ,
"updatedTimestamp": 1665639681000
}
}
```
### ##########################################################

### #######

### ######################### ORDER APIs

### ##############################

### ##########################################################

### #######

## PLACE ORDER REQUEST API

#### URL :
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/order/request]() \
**METHOD** : POST \
**CASE 1: ADOPTION REQUEST:**

```
{
"userId" : 1 ,
"petId" : 1 ,
"type" : "ADOPTION",
"title" : "Need someone to take care of my pet",
"startDate" : 1666098000000 ,
"durationNumber" : 3 ,
"durationType" : "DAYS"
}
```

#### RESPONSE :

```
{
"status": {
"statusCode": 2012 ,
"message": "Request placed successfully",
"localizedMessage": "Request placed successfully"
},
"responseData": {
"id": 1 ,
"title": "Need someone to take care of my pet",
"type": "ADOPTION",
"startDate": 1666098000000 ,
"endDate": 1666357200000 ,
"durationNumber": 3 ,
"durationType": "DAYS",
"status": "REQUESTED",
"petId": 1 ,
"ownerId": 1 ,
"acceptedUserId": null,
"allowedPets": null,
"createdTimestamp": 1666006005091 ,
"updatedTimestamp": 1666006005091
}
}
```
#### CASE 2: FOSTER

#### REQUEST:

```
{
"userId" : 1 ,
"type" : "FOSTER",
"title" : "I can take care of your pet",
"startDate" : 1666098000000 ,
"durationNumber" : 3 ,
"durationType" : "WEEKS",
"allowedPets" : [
"Dog", "Cat"
]
}
```
#### RESPONSE:

```
{
"status": {
"statusCode": 2012 ,
"message": "Request placed successfully",
"localizedMessage": "Request placed successfully"
},
"responseData": {
"id": 2 ,
"title": "I can take care of your pet",
"type": "FOSTER",
"startDate": 1666098000000 ,
"endDate": 1667912400000 ,
"durationNumber": 3 ,
"durationType": "WEEKS",
"status": "REQUESTED",
"petId": null,
"ownerId": 1 ,
"acceptedUserId": null,
"allowedPets": "Dog,Cat",
"createdTimestamp": 1666006131780 ,
"updatedTimestamp": 1666006131780
}
}
```
## ACCEPT ORDER REQUEST API

#### URL :
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/order/accept]() \
**METHOD** : POST \

**CASE 1: ADOPTION REQUEST:**

```
{
"userId" : 2 ,
"orderId" : 1
}
```
#### RESPONSE :

```
{
"status": {
"statusCode": 2013 ,
"message": "Order accepted successfully",
"localizedMessage": "Order accepted successfully"
},
"responseData": {
"id": 1 ,
"title": "Need someone to take care of my pet",
"type": "ADOPTION",
"startDate": 1666098000000 ,
"endDate": 1666357200000 ,
"durationNumber": 3 ,
"durationType": "DAYS",
"status": "ACCEPTED",
"petId": 1 ,
"ownerId": 1 ,
"acceptedUserId": 2 ,
"allowedPets": null,
"createdTimestamp": 1666006005000 ,
"updatedTimestamp": 1666006906193
}
}
```
#### CASE 2: FOSTER

#### REQUEST:

```
{
"userId" : 2 ,
"orderId" : 2 ,
"petId" : 2
}
```
#### RESPONSE:

```
{
"status": {
"statusCode": 2013 ,
"message": "Order accepted successfully",
"localizedMessage": "Order accepted successfully"
},
"responseData": {
"id": 2 ,
"title": "I can take care of your pet",
"type": "FOSTER",
"startDate": 1666098000000 ,
"endDate": 1667912400000 ,
"durationNumber": 3 ,
"durationType": "WEEKS",
"status": "REQUESTED",
"petId": 2 ,
"ownerId": 1 ,
"acceptedUserId": 2 ,
"allowedPets": "Dog,Cat",
"createdTimestamp": 1666006131780 ,
"updatedTimestamp": 1666006131780
}
}
```
## DELETE ORDER API

#### URL :
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/order/delete]() \
**METHOD** : DELETE \
**PATH PARAMETERS** : /1 // {{ order Id }} \
**EXAMPLE:**
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/order/delete/1]() \

**RESPONSE:**

```
{
"status": {
"statusCode": 2005 ,
"message": "Order request deleted successfully",
"localizedMessage": "Order request deleted successfully"
},
"responseData": {}
}
```

### ##########################################################

### #######

### ######################### HISTORY APIs

### #############################

### ##########################################################

### #######

## FETCH USER ORDER HISTORY API

#### URL :

[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/history/user]() \
**METHOD** : GET \
**PATH PARAMETERS** : /1 // {{ userId }} \
**EXAMPLE:**
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80/pet-me-now/history/user/1]() \
**RESPONSE** :

```
{
"status": {
"statusCode": 2015 ,
"message": "Order history for user fetched successfully",
"localizedMessage": "Order history for user fetched successfully"
},
"responseData": {
"userDetails": {
"id": 1 ,
"firstName": "abhijeet",
"lastName": "S",
"userName": "abhijeet1",
"password": "password",
"email": "abhijeet1@student.unimelb.edu.au",
"phoneNumber": 474517087 ,
"address": "39 Lonsdale Street",
"image":
"https://petmenow-images.s3.us-west-2.amazonaws.com/ScreenShot2022-10-14at
2.20.49am.png",
"dateOfBirth": 835797600000 ,
"createdTimestamp": 1664854793000 ,
"updatedTimestamp": 1664818245000 ,
"ownerPetList": [
{
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dogwith great loyalty
towards owner",
"createdTimestamp": 1665639681000 ,
"updatedTimestamp": 1665639681000
}
]
},
"orderDetails": [
{
"id": 1 ,
"title": "Need someone to take care of my pet",
"type": "ADOPTION",
"startDate": 1666098000000 ,
"endDate": 1666357200000 ,
"durationNumber": 3 ,
"durationType": "DAYS",
"status": "REQUESTED",
"petId": 1 ,
"ownerId": 1 ,
"acceptedUserId": null,
"allowedPets": null,
"createdTimestamp": 1666006005000 ,
"updatedTimestamp": 1666006005000 ,
"petDetails": {
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dogwith great loyalty
towards owner",
"createdTimestamp": 1665639681000 ,
"updatedTimestamp": 1665639681000
},
"acceptedUserDetails": null
},
{
"id": 2 ,
"title": "I can take care of your pet",
"type": "FOSTER",
"startDate": 1666098000000 ,
"endDate": 1667912400000 ,
"durationNumber": 3 ,
"durationType": "WEEKS",
"status": "REQUESTED",
"petId": null,
"ownerId": 1 ,
"acceptedUserId": null,
"allowedPets": "Dog,Cat",
"createdTimestamp": 1666006132000 ,
"updatedTimestamp": 1666006132000 ,
"petDetails": null,
"acceptedUserDetails": null
}
]
}
}
```
## FETCH ALL ACTIVE ORDERS API

#### URL :
[http://petmenowspringboot-env.eba-gbytpf9b.us-west-2.elasticbeanstalk.com:80 /pet-me-now/history/active]() \
**METHOD** : GET \
**RESPONSE** :

```
{
"status": {
"statusCode": 2017 ,
"message": "Active requests fetched successfully",
"localizedMessage": "Active requests fetched successfully"
},
"responseData": {
"activeOrders": [
{
"id": 1 ,
"title": "Need someone to take care of my pet",
"type": "ADOPTION",
"startDate": 1666098000000 ,
"endDate": 1666357200000 ,
"durationNumber": 3 ,
"durationType": "DAYS",
"status": "REQUESTED",
"petId": 1 ,
"ownerId": 1 ,
"acceptedUserId": null,
"allowedPets": null,
"createdTimestamp": 1666006005000 ,
"updatedTimestamp": 1666006005000 ,
"petDetails": {
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dogwith great loyalty
towards owner",
"createdTimestamp": 1665639681000 ,
"updatedTimestamp": 1665639681000
},
"userDetails": {
"id": 1 ,
"firstName": "abhijeet",
"lastName": "S",
"userName": "abhijeet1",
"password": "password",
"email": "abhijeet1@student.unimelb.edu.au",
"phoneNumber": 474517087 ,
"address": "39 Lonsdale Street",
"image":
"https://petmenow-images.s3.us-west-2.amazonaws.com/ScreenShot2022-10-14at1
2.20.49am.png",
"dateOfBirth": 835797600000 ,
"createdTimestamp": 1664854793000 ,
"updatedTimestamp": 1664818245000 ,
"ownerPetList": [
{
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dog with great
loyalty towards owner",
"createdTimestamp": 1665639681000 ,
"updatedTimestamp": 1665639681000
}
]
}
},
{
"id": 2 ,
"title": "I can take care of your pet",
"type": "FOSTER",
"startDate": 1666098000000 ,
"endDate": 1667912400000 ,
"durationNumber": 3 ,
"durationType": "WEEKS",
"status": "REQUESTED",
"petId": null,
"ownerId": 1 ,
"acceptedUserId": null,
"allowedPets": "Dog,Cat",
"createdTimestamp": 1666006132000 ,
"updatedTimestamp": 1666006132000 ,
"petDetails": null,
"userDetails": {
"id": 1 ,
"firstName": "abhijeet",
"lastName": "S",
"userName": "abhijeet1",
"password": "password",
"email": "abhijeet1@student.unimelb.edu.au",
"phoneNumber": 474517087 ,
"address": "39 Lonsdale Street",
"image":
"https://petmenow-images.s3.us-west-2.amazonaws.com/ScreenShot2022-10-14at1
2.20.49am.png",
"dateOfBirth": 835797600000 ,
"createdTimestamp": 1664854793000 ,
"updatedTimestamp": 1664818245000 ,
"ownerPetList": [
{
"id": 1 ,
"name": "Casper",
"petType": "Dog",
"petBreed": "Lhasa Apsos",
"age": 2 ,
"vaccinated": true,
"image": null,
"ownerId": 1 ,
"description": "Fun-loving dog with great
loyalty towards owner",
"createdTimestamp": 1665639681000 ,
"updatedTimestamp": 1665639681000
} ] } } ] } }
```

