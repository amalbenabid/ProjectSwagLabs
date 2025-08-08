pipeline {
    agent any

    tools {
        maven 'maven3.9.11' // Nom que tu as configuré dans Jenkins
        jdk 'jdk17'         // Nom du JDK configuré dans Jenkins
    }

    stages {

        stage('Checkout') {
            steps {
                echo '📥 Clonage du projet depuis GitHub...'
                git 'https://github.com/amalbenabid/ProjectSwagLabs.git'
            }
        }

        stage('Build') {
            steps {
                echo '🏗️ Compilation du projet et téléchargement des dépendances...'
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Lancement des tests Selenium/Cucumber/TestNG...'
                sh 'mvn test'
            }
        }

        stage('Rapports JUnit') {
            steps {
                echo '📊 Publication des rapports JUnit/TestNG...'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Rapport HTML Cucumber') {
            steps {
                echo '🌐 Publication du rapport HTML Cucumber...'
                publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'cucumber-reports.html',
                    reportName: 'Rapport Cucumber'
                ])
            }
        }
    }

    post {
        success {
            echo '✅ Build terminé avec succès !'
            mail to: 'ton.email@example.com',
                 subject: "✅ Build réussi : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Le build ${env.BUILD_NUMBER} a réussi. Voir : ${env.BUILD_URL}"
        }
        failure {
            echo '❌ Le build a échoué.'
            mail to: 'ton.email@example.com',
                 subject: "❌ Build échoué : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Le build ${env.BUILD_NUMBER} a échoué. Voir : ${env.BUILD_URL}"
        }
    }
}
