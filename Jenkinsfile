pipeline {
    agent any
    tools { maven 'Maven 3.8+' } // vagy Maven név, amit Jenkinsben beállítottál
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/hannibal-root/cucumber_login.git', branch: 'master'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dcucumber.plugin=pretty,html:target/cucumber-html-report,json:target/cucumber.json'
            }
        }
        stage('Cucumber Report') {
            steps {
                cucumber buildStatus: 'UNSTABLE', jsonReportDirectory: 'target', fileIncludePattern: '*.json'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.html', fingerprint: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}
