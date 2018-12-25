#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    stage('check java') {
        java -version
    }

    stage('clean') {
        ./mvnw clean
    }

    stage('install tools') {
        ./mvnw com.github.eirslett:frontend-maven-plugin:install-node-and-npm -DnodeVersion=v10.14.1 -DnpmVersion=6.4.1
    }

    stage('npm install') {
        ./mvnw com.github.eirslett:frontend-maven-plugin:npm
    }

    stage('backend tests') {
        try {
            ./mvnw test
        } catch(err) {
            throw err
        } finally {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }

    stage('frontend tests') {
        try {
            ./mvnw com.github.eirslett:frontend-maven-plugin:npm -Dfrontend.npm.arguments='run test'
        } catch(err) {
            throw err
        } finally {
            junit '**/target/test-results/jest/TESTS-*.xml'
        }
    }

    stage('packaging') {
        ./mvnw verify deploy -Pprod -DskipTests
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
