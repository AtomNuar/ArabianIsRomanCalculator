import java.util.Scanner;

public class CalculatorKata {
    static String inputUser;
    static int numberOne;
    static int numberTwo;
    static char operator;

    public static void main(String[] args) throws Exception {
        printStart();
        calc(inputUser);
        resultPrint();
    }

    private static void printStart() {
        System.out.println("Калькулятор выполняет операции только с римскими или только с арабскими числами.");
        System.out.println("На вход подаются числа [1 - 10] или [I - X].");
        System.out.println("Водд данных строго через пробел! Пример: [2 + 2] или [V - II].");
        System.out.print("Введите арифметическое выражение: ");
    }

    private static void resultPrint() {
        int result = calculate(numberOne, numberTwo, operator);
        if (isRoman(inputUser.split(" ")[0])) {
            if (result < 0) {
                throw new RuntimeException("В римской системе нет отрицательных чисел");
            }
            System.out.println("Результат: " + arabicToRoman(result));
        } else {
            System.out.println("Результат: " + result);
        }
    }

    public static String calc(String input) throws Exception {
        Scanner scanner = new Scanner(System.in);
        inputUser = scanner.nextLine();
        stringSplit();
        return input;
    }

    private static void stringSplit() throws Exception {

        String[] inputUserSplit = inputUser.split(" ");
        if (inputUserSplit.length > 3)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (inputUserSplit.length <= 1)
            throw new ArrayIndexOutOfBoundsException("Строка не является математической операцией");

        if (isRoman(inputUserSplit[0])) {
            numberOne = romanToArabic(inputUserSplit[0]);
        } else {
            try {
                numberOne = Integer.parseInt(inputUserSplit[0]);
            } catch (NumberFormatException e) {
            }
        }
        if (numberOne > 10 || numberOne <= 0) {
            throw new NumberFormatException("На вход подаются числа [1 - 10] и [I - X]");
        }
        operator = inputUserSplit[1].charAt(0);
        if (isRoman(inputUserSplit[2])) {
            numberTwo = romanToArabic(inputUserSplit[2]);
        } else {
            try {
                numberTwo = Integer.parseInt(inputUserSplit[2]);
            } catch (NumberFormatException ignored) {
            }

        }
        if (numberTwo > 10 || numberTwo <= 0) {
            throw new NumberFormatException("На вход подаются числа [1 - 10] и [I - X]");
        }
        if (!isRoman(inputUserSplit[0])) {
            if (!isRoman(inputUserSplit[0]) && isRoman(inputUserSplit[2])) {
                throw new NumberFormatException("Калькулятор не выполняет операции с арабским и римским числом!");
            }
        } else {
            if (!isRoman(inputUserSplit[2])) {
                throw new NumberFormatException("Калькулятор не выполняет операции с арабским и римским числом!");
            } else if (!isRoman(inputUserSplit[0]) && isRoman(inputUserSplit[2])) {
                throw new NumberFormatException("Калькулятор не выполняет операции с арабским и римским числом!");
            }
        }
    }
        public static int romanToArabic(String input) throws NumberFormatException {
            return switch (input) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> throw new NumberFormatException("Неверное римское число: " + input);
            };
        }

        public static int calculate(int numberOne, int numberTwo, char operator) {
            int result = 0;
            switch (operator) {
                case '+':
                    result = numberOne + numberTwo;
                    break;
                case '-':
                    result = numberOne - numberTwo;
                    break;
                case '*':
                    result = numberOne * numberTwo;
                    break;
                case '/':
                    result = numberOne / numberTwo;
                    break;
                default:
                    throw new NumberFormatException("Неверный оператор: " + operator);
            }
            return result;
        }
    public static boolean isRoman(String input)  {
        String[] romanNumeralLetters = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (String letter : romanNumeralLetters) {
            if (letter.equals(input)) {
                return true;
            }
        }
        return false;
    }


    public static String arabicToRoman(int input) {
        String[] romanNumeralNumbers = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] arabicNumbers = {1, 4, 5, 9, 10, 40, 50, 90, 100};
        StringBuilder result = new StringBuilder();
        int i = arabicNumbers.length - 1;
        while (input > 0) {
            if (input - arabicNumbers[i] >= 0) {
                result.append(romanNumeralNumbers[i]);
                input -= arabicNumbers[i];
            } else {
                i--;

            }
        }
        return result.toString();
    }
}

