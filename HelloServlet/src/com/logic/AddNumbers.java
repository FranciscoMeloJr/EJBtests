    package com.logic;
     
    import java.io.IOException;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.*;
     
    /**
     * Servlet implementation class AddNumbers
     */
    @WebServlet("/AddNumbers")
    public class AddNumbers extends HttpServlet {
    	private static final long serialVersionUID = 1L;
           
        /**
         * @see HttpServlet#HttpServlet()
         */
        public AddNumbers() {
            super();
            // TODO Auto-generated constructor stub
        }
     
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		String as=request.getParameter("a");
    		int a=Integer.parseInt(as);
    		
    		String bs=request.getParameter("b");
    		int b=Integer.parseInt(bs);
    		
    		Addition myobj=new Addition();
    		int result=myobj.add(a, b);
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		out.print("<h1>Actual Addition Result</h1>");
    		out.print("The result is ..... <b>"+result+"</b>");
    	}
     
    	/**
    	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    	}
     
    }