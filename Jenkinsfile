pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Exécute la commande Maven pour construire le projet
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                // Exécute les tests JUnit
                sh 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    echo 'Analyzing with SonarQube...'
                    // Remplace 'SonarQube' par le nom de ton serveur SonarQube configuré dans Jenkins
                    withSonarQubeEnv('SonarQube') {
                        // Assure-toi que sonar-scanner est correctement configuré
                        sh 'sonar-scanner -Dsonar.projectKey=sonar_eyajerbi5BI5 -Dsonar.projectName=sonar_eyajerbi5BI5 -Dsonar.sources=src/main -Dsonar.language=java'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Ajoute ici la commande pour déployer si nécessaire
            }
        }
        stage('Quality Gate') {
            steps {
                // Cette étape attend la réponse de SonarQube sur la qualité du code
                waitForQualityGate abortPipeline: true
            }
        }
    }
}

