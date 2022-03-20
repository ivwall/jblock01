SHA 256

env 
openjdk version "11.0.13" 2021-10-19 
OpenJDK Runtime Environment (build 11.0.13+8-Ubuntu-0ubuntu1.21.04) 
OpenJDK 64-Bit Server VM (build 11.0.13+8-Ubuntu-0ubuntu1.21.04, mixed mode, sharing) 

sudo apt install maven 

mvn clean compile assembly:single

java -cp ./target/addr-1.0-SNAPSHOT-jar-with-dependencies.jar com.crtp.app.App


reference links
https://iancoleman.io/

https://iancoleman.io/bitcoin-key-compression/

https://medium.com/@parthshah.ce/generate-bitcoin-addresses-using-java-in-six-steps-b1c418796a9e

https://www.novixys.com/blog/generate-bitcoin-addresses-java/

https://developpaper.com/how-to-generate-bitcoin-wallet-address-in-java/




