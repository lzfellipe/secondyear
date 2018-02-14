#include <pthread.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <math.h>
#include <curses.h>

#define NUM_THREADS 6 
#define N 1000

//Purpose of this program is to perform integration of the function f(x) = 4 / 1 + x^2 over the integral
//from limits 0 to 1

float y[20], x[20], so, se, h, x0, xn;
int n, i;

struct integration_struct	{
    //Or whatever information that you need
    int i;
    float h;
};

//Method returns desired function result for input x
float f(float x)
{
    return(4/(1+pow(x,2)));
}

//Integration by parts (threaded)
void *integrate(void *args){
	
	struct integration_struct* actual_args = args;
	int iz = actual_args->i;
	float hz = actual_args->h;
	
    printf("i = %i\n",iz);
	printf("h = %f\n",hz);
	x[iz]=x0+((iz)*(hz));
	printf("x[i=%i] = %f\n",iz,x[iz]);
	y[iz]=f(x[iz]);
	printf("f(x[i=%i]) = %f\n\n",iz,y[iz]);
	free(actual_args);
	return 0;
}

int main (int argc, const char * argv[]) { 
	
	float ans;
	int t, rc;

	//Upper and lower limits
	x0 = 0;
	xn = 1;
	h = 0.1;
	i = 0;
    n=(xn-x0)/h;
    printf("%i",n);
	
	pthread_t threads[n];
	
    if(n%2==1)
    {
        n=n+1;
    }

    h=(xn-x0)/n;	
	
	//Create threads and perform integration
	for (t=0;t<=n;t++) { 
		struct integration_struct *args = malloc(sizeof *args);
        args->i = t;
		args->h = h;
		printf("Creating thread %d\n",t); 
		rc = pthread_create(&threads[t],NULL,integrate,args); 
		if (rc) { 
			printf("ERROR return code from pthread_create(): %d\n",rc); 
			exit(-1); 
		} 
	}

	// wait for threads to exit 
	for(t=0;t<n;t++) { 
		pthread_join( threads[t], NULL); 
		printf("Thread %i exited\n",t);
	}	 	

    so=0;
    se=0;

	//Seperate even & odd indexed elements
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