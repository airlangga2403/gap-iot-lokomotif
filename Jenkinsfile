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

                def message = """
                    {
                        "chat_id": "${CHAT_ID}",
                        "text": "Build Success Notification\\n==========================\\nUser Name: ${commitAuthorName}\\nEmail: ${commitAuthorEmail}\\nAction: Build and Test\\nBranch: ${branchName}\\nCommit Message: ${commitMessage}",
                        "disable_notification": false
                    }
                """.stripIndent()

                // Escaping double quotes and backslashes for JSON in curl command
                bat "curl -X POST -H \"Content-Type: application/json\" -d '${message}' https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
        failure {
            script {
                def failureMessage = """
                    {
                        "chat_id": "${CHAT_ID}",
                        "text": "Pipeline failed!",
                        "disable_notification": false
                    }
                """.stripIndent()

                // Escaping double quotes and backslashes for JSON in curl command
                bat "curl -X POST -H \"Content-Type: application/json\" -d '${failureMessage}' https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
    }
}
