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
import it.raptor.lang.SourcePosition;
import it.raptor.lang.InvalidTypeException;
import it.raptor.lang.runtime.Dictionary;
import it.raptor.lang.runtime.ZemArray;
import it.raptor.lang.runtime.ZemObject;

/**
 * foreach control structure.
 *
 *  
 */
public class ForeachNode extends Node {
    private VariableNode onVariableNode;
    private Node asNode;
    private Node loopBody;

    public ForeachNode(SourcePosition pos, VariableNode onVariableNode, Node asNode, Node loopBody) {
        super(pos);
        this.onVariableNode = onVariableNode;
        this.asNode = asNode;
        this.loopBody = loopBody;
    }

    @Override
    public ZemObject eval(Interpreter interpreter) {
        ZemObject onVariable = interpreter.getVariable(onVariableNode.getName(), onVariableNode.getPosition());
        ZemObject ret = null;
        if (onVariable instanceof ZemArray) {
            String asVariableName = asNode.toString();
            for (ZemObject element : (ZemArray) onVariable) {
                interpreter.setVariable(asVariableName, element);
                ret = loopBody.eval(interpreter);
            }
            return ret;
        } else if (onVariable instanceof Dictionary) {
            DictionaryEntryNode entryNode = (DictionaryEntryNode) asNode;
            String keyName = ((VariableNode) entryNode.getKey()).getName();
            String valueName = ((VariableNode) entryNode.getValue()).getName();
            for (Map.Entry<ZemObject, ZemObject> entry : (Dictionary) onVariable) {
                interpreter.setVariable(keyName, entry.getKey());
                interpreter.setVariable(valueName, entry.getValue());
                ret = loopBody.eval(interpreter);
            }
            return ret;
        }
        throw new InvalidTypeException("foreach expects an array or dictionary.", onVariableNode.getPosition());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append("foreach ");
        sb.append(onVariableNode);
        sb.append(' ');
        sb.append(asNode);
        sb.append(' ');
        sb.append(loopBody);
        sb.append(')');
        return sb.toString();
    }
}
