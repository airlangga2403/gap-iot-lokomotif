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
                echo "Build successful."
                telegramSend(message: "Build success on GitHub by ${env.BUILD_USER}!")
            }
        }
        failure {
            script {
                echo "Build failed."
                telegramSend(message: "Build failed on GitHub by ${env.BUILD_USER}!")
            }
        }
    }
}
