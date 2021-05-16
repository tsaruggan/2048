#Path separator is different on Windows versus Unix based OSes
ifeq ($(OS),Windows_NT)
	SEP=;
else
	SEP=:
endif

JFLAGS = -g
JCLASS = -cp "src$(SEP).$(SEP)./lib/junit-4.5.jar"
JC = javac
JVM = java

.PHONY: demo doc test

demo:
	$(JC) $(JCLASS) $(JFLAGS) src/Demo.java
	$(JVM) src/Demo

doc:
	doxygen doxConfig
	cd latex && $(MAKE)

test:
	# find . -name '*.class' -exec rm -f {} \;
	$(JC) $(JCLASS) $(JFLAGS) test/UnitTests.java
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore test.UnitTests

clean:
	rm -rf html
	rm -rf latex
	cd src
	rm **/*.class
	rm **/*/*.class
	cd ..
	cd test
	rm **/*.class