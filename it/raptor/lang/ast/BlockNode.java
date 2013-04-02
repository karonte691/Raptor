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
import it.raptor.lang.SourcePosition;
import it.raptor.lang.runtime.ZemObject;
import it.raptor.sqlsentinel.SQLSInterface;
import java.util.List;

/**
 * A block is a group of statements.
 *
 *  
 */
public class BlockNode extends Node {
    private List<Node> statements;

    public BlockNode(SourcePosition pos, List<Node> statements) {
        super(pos);
        this.statements = statements;
    }

    public Node get(int index) {
        return statements.get(index);
    }

    protected List<Node> getStatements() {
        return statements;
    }

    @Override
    public ZemObject eval(Interpreter interpreter) {
        ZemObject ret = null;
        if(!SQLSInterface.exit_script){
            for (Node statement : statements) {
                if(!SQLSInterface.exit_script){
                    ret = statement.eval(interpreter);
                }
                else
                    break;
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Node node : statements) {
            sb.append(node.toString());
        }
        sb.append(')');
        return sb.toString();
    }
}
