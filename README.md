SHA 256

env \n
openjdk version "11.0.13" 2021-10-19 \n
OpenJDK Runtime Environment (build 11.0.13+8-Ubuntu-0ubuntu1.21.04) \n
OpenJDK 64-Bit Server VM (build 11.0.13+8-Ubuntu-0ubuntu1.21.04, mixed mode, sharing) \n

sudo apt install maven \n

mvn clean compile assembly:single

java -cp ./target/addr-1.0-SNAPSHOT-jar-with-dependencies.jar com.crtp.app.App
