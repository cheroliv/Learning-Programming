/*
 ================================
 Name         : base_exercises.go
 Author       : Charles T.
 ================================
*/

package main

import (
	"fmt"
	"math"
	"regexp"
	"strconv"
	"strings"
	"unicode"
)

func hello() {
	fmt.Println("hello")
}

func displayNumbers(limit int) {
	for i := 0; i < limit; i++ {
		fmt.Print(i, " ")
	}
}

func displayAlphabet() {
	for c := 'a'; c < 'z'; c++ {
		fmt.Printf("%c", c)
	}
}

func displayMultTables(n int) {
	for i := 1; i <= n; i++ {
		for j := 1; j <= 10; j++ {
			fmt.Printf("%4d ", i*j)
		}
		fmt.Println()
	}
}

func max(i int, j int, k int) int {
	m := i

	if m < j {
		m = j
	}
	if m < k {
		m = k
	}
	return m
}

func getMultipleNumber(n int) {
	nb := 0

	for ok := true; ok; ok = ((nb % n) != 0) {
		fmt.Print("Give an integer: ")
		fmt.Scanf("%d\n", &nb)
	}
	fmt.Println("Your number is: ", nb)
}

func myAbs(nb int) int {
	if nb < 0 {
		nb = -nb
	}
	return nb
}

func factorial(nb int) int {
	result := 1

	for i := 2; i <= nb; i++ {
		result *= i
	}
	return result
}

func displayDigits(nb int) {
	if nb < 10 {
		fmt.Print(nb, " ")
	} else {
		displayDigits(nb / 10)
		fmt.Print(nb%10, " ")
	}
}

func displayFibonacci(n int) {
	nb1 := 0
	nb2 := 1
	fibo := 0

	for i := 0; i < n; i++ {
		if (i & 1) == 0 {
			fmt.Print(nb1, " ", nb2, " ")
		}
		fibo = nb1 + nb2
		nb1 = nb2
		nb2 = fibo
	}
}

func quadraticEquation(a float64, b float64, c float64) [2]float64 {
	tab := [2]float64{math.NaN(), math.NaN()}
	discriminant := b*b - 4*a*c

	if discriminant == 0 {
		tab[0] = -b / 2 * a
	} else if discriminant > 0 {
		tab[0] = (-b - math.Sqrt(discriminant)) / (2 * a)
		tab[1] = (-b + math.Sqrt(discriminant)) / (2 * a)
	}
	return tab
}

func gcd(a int, b int) int {
	if b == 0 {
		return a
	} else {
		return gcd(b, a%b)
	}
}

func isPerfectNumber(nb int) bool {
	res := 0

	for i := 1; i < nb/2+1; i++ {
		if nb%i == 0 {
			res += i
		}
	}
	return res == nb
}

func displayPrimeFactors(nb int) {
	for i := 2; i <= int(math.Sqrt(float64(nb))); i++ {
		if nb%i == 0 {
			fmt.Print(i, " ")
			nb /= i
		}
	}
	if nb > 2 {
		fmt.Print(nb)
	}
}

func displayTab(tab []int) {
	for _, elem := range tab {
		fmt.Print(elem, " ")
	}
}

func sumTab(tab []int) int {
	sum := 0

	for i := 0; i < len(tab); i++ {
		sum += tab[i]
	}
	return sum
}

func maxTab(tab []int) int {
	max := tab[0]

	for i := 1; i < len(tab); i++ {
		if max < tab[i] {
			max = tab[i]
		}
	}
	return max
}

func swapValuesTab(tab []int, i int, j int) {
	tmp := tab[i]

	tab[i] = tab[j]
	tab[j] = tmp
}

func arrayOccurrences(tab []int, max int) {
	tmp := make([]int, max)

	for i := 0; i < len(tab); i++ {
		tmp[tab[i]]++
	}

	for i := 0; i < len(tmp); i++ {
		if tmp[i] != 0 {
			fmt.Printf("%d:\t %d occurrence(s)\n", i, tmp[i])
		}
	}
}

func countLetters(str string, letter rune) int {
	res := 0

	for _, character := range str {
		if rune(character) == letter {
			res++
		}
	}
	return res
}

func countVowels(str string) int {
	nbVowels := 0
	re := regexp.MustCompile("[aeiouy]")

	for i := 0; i < len(str); i++ {
		if re.MatchString(str[i : i+1]) {
			nbVowels++
		}
	}
	return nbVowels
}

