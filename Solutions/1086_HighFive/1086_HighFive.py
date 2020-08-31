from heapq import heappush, heappop

class Solution:
    def highFive(self, items: List[List[int]]) -> List[List[int]]:
        """
        Edge Case 1 - Empty input:
            - Return empty list
        
        Array-based approach:
            - Sort the whole array by the following: O(m*klogm*k)
                - First, by score
                - Second, by student ID
            - Get top 5 results for each student (ignore the rest of the scores) O(n)
            - Calculate average based on top 5 results O(1)
            - Return average for each student
            
        Heap Time-optimized:
            - Build a heap for each student, assuming they have k scores O(mlogk)
            - Poll from each PQ, 5 times O(mlogk)
            - Calculate average for each student and return O(1)
            
        Heap Space-optimized:
            - Keep going through array, but reuse same PQ for each student
        """
        studentDict = {}
        
        # Create student heaps
        for scoreSet in items:
            studentID = scoreSet[0]
            score = scoreSet[1]
            
            # Create heap for student if not present
            if studentID not in studentDict:
                studentDict[studentID] = []
                
            # Add new score to the student's heap
            heappush(studentDict[studentID], -1*score)
            
        # Get averages for each student
        resultDict = {}
        for student in studentDict:
            studentHeap = studentDict[student]
            total = 0
            for _ in range(5):
                total += -1*heappop(studentHeap)
                
            # Update result dict
            resultDict[student] = total//5
            
        return resultDict.items()
            
                
            
