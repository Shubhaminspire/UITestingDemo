pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from your SCM
                git branch: 'feature-Shubham-UITestingDemo', url: 'https://github.com/Shubhaminspire/feature-Shubham-UITestingDemo.git'
            }
        }
        stage('Build') {
            steps {
                // Build your project
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Run your TestNG tests with your existing testng.xml
                sh 'mvn test -Dtestng.xml=testng.xml'
            }
        }
    }

    post {
        always {
            // Archive test results
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
