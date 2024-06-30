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
                def commitAuthorEmail = sh(script: 'git log -1 --pretty=format:"%ae"', returnStdout: true).trim()
                def commitMessage = sh(script: 'git log -1 --pretty=format:"%s"', returnStdout: true).trim()
                def branchName = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()

                // Constructing multi-line message
                def message = """
                    Build Success Notification
                    ==========================
                    User Name: ${commitAuthorName}
                    Email: ${commitAuthorEmail}
                    Action: Build and Test
                    Branch: ${branchName}
                    Commit Message: ${commitMessage}
                """.stripIndent()

                // Escaping double quotes and backslashes in the message for JSON
                message = message.replaceAll('"', '\\\\"')

                // Construct JSON payload for curl command
                def payload = """
                    {
                        "chat_id": "${CHAT_ID}",
                        "text": "${message}",
                        "disable_notification": false
                    }
                """.stripIndent()

                // Execute curl command to send the message
                bat "curl -X POST -H 'Content-Type: application/json' -d '${payload}' https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
        failure {
            script {
                // Construct JSON payload for failure message (if needed)
                def failureMessage = """
                    {
                        "chat_id": "${CHAT_ID}",
                        "text": "Pipeline failed!",
                        "disable_notification": false
                    }
                """.stripIndent()

                // Execute curl command to send failure message (if needed)
                bat "curl -X POST -H 'Content-Type: application/json' -d '${failureMessage}' https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
    }
}
