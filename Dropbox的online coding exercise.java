/*
http://www.mitbbs.com/article_t/JobHunting/32817129.html
Given a pattern and a string input - find if the string follows the same 
pattern and return 0 or 1.
Examples:
1) Pattern : "abab", input: "redblueredblue" should return 1.
2) Pattern: "aaaa", input: "asdasdasdasd" should return 1.
3) Pattern: "aabb", input: "xyzabcxzyabc" should return 0.
*/
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class DFS {
	public static void main(String[] args) {
		DFS obj = new DFS();
		String str = "aaaa";
		String pattern = "aabb";
		int result = obj.validPattern(str, pattern);
		if (result == 1) {
			System.out.println("Match!");
		}
		else {
			System.out.println("Not Match!");
		}
	}

	public int validPattern(String str, String pattern) {
		if (str == null || pattern == null) {
			return 0;
		}
		Map<Character, String> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		int result = validPatternCore(str, pattern, 0, 0, map, set);
		return result;
	}

	private int validPatternCore(String str, 
				     String pattern, 
				     int strIndex, 
				     int patIndex, 
				     Map<Character, String> map,
				     Set<String> set) {
		if (strIndex == str.length() && patIndex == pattern.length()) {
			return 1;
		}
		if (strIndex == str.length() || patIndex == pattern.length()) {
			return 0;
		}

		char curChar = pattern.charAt(patIndex);
		if (map.containsKey(curChar)) {
			String matchPattern = map.get(curChar);
			for (int i = strIndex + 1; i <= str.length(); i++) {
				if (str.substring(strIndex, i).equals(matchPattern)) {
					return validPatternCore(str, pattern, i, patIndex + 1, map, set);
				}
			}
			return 0;
		}
		else {
			for (int i = strIndex + 1; i <= str.length(); i++) {
				String subStr = str.substring(strIndex, i);
				if (set.contains(subStr)) {
					continue;
				}
				set.add(subStr);
				map.put(curChar, subStr);
				int result = validPatternCore(str, pattern, i, patIndex + 1, map, set);
				if (result == 1) {
					return 1;
				}
				set.remove(subStr);
				map.remove(curChar);
			}
		}
		return 0;
	}
}
