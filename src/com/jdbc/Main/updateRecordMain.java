package com.jdbc.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.jdbc.Connection.DBConnectionProvider;
import com.jdbc.DAO.EmployeeDAO;
import com.jdbc.Entity.Employee;

public class updateRecordMain {

	private static Connection con = null;
	private static PreparedStatement ps = null;

	public static void main(String[] args) {

		EmployeeDAO dao = new EmployeeDAO(DBConnectionProvider.getConnection());

		Employee emp = new Employee();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Id:-");
		int empId = scanner.nextInt();
		System.out.println("Enter the name:-");
		String empName = scanner.next();
		System.out.println("Enter the Address:-");
		String empAddress = scanner.next();

		emp.setId(empId);
		emp.setName(empName);
		emp.setAddress(empAddress);

		boolean editTest = dao.editRecord(emp);
		if (editTest) {
			System.out.println("Records updated Successfully!!!");
		} else {
			System.out.println("Something went wrong!!!");
		}

		DBConnectionProvider.closeResources_1(ps, con);
	}

}
