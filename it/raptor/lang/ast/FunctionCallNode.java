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

import java.util.ArrayList;
import java.util.List;

import it.raptor.lang.Interpreter;
import it.raptor.lang.SourcePosition;
import it.raptor.lang.runtime.ZemObject;

/**
 * Call to function.
 *
 *  
 */
public class FunctionCallNode extends Node {
    final static public List<Node> NO_ARGUMENTS = new ArrayList<Node>(0);

    private String functionName;
    private List<Node> arguments;

    public FunctionCallNode(SourcePosition pos, String functionName, List<Node> arguments) {
        super(pos);
        this.functionName = functionName;
        this.arguments = arguments;
    }

    @Override
    public ZemObject eval(Interpreter interpreter) {
        interpreter.checkFunctionExists(functionName, getPosition());
        // Evaluate the arguments
        List<ZemObject> args = new ArrayList<ZemObject>(arguments.size());
        for (Node node : arguments) {
            args.add(node.eval(interpreter));
        }
        return interpreter.callFunction(functionName, args, getPosition());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(functionName);
        for (Node arg : arguments) {
            sb.append(' ');
            sb.append(arg);
        }
        sb.append(')');
        return sb.toString();
    }
}
