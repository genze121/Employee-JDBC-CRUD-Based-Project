package com.jdbc.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.jdbc.Connection.DBConnectionProvider;
import com.jdbc.DAO.EmployeeDAO;

public class deleteRecordMain {

	private static Connection con = null;
	private static PreparedStatement ps = null;

	public static void main(String[] args) {

		EmployeeDAO dao = new EmployeeDAO(DBConnectionProvider.getConnection());

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the id:-");
		int empId = scanner.nextInt();

		boolean deleteTest = dao.deleteRecord(empId);
		if (deleteTest) {
			System.out.println("Records are deleted Successfully!!!");
		} else {
			System.out.println("Something went wrong!!!");
		}

		DBConnectionProvider.closeResources_1(ps, con);
	}

}
