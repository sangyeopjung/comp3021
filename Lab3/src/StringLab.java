public class StringLab {

    /**
     * @param str The input string
     * @return The reversed string
     */
    public String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Makes all characters before the index value uppercase, makes all characters on the index and afterwards
     * lowercase. See test cases for a better understanding.
     *
     * @param str   The input string
     * @param index All character positions smaller than index must be uppercase. All character positions greater
     *              than index must be lowercase.
     * @return The new string
     */
    public String capitalizeAndMakeLowercase(String str, int index) {
        StringBuilder sb = new StringBuilder();
        if (index >= str.length()) {
            sb.append(str.toUpperCase());
        } else if (index <= 0) {
            sb.append(str.toLowerCase());
        } else {
            sb.append(str.substring(0, index).toUpperCase());
            sb.append(str.substring(index).toLowerCase());
        }
        return sb.toString();
    }

    /**
     * Counts the number of vowels in a string.
     *
     * @param str The input string
     * @return The number of vowels
     */
    public long countVowels(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a'
                    || str.charAt(i) == 'e'
                    || str.charAt(i) == 'i'
                    || str.charAt(i) == 'o'
                    || str.charAt(i) == 'u') {
                count++;
            }
        }
        return count; // YOU also need to modify this line
    }

    /**
     * Removes a certain letter from a string
     *
     * @param str The input string
     * @param a   The letter to remove
     * @return The input string without the specified letter
     */
    public String removeLetter(String str, char a) {
        return str.replaceAll(Character.toString(a), "");
    }

    /**
     * Checks if a string is a palindrome
     *
     * @param str The string to check
     * @return Whether or not the string is a palindrome
     */
    public boolean isPalindrome(String str) {
        for (int i = 0; i < str.length()/2; i++) {
            if (str.charAt(i) != str.charAt(str.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
}
