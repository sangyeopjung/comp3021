# Lab 1: IntelliJ + Git Quick Start Guide
This lab covers the following topics:
* Installing and running IntelliJ IDEA with Java 10
* Creating a Hello World program
* Using git with GitHub for version control
* A sneak peak of assignment 1

## JDK 10 download and installation
IntelliJ comes bundled with Java 8 but this is not the version we want. Therefore, we must Download and install JDK 10 [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html). Note the install location, as it will be useful later.
(No need if using lab pc in room 4210, it is located at `C:\IntelliJ IDEA Community Edition 2018.2.2\IntelliJ IDEA Community Edition 2018.2.2` )
## IntelliJ Installation
IntelliJ IDEA comes in two versions, Community and Ultimate. The Community version is good enough for this course, but you can apply for a student Ultimate license [here](https://www.jetbrains.com/student/). Follow the download and installation instructions [here](https://www.jetbrains.com/help/idea/install-and-set-up-product.html). 
(No need if using lab pc in room 4210, the JDK 10 is in `C:\Program Files\Java\jdk-10.0.2`)
## Hello World in IntelliJ
1. Launch IntelliJ and create a new project
2. In the Project SDK bar, select the `jdk-10.0.2` folder we installed from the previous section, and click next. (For reference, mine was located at `C:\Program Files\Java\jdk-10.0.2`)
3. Name your project as `lab1`, select a location, and click Finish
4. Right click the `src` folder in left sidepanel, then choose `New`--`Java class` and create a new file called `Main.java`. Type following code in `Main.java`
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //TODO: testing java 10 features
        
    }
}
```
After then, click the green play button in the top right corner to run the program. After a couple of seconds, a terminal should pop up saying Hello World

To view the project files, on the left sidebar, click the "Project" button. The directory view is very similar to the view in Eclipse. At the bottom bar, click the "TODO" button to view any `//TODO` comments left by the TA throughout the code.

### Testing a Java 10 feature
In the main function, add the following lines
```
var x = 5;
System.out.println(x);
```

IntelliJ will highlight the `var` keyword in red. Click on it, press `alt`+`enter`, and select "Enable support for beta java version". Rerun the program to verify you have Java 10 working!

### Useful IntelliJ Shortcuts
* In your code, click on an error and press `alt`+`enter` for suggestions on how to fix the error
* `ctrl` + `alt` + `L` to automatically fix code formatting
* After clicking on a function, press `ctrl` + `alt` + `b` to jump to the implementation 
* `ctrl` + `shift` + `F` to search
* `ctrl` + `R` for find/replace menu
* `ctrl` + `shift` + `Enter` to smartly complete a statement
* `ctrl` + `shift` + `Space` for basic code completion
* `ctrl` + `shift` + `/` to add/remove block comment
* `ctrl` + `Q` for quick documentation
* More useful shortcuts can be found at:
https://www.jetbrains.com/help/idea/discover-intellij-idea.html

### Import and export in Intellij
#### Import
You may refer to the video for instructions to import our project into Intellij and add JUnit to your classpath:

https://youtu.be/58xRIW5D-Zo
#### Export
In order to export your project, you may open your project directory and compress your files as a zip file.

## Using git and GitHub
### GitHub
We will be using git for version control, and GitHub to store the code remotely. In order to create private code repositories on GitHub, you'll need to register for a free student pack [here](https://help.github.com/articles/applying-for-a-student-developer-pack/). Once your application is approved, create a private repository like [this](https://help.github.com/articles/create-a-repo/). Note the repository URL, e.g. `https://github.com/lamngok/helloworld.git`

### Git in Command Line
In your command prompt, navigate to the root directory for the Hello World program we just created. Type the following commands.  
* `git init`, which creates a git repo here
* `git add .`, which adds all the files into the repo
* `git commit -m "first commit"`, which commits the files being tracked
* `git remote add origin <YOUR REPOSITORY URL>`, which tells git where the remote repository is, and names it origin. Substitute your repository URL accordingly.
* `git push -u origin master`, which pushes your master branch to origin, in a branch also called master.  

Optionally, you may create a `.gitignore` to avoid tracking unnecessary files. This doesn't really matter, but you can Google it on your own.

### Git in Intellij
You may refer to the following video for steps to use git in intellij to create repositories, add files, commit, add remote repositories, and push.

https://youtu.be/AuyA87IpHzQ


### Useful commands
Here's a [list](https://confluence.atlassian.com/bitbucketserver/basic-git-commands-776639767.html) of useful git commands.

## IntelliJ vs. Eclipse
 There's plenty of articles online detailing the objective benefits of IntelliJ over Eclipse. Having used both, here's a list of subjective personal opinions.
 * Better refactoring
 * Better searching
 * Better window layouts
 * Better autocomplete, code simplification suggestions

## Assignment 1 
In the upcoming 6 labs, the concepts required to complete PA1 will be covered. The assignment will require you to implement an ASCII version of [Sokoban](https://en.wikipedia.org/wiki/Sokoban).  

## Lab Outcome Submission
1. Import our prepared assignment1.zip into IntelliJ
2. Create a git repository for your imported assignment 1 project
3. Create a private repositoy on GitHub
4. Add your GitHub repository as a remote repository for your imported assignment 1 project
5. Push your assignment 1 to your private repository on GitHub
6. **Submit a Word or PDF file including a screenshot of your imported IntelliJ project and a screenshot of your created Git repository on GitHub to [CASS](https://course.cse.ust.hk/cass).**

NOTE: The GitHub repository you created in this lab may be later used in other labs and assignments. To avoid potential plagiarism, please make sure that your created repository is private.