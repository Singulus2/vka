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

    stage('frontend tests') {
        try {
            mvnw com.github.eirslett:frontend-maven-plugin:npm -Dfrontend.npm.arguments='run test'
        } catch(err) {
            throw err
        } finally {
            junit '**/target/test-results/jest/TESTS-*.xml'
        }
    }

    stage('packaging') {
        mvnw verify deploy -Pprod -DskipTests
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
