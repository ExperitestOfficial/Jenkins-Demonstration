pipeline {
  agent any
  stages {
    stage('Build Server') {
      parallel {
        stage('Build Server') {
          steps {
            sleep 10
          }
        }
        stage('Build Mobile App') {
          steps {
            sleep 22
          }
        }
      }
    }
    stage('Deploy to Stage') {
      parallel {
        stage('Deploy to Stage') {
          steps {
            sleep 10
          }
        }
        stage('Upload Mobile App') {
          steps {
            sleep 10
          }
        }
      }
    }
    stage('Run API Tests') {
      parallel {
        stage('Run API Tests') {
          steps {
            sleep 10
          }
        }
        stage('Run App UI Tests') {
          steps {
            sleep 10
          }
        }
        stage('Run Performance Tests') {
          steps {
            sleep 20
          }
        }
      }
    }
  }
}