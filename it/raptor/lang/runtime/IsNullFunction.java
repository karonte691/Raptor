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


public class IsNullFunction extends Function {

    @Override
    public int getParameterCount() {
        return 1;
    }

    @Override
    public String getParameterName(int index) {
        return "string";
    }

    @Override
    public ZemObject getDefaultValue(int index) {
        return null;
    }

    @Override
    public ZemBoolean eval(Interpreter interpreter, SourcePosition pos) {
       ZemBoolean bol;
       try
       {
           ZemObject obj = interpreter.getVariable("string", pos);
           bol = ZemBoolean.FALSE;
       }catch(Exception e){
           bol = ZemBoolean.TRUE;
       }
       return bol;
    }
    
}
