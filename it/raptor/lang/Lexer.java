/*

 Raptor Interpreter engine

 Copyright (C) 2013  Luca Magistrelli <blackstorm010[at]gmail[dot]com>

 Based on ZemScript interpreter by Cameron Zemek Copyright (c) 2008 grom[at]zeminvaders[dot]net

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

*/
package it.raptor.lang;

import java.io.IOException;
import java.io.Reader;

/**
 * The <a href="http://en.wikipedia.org/wiki/Lexical_analysis#Scanner">lexer</a>
 * is used to read characters and identify tokens and pass them to the parser
 *
 */
public class Lexer {
    static final private int END_OF_FILE = -1;

    private int lineNo = 1;
    private int columnNo = 1;
    private PeekReader in;

    public Lexer(Reader in) throws IOException {
        this.in = new PeekReader(in, 2);
    }

    private int lookAhead(int i) {
        return in.peek(i);
    }

    private int read() {
        try {
            int c = in.read();;
            if (c == '\n') {
                lineNo++;
                columnNo = 0;
            }
            columnNo++;
            return c;
        } catch (IOException e) {
            throw new LexerException(e.getMessage(), lineNo, columnNo);
        }
    }

    private void close() {
        try {
            in.close();
        } catch (IOException e) {
        }
    }

    private int next() {
        read();
        return lookAhead(1);
    }

    private char match(char c) {
        int input = read();
        if (input != c) {
            String inputChar = (input != END_OF_FILE) ? "" + (char) input : "END_OF_FILE";
            throw new LexerException("Expected '" + c + "' but got '" + inputChar + "'", lineNo, columnNo);
        }
        return c;
    }

    private String match(String str) {
        for (int i = 0; i < str.length(); i++) {
            match(str.charAt(i));
        }
        return str;
    }

    private Token createToken(TokenType type, char c) {
        SourcePosition pos = new SourcePosition(lineNo, columnNo);
        match(c);
        return new Token(pos, type, "" + c);
    }

    private Token createToken(TokenType type, String str) {
        SourcePosition pos = new SourcePosition(lineNo, columnNo);
        match(str);
        return new Token(pos, type, str);
    }

    public Token getNextToken() {
        int character = lookAhead(1);
        // Skip whitespace
        while (character == ' ' || character == '\t' ||
                character == '\r' || character == '\n') {
            character = next();
        }
        switch (character) {
            case END_OF_FILE: {
                // End of character stream.
                // Return null to indicate end of token stream
                close();
                return null;
            }
            case ';': {
                return createToken(TokenType.END_STATEMENT, ";");
            }
            case '+': {
                return createToken(TokenType.PLUS, '+');
            }
            case '-': {
                return createToken(TokenType.MINUS, '-');
            }
            case '*': {
                return createToken(TokenType.MULTIPLY, '*');
            }
            case '/': {
                int char2 = lookAhead(2);
                if (char2 == '/') {
                    return matchLineComment();
                } else if (char2 == '*') {
                    return matchBlockComment();
                } else {
                    return createToken(TokenType.DIVIDE, '/');
                }
            }
            case '%': {
                return createToken(TokenType.MOD, '%');
            }
            case '^': {
                return createToken(TokenType.POWER, '^');
            }
            case ',': {
                return createToken(TokenType.COMMA, ',');
            }
            case '~': {
                return createToken(TokenType.CONCAT, '~');
            }
            case ':': {
                return createToken(TokenType.COLON, ':');
            }
            case '(': {
                return createToken(TokenType.LPAREN, '(');
            }
            case ')': {
                return createToken(TokenType.RPAREN, ')');
            }
            case '{': {
                return createToken(TokenType.LBRACE, '{');
            }
            case '}': {
                return createToken(TokenType.RBRACE, '}');
            }
            case '[': {
                return createToken(TokenType.LBRACKET, '[');
            }
            case ']': {
                return createToken(TokenType.RBRACKET, ']');
            }
            case '=': {
                if (lookAhead(2) == '=') {
                    return createToken(TokenType.EQUAL, "==");
                } else {
                    return createToken(TokenType.ASSIGN, '=');
                }
            }
            case '|': {
                return createToken(TokenType.OR, "||");
            }
            case '&': {
                return createToken(TokenType.AND, "&&");
            }
            case '!': {
                if (lookAhead(2) == '=') {
                    return createToken(TokenType.NOT_EQUAL, "!=");
                } else {
                    return createToken(TokenType.NOT, '!');
                }
            }
            case '<': {
                if (lookAhead(2) == '=') {
                    return createToken(TokenType.LESS_EQUAL, "<=");
                } else {
                    return createToken(TokenType.LESS_THEN, '<');
                }
            }
            case '>': {
                if (lookAhead(2) == '=') {
                    return createToken(TokenType.GREATER_EQUAL, ">=");
                } else {
                    return createToken(TokenType.GREATER_THEN, '>');
                }
            }
            case '\'':
            case '"': {
                return matchStringLiteral((char) character);
            }
            default: {
                if (character >= '0' && character <= '9') {
                    return matchNumber();
                } else if ((character >= 'A' && character <= 'Z') ||
                    (character >= 'a' && character <= 'z') ||
                    character == '_') {
                    return matchIdentifier();
                } else {
                    throw new LexerException("Unexpected '" + ((char) character) + "' character", lineNo, columnNo);
                }
            }
        }
    }

