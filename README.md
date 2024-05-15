# Notes App

This is the back-end part of a project aimed at creating a simple Notes App, it uses Java, Spring and Maven.
Please refer to the front-end part of the project in order to have a working Notes App, available at my GitHub:
https://github.com/rubengarciavilches/notes_app

### Notes App:
It allows the user to create, read, update and delete any number of notes, which are constantly updated from the API calls
made by the website, in addition to that, it manages the authentication of users while using the app, restricting access 
only to the relevant parts, it allows the use of temporary accounts for the purpose of testing the website.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You will need to have installed Java and Maven for this project to work, you can do so from https://www.java.com/en/download/
and https://maven.apache.org/download.cgi, you will also need an editor of your choosing, 
[IntelliJ IDEA](https://www.jetbrains.com/idea/) is my personal recommendation, as this project was developed using that, 
but any other editor will work.

If the ``mvn`` commands do not work, you might have to restart the terminal or editor being used.

### Installing

You need to follow very few steps in order to get this project working.\
Open the project in your preferred editor or in the terminal, and install all the maven dependencies.
Please take note that you have to run this code at the directory where `pom.xml` is present.

### `./mvnw clean`
### `./mvnw install`

Now all the dependencies should have been installed, let your editor or IDE some time to update its indexes.\
In the project directory, you can start the project for development by building and running:

### `com.rubengv.portfolio.PortfolioApplication`

Runs the app in the development mode.\
It can be accessed at [http://localhost:8080](http://localhost:8080) and further endpoints.

It is recommended to use an IDE that automates the building and running process.

The Notes App will require the front-end to be running and able to reach this API, it can be tested without it.

## Endpoints

# API endpoints

These endpoints allow you to handle calls to the Notes App API.

### USER
`GET`[/api/v1/user/{userId}](#get-apiv1useruserid) `&token` <br/>
`POST`[/api/v1/user/signup](#post-apiv1signup) `+Body(email, pasword, username)` <br/>
`POST`[/api/v1/user/signup/guest](#post-apiv1signupguest) <br/>

### NOTE
`GET`[/api/v1/note/{userId}](#get-apiv1note) `&token` <br/>
`POST`[/api/v1/note/{userId}](#post-apiv1note) `&token +Body(title, content)` <br/>
`PUT`[/api/v1/note/{userId}/{noteId}](#put-apiv1signupguest) `&token +Body(title, content)` <br/>
`DELETE`[/api/v1/note/{userId}/{noteId}](#delete-apiv1signupguest) `&token` <br/>

### TOKEN
`POST`[/api/v1/auth/](#get-apiv1note) `+Body(email, password)` <br/>
___

### GET /api/v1/user/{userId}
Get data about the specified user.``Requires AUTH``

**Parameters**

|              Name | Required |  Type   | Description                                |
|------------------:|:--------:|:-------:|--------------------------------------------|
|          `userId` | required | UUID | The user for which to get the information. |
**Response**

```
{
    "errorMessage": null,
    "content": {
        "User": {
            "id": "0eff6d14-b3c8-497c-b06e-3e23fb3caecb",
            "email": "the.real.rick.astley.fr.fr@nggyu.com",
            "encrypted_password": "fasdf5HYTSHDH234lkjahsdlk1234aSKDJFRH",
            "username": "RickAstley",
            "created_at": "2024-05-19-23:56:12:231",
            "user_type": "registered"
        }
    }
}
```


## Deployment

For the deployment of this part of the project, Fly.io was chosen for the simplicity it provides, you may follow the
instructions for that at: https://fly.io/docs/languages-and-frameworks/dockerfile/

You would first delete any previous maven files and generate the new ones, then delete the app.jar if it exists already,
move the generated .jar to the root folder and deploy to fly.io

```
./mvnw clean
./mvnw install
del app.jar
move ./target/*.jar ./app.jar
fly deploy
```

You will need a PostgreSQL database running and accessible to the program, you will have to declare the following 
secrets for the project from the newly created Fly.io instance, they are the database credentials that you will 
be using:

```
ENV P_BACK_URL
ENV P_BACK_USER
ENV P_BACK_PASS
```

You may also deploy the back-end manually, you will still need to specify the same system variables, the commands to use
 to build it, and deploy it locally:

```
git clone https://github.com/rubengarciavilches/rubengv_spring.git
cd rubengv_spring
sudo apt update
apt install maven -y
mvn spring-boot:run
```

This approach may need further work, we recommend the Fly.io approach or other pre-built solutions.

## Built With

* [Spring](https://spring.io/) - The framework used.
* [Fly.io](https://sass-lang.com/) - Deployment website chosen.
* [Supabase](https://vercel.com/) - Online PostgreSQL database used.

## License

This project is licensed under the MIT License.

## Acknowledgments

* [Stackoverflow](https://stackoverflow.com/) - For troubleshooting and learning.
* [Dog](https://www.rubengv.com/dog.jpg) - For emotional support and code review.