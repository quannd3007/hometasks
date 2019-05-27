pipeline {
    agent any
    tools {
        maven 'Maven3.6.1'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Website Testing') {
            steps {
                sh 'mvn clean test -PWebsite'
            }
        }

        stage ('Mobile Testing') {
                    steps {
                        sh 'mvn clean test -PMobile'
                    }
        }

    }

    post('Report') {
        always {
            script {
                allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                        ])
            }
        }
    }
}