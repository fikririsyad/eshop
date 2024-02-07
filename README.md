# Eshop

**Name:** Fikri Risyad Indratno<br>
**NPM:** 2206031170<br>
**Class:** Advance Programming B<br>

---

##  Reflection 1

> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding 
standards that you have learned in this module. Write clean code principles and secure coding practices that have 
been applied to your code.  If you find any mistake in your source code, please explain how to improve your code. 
**Please write your reflection inside the repository's README.md file.**

In this assignment, I have implemented a few of clean code principles, such as:
- Meaningful names for variables, functions, and classes. All the names reveal their intent, so it's easier to read
and understand.
- Make functions small and do one specific job.
- I haven't put any comments in my code, since I think it's still understandable what their purpose are.
- Implement OOP by creating interface and classes.

I haven't applied much of secure coding practices and I hope I can improve it in the future.

---

## Reflection 2

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure 
that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code 
coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, 
does that mean your code has no bugs or errors? 

I'm starting to understand more about writing unit test. I think there needs to be at least one unit test for each 
method in a class. To make sure our tests are enough to verify our program, we can use code coverage, which measures
the percentage of our code that has been tested. However, even if we have 100% code coverage, it doesn't mean that 
our code is free of bugs or errors. We may have tested every line of our code but there still might be boundary cases 
that haven't been covered. 

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, 
you were asked to create another functional test suite that verifies the **number of items in the product list**. 
You decided to create a new Java class similar to the prior functional test suites with **the same setup procedures 
and instance variables**.
> 
> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce 
the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements 
to make the code cleaner! **Please write your reflection inside the repository's README.md file.**

I think it's redundant since the setup procedures is the same as the other suite. I think it would be better for the 
new class to inherit the setup and instance variables from CreateProductFunctionalTest.java. Or maybe we can make the
setup procedures and instance variables as a new class that both CreateProductFunctionalTest.java and the new suite
will inherit. Therefore, we reduce the amount of code while still having the same purpose, and we don't repeat ourselves.