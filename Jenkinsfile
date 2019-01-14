pipeline {
  agent any
  stages {
    stage('Build') {
      post {
        failure {
          mail(subject: 'Build Finish', body: 'le build a echouee', to: 'fi_neddar@esi.dz')

        }

        success {
          mail(subject: 'Build Finish', body: 'le build a terminer', to: 'fi_neddar@esi.dz')

        }

      }
      steps {
        bat 'gradle build'
        bat 'gradle myJavadocs'
        archiveArtifacts(artifacts: 'build/libs/*.jar', onlyIfSuccessful: true)
        archiveArtifacts(artifacts: 'build/docs/javadoc/*', onlyIfSuccessful: true)
      }
    }
    stage('Code Analysis') {
      parallel {
        stage('Code Analysis') {
          agent any
          steps {
            withSonarQubeEnv('sonarqube') {
              bat 'C:\\Users\\ISLEM\\Downloads\\Compressed\\sonar-scanner-cli-3.3.0.1492-windows\\sonar-scanner-3.3.0.1492-windows\\bin\\sonar-scanner'
            }

          }
        }
        stage('Test Reporting') {
          steps {
            jacoco(buildOverBuild: true, maximumComplexityCoverage: '70')
          }
        }
      }
    }
    stage('Deployment') {
      when {
        branch 'master'
      }
      steps {
        bat 'gradle deployJar'
      }
    }
    stage('SlackNotification') {
      when {
        branch 'master'
      }
      steps {
        slackSend(baseUrl: 'https://tpgulp.slack.com/services/hooks/jenkins-ci/', channel: 'tpci-jenkins', failOnError: true, message: 'build and deployment finish', token: 'GXZd5JU7W2eJqhGQgcfB92Dd')
      }
    }
  }
}