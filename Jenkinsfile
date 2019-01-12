pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat 'gradle build'
        bat 'gradle myJavadocs'
        archiveArtifacts(artifacts: 'build/libs/*.jar', onlyIfSuccessful: true)
      }
    }
    stage('MailNotification') {
      steps {
        mail(subject: 'Build Finish', body: 'le build a terminer', to: 'fi_neddar@esi.dz')
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