# Software Development Automation Application

## Group Details
Group ID- 2020-170

### Supervisor
Name: Ms. Dinuka Wijendra<br/>
Email: dinuka.w@sliit.lk

### Co-supervisor
Name: Ms. Jenny Krishara<br/>
Email: jenny.k@sliit.lk

#### Member 1 (Leader)
Name           : A. R. V. Anthony<br/>
ID number      : IT17140126<br/>
Email          : it17140126@my.sliit.lk<br/>
Responsible for: Predicting the software logic by analyzing the customer requirements (Component 1)
    
#### Member 2
Name           : G. M. Dilshan Prasad<br/>
ID number      : IT17397872<br/>
Email          : it17397872@my.sliit.lk<br/>
Responsible for: Automation of mapping requirements into UML diagrams (Component 2)

#### Member 3
Name           : S. U. Randunuge<br/>
ID number      : IT17146470<br/>
Email          : it17146470@my.sliit.lk<br/>
Responsible for: Detecting unidentified bugs and providing suggestions to eliminate the bugs (Component 3)

#### Member 4
Name           : S. R. A. M. P. A. Alahakoon<br/>
ID number      : IT17105712<br/>
Email          : it17105712@my.sliit.lk<br/>
Responsible for: Analyzing code quality and calculating code complexity (Component 4)

<br/>

## Project Description
The software development process is complicated and tedious. The complexity of the process often lead to unsuccessful projects, which result in financial and credibility loss.
This application automates four crucial process in the Software Development LifeCycle (SDLC), which increase the complexity. Idenfication of the logic of 
a software to be development from client requirements is a process in Requirement Elicitation phase of the SDLC. This process is automated in this 
application by the component 1. This will reduce the time consumed to analyze the requirements of a software. Second component of this application
uses the logic identified by the first component and generates diagrams to visualize the internal structure of the software to be developed.
This component reduces the time that will be spent in the design phase to design UML and other diagrams manually. It also increases the accuracy
of the diagrams as the automation is free from human errors. Debugging is one of the most complicated tasks in development of a software. Third 
component of this project focusses on identifying bugs that are not detected by available bug trackers and providing suggestions to eliminate the
identified bugs. This component increases the efficiency of the debugging process in testing and implementation phases of the SDLC by assisting the 
developers by providing suggestions. Code complexity and code quality are two crucial factors that affect the maintenance phase of SDLC. Fourth 
component of the project analyzes the code quality and calculates code complexity of a codebase. This component provides developers with suggestions
to reduce code complexity and improve code quality. <br/>

The Software Developement Automation Application provides important functionalities that can improve the efficiency and the quality of the software
development process.

## Research Question

### Main Research Question

Technology is updating day by day. In information technology field, software application development plays a major role. 
All the applications and websites people are currently using is developed by the software engineers.It takes a long time to make a 
successful project by the engineers.SDLC shows the steps to make a software application.
It takes a lot of time and effort to going through the phases of SDLC and completing an errorless system.All the work according to 
each phase is done by a human. So anywhere an error can be made. An error is making a risk because, if developer couldn’t figure it out 
at the beginning, it will be costly to correct it in the later phases in the SDLC. <br/>

>  ***How to make the Software Development process more efficient and less prone to errors?***

<br/>

### Individual Research Questions

1.  How to automate the process of analyzing customer requirement and predicting the logic of the software to be developed?
2.  How to automate the generation of accurate UML diagrams from information filtered out from the customer requirements?
3.  How to improve the functionality of software bug tracking systems by identifying new bug categories in java codes?
4.  How to improve software development process by improving code complexity and code quality?

<br/>

## Research Objective 
The main objective of this research is to reduce the software development complexity and make the process more efficient by 
improving the processes of requirement gathering, designing, implementation and maintenance stages of software development by using the proposed system.
                
                        
### Individual Objectives

1. Analyze user requirements and identify structure of the required software.
2. Map customer requirements into UML diagrams dynamically.
3. Enable developers to develop bug-free code effortlessly with efficient bug tracking.
4. Analyze and maintain code quality and code complexity with suitable recommendations.

<br/>

## Individual Components

### Component 1 - Logic Identification from Customer Requirements
Predicting the class names and the basic software logic by 
analyzing the Project proposal file and predicting the 
time range which consumes for the expecting software 
to be completed.

### Component 2 - Automatic Diagram Generator
This component is responsible for the dynamic mapping of customer requirements into diagrams. Diagram generator component accepts a text file,
 which contains the potential class names identified from the component 1, as input. **Entities.txt** provided in the repository is a sample input 
