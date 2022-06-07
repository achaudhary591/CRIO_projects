
# Pre-requisites
* Java 1.8/1.11/1.15
* Gradle 6

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.

Internally both the scripts run the following commands 

 * `gradle clean build -x test --no-daemon` - This will create a jar file `geektrust.jar` in the `build/libs` folder.
 * `java -jar build/libs/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 Use the build.gradle file provided along with this project. Please change the main class entry under the `jar` task

 ```
 manifest {
        attributes 'Main-Class' : 'com.geektrust.backend.App' //Change this to the main class of your program which will be executed
    }
```
in the build.gradle if your main class has changed.

 # How to execute the unit tests

 `gradle clean test --no-daemon` will execute the unit test cases.

# Help

You can refer our help documents [here](https://help.geektrust.in)
You can read build instructions [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java)

# PROBLEM_STATEMENT

You work at MyFund&Stocks, a platform that lets investors track their investments in equity mutual funds.

Equity mutual funds are a collection of individual stocks. Fund overlap is the term used to quantify the percentage of common stocks between different mutual funds. Investors prefer to have funds with as low an overlap as possible, since investing in different mutual funds with a high overlap will not give the benefits of diversification.

Available mutual funds and their respective stocks are mentioned here.

Portfolio Overlap
Overlap is defined as a measure of the commonality between portfolios.

Suppose there are 2 funds A & B. The overlap between them is given as the number of stocks in common, counting both portfolio A and B( (i.e. double counting the common elements) divided by the total number of elements in portfolio A and portfolio B.

Overlap (A,B) =
2*(No of common stocks in A & B)/ (No of stocks in A + No of stocks in B) * 100

Your program should take as input:

1. Name of current funds the user holds in his/her portfolio.
2. New funds to be added.
3. Stock to be added for existing fund.
The output should be

1. Overlapping stocks of the new fund with the current funds.
2. If a given fund name is not present while calculating overlap or adding stocks to existing fund, the output should be FUND_NOT_FOUND.
Input Commands
There are 4 input commands defined to separate out the actions. Your input format will start with either of these commands i.e CURRENT_PORTFOLIO, CALCULATE_OVERLAP, OVERLAP_PERCENTAGE, ADD_STOCK


CURRENT_PORTFOLIO
The CURRENT_PORTFOLIO command receives the current funds that the user holds in his/her portfolio.
Format - CURRENT_PORTFOLIO FUND_NAME_1 FUND_NAME_2
Example- CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP means that the current fund that the user holds in his/her portfolio are AXIS_BLUECHIP and ICICI_PRU_BLUECHIP

CALCULATE_OVERLAP
The CALCULATE_OVERLAP command receives the fund name and calculates the overlap between that fund with each fund in the current portfolio.
Format - CALCULATE_OVERLAP FUND_NAME
Example - CALCULATE_OVERLAP AXIS_BLUECHIP means calculate the percentage of overlapping stocks of AXIS_BLUECHIP with each fund that the user holds in his/her current portfolio.

ADD_STOCK
The ADD_STOCK command receives the fund name to which the new stock will be added and the name of the new stock.
Format - ADD_STOCK FUND_NAME STOCK_NAME
Example - ADD_STOCK AXIS_BLUECHIP HDFC BANK LIMITED means we have to add the stock HDFC BANK LIMITED to AXIS_BLUECHIP fund.
Assumptions
1. All the input funds will be those that are present in the available funds json provided.
2. The overlap percentages will be rounded to 2 decimal points
3. The stocks names can have spaces in between, for eg: HDFC BANK LIMITED
