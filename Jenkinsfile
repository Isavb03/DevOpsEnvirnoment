// pipeline {
//   agent any
//   stages {
//       stage('FASE 1 LOAD '){
//         steps{
//           load "C:/Users/mvelazquez/JenkinsFiles/Jenkinsfile"
//         }
//       }

//   }
// }

// stage('SCM') {
//     git 'https://github.com/foo/bar.git'
//   }
//   stage('SonarQube analysis') {
//     withSonarQubeEnv('My SonarQube Server') {
//       // requires SonarQube Scanner for Maven 3.2+
//       sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
//     }
//   }
pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    triggers {
        // Poll every 5 minutes instead of using GitHub webhooks
        pollSCM('*/5 * * * *')
    }

    stages {
       
        stage('Step 1: LOAD SCM'){
            steps {
                // Get some code from a GitHub repository
                git branch: 'feature/initialconfig', url: 'https://github.com/Isavb03/DevOpsEnvirnoment.git'

            }
        }
       
        stage('Step 2: BUILD') {
            steps {
                sh '''
                    mvn clean compile
                '''               
            }
           
        }
       
        stage('STEP 3: PACKAGE'){
            steps{
                sh '''
                    mvn package
                '''             
            }                      
        }
 
        // stage('STEP 4: TEST'){
        //     steps{     
        //     }                      
        // }

        stage('STEP 5: BUILD DOCKER IMAGE') {
            steps {
                sh '''
                    BUILD_ID=${BUILD_ID}
                    docker build -t isavb03/university-result-system:$BUILD_ID .
                '''
            }
        }

        stage('STEP 6: PUSH DOCKER IMAGE'){
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PWD'
                )]) {
                    sh """
                        docker login -u ${env.DOCKER_USER} -p ${env.DOCKER_PWD}
                        docker push isavb03/university-result-system:${env.BUILD_ID}
                    """
                }
            }
        }

        stage('STEP 7: DEPLOY TO MINIKUBE'){
            steps{     
                sh '''
                    kubectl apply -f deployment.yaml
                    kubectl apply -f service.yaml
                '''             
            }                   
        }      

    }

    post {
      // If Maven was able to run the tests, even if some of the test
      // failed, record the test results and archive the jar file.
      success{
        archiveArtifacts 'target/*.war'
      }
    //   always {
    //     junit '**/target/surefire-reports/TEST-*.xml'
    //   }
      // changed{
      //     emailext subject: "Job '${JOB_NAME}' (${BUILD_NUMBER})",
      //     body: "Please go to ${BUILD_URL} and verify the build",
      //     attachLog: true,
      //     compressLog: true,
      //     to: 'isabel.valles-bertomeu.external@capgemini.com'
      // }
      failure{
        echo "Ha fallado el build número ${currentBuild.fullDisplayName}"
      }      
    }
}