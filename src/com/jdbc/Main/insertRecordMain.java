package com.jdbc.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.jdbc.Connection.DBConnectionProvider;
import com.jdbc.DAO.EmployeeDAO;
import com.jdbc.Entity.Employee;

public class insertRecordMain {

	private static Connection con = null;
	private static PreparedStatement ps = null;

	public static void main(String[] args) {

		EmployeeDAO dao = new EmployeeDAO(DBConnectionProvider.getConnection());

		Employee emp = new Employee();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Employee Id:-");
			int empId = scanner.nextInt();
			System.out.println("Enter Employee Name:-");
			String empName = scanner.next();
			System.out.println("Enter Employee Address:-");
			String empAddress = scanner.next();
			System.out.println("Enter Employee Salary:-");
			int empSalary = scanner.nextInt();

			emp.setId(empId);
			emp.setName(empName);
			emp.setAddress(empAddress);
			emp.setSalary(empSalary);

			boolean insertTest = dao.insertRecord(emp);
			if (insertTest) {
				System.out.println("Records are inserted successfully!!!");
			} else {
				System.out.println("Something went wrong!!!");
			}

			System.out.println("Do you want to insert more records [Yes|No]");
			String option = scanner.next();
			if (option.equalsIgnoreCase("NO")) {
				break;
			}

		}

		DBConnectionProvider.closeResources_1(ps, con);

	}

}
