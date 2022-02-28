# Overview
As discussed at interview, this coding assessment was originally designed for java and delivered to candidates via an integrated java application.
For your solution, IDBS have agreed to accept the assessment in a language other than java - if this was NOT discussed at interview please leave 
this section of the repo and return to the top level java section - we will only accept solutions in non-java from specific candidates.

The original java-based application is a cyclic test framework: it generates 100s to 1000s of random data items (each representing a polynomial equation - see below for detail) 
and one at a time feeds each to the candidate's solution via an integrated "candidate entry-point".

The candidate's solution receives a single data item (the encoded polynomial), interprets the data and makes the necessary calculation to solve the polynomial (calculates the Y-value for a X-value). 
The Y-value calculated is returned to the application and the framework then tests if the correct answer has been calculated.

In order to provide the same functionality for non-java solutions a data file has been generated (one for each level of difficulty). 
The data in the file represents 100s to 1000s polynomial equations, each polynomial has two lines of data:
	 - the first line contains the encoded polynomial data
	 - the second line contains the expected Y-value when solving the polynomial for the given X-value.
	 
You solution should read the data (two lines at a time), interpret the data from the first line and calculate the **your Y-value** (i.e. solve the equation for the given X-value). 
Then compare your calculated Y-value with the actual expected Y-value provided in the second line of data.

Repeat for the 100s to 1000s of polynomials provided in the file.


## Polynomial Level 1 (detail)

### Levels of Difficulty
There are TWO levels of difficulty for this question (this is for level-1):
 - Level 1 - simplest level&nbsp;&nbsp;&nbsp;(basic input and calculations)
 - Level 2 - medium complexity&nbsp;&nbsp;&nbsp;(level-1 + varied input sources)

The general scenario and rules described below (level-1 rules)
apply across all levels of difficulty for this question. Each level
of difficulty adds additional rules and builds on the previous and
is designed to give you the opportunity to demonstrate your design,
technical and coding skills. 

Your final solution
can be at the level of your choice; it should be coded to a production-ready standard. 
It should include Unit tests if possible and show case
your OO-design and OO-programming abilities (or other design/development paradigm).

### Scenario &amp; Rules: Level-1
Your objective is to do a mathematical calculation, with a
coding twist. You need to evaluate multiple polynomial
equations,&nbsp;i.e.&nbsp;for the polynomial equation provided
you'll calculate the Y-value for a given X-value.

The polynomial will have the following form: 

<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;y&nbsp;&nbsp;=&nbsp;&nbsp;3x<sup>4</sup>&nbsp;&nbsp;+&nbsp;&nbsp;2x<sup>3</sup>&nbsp;&nbsp;-&nbsp;&nbsp;6x<sup>2</sup>&nbsp;&nbsp;+&nbsp;&nbsp;8x&nbsp;&nbsp;-&nbsp;&nbsp;2</b>

 - the number of terms in the polynomial will not exceed 10 terms
 - the value of the multiplier will not exceed 10
 - the value of X will not exceed 50
 - all numbers used (the power, multiplier, constant and value of X) will always be positive integers


Now here is the coding twist:

					The code that you write to evaluate the equation must
					ONLY utilise the mathematical operators ADD and SUBTRACT 
					the coding language operator + and -

You **are not allowed** to use mathematical operators such as
multiplication, division, modulus, "raised to the power of",
logarithms, base conversions, logical-shift operators or any
mathematical functions/operators other than the operators
ADD "&nbsp;+&nbsp;" or SUBTRACT "&nbsp;-&nbsp;"&nbsp;. If the code you
submit uses any method other than simple ADD or SUBTRACT during
the calculation process the solution will be rejected.

Use of the increment/decrement operator
++&nbsp;or&nbsp;-- (for example: i++ as a loop counter) is
allowed within the code of your solution, but the value should
not be used as part of an addition or subtraction within the
polynomial calculation itself.

### Level-1 Specifics:
The polynomial will be provided as input data
in the form of Json as follows, (this json is for the example
above):

		{
		   "xValue": 5,
		   "terms":
			  [
				 {
					"power": 1,
					"multiplier": 8,
					"action": "add"
				 },
				 {
					"power": 0,
					"multiplier": 2,
					"action": "subtract"
				 },
				 {
					"power": 4,
					"multiplier": 3,
					"action": "add"
				 },
				 {
					"power": 3,
					"multiplier": 2,
					"action": "add"
				 },
				 {
					"power": 2,
					"multiplier": 6,
					"action": "subtract"
				 }
			  ]
		} 


### Executing Your Solution
You will execute your solution in your chosen language by reading the data from the data file provided.

***LEVEL_1_DataFile.dat***

As discussed in the opening section the data for each polynomial is in two parts:
	 - the first line contains the encoded polynomial data
	 - the second line contains the expected Y-value when solving the polynomial for the given X-value.

Your solution should calculate the Y-value and then compare this to the expected Y-value from the data file.
Once you have all polynomials being calculated correctly AND you are satisfied with the quality of code you have
written (remember production level quality)- **you are ready to submit your solution** - 
please see *Submiting Your Solution* below for instructions on submitting your solution.

### What We're Looking For
In this assessment exercise we are assessing your design
and coding skills so your code should be of the highest quality.
Although it is a "simple" problem you should use design and coding
techniques that you would apply within a production environment.
Make your sure solution is well designed using OOD/OOP practices (or other 
programming paradigms), readable, reliable, robust
and maintainable. As well as using any techniques that you feel
appropriate for high quality and maintainable code you should aim
to provide a reasonable level of test coverage for you solution -
which we would like to see submitted along with your solution.


When writing unit tests you MAY use mathematical operators
other than ADD and SUBTRACT in your test code.

### Third Party Libraries
Please do not use any third party libraries beyond the standard runtime for your selected language, 
other than those required to read JSON.

For security reasons we will be unable to accept any solution that uses third-party libraries other 
than those for Json api.

## Submiting Your Solution
Your solution should be submitted to us via a ***private github repository.*** 
Please add our github user **DevAssessmentIDBS** as a collaborator to your 
repository - this will allow us to access the code in your private repository. 

**Please ensure you include your full name in your submission somewhere,**
** e.g. in the name of the repo OR in a text file within the repo.**

You should include all the source code you write for
your solution. If you write any supporting code (e.g. unit test
code) - please submit this code to us as part of the assessment,
but without the associated third party libraries (e.g. unit
testing libraries), we will assess your test code without
executing it. Once submitted to github, please email the url for
your repository to us via the email address supplied previously.


### Final Comments &amp; Thoughts
This is a coding assessment exercise and as such it is
necessarily brief. We are looking for a well-designed solution and
well written code that is of the highest quality. Do not be
afraid to "over design" your solution - the idea is to demonstrate
your design and coding skills rather than produce the shortest,
simplest solution.

If you have any feedback you wish to share or problems
getting started with the assessment application, please contact us
on the email used in our previous communications.



**Good Luck**

