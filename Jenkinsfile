pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clean workspace before checkout
                deleteDir()

                // Checkout the GitHub repository
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }

        stage('Unit Test') {
            steps {
                // Run unit tests
                sh './gradlew test'
            }
        }

        stage('Publish Code Coverage') {
            steps {
                // Generate code coverage report
                sh './gradlew jacocoTestReport'

                // Archive code coverage report
                publishHTML(target: [allowMissing: false,
                                     alwaysLinkToLastBuild: false,
                                     keepAll: true,
                                     reportDir: 'build/reports/tests/test',
                                     reportFiles: 'index.html',
                                     reportName: 'Code Coverage Report'])
            }
        }        
        
        stage('SonarQube Analysis') {
                withSonarQubeEnv() {
                sh "./gradlew sonar"
            }
        }
    }

    // Add post-build actions if needed
    // For example: sending notifications, archiving artifacts
}
