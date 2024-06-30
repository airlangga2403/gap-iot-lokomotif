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
                def commitAuthorName = bat(script: 'git log -1 --pretty=format:%an', returnStdout: true).trim()
                def commitAuthorEmail = bat(script: 'git log -1 --pretty=format:%ae', returnStdout: true).trim()
                def commitMessage = bat(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                def branchName = bat(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()

                def message = """\
*Build Success Notification*
==========================
*User Name:* ${commitAuthorName}
*Email:* ${commitAuthorEmail}
*Action:* Build and Test
*Branch:* ${branchName}
*Commit Message:* ${commitMessage}
"""

                bat "curl -X POST -H \"Content-Type: application/json\" -d \"{\\\"chat_id\\\":\\\"${CHAT_ID}\\\", \\\"text\\\": \\\"${message}\\\", \\\"parse_mode\\\": \\\"Markdown\\\", \\\"disable_notification\\\": false}\" https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
        failure {
            script {
                def commitAuthorName = bat(script: 'git log -1 --pretty=format:%an', returnStdout: true).trim()
                def commitAuthorEmail = bat(script: 'git log -1 --pretty=format:%ae', returnStdout: true).trim()
                def commitMessage = bat(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                def branchName = bat(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()

                def message = """\
*Build Failure Notification*
==========================
*User Name:* ${commitAuthorName}
*Email:* ${commitAuthorEmail}
*Action:* Build and Test
*Branch:* ${branchName}
*Commit Message:* ${commitMessage}
"""

                bat "curl -X POST -H \"Content-Type: application/json\" -d \"{\\\"chat_id\\\":\\\"${CHAT_ID}\\\", \\\"text\\\": \\\"${message}\\\", \\\"parse_mode\\\": \\\"Markdown\\\", \\\"disable_notification\\\": false}\" https://api.telegram.org/bot${TOKEN}/sendMessage"
            }
        }
    }
}