func convertToUpperCase(str string) string {
	return strings.ToUpper(str)
}

func containsUpperCase(str string) bool {
	i := 0

	for i < len(str) && !unicode.IsUpper(rune(str[i])) {
		i++
	}
	return i < len(str)
}

func isInStr(source string, str string) bool {
	return strings.Contains(source, str)
}

func isPalindromeWord(str string) bool {
	i := 0
	j := len(str) - 1

	for i < j {
		if str[i] != str[j] {
			return false
		}
		i++
		j--
	}
	return true
}

func isPalindromeSentence(str string, start int, end int) bool {
	if str[start] == ' ' {
		return isPalindromeSentence(str, start+1, end)
	} else if str[end] == ' ' {
		return isPalindromeSentence(str, start, end-1)
	} else if start >= end {
		return true
	} else if str[start] != str[end] {
		return false
	} else {
		return isPalindromeSentence(str, start+1, end-1)
	}
}

func convertBase(nb int, base int) string {
	gBase := "0123456789ABCDEF"
	res := ""
	tmp := nb

	for ok := true; ok; ok = (tmp != 0) {
		res += string(gBase[tmp%base])
		tmp /= base
	}
	return res
}

func cesarEncrypt(str string, n int) string {
	sb := ""
	keys := "abcdefghijklmnopqrstuvwxyz"

	for _, character := range str {
		sb += string(keys[(int(character)-97+n)%26])
	}
	return sb
}

func getItemMap(hashtable map[string]int, key string) int {
	return hashtable[key]
}

func postfix(str string) int {
	stack := make([]string, len(str))
	elements := strings.Fields(str)
	res := 0
	i := 0

	for _, elem := range elements {
		if strings.Contains("+-*/", elem) {
			op1, _ := strconv.Atoi(stack[i-1])
			i = i - 1
			op2, _ := strconv.Atoi(stack[i-1])
			i = i - 1
			if elem == "+" {
				res = op2 + op1
			} else if elem == "-" {
				res = op2 - op1
			} else if elem == "*" {
				res = op1 * op2
			} else {
				res = op2 / op1
			}
			stack[i] = strconv.Itoa(res)
			i = i + 1
		} else {
			stack[i] = elem
			i = i + 1
		}
	}
	return res
}

func main() {
	hello()

	displayNumbers(20)

	fmt.Println()

	displayAlphabet()

	fmt.Println()

	displayMultTables(7)

	fmt.Println(max(2, 1, 3))

	getMultipleNumber(8)

	fmt.Println(myAbs(-8))

	fmt.Println(factorial(10))

	displayDigits(65535)
	fmt.Println()

	displayFibonacci(10)
	fmt.Println()

	t := quadraticEquation(1, -1, -1)
	fmt.Println(t[0], " ", t[1])

	fmt.Println(gcd(28, 4294967296))

	fmt.Println(isPerfectNumber(33_550_336))

	displayPrimeFactors(65535)
	fmt.Println()

	t1 := []int{1, 2, 3, 4, 5, 6, 7}
	displayTab(t1)
	fmt.Println()

	fmt.Println(sumTab(t1))

	fmt.Println(maxTab(t1))

	swapValuesTab(t1, 2, 6)
	fmt.Println(t1)

	t2 := []int{2, 1, 3, 4, 4, 5, 6, 4, 7, 1, 8, 9, 5, 18, 7, 1}
	arrayOccurrences(t2, 19)

	fmt.Println(countLetters("mémoire", 'm'))

	fmt.Println(countVowels("ordinateur"))

	fmt.Println(convertToUpperCase("quantique"))

	fmt.Println(containsUpperCase("cordeS"))

	fmt.Println(isInStr("ordinateur", "ina"))

	fmt.Println(isPalindromeWord("radar"))

	str := "engage le jeu que je le gagne"
	fmt.Println(isPalindromeSentence(str, 0, len(str)-1))

	fmt.Println(convertBase(2500, 8))

	fmt.Println(cesarEncrypt("abcdefghijklmnopqrstuvwxyz", 4))

	players := map[string]int{"charles": 1500, "magnus": 2850}
	fmt.Println(getItemMap(players, "charles"))

	fmt.Println(postfix("3 12 3 - 3 / 1 - *"))
}
