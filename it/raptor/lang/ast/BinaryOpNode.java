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
package it.raptor.lang.ast;

import it.raptor.lang.SourcePosition;

/**
 * Base class for binary operators.
 *
 *  
 */
public abstract class BinaryOpNode extends Node {
    protected String operator;
    protected Node left;
    protected Node right;

    /**
     * @param left  Left operand
     * @param right Right operand
     */
    protected BinaryOpNode(SourcePosition pos, String operator, Node left, Node right) {
        super(pos);
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    /**
     * Return operator symbol
     */
    public String getName() {
        return operator;
    }

    /**
     * Get left operand
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Get right operand
     */
    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(getName());
        sb.append(' ');
        sb.append(left.toString());
        sb.append(' ');
        sb.append(right.toString());
        sb.append(')');
        return sb.toString();
    }
}
