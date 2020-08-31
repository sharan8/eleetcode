/*
Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
60 65 87 91 92 100
76 77 93 97 100
Output: [[1,87],[2,88]]
Explanation: 
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.

restrictions:
1 to 1000 students
items length is 1 to 1000, for each student there are at least 5 scores
means there's 200 students
*/

import java.util.*;

class Solution {
    public static void main(String[] args){
        int[][] result = highFive(new int[][] {{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}});
        for(int[] a : result){
            System.out.println(Arrays.toString(a));
        }

    }
    public static int[][] highFive(int[][] scores) {
        // We could go through the entire scores, maintain a new list for each new student, and add the score in.
        Map<Integer, List<Integer>> scoreMap = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < scores.length; i++){
            List<Integer> studentScore = scoreMap.get(scores[i][0]);
            if(studentScore != null){
                // Add student score into list
                studentScore.add(scores[    i][1]);
            }else{
                List<Integer> tempStudScore = new ArrayList<Integer>();
                tempStudScore.add(scores[i][1]);
                scoreMap.put(scores[i][0], tempStudScore);
            }
        }

        // Treemap can sort a map by key lol TIL
        Map<Integer, List<Integer>> sortedMap = new TreeMap<>(scoreMap);

        int[][] results = new int[sortedMap.size()][2];
        int counter = 0;
        // Now we get the process the top 5 average scores
        for(Map.Entry<Integer, List<Integer>> entry : sortedMap.entrySet()){
            List<Integer> studentScore = entry.getValue();
            Collections.sort(studentScore, Collections.reverseOrder());
            // Retrieve average top 5 value
            int avg = 0;
            for(int i = 0; i < 5 ; i++){
                avg += studentScore.get(i);
            }
            avg = avg / 5;
            results[counter] = new int[] {entry.getKey(), avg};
            counter++;
        }
        return results;
    }
}