    private Token matchLineComment() {
        SourcePosition pos = new SourcePosition(lineNo, columnNo);
        match("//");
        StringBuilder sb = new StringBuilder();
        int character = lookAhead(1);
        while (character != '\r' && character != '\n' && character != END_OF_FILE) {
            sb.append((char) character);
            character = next();
        }
        return new Token(pos, TokenType.COMMENT, sb.toString());
    }

    private Token matchBlockComment() {
        SourcePosition pos = new SourcePosition(lineNo, columnNo);
        match("/*");
        StringBuilder sb = new StringBuilder();
        int character = lookAhead(1);
        while (true) {
            if (character == END_OF_FILE) {
                throw new LexerException("Expecting */ but found end of file", lineNo, columnNo);
            }
            if (lookAhead(1) == '*' && lookAhead(2) == '/') {
                break;
            }
            sb.append((char) character);
            character = next();
        }
        match("*/");
        return new Token(pos, TokenType.COMMENT, sb.toString());
    }

    private Token matchNumber() {
        SourcePosition pos = new SourcePosition(lineNo, columnNo);
        StringBuilder sb = new StringBuilder();
        boolean decimal = false;
        int character = lookAhead(1);
        while ((character >= '0' && character <= '9') || character == '.') {
            if (decimal && character == '.') {
                throw new LexerException("Unexcepted '.' character", lineNo, columnNo);
            } else if (character == '.') {
                decimal = true;
            }
            sb.append((char) character);
            character = next();
        }
        return new Token(pos, TokenType.NUMBER, sb.toString());
    }

    /**
     * An identifier is either a keyword, function, or variable
     *
     * @return Token
     */
    private Token matchIdentifier() {
        SourcePosition pos = new SourcePosition(lineNo, columnNo);
        StringBuilder sb = new StringBuilder();
        int character = lookAhead(1);
        while ((character >= 'a' && character <= 'z') ||
                (character >= 'A' && character <= 'Z') ||
                (character >= '0' && character <= '9') ||
                character == '_') {
            sb.append((char) character);
            character = next();
        }
        String word = sb.toString();
        if (word.equals("true")) {
            return new Token(pos, TokenType.TRUE, word);
        } else if (word.equals("false")) {
            return new Token(pos, TokenType.FALSE, word);
        } else if (word.equals("if")) {
            return new Token(pos, TokenType.IF, word);
        } else if (word.equals("else")) {
            return new Token(pos, TokenType.ELSE, word);
        } else if (word.equals("while")) {
            return new Token(pos, TokenType.WHILE, word);
        } else if (word.equals("foreach")) {
            return new Token(pos, TokenType.FOR_EACH, word);
        } else if (word.equals("as")) {
            return new Token(pos, TokenType.AS, word);
        } else if (word.equals("function")) {
            return new Token(pos, TokenType.FUNCTION, word);
        } else if (word.equals("return")) {
            return new Token(pos, TokenType.RETURN, word);
        } else {
            return new Token(pos, TokenType.VARIABLE, word);
        }
    }

    private Token matchStringLiteral(char quote) {
        SourcePosition pos = new SourcePosition(lineNo, columnNo);
        match(quote);
        StringBuilder sb = new StringBuilder();
        int character = lookAhead(1);
        while (character != quote && character != END_OF_FILE) {
            sb.append((char) character);
            character = next();
        }
        match(quote);
        return new Token(pos, TokenType.STRING_LITERAL, sb.toString());
    }
}
