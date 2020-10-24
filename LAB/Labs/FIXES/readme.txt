#SOAP WSDL

##Server

New project > Maven > Java application


##Client

After Server started and running, create using always

New project > Maven > Java application

Specify in pom.xml wsdl location if not using activemq

using <wsdlUrls> <wsdlUrl> http://localhost:9000/bank?wsdl </wsdlUrl></wsdlUrls>


#Port already in use
Use fuser linux utility:
$ fuser 9000/tcp -k 
