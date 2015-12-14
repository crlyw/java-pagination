package com.imooc.page.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.page.constant.Constant;
import com.imooc.page.model.Pager;
import com.imooc.page.model.Student;
import com.imooc.page.service.StudentService;
import com.imooc.page.service.SublistStudentServiceImpl;
import com.imooc.page.util.StringUtil;

/**
 * Servlet implementation class SublistServlet
 */

public class SublistServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private StudentService studentService = new SublistStudentServiceImpl();
    /**
     * Default constructor. 
     */
    public SublistServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//接收request里的参数
		String stuName = request.getParameter("stuName"); //学生姓名
		
		//获取学生性别
	    int gender = Constant.DEFAULT_GENDER;
	    String genderStr = request.getParameter("gender");
	    if(genderStr!=null && !"".equals(genderStr.trim())){
	    	gender = Integer.parseInt(genderStr);
	    }
	    
	    int pageNum = Constant.DEFAULT_PAGE_NUM;//显示第几页数据
	    String pageNumStr = request.getParameter("pageNum");
	    if(pageNumStr!=null && !StringUtil.isNum(pageNumStr)){
	    	request.setAttribute("errorMsg", "参数传输错误");
	    	request.getRequestDispatcher("/sublistStudent.jsp").forward(request, response);
	    	return;
	    }
	    
	    int pageSize = 5; //每页显示多少条纪录
	    String pageSizeStr = request.getParameter("pageSize");
	    if(pageSizeStr!=null && !"".equals(pageSizeStr.trim())){
	    	pageSize = Integer.parseInt(pageSizeStr);
	    }
	    
		//组装查询条件
		Student searchModel = new Student();
		searchModel.setStuName(stuName);
		searchModel.setGender(gender);
		
		//调用service获取查询结果
		Pager<Student> result = studentService.findStudent(searchModel, pageNum, pageSize);
		
		//返回结果到页面
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("/sublistStudent.jsp").forward(request, response);
	}

}
