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
 * The type of token
 *
 *  
 */
public enum TokenType {
    COMMENT, ASSIGN,
    PLUS, MINUS, MULTIPLY, DIVIDE, MOD, POWER, // Math operators
    LPAREN, RPAREN, LBRACE, RBRACE, LBRACKET, RBRACKET, COMMA, COLON, END_STATEMENT,
    CONCAT, // String operators
    NOT, AND, OR, // Boolean operators
    LESS_THEN, LESS_EQUAL, EQUAL, GREATER_EQUAL, GREATER_THEN, NOT_EQUAL, // Comparison operators
    NUMBER, STRING_LITERAL, TRUE, FALSE, // Constant types
    IF, ELSE, WHILE, FOR_EACH, AS, // Control structures
    VARIABLE, FUNCTION, RETURN // Other keywords
}
