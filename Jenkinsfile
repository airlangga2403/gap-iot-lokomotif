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
                def commitAuthorName = bat(script: 'git log -1 --pretty=format:"%an"', returnStdout: true).trim()
                def commitAuthorEmail = bat(script: 'git log -1 --pretty=format:"%ae"', returnStdout: true).trim()
                def commitMessage = bat(script: 'git log -1 --pretty=format:"%s"', returnStdout: true).trim()
                def branchName = bat(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()

                def message = "Pipeline succeeded! Commit author name: ${commitAuthorName}"

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
