pipeline {
    agent any

    environment {
        // Telegram configuration
        TOKEN = credentials('telegram-token')
        CHAT_ID = credentials('telegram-chat-id')
    }

    stages {
        stage('Run Stage') {
            steps {
                echo 'Running'
            }
        }
    }

    post {
        success {
            script {
                def commitAuthorName = sh(script: 'git log -1 --pretty=format:"%an"', returnStdout: true).trim()
                echo "Commit Author Name: ${commitAuthorName}"

                def commitAuthorEmail = sh(script: 'git log -1 --pretty=format:"%ae"', returnStdout: true).trim()
                echo "Commit Author Email: ${commitAuthorEmail}"

                def commitMessage = sh(script: 'git log -1 --pretty=format:"%s"', returnStdout: true).trim()
                echo "Commit Message: ${commitMessage}"

                def branchName = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
                echo "Branch Name: ${branchName}"

   def message = "Build Success Notification\n
                             ==========================\n
                              User Name: ${commitAuthorName}\n
                              Email: ${commitAuthorEmail}\n
                              Action: Build and Test\n
                              Branch: ${branchName}\n
                              Commit Message: ${commitMessage}"

                bat "curl -X POST -H \"Content-Type: application/json\" -d \"{\\\"chat_id\\\":${CHAT_ID}, \\\"text\\\": \\\"${message}\\\", \\\"disable_notification\\\": false}\" https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
        failure {
            script {
                bat "curl -X POST -H \"Content-Type: application/json\" -d \"{\\\"chat_id\\\":${CHAT_ID}, \\\"text\\\": \\\"Pipeline failed!\\\", \\\"disable_notification\\\": false}\" https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
    }
}
