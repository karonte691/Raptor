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

import java.util.Map;

import it.raptor.lang.Interpreter;
import it.raptor.lang.InvalidTypeException;
import it.raptor.lang.SourcePosition;
import it.raptor.lang.runtime.Dictionary;
import it.raptor.lang.runtime.ZemArray;
import it.raptor.lang.runtime.ZemObject;

/**
 * foreach control structure.
 *
 *  
 */
public class LookupNode extends Node {
    private VariableNode varNode;
    private Node keyNode;

    public LookupNode(SourcePosition pos, VariableNode varNode, Node keyNode) {
        super(pos);
        this.varNode = varNode;
        this.keyNode = keyNode;
    }

    public ZemObject get(Interpreter interpreter) {
        ZemObject var = interpreter.getVariable(varNode.getName(), varNode.getPosition());
        ZemObject ret = null;
        if (var instanceof ZemArray) {
            int index = keyNode.eval(interpreter).toNumber(keyNode.getPosition()).intValue();
            return ((ZemArray) var).get(index);
        } else if (var instanceof Dictionary) {
            ZemObject key = keyNode.eval(interpreter);
            return ((Dictionary) var).get(key);
        }
        throw new InvalidTypeException("lookup expects an array or dictionary.", getPosition());
    }

    public void set(Interpreter interpreter, ZemObject result) {
        ZemObject var = interpreter.getVariable(varNode.getName(), varNode.getPosition());
        ZemObject ret = null;
        if (var instanceof ZemArray) {
            int index = keyNode.eval(interpreter).toNumber(keyNode.getPosition()).intValue();
            ((ZemArray) var).set(index, result);
            return;
        } else if (var instanceof Dictionary) {
            ZemObject key = keyNode.eval(interpreter);
            ((Dictionary) var).set(key, result);
            return;
        }
        throw new InvalidTypeException("lookup expects an array or dictionary.", getPosition());
    }

    @Override
    public ZemObject eval(Interpreter interpreter) {
        return get(interpreter);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append('(');
        sb.append("lookup ");
        sb.append(varNode);
        sb.append(' ');
        sb.append(keyNode);
        sb.append(')');
        return sb.toString();
    }
}
