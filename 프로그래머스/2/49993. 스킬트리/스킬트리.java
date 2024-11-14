import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Set<String> skillSet = getValidSkillSet(skill);
        
        for (String skill_tree : skill_trees) {
            String newSkillTree = getNewSkillTree(skill, skill_tree);

            if (isValidSkillTree(skillSet, newSkillTree)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private Set<String> getValidSkillSet(String skill) {
        Set<String> skillSet = new HashSet<>();
        
        for (int i = 1; i <= skill.length(); i++) {
            skillSet.add(skill.substring(0, i));
        }
        
        return skillSet;
    }
    
    private String getNewSkillTree(String skill, String skill_tree) {
        StringBuilder sb = new StringBuilder();
        
        for (char ch : skill_tree.toCharArray()) {
            if (skill.indexOf(ch) != -1) {
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
    
    private boolean isValidSkillTree(Set<String> skillSet, String newSkillTree) {
        return skillSet.contains(newSkillTree) || newSkillTree.isEmpty();
    }
}