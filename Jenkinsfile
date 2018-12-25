#!/usr/bin/env groovy

node ("windows") {
  stage ('Build') {
 
    git url: 'https://github.com/Singulus2/vka'
 
    withMaven(...) {
 
      bat "mvn clean install"
 
    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
  }
}
