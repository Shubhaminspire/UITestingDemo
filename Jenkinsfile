pipeline {
    agent any

    tools {
        maven 'Maven-3.8.6' // The name you provided in the Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'feature-Shubham-UITestingDemo', url: 'https://github.com/Shubhaminspire/feature-Shubham-UITestingDemo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Dtestng.xml=testng.xml'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
