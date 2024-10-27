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
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Ajoute ici la commande pour déployer si nécessaire
            }
        }
    }
}

