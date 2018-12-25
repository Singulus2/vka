#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }




    stage('packaging') {
        mvnw verify deploy -Pprod -DskipTests
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
