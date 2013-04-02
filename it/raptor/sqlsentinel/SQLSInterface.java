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

package it.raptor.sqlsentinel;


public  class SQLSInterface {
    public static String VULN_URL = "";
    public static boolean attack_success;
    public static String url_vuln_found = "";
    public static boolean exit_script = false;
    
    public static void cleanVar(){
        SQLSInterface.VULN_URL = "";
        SQLSInterface.attack_success = false;
        SQLSInterface.url_vuln_found = "";
    }

    public static String getVULN_URL() {
        return VULN_URL;
    }

    public static boolean isAttack_success() {
        return attack_success;
    }
}
