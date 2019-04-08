/*
 * A queue is an abstract data type that maintains the order in which elements were added to it, allowing the oldest elements to be removed from the front and new elements to be added to the rear. This is called a First-In-First-Out (FIFO) data structure because the first element added to the queue (i.e., the one that has been waiting the longest) is always the first one to be removed.

A basic queue has the following operations:

Enqueue: add a new element to the end of the queue.
Dequeue: remove the element from the front of the queue and return it.
In this challenge, you must first implement a queue using two stacks. Then process  queries, where each query is one of the following  types:

1 x: Enqueue element  into the end of the queue.
2: Dequeue the element at the front of the queue.
3: Print the element at the front of the queue.
 */
import java.io.*;
import java.util.*;

public class QueueUsingTwoStacks {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        Stack<Integer> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();
        while(t>0)
        {
            int op=sc.nextInt();
            if(op==1)
            {
                s1.push(sc.nextInt());
            }
            if(op==2)
            {
                if(s2.empty())
                {
                while(!s1.empty())
                {
                    s2.push(s1.pop());
                }
                }
                s2.pop();
               
            }
            if(op==3)
            {
                if(s2.empty())
                {
                   while(!s1.empty())
                {
                    s2.push(s1.pop());
                }
                }
                System.out.println(s2.peek());
                

            }
            t--;

        }
    }
}

