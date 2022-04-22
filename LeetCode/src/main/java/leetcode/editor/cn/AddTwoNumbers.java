package leetcode.editor.cn;

/**
题目名字---:两数相加
题目编号---:2	
解题时间---:2022-04-23 00:41:54
*/

public class AddTwoNumbers{
    public static void main(String[] args) {
        System.out.println("test");
        Solution solution = new AddTwoNumbers().new Solution();
        solution.addTwoNumbers(null,null);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        System.out.println("哈哈");
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

}