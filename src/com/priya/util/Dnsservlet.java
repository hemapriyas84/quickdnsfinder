package com.priya.util;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Servlet implementation class Dnsservlet
 */

@WebServlet("/dns")
public class Dnsservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Dnsservlet() {
    	super();
        // TODO Auto-generated constructor stub
    	
    } 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> newDnsList = new ArrayList<String>()	;
		String nameserver= " SORRY ! NONE FOUND";
		String dnsName = request.getParameter("dnsDomainName");
		PrintWriter writer = response.getWriter();
		
		
		
		//NS LOOKUP LOGIC START 
		
		 //Scanner inputvalue =new Scanner(System.in);
		 
		 System.out.println("Enter Domain Name to find the Name Server::::");
		 
		 
	     // explain what program does and how to use it 
//	     if (args.length != 1)
//	     {
//	         System.err.println("Print out DNS Record for an Internet Address");
//	         System.err.println("USAGE: java DNSLookup domainName|domainAddress");
//	         System.exit(-1);
//	     }
	     try
	     
	     {
	    	 
	    	 InetAddress inetAddress;
	         // if first character is a digit then assume is an address
	         if (Character.isDigit(dnsName.charAt(0)))
	         {   // convert address from string representation to byte array
	             byte[] b = new byte[4];
	             String[] bytes = dnsName.split("[.]");
	             for (int i = 0; i < bytes.length; i++)
	             {
	                 b[i] = new Integer(bytes[i]).byteValue();
	             }
	             // get Internet Address of this host address
	             inetAddress = InetAddress.getByAddress(b);
	         }
	         else
	         {   // get Internet Address of this host name
	             inetAddress = InetAddress.getByName(dnsName);
	         }
	         // show the Internet Address as name/address
	         System.out.println(inetAddress.getHostName() + "/" + inetAddress.getHostAddress());
	         // get the default initial Directory Context
	         InitialDirContext iDirC = new InitialDirContext();
	         // get the DNS records for inetAddress
	         Attributes attributes = iDirC.getAttributes("dns:/" + inetAddress.getHostName());
	         // get an enumeration of the attributes and print them out
	         NamingEnumeration attributeEnumeration = attributes.getAll();
	         System.out.println("-- DNS INFORMATION --");
	         while (attributeEnumeration.hasMore())
	         {
	            // System.out.println("" + attributeEnumeration.next());
	             
	             newDnsList.add("" + attributeEnumeration.next());
	             
	         }
	         attributeEnumeration.close();
	     }
	     catch (UnknownHostException exception)
	     {
	         System.err.println("ERROR: No Internet Address for '" + dnsName + "'");
	     }
	     catch (NamingException exception)
	     {
	         System.err.println("ERROR: No DNS record for '" + dnsName + "'");
	     }
	     
	     catch (Exception exception)
	     {
	         System.err.println("UNKNOWNERROR: No DNS record for '" + dnsName +  exception.getMessage()+ "' " );
	     }
	     
	     for (String item : newDnsList) {
	    	 System.out.println("Value"+item);
			if(item.contains("NS:"))
			{
			 nameserver= item.substring(4);
			}
		}
	     
	     
	     StringTokenizer st2 = new StringTokenizer(nameserver, ",");
	     
	     writer.println("   <h1 align=center><font color =\"Blue\" >::::Name Servers found for domain: "+ dnsName + "::::</font></h1>");
	     writer.println("   <h2 align=center>-------------------------------------------------------------------------------------------</h2>");
	     while (st2.hasMoreElements()) {
			writer.println("   <h2 align=center><font color =\"Red\">"  + st2.nextElement() + "</font></h2>");
		}
		 writer.println("   <h2 align=center>-------------------------------------------------------------------------------------------</h2>");
		 writer.println("   <div style=\"text-align:center\"> <a href=\"https://www.hemapriya.com\">Check for another Domain</a></div>");
			
	     
	    writer.close();
	   
			
			
	     //NSLOOKUP END
	     
		
		
		
	}

}
