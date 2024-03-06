# Eshop

**Name:** Fikri Risyad Indratno<br>
**NPM:** 2206031170<br>
**Class:** Advance Programming B<br>

**App URL: https://eshop-fikririsyad.koyeb.app/**

---
## Sections
- [Tutorial 1](#tutorial-1)
- [Tutorial 2](#tutorial-2)
- [Tutorial 3](#tutorial-3)
---

## Tutorial 1

###  Reflection 1

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

### Reflection 2

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

---

# Tutorial 2

### Reflection

> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

I use SonarCloud for scanning my code. The code quality issue that the scanner found was the lack of description in 
my Product List Table. According to SonarCloud, an HTML table should have a description to be accessible to visually 
impaired users. To resolve this issue, I added a description `"List of user's product"` using `<caption>` tag right 
below the `<table>` tag, which explained the purpose of the table. After I pushed the latest commit, the issue is fixed 
and the scanner didn't find any code quality issue.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the 
> definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Yes, I think my implementation has met the definition of CI and CD. My code is integrated and verified automatically by 
CI workflow. The workflow will get triggered every time I push or make a pull request, and then it will run the unit 
tests to check my code. I also have Scorecard and SonarCloud workflow to check the security and the quality of my code.
As for the deployment (CD), it will be deployed automatically by Koyeb every time there is a push or pull request in the `main`
branch.

---

# Tutorial 3

### Reflection

> Explain what principles you apply to your project!

1. **Single Responsibility Principle (SRP)**

    I have applied SRP by separating `CarController` from `ProductController` file into its own file. By doing this,
    each controller file has its own responsibility seeing that `CarController` is not `ProductController`'s responsibility.

2. **Liskov Substitution Principle (LSP)**
   
    I have applied LSP since I can use `CarService` and `CarServiceImpl` interchangeably without causing
    unexpected behavior.

3. **Dependency Inversion Principle (DIP)**

    I have applied DIP by changing `CarServiceImpl` class to `CarService` interface in `CarController` class. Therefore,
    `CarController` will depends on its abstraction rather than its concrete implementation.

> Explain the advantages of applying SOLID principles to your project with examples.

By adhering to SOLID principles, our code will be more organized and manageable. For instance, it will be easier for me 
and other people to understand and maintain `CarController` now after we separate it from `ProductController`. We can
easily find which file that we need to modify. By applying DIP, changes to implementations do not directly impact 
the class that depends on the interface, leading to a more resilient and maintainable codebase, like in the case of 
`CarController` and `CarService`.

> Explain the disadvantages of not applying SOLID principles to your project with examples.

If I don't adhere to SOLID principles, the code would be unorganized, and it would be harder for us and other people to
read and understand our code. We would be having a hard time trying to modify our code because it's so messy when we left
it. It would be more difficult for us to identify the cause of an error.

---

> Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” 
> submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If 
> not, explain things that you need to do next time you make more tests.

I think TDD flow might be useful for me in the future. As for now, I am still not used to the flow and sometimes confused
on what I have to test. As for the correctness, I believe my tests have covered most of the code, but there might still be edge
cases. As for the maintainable code, I believe using TDD flow has made my code a bit cleaner than before, and with the
refactoring step, I can improve the code. As for productive workflow, I think writing the tests is the longest part of
the whole TDD process. I still need to practice more so that I can improve my workflow.

> You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. 
> principle or not. If not, explain things that you need to do the next time you create more tests.

I think the tests that I made have adhered to almost all of F.I.R.S.T principles. I believe my tests are fast and will show the results
shortly after they are run. Each test case is also isolated from the others, and I've used setUp method for setting up
the environment. All the tests will return the same result every time I run the tests, so it is consistent and repeatable.
I think all assertions that I made are human-readable, and related to the context. As for the Thorough/Timely principle,
I assume there are still unhappy paths that I haven't fully handled, so there might be errors that haven't been covered
yet.

---