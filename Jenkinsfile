pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/Developer-SANKET/Just-DayOne.git'
      }
    }

    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }

    stage('Build & Push Image') {
      steps {
        sh 'docker build -t devsanket99/nike-app:latest .'
        withCredentials([usernamePassword(credentialsId: "dockerhub-creds", usernameVariable: "DH_USER", passwordVariable: "DH_PASS")]) {
          sh 'echo $DH_PASS | docker login -u $DH_USER --password-stdin'
          sh 'docker push devsanket99/nike-app:latest'
        }
      }
    }

    stage('Deploy to EC2') {
      steps {
        sh '''
          ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/.ssh/id_rsa ubuntu@${EC2_HOST} \
          "docker pull devsanket99/nike-app:latest && \
           docker stop nike-app || true && \
           docker rm nike-app || true && \
           docker run -d --name nike-app -p 8080:8080 devsanket99/nike-app:latest"
        '''
      }
    }
  }
}
