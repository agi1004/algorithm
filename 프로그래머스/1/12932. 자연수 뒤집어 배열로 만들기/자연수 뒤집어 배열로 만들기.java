class Solution {
    public int[] solution(long n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n)).reverse();
        return sb.chars()
            .map(c -> Character.getNumericValue(c))
            .toArray();
    }
}