pipeline {
  agent any
  environment {
    DOCKER_IMAGE = "your-dockerhub-username/nike-app:latest"
    EC2_HOST = "${EC2_HOST}"
    EC2_USER = "ec2-user"
    SSH_KEY = "/var/lib/jenkins/.ssh/id_rsa"
  }
  stages {
    stage('Checkout') {
      steps { git branch: 'main', url: 'https://github.com/your-username/nike-app.git' }
    }
    stage('Build') {
      steps { sh 'mvn -B -DskipTests clean package' }
    }
    stage('Build & Push Image') {
      steps {
        sh 'docker build -t $DOCKER_IMAGE .'
        withCredentials([usernamePassword(credentialsId: "dockerhub-creds", usernameVariable: "DH_USER", passwordVariable: "DH_PASS")]) {
          sh 'echo $DH_PASS | docker login -u $DH_USER --password-stdin'
          sh 'docker push $DOCKER_IMAGE'
        }
      }
    }
    stage('Deploy to EC2') {
      steps {
        echo "Deploying to EC2 host: ${EC2_HOST}"
        sh '''
          ssh -o StrictHostKeyChecking=no -i ${SSH_KEY} ${EC2_USER}@${EC2_HOST} \
          "docker pull $DOCKER_IMAGE && docker stop nike-app || true && docker rm nike-app || true && docker run -d --name nike-app -p 8080:8080 $DOCKER_IMAGE"
        '''
      }
    }
  }
}
