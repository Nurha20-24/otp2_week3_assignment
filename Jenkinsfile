pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'
    }

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'Docker-Hub'
        DOCKERHUB_REPO = 'nurha2024/otp2_week3_assignment'
        DOCKER_IMAGE_TAG = 'latest'
        PATH = "/usr/local/bin:/opt/homebrew/bin:$PATH"
        SONAR_TOKEN = credentials('sonar-token')  //stored in Jenkins credentials

    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Nurha20-24/otp2_week3_assignment.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Generate Report') {
            steps {
                sh 'mvn jacoco:report'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('SonarQube Analysis') {
        steps {
            sh """
                mvn clean verify sonar:sonar \
                -Dsonar.projectKey=otp2_week3 \
                -Dsonar.token=${SONAR_TOKEN} \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
            """
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '/usr/local/bin/docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} .'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Docker-Hub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '/usr/local/bin/docker login -u $DOCKER_USER -p $DOCKER_PASS'
                    sh '/usr/local/bin/docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}'
                }
            }
        }
    }
}