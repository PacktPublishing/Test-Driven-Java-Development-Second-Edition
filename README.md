


# Test-Driven Java Development - Second Edition
This is the code repository for [Test-Driven Java Development - Second Edition](https://www.packtpub.com/application-development/test-driven-java-development-second-edition?utm_source=github&utm_medium=repository&utm_campaign=9781788836111), published by [Packt](https://www.packtpub.com/?utm_source=github). It contains all the supporting project files necessary to work through the book from start to finish.
## About the Book
Test-driven development (TDD) is a development approach that relies on a test-first procedure that emphasizes writing a test before writing the necessary code, and then refactoring the code to optimize it.The value of performing TDD with Java, one of the longest established programming languages, is to improve the productivity of programmers and the maintainability and performance of code, and develop a deeper understanding of the language and how to employ it effectively.

Starting with the basics of TDD and understanding why its adoption is beneficial, this book will take you from the first steps of TDD with Java until you are confident enough to embrace the practice in your day-to-day routine.You'll be guided through setting up tools, frameworks, and the environment you need, and we will dive right into hands-on exercises with the goal of mastering one practice, tool, or framework at a time. You'll learn about the Red-Green-Refactor procedure, how to write unit tests, and how to use them as executable documentation.With this book, you'll also discover how to design simple and easily maintainable code, work with mocks, utilize behavior-driven development, refactor old legacy code, and release a half-finished feature to production with feature toggles.You will finish this book with a deep understanding of the test-driven development methodology and the confidence to apply it to application programming with Java.

## Instructions and Navigation
All of the code is organized into folders. Each folder starts with a number followed by the application name. For example, Chapter02.



The code will look like the following:
```
public String play(int x, int y) {
  checkAxis(x);
  checkAxis(y);
  lastPlayer = nextPlayer();
  setBox(x, y, lastPlayer);
  if (isWin()) {
    return lastPlayer + " is the winner";
  } else if (isDraw()) {
    return "The result is draw";
  } else {
    return "No winner";
  }
}

```

The exercises in this book require readers to have a 64-bit computer. Installation instructions for all required software is provided throughout the book.

## Related Products
* [Angular Test-Driven Development - Second Edition](https://www.packtpub.com/web-development/angular-test-driven-development-second-edition?utm_source=github&utm_medium=repository&utm_campaign=9781786465474)

* [Test-Driven iOS Development with Swift 4 - Third Edition](https://www.packtpub.com/application-development/test-driven-ios-development-swift-4-third-edition?utm_source=github&utm_medium=repository&utm_campaign=9781788475709)

* [Practical Test-Driven Development using C# 7](https://www.packtpub.com/web-development/practical-test-driven-development-using-c-7?utm_source=github&utm_medium=repository&utm_campaign=9781788398787)

### Download a free PDF

 <i>If you have already purchased a print or Kindle version of this book, you can get a DRM-free PDF version at no cost.<br>Simply click on the link to claim your free PDF.</i>
<p align="center"> <a href="https://packt.link/free-ebook/9781788836111">https://packt.link/free-ebook/9781788836111 </a> </p>