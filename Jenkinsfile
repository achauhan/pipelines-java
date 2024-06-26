@Library('sharedlib') _
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clean workspace before checkout
                deleteDir()
                checkout scm
                script {
                    HelloWorld()
                    log.info "info"
                    log.warning "warning"
                    // Checkout the GitHub repository
                }
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
            steps {
                // Run SonarQube analysis using the SonarQube Scanner for Jenkins plugin
                script {
                    try{
                    def scannerHome = tool 'SonarQubeScanner'
                    withSonarQubeEnv('SonarQubeScanner') {
                        sh "${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=sonartest \
                            -Dsonar.projectName=sonartest \
                            -Dsonar.sources=src \
                            -Dsonar.plugins.downloadOnlyRequired=true \
                            -Dsonar.java.binaries=build/classes"

                        // waitForQualityGate abortPipeline: true
                    }
                    }
                    catch (Exception e) {
                        echo "${e.getMessage()}"
                    }
                }
            }
        }


    }
}