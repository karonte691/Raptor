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
 * Base class for unary operators.
 *
 *  
 */
public abstract class UnaryOpNode extends Node {
    protected String operator;
    protected Node operand;

    public UnaryOpNode(SourcePosition pos, String operator, Node operand) {
        super(pos);
        this.operator = operator;
        this.operand = operand;
    }

    /**
     * Return operator symbol
     */
    public String getName() {
        return operator;
    }

    /**
     * Return operand
     */
    public Node getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(getName());
        sb.append(' ');
        sb.append(operand.toString());
        sb.append(')');
        return sb.toString();
    }
}
