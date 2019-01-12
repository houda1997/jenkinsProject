pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'gradle build'
        sh 'gradle myJavadocs'
        sh 'gradle generateMatrixAPI'
      }
    }
    stage('MailNotification') {
      steps {
        mail(subject: 'Build Finish', body: 'le build a terminer', to: 'fi_neddar@esi.dz')
      }
    }
    stage('CodeAnalysis') {
      steps {
        withSonarQubeEnv('My SonarQube Server') {
          sh 'sh \'./gradlew --info sonarqube\''
        }

      }
    }
    stage('TestReporting') {
      steps {
        jacoco(buildOverBuild: true)
      }
    }
    stage('Deployment') {
      steps {
        sh 'gradle deployJar'
      }
    }
    stage('SlackNotification') {
      steps {
        slackSend(baseUrl: 'https://tpgulp.slack.com/services/hooks/jenkins-ci/', channel: 'tpci-jenkins', failOnError: true, message: 'build and deployment finish', token: 'GXZd5JU7W2eJqhGQgcfB92Dd')
      }
    }
  }
}