// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2018
 */

 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){

        int n = a.length;

		//Iterate through input array
		for(int i=0; i<n; i++)
		{		
			double temp = a[i];
			//Compare a[i] with a[i-1]
			int j = i-1;
			
			//Iterate through all possible comparisons and swap accordingly	
			while(j>=0 && a[j] < temp)
			{
				a[j+1] = a[j];
				j = j-1;
			}
			a[j+1] = temp;
		}
		return a;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Method implemented using https://www.programcreek.com/2012/11/quicksort-array-in-java/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
	
    	int low = 0;
    	int high = a.length-1;
    	
    	qSort(a, low, high);
    	return a;
    }
    
    /**
     * This method implements quickSort recursively for a given array of doubles.
     * @param a: Unsorted array of doubles.
     * @param low: Low index.
     * @param high: High index.
     */
    private static void qSort(double a[], int low, int high) {
    	
    	if (a == null || a.length == 0)
			return;
 
		if (low >= high)
			return;
 
		//Find pivot value
		int middle = low + (high - low) / 2;
		double pivot = a[middle];
		
		//Sort values < pivot to left and > pivot to right
		int i = low;
		int j = high;
		
		while(i<=j) {
			//Find low value !< pivot
			while (a[i] < pivot) {
				i++;
			}
			//Find high value !> pivot
			while(a[j] > pivot) {
				j--;
			}
			
			if(i <= j) {
				double tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				i++;
				j--;
			}
		}
		
		//Recursively quicksort two sub parts
		if(low < j)
			qSort(a, low, j);
		
		if(high > i)
			qSort(a, i, high);
    	
    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] mergeSort (double a[]){

		 int low = 0;
		 int high = a.length - 1;
		 
		 double tmp[] = new double[a.length];
		 
		 mSort(a, tmp, low, high);
		 return a;
	
    }
    
    /**
     * Performs mergesort recursively within the given indices low and high
     * @param a: An unsorted array of doubles
     * @param tmp: Temp array to be used for the mergesort.
     * @param low: Lower index
     * @param high: Higher index
     */
    private static void mSort(double a[], double tmp[], int low, int high) {
    	
    	//If low is smaller than high array is unsorted
		if(low < high) {
			//Get index of middle element
			int middle = low + (high - low) / 2;
			//Sort left array
			mSort(a, tmp, low, middle);
			//Sort right array
			mSort(a, tmp, middle + 1, high);
			//Combine them both
			merge(a, tmp, low, middle, high);
			
		 }
    	
    }
    
    /**
     * Combines recursive mergesorts into the one larger array
     * Algorithm implemented using http://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html
     * @param a: An unsorted array of doubles.
     * @param tmp: Temp array to be used in the mergesort.
     * @param low: Index of lower element.
     * @param middle: Index of middle element.
     * @param high: Index of higher element.
     */
    private static void merge(double a[], double tmp[], int low, int middle, int high) {
    	
    	 // Copy both parts into the tmp array
        for (int i = low; i <= high; i++) {
            tmp[i] = a[i];
        }
        
        int i = low;
        int j = middle + 1;
        int k = low;
        
    	// Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (tmp[i] <= tmp[j]) {
                a[k] = a[i];
                i++;
            } else {
               a[k] = tmp[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            a[k] = tmp[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.
    	
    }

    /**
     * Sorts an array of doubles using Shell Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/shellsort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] shellSort (double a[]){

		 int n = a.length;
		 
		 //Start with a gap of a.length/2, keep reducing by half
		 for(int gap = n/2; gap > 0; gap /= 2) {
			 
			 //Perform insertion sort within the gaps 
			 for(int i = gap; i < n; i += 1) {
				
				 //Store a[i] into tmp
				 double tmp = a[i];
				 
				 //Shift earlier gap-sorted elements up until a[i] position is found
				 int j;
				 for(j=i; j>=gap && a[j-gap] > tmp; j -= gap) {
					 
					 a[j] = a[j-gap];
					 
				 }
				 
				 //Place tmp in its correct location
				 a[j] = tmp;
			 }
		 }
		 return a;
    }

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/selection-sort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){

         int n = a.length;
         double tmp;
         //Increment the unsorted array index by one
         for(int i = 0; i < n-1; i++) {
        	 //Find index of min element 
        	 int min = i;
        	 for(int j = i+1; j < n; j++) {
        		 if(a[j] < a[min])
        			 min = j;
        	 }
        	 
        	 //Swap the found min with the element a[i]
        	 tmp = a[min];
        	 a[min] = a[i];
        	 a[i] = tmp;
         }
         return a;

    }

    /**
     * Sorts an array of doubles using Bubble Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/bubble-sort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] bubbleSort (double a[]){

         int n = a.length;
         int i, j;
         double tmp;
         boolean swapped;
         
         //Increment over entire array
         for(i = 0; i < n; i++) {
        	 
        	 swapped = false;
        	 for(j = 0; j < n-i-1; j++) {
        		 
        		 //Swap a[j] with a[j+1] if larger
        		 if(a[j] > a[j+1]) {
        			 tmp = a[j];
        			 a[j] = a[j+1];
        			 a[j+1] = tmp;
        			 swapped = true;
        		 }
        	 }
        	 
        	 //If no elements swapped during inner loop, break
        	 if(!swapped)
        		 break;
         }
         return a;
		 
    }


    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

 }//end class
