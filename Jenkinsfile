pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo "Starting checkout..."
                checkout scm
                echo "Checkout complete."
            }
        }
        stage('Build') {
            steps {
                echo "Starting build..."
                // Your build steps here
                echo "Build step complete."
            }
        }
    }

 post {
        success {
            script {
                // Trigger another pipeline upon success
                build job: 'telegram-notification' //your pipeline for telegram
            }
        }
        failure {
            script {
                // Trigger another pipeline upon failure
                build job: 'telegram-notification' //your pipeline for telegram
            }
        }
    }