/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int countNodes(TreeNode root) {
        // Complete binary tree.
        // Full Binary tree properties: Num of Nodes = 2^h - 1
        // We check left and right height, if same, then we know the num of nodes for this subtree is 2^h - 1
        // Else, we continue down and return + 1
        // Time Complexity: CheckLeft/CheckRight (lg n)
        // CountNodes (lg n), total = O((lg n)^2)
        if(root == null){
            return 0;
        }
        
        int leftHeight = checkLeft(root);
        int rightHeight = checkRight(root);
        
        if(leftHeight == rightHeight){
            return (int) Math.pow(2,leftHeight) - 1;
            // return (1<<(height)-1); // >> shifts bit wise height times, Similar effect as of multiplying the number with some power of two.
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
        
        
    }
    
    public int checkLeft(TreeNode node){
        if(node == null){
            return 0;
        }
        return checkLeft(node.left) + 1;
    }
    
    public int checkRight(TreeNode node){
        if(node == null){
            return 0;
        }
        return checkRight(node.right) + 1;
    }
}