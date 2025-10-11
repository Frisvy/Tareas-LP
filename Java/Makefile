.PHONY: all compile run clean
BIN := bin
SOURCES := $(shell find . -name "*.java" -not -path "./$(BIN)/*")
MAIN := Main   # cambia si tu Main tiene package (p.ej. app.Main)

all: compile

compile: $(BIN)/.stamp

$(BIN)/.stamp: $(SOURCES)
	@mkdir -p $(BIN)
	javac -d $(BIN) $(SOURCES)
	@touch $(BIN)/.stamp

run: compile
	java -cp $(BIN) $(MAIN)

clean:
	rm -rf $(BIN)