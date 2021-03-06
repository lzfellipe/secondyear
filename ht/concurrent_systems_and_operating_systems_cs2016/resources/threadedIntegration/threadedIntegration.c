#include <pthread.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <math.h>
#include <curses.h>

//Purpose of this program is to perform integration of the function f(x) = 4 / 1 + x^2 over the integral
//from limits 0 to 1

float y[20], x[20], sumOfOddIndexElements, sumOfEvenIndexElements, sizeOfIntervals, lowerLimit, upperLimit;
int intervalCount, i;

//Struct used to house parameters for integration by parts
struct integration_struct	{
    //Or whatever information that you need
    int i;
    float sizeOfIntervals;
};

//Method returns desired function result for input x
float f(float x)
{
    return(4/(1+pow(x,2)));
}

//Integration by parts (threaded)
void *integrate(void *args){
	
	struct integration_struct* actual_args = args;
	int index = actual_args->i;
	float hz = actual_args->sizeOfIntervals;
	
    printf("i = %i\n",index);
	printf("sizeOfIntervals = %f\n",hz);
	x[index]=lowerLimit+((index)*(hz));
	printf("x[i=%i] = %f\n",index,x[index]);
	y[index]=f(x[index]);
	printf("f(x[i=%i]) = %f\n\n",index,y[index]);
	free(actual_args);
	return 0;
}

int main (int argc, const char * argv[]) { 
	
	int t, returnCode;

	//Upper and lower limits
	lowerLimit = 0;
	upperLimit = 1;
	sizeOfIntervals = 0.1;
	i = 0;
    intervalCount=(upperLimit-lowerLimit)/sizeOfIntervals;
    printf("Amount of intervals = %i",intervalCount);
	
	pthread_t threads[intervalCount];
	
    if(intervalCount%2==1)
    {
        intervalCount=intervalCount+1;
    }

    sizeOfIntervals=(upperLimit-lowerLimit)/intervalCount;	
	
	//Create threads and perform integration
	for (t=0;t<=intervalCount;t++) { 
	    //Create struct to hold arguments for thread
		struct integration_struct *args = malloc(sizeof *args);
		
		//Pass arguments to struct
        args->i = t;
		args->sizeOfIntervals = sizeOfIntervals;
		
		printf("Creating thread number %i\n",t); 
		
		//Create integration thread and pass arguments for current interval
		returnCode = pthread_create(&threads[t],NULL,integrate,args); 
		if (returnCode) { 
			printf("ERROR return code from pthread_create(): %d\n",returnCode); 
			exit(-1); 
		} 
	}

	//Wait for threads to exit
	for(t=0;t<intervalCount;t++) { 
		pthread_join(threads[t], NULL); 
		printf("Threads exited");
	}	 	

    //sumOfOddIndexElements = s
    sumOfOddIndexElements=0;
    sumOfEvenIndexElements=0;

	//Seperate even & odd indexed elements
    for(i=1; i<intervalCount; i++)
    {
        if(i%2==1)
        {
            sumOfOddIndexElements=sumOfOddIndexElements+y[i];
        }
        else
        {
            sumOfEvenIndexElements=sumOfEvenIndexElements+y[i];
        }
    }

    //Calculate final answer
    float ans=sizeOfIntervals/3*(y[0]+y[intervalCount]+4*sumOfOddIndexElements+2*sumOfEvenIndexElements);
    printf("\nFinal integration result is: %f \n",ans);
    getch();
}
