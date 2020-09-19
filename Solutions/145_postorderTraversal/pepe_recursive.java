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
    public List<Integer> postorderTraversal(TreeNode root) {
    
        //postorder traversal LRN
        // Since we need to return a list, better create a helper method
        List<Integer> sol = new ArrayList<Integer>();
        postorder(root, sol);
        return sol;
    }
    
    public void postorder(TreeNode node, List<Integer> list){
        if(node == null){
            return;
        }
        
        postorder(node.left, list);
        postorder(node.right,list);
        
        list.add(node.val);
    }
}