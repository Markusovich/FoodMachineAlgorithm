package source;

import java.util.Random;

public class LinkedList<T> {
	
	@SuppressWarnings("hiding")
	private class Node<T> {  //Create node class for linked list
		private T data;  //Part of node that carries the data
		private Node<T> next;  //Part of node that contains reference to next node
		
		private Node(T dataPortion) {  //Constructor for creating a new node
			this(dataPortion, null);
		}
		private Node(T dataPortion, Node<T> nextNode) {
			data = dataPortion;
			next = nextNode;
		}
		
		private T getNodeData() {  //Gets data portion of current node
			return data;
		}
		@SuppressWarnings("unused")
		private void setNodeData(T newEntry) {  //Sets data portion
			data = newEntry;
		}
		private Node<T> getNextNode() {  //Reference to next node
			return next;
		}
		private void setNextNode(Node<T> newNode) {  //Sets next node
			next = newNode;
		}
		
	}
	
	private Node<T> firstNode;  //Node representing the head
	private Node<T> currentNode;
	private int numberOfEntries;
	public LinkedList() {  //Constructor
		firstNode = null;
		currentNode = null;
		numberOfEntries = 0;
	}
	
	public int getCurrentSize() {
		return numberOfEntries;
	}
	public int changeCurrentSize(int increment) {
		numberOfEntries = numberOfEntries + increment;
		return numberOfEntries;
	}
	public boolean isEmpty() {
		if(firstNode == null  && numberOfEntries == 0) {
			return true;
		}
		
		return false;
	}
	public boolean addAtHead(T newEntry) {
		
		Node<T> newNode = new Node<T>(newEntry);
		
		if(firstNode == null) {
			firstNode = newNode;
		}
		else {
			newNode.setNextNode(firstNode);
			firstNode = newNode;
		}
		numberOfEntries++;
		return true;
	}
	public boolean addAtTail(T newEntry) {
		
		Node<T> newNode = new Node<T>(newEntry);
		
		if(firstNode == null) {
			firstNode = newNode;
		}
		else {
			currentNode = firstNode;
			while(currentNode.getNextNode() != null) {
				currentNode = currentNode.getNextNode();
			}
			currentNode.setNextNode(newNode);
		}
		numberOfEntries++;
		return true;
	}
	public T getHead() {
		return firstNode.getNodeData();
	}
	public T getTail() {
		currentNode = firstNode;
		while(currentNode.getNextNode() != null) {
			currentNode = currentNode.getNextNode();
		}
		return currentNode.getNodeData();
	}
	public T removeHead() {
		
		if(firstNode == null) {
			return null;
		}
		
		Node<T> removedNode = firstNode;
		firstNode = firstNode.getNextNode();
		numberOfEntries--;
		
		return removedNode.getNodeData();
	}
	public T removeTail() {
		
		currentNode = firstNode;
		if(currentNode == null) {
			return null;
		}
		
		if(currentNode.getNextNode() == null) {
			currentNode = null;
			return currentNode.getNodeData();
		}
		
		while(currentNode.getNextNode().getNextNode() != null) {
			currentNode = currentNode.getNextNode();
		}
		
		Node<T> removedNode = currentNode.getNextNode();
		currentNode.setNextNode(null);
		numberOfEntries--;
		
		return removedNode.getNodeData();
	}
	public T remove() {
		
		if(firstNode == null) {
			return null;
		}
		
		Random rand = new Random();
		int num = rand.nextInt(numberOfEntries);
		
		if(num == 0) {
			T temp = removeHead();
			return temp;
		}
		
		if(num == numberOfEntries - 1) {
			T temp = removeTail();
			return temp;
		}
		
		currentNode = firstNode;
		for(int i = 0; i < num-1; i++) {
			currentNode = currentNode.getNextNode();
		}
		
		Node<T> removedNode = currentNode.getNextNode();
		currentNode.setNextNode(removedNode.getNextNode());
		numberOfEntries--;
		
		return removedNode.getNodeData();
	}
	public boolean remove(T anEntry) {
		
		if(anEntry.equals(firstNode.getNodeData())) {
			removeHead();
			return true;
		}
		
		currentNode = firstNode;
		
		while(currentNode.getNextNode() != null) {
			if(anEntry.equals(currentNode.getNextNode().getNodeData())) {
				Node<T> removedNode = currentNode.getNextNode();
				currentNode.setNextNode(removedNode.getNextNode());
				numberOfEntries--;
				
				return true;
				
			}
			currentNode = currentNode.getNextNode();
		}
		return false;
	}
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}
	public int getFrequencyOf(T anEntry) {
		
		currentNode = firstNode;
		int count = 0;
		
		if(anEntry.equals(currentNode.getNodeData())) {
			count++;
		}
		
		while(currentNode.getNextNode() != null) {
			currentNode = currentNode.getNextNode();
			if(anEntry.equals(currentNode.getNodeData())) {
				count++;
			}
		}
		return count;
	}
	public boolean contains(T anEntry) {
		
		currentNode = firstNode;
		
		if(anEntry.equals(currentNode.getNodeData())) {
			return true;
		}
		
		while(currentNode.getNextNode() != null) {
			currentNode = currentNode.getNextNode();
			if(anEntry.equals(currentNode.getNodeData())) {
				return true;
			}
		}
		return false;
	}
	public int getIndex(T anEntry) {
		if(!contains(anEntry)) {
			return -1;
		}
		
		currentNode = firstNode;
		
		if(anEntry.equals(currentNode.getNodeData())) {
			return 0;
		}
		
		int i = 1;
		
		while(currentNode.getNextNode() != null) {
			currentNode = currentNode.getNextNode();
			if(anEntry.equals(currentNode.getNodeData())) {
				return i;
			}
			i++;
		}
		return -1;
	}
	public void roll() {
		
		currentNode = firstNode;
		
		if(firstNode == null) {
			System.out.println("List is empty");
			return;
		}
		
		while(currentNode != null) {
			System.out.println(currentNode.getNodeData());
			currentNode = currentNode.getNextNode();
		}
		
		return;
	}
	public boolean addAtIndex(T newEntry, int index) {
		if(index < 0 || index > numberOfEntries) {
			return false;
		}
		if(index == 0) {
			addAtHead(newEntry);
			return true;
		}
		if(index == numberOfEntries) {
			addAtTail(newEntry);
			return true;
		}
		Node<T> newNode = new Node<T>(newEntry);
		currentNode = firstNode;
		for(int i = 0; i < index-1; i++) {
			currentNode = currentNode.getNextNode();
		}
		Node<T> currentNext = currentNode.getNextNode(); 
		currentNode.setNextNode(newNode);
		newNode.setNextNode(currentNext);
		numberOfEntries++;
		return true;
	}
	public boolean removeAtIndex(int index) {
		if(index < 0 || index > numberOfEntries) {
			return false;
		}
		if(index == 0) {
			removeHead();
			return true;
		}
		if(index == numberOfEntries) {
			removeTail();
			return true;
		}
		currentNode = firstNode;
		for(int i = 0; i < index-1; i++) {
			currentNode = currentNode.getNextNode();
		}
		currentNode.setNextNode(currentNode.getNextNode().getNextNode());
		numberOfEntries--;
		return true;
	}
	public T getAtIndex(int index) {
		if(index < 0 || index > numberOfEntries-1) {
			return null;
		}
		if(index == 0) {
			getHead();
		}
		if(index == numberOfEntries-1) {
			getTail();
		}
		currentNode = firstNode;
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.getNextNode();
		}
		return currentNode.getNodeData();
	}
	
}
