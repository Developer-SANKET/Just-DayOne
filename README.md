# Nike DevOps Demo (Simple Java Spring Boot)

What you get:
- Spring Boot app (login + simple catalog)
- Dockerfile
- docker-compose.yml
- Jenkinsfile (4-step pipeline)
- Instructions to build, run, and deploy to EC2

Local build:
1. Install Java 17 and Maven.
2. mvn package
3. docker build -t your-dockerhub-username/nike-app:latest .
4. docker run -p 8080:8080 your-dockerhub-username/nike-app:latest

Docker Compose:
- docker-compose up --build

Jenkins:
- Add DockerHub credentials to Jenkins with id `dockerhub-creds`.
- Ensure Jenkins has SSH key at path referenced in Jenkinsfile (or modify).
- Configure `EC2_HOST` environment variable or param for target EC2 instance.

EC2:
- Ensure Docker is installed on EC2 and port 8080 allowed in Security Group.
- Copy your SSH key to Jenkins or use Jenkins credentials to SSH.

Default users:
- admin / admin123
- user / user123
