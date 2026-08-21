import java.util.*;

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        long sum = 0;
        long maxSum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            if (right - left + 1 > k) {
                int leftElement = nums[left];

                sum -= leftElement;

                map.put(leftElement, map.get(leftElement) - 1);

                if (map.get(leftElement) == 0) {
                    map.remove(leftElement);
                }

                left++;
            }

            if (right - left + 1 == k && map.size() == k) {
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}