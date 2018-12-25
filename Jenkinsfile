#!/usr/bin/env groovy

node ("windows") {
  stage ('Build') {
 
    git url: 'https://github.com/Singulus2/vka'
 
 
      bat "mvn clean install"
 
   }
}
