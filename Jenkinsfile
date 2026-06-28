pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/DKS2424/smart-expiry-tracker.git'
                    ]]
                )
            }
        }
        stage('Build Backend') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t smart-expiry-backend .'
            }
        }
        stage('Docker Deploy') {
            steps {
                sh 'docker stop smart-expiry-backend || true'
                sh 'docker rm smart-expiry-backend || true'
                sh 'docker run -d --name smart-expiry-backend -p 9090:9090 smart-expiry-backend'
            }
        }
    }
    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Deployment Failed!'
        }
    }
}
