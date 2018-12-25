#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }


    stage('clean') {
        mvnw clean
    }


    stage('backend tests') {
        try {
            mvnw test
        } catch(err) {
            throw err
        } finally {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }


    stage('packaging') {
        mvnw verify deploy -Pprod -DskipTests
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
