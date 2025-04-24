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

    stages {
       
        stage('Step 1: LOAD SCM'){
            steps {
                // Get some code from a GitHub repository
                git branch: 'feature/initialconfig', url: 'https://github.com/Isavb03/DevOpsEnvirnoment.git'
                // sh '''
                //     git --version
                //     mvn --version
                // '''
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
                    mvn clean compile
                '''             
            }                      
        }
    }

    post {
      // If Maven was able to run the tests, even if some of the test
      // failed, record the test results and archive the jar file.
      success{
        archiveArtifacts 'target/*.jar'
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