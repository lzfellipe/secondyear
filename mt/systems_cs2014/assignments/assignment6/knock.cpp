#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <string>


#include <arpa/inet.h>
#include <errno.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <netinet/in.h>
#include <netdb.h>

#include <fstream>
#include <iostream>

using namespace std;


/* Globals */
bool hostflag, portflag, helpflag, webflag, fileflag;

void usage(int n)
{
  string usageString("usage: ./knock -h host -p port [-H] [-w] [-f file]");

  cout << usageString << "\n";
  exit(0);
}

int main(int argc, char **argv)
{
	//Initialise flags to false
	hostflag = portflag = helpflag = webflag = fileflag = false;

	char host[500];
    char request[500];
    char merged_request[1000];
    struct hostent *server;
    struct sockaddr_in serveraddr;
    int port;

	int n,i,j;
	//Process command line args
	for(n=i=1;i<argc;i=n) {
		n++;
		//If arg starts with '-' do this
		if (argv[i][0] == '-' && argv[i][1]) {
		  for(j=1;argv[i][j];j++) {
			switch(argv[i][j]) {
			case 'h':
			  hostflag = true;
			  memcpy(host, argv[2], strlen(argv[2])+1);
 			  cout << "hostflag set to true" << "\n";
			  cout << "hostvalue entered = " << host << "\n";
			  break;
			case 'p':
			  portflag = true;
			  port = atoi(argv[4]);
			  cout << "portflag set to true" << "\n";
			  cout << "portvalue entered = " << port << "\n";
			  break;
			case 'H':
			  helpflag = true;
			  cout << "helpflag set to true" << "\n";
			  break;
			case 'w':
			  webflag = true;
			  memcpy(request, argv[6], strlen(argv[6])+1);
			  cout << "webflag set to true" << "\n";
			  cout << "HTTP get request is : " << request << "\n";	
			  break;
			case 'f':
			  fileflag = true;
			  cout << "fileflag set to true" << "\n";
			  break;
			case '?':
			  helpflag = true;
			  cout << "helpflag set to true" << "\n";
			  break;
			default:
			  //If no flag has been set, then invalid input 
			  if(!hostflag && !portflag && !helpflag && !webflag && !fileflag) {
				  fprintf(stderr,"knock: Invalid argument -`%c'.\n",argv[i][j]);
				  usage(1);
				  exit(1);
				  break;
			  }
			}
		  }
		}
		//If arg doesn't start with '-' do this
		else if(argv[i][0] != '-') {
			for(j=0;argv[i][j];j++) {
	    		if (!strcmp("host", argv[i])) {
	     		  hostflag = true;
			  memcpy(host, argv[2], strlen(argv[2])+1);
 			  cout << "hostflag set to true" << "\n";
			  cout << "hostvalue entered = " << host << "\n";
	     		  break;
	    		}
	    		if (!strcmp("port",argv[i])) {
	    		  portflag = true;
			  port = atoi(argv[4]);
			  cout << "portflag set to true" << "\n";
			  cout << "portvalue entered = " << port << "\n";
	     		  break;
	    		}
				if (!strcmp("help",argv[i])) {
	    		  helpflag = true;
			  cout << "helpflag set to true" << "\n";
	     		  break;
	    		}
				if (!strcmp("web",argv[i])) {
	    		 	 webflag = true;
			 		 memcpy(request, argv[6], strlen(argv[6])+1);
			  		 cout << "webflag set to true" << "\n";
			  		 cout << "HTTP get request is : " << request << "\n";
	     		     break;
	    		}
				if (!strcmp("file",argv[i])) {
	    		  fileflag = true;
    			  cout << "fileflag set to true" << "\n";
	     		  break;
	    		}
				//If no flag has been set, then invalid input 
			  	else if(!hostflag && !portflag && !helpflag && !webflag && !fileflag) {
				  fprintf(stderr,"knock: Invalid argument -`%c'.\n",argv[i][j]);
				  usage(1);
				  exit(1);
				  break;
			  	}
			}
		}
	}

	if(helpflag){
		usage(1);
	}
	
	else{
		int tcpSocket = socket(AF_INET, SOCK_STREAM, 0);
     
    	if (tcpSocket < 0)
        	printf("\nError opening socket");
    	else
        	printf("\nSuccessfully opened socket");
     
    	server = gethostbyname(host);
     
    	if (server == NULL)
    	{
        	printf("gethostbyname() failed\n");
    	}
    	else
    	{
        	printf("\n%s = ", server->h_name);
        	unsigned int j = 0;
        	while (server -> h_addr_list[j] != NULL)
        	{
           		printf("%s", inet_ntoa(*(struct in_addr*)(server -> h_addr_list[j])));
            		j++;
        	}
	}
	printf("\n");
 
    	bzero((char *) &serveraddr, sizeof(serveraddr));
    	serveraddr.sin_family = AF_INET;
 
    	bcopy((char *)server->h_addr, (char *)&serveraddr.sin_addr.s_addr, server->h_length);
     
    	serveraddr.sin_port = htons(port);
     
    	if (connect(tcpSocket, (struct sockaddr *) &serveraddr, sizeof(serveraddr)) < 0)
        	printf("\nError Connecting");
    	else
        	printf("\nSuccessfully Connected");
   
    	bzero(request, 1000);
 
    	sprintf(request, "Get %s HTTP/1.1\r\n Host: %s\r\n \r\n \r\n", request, host);
     
    	printf("\n%s", request);
     
    	if (send(tcpSocket, request, strlen(request), 0) < 0)
        	printf("Error with send()");
    	else
        	printf("Successfully sent html fetch request");
     
    	bzero(request, 1000);
     
    	recv(tcpSocket, merged_request, 999, 0);
    	printf("\n%s", merged_request);
    	printf("\nhello");
     
    	close(tcpSocket);
     
    	return (1);

}
}
