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
        // If we want to do this iteratively, but still postorder traversal, then we can use a stack to replicate the recursive output behaviour.
        // DFS essentially.
        if(root == null){
            return new ArrayList<Integer>();
        }
        List<Integer> solution = new ArrayList<Integer>();
        
        // lmao whats a stack in java again.
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        stack.push(root);
        Set<TreeNode> hasSeen = new HashSet<TreeNode>();
        // By right I should push everything in first.
        while(!stack.isEmpty()){
            TreeNode currNode = stack.pop();
            if(currNode != null ){
                stack.push(currNode.left);
                stack.push(currNode.right);
                if( !hasSeen.contains(currNode)){
                    solution.add(currNode.val);
                    hasSeen.add(currNode);
                }
            }
        }
        // this ended up being a preorder traversal. Will try to do again.
        Collections.reverse(solution);
        return solution;
        
    }
}