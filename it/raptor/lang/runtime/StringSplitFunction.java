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
package it.raptor.lang.runtime;

import it.raptor.lang.Interpreter;
import it.raptor.lang.SourcePosition;


public class StringSplitFunction extends Function {
    private String[] parameters = {"stringToSplit", "splitChar", "ab"};
    @Override
    public int getParameterCount() {
        return 3;
    }

    @Override
    public String getParameterName(int index) {
        return parameters[index];
    }

    @Override
    public ZemObject getDefaultValue(int index) {
        return null;
    }

    @Override
    public ZemString eval(Interpreter interpreter, SourcePosition pos) {
        ZemString stringToSplit = interpreter.getVariable("stringToSplit", pos).toZString();
        ZemString splitChar = interpreter.getVariable("splitChar", pos).toZString();
        ZemNumber abz = new ZemNumber(interpreter.getVariable("ab", pos).toString());
        ZemString returnS = null;
        String retVar;
        try
        {
            int i = stringToSplit.toString().indexOf(splitChar.toString());
            if(i > 0){
                
                if(abz.intValue() == 0){
                     retVar = stringToSplit.toString().substring(0, i);
                }
                else {
                    retVar = stringToSplit.toString().substring(i + 1, stringToSplit.toString().length());
                }
                returnS = new ZemString(retVar);
            }
        }catch(Exception e){
            returnS = null;
        }
        return returnS;
    }
    
}
