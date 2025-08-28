pipeline {
    agent any

    tools {
        jdk   'jdk23'          // Nom du JDK déclaré dans Jenkins
        maven 'Maven_3.9.9'    // Nom de Maven déclaré dans Jenkins
    }

    environment {
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
                    branches: [[name: '*/master']],   // adapte si ta branche est différente
                    userRemoteConfigs: [[
                        url: 'https://github.com/amalbenabid/ProjectSwagLabs.git',
                        credentialsId: 'Github-token'
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
                    echo '🧪 Running Selenium/Cucumber/JUnit tests...'
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
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            }
        }
           stage('Generate Report') {
               steps {
                   bat 'mvn surefire-report:report'
               }
           }
        stage('Archive Surefire HTML Report') {
            steps {
                echo '📄 Archiving Surefire HTML test report...'
                archiveArtifacts artifacts: "target/site/surefire-report.html", fingerprint: true
            }
        }

        stage('Publish Cucumber Report') {
            steps {
                echo '🥒 Publishing Cucumber HTML report...'
                publishHTML([
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'cucumber.html',
                    reportName: 'Cucumber HTML Report',
                    keepAll: true
                ])
            }
        }
         stage('Allure Report') {
                    steps {
                        allure([
                            includeProperties: false,
                            jdk: '',
                            results: [[path: 'target/allure-results']]
                        ])
                    }
                }
    }

    post {
        success {
            echo '✅ Pipeline executed successfully. Reports archived.'
            emailext(
                subject: "✅ Build SUCCESS - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>The Selenium/Cucumber/JUnit pipeline completed successfully.</p>
                    <p>
                        <a href='${env.BUILD_URL}artifact/target/site/surefire-report.html'>
                            📄 View Surefire HTML Report
                        </a><br/>
                        <a href='${env.BUILD_URL}Cucumber_20HTML_20Report/'>
                            🥒 View Cucumber HTML Report
                        </a>
                    </p>
                """,
                mimeType: "${MIME_TYPE}",
                to: "${EMAIL_TO}"
            )
        }

        unstable {
            echo '⚠️ Pipeline unstable due to test failures. Reports still archived.'
            emailext(
                subject: "⚠️ Build UNSTABLE - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>Some tests failed.</p>
                    <p>
                        <a href='${env.BUILD_URL}artifact/target/site/surefire-report.html'>
                            📄 View Surefire HTML Report
                        </a><br/>
                        <a href='${env.BUILD_URL}Cucumber_20HTML_20Report/'>
                            🥒 View Cucumber HTML Report
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
