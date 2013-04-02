/*

 RaptorDevelop v 0.1

 Copyright (C) 2013  Luca Magistrelli <blackstorm010[at]gmail[dot]com>

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
package raptordevelop;

import it.raptor.lang.Interpreter;
import it.raptor.sqlsentinel.SQLSInterface;
import java.io.File;

/*
	Use this little program for testing your raptor script

	It needs:
		-file.rl -> raptor script
		-url_to_test -> url to test for sql injection
*/

public class RaptorDevelop {


    public static void main(String[] args) {
        Interpreter raptor = null;
        File raptorFile = null;
        String returnUrlVuln = new String();
        String url_to_test = null;
        
        try
        {
            if(args.length <= 0)
            {
                System.out.println("[ERROR]Missing raptor file");
                System.out.println("Usage: java -jar RaptorDevelop.jar file.rl url_to_test");
                return;
            }
            
            try
            {
                raptorFile = new File(args[0]);
            } catch(Exception ex){
                System.out.println("[ERROR] Could not open the file");
                return;
            }
            url_to_test = args[1];
            
            SQLSInterface.url_vuln_found = url_to_test;
            
            raptor = new Interpreter();
            
            raptor.eval(raptorFile);
            
            if(SQLSInterface.isAttack_success())
            {
                System.out.println("attack: success");
                System.out.println("url vuln: " + SQLSInterface.getVULN_URL());
            }
            else
            {
                System.out.println("attack: failed");
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
