pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clean workspace before checkout
                deleteDir()

                // Checkout the GitHub repository
                git branch: 'main', credentialsId: '134884d4-f75f-487e-bfdb-13c96df52434', url: 'https://github.com/achauhan/pipelines-java.git'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }

        // Add more stages as needed
        // For example: Test, Deploy
    }

    // Add post-build actions if needed
    // For example: sending notifications, archiving artifacts
}
