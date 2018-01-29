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
		int temp = a[i];
		//Compare a[i] with a[i-1]
		int j = i-1;
		
		//Iterate through all possible comparisons and swap accordingly	
		while(j>=0 && a[j] < temp)
		{
			a[j+1] = a[j]
			j = j-1;
		}
		a[j+1] = temp;
		return a;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
	
		sort(a, 0, a.length-1);
		
		private static void sort(double a[], int lo, int hi){

			if(hi <= lo)
				return;

			int j = partition(a, low, high);

			//Sort left partition
			sort(a, low, j-1);

			//Sort right partition
			sort(a, j+1, high);
		
		}
	
		private static int partition(double a[], int lo, int hi){

			int pivot = arr[hi];
			int i = (lo-1);

			for(int j=lo; j<hi; j++)
			{
				if(a[j] <= pivot)
				{
					i++;
					
					//Swap a[i] and a[j]
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}

			int temp = a[i+1];
			a[i+1] = a[hi];
			a[hi] = temp;
			
			return i+1;
		}

    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] mergeSort (double a[]){

		 //todo: implement the sort
	
    }//end mergesort

    /**
     * Sorts an array of doubles using Shell Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] shellSort (double a[]){

		 //todo: implement the sort
		 
    }//end shellsort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){

         //todo: implement the sort

    }//end selectionsort

    /**
     * Sorts an array of doubles using Bubble Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] bubbleSort (double a[]){

         //todo: implement the sort
		 
    }//end bubblesort


    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

 }//end class

