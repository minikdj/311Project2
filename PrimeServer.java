/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.net.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimeServer {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        
        try (
            ServerSocket serverSocket =
                new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
        	while(true) {
        		BigDecimal number = new BigDecimal(in.readLine());
        		System.out.println("number: " + number);
        		ArrayList<Integer> primes = new ArrayList<Integer>();
        		primeFactorization(number, primes);
            
        		out.println(printPrimesStr(number, primes));
        		primes.clear();
        	}
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
    
    public static void primeFactorization(BigDecimal num, ArrayList<Integer> primesList) {
    	
    	int maxVal = SquareRoot.sqRoot(num).intValue()+1;
    	for(int i = 2; i < maxVal; i++) {
    		if(num.intValue() % i == 0) {
    			primesList.add(i);
    			BigDecimal factoredNum = new BigDecimal(num.intValue()/i);
    			primeFactorization(factoredNum, primesList);
    			break;
    		} else if (i == maxVal - 1) {
    			primesList.add(num.intValue());
    		}
    	}
    }
    
    public static String printPrimesStr(BigDecimal number, ArrayList<Integer> primesList) {
    	String solution = Integer.toString(number.intValue()) + " := ";
    	for(int i = 0; i < primesList.size(); i++) {
    		int tempInt = primesList.get(i);
    		if(i == primesList.size()-1)
    			solution += Integer.toString(tempInt);
    		else
    			solution += Integer.toString(tempInt) + " * ";
    	}
    	return solution;
    }
    
}

