pipeline {
    agent any

    tools {
        jdk 'jdk17'      // use the name configured in Jenkins global tools
        maven 'maven'    // same here
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ntnnganhg-wq/Automation_Demo.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Run the build inside the 'myproject' folder
                dir('myproject') {
                    sh 'mvn clean test'
                }
            }
        }
    }
}
