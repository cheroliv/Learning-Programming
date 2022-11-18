/*
 ================================
 Name         : base_exercises.rs
 Author       : Charles T.
 ================================
*/

use std::collections::HashMap;
use std::io;

fn hello() {
    println!("Hello World!");
}

fn display_numbers(limit: i64) {
    for i in 0..limit {
        print!("{} ", i);
    }
}

fn display_alphabet() {
    for c in 'a'..'z' {
        print!("{}", c);
    }
}

fn display_mult_tables(n: i32) {
    for i in 1..=n {
        for j in 1..=10 {
            print!("{:4} ", i*j)
        }
        println!();
    }
}

fn max(i: i64, j: i64, k: i64) -> i64 {
    let mut m = i;

    if m < j {
      m = j;
    }
    if m < k {
      m = k;
    }
    return m;
}

fn get_multiple_number(n: i64) {
    let mut nb;

    loop {
        println!("Give an integer: ");
        let mut str = String::new();
        io::stdin().read_line(&mut str).expect("failed to read input.");
        nb = str.trim().parse::<i64>().expect("invalid input");
        if !((nb % n) != 0) {
            break;
        }
    }
    println!("Your number is: {}", nb);
}

fn my_abs(nb: i64) -> i64 {
    return if nb < 0 {-nb} else {nb};
}

fn factorial(nb: i64) -> i64 {
    let mut result = 1;

    for i in 2..=nb {
        result *= i;
    }
    return result;
}

fn display_digits(nb: i64) {
    if nb < 10 {
        print!("{} ", nb);
    } else {
        display_digits(nb / 10);
        print!("{} ", nb%10);
    }
}

fn display_fibonacci(n: i64) {
    let mut nb1 = 0;
    let mut nb2 = 1;
    let mut fibo;

    for i in 0..n {
        if (i & 1) == 0 {
            print!("{} {} ", nb1, nb2);
        }
        fibo = nb1 + nb2;
        nb1 = nb2;
        nb2 = fibo;
    }
}

fn quadratic_equation(a: f64, b: f64, c: f64) -> [f64;2] {
    let mut tab: [f64;2] = [f64::NAN, f64::NAN];
    let discriminant = b*b - 4.0*a*c;

    if discriminant == 0.0 {
        tab[0] = -b / 2.0 * a;
    } else if discriminant > 0.0 {
        tab[0] = (-b - discriminant.sqrt()) / (2.0 * a);
        tab[1] = (-b + discriminant.sqrt()) / (2.0 * a);
    }
    return tab;
}

fn gcd(a: i64, b: i64) -> i64 {
    if b == 0 {
        return a;
    } else {
        return gcd(b, a%b);
    }
}

fn is_perfect_number(nb: i64) -> bool {
    let mut res = 0;

    for i in 1..nb/2+1 {
        if nb%i == 0 {
            res += i;
        }
    }
    return res == nb;
}

fn display_prime_factors(mut nb: i64) {
    let limit = (nb as f64).sqrt();

    for i in 2..(limit as i64) {
        if nb%i == 0 {
            print!("{} ", i);
            nb /= i;
        }
    }
    if nb > 2 {
        print!("{}", nb);
    }
}

fn display_tab(tab: &[i64]) {
    for i in 0..tab.len() {
        print!("{} ", tab[i]);
    }
}

fn sum_tab(tab: &[i64]) -> i64 {
    let mut sum = 0;

    for i in 0..tab.len() {
        sum += tab[i];
    }
    return sum;
}

fn max_tab(tab: &[i64]) -> i64 {
    let mut max = tab[0];

    for i in 0..tab.len() {
        if max < tab[i] {
            max = tab[i];
        }
    }
    return max;
}

fn swap_values_tab(tab: &mut [i64], i: usize, j: usize) {
    let tmp = tab[i];

    tab[i] = tab[j];
    tab[j] = tmp;
}

fn array_occurrences(tab: &[i64], max: i64) {
    let mut tmp: Vec<i64> =  vec![0;max as usize];

    for i in 0..tab.len() {
        tmp[tab[i] as usize] += 1;
    }
    
    for i in 0.. tmp.len() {
        if tmp[i] != 0 {
            print!("{}:\t {} occurrence(s)\n", i, tmp[i]);
        }
    }
}

fn count_letters(word: &str, letter: char) -> i64 {
    let mut res = 0;

    for c in word.chars() {
        if c == letter {
            res += 1;
        }
    }
    return res;
}

fn count_vowels(word: &str) -> i64 {
    let mut nb_vowels = 0;

    for c in word.chars() {
        if "aeiouy".contains(c) {
            nb_vowels += 1;
        }
    }
    return nb_vowels;
}

fn convert_to_uppercase(word: &str) -> String {
    return word.to_uppercase();
}

