package heap;

public class DriverProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a[] = {4, 3, 2, 1};
		
		Heap buildHeap = HeapHelper.heapSort(a);
		
		
		for(int i = 0; i <a.length ; i++){
			System.out.print(buildHeap.heapElement[i]+" ");
		}
	}

}
