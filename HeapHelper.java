package heap;

public class HeapHelper {
	
	static void maxHeapify(Heap myHeap, int i){
		
		/* This function is used to heapify the element i.e bringing the given
		 * index i to the maximum value. This uses a recursive call to the fn
		 */
		
		// Declaring the variable and getting its left child as well as right child
		// and considering a largest value
		int left = getLeft(i);
		int right = getRight(i);
		int largest = i;
		
		if(left< myHeap.heapSize){
			// If the left children exist i.e the left < heapsize and the children is 
			// greater than the element then assigning the largest variable to the left
			if(myHeap.heapElement[left]>myHeap.heapElement[i])
				largest = left;
		}
		if(right<myHeap.heapSize){
			// If the right children exist i.e right < heapsize and this children is 
			// greater than largest element then assigning that variable to the largest
			if(myHeap.heapElement[right]>myHeap.heapElement[largest])
			    largest = right;
		}
		if(largest != i){
			// If the parent is not the largest one i.e one of its children is largest
			// then exchanging the elements and calling the function recursively to 
			// Accommodate the changed structure of the heap
			left = myHeap.heapElement[i];
			myHeap.heapElement[i] = myHeap.heapElement[largest];
			myHeap.heapElement[largest] = left;
			maxHeapify(myHeap, largest);
		}
	}

	static int getLeft(int i){
		// This function returns the left children of the given element
		// If the index is 0 then it returns i*2 +1 and if the index is
		// 1 as in the book it returns 2*i
		return i*2+1;
	}
	
	static int getRight(int i){
		// This function returns the right children of the parent 
		// if index starts from 1 then it returns 2*i+1 and if it 
		// starts from 0 then it returns 2*i+2
		return 2*i+2;
	}
	
	static int getParent(int i){
		// returns the parent of the children the same applies here
		// if the index starts from 1 then it returns i/2 and if it 
		// starts from 0 then it returns i-1/2 
		// For more details see
		/// http://stackoverflow.com/questions/22900388
		//  /why-in-a-heap-implemented-by-array-the-index-0-is-left-unused
		return (i-1)/2;
	}
	
	static Heap buildMaxHeap(int[] a){
		// This function is used to build a heap 
		Heap heap = new Heap(a);
		// You can check the loop invariant for the heap see the condition
		for(int i = a.length/2-1; i>=0; i--){
			maxHeapify(heap,i);
		}
		// returns a heap build from the given array
		return heap;
	}
	
	static Heap heapSort(int[] a){
		
		// This function is used to sort the built heap
		
		// building a heap from the given array
		Heap buildHeap = buildMaxHeap(a);
		
		int temp = 0;
		// Sorting the heap
		for(int i = a.length-1; i>0; i--){
			// exchanging the top element from the heap
			temp = buildHeap.heapElement[0];
			buildHeap.heapElement[0] = buildHeap.heapElement[i];
			buildHeap.heapElement[i] = temp;
			// moving the top heap element to the sorted array and then
			// performing the heapify operation
			buildHeap.heapSize = buildHeap.heapSize -1;
			maxHeapify(buildHeap,0);
		}
		// returning the sorted heap
		return buildHeap;
	}
	
	static int getMax(Heap h){
		// getting the maximum element which is present at the top of the 
		// heap which is used to implement the maximum priority queue
		return h.heapElement[0];
	}

	static int extractMax(Heap heap){
		if(heap.heapSize<=0){
			System.out.println("Heap Underflow");
		}
		int max = heap.heapElement[0];
		heap.heapElement[0] = heap.heapElement[heap.heapSize-1];
		heap.heapSize = heap.heapSize-1;
		maxHeapify(heap, 0);
		return max;
	}
	
	static void increaseKey(Heap heap, int key, int increseKey){
		// Here, the key is the index to which the key is to be increased
		if(increseKey<heap.heapElement[key]){
			System.out.println("Increase Key is smaller than the current key");
		}
		// Here, heapify function will not work because this will not do the 
		// heapify operation if the parent is greater so move the element up until
		// you got the top or the parent is greater than the children
		heap.heapElement[key] = increseKey;
		while(key>0 & heap.heapElement[getParent(key)]< heap.heapElement[key]){
			
			// exchange the parent and the children
			int temp = heap.heapElement[key];
			heap.heapElement[key] = heap.heapElement[getParent(key)];
			heap.heapElement[getParent(key)] = temp;
			// change the index "key" to the parent of the children
			key = getParent(key);
		}
	}

	static Heap insertMaxHeap(Heap heap, int key){
		// Heap can also be formed by the use of the insertMaxHeap method
		// by inserting all the elements one by one but it will be of the
		// O(square(n)) which will lead to the inefficiency problem
		// although it can be done precisely be the use of the arrayList 
		// Method which needs to refine all the program with arrayList
		int a[] = new int[heap.heapSize+1];
		for(int i = 0; i < heap.heapSize; i++ )
			a[i] = heap.heapElement[i];
	    Heap newHeap = new Heap(a);
	    heap = newHeap;
	    heap.heapElement[heap.heapSize-1] = (int) Double.NEGATIVE_INFINITY;
	    increaseKey(heap, heap.heapSize-1, key);
	    return heap;
	}

	
}
