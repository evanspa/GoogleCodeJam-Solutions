# Google Code Jam 'Store Credit' challenge

+ Project URL: http://code.google.com/codejam/contest/dashboard?c=351101#s=p0

The folder containing the solution source is: src/main/clojure/name/paulevans/googlecodejam/storecredit

To run the code, you'll need a Java 6 runtime. It would also be helpful to install Leiningen, a build tool for Clojure: https://github.com/technomancy/leiningen.  

You'll also need to edit the src/main/clojure/name/paulevans/googlecodejam/storecredit/main.clj source file, and update the value of 'testcases-root-folder' to match the folder structure on your own machine.

Once you have Leiningen installed (and on your path), and you've updated main.clj, simply do the following:

shell> lein repl
name.paulevans.googlecodejam.storecredit.main=> (run)

Then do the following to run the program using Google's "Small input" file:

name.paulevans.googlecodejam.storecredit.main=> (run processed-medium)

Then to run the program using Google's "Large input" file:

name.paulevans.googlecodejam.storecredit.main=> (run processed-large)


