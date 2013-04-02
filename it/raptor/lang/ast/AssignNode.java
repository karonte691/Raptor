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

import it.raptor.lang.Interpreter;
import it.raptor.lang.InvalidTypeException;
import it.raptor.lang.SourcePosition;
import it.raptor.lang.runtime.ZemObject;

/**
 * Assignment (=) operator. Assigns a value to a variable.
 *
 *  
 */
public class AssignNode extends BinaryOpNode {
    public AssignNode(SourcePosition pos, Node var, Node expression) {
        super(pos, "set!", var, expression);
    }

    @Override
    public ZemObject eval(Interpreter interpreter) {
        Node left = getLeft();
        ZemObject value = getRight().eval(interpreter);
        if (left instanceof VariableNode) {
            String name = ((VariableNode) left).getName();
            interpreter.setVariable(name, value);
            return value;
        } else if (left instanceof LookupNode) {
            ((LookupNode) left).set(interpreter, value);
            return value;
        }
        throw new InvalidTypeException("Left hand of assignment must be a variable.", left.getPosition());
    }
}
