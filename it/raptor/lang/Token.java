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

/**
 * A <a href="http://en.wikipedia.org/wiki/Lexical_analysis#Token">token</a>
 * is a categorized block of text that represents an atomic element in the source code.
 *
 *  
 */
public class Token {
    private SourcePosition position;
    private TokenType type;
    private String text;

    public Token(SourcePosition position, TokenType type, String text) {
        this.position = position;
        this.type = type;
        this.text = text;
    }

    public SourcePosition getPosition() {
        return position;
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Token))
            return false;
        Token other = (Token) obj;
        return this.type == other.type && this.text.equals(other.text) && this.position.equals(other.position);
    }

    @Override
    public String toString() {
        return type + ",'" + text + "'";
    }
}
