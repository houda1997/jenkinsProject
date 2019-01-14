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
          
          steps {
            withSonarQubeEnv('sonarqube') {
              bat "sonar-scanner"
            }

            waitForQualityGate true
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
