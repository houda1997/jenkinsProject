pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat 'gradle build'
        bat 'gradle myJavadocs'
        bat 'gradle generateMatrixAPI'
      }
    }
    stage('MailNotification') {
      steps {
        mail(subject: 'Build Finish', body: 'le build a terminer', to: 'fi_neddar@esi.dz')
      }
    }
    stage('CodeAnalysis') {
      steps {
        waitForQualityGate true
      }
    }
    stage('TestReporting') {
      steps {
        jacoco(buildOverBuild: true)
      }
    }
    stage('Deployment') {
      steps {
        bat 'gradle deployJar'
      }
    }
    stage('SlackNotification') {
      steps {
        slackSend(baseUrl: 'https://tpgulp.slack.com/services/hooks/jenkins-ci/', channel: 'tpci-jenkins', failOnError: true, message: 'build and deployment finish', token: 'GXZd5JU7W2eJqhGQgcfB92Dd')
      }
    }
  }
}