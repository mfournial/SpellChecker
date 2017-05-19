# SpellChecker

SpellChecker is a very fast way of checking the spelling of you documents.
For now, it takes a doc (eg .txt file) as an argument and outputs the misspelled words.

**Note it's still in development, soon versions will split working code and beta code**
  
### Example:   

    Reading from: src/example.txt  
    Numbers of words checked: 48  
    Numbers of words in dictionary: 354975  
    Misspelled words:  
    writtten   
    'text  
    deected  
    theee  
    'dont'  
    'dont  
    onkey  
    onkey 

### Acknowledgements 

SpellChecker was from a coursework done for *CS50* in 2015 written in **C** at the time.

## Known bugs and Issues

Numbers are not checked (like 1st ~ 1nd), and special characters are largely ignored, maybe there's a better correction to provide using those.

## What's next for Spellchecker

* Integrate it in a software or a GUI to do continuous spellchecking, which is what it is the best at.
(Load time >> check time). 
* Improve load time by saving Dictree state
* Support of other languages. Why not also mix languages?  
* Provide options with similar words.
* Compare it with an implementation with a radix tree, or switch to radix tree after like 3/4 letters not to waste space with empty arrays