file that can be used as an input for this system. Then, ***testRelationshipGen.py*** python script analyzes the similarity between the class names
in the input file to identify relationships types. Class diagram, ER diagram, Object diagram and a new diagram named flow diagram are generated based on the
relationships derived from the similarity levels between classes. Flow diagram illustrates the similarity levels of a class selected by the user 
with other classes in the system. The output diagrams will be generated in a folder named output inside a folder named UML in the C partition of the hard disk.

### Component 3 - Bug Tracker
When a developer builds a system, it is easy to make mistakes which will be costly to correct
in the later phases of the Software Development Life Cycle (SDLC). Usually software
developers use new tools to track bugs in the code. The popular bug tracker systems which was
available was studied and found many failures in those systems. Main issue of the available
bug tracker systems is that, those systems do not focus on all the possible bugs that can be
occurred in a code, which gives the user a bad experience. The proposed bug tracker system
identifies the bugs which the available bug trackers does not identify. And this is a powerful
tool which makes the software development process easier and more user friendly. In this paper
it is focused on, “Analyzing bugs of the application and recommend the correct coding”.
According to the currently available bug tracker systems, they only find specific bugs for some
specific scenarios. That is why the proposed bug tracker system is important because it
identifies all the possible bugs that will not identified by the available tools. Other than showing
the identified bugs, this system will provide accurate suggestions to correct the code. So that,
it will make the development phase easier. All these bugs are identified by analyzing the
patterns in the code. If any pattern mismatches, the system will detect it as an error. This system
is tested with 30+ codes. When testing the system, almost all the time it gave more accurate
results by detecting the bugs correctly. Through this research the objective is to find a solution
to improve the bug tracking and recommendation process, which would be more efficient and
user friendly.

### Component 4 - Code Complexity and Quality Analyzer
This component will provide users a more efficient way to calculate complexity and 
detect issues in codes in order to reduce overall software development complexity. 
There are mainly 2 parts of this component. These parts are the complexity calculation 
and Code quality issues detection. These parts can be accessed throught the main interface 
seperatly as the users need. By using code complexity calculation component users can 
calculate the code complexity of given codes using 7 different metrices and show results 
visually so users can have a better understanding. By using the code quality issues detector, 
users can analyze code and find code quality issues (Currently system detects 6 issues) and 
use the system to get fixes for issues or recommendations.

<br/>

## Data Required to Execute the Code

*  **Global Vector for Word Representation** is a dataset required to convert the identified class names to their vector representations
   to calculate the similarity (Cosine Similarity) between the class names in order to identify the relationships between them. This dataset
   will be downloaded from the internet when the diagram generation is performed for the first time.

<br/>

## Instructions to Execute Code

### For Component 1
---Required files---


Time range prediction model
<br/>
	Developers number.txt, months.txt, dataSet_timeforecast.csv

Class Names prediction model
<br/>
	Functionalitiy.txt, classNames.txt

Time Prediction JFrame
<br/>
	months.txt, classNames.txt, TimePredict_research.py, classIdentification_research.py, Developers number.txt

Home Page JFrame
<br/>
	plainText_projectProposal.txt, Functionalitiy.txt

Please consider the applications of these text files
(which are included in the gitlab also) when changing 
their file paths.

### For Component 2

*  Graphviz, Graph Visualization Software must be installed to the computer to produce the diagrams as PNG images. An environment variable with the
   name **GRAPHVIZ_DOT** and the path must be set to the dot.exe inside the graphviz bin folder.
    <br/>
   Visit https://bobswift.atlassian.net/wiki/spaces/GVIZ/pages/20971549/How+to+install+Graphviz+software for steps to install Graphviz

*  The filepaths of **Entities.txt** and **testRelationshipGen.py** (available in repository) files must be set in **GeneratorUI.java** file in the component 2 
   directory of the project

### For Component 3

To work with the bug tracker, the user needs to load the main interface to navigate for the bug tracker interface. 
After opening the bug tracker main page, the user needs to browse a java code. Then have to select the "Track Bugs" button inorder to detect the bugs in the code.
After detecting the bugs, if the user needs to get a summary of the bug tracking process, User can select the "Summary" option given in the UI.

### For Component 4

In order to access the 2 parts of the component 4 users must use the main interface to navigate to the relavent parts.
In both parts users must select the java code file they want to calculate code complexity or detect code quality issues of to continue with the process.
In each part there will be a browse option to locate the file user wants.
Please make sure that the "FixedFiles" folder is in the project directory in order to system to save the fixed files.

<br/>

## Dependencies

*  **Python 3.6 or above**
*  **PyTorch and TorchText libraries must be installed**
*  **NLTK must be installed**
*  **Graphviz (also mentioned in instructions above)**
*  **NetBeans IDE preferable**
