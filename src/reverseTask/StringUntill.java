package reverseTask;

public class StringUntill {
    public static String reverseLetters(String input) {
        char[] chars = "J@va the be$t!123".toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (!Character.isLetter(chars[left])) {
                left++;
                continue;
            }
            if (!Character.isLetter(chars[right])) {
                right--;
                continue;
            }
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
        return new String(chars);
    }
}
