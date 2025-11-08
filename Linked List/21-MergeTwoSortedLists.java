// LeetCode #21
// Approach: At first I came up with sorting logic without the edge cases
//           where list1 == null || list2 == null and get nullpoint error 
// Date:

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
        ListNode head = null;

        //if both lists are null, return null
        if (list1 == null && list2 == null) {
            return head;
        }

        else if (list1 == null) {
            return list2;
        }

        else if (list2 == null) {
            return list1; 
        }
        
        //if one of the list is null, return head of the other list 
        if (list1.val <= list2.val) {
            head = list1;
        } else {
            head = list2; 
        }

        ListNode curr1 = list1;
        ListNode curr2 = list2; 

        //while curr1 != ull && curr2 != null, not the val 
        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                ListNode temp = curr1.next;
                curr1.next = curr2;
                curr1 = temp;
            }

            else if (curr1.val >= curr2.val) {
                ListNode temp = curr2.next;
                curr2.next = curr1;
                curr2 = temp;
            }
        }

        return head;
        
    }
};