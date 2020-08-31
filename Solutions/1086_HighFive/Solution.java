// time:    O(n) - array is iterated once and subsequent iterations over individual PQs are O(k) at max
// space:   O(m) - m is the # of students

// idea:    Use a map of studentId to Score Max PQ/Heap to avoid the need to sort anything.

class Solution {
  public static int[][] highFive(int[][] scores) {
    int ID_INDEX = 0;
    int SCORE_INDEX = 1;
    int NUM_TO_AVG = 5;

    // Edge case
    if (scores.length == 0) return new int[0][0];

    Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

    // max heap (ele2 - ele1)
    Comparator<Integer> cmp = new Comparator<>() {
      @Override
      public int compare(Integer ele1, Integer ele2) {
        return ele2 - ele1;
      };
    };

    // populate map and individual heaps
    for (int i = 0; i < scores.length; i++) {
      if (map.containsKey(scores[i][ID_INDEX])) {
        map.get(scores[i][ID_INDEX]).add(scores[i][SCORE_INDEX]);
      } else {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(cmp);
        pq.add(scores[i][SCORE_INDEX]);
        map.put(scores[i][ID_INDEX], pq);
      }
    }

    // ensure studentIds are sorted  
    ArrayList<Integer> sortedKeys = new ArrayList<Integer>(map.keySet()); 
    Collections.sort(sortedKeys);
    int[][] ans = new int[sortedKeys.size()][2];
    for (int j = 0; j < sortedKeys.size(); j++) {
      int id = sortedKeys.get(j);
      PriorityQueue<Integer> pq = map.get(id);
      int sum = 0;
      for (int k = 0; k < NUM_TO_AVG; k++) {
        if (pq.size() == 0) break;
        sum += pq.poll();
      }
      ans[j][ID_INDEX] = id;
      ans[j][SCORE_INDEX] = sum / NUM_TO_AVG;
    }
    return ans;
  }
}