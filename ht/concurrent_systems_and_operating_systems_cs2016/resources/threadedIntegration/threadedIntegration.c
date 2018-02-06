#include <pthread.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <math.h>
#include <curses.h>

#define NUM_THREADS 6 
#define N 1000

//Purpose of this program is to perform integration of the function f(x) = 4 / 1 + x^2 over the integral
//from limits 0 to 1

void *PrintHello(void *threadid) { 
	printf("\n%d: Hello World!\n", threadid); 
	pthread_exit(NULL); 
} 

int main (int argc, const char * argv[]) { 
	
	/*pthread_t threads[NUM_THREADS]; 
	int rc,t; 
	for (t=0;t<NUM_THREADS;t++) { 
		printf("Creating thread %d\n",t); 
		rc = pthread_create(&threads[t],NULL,PrintHello,(void *)t); 
		if (rc) { 
			printf("ERROR return code from pthread_create(): %d\n",rc); 
			exit(-1); 
		} 
	} 
	// wait for threads to exit 
	for(t=0;t<NUM_THREADS;t++) { 
		pthread_join( threads[t], NULL); 
	} 
	return 0;
	*/

	doIntegration();
	return 0;

}


float f(float x)
{
    return(4/(1+pow(x,2)));
}


void doIntegration()
{
    int i,n;
    float x0,xn,h,y[20],so,se,ans,x[20];
	
	x0 = 0;
	xn = 1;
	h = 0.1;
    n=(xn-x0)/h;

    if(n%2==1)
    {
        n=n+1;
    }

    h=(xn-x0)/n;	
	
	//Do integration by parts
    for(i=0; i<=n; i++)
    {
        x[i]=x0+i*h;
        y[i]=f(x[i]);
        printf("\n%f\n",y[i]);
    }
    so=0;
    se=0;

    for(i=1; i<n; i++)
    {
        if(i%2==1)
        {
            so=so+y[i];
        }
        else
        {
            se=se+y[i];
        }
    }

    ans=h/3*(y[0]+y[n]+4*so+2*se);
    printf("\nfinal integration is %f \n",ans);
    getch();
}
