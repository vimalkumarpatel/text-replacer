text-replacer
=============

A utility program created to sanitize log files.

#Usage

execute the program using following arguments:
1. log file full path
2. properties file full path
3. one of the three REPLACEMENT type [IP_REPLACEMENT, LITERAL_REPLACEMENT, WORD_REPLACEMENT]

ex: c:\>net.vimalpatel.main C:\Users\...\log.file C:\Users\...\properties.file IP_REPLACEMENT


or simply open this project in eclipse and run :P

notes:

1. while replacement, if you want the 22 and 222 be replaced, then put 222 before 22 in the property file.

# Libraries used:
-	Apache commons.io for File utils
