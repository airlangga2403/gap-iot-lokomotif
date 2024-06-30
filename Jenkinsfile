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

                def message = new StringBuilder()
                message.append("Build Success Notification\\n")
                message.append("==========================\\n")
                message.append("User Name ${commitAuthorName}\\n")
                message.append("Email ${commitAuthorEmail}\\n")
                message.append("Action Build and Test\\n")
                message.append("Branch ${branchName}\\n")
                message.append("Commit Message ${commitMessage}")
                echo "${message}"

                // Replace line breaks with \n for JSON compatibility
                def jsonMessage = message.toString()

                bat "curl -X POST -H \"Content-Type: application/json\" -d \"{\\\"chat_id\\\":${CHAT_ID}, \\\"text\\\": \\\"${jsonMessage}\\\", \\\"disable_notification\\\": false}\" https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
        failure {
            script {
                bat "curl -X POST -H \"Content-Type: application/json\" -d \"{\\\"chat_id\\\":${CHAT_ID}, \\\"text\\\": \\\"Pipeline failed!\\\", \\\"disable_notification\\\": false}\" https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
    }
}
