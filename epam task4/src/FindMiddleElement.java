
public class FindMiddleElement {
	Node head; // head of linked list 
    class Node 
    { 
        int data; 
        Node next; 
        Node(int d) 
        { 
            data = d; 
            next = null; 
        } 
    }
    void Middle() 
    { 
        Node slow = head; 
        Node fast = head; 
        if (head != null) 
        { 
            while (fast != null && fast.next != null) 
            { 
                fast= fast.next.next; 
                slow = slow.next; 
            } 
            System.out.println("The middle element is " +  slow.data ); 
        } 
    } 
  

}
