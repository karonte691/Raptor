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

import java.util.LinkedList;

/**
 * Buffer of Tokens. Used to provide lookahead into the stream from the lexer.
 * Also filters out comment tokens.
 *
 */
public class TokenBuffer {
    private LinkedList<Token> tokenQueue;
    private Lexer lexer;

    public TokenBuffer(Lexer lexer, int size) {
        this.lexer = lexer;
        tokenQueue = new LinkedList<Token>();

        // init queue
        for (int i = 0; i < size; i++) {
            Token token = nextToken();
            if (token == null) {
                break;
            }
            tokenQueue.addLast(token);
        }
    }

    private Token nextToken() {
        Token token = lexer.getNextToken();
        while (token != null && token.getType() == TokenType.COMMENT) {
            token = lexer.getNextToken();
        }
        return token;
    }

    public boolean isEmpty() {
        return tokenQueue.isEmpty();
    }

    public int size() {
        return tokenQueue.size();
    }

    public Token getToken(int i) {
        return tokenQueue.get(i);
    }

    /**
     * Read the next token from the lexer
     */
    public Token readToken() {
        if (tokenQueue.isEmpty()) {
            return null;
        }
        Token token = tokenQueue.removeFirst();

        // Add another token to the queue
        Token newToken = nextToken();
        if (newToken != null) {
            tokenQueue.addLast(newToken);
        }
        return token;
    }
}