fn contains_uppercase(word: &str) -> bool {
    let mut i = 0;

    while i < word.len() && (!word.as_bytes()[i] as char).is_uppercase() {
        i += 1;
    }
    return i < word.len();
}

fn is_in_str(source: &str, word: &str) -> bool {
    return source.contains(word);
}

fn is_palindrome_word(word: &str) -> bool {
    let mut i = 0;
    let mut j = word.len() - 1;

    while i < j {
        if word.as_bytes()[i] != word.as_bytes()[j] {
            return false;
        }
        i += 1;
        j -= 1;
    }
    return true;
}

fn is_palindrome_sentence(s: &str, start: i64, end: i64) -> bool {
    if s.as_bytes()[start as usize] == ' ' as u8 {
        return is_palindrome_sentence(s, start+1, end);
    } else if s.as_bytes()[end as usize] == ' ' as u8 {
        return is_palindrome_sentence(s, start, end-1);
    } else if start >= end {
        return true;
    } else if s.as_bytes()[start as usize] != s.as_bytes()[end as usize] {
        return false;
    } else {
        return is_palindrome_sentence(s, start+1, end-1);
    }
}

fn convert_base(nb: i64, base: i64) -> String {
    let g_base = "0123456789ABCDEF";
    let mut res = String::from("");
    let mut tmp = nb;

    while tmp != 0 {
        let i = (tmp%base) as usize;
        let s = g_base.as_bytes()[i] as char;
        res.push(s);
        tmp /= base;
    }
    return res.chars().rev().collect();
}

fn cesar_encrypt(word: &str, n: usize) -> String {
    let mut enc_word = String::from("");
    let keys = "abcdefghijklmnopqrstuvwxyz";
    let mut i = 0;

    while i < word.len() {
        let s = keys.as_bytes()[(i + n) % 26] as char;
        enc_word.push(s);
        i += 1;
    }
    return enc_word;
}

fn get_item_map(players: HashMap<&str, i64>, key: &str) -> i64 {
    return *players.get(&key).unwrap();
}

fn postfix(expr: &str) -> i64 {
    let mut stack = Vec::<String>::new();
    let mut res = 0;

    for elem in expr.split(" ") {
        if "+-*/".contains(elem) {
            let op1 = stack.pop().unwrap().parse::<i64>().unwrap();
            let op2 = stack.pop().unwrap().parse::<i64>().unwrap();
            if elem == "+" {
                res = op2 + op1;
            } else if elem == "-" {
                res = op2 - op1;
            } else if elem == "*" {
                res = op1 * op2;
            } else {
                res = op2 / op1;
            }
            stack.push(res.to_string());
        } else {
            stack.push(elem.to_string());
        }
    }
    return res;
}

fn main() {
    hello();
    display_numbers(20);
    println!();
    display_alphabet();
    println!();
    display_mult_tables(7);
    println!("{}", max(2, 1, 3));
    get_multiple_number(8);
    println!("{}", my_abs(-8));
    println!("{}", factorial(10));
    display_digits(65535);
    println!();
    display_fibonacci(10);
    println!();
    let t = quadratic_equation(1.0, -1.0, -1.0);
    println!("{} {}", t[0], t[1]);
    println!("{}", gcd(28, 4294967296));
    println!("{}", is_perfect_number(33_550_336));
    display_prime_factors(65535);
    println!();
    let mut t1 = [1, 2, 3, 4, 5, 6, 7];
    display_tab(&t1);
    println!();
    println!("{}", sum_tab(&t1));
    println!("{}", max_tab(&t1));
    swap_values_tab(&mut t1, 2, 6);
    display_tab(&t1);
    println!();
    let t2 = [2, 1, 3, 4, 4, 5, 6, 4, 7, 1, 8, 9, 5, 18, 7, 1];
    array_occurrences(&t2, 19);
    println!("{}", count_letters("mémoire", 'm'));
    println!("{}", count_vowels("ordinateur"));
    println!("{}", convert_to_uppercase("quantique"));
    println!("{}", is_palindrome_word("radar"));
    println!("{}", contains_uppercase("cordeS"));
    println!("{}", is_in_str("ordinateur", "ina"));
    println!("{}", is_palindrome_word("radar"));
    let s = "engage le jeu que je le gagne";
    println!("{}", is_palindrome_sentence(s, 0, (s.len() - 1) as i64));
    println!("{}", convert_base(2500, 8));
    println!("{}", cesar_encrypt("abcdefghijklmnopqrstuvwxyz", 4));
    let mut players = HashMap::new();
    players.insert("charles", 1500); 
    players.insert("archimède", 2850);
    println!("{}", get_item_map(players, "charles"));
    println!("{}", postfix("3 12 3 - 3 / 1 - *"));
}