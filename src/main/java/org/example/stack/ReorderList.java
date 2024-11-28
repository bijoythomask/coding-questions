package org.example.stack;

/**
 * You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find the middle of the linked list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the linked list
        ListNode prev = null, curr = slow, tempNext;
        while (curr != null) {
            tempNext = curr.next;
            curr.next = prev;
            prev = tempNext;
            curr = tempNext;
        }

        // Step 3: Merge the two halves
        ListNode first = head, second = prev;
        while (second.next != null) {
            tempNext = first.next;
            first.next = second;
            first = tempNext; // Move first to the next node

            tempNext = second.next;
            second.next = first;
            second = tempNext;// Move second to the next node
        }
    }
    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        reorderList.reorderList(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
            
        }
    }
}
