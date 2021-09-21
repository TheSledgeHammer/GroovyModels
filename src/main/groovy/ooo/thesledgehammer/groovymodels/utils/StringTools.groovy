/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ooo.thesledgehammer.groovymodels.utils

class StringTools {
/*
    static String formatTextColor(String name, TextFormatting color) {
        int length = name.length();

        if (length < 1) {
            return "";
        }
        String output = "";
        output = output + color + name;
        return output;
    }

    static String stringToRainbow(String name, boolean isBlack) {
        int length = name.length();

        if (length < 1) {
            return "";
        }
        String output = "";
        ArrayList<TextFormatting> colorChars = new ArrayList<>();
        colorChars.add(TextFormatting.RED);
        colorChars.add(TextFormatting.GOLD);
        colorChars.add(TextFormatting.YELLOW);
        colorChars.add(TextFormatting.GREEN);
        colorChars.add(TextFormatting.AQUA);
        colorChars.add(TextFormatting.BLUE);
        colorChars.add(TextFormatting.LIGHT_PURPLE);
        colorChars.add(TextFormatting.DARK_PURPLE);

        for(int i = 0; i < length; i++) {
            output = output + colorChars.get(i % 8) + name.substring(i, i + 1);
        }
        if(isBlack) {
            return output + TextFormatting.BLACK;
        }
        return output + TextFormatting.WHITE;
    }

    static String stringToRainbow(String name) {
        return stringToRainbow(name, false);
    }
*/
    static String[] split(String name, String regex) {
        String[] arr = name.split(regex);
        return arr;
    }

    static String regex(String name, String toSearch, String replacement) {
        if(name.contains(toSearch)) {
            String replace = name.replace(toSearch, replacement);
            return replace
        }
        return null;
    }

    static String regexFirst(String name, String toSearch, String replacement) {
        if(name.contains(toSearch)) {
            String replace = name.replaceFirst(toSearch, replacement);
            return replace
        }
        return null;
    }

    static String SubString(String name, String to) {
        String newName = "";
        if(name.contains(to)) {
            int idx = name.indexOf(to);
            newName = name.substring(idx);
        }
        return newName;
    }

    static String stringToEnum(String name, Class clazz) {
        String lowerCase = name.toLowerCase();
        for(int i = 0; i < clazz.enumConstants.size(); i++) {
            if(lowerCase.contains(clazz.enumConstants[i].toString())) {
                lowerCase = clazz.enumConstants[i].toString();
                return lowerCase;
            }
        }
        return null
    }

    static boolean contains(String contents, String name) {
        if(contents.contains(name)) {
            return true;
        }
        return false;
    }

    static boolean containsDigit(String name) {
        for(int i = 0; i < name.length(); i++) {
            if(Character.isDigit(name.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    static String getDigitFromString(String name) {
        String digit = "";

        for(int i = 0; i < name.length(); i++) {
            if(Character.isDigit(name.charAt(i))) {
                int idx = Character.getNumericValue(name.charAt(i));
                digit = name.substring(0, idx)
            }
        }
        //Removes Space if Present
        if(digit.contains(' ')) {
            int idx = name.indexOf(' ');
            digit = digit.substring(0, idx);
        }
        return digit;
    }

    static int getIntegerFromString(String name) {
        return Integer.valueOf(getDigitFromString(name));
    }

    static float getFloatFromString(String name) {
        return Float.valueOf(getDigitFromString(name));
    }

    static long getLongFromString(String name) {
        return Long.valueOf(getDigitFromString(name));
    }

    static double getDoubleFromString(String name) {
        return Double.valueOf(getDigitFromString(name));
    }

    static byte getByteFromString(String name) {
        return Byte.valueOf(getDigitFromString(name));
    }

    static boolean getBooleanFromString(String name) {
        return Boolean.valueOf(name);
    }
}
