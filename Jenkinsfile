pipeline {
    agent any

    tools {
        jdk   'jdk-23'          // ⚠️ mets ici le nom de ton JDK déclaré dans Jenkins
        maven 'maven-3.9.8'     // ⚠️ idem pour Maven
    }

    environment {
        REPORT_DIR  = 'target/surefire-reports'
        REPORT_NAME = 'junit-report.html'
        EMAIL_TO    = 'benabidamal01@gmail.com'
        MIME_TYPE   = 'text/html'
    }

    stages {
        stage('Verify Environment') {
            steps {
                echo '📦 Verifying environment setup...'
                bat 'java -version'
                bat 'mvn -v'
                bat 'git --version'
            }
        }

        stage('Checkout Code') {
            steps {
                echo '📥 Checking out code from GitHub...'
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],     // adapte si ta branche est "master"
                    userRemoteConfigs: [[
                        url: 'https://github.com/amalbenabid/ProjectSwagLabs.git',
                        credentialsId: 'gituse'
                    ]]
                ])
            }
        }

        stage('Build Project') {
            steps {
                echo '🚀 Building project with Maven (skip tests)...'
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    echo '🧪 Running Selenium/JUnit tests...'
                    int status = bat(
                        script: 'mvn test',
                        returnStatus: true
                    )
                    if (status != 0) {
                        echo "⚠️ Tests failed with exit code ${status} — marking build UNSTABLE."
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Publish JUnit Reports') {
            steps {
                echo '📊 Publishing JUnit XML reports...'
                junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            }
        }

        stage('Archive HTML Report') {
            steps {
                echo '📄 Archiving HTML test report...'
                // ⚠️ tu dois générer un rapport HTML via surefire, cucumber-reporting ou autre
                // Ici on suppose que tu génères déjà target/site/surefire-report.html
                archiveArtifacts artifacts: "target/site/surefire-report.html", fingerprint: true
                echo "📎 Report available at: ${env.BUILD_URL}artifact/target/site/surefire-report.html"
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline executed successfully. Test report archived.'
            emailext(
                subject: "✅ Build SUCCESS - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>The Selenium/JUnit pipeline completed successfully.</p>
                    <p>
                        <a href='${env.BUILD_URL}artifact/target/site/surefire-report.html'>
                            📄 View JUnit report
                        </a>
                    </p>
                """,
                mimeType: "${MIME_TYPE}",
                to: "${EMAIL_TO}"
            )
        }

        unstable {
            echo '⚠️ Pipeline unstable due to test failures. Report still archived.'
            emailext(
                subject: "⚠️ Build UNSTABLE - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>Some JUnit tests failed.</p>
                    <p>
                        <a href='${env.BUILD_URL}artifact/target/site/surefire-report.html'>
                            📄 View JUnit report
                        </a>
                    </p>
                """,
                mimeType: "${MIME_TYPE}",
                to: "${EMAIL_TO}"
            )
        }

        failure {
            echo '❌ Pipeline failed. Please check the logs for details.'
            emailext(
                subject: "❌ Build FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>The pipeline failed before or during Maven build/tests.</p>
                    <p>
                        <a href='${env.BUILD_URL}console'>🧾 View Console Output</a>
                    </p>
                """,
                mimeType: "${MIME_TYPE}",
                to: "${EMAIL_TO}"
            )
        }

        cleanup {
            cleanWs()
        }
    }
}
