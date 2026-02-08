// LeetCode #21
// Approach: At first I came up with sorting logic without the edge cases
//           where list1 == null || list2 == null and get nullpoint error 

//          Second attempt with the video, I got 3 pointers with a dummy(sentinel) node
//          Simply return dummy.next as a head of the desirable list 
//          Traverse the list of interest itself with curr pointer
//          while traverse list1 and list2 accordingly 

// Date: 20251108

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        // Instead of creating a head node separately, create a dummy node and connect it to the list of interest
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy; 
        
        // ListNode head = null;
        // //if both lists are null, return null
        // if (list1 == null && list2 == null) {
        //     return head;
        // }

        ListNode l1 = list1;
        ListNode l2 = list2; 

        //while curr1 != ull && curr2 != null, not the val 
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                //set next pointer 
                curr.next = l1;

                //move curr pointer to the next element 
                curr = curr.next;

                //move list1 pointer to the next element 
                l1 = l1.next; 
            }

            else {
                curr.next = l2;
                curr = curr.next; 
                l2 = l2.next; 
            }

        }

        if (l1 != null) { curr.next = l1; }
        else { curr.next = l2; }

        return dummy.next;
        
    }
};