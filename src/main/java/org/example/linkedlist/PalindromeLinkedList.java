package org.example.linkedlist;

/**
 * Given the head of a singly linked list, return true if it is a
 * palindrome
 *  or false otherwise.
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (prev != null && slow != null) {
            if (prev.val != slow.val) {
                return false;
            }
            prev = prev.next;
            slow = slow.next;
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println(palindromeLinkedList.isPalindrome(head)); // Output: true
    }
}
