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
pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    triggers {
        // Poll every 5 minutes instead of using GitHub webhooks
        pollSCM('*/1 * * * *')
    }

    stages {
       
        stage('Step 1: LOAD SCM'){
            steps {
                // Get some code from a GitHub repository
                git branch: 'feature/service1.0', url: 'https://github.com/Isavb03/DevOpsEnvirnoment.git'

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
 
        // stage('STEP 4: SONARQUBE') {
        //     environment {
        //         SONARQUBE_TOKEN = credentials('sonarqube-token')
        //     }
        //     steps {
        //         withSonarQubeEnv('SonarQube') {
        //             sh '''
        //                 mvn clean verify sonar:sonar \
        //                 -Dsonar.projectName='university-result-system' \
        //                 -Dsonar.host.url=http://sonarqube:9000 \
        //                 -Dsonar.projectKey=university-result-system \
        //                 -Dsonar.token=${SONARQUBE_TOKEN} \
        //                 -Dsonar.java.binaries=target/classes \
        //                 -Dsonar.javascript.node.maxspace=4096 \
        //                 -Dsonar.javascript.timeout=1800 \
        //                 -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
        //                 -Dsonar.java.coveragePlugin=jacoco \
        //                 -Dsonar.dependencyCheck.reportPath=target/dependency-check-report.xml \
        //                 -Dsonar.dependencyCheck.htmlReportPath=target/dependency-check-report.html \
        //                 -Dsonar.exclusions=**/vendor/**,**/node_modules/**,**/*.spec.ts \
        //                 -Dsonar.test.inclusions=**/*Test.java,**/*Tests.java,**/*IT.java \
        //                 -Dsonar.qualitygate.wait=false  # poner en true cuando se corrija el codigo
        //             '''
        //         }
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
                sh """
                    # rm -rf admin-service
                    # git clone https://github.com/Isavb03/admin-service.git

                    # ls -l admin-service/

                    kubectl config view      
                    kubectl config current-context
                    ls -l /var/run/secrets/kubernetes.io/serviceaccount    

                    # Verify BUILD_ID is set
                    echo "BUILD_ID=${env.BUILD_ID}"

                    # Substitute variable
                    envsubst < manifests/deployment.yaml > deployment-with-build-id.yaml
                    # envsubst < admin-service/deployment-auth.yaml > deployment-auth-with-build-id.yaml


                    # Check the generated file
                    cat deployment-with-build-id.yaml
                    # cat deployment-auth-with-build-id.yaml

                    export KUBECONFIG=/home/jenkins/.kube/config
                    
                                       
                    # kubectl apply -f deployment-auth-with-build-id.yaml
                    # kubectl apply -f admin-service/service-auth.yaml

                    # kubectl wait --for=condition=available --timeout=300s deployment/admin-service

                    # Deploy
                    kubectl apply -f deployment-with-build-id.yaml
                    kubectl apply -f manifests/service.yaml

                    # Verificar conexión
                    kubectl get svc admin-service -o wide
                """             
            }                   
        }
        
        stage('STEP 8: CLEANUP'){
            steps{     
                sh """
                    rm -f deployment-with-build-id.yaml
                    # rm -rf admin-service
                """             
            }                      
        }      

    }

    post {
      // If Maven was able to run the tests, even if some of the test
      // failed, record the test results and archive the jar file.
      success{
        archiveArtifacts 'target/*.war'
        echo "Build ${currentBuild.fullDisplayName} completed successfully!! :D"
      }
    //   always {
    //     junit allowEmptyResults: true, testResults: '**/target/surefire-reports/TEST-*.xml'

    //     // Collect JaCoCo code coverage results
    //     jacoco(
    //         execPattern: 'target/jacoco.exec',
    //         classPattern: 'target/classes',
    //         sourcePattern: 'src/main/java',
    //         exclusionPattern: 'src/test*'
    //     )
    
      
    //   }

      failure{
        echo "Ha fallado el build número ${currentBuild.fullDisplayName}"
      }      
    }
}