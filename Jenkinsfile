pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'SpringBootApp'
        DOCKER_TAG = 'latest'
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_USERNAME = 'pramodcgowda'
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    def dockerHome = tool 'mydocker'
                    env.PATH = "${dockerHome}/bin:${env.PATH}"
                }
            }
        }

        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    sh './gradlew clean build'
                }
            }
        }

        stage('Package') {
             steps {
                 sh './gradlew bootJar'
             }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh """
                        docker build -t ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${DOCKER_TAG} .
                    """
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: '13b0e139-9da6-480e-bd42-d36fbee50cd0', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh """
                            echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password \$DOCKER_PASSWORD
                            docker push \${DOCKER_USERNAME}/\${DOCKER_IMAGE}:\${DOCKER_TAG}
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Backend build and Docker push successful!'
        }
        failure {
            echo 'Backend build or Docker push failed.'
        }
    }
}
