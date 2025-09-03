package org.example.cs.linkedlist.IntersectionTwoLinkedLists;

import org.example.cs.linkedlist.ListNode;

/**
 * See detailed documentation in README.md in this folder.
 */
public class IntersectionTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    public static void main(String[] args) {
        IntersectionTwoLinkedLists intersectionofTwoLinkedLists = new IntersectionTwoLinkedLists();
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(8);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = headA.next.next;
        System.out.println(intersectionofTwoLinkedLists.getIntersectionNode(headA, headB).val); // Output: 8
    }
}
