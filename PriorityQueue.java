package org.gradle;

//Priority Queue Implementation using Binary Heap

public class PriorityQueue {
	
	//Total no of elements a heap can have
	private static final int MAX_SIZE=12;
	//No of elements in heap
	private int size=10;
	//root will be stored at index 1
	private int heap[]=new int[MAX_SIZE+1];
	private static final int INFINITE=999999999;
	private int removedItem;
	
	private int getParent(int index){
		return index/2;
	}
	
	private int getLeftChild(int index){
		return 2*index;
	}

	private int getRightChild(int index){
		return 2*index+1;
	}cup
	
	private void shiftUp(int index){
		while(index>1 && heap[getParent(index)]<heap[index]){
			int temp=heap[getParent(index)];
			heap[getParent(index)]=heap[index];
			heap[index]=temp;
			index=getParent(index);
		}
	}
	
	private void insert(int item){
		if(size== MAX_SIZE){
			throw new ArrayIndexOutOfBoundsException("Heap is Full");
		}else{
			size=size+1;
			heap[size]=item;
			shiftUp(size);
		}
	}
	
	private int extractMax(){
		int maxItem=heap[1];
		if(heap[1]==INFINITE){
			maxItem=removedItem;
		}
		heap[1]=heap[size];
		size=size-1;
		shiftDown(1);
		return maxItem;
	}
	//can be same as ExtractMax
	private int remove(int index){
		removedItem=heap[index];
		heap[index]=INFINITE;
		shiftUp(index);
		return extractMax();
	}
	
	private void changePriority(int index,int newItem){
		int oldItem=heap[index];
		heap[index]=newItem;
		if(newItem>oldItem){
			shiftUp(index);
		}
		if(newItem<oldItem){
			shiftDown(index);
		}
	}
	
	private void shiftDown(int index){
		int maxIndex=index;
		int left=getLeftChild(index);
		if(left<=size && heap[left]>heap[maxIndex]){
			maxIndex=left;
		}
		int right=getRightChild(index);
		if(right<=size && heap[right]>heap[maxIndex]){
			maxIndex=right;
		}
		if(index!=maxIndex){
			int temp=heap[index];
			heap[index]=heap[maxIndex];
			heap[maxIndex]=temp;
			shiftDown(maxIndex);
		}
	}
}
