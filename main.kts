// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg : Any) : String {
    if (arg == "Hello") {
        return "world"
    } else if (arg is String) {
        return "Say what?"
    } else if (arg == 0) {
        return "zero"
    } else if (arg == 1) {
        return "one"
    }  else if (arg is Int) {
        if (arg.toInt() >= 2 && arg.toInt() <= 10) {
            return "low number"
        } else {
            return "a number"
        }
    } else {
        return "I don't understand"
    }
}
// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(first: Int, second: Int) : Int {
    return first + second
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(first : Int, second: Int) : Int {
    return (first - second)
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(first: Int, second: Int, fnc: (Int, Int) -> Int) : Int {
    return fnc(first, second)
}
// write a class "Person" with first name, last name and age
class Person (var firstName: String,var lastName: String,var age: Int) {

    //val debugString : String = "[Person firstName:$firstName lastName:$lastName age:$age]"
    val debugString: String by lazy {
        "[Person firstName:$firstName lastName:$lastName age:$age]"
    }
}

// write a class "Money"
/*Create a class, "Money", that has two properties, "amount" and "currency".
 "Currency" can be one of "USD", "EUR", "CAN" and "GBP". "Amount" is a standard Int.
  Define the properties such that "amount" can never be less than zero, and that "currency" can only be one of those four symbols.
  Define a public method, convert, that takes a String argument for the currency type to convert to,
   and return a new Money instance with the amount converted.
    Conversion rates should be as follows: 10 USD converts to 5 GBP; 10 USD converts to 15 EUR; 12 USD converts to 15 CAN.
    (Make sure you can convert in both directions!)
    Define the "+" operator on Money to return a new instance of Money that adds the amount,
     converting the currency to the first (left-hand) Money's currency. So adding (10 USD) + (5 GBP)
     should return a result in USD. Similarly, adding (5 GBP) + (10 USD) should return the result in GBP. */

public class Money(var amount:Int, var currency: String){

    operator fun plus(other: Money): Money{
        return Money(this.amount + other.convert(this.currency).amount, this.currency)
    }

    operator fun Money.unaryMinus() = Money(-amount, currency)

    fun convert(type: String) : Money {
        if (type == "GBP" && this.currency == "USD") { //usd to gbp
            return (Money(this.amount / 2, "GBP"))
        } else if (type == "EUR" && this.currency == "USD"){ // usd to eur
             return (Money((this.amount.toDouble() * 1.5).toInt(), "EUR"))
        } else if (type == "CAN" && this.currency == "USD") { //USD TO CAN
            return (Money((this.amount / 12 )* 15, "CAN"))
        } else if (type == "USD" && this.currency == "GBP") { //GBP TO USD
            return (Money(this.amount * 2, "USD"))
        } else if (type == "USD" && this.currency == "EUR") { //EUR TO USD
            return (Money((this.amount * 2) / 3, "USD"))
        } else if (type == "USD" && this.currency == "CAD") { //CAD TO USD
            return (Money((this.amount * 15 )/ 12, "USD"))
        } else if (type == "USD" && this.currency == "USD") {// USD TO USD
            return (Money(this.amount, "USD"))
        } else { // gbp to eur
            return (Money(this.amount*3, "EUR"))
        }
    }
}


// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)

for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